package org.apache.commons.compress.archivers.cpio;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CpioUtil {
    public static long byteArray2long(byte[] bArr, boolean z) {
        if (bArr.length % 2 != 0) {
            throw new UnsupportedOperationException();
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        if (!z) {
            for (int i = 0; i < length; i += 2) {
                byte b = bArr2[i];
                int i2 = i + 1;
                bArr2[i] = bArr2[i2];
                bArr2[i2] = b;
            }
        }
        long j = bArr2[0] & 255;
        for (int i3 = 1; i3 < length; i3++) {
            j = (j << 8) | ((long) (bArr2[i3] & 255));
        }
        return j;
    }

    public static byte[] long2byteArray(long j, int i, boolean z) {
        byte[] bArr = new byte[i];
        if (i % 2 != 0 || i < 2) {
            throw new UnsupportedOperationException();
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            bArr[i2] = (byte) (255 & j);
            j >>= 8;
        }
        if (!z) {
            for (int i3 = 0; i3 < i; i3 += 2) {
                byte b = bArr[i3];
                int i4 = i3 + 1;
                bArr[i3] = bArr[i4];
                bArr[i4] = b;
            }
        }
        return bArr;
    }
}
