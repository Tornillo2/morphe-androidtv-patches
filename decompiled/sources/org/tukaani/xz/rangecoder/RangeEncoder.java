package org.tukaani.xz.rangecoder;

import com.amazon.ion.impl.IonUTF8;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes4.dex */
public final class RangeEncoder extends RangeCoder {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static final int BIT_PRICE_SHIFT_BITS = 4;
    public static final int MOVE_REDUCING_BITS = 4;
    public static /* synthetic */ Class class$org$tukaani$xz$rangecoder$RangeEncoder;
    public static final int[] prices;
    public final byte[] buf;
    public int bufPos;
    public byte cache;
    public int cacheSize;
    public long low;
    public int range;

    static {
        if (class$org$tukaani$xz$rangecoder$RangeEncoder == null) {
            class$org$tukaani$xz$rangecoder$RangeEncoder = class$("org.tukaani.xz.rangecoder.RangeEncoder");
        }
        $assertionsDisabled = true;
        prices = new int[128];
        for (int i = 8; i < 2048; i += 16) {
            int i2 = i;
            int i3 = 0;
            for (int i4 = 0; i4 < 4; i4++) {
                i2 *= i2;
                i3 <<= 1;
                while (((-65536) & i2) != 0) {
                    i2 >>>= 1;
                    i3++;
                }
            }
            prices[i >> 4] = 161 - i3;
        }
    }

    public RangeEncoder(int i) {
        this.buf = new byte[i];
        reset();
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getBitPrice(int i, int i2) {
        if ($assertionsDisabled || i2 == 0 || i2 == 1) {
            return prices[(i ^ ((-i2) & IonUTF8.UNICODE_MAX_TWO_BYTE_SCALAR)) >>> 4];
        }
        throw new AssertionError();
    }

    public static int getBitTreePrice(short[] sArr, int i) {
        int length = i | sArr.length;
        int bitPrice = 0;
        do {
            int i2 = length & 1;
            length >>>= 1;
            bitPrice += getBitPrice(sArr[length], i2);
        } while (length != 1);
        return bitPrice;
    }

    public static int getDirectBitsPrice(int i) {
        return i << 4;
    }

    public static int getReverseBitTreePrice(short[] sArr, int i) {
        int length = i | sArr.length;
        int bitPrice = 0;
        int i2 = 1;
        do {
            int i3 = length & 1;
            length >>>= 1;
            bitPrice += getBitPrice(sArr[i2], i3);
            i2 = (i2 << 1) | i3;
        } while (length != 1);
        return bitPrice;
    }

    public void encodeBit(short[] sArr, int i, int i2) {
        short s = sArr[i];
        int i3 = this.range;
        int i4 = (i3 >>> 11) * s;
        if (i2 == 0) {
            this.range = i4;
            sArr[i] = (short) (s + ((2048 - s) >>> 5));
        } else {
            this.low += ((long) i4) & 4294967295L;
            this.range = i3 - i4;
            sArr[i] = (short) (s - (s >>> 5));
        }
        int i5 = this.range;
        if (((-16777216) & i5) == 0) {
            this.range = i5 << 8;
            shiftLow();
        }
    }

    public void encodeBitTree(short[] sArr, int i) {
        int length = sArr.length;
        int i2 = 1;
        do {
            length >>>= 1;
            int i3 = i & length;
            encodeBit(sArr, i2, i3);
            i2 <<= 1;
            if (i3 != 0) {
                i2 |= 1;
            }
        } while (length != 1);
    }

    public void encodeDirectBits(int i, int i2) {
        do {
            int i3 = this.range >>> 1;
            this.range = i3;
            i2--;
            this.low += (long) ((0 - ((i >>> i2) & 1)) & i3);
            if (((-16777216) & i3) == 0) {
                this.range = i3 << 8;
                shiftLow();
            }
        } while (i2 != 0);
    }

    public void encodeReverseBitTree(short[] sArr, int i) {
        int length = i | sArr.length;
        int i2 = 1;
        do {
            int i3 = length & 1;
            length >>>= 1;
            encodeBit(sArr, i2, i3);
            i2 = (i2 << 1) | i3;
        } while (length != 1);
    }

    public int finish() {
        for (int i = 0; i < 5; i++) {
            shiftLow();
        }
        return this.bufPos;
    }

    public int getPendingSize() {
        return this.bufPos + this.cacheSize + 4;
    }

    public void reset() {
        this.low = 0L;
        this.range = -1;
        this.cache = (byte) 0;
        this.cacheSize = 1;
        this.bufPos = 0;
    }

    public final void shiftLow() {
        long j = this.low;
        int i = (int) (j >>> 32);
        if (i != 0 || j < 4278190080L) {
            byte b = this.cache;
            while (true) {
                byte[] bArr = this.buf;
                int i2 = this.bufPos;
                this.bufPos = i2 + 1;
                bArr[i2] = (byte) (b + i);
                int i3 = this.cacheSize - 1;
                this.cacheSize = i3;
                if (i3 == 0) {
                    break;
                } else {
                    b = 255;
                }
            }
            this.cache = (byte) (this.low >>> 24);
        }
        this.cacheSize++;
        this.low = (this.low & 16777215) << 8;
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(this.buf, 0, this.bufPos);
    }
}
