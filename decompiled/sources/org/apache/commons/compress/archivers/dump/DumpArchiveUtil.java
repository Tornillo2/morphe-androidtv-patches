package org.apache.commons.compress.archivers.dump;

import com.google.common.base.Ascii;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DumpArchiveUtil {
    public static int calculateChecksum(byte[] bArr) {
        int iConvert32 = 0;
        for (int i = 0; i < 256; i++) {
            iConvert32 += convert32(bArr, i * 4);
        }
        return DumpArchiveConstants.CHECKSUM - (iConvert32 - convert32(bArr, 28));
    }

    public static final int convert16(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public static final int convert32(byte[] bArr, int i) {
        return (bArr[i + 3] << Ascii.CAN) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public static final long convert64(byte[] bArr, int i) {
        return (((long) bArr[i + 7]) << 56) + ((((long) bArr[i + 6]) << 48) & ZipEightByteInteger.BYTE_6_MASK) + ((((long) bArr[i + 5]) << 40) & ZipEightByteInteger.BYTE_5_MASK) + ((((long) bArr[i + 4]) << 32) & ZipEightByteInteger.BYTE_4_MASK) + ((((long) bArr[i + 3]) << 24) & 4278190080L) + ((((long) bArr[i + 2]) << 16) & 16711680) + ((((long) bArr[i + 1]) << 8) & 65280) + (((long) bArr[i]) & 255);
    }

    public static final int getIno(byte[] bArr) {
        return convert32(bArr, 20);
    }

    public static final boolean verify(byte[] bArr) {
        return convert32(bArr, 24) == 60012 && convert32(bArr, 28) == calculateChecksum(bArr);
    }
}
