package org.apache.commons.compress.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArchiveUtils {
    public static boolean isEqual(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, boolean z) {
        int i5 = i2 < i4 ? i2 : i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if (bArr[i + i6] != bArr2[i3 + i6]) {
                return false;
            }
        }
        if (i2 == i4) {
            return true;
        }
        if (!z) {
            return false;
        }
        if (i2 > i4) {
            while (i4 < i2) {
                if (bArr[i + i4] != 0) {
                    return false;
                }
                i4++;
            }
        } else {
            while (i2 < i4) {
                if (bArr2[i3 + i2] != 0) {
                    return false;
                }
                i2++;
            }
        }
        return true;
    }

    public static boolean isEqualWithNull(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return isEqual(bArr, i, i2, bArr2, i3, i4, true);
    }

    public static boolean matchAsciiBuffer(String str, byte[] bArr, int i, int i2) {
        try {
            byte[] bytes = str.getBytes("ASCII");
            return isEqual(bytes, 0, bytes.length, bArr, i, i2, false);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] toAsciiBytes(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toAsciiString(byte[] bArr) {
        try {
            return new String(bArr, "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(ArchiveEntry archiveEntry) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(archiveEntry.isDirectory() ? 'd' : '-');
        String string = Long.toString(archiveEntry.getSize());
        stringBuffer.append(' ');
        for (int i = 7; i > string.length(); i--) {
            stringBuffer.append(' ');
        }
        stringBuffer.append(string);
        stringBuffer.append(' ');
        stringBuffer.append(archiveEntry.getName());
        return stringBuffer.toString();
    }

    public static String toAsciiString(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEqual(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return isEqual(bArr, i, i2, bArr2, i3, i4, false);
    }

    public static boolean matchAsciiBuffer(String str, byte[] bArr) {
        return matchAsciiBuffer(str, bArr, 0, bArr.length);
    }

    public static boolean isEqual(byte[] bArr, byte[] bArr2) {
        return isEqual(bArr, 0, bArr.length, bArr2, 0, bArr2.length, false);
    }

    public static boolean isEqual(byte[] bArr, byte[] bArr2, boolean z) {
        return isEqual(bArr, 0, bArr.length, bArr2, 0, bArr2.length, z);
    }
}
