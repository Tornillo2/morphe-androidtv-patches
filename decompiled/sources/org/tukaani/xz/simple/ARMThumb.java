package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes4.dex */
public final class ARMThumb implements SimpleFilter {
    public final boolean isEncoder;
    public int pos;

    public ARMThumb(boolean z, int i) {
        this.isEncoder = z;
        this.pos = i + 4;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] bArr, int i, int i2) {
        int i3 = (i2 + i) - 4;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = i4 + 1;
            int i6 = bArr[i5];
            if ((i6 & 248) == 240) {
                int i7 = i4 + 3;
                int i8 = bArr[i7];
                if ((i8 & 248) == 248) {
                    int i9 = ((i6 & 7) << 19) | ((bArr[i4] & 255) << 11) | ((i8 & 7) << 8);
                    int i10 = i4 + 2;
                    int i11 = (i9 | (bArr[i10] & 255)) << 1;
                    int i12 = this.isEncoder ? ((this.pos + i4) - i) + i11 : i11 - ((this.pos + i4) - i);
                    bArr[i5] = (byte) (240 | ((i12 >>> 20) & 7));
                    bArr[i4] = (byte) (i12 >>> 12);
                    bArr[i7] = (byte) (((i12 >>> 9) & 7) | 248);
                    bArr[i10] = (byte) (i12 >>> 1);
                    i4 = i10;
                }
            }
            i4 += 2;
        }
        int i13 = i4 - i;
        this.pos += i13;
        return i13;
    }
}
