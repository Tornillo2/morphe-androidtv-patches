package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes4.dex */
public final class LZDecoder {
    public final byte[] buf;
    public int full;
    public int pos;
    public int start;
    public int limit = 0;
    public int pendingLen = 0;
    public int pendingDist = 0;

    public LZDecoder(int i, byte[] bArr) {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        byte[] bArr2 = new byte[i];
        this.buf = bArr2;
        if (bArr != null) {
            int iMin = Math.min(bArr.length, i);
            this.pos = iMin;
            this.full = iMin;
            this.start = iMin;
            System.arraycopy(bArr, bArr.length - iMin, bArr2, 0, iMin);
        }
    }

    public void copyUncompressed(DataInputStream dataInputStream, int i) throws IOException {
        int iMin = Math.min(this.buf.length - this.pos, i);
        dataInputStream.readFully(this.buf, this.pos, iMin);
        int i2 = this.pos + iMin;
        this.pos = i2;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public int flush(byte[] bArr, int i) {
        int i2 = this.pos;
        int i3 = this.start;
        int i4 = i2 - i3;
        byte[] bArr2 = this.buf;
        if (i2 == bArr2.length) {
            this.pos = 0;
        }
        System.arraycopy(bArr2, i3, bArr, i, i4);
        this.start = this.pos;
        return i4;
    }

    public int getByte(int i) {
        int i2 = this.pos;
        int length = (i2 - i) - 1;
        if (i >= i2) {
            length += this.buf.length;
        }
        return this.buf[length] & 255;
    }

    public int getPos() {
        return this.pos;
    }

    public boolean hasPending() {
        return this.pendingLen > 0;
    }

    public boolean hasSpace() {
        return this.pos < this.limit;
    }

    public void putByte(byte b) {
        byte[] bArr = this.buf;
        int i = this.pos;
        int i2 = i + 1;
        this.pos = i2;
        bArr[i] = b;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public void repeat(int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i >= this.full) {
            throw new CorruptedInputException();
        }
        int iMin = Math.min(this.limit - this.pos, i2);
        this.pendingLen = i2 - iMin;
        this.pendingDist = i;
        int i4 = this.pos;
        int length = (i4 - i) - 1;
        if (i >= i4) {
            length += this.buf.length;
        }
        do {
            byte[] bArr = this.buf;
            int i5 = this.pos;
            i3 = i5 + 1;
            this.pos = i3;
            int i6 = length + 1;
            bArr[i5] = bArr[length];
            length = i6 == bArr.length ? 0 : i6;
            iMin--;
        } while (iMin > 0);
        if (this.full < i3) {
            this.full = i3;
        }
    }

    public void repeatPending() throws IOException {
        int i = this.pendingLen;
        if (i > 0) {
            repeat(this.pendingDist, i);
        }
    }

    public void reset() {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.limit = 0;
        this.buf[r1.length - 1] = 0;
    }

    public void setLimit(int i) {
        byte[] bArr = this.buf;
        int length = bArr.length;
        int i2 = this.pos;
        if (length - i2 <= i) {
            this.limit = bArr.length;
        } else {
            this.limit = i2 + i;
        }
    }
}
