package org.apache.commons.compress.archivers.zip;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ZipUtil {
    public static final byte[] DOS_TIME_MIN = ZipLong.getBytes(8448);

    public static long adjustToLong(int i) {
        return i < 0 ? ((long) i) + 4294967296L : i;
    }

    public static long bigToLong(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 63) {
            return bigInteger.longValue();
        }
        throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + bigInteger + "]");
    }

    public static boolean canHandleEntryData(ZipArchiveEntry zipArchiveEntry) {
        return supportsEncryptionOf(zipArchiveEntry) && supportsMethodOf(zipArchiveEntry);
    }

    public static void checkRequestedFeatures(ZipArchiveEntry zipArchiveEntry) throws UnsupportedZipFeatureException {
        if (!supportsEncryptionOf(zipArchiveEntry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, zipArchiveEntry);
        }
        if (supportsMethodOf(zipArchiveEntry)) {
            return;
        }
        ZipMethod methodByCode = ZipMethod.getMethodByCode(zipArchiveEntry.getMethod());
        if (methodByCode != null) {
            throw new UnsupportedZipFeatureException(methodByCode, zipArchiveEntry);
        }
        throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, zipArchiveEntry);
    }

    public static byte[] copy(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public static long dosToJavaTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, ((int) ((j >> 25) & 127)) + 1980);
        calendar.set(2, ((int) ((j >> 21) & 15)) - 1);
        calendar.set(5, ((int) (j >> 16)) & 31);
        calendar.set(11, ((int) (j >> 11)) & 31);
        calendar.set(12, ((int) (j >> 5)) & 63);
        calendar.set(13, ((int) (j << 1)) & 62);
        return calendar.getTime().getTime();
    }

    public static Date fromDosTime(ZipLong zipLong) {
        return new Date(dosToJavaTime(zipLong.value));
    }

    public static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField abstractUnicodeExtraField, byte[] bArr) {
        if (abstractUnicodeExtraField == null) {
            return null;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        if (crc32.getValue() != abstractUnicodeExtraField.getNameCRC32()) {
            return null;
        }
        try {
            return ZipEncodingHelper.UTF8_ZIP_ENCODING.decode(abstractUnicodeExtraField.getUnicodeName());
        } catch (IOException unused) {
            return null;
        }
    }

    public static BigInteger longToBig(long j) {
        if (j < -2147483648L) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Negative longs < -2^31 not permitted: [", j, "]"));
        }
        if (j < 0 && j >= -2147483648L) {
            j = adjustToLong((int) j);
        }
        return BigInteger.valueOf(j);
    }

    public static byte[] reverse(byte[] bArr) {
        int length = bArr.length - 1;
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            int i2 = length - i;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    public static void setNameAndCommentFromExtraFields(ZipArchiveEntry zipArchiveEntry, byte[] bArr, byte[] bArr2) {
        String unicodeStringIfOriginalMatches;
        UnicodePathExtraField unicodePathExtraField = (UnicodePathExtraField) zipArchiveEntry.getExtraField(UnicodePathExtraField.UPATH_ID);
        String name = zipArchiveEntry.getName();
        String unicodeStringIfOriginalMatches2 = getUnicodeStringIfOriginalMatches(unicodePathExtraField, bArr);
        if (unicodeStringIfOriginalMatches2 != null && !name.equals(unicodeStringIfOriginalMatches2)) {
            zipArchiveEntry.setName(unicodeStringIfOriginalMatches2);
        }
        if (bArr2 == null || bArr2.length <= 0 || (unicodeStringIfOriginalMatches = getUnicodeStringIfOriginalMatches((UnicodeCommentExtraField) zipArchiveEntry.getExtraField(UnicodeCommentExtraField.UCOM_ID), bArr2)) == null) {
            return;
        }
        zipArchiveEntry.setComment(unicodeStringIfOriginalMatches);
    }

    public static int signedByteToUnsignedInt(byte b) {
        return b >= 0 ? b : b + 256;
    }

    public static boolean supportsEncryptionOf(ZipArchiveEntry zipArchiveEntry) {
        return !zipArchiveEntry.getGeneralPurposeBit().encryptionFlag;
    }

    public static boolean supportsMethodOf(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getMethod() == 0 || zipArchiveEntry.getMethod() == 8;
    }

    public static ZipLong toDosTime(Date date) {
        return new ZipLong(toDosTime(date.getTime()), 0);
    }

    public static byte unsignedIntToSignedByte(int i) {
        if (i > 255 || i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Can only convert non-negative integers between [0,255] to byte: [", i, "]"));
        }
        return i < 128 ? (byte) i : (byte) (i - 256);
    }

    public static byte[] toDosTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(1) < 1980) {
            return copy(DOS_TIME_MIN);
        }
        return ZipLong.getBytes((calendar.get(13) >> 1) | ((r5 - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5));
    }
}
