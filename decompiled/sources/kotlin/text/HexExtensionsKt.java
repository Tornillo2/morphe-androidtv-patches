package kotlin.text;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import com.google.common.base.Ascii;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.HexFormat;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nHexExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1249:1\n1198#1,7:1251\n1198#1,7:1258\n1198#1,7:1265\n1198#1,7:1272\n1198#1,7:1279\n1198#1,7:1286\n1198#1,7:1293\n1198#1,7:1300\n1209#1,5:1307\n1209#1,5:1312\n1198#1,7:1317\n1198#1,7:1324\n1209#1,5:1331\n1218#1,5:1336\n1#2:1250\n1188#3,3:1341\n1188#3,3:1344\n1188#3,3:1347\n1188#3,3:1350\n*S KotlinDebug\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n*L\n457#1:1251,7\n490#1:1258,7\n494#1:1265,7\n497#1:1272,7\n538#1:1279,7\n541#1:1286,7\n546#1:1293,7\n551#1:1300,7\n558#1:1307,5\n559#1:1312,5\n1153#1:1317,7\n1155#1:1324,7\n1183#1:1331,5\n1191#1:1336,5\n43#1:1341,3\n44#1:1344,3\n55#1:1347,3\n56#1:1350,3\n*E\n"})
public final class HexExtensionsKt {

    @NotNull
    public static final int[] BYTE_TO_LOWER_CASE_HEX_DIGITS;

    @NotNull
    public static final int[] BYTE_TO_UPPER_CASE_HEX_DIGITS;

    @NotNull
    public static final int[] HEX_DIGITS_TO_DECIMAL;

    @NotNull
    public static final long[] HEX_DIGITS_TO_LONG_DECIMAL;

    @NotNull
    public static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";

