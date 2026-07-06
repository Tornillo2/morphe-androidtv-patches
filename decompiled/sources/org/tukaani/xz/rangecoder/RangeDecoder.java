package org.tukaani.xz.rangecoder;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes4.dex */
public final class RangeDecoder extends RangeCoder {
    public static final int INIT_SIZE = 5;
    public final byte[] buf;
    public int pos = 0;
    public int end = 0;
    public int range = 0;
    public int code = 0;

    public RangeDecoder(int i) {
        this.buf = new byte[i - 5];
    }

    public int decodeBit(short[] sArr, int i) throws IOException {
        normalize();
        short s = sArr[i];
        int i2 = this.range;
        int i3 = (i2 >>> 11) * s;
        int i4 = this.code;
        if ((i4 ^ Integer.MIN_VALUE) < (Integer.MIN_VALUE ^ i3)) {
            this.range = i3;
            sArr[i] = (short) (s + ((2048 - s) >>> 5));
            return 0;
        }
        this.range = i2 - i3;
        this.code = i4 - i3;
        sArr[i] = (short) (s - (s >>> 5));
        return 1;
    }

    public int decodeBitTree(short[] sArr) throws IOException {
        int iDecodeBit = 1;
        do {
            iDecodeBit = decodeBit(sArr, iDecodeBit) | (iDecodeBit << 1);
        } while (iDecodeBit < sArr.length);
        return iDecodeBit - sArr.length;
    }

    public int decodeDirectBits(int i) throws IOException {
        int i2 = 0;
        do {
            normalize();
            int i3 = this.range >>> 1;
            this.range = i3;
            int i4 = this.code;
            int i5 = (i4 - i3) >>> 31;
            this.code = i4 - (i3 & (i5 - 1));
            i2 = (i2 << 1) | (1 - i5);
            i--;
        } while (i != 0);
        return i2;
    }

    public int decodeReverseBitTree(short[] sArr) throws IOException {
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (true) {
            int iDecodeBit = decodeBit(sArr, i3);
            i3 = (i3 << 1) | iDecodeBit;
            int i4 = i2 + 1;
            i |= iDecodeBit << i2;
            if (i3 >= sArr.length) {
                return i;
            }
            i2 = i4;
        }
    }

    public boolean isFinished() {
        return this.pos == this.end && this.code == 0;
    }

    public boolean isInBufferOK() {
        return this.pos <= this.end;
    }

    public void normalize() throws IOException {
        int i = this.range;
        if (((-16777216) & i) == 0) {
            try {
                int i2 = this.code << 8;
                byte[] bArr = this.buf;
                int i3 = this.pos;
                this.pos = i3 + 1;
                this.code = i2 | (bArr[i3] & 255);
                this.range = i << 8;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new CorruptedInputException();
            }
        }
    }

    public void prepareInputBuffer(DataInputStream dataInputStream, int i) throws IOException {
        if (i < 5) {
            throw new CorruptedInputException();
        }
        if (dataInputStream.readUnsignedByte() != 0) {
            throw new CorruptedInputException();
        }
        this.code = dataInputStream.readInt();
        this.range = -1;
        this.pos = 0;
        int i2 = i - 5;
        this.end = i2;
        dataInputStream.readFully(this.buf, 0, i2);
    }
}
