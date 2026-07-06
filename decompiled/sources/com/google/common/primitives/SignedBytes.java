package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(byte[] left, byte[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Byte.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    public static byte checkedCast(long value) {
        byte b = (byte) value;
        Preconditions.checkArgument(((long) b) == value, "Out of range: %s", value);
        return b;
    }

    public static int compare(byte a, byte b) {
        return Byte.compare(a, b);
    }

    public static String join(String separator, byte... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 5);
        sb.append((int) array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append((int) array[i]);
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static byte max(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        byte b = array[0];
        for (int i = 1; i < array.length; i++) {
            byte b2 = array[i];
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    public static byte min(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        byte b = array[0];
        for (int i = 1; i < array.length; i++) {
            byte b2 = array[i];
            if (b2 < b) {
                b = b2;
            }
        }
        return b;
    }

    public static byte saturatedCast(long value) {
        if (value > 127) {
            return (byte) 127;
        }
        if (value < -128) {
            return (byte) -128;
        }
        return (byte) value;
    }

    public static void sortDescending(byte[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Bytes.reverse(array, fromIndex, toIndex);
    }
}