    @NotNull
    public static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    static {
        int[] iArr = new int[256];
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = LOWER_CASE_HEX_DIGITS.charAt(i2 & 15) | (LOWER_CASE_HEX_DIGITS.charAt(i2 >> 4) << '\b');
        }
        BYTE_TO_LOWER_CASE_HEX_DIGITS = iArr;
        int[] iArr2 = new int[256];
        for (int i3 = 0; i3 < 256; i3++) {
            iArr2[i3] = "0123456789ABCDEF".charAt(i3 & 15) | ("0123456789ABCDEF".charAt(i3 >> 4) << '\b');
        }
        BYTE_TO_UPPER_CASE_HEX_DIGITS = iArr2;
        int[] iArr3 = new int[256];
        for (int i4 = 0; i4 < 256; i4++) {
            iArr3[i4] = -1;
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < LOWER_CASE_HEX_DIGITS.length()) {
            iArr3[LOWER_CASE_HEX_DIGITS.charAt(i5)] = i6;
            i5++;
            i6++;
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < "0123456789ABCDEF".length()) {
            iArr3["0123456789ABCDEF".charAt(i7)] = i8;
            i7++;
            i8++;
        }
        HEX_DIGITS_TO_DECIMAL = iArr3;
        long[] jArr = new long[256];
        for (int i9 = 0; i9 < 256; i9++) {
            jArr[i9] = -1;
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < LOWER_CASE_HEX_DIGITS.length()) {
            jArr[LOWER_CASE_HEX_DIGITS.charAt(i10)] = i11;
            i10++;
            i11++;
        }
        int i12 = 0;
        while (i < "0123456789ABCDEF".length()) {
            jArr["0123456789ABCDEF".charAt(i)] = i12;
            i++;
            i12++;
        }
        HEX_DIGITS_TO_LONG_DECIMAL = jArr;
    }

    public static final long charsPerSet(long j, int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        long j2 = i;
        return ((j2 - 1) * ((long) i2)) + (j * j2);
    }

    public static final int checkContainsAt(String str, int i, int i2, String str2, boolean z, String str3) {
        if (str2.length() == 0) {
            return i;
        }
        int length = str2.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (!CharsKt__CharKt.equals(str2.charAt(i3), str.charAt(i + i3), z)) {
                throwNotContainedAt(str, i, i2, str2, str3);
                throw null;
            }
        }
        return str2.length() + i;
    }

    public static final int checkFormatLength(long j) {
        if (0 <= j && j <= 2147483647L) {
            return (int) j;
        }
        throw new IllegalArgumentException("The resulting string length is too big: " + ((Object) UnsignedKt.ulongToString(j, 10)));
    }

    public static final int checkNewLineAt(String str, int i, int i2) {
        if (str.charAt(i) == '\r') {
            int i3 = i + 1;
            return (i3 >= i2 || str.charAt(i3) != '\n') ? i3 : i + 2;
        }
        if (str.charAt(i) == '\n') {
            return i + 1;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Expected a new line at index ", i, ", but was ");
        sbM.append(str.charAt(i));
        throw new NumberFormatException(sbM.toString());
    }

    public static final void checkNumberOfDigits(String str, int i, int i2, int i3) {
        int i4 = i2 - i;
        if (i4 < 1) {
            throwInvalidNumberOfDigits(str, i, i2, "at least", 1);
            throw null;
        }
        if (i4 > i3) {
            checkZeroDigits(str, i, (i4 + i) - i3);
        }
    }

    public static final void checkPrefixSuffixNumberOfDigits(String str, int i, int i2, String str2, String str3, boolean z, int i3) {
        if ((i2 - i) - str2.length() <= str3.length()) {
            throwInvalidPrefixSuffix(str, i, i2, str2, str3);
            throw null;
        }
        if (str2.length() != 0) {
            int length = str2.length();
            for (int i4 = 0; i4 < length; i4++) {
                if (!CharsKt__CharKt.equals(str2.charAt(i4), str.charAt(i + i4), z)) {
                    throwNotContainedAt(str, i, i2, str2, "prefix");
                    throw null;
                }
            }
            i += str2.length();
        }
        int length2 = i2 - str3.length();
        if (str3.length() != 0) {
            int length3 = str3.length();
            for (int i5 = 0; i5 < length3; i5++) {
                if (!CharsKt__CharKt.equals(str3.charAt(i5), str.charAt(length2 + i5), z)) {
                    throwNotContainedAt(str, length2, i2, str3, "suffix");
                    throw null;
                }
            }
        }
        checkNumberOfDigits(str, i, length2, i3);
    }

    public static final void checkZeroDigits(String str, int i, int i2) {
        while (i < i2) {
            if (str.charAt(i) != '0') {
                StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Expected the hexadecimal digit '0' at index ", i, ", but was '");
                sbM.append(str.charAt(i));
                sbM.append("'.\nThe result won't fit the type being parsed.");
                throw new NumberFormatException(sbM.toString());
            }
            i++;
        }
    }

    public static final int decimalFromHexDigitAt(String str, int i) {
        int i2;
        char cCharAt = str.charAt(i);
        if ((cCharAt >>> '\b') == 0 && (i2 = HEX_DIGITS_TO_DECIMAL[cCharAt]) >= 0) {
            return i2;
        }
        throwInvalidDigitAt(str, i);
        throw null;
    }

    public static final int formatByteAt(byte[] bArr, int i, String str, String str2, int[] iArr, char[] cArr, int i2) {
        return toCharArrayIfNotEmpty(str2, cArr, formatByteAt(bArr, i, iArr, cArr, toCharArrayIfNotEmpty(str, cArr, i2)));
    }

    public static final int formattedStringLength(int i, int i2, int i3, int i4) {
        if (i <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        long j = i2;
        return checkFormatLength((((long) i) * (((((long) i3) + 2) + ((long) i4)) + j)) - j);
    }

    @NotNull
    public static final int[] getBYTE_TO_LOWER_CASE_HEX_DIGITS() {
        return BYTE_TO_LOWER_CASE_HEX_DIGITS;
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    public static final byte hexToByte(@NotNull String str, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return (byte) hexToIntImpl(str, 0, str.length(), format, 2);
    }

    public static byte hexToByte$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToByte(str, hexFormat);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final byte[] hexToByteArray(@NotNull String str, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToByteArray(str, 0, str.length(), format);
    }

    public static byte[] hexToByteArray$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToByteArray(str, hexFormat);
    }

    @ExperimentalStdlibApi
    public static final byte[] hexToByteArrayNoLineAndGroupSeparator(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        return bytesHexFormat.shortByteSeparatorNoPrefixAndSuffix ? hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(str, i, i2, bytesHexFormat) : hexToByteArrayNoLineAndGroupSeparatorSlowPath(str, i, i2, bytesHexFormat);
    }

    @ExperimentalStdlibApi
    public static final byte[] hexToByteArrayNoLineAndGroupSeparatorSlowPath(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        String str2 = bytesHexFormat.bytePrefix;
        String str3 = bytesHexFormat.byteSuffix;
        String str4 = bytesHexFormat.byteSeparator;
        long length = str4.length();
        long length2 = ((long) str2.length()) + 2 + ((long) str3.length()) + length;
        long j = i2 - i;
        int i3 = (int) ((j + length) / length2);
        if ((((long) i3) * length2) - length != j) {
            return null;
        }
        boolean z = bytesHexFormat.ignoreCase;
        byte[] bArr = new byte[i3];
        if (str2.length() != 0) {
            int length3 = str2.length();
            for (int i4 = 0; i4 < length3; i4++) {
                if (!CharsKt__CharKt.equals(str2.charAt(i4), str.charAt(i + i4), z)) {
                    throwNotContainedAt(str, i, i2, str2, "byte prefix");
                    throw null;
                }
            }
            i += str2.length();
        }
        String strM = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str3, str4, str2);
        int i5 = i3 - 1;
        for (int i6 = 0; i6 < i5; i6++) {
            bArr[i6] = parseByteAt(str, i);
            i += 2;
            if (strM.length() != 0) {
                int length4 = strM.length();
                for (int i7 = 0; i7 < length4; i7++) {
                    if (!CharsKt__CharKt.equals(strM.charAt(i7), str.charAt(i + i7), z)) {
                        throwNotContainedAt(str, i, i2, strM, "byte suffix + byte separator + byte prefix");
                        throw null;
                    }
                }
                i = strM.length() + i;
            }
        }
        bArr[i5] = parseByteAt(str, i);
        int i8 = i + 2;
        if (str3.length() == 0) {
            return bArr;
        }
        int length5 = str3.length();
        for (int i9 = 0; i9 < length5; i9++) {
            if (!CharsKt__CharKt.equals(str3.charAt(i9), str.charAt(i8 + i9), z)) {
                throwNotContainedAt(str, i8, i2, str3, "byte suffix");
                throw null;
            }
        }
        return bArr;
    }

    @ExperimentalStdlibApi
    public static final byte[] hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(String str, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat) {
        int length = bytesHexFormat.byteSeparator.length();
        if (length > 1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i3 = i2 - i;
        int i4 = 2;
        if (length == 0) {
            if ((i3 & 1) != 0) {
                return null;
            }
            int i5 = i3 >> 1;
            byte[] bArr = new byte[i5];
            int i6 = 0;
            for (int i7 = 0; i7 < i5; i7++) {
                bArr[i7] = parseByteAt(str, i6);
                i6 += 2;
            }
            return bArr;
        }
        if (i3 % 3 != 2) {
            return null;
        }
        int i8 = (i3 / 3) + 1;
        byte[] bArr2 = new byte[i8];
        char cCharAt = bytesHexFormat.byteSeparator.charAt(0);
        bArr2[0] = parseByteAt(str, 0);
        for (int i9 = 1; i9 < i8; i9++) {
            if (str.charAt(i4) != cCharAt) {
                String str2 = bytesHexFormat.byteSeparator;
                boolean z = bytesHexFormat.ignoreCase;
                if (str2.length() == 0) {
                    continue;
                } else {
                    int length2 = str2.length();
                    for (int i10 = 0; i10 < length2; i10++) {
                        if (!CharsKt__CharKt.equals(str2.charAt(i10), str.charAt(i4 + i10), z)) {
                            throwNotContainedAt(str, i4, i2, str2, "byte separator");
                            throw null;
                        }
                    }
                    str2.length();
                }
            }
            bArr2[i9] = parseByteAt(str, i4 + 1);
            i4 += 3;
        }
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0137 A[SYNTHETIC] */
    @kotlin.ExperimentalStdlibApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final byte[] hexToByteArraySlowPath(java.lang.String r19, int r20, int r21, kotlin.text.HexFormat.BytesHexFormat r22) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.HexExtensionsKt.hexToByteArraySlowPath(java.lang.String, int, int, kotlin.text.HexFormat$BytesHexFormat):byte[]");
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    public static final int hexToInt(@NotNull String str, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToInt(str, 0, str.length(), format);
    }

    public static int hexToInt$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToInt(str, hexFormat);
    }

    @ExperimentalStdlibApi
    public static final int hexToIntImpl(String str, int i, int i2, HexFormat hexFormat, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        HexFormat.NumberHexFormat numberHexFormat = hexFormat.number;
        if (numberHexFormat.isDigitsOnly) {
            checkNumberOfDigits(str, i, i2, i3);
            return parseInt(str, i, i2);
        }
        String str2 = numberHexFormat.prefix;
        String str3 = numberHexFormat.suffix;
        checkPrefixSuffixNumberOfDigits(str, i, i2, str2, str3, numberHexFormat.ignoreCase, i3);
        return parseInt(str, str2.length() + i, i2 - str3.length());
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    public static final long hexToLong(@NotNull String str, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToLong(str, 0, str.length(), format);
    }

    public static long hexToLong$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToLong(str, hexFormat);
    }

    @ExperimentalStdlibApi
    public static final long hexToLongImpl(String str, int i, int i2, HexFormat hexFormat, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        HexFormat.NumberHexFormat numberHexFormat = hexFormat.number;
        if (numberHexFormat.isDigitsOnly) {
            checkNumberOfDigits(str, i, i2, i3);
            return parseLong(str, i, i2);
        }
        String str2 = numberHexFormat.prefix;
        String str3 = numberHexFormat.suffix;
        checkPrefixSuffixNumberOfDigits(str, i, i2, str2, str3, numberHexFormat.ignoreCase, i3);
        return parseLong(str, str2.length() + i, i2 - str3.length());
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    public static final short hexToShort(@NotNull String str, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return (short) hexToIntImpl(str, 0, str.length(), format, 4);
    }

    public static short hexToShort$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToShort(str, hexFormat);
    }

    public static final long longDecimalFromHexDigitAt(String str, int i) {
        char cCharAt = str.charAt(i);
        if ((cCharAt >>> '\b') == 0) {
            long j = HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
            if (j >= 0) {
                return j;
            }
        }
        throwInvalidDigitAt(str, i);
        throw null;
    }

    public static final byte parseByteAt(String str, int i) {
        int[] iArr;
        int i2;
        int i3;
        char cCharAt = str.charAt(i);
        if ((cCharAt >>> '\b') != 0 || (i2 = (iArr = HEX_DIGITS_TO_DECIMAL)[cCharAt]) < 0) {
            throwInvalidDigitAt(str, i);
            throw null;
        }
        int i4 = i + 1;
        char cCharAt2 = str.charAt(i4);
        if ((cCharAt2 >>> '\b') == 0 && (i3 = iArr[cCharAt2]) >= 0) {
            return (byte) ((i2 << 4) | i3);
        }
        throwInvalidDigitAt(str, i4);
        throw null;
    }

    public static final int parseInt(String str, int i, int i2) {
        int i3;
        int i4 = 0;
        while (i < i2) {
            int i5 = i4 << 4;
            char cCharAt = str.charAt(i);
            if ((cCharAt >>> '\b') != 0 || (i3 = HEX_DIGITS_TO_DECIMAL[cCharAt]) < 0) {
                throwInvalidDigitAt(str, i);
                throw null;
            }
            i4 = i5 | i3;
            i++;
        }
        return i4;
    }

    public static final long parseLong(String str, int i, int i2) {
        long j = 0;
        while (i < i2) {
            long j2 = j << 4;
            char cCharAt = str.charAt(i);
            if ((cCharAt >>> '\b') == 0) {
                long j3 = HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
                if (j3 >= 0) {
                    j = j2 | j3;
                    i++;
                }
            }
            throwInvalidDigitAt(str, i);
            throw null;
        }
        return j;
    }

    public static final int parsedByteArrayMaxSize(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long jCharsPerSet;
        if (i <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        long j = ((long) i6) + 2 + ((long) i7);
        long jCharsPerSet2 = charsPerSet(j, i3, i5);
        if (i2 <= i3) {
            jCharsPerSet = charsPerSet(j, i2, i5);
        } else {
            jCharsPerSet = charsPerSet(jCharsPerSet2, i2 / i3, i4);
            int i8 = i2 % i3;
            if (i8 != 0) {
                jCharsPerSet = charsPerSet(j, i8, i5) + jCharsPerSet + ((long) i4);
            }
        }
        long j2 = i;
        long jWholeElementsPerSet = wholeElementsPerSet(j2, jCharsPerSet, 1);
        long j3 = j2 - ((jCharsPerSet + 1) * jWholeElementsPerSet);
        long jWholeElementsPerSet2 = wholeElementsPerSet(j3, jCharsPerSet2, i4);
        long j4 = j3 - ((jCharsPerSet2 + ((long) i4)) * jWholeElementsPerSet2);
        long jWholeElementsPerSet3 = wholeElementsPerSet(j4, j, i5);
        return (int) ((jWholeElementsPerSet2 * ((long) i3)) + (jWholeElementsPerSet * ((long) i2)) + jWholeElementsPerSet3 + ((long) (j4 - ((j + ((long) i5)) * jWholeElementsPerSet3) > 0 ? 1 : 0)));
    }

    public static final Void throwInvalidDigitAt(String str, int i) {
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Expected a hexadecimal digit at index ", i, ", but was ");
        sbM.append(str.charAt(i));
        throw new NumberFormatException(sbM.toString());
    }

    public static final void throwInvalidNumberOfDigits(String str, int i, int i2, String str2, int i3) {
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        throw new NumberFormatException("Expected " + str2 + ' ' + i3 + " hexadecimal digits at index " + i + ", but was \"" + strSubstring + "\" of length " + (i2 - i));
    }

    public static final void throwInvalidPrefixSuffix(String str, int i, int i2, String str2, String str3) {
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Expected a hexadecimal number with prefix \"", str2, "\" and suffix \"", str3, "\", but was ");
        sbM.append(strSubstring);
        throw new NumberFormatException(sbM.toString());
    }

    public static final void throwNotContainedAt(String str, int i, int i2, String str2, String str3) {
        int length = str2.length() + i;
        if (length <= i2) {
            i2 = length;
        }
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Expected ", str3, " \"", str2, "\" at index ");
        sbM.append(i);
        sbM.append(", but was ");
        sbM.append(strSubstring);
        throw new NumberFormatException(sbM.toString());
    }

    public static final int toCharArrayIfNotEmpty(String str, char[] cArr, int i) {
        int length = str.length();
        if (length != 0) {
            if (length != 1) {
                str.getChars(0, str.length(), cArr, i);
            } else {
                cArr[i] = str.charAt(0);
            }
        }
        return str.length() + i;
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(byte b, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        String str = format.upperCase ? "0123456789ABCDEF" : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat numberHexFormat = format.number;
        if (!numberHexFormat.isDigitsOnlyAndNoPadding) {
            return toHexStringImpl(b, numberHexFormat, str, 8);
        }
        char[] cArr = {str.charAt((b >> 4) & 15), str.charAt(b & Ascii.SI)};
        if (!numberHexFormat.removeLeadingZeros) {
            return StringsKt__StringsJVMKt.concatToString(cArr);
        }
        int iNumberOfLeadingZeros = (Integer.numberOfLeadingZeros(b & 255) - 24) >> 2;
        return StringsKt__StringsJVMKt.concatToString$default(cArr, iNumberOfLeadingZeros <= 1 ? iNumberOfLeadingZeros : 1, 0, 2, null);
    }

    public static String toHexString$default(byte[] bArr, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(bArr, hexFormat);
    }

    @ExperimentalStdlibApi
    public static final String toHexStringImpl(long j, HexFormat.NumberHexFormat numberHexFormat, String str, int i) {
        if ((i & 3) != 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i2 = i >> 2;
        int i3 = numberHexFormat.minLength;
        int i4 = i3 - i2;
        if (i4 < 0) {
            i4 = 0;
        }
        String str2 = numberHexFormat.prefix;
        String str3 = numberHexFormat.suffix;
        boolean z = numberHexFormat.removeLeadingZeros;
        int iCheckFormatLength = checkFormatLength(((long) str2.length()) + ((long) i4) + ((long) i2) + ((long) str3.length()));
        char[] cArr = new char[iCheckFormatLength];
        int charArrayIfNotEmpty = toCharArrayIfNotEmpty(str2, cArr, 0);
        if (i4 > 0) {
            int i5 = i4 + charArrayIfNotEmpty;
            Arrays.fill(cArr, charArrayIfNotEmpty, i5, str.charAt(0));
            charArrayIfNotEmpty = i5;
        }
        boolean z2 = z;
        int i6 = i;
        for (int i7 = 0; i7 < i2; i7++) {
            i6 -= 4;
            int i8 = (int) ((j >> i6) & 15);
            z2 = z2 && i8 == 0 && (i6 >> 2) >= i3;
            if (!z2) {
                cArr[charArrayIfNotEmpty] = str.charAt(i8);
                charArrayIfNotEmpty++;
            }
        }
        int charArrayIfNotEmpty2 = toCharArrayIfNotEmpty(str3, cArr, charArrayIfNotEmpty);
        return charArrayIfNotEmpty2 == iCheckFormatLength ? StringsKt__StringsJVMKt.concatToString(cArr) : StringsKt__StringsJVMKt.concatToString$default(cArr, 0, charArrayIfNotEmpty2, 1, null);
    }

    @ExperimentalStdlibApi
    public static final String toHexStringNoLineAndGroupSeparator(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        return bytesHexFormat.shortByteSeparatorNoPrefixAndSuffix ? toHexStringShortByteSeparatorNoPrefixAndSuffix(bArr, i, i2, bytesHexFormat, iArr) : toHexStringNoLineAndGroupSeparatorSlowPath(bArr, i, i2, bytesHexFormat, iArr);
    }

    @ExperimentalStdlibApi
    public static final String toHexStringNoLineAndGroupSeparatorSlowPath(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        String str = bytesHexFormat.bytePrefix;
        String str2 = bytesHexFormat.byteSuffix;
        String str3 = bytesHexFormat.byteSeparator;
        char[] cArr = new char[formattedStringLength(i2 - i, str3.length(), str.length(), str2.length())];
        int charArrayIfNotEmpty = toCharArrayIfNotEmpty(str2, cArr, formatByteAt(bArr, i, iArr, cArr, toCharArrayIfNotEmpty(str, cArr, 0)));
        while (true) {
            i++;
            if (i >= i2) {
                return StringsKt__StringsJVMKt.concatToString(cArr);
            }
            charArrayIfNotEmpty = toCharArrayIfNotEmpty(str2, cArr, formatByteAt(bArr, i, iArr, cArr, toCharArrayIfNotEmpty(str, cArr, toCharArrayIfNotEmpty(str3, cArr, charArrayIfNotEmpty))));
        }
    }

    @ExperimentalStdlibApi
    public static final String toHexStringShortByteSeparatorNoPrefixAndSuffix(byte[] bArr, int i, int i2, HexFormat.BytesHexFormat bytesHexFormat, int[] iArr) {
        int length = bytesHexFormat.byteSeparator.length();
        if (length > 1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i3 = i2 - i;
        int byteAt = 0;
        if (length == 0) {
            char[] cArr = new char[checkFormatLength(((long) i3) * 2)];
            while (i < i2) {
                byteAt = formatByteAt(bArr, i, iArr, cArr, byteAt);
                i++;
            }
            return StringsKt__StringsJVMKt.concatToString(cArr);
        }
        char[] cArr2 = new char[checkFormatLength((((long) i3) * 3) - 1)];
        char cCharAt = bytesHexFormat.byteSeparator.charAt(0);
        int byteAt2 = formatByteAt(bArr, i, iArr, cArr2, 0);
        for (int i4 = i + 1; i4 < i2; i4++) {
            cArr2[byteAt2] = cCharAt;
            byteAt2 = formatByteAt(bArr, i4, iArr, cArr2, byteAt2 + 1);
        }
        return StringsKt__StringsJVMKt.concatToString(cArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0043 A[SYNTHETIC] */
    @kotlin.ExperimentalStdlibApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String toHexStringSlowPath(byte[] r11, int r12, int r13, kotlin.text.HexFormat.BytesHexFormat r14, int[] r15) {
        /*
            int r1 = r14.bytesPerLine
            int r2 = r14.bytesPerGroup
            java.lang.String r7 = r14.bytePrefix
            java.lang.String r8 = r14.byteSuffix
            java.lang.String r9 = r14.byteSeparator
            java.lang.String r14 = r14.groupSeparator
            int r0 = r13 - r12
            int r3 = r14.length()
            int r4 = r9.length()
            int r5 = r7.length()
            int r6 = r8.length()
            int r0 = formattedStringLength(r0, r1, r2, r3, r4, r5, r6)
            char[] r3 = new char[r0]
            r4 = 0
            r5 = 0
            r6 = 0
            r10 = 0
        L28:
            if (r12 >= r13) goto L56
            if (r6 != r1) goto L36
            int r6 = r5 + 1
            r10 = 10
            r3[r5] = r10
            r5 = r6
            r6 = 0
        L34:
            r10 = 0
            goto L3d
        L36:
            if (r10 != r2) goto L3d
            int r5 = toCharArrayIfNotEmpty(r14, r3, r5)
            goto L34
        L3d:
            if (r10 == 0) goto L43
            int r5 = toCharArrayIfNotEmpty(r9, r3, r5)
        L43:
            int r5 = toCharArrayIfNotEmpty(r7, r3, r5)
            int r5 = formatByteAt(r11, r12, r15, r3, r5)
            int r5 = toCharArrayIfNotEmpty(r8, r3, r5)
            int r10 = r10 + 1
            int r6 = r6 + 1
            int r12 = r12 + 1
            goto L28
        L56:
            if (r5 != r0) goto L5d
            java.lang.String r11 = kotlin.text.StringsKt__StringsJVMKt.concatToString(r3)
            return r11
        L5d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "Check failed."
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.HexExtensionsKt.toHexStringSlowPath(byte[], int, int, kotlin.text.HexFormat$BytesHexFormat, int[]):java.lang.String");
    }

    public static final long wholeElementsPerSet(long j, long j2, int i) {
        if (j <= 0 || j2 <= 0) {
            return 0L;
        }
        long j3 = i;
        return (j + j3) / (j2 + j3);
    }

    @ExperimentalStdlibApi
    public static final byte[] hexToByteArray(String str, int i, int i2, HexFormat hexFormat) {
        byte[] bArrHexToByteArrayNoLineAndGroupSeparator;
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, str.length());
        if (i == i2) {
            return new byte[0];
        }
        HexFormat.BytesHexFormat bytesHexFormat = hexFormat.bytes;
        return (!bytesHexFormat.noLineAndGroupSeparator || (bArrHexToByteArrayNoLineAndGroupSeparator = hexToByteArrayNoLineAndGroupSeparator(str, i, i2, bytesHexFormat)) == null) ? hexToByteArraySlowPath(str, i, i2, bytesHexFormat) : bArrHexToByteArrayNoLineAndGroupSeparator;
    }

    @ExperimentalStdlibApi
    public static final int hexToInt(@NotNull String str, int i, int i2, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToIntImpl(str, i, i2, format, 8);
    }

    @ExperimentalStdlibApi
    public static final long hexToLong(@NotNull String str, int i, int i2, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToLongImpl(str, i, i2, format, 16);
    }

    public static final int formattedStringLength(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i > 0) {
            int i8 = i - 1;
            int i9 = i8 / i2;
            int i10 = (i2 - 1) / i3;
            int i11 = i % i2;
            if (i11 != 0) {
                i2 = i11;
            }
            int i12 = (i10 * i9) + ((i2 - 1) / i3);
            return checkFormatLength(((((long) i6) + 2 + ((long) i7)) * ((long) i)) + (((long) ((i8 - i9) - i12)) * ((long) i5)) + (((long) i12) * ((long) i4)) + ((long) i9));
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    @ExperimentalStdlibApi
    public static final byte hexToByte(String str, int i, int i2, HexFormat hexFormat) {
        return (byte) hexToIntImpl(str, i, i2, hexFormat, 2);
    }

    @ExperimentalStdlibApi
    public static final short hexToShort(String str, int i, int i2, HexFormat hexFormat) {
        return (short) hexToIntImpl(str, i, i2, hexFormat, 4);
    }

    public static final int formatByteAt(byte[] bArr, int i, int[] iArr, char[] cArr, int i2) {
        int i3 = iArr[bArr[i] & 255];
        cArr[i2] = (char) (i3 >> 8);
        cArr[i2 + 1] = (char) (i3 & 255);
        return i2 + 2;
    }

    public static byte hexToByte$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return (byte) hexToIntImpl(str, i, i2, hexFormat, 2);
    }

    public static byte[] hexToByteArray$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToByteArray(str, i, i2, hexFormat);
    }

    public static int hexToInt$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToInt(str, i, i2, hexFormat);
    }

    public static long hexToLong$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return hexToLong(str, i, i2, hexFormat);
    }

    public static short hexToShort$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return (short) hexToIntImpl(str, i, i2, hexFormat, 4);
    }

    public static String toHexString$default(byte[] bArr, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(bArr, i, i2, hexFormat);
    }

    public static String toHexString$default(byte b, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(b, hexFormat);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(int i, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        String str = format.upperCase ? "0123456789ABCDEF" : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat numberHexFormat = format.number;
        if (numberHexFormat.isDigitsOnlyAndNoPadding) {
            char[] cArr = {str.charAt((i >> 28) & 15), str.charAt((i >> 24) & 15), str.charAt((i >> 20) & 15), str.charAt((i >> 16) & 15), str.charAt((i >> 12) & 15), str.charAt((i >> 8) & 15), str.charAt((i >> 4) & 15), str.charAt(i & 15)};
            if (numberHexFormat.removeLeadingZeros) {
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i) >> 2;
                return StringsKt__StringsJVMKt.concatToString$default(cArr, iNumberOfLeadingZeros <= 7 ? iNumberOfLeadingZeros : 7, 0, 2, null);
            }
            return StringsKt__StringsJVMKt.concatToString(cArr);
        }
        return toHexStringImpl(i, numberHexFormat, str, 32);
    }

    public static String toHexString$default(short s, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(s, hexFormat);
    }

    public static String toHexString$default(int i, HexFormat hexFormat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(i, hexFormat);
    }

    public static String toHexString$default(long j, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            hexFormat = HexFormat.Default;
        }
        return toHexString(j, hexFormat);
    }

    @ExperimentalStdlibApi
    public static /* synthetic */ void getBYTE_TO_LOWER_CASE_HEX_DIGITS$annotations() {
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(long j, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        String str = format.upperCase ? "0123456789ABCDEF" : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat numberHexFormat = format.number;
        if (numberHexFormat.isDigitsOnlyAndNoPadding) {
            char[] cArr = {str.charAt((int) ((j >> 60) & 15)), str.charAt((int) ((j >> 56) & 15)), str.charAt((int) ((j >> 52) & 15)), str.charAt((int) ((j >> 48) & 15)), str.charAt((int) ((j >> 44) & 15)), str.charAt((int) ((j >> 40) & 15)), str.charAt((int) ((j >> 36) & 15)), str.charAt((int) ((j >> 32) & 15)), str.charAt((int) ((j >> 28) & 15)), str.charAt((int) ((j >> 24) & 15)), str.charAt((int) ((j >> 20) & 15)), str.charAt((int) ((j >> 16) & 15)), str.charAt((int) ((j >> 12) & 15)), str.charAt((int) ((j >> 8) & 15)), str.charAt((int) ((j >> 4) & 15)), str.charAt((int) (j & 15))};
            if (numberHexFormat.removeLeadingZeros) {
                int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j) >> 2;
                return StringsKt__StringsJVMKt.concatToString$default(cArr, iNumberOfLeadingZeros <= 15 ? iNumberOfLeadingZeros : 15, 0, 2, null);
            }
            return StringsKt__StringsJVMKt.concatToString(cArr);
        }
        return toHexStringImpl(j, numberHexFormat, str, 64);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(short s, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        String str = format.upperCase ? "0123456789ABCDEF" : LOWER_CASE_HEX_DIGITS;
        HexFormat.NumberHexFormat numberHexFormat = format.number;
        if (numberHexFormat.isDigitsOnlyAndNoPadding) {
            char[] cArr = {str.charAt((s >> 12) & 15), str.charAt((s >> 8) & 15), str.charAt((s >> 4) & 15), str.charAt(s & 15)};
            if (numberHexFormat.removeLeadingZeros) {
                int iNumberOfLeadingZeros = (Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16) >> 2;
                return StringsKt__StringsJVMKt.concatToString$default(cArr, iNumberOfLeadingZeros <= 3 ? iNumberOfLeadingZeros : 3, 0, 2, null);
            }
            return StringsKt__StringsJVMKt.concatToString(cArr);
        }
        return toHexStringImpl(s, numberHexFormat, str, 16);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(@NotNull byte[] bArr, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexString(bArr, 0, bArr.length, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @NotNull
    public static final String toHexString(@NotNull byte[] bArr, int i, int i2, @NotNull HexFormat format) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i, i2, bArr.length);
        if (i == i2) {
            return "";
        }
        int[] iArr = format.upperCase ? BYTE_TO_UPPER_CASE_HEX_DIGITS : BYTE_TO_LOWER_CASE_HEX_DIGITS;
        HexFormat.BytesHexFormat bytesHexFormat = format.bytes;
        if (bytesHexFormat.noLineAndGroupSeparator) {
            return toHexStringNoLineAndGroupSeparator(bArr, i, i2, bytesHexFormat, iArr);
        }
        return toHexStringSlowPath(bArr, i, i2, bytesHexFormat, iArr);
    }
}
