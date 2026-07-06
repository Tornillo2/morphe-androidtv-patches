package org.apache.commons.compress.archivers.tar;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarUtils {
    public static final int BYTE_MASK = 255;
    public static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(null);
    public static final ZipEncoding FALLBACK_ENCODING = new AnonymousClass1();

    /* JADX INFO: renamed from: org.apache.commons.compress.archivers.tar.TarUtils$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 implements ZipEncoding {
        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public boolean canEncode(String str) {
            return true;
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public String decode(byte[] bArr) {
            StringBuffer stringBuffer = new StringBuffer(bArr.length);
            for (byte b : bArr) {
                if (b == 0) {
                    break;
                }
                stringBuffer.append((char) (b & 255));
            }
            return stringBuffer.toString();
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public ByteBuffer encode(String str) {
            int length = str.length();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) str.charAt(i);
            }
            return ByteBuffer.wrap(bArr);
        }
    }

    public static long computeCheckSum(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += (long) (b & 255);
        }
        return j;
    }

    public static String exceptionMessage(byte[] bArr, int i, int i2, int i3, byte b) {
        String strReplaceAll = new String(bArr, i, i2).replaceAll("\u0000", "{NUL}");
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Invalid byte ", b, " at offset ");
        sbM.append(i3 - i);
        sbM.append(" in '");
        sbM.append(strReplaceAll);
        sbM.append("' len=");
        sbM.append(i2);
        return sbM.toString();
    }

    public static void formatBigIntegerBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        byte[] byteArray = BigInteger.valueOf(j).toByteArray();
        int length = byteArray.length;
        int i3 = (i2 + i) - length;
        System.arraycopy(byteArray, 0, bArr, i3, length);
        byte b = (byte) (z ? 255 : 0);
        while (true) {
            i++;
            if (i >= i3) {
                return;
            } else {
                bArr[i] = b;
            }
        }
    }

    public static int formatCheckSumOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 0;
        bArr[(i2 - 1) + i] = 32;
        return i + i2;
    }

    public static void formatLongBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        int i3 = (i2 - 1) * 8;
        long j2 = 1 << i3;
        long jAbs = Math.abs(j);
        if (jAbs >= j2) {
            throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
        }
        if (z) {
            jAbs = ((jAbs ^ (j2 - 1)) | ((long) (255 << i3))) + 1;
        }
        for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
            bArr[i4] = (byte) jAbs;
            jAbs >>= 8;
        }
    }

    public static int formatLongOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        return i + i2;
    }

    public static int formatLongOctalOrBinaryBytes(long j, byte[] bArr, int i, int i2) {
        long j2 = i2 == 8 ? 2097151L : TarConstants.MAXSIZE;
        boolean z = j < 0;
        if (!z && j <= j2) {
            return formatLongOctalBytes(j, bArr, i, i2);
        }
        if (i2 < 9) {
            formatLongBinary(j, bArr, i, i2, z);
        }
        formatBigIntegerBinary(j, bArr, i, i2, z);
        bArr[i] = (byte) (z ? 255 : 128);
        return i + i2;
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2) {
        try {
            try {
                return formatNameBytes(str, bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException unused) {
                return formatNameBytes(str, bArr, i, i2, FALLBACK_ENCODING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int formatOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        bArr[(i2 - 1) + i] = 0;
        return i + i2;
    }

    public static void formatUnsignedOctalString(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2 - 1;
        if (j == 0) {
            i3 = i2 - 2;
            bArr[i4 + i] = 48;
        } else {
            long j2 = j;
            while (i4 >= 0 && j2 != 0) {
                bArr[i + i4] = (byte) (((byte) (7 & j2)) + 48);
                j2 >>>= 3;
                i4--;
            }
            if (j2 != 0) {
                throw new IllegalArgumentException(j + "=" + Long.toOctalString(j) + " will not fit in octal number buffer of length " + i2);
            }
            i3 = i4;
        }
        while (i3 >= 0) {
            bArr[i + i3] = 48;
            i3--;
        }
    }

    public static long parseBinaryBigInteger(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 1, bArr2, 0, i3);
        BigInteger bigInteger = new BigInteger(bArr2);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1L)).not();
        }
        if (bigInteger.bitLength() > 63) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("At offset ", i, ", ", i2, " byte binary number exceeds maximum signed long value"));
        }
        long jLongValue = bigInteger.longValue();
        return z ? -jLongValue : jLongValue;
    }

    public static long parseBinaryLong(byte[] bArr, int i, int i2, boolean z) {
        if (i2 >= 9) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("At offset ", i, ", ", i2, " byte binary number exceeds maximum signed long value"));
        }
        long jPow = 0;
        for (int i3 = 1; i3 < i2; i3++) {
            jPow = (jPow << 8) + ((long) (bArr[i + i3] & 255));
        }
        if (z) {
            jPow = (jPow - 1) ^ (((long) Math.pow(2.0d, (i2 - 1) * 8)) - 1);
        }
        return z ? -jPow : jPow;
    }

    public static boolean parseBoolean(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    public static String parseName(byte[] bArr, int i, int i2) {
        try {
            try {
                return parseName(bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException unused) {
                return parseName(bArr, i, i2, FALLBACK_ENCODING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long parseOctal(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i2 < 2) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Length ", i2, " must be at least 2"));
        }
        long j = 0;
        if (bArr[i] == 0) {
            return 0L;
        }
        int i4 = i;
        while (i4 < i3 && bArr[i4] == 32) {
            i4++;
        }
        int i5 = i3 - 1;
        byte b = bArr[i5];
        if (b != 0 && b != 32) {
            throw new IllegalArgumentException(exceptionMessage(bArr, i, i2, i5, b));
        }
        int i6 = i3 - 1;
        byte b2 = bArr[i3 - 2];
        while (i4 < i6 - 1 && (b2 == 0 || b2 == 32)) {
            int i7 = i6 - 1;
            byte b3 = bArr[i6 - 2];
            i6 = i7;
            b2 = b3;
        }
        while (i4 < i6) {
            byte b4 = bArr[i4];
            if (b4 < 48 || b4 > 55) {
                throw new IllegalArgumentException(exceptionMessage(bArr, i, i2, i4, b4));
            }
            j = (j << 3) + ((long) (b4 - 48));
            i4++;
        }
        return j;
    }

    public static long parseOctalOrBinary(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        if ((b & 128) == 0) {
            return parseOctal(bArr, i, i2);
        }
        boolean z = b == -1;
        return i2 < 9 ? parseBinaryLong(bArr, i, i2, z) : parseBinaryBigInteger(bArr, i, i2, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean verifyCheckSum(byte[] r13) {
        /*
            r0 = 0
            r2 = 0
            r3 = r0
            r5 = r3
            r7 = 0
            r8 = 0
        L7:
            int r9 = r13.length
            if (r7 >= r9) goto L3b
            r9 = r13[r7]
            r10 = 148(0x94, float:2.07E-43)
            if (r10 > r7) goto L32
            r10 = 156(0x9c, float:2.19E-43)
            if (r7 >= r10) goto L32
            r10 = 48
            r11 = 6
            if (r10 > r9) goto L2d
            r10 = 55
            if (r9 > r10) goto L2d
            int r10 = r8 + 1
            if (r8 >= r11) goto L2c
            r11 = 8
            long r0 = r0 * r11
            long r8 = (long) r9
            long r0 = r0 + r8
            r8 = 48
            long r0 = r0 - r8
            r8 = r10
            goto L30
        L2c:
            r8 = r10
        L2d:
            if (r8 <= 0) goto L30
            r8 = 6
        L30:
            r9 = 32
        L32:
            r10 = r9 & 255(0xff, float:3.57E-43)
            long r10 = (long) r10
            long r3 = r3 + r10
            long r9 = (long) r9
            long r5 = r5 + r9
            int r7 = r7 + 1
            goto L7
        L3b:
            int r13 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r13 == 0) goto L47
            int r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r3 == 0) goto L47
            if (r13 <= 0) goto L46
            goto L47
        L46:
            return r2
        L47:
            r13 = 1
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.verifyCheckSum(byte[]):boolean");
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int length = str.length();
        ByteBuffer byteBufferEncode = zipEncoding.encode(str);
        while (byteBufferEncode.limit() > i2 && length > 0) {
            length--;
            byteBufferEncode = zipEncoding.encode(str.substring(0, length));
        }
        int iLimit = byteBufferEncode.limit() - byteBufferEncode.position();
        System.arraycopy(byteBufferEncode.array(), byteBufferEncode.arrayOffset(), bArr, i, iLimit);
        while (iLimit < i2) {
            bArr[i + iLimit] = 0;
            iLimit++;
        }
        return i + i2;
    }

    public static String parseName(byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        while (i2 > 0 && bArr[(i + i2) - 1] == 0) {
            i2--;
        }
        if (i2 > 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return zipEncoding.decode(bArr2);
        }
        return "";
    }
}
