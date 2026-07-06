package org.tukaani.xz.lzma;

import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMACoder;
import org.tukaani.xz.rangecoder.RangeDecoder;

/* JADX INFO: loaded from: classes4.dex */
public final class LZMADecoder extends LZMACoder {
    public final LiteralDecoder literalDecoder;
    public final LZDecoder lz;
    public final LengthDecoder matchLenDecoder;
    public final RangeDecoder rc;
    public final LengthDecoder repLenDecoder;

    public class LengthDecoder extends LZMACoder.LengthCoder {
        public LengthDecoder() {
            super();
        }

        public int decode(int i) throws IOException {
            return LZMADecoder.this.rc.decodeBit(this.choice, 0) == 0 ? LZMADecoder.this.rc.decodeBitTree(this.low[i]) + 2 : LZMADecoder.this.rc.decodeBit(this.choice, 1) == 0 ? LZMADecoder.this.rc.decodeBitTree(this.mid[i]) + 10 : LZMADecoder.this.rc.decodeBitTree(this.high) + 18;
        }
    }

    public class LiteralDecoder extends LZMACoder.LiteralCoder {
        public LiteralSubdecoder[] subdecoders;

        public class LiteralSubdecoder extends LZMACoder.LiteralCoder.LiteralSubcoder {
            public LiteralSubdecoder() {
                super();
            }

            public void decode() throws IOException {
                int iDecodeBit = 1;
                if (LZMADecoder.this.state.isLiteral()) {
                    do {
                        iDecodeBit = LZMADecoder.this.rc.decodeBit(this.probs, iDecodeBit) | (iDecodeBit << 1);
                    } while (iDecodeBit < 256);
                } else {
                    LZMADecoder lZMADecoder = LZMADecoder.this;
                    int i = lZMADecoder.lz.getByte(lZMADecoder.reps[0]);
                    int i2 = 256;
                    int i3 = 1;
                    do {
                        i <<= 1;
                        int i4 = i & i2;
                        int iDecodeBit2 = LZMADecoder.this.rc.decodeBit(this.probs, i2 + i4 + i3);
                        i3 = (i3 << 1) | iDecodeBit2;
                        i2 &= (~i4) ^ (0 - iDecodeBit2);
                    } while (i3 < 256);
                    iDecodeBit = i3;
                }
                LZMADecoder.this.lz.putByte((byte) iDecodeBit);
                LZMADecoder.this.state.updateLiteral();
            }
        }

        public LiteralDecoder(int i, int i2) {
            super(i, i2);
            this.subdecoders = new LiteralSubdecoder[1 << (i + i2)];
            int i3 = 0;
            while (true) {
                LiteralSubdecoder[] literalSubdecoderArr = this.subdecoders;
                if (i3 >= literalSubdecoderArr.length) {
                    return;
                }
                literalSubdecoderArr[i3] = new LiteralSubdecoder();
                i3++;
            }
        }

        public void decode() throws IOException {
            this.subdecoders[getSubcoderIndex(LZMADecoder.this.lz.getByte(0), LZMADecoder.this.lz.pos)].decode();
        }

        public void reset() {
            int i = 0;
            while (true) {
                LiteralSubdecoder[] literalSubdecoderArr = this.subdecoders;
                if (i >= literalSubdecoderArr.length) {
                    return;
                }
                literalSubdecoderArr[i].reset();
                i++;
            }
        }
    }

    public LZMADecoder(LZDecoder lZDecoder, RangeDecoder rangeDecoder, int i, int i2, int i3) {
        super(i3);
        this.matchLenDecoder = new LengthDecoder();
        this.repLenDecoder = new LengthDecoder();
        this.lz = lZDecoder;
        this.rc = rangeDecoder;
        this.literalDecoder = new LiteralDecoder(i, i2);
        reset();
    }

    public void decode() throws IOException {
        this.lz.repeatPending();
        while (this.lz.hasSpace()) {
            int i = this.lz.pos & this.posMask;
            if (this.rc.decodeBit(this.isMatch[this.state.state], i) == 0) {
                this.literalDecoder.decode();
            } else {
                this.lz.repeat(this.reps[0], this.rc.decodeBit(this.isRep, this.state.state) == 0 ? decodeMatch(i) : decodeRepMatch(i));
            }
        }
        this.rc.normalize();
        if (!this.rc.isInBufferOK()) {
            throw new CorruptedInputException();
        }
    }

    public final int decodeMatch(int i) throws IOException {
        this.state.updateMatch();
        int[] iArr = this.reps;
        iArr[3] = iArr[2];
        iArr[2] = iArr[1];
        iArr[1] = iArr[0];
        int iDecode = this.matchLenDecoder.decode(i);
        int iDecodeBitTree = this.rc.decodeBitTree(this.distSlots[LZMACoder.getDistState(iDecode)]);
        if (iDecodeBitTree < 4) {
            this.reps[0] = iDecodeBitTree;
            return iDecode;
        }
        int i2 = iDecodeBitTree >> 1;
        int[] iArr2 = this.reps;
        int i3 = (2 | (iDecodeBitTree & 1)) << (i2 - 1);
        iArr2[0] = i3;
        if (iDecodeBitTree < 14) {
            iArr2[0] = this.rc.decodeReverseBitTree(this.distSpecial[iDecodeBitTree - 4]) | i3;
            return iDecode;
        }
        iArr2[0] = (this.rc.decodeDirectBits(i2 - 5) << 4) | i3;
        int[] iArr3 = this.reps;
        iArr3[0] = iArr3[0] | this.rc.decodeReverseBitTree(this.distAlign);
        return iDecode;
    }

    public final int decodeRepMatch(int i) throws IOException {
        int i2;
        if (this.rc.decodeBit(this.isRep0, this.state.state) != 0) {
            if (this.rc.decodeBit(this.isRep1, this.state.state) == 0) {
                i2 = this.reps[1];
            } else {
                if (this.rc.decodeBit(this.isRep2, this.state.state) == 0) {
                    i2 = this.reps[2];
                } else {
                    int[] iArr = this.reps;
                    int i3 = iArr[3];
                    iArr[3] = iArr[2];
                    i2 = i3;
                }
                int[] iArr2 = this.reps;
                iArr2[2] = iArr2[1];
            }
            int[] iArr3 = this.reps;
            iArr3[1] = iArr3[0];
            iArr3[0] = i2;
        } else if (this.rc.decodeBit(this.isRep0Long[this.state.state], i) == 0) {
            this.state.updateShortRep();
            return 1;
        }
        this.state.updateLongRep();
        return this.repLenDecoder.decode(i);
    }

    @Override // org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        super.reset();
        this.literalDecoder.reset();
        this.matchLenDecoder.reset();
        this.repLenDecoder.reset();
    }
}
