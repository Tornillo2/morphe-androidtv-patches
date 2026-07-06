package com.google.common.primitives;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.InlineMe;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AsciiDigits {
        public static final byte[] asciiDigits;

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i = 0; i < 10; i++) {
                bArr[i + 48] = (byte) i;
            }
            for (int i2 = 0; i2 < 26; i2++) {
                byte b = (byte) (i2 + 10);
                bArr[i2 + 65] = b;
                bArr[i2 + 97] = b;
            }
            asciiDigits = bArr;
        }

        public static int digit(char c) {
            if (c < 128) {
                return asciiDigits[c];
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Longs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] left, long[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Long.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable, List {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final long[] array;
        public final int end;
        public final int start;

        public LongArrayAsList(long[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Long) && Longs.indexOf(this.array, ((Long) target).longValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof LongArrayAsList)) {
                return super.equals(object);
            }
            LongArrayAsList longArrayAsList = (LongArrayAsList) object;
            int size = size();
            if (longArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == longArrayAsList.array[longArrayAsList.start + i]) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // java.lang.Iterable, j$.util.Collection, j$.lang.a
        public /* synthetic */ void forEach(Consumer consumer) {
            Iterable$CC.$default$forEach(this, consumer);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i = this.start; i < this.end; i++) {
                iHashCode = (iHashCode * 31) + Longs.hashCode(this.array[i]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            int iIndexOf;
            if (!(target instanceof Long) || (iIndexOf = Longs.indexOf(this.array, ((Long) target).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object target) {
            int iLastIndexOf;
            if (!(target instanceof Long) || (iLastIndexOf = Longs.lastIndexOf(this.array, ((Long) target).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.Collection
        public /* synthetic */ Stream parallelStream() {
            return Stream.Wrapper.convert(parallelStream());
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ boolean removeIf(Predicate predicate) {
            return Collection.CC.$default$removeIf(this, predicate);
        }

        @Override // java.util.List, j$.util.List
        public /* synthetic */ void replaceAll(UnaryOperator unaryOperator) {
            List.CC.$default$replaceAll(this, unaryOperator);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.List, j$.util.List
        public /* synthetic */ void sort(Comparator comparator) {
            List.CC.$default$sort(this, comparator);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List
        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection
        public /* synthetic */ java.util.stream.Stream stream() {
            return Stream.Wrapper.convert(stream());
        }

        @Override // java.util.AbstractList, java.util.List
        public java.util.List<Long> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            long[] jArr = this.array;
            int i = this.start;
            return new LongArrayAsList(jArr, fromIndex + i, i + toIndex);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        public long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 10);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i >= this.end) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append(this.array[i]);
            }
        }

        public LongArrayAsList(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Long.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.AbstractList, java.util.List
        public Long set(int index, Long element) {
            Preconditions.checkElementIndex(index, size());
            long[] jArr = this.array;
            int i = this.start + index;
            long j = jArr[i];
            element.getClass();
            jArr[i] = element.longValue();
            return Long.valueOf(j);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public Spliterator.OfLong spliterator() {
            return Spliterators.spliterator(this.array, this.start, this.end, 0);
        }
    }

    public static java.util.List<Long> asList(long... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new LongArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    @InlineMe(replacement = "Long.compare(a, b)")
    public static int compare(long a, long b) {
        return Long.compare(a, b);
    }

    public static long[] concat(long[]... arrays) {
        long length = 0;
        for (long[] jArr : arrays) {
            length += (long) jArr.length;
        }
        long[] jArr2 = new long[checkNoOverflow(length)];
        int length2 = 0;
        for (long[] jArr3 : arrays) {
            System.arraycopy(jArr3, 0, jArr2, length2, jArr3.length);
            length2 += jArr3.length;
        }
        return jArr2;
    }

    public static long constrainToRange(long value, long min, long max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
        return Math.min(Math.max(value, min), max);
    }

    public static boolean contains(long[] array, long target) {
        for (long j : array) {
            if (j == target) {
                return true;
            }
        }
        return false;
    }

    public static long[] ensureCapacity(long[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static long fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 8, "array too small: %s < %s", bytes.length, 8);
        return fromBytes(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public static long fromBytes(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((((long) b2) & 255) << 48) | ((((long) b1) & 255) << 56) | ((((long) b3) & 255) << 40) | ((((long) b4) & 255) << 32) | ((((long) b5) & 255) << 24) | ((((long) b6) & 255) << 16) | ((((long) b7) & 255) << 8) | (((long) b8) & 255);
    }

    public static int hashCode(long value) {
        return (int) (value ^ (value >>> 32));
    }

    public static int indexOf(long[] array, long target) {
        return indexOf(array, target, 0, array.length);
    }

    public static String join(String separator, long... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 10);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(long[] array, long target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long j = array[0];
        for (int i = 1; i < array.length; i++) {
            long j2 = array[i];
            if (j2 > j) {
                j = j2;
            }
        }
        return j;
    }

    public static long min(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long j = array[0];
        for (int i = 1; i < array.length; i++) {
            long j2 = array[i];
            if (j2 < j) {
                j = j2;
            }
        }
        return j;
    }

    public static void reverse(long[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(long[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static void sortDescending(long[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] toArray(java.util.Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            jArr[i] = ((Number) obj).longValue();
        }
        return jArr;
    }

    public static byte[] toByteArray(long value) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (255 & value);
            value >>= 8;
        }
        return bArr;
    }

    public static Long tryParse(String string) {
        return tryParse(string, 10);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LongConverter extends Converter<String, Long> implements Serializable {
        public static final Converter<String, Long> INSTANCE = new LongConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        public LongConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Long value) {
            return value.toString();
        }

        @Override // com.google.common.base.Converter
        public Long doForward(String value) {
            return Long.decode(value);
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        /* JADX INFO: renamed from: doBackward, reason: avoid collision after fix types in other method */
        public String doBackward2(Long value) {
            return value.toString();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public Long doForward2(String value) {
            return Long.decode(value);
        }
    }

    public static int indexOf(long[] array, long target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(long[] array, int distance, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        if (array.length <= 1) {
            return;
        }
        int i = toIndex - fromIndex;
        int i2 = (-distance) % i;
        if (i2 < 0) {
            i2 += i;
        }
        int i3 = i2 + fromIndex;
        if (i3 == fromIndex) {
            return;
        }
        reverse(array, fromIndex, i3);
        reverse(array, i3, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static Long tryParse(String string, int radix) {
        string.getClass();
        if (string.isEmpty()) {
            return null;
        }
        if (radix < 2 || radix > 36) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("radix must be between MIN_RADIX and MAX_RADIX but was ", radix));
        }
        int i = string.charAt(0) == '-' ? 1 : 0;
        if (i == string.length()) {
            return null;
        }
        int i2 = i + 1;
        int iDigit = AsciiDigits.digit(string.charAt(i));
        if (iDigit < 0 || iDigit >= radix) {
            return null;
        }
        long j = -iDigit;
        long j2 = radix;
        long j3 = Long.MIN_VALUE / j2;
        while (i2 < string.length()) {
            int i3 = i2 + 1;
            int iDigit2 = AsciiDigits.digit(string.charAt(i2));
            if (iDigit2 < 0 || iDigit2 >= radix || j < j3) {
                return null;
            }
            long j4 = j * j2;
            long j5 = iDigit2;
            if (j4 < j5 - Long.MIN_VALUE) {
                return null;
            }
            j = j4 - j5;
            i2 = i3;
        }
        if (i != 0) {
            return Long.valueOf(j);
        }
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(long[] r8, long[] r9) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r8, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r9, r0)
            int r0 = r9.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r8.length
            int r3 = r9.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2c
            r2 = 0
        L18:
            int r3 = r9.length
            if (r2 >= r3) goto L2b
            int r3 = r0 + r2
            r3 = r8[r3]
            r5 = r9[r2]
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L28
            int r0 = r0 + 1
            goto L10
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            return r0
        L2c:
            r8 = -1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Longs.indexOf(long[], long[]):int");
    }

    public static void reverse(long[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            long j = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = j;
            fromIndex++;
        }
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }
}
