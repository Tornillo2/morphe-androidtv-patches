package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class UnsignedInts {
    public static final long INT_MASK = 4294967295L;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] left, int[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int i2 = left[i];
                int i3 = right[i];
                if (i2 != i3) {
                    return UnsignedInts.compare(i2, i3);
                }
            }
            return left.length - right.length;
        }
    }

    public static int checkedCast(long value) {
        Preconditions.checkArgument((value >> 32) == 0, "out of range: %s", value);
        return (int) value;
    }

    public static int compare(int a, int b) {
        return Integer.compare(a ^ Integer.MIN_VALUE, b ^ Integer.MIN_VALUE);
    }

    @CanIgnoreReturnValue
    public static int decode(String stringValue) {
        ParseRequest parseRequestFromString = ParseRequest.fromString(stringValue);
        try {
            return parseUnsignedInt(parseRequestFromString.rawValue, parseRequestFromString.radix);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = new NumberFormatException("Error parsing value: ".concat(stringValue));
            numberFormatException.initCause(e);
            throw numberFormatException;
        }
    }

    public static int divide(int dividend, int divisor) {
        return (int) ((((long) dividend) & 4294967295L) / (((long) divisor) & 4294967295L));
    }

    public static int flip(int value) {
        return value ^ Integer.MIN_VALUE;
    }

    public static String join(String separator, int... array) {
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

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int max(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0] ^ Integer.MIN_VALUE;
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2] ^ Integer.MIN_VALUE;
            if (i3 > i) {
                i = i3;
            }
        }
        return i ^ Integer.MIN_VALUE;
    }

    public static int min(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0] ^ Integer.MIN_VALUE;
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2] ^ Integer.MIN_VALUE;
            if (i3 < i) {
                i = i3;
            }
        }
        return i ^ Integer.MIN_VALUE;
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String s) {
        return parseUnsignedInt(s, 10);
    }

    public static int remainder(int dividend, int divisor) {
        return (int) ((((long) dividend) & 4294967295L) % (((long) divisor) & 4294967295L));
    }

    public static int saturatedCast(long value) {
        if (value <= 0) {
            return 0;
        }
        if (value >= 4294967296L) {
            return -1;
        }
        return (int) value;
    }

    public static void sort(int[] array) {
        array.getClass();
        sort(array, 0, array.length);
    }

    public static void sortDescending(int[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static long toLong(int value) {
        return ((long) value) & 4294967295L;
    }

    public static String toString(int x) {
        return toString(x, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String string, int radix) {
        string.getClass();
        long j = Long.parseLong(string, radix);
        if ((4294967295L & j) == j) {
            return (int) j;
        }
        throw new NumberFormatException("Input " + string + " in base " + radix + " is not in the range of an unsigned integer");
    }

    public static String toString(int x, int radix) {
        return Long.toString(((long) x) & 4294967295L, radix);
    }

    public static void sort(int[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = Integer.MIN_VALUE ^ array[i];
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = array[fromIndex] ^ Integer.MIN_VALUE;
            fromIndex++;
        }
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = Integer.MAX_VALUE ^ array[i];
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = array[fromIndex] ^ Integer.MAX_VALUE;
            fromIndex++;
        }
    }
}
