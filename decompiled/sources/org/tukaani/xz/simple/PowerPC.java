package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes4.dex */
public final class PowerPC implements SimpleFilter {
    public final boolean isEncoder;
    public int pos;

    public PowerPC(boolean z, int i) {
        this.isEncoder = z;
        this.pos = i;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] bArr, int i, int i2) {
        int i3 = (i2 + i) - 4;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = bArr[i4];
            if ((i5 & 252) == 72) {
                int i6 = i4 + 3;
                int i7 = bArr[i6];
                if ((i7 & 3) == 1) {
                    int i8 = i4 + 1;
                    int i9 = i4 + 2;
                    int i10 = ((i5 & 3) << 24) | ((bArr[i8] & 255) << 16) | ((bArr[i9] & 255) << 8) | (i7 & 252);
                    int i11 = this.isEncoder ? ((this.pos + i4) - i) + i10 : i10 - ((this.pos + i4) - i);
                    bArr[i4] = (byte) (((i11 >>> 24) & 3) | 72);
                    bArr[i8] = (byte) (i11 >>> 16);
                    bArr[i9] = (byte) (i11 >>> 8);
                    bArr[i6] = (byte) ((bArr[i6] & 3) | i11);
                }
            }
            i4 += 4;
        }
        int i12 = i4 - i;
        this.pos += i12;
        return i12;
    }
}
