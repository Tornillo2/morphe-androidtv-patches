package com.google.common.primitives;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Arrays;
import java.util.Comparator;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = -128;
    public static final byte MAX_VALUE = -1;
    public static final int UNSIGNED_MASK = 255;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class LexicographicalComparatorHolder {
        public static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName().concat("$UnsafeComparator");
        public static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public static /* synthetic */ PureJavaComparator[] $values() {
                return new PureJavaComparator[]{INSTANCE};
            }

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] left, byte[] right) {
                int iMin = Math.min(left.length, right.length);
                for (int i = 0; i < iMin; i++) {
                    int iCompare = UnsignedBytes.compare(left[i], right[i]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                }
                return left.length - right.length;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @VisibleForTesting
        public enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;

            public static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            public static final int BYTE_ARRAY_BASE_OFFSET;
            public static final Unsafe theUnsafe;

            public static /* synthetic */ UnsafeComparator[] $values() {
                return new UnsafeComparator[]{INSTANCE};
            }

            static {
                Unsafe unsafe = getUnsafe();
                theUnsafe = unsafe;
                int iArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
                BYTE_ARRAY_BASE_OFFSET = iArrayBaseOffset;
                if (!Objects.equals(System.getProperty("sun.arch.data.model"), "64") || iArrayBaseOffset % 8 != 0 || unsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
                }
            }

            public static Unsafe getUnsafe() {
                try {
                    try {
                        return Unsafe.getUnsafe();
                    } catch (PrivilegedActionException e) {
                        throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                    }
                } catch (SecurityException unused) {
                    return (Unsafe) AccessController.doPrivileged(new UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$$ExternalSyntheticLambda0());
                }
            }

            public static /* synthetic */ Unsafe lambda$getUnsafe$0() throws Exception {
                for (Field field : Unsafe.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    if (Unsafe.class.isInstance(obj)) {
                        return (Unsafe) Unsafe.class.cast(obj);
                    }
                }
                throw new NoSuchFieldError("the Unsafe");
            }

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] left, byte[] right) {
                int iMin = Math.min(left.length, right.length);
                int i = iMin & (-8);
                int i2 = 0;
                while (i2 < i) {
                    Unsafe unsafe = theUnsafe;
                    int i3 = BYTE_ARRAY_BASE_OFFSET;
                    long j = i2;
                    long j2 = unsafe.getLong(left, ((long) i3) + j);
                    long j3 = unsafe.getLong(right, ((long) i3) + j);
                    if (j2 != j3) {
                        if (BIG_ENDIAN) {
                            return Long.compare(j2 ^ Long.MIN_VALUE, j3 ^ Long.MIN_VALUE);
                        }
                        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & (-8);
                        return ((int) ((j2 >>> iNumberOfTrailingZeros) & 255)) - ((int) ((j3 >>> iNumberOfTrailingZeros) & 255));
                    }
                    i2 += 8;
                }
                while (i2 < iMin) {
                    int iCompare = UnsignedBytes.compare(left[i2], right[i2]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                    i2++;
                }
                return left.length - right.length;
            }
        }

        public static Comparator<byte[]> getBestComparator() {
            try {
                Object[] enumConstants = Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants();
                Objects.requireNonNull(enumConstants);
                return (Comparator) enumConstants[0];
            } catch (Throwable unused) {
                return PureJavaComparator.INSTANCE;
            }
        }
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long value) {
        Preconditions.checkArgument((value >> 8) == 0, "out of range: %s", value);
        return (byte) value;
    }

    public static int compare(byte a, byte b) {
        return (a & 255) - (b & 255);
    }

    public static byte flip(byte b) {
        return (byte) (b ^ 128);
    }

    public static String join(String separator, byte... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((separator.length() + 3) * array.length);
        sb.append(array[0] & 255);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(toString(array[i], 10));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    public static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static byte max(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0] & 255;
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2] & 255;
            if (i3 > i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    public static byte min(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0] & 255;
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2] & 255;
            if (i3 < i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    @CanIgnoreReturnValue
    public static byte parseUnsignedByte(String string) {
        return parseUnsignedByte(string, 10);
    }

    public static byte saturatedCast(long value) {
        if (value > 255) {
            return (byte) -1;
        }
        if (value < 0) {
            return (byte) 0;
        }
        return (byte) value;
    }

    public static void sort(byte[] array) {
        array.getClass();
        sort(array, 0, array.length);
    }

    public static void sortDescending(byte[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static int toInt(byte value) {
        return value & 255;
    }

    public static String toString(byte x) {
        return toString(x, 10);
    }

    @CanIgnoreReturnValue
    public static byte parseUnsignedByte(String string, int radix) {
        string.getClass();
        int i = Integer.parseInt(string, radix);
        if ((i >> 8) == 0) {
            return (byte) i;
        }
        throw new NumberFormatException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("out of range: ", i));
    }

    public static String toString(byte x, int radix) {
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
        return Integer.toString(x & 255, radix);
    }

    public static void sort(byte[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = (byte) (array[i] ^ 128);
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = (byte) (array[fromIndex] ^ 128);
            fromIndex++;
        }
    }

    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = (byte) (array[i] ^ 127);
        }
        Arrays.sort(array, fromIndex, toIndex);
        while (fromIndex < toIndex) {
            array[fromIndex] = (byte) (array[fromIndex] ^ 127);
            fromIndex++;
        }
    }
}
