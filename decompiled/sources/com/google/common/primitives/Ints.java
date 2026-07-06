package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Ascii;
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
@GwtCompatible(emulated = true)
public final class Ints extends IntsMethodsForWeb {
    public static final int BYTES = 4;
    public static final int MAX_POWER_OF_TWO = 1073741824;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable, List {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final int[] array;
        public final int end;
        public final int start;

        public IntArrayAsList(int[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Integer) && Ints.indexOf(this.array, ((Integer) target).intValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof IntArrayAsList)) {
                return super.equals(object);
            }
            IntArrayAsList intArrayAsList = (IntArrayAsList) object;
            int size = size();
            if (intArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == intArrayAsList.array[intArrayAsList.start + i]) {
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
            int i = 1;
            for (int i2 = this.start; i2 < this.end; i2++) {
                i = (i * 31) + this.array[i2];
            }
            return i;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            int iIndexOf;
            if (!(target instanceof Integer) || (iIndexOf = Ints.indexOf(this.array, ((Integer) target).intValue(), this.start, this.end)) < 0) {
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
            if (!(target instanceof Integer) || (iLastIndexOf = Ints.lastIndexOf(this.array, ((Integer) target).intValue(), this.start, this.end)) < 0) {
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
        public java.util.List<Integer> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            int[] iArr = this.array;
            int i = this.start;
            return new IntArrayAsList(iArr, fromIndex + i, i + toIndex);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        public int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        public IntArrayAsList(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Integer.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer set(int index, Integer element) {
            Preconditions.checkElementIndex(index, size());
            int[] iArr = this.array;
            int i = this.start + index;
            int i2 = iArr[i];
            element.getClass();
            iArr[i] = element.intValue();
            return Integer.valueOf(i2);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public Spliterator.OfInt spliterator() {
            return Spliterators.spliterator(this.array, this.start, this.end, 0);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Ints.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] left, int[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Integer.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    public static java.util.List<Integer> asList(int... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new IntArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    public static int checkedCast(long value) {
        int i = (int) value;
        Preconditions.checkArgument(((long) i) == value, "Out of range: %s", value);
        return i;
    }

    @InlineMe(replacement = "Integer.compare(a, b)")
    public static int compare(int a, int b) {
        return Integer.compare(a, b);
    }

    public static int[] concat(int[]... arrays) {
        long length = 0;
        for (int[] iArr : arrays) {
            length += (long) iArr.length;
        }
        int[] iArr2 = new int[checkNoOverflow(length)];
        int length2 = 0;
        for (int[] iArr3 : arrays) {
            System.arraycopy(iArr3, 0, iArr2, length2, iArr3.length);
            length2 += iArr3.length;
        }
        return iArr2;
    }

    public static int constrainToRange(int value, int min, int max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
        return Math.min(Math.max(value, min), max);
    }

    public static boolean contains(int[] array, int target) {
        for (int i : array) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }

    public static int[] ensureCapacity(int[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static int fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 4, "array too small: %s < %s", bytes.length, 4);
        return fromBytes(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return (b1 << Ascii.CAN) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    public static int indexOf(int[] array, int target) {
        return indexOf(array, target, 0, array.length);
    }

    public static String join(String separator, int... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 5);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(int[] array, int target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static int max(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0];
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2];
            if (i3 > i) {
                i = i3;
            }
        }
        return i;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static int min(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int i = array[0];
        for (int i2 = 1; i2 < array.length; i2++) {
            int i3 = array[i2];
            if (i3 < i) {
                i = i3;
            }
        }
        return i;
    }

    public static void reverse(int[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(int[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static int saturatedCast(long value) {
        if (value > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (value < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    public static void sortDescending(int[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static Converter<String, Integer> stringConverter() {
        return IntConverter.INSTANCE;
    }

    public static int[] toArray(java.util.Collection<? extends Number> collection) {
        if (collection instanceof IntArrayAsList) {
            return ((IntArrayAsList) collection).toIntArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            iArr[i] = ((Number) obj).intValue();
        }
        return iArr;
    }

    public static byte[] toByteArray(int value) {
        return new byte[]{(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value};
    }

    public static Integer tryParse(String string) {
        return tryParse(string, 10);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IntConverter extends Converter<String, Integer> implements Serializable {
        public static final Converter<String, Integer> INSTANCE = new IntConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        public IntConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Integer value) {
            return value.toString();
        }

        @Override // com.google.common.base.Converter
        public Integer doForward(String value) {
            return Integer.decode(value);
        }

        public String toString() {
            return "Ints.stringConverter()";
        }

        /* JADX INFO: renamed from: doBackward, reason: avoid collision after fix types in other method */
        public String doBackward2(Integer value) {
            return value.toString();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public Integer doForward2(String value) {
            return Integer.decode(value);
        }
    }

    public static int indexOf(int[] array, int target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(int[] array, int distance, int fromIndex, int toIndex) {
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

    public static Integer tryParse(String string, int radix) {
        Long lTryParse = Longs.tryParse(string, radix);
        if (lTryParse == null || lTryParse.longValue() != lTryParse.intValue()) {
            return null;
        }
        return Integer.valueOf(lTryParse.intValue());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(int[] r5, int[] r6) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r5, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r6, r0)
            int r0 = r6.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r5.length
            int r3 = r6.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2a
            r2 = 0
        L18:
            int r3 = r6.length
            if (r2 >= r3) goto L29
            int r3 = r0 + r2
            r3 = r5[r3]
            r4 = r6[r2]
            if (r3 == r4) goto L26
            int r0 = r0 + 1
            goto L10
        L26:
            int r2 = r2 + 1
            goto L18
        L29:
            return r0
        L2a:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Ints.indexOf(int[], int[]):int");
    }

    public static void reverse(int[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            int i2 = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = i2;
            fromIndex++;
        }
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static int hashCode(int value) {
        return value;
    }
}
