package org.tukaani.xz.lz;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LZEncoder {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static final int MF_BT4 = 20;
    public static final int MF_HC4 = 4;
    public static /* synthetic */ Class class$org$tukaani$xz$lz$LZEncoder;
    public final byte[] buf;
    public final int keepSizeAfter;
    public final int keepSizeBefore;
    public final int matchLenMax;
    public final int niceLen;
    public int readPos = -1;
    public int readLimit = -1;
    public boolean finishing = false;
    public int writePos = 0;
    public int pendingSize = 0;

    static {
        if (class$org$tukaani$xz$lz$LZEncoder == null) {
            class$org$tukaani$xz$lz$LZEncoder = class$("org.tukaani.xz.lz.LZEncoder");
        }
        $assertionsDisabled = true;
    }

    public LZEncoder(int i, int i2, int i3, int i4, int i5) {
        this.buf = new byte[getBufSize(i, i2, i3, i5)];
        this.keepSizeBefore = i2 + i;
        this.keepSizeAfter = i3 + i5;
        this.matchLenMax = i5;
        this.niceLen = i4;
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getBufSize(int i, int i2, int i3, int i4) {
        return i2 + i + i3 + i4 + Math.min((i / 2) + 262144, 536870912);
    }

    public static LZEncoder getInstance(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i6 == 4) {
            return new HC4(i, i2, i3, i4, i5, i7);
        }
        if (i6 == 20) {
            return new BT4(i, i2, i3, i4, i5, i7);
        }
        throw new IllegalArgumentException();
    }

    public static int getMemoryUsage(int i, int i2, int i3, int i4, int i5) {
        int memoryUsage;
        int bufSize = (getBufSize(i, i2, i3, i4) / 1024) + 10;
        if (i5 == 4) {
            memoryUsage = HC4.getMemoryUsage(i);
        } else {
            if (i5 != 20) {
                throw new IllegalArgumentException();
            }
            memoryUsage = BT4.getMemoryUsage(i);
        }
        return memoryUsage + bufSize;
    }

    public static void normalize(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 <= i) {
                iArr[i2] = 0;
            } else {
                iArr[i2] = i3 - i;
            }
        }
    }

    public void copyUncompressed(OutputStream outputStream, int i, int i2) throws IOException {
        outputStream.write(this.buf, (this.readPos + 1) - i, i2);
    }

    public int fillWindow(byte[] bArr, int i, int i2) {
        int i3;
        boolean z = $assertionsDisabled;
        if (!z && this.finishing) {
            throw new AssertionError();
        }
        if (this.readPos >= this.buf.length - this.keepSizeAfter) {
            moveWindow();
        }
        byte[] bArr2 = this.buf;
        int length = bArr2.length;
        int i4 = this.writePos;
        if (i2 > length - i4) {
            i2 = bArr2.length - i4;
        }
        System.arraycopy(bArr, i, bArr2, i4, i2);
        int i5 = this.writePos + i2;
        this.writePos = i5;
        int i6 = this.keepSizeAfter;
        if (i5 >= i6) {
            this.readLimit = i5 - i6;
        }
        int i7 = this.pendingSize;
        if (i7 > 0 && (i3 = this.readPos) < this.readLimit) {
            this.readPos = i3 - i7;
            this.pendingSize = 0;
            skip(i7);
            if (!z && this.pendingSize >= i7) {
                throw new AssertionError();
            }
        }
        return i2;
    }

    public int getAvail() {
        if ($assertionsDisabled || isStarted()) {
            return this.writePos - this.readPos;
        }
        throw new AssertionError();
    }

    public int getByte(int i) {
        return this.buf[this.readPos - i] & 255;
    }

    public int getMatchLen(int i, int i2) {
        int i3 = (this.readPos - i) - 1;
        int i4 = 0;
        while (i4 < i2) {
            byte[] bArr = this.buf;
            if (bArr[this.readPos + i4] != bArr[i3 + i4]) {
                break;
            }
            i4++;
        }
        return i4;
    }

    public abstract Matches getMatches();

    public int getPos() {
        return this.readPos;
    }

    public boolean hasEnoughData(int i) {
        return this.readPos - i < this.readLimit;
    }

    public boolean isStarted() {
        return this.readPos != -1;
    }

    public int movePos(int i, int i2) {
        if (!$assertionsDisabled && i < i2) {
            throw new AssertionError();
        }
        int i3 = this.readPos + 1;
        this.readPos = i3;
        int i4 = this.writePos - i3;
        if (i4 >= i || (i4 >= i2 && this.finishing)) {
            return i4;
        }
        this.pendingSize++;
        return 0;
    }

    public final void moveWindow() {
        int i = ((this.readPos + 1) - this.keepSizeBefore) & (-16);
        int i2 = this.writePos - i;
        byte[] bArr = this.buf;
        System.arraycopy(bArr, i, bArr, 0, i2);
        this.readPos -= i;
        this.readLimit -= i;
        this.writePos -= i;
    }

    public void setFinishing() {
        this.readLimit = this.writePos - 1;
        this.finishing = true;
    }

    public void setFlushing() {
        this.readLimit = this.writePos - 1;
    }

    public void setPresetDict(int i, byte[] bArr) {
        boolean z = $assertionsDisabled;
        if (!z && isStarted()) {
            throw new AssertionError();
        }
        if (!z && this.writePos != 0) {
            throw new AssertionError();
        }
        if (bArr != null) {
            int iMin = Math.min(bArr.length, i);
            System.arraycopy(bArr, bArr.length - iMin, this.buf, 0, iMin);
            this.writePos += iMin;
            skip(iMin);
        }
    }

    public abstract void skip(int i);

    public boolean verifyMatches(Matches matches) {
        int iMin = Math.min(getAvail(), this.matchLenMax);
        for (int i = 0; i < matches.count; i++) {
            if (getMatchLen(matches.dist[i], iMin) != matches.len[i]) {
                return false;
            }
        }
        return true;
    }

    public int getByte(int i, int i2) {
        return this.buf[(this.readPos + i) - i2] & 255;
    }

    public int getMatchLen(int i, int i2, int i3) {
        int i4 = this.readPos + i;
        int i5 = (i4 - i2) - 1;
        int i6 = 0;
        while (i6 < i3) {
            byte[] bArr = this.buf;
            if (bArr[i4 + i6] != bArr[i5 + i6]) {
                break;
            }
            i6++;
        }
        return i6;
    }
}
