package com.google.common.primitives;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] left, long[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                long j = left[i];
                long j2 = right[i];
                if (j != j2) {
                    return UnsignedLongs.compare(j, j2);
                }
            }
            return left.length - right.length;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ParseOverflowDetection {
        public static final long[] maxValueDivs = new long[37];
        public static final int[] maxValueMods = new int[37];
        public static final int[] maxSafeDigits = new int[37];

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i = 2; i <= 36; i++) {
                long j = i;
                maxValueDivs[i] = UnsignedLongs.divide(-1L, j);
                maxValueMods[i] = (int) UnsignedLongs.remainder(-1L, j);
                maxSafeDigits[i] = bigInteger.toString(i).length() - 1;
            }
        }

        public static boolean overflowInParse(long current, int digit, int radix) {
            if (current < 0) {
                return true;
            }
            long j = maxValueDivs[radix];
            if (current < j) {
                return false;
            }
            return current > j || digit > maxValueMods[radix];
        }
    }

    public static int compare(long a, long b) {
        return Long.compare(a ^ Long.MIN_VALUE, b ^ Long.MIN_VALUE);
    }

    @CanIgnoreReturnValue
    public static long decode(String stringValue) {
        ParseRequest parseRequestFromString = ParseRequest.fromString(stringValue);
        try {
            return parseUnsignedLong(parseRequestFromString.rawValue, parseRequestFromString.radix);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = new NumberFormatException("Error parsing value: ".concat(stringValue));
            numberFormatException.initCause(e);
            throw numberFormatException;
        }
    }

    public static long divide(long dividend, long divisor) {
        if (divisor < 0) {
            return compare(dividend, divisor) < 0 ? 0L : 1L;
        }
        if (dividend >= 0) {
            return dividend / divisor;
        }
        long j = ((dividend >>> 1) / divisor) << 1;
        return j + ((long) (compare(dividend - (j * divisor), divisor) < 0 ? 0 : 1));
    }

    public static long flip(long a) {
        return a ^ Long.MIN_VALUE;
    }

    public static String join(String separator, long... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 5);
        sb.append(toString(array[0], 10));
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(toString(array[i], 10));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long j = array[0] ^ Long.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            long j2 = array[i] ^ Long.MIN_VALUE;
            if (j2 > j) {
                j = j2;
            }
        }
        return j ^ Long.MIN_VALUE;
    }

    public static long min(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long j = array[0] ^ Long.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            long j2 = array[i] ^ Long.MIN_VALUE;
            if (j2 < j) {
                j = j2;
            }
        }
        return j ^ Long.MIN_VALUE;
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String string) {
        return parseUnsignedLong(string, 10);
    }

    public static long remainder(long dividend, long divisor) {
        if (divisor < 0) {
            return compare(dividend, divisor) < 0 ? dividend : dividend - divisor;
        }
        if (dividend >= 0) {
            return dividend % divisor;
        }
        long j = dividend - ((((dividend >>> 1) / divisor) << 1) * divisor);
        if (compare(j, divisor) < 0) {
            divisor = 0;
        }
        return j - divisor;
    }

    public static void sort(long[] array) {
        array.getClass();
        sort(array, 0, array.length);
    }

    public static void sortDescending(long[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static String toString(long x) {
        return toString(x, 10);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String string, int radix) {
        string.getClass();
        if (string.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        if (radix < 2 || radix > 36) {
            throw new NumberFormatException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("illegal radix: ", radix));
        }
        int i = ParseOverflowDetection.maxSafeDigits[radix] - 1;
        long j = 0;
        for (int i2 = 0; i2 < string.length(); i2++) {
            int iDigit = Character.digit(string.charAt(i2), radix);
            if (iDigit == -1) {
                throw new NumberFormatException(string);
            }
            if (i2 > i && ParseOverflowDetection.overflowInParse(j, iDigit, radix)) {
                throw new NumberFormatException("Too large for unsigned long: ".concat(string));
            }
            j = (j * ((long) radix)) + ((long) iDigit);
        }
        return j;
    }

    public static String toString(long x, int radix) {
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
        if (x == 0) {
            return "0";
        }
        if (x > 0) {
            return Long.toString(x, radix);
        }
        int i = 64;
        char[] cArr = new char[64];
        int i2 = radix - 1;
        if ((radix & i2) == 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(radix);
            do {
                i--;
                cArr[i] = Character.forDigit(((int) x) & i2, radix);
                x >>>= iNumberOfTrailingZeros;
            } while (x != 0);
        } else {
            long jDivide = (radix & 1) == 0 ? (x >>> 1) / ((long) (radix >>> 1)) : divide(x, radix);
            long j = radix;
            int i3 = 63;
            cArr[63] = Character.forDigit((int) (x - (jDivide * j)), radix);
            while (jDivide > 0) {
                i3--;
                cArr[i3] = Character.forDigit((int) (jDivide % j), radix);
                jDivide /= j;
            }
            i = i3;
        }
        return new String(cArr, i, 64 - i);
    }

    public static void sort(long[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = Long.MIN_VALUE ^ array[i];
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = array[fromIndex] ^ Long.MIN_VALUE;
            fromIndex++;
        }
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = Long.MAX_VALUE ^ array[i];
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = array[fromIndex] ^ Long.MAX_VALUE;
            fromIndex++;
        }
    }
}
