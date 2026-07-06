package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
import java.util.regex.Pattern;
import java.util.stream.Stream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Doubles extends DoublesMethodsForWeb {
    public static final int BYTES = 8;

    @GwtIncompatible
    public static final Pattern FLOATING_POINT_PATTERN = fpPattern();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class DoubleArrayAsList extends AbstractList<Double> implements RandomAccess, Serializable, List {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final double[] array;
        public final int end;
        public final int start;

        public DoubleArrayAsList(double[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Double) && Doubles.indexOf(this.array, ((Double) target).doubleValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof DoubleArrayAsList)) {
                return super.equals(object);
            }
            DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) object;
            int size = size();
            if (doubleArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == doubleArrayAsList.array[doubleArrayAsList.start + i]) {
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
                iHashCode = (iHashCode * 31) + Doubles.hashCode(this.array[i]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            int iIndexOf;
            if (!(target instanceof Double) || (iIndexOf = Doubles.indexOf(this.array, ((Double) target).doubleValue(), this.start, this.end)) < 0) {
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
            if (!(target instanceof Double) || (iLastIndexOf = Doubles.lastIndexOf(this.array, ((Double) target).doubleValue(), this.start, this.end)) < 0) {
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
        public java.util.List<Double> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            double[] dArr = this.array;
            int i = this.start;
            return new DoubleArrayAsList(dArr, fromIndex + i, i + toIndex);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        public double[] toDoubleArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
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

        public DoubleArrayAsList(double[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Double.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.AbstractList, java.util.List
        public Double set(int index, Double element) {
            Preconditions.checkElementIndex(index, size());
            double[] dArr = this.array;
            int i = this.start + index;
            double d = dArr[i];
            element.getClass();
            dArr[i] = element.doubleValue();
            return Double.valueOf(d);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public Spliterator.OfDouble spliterator() {
            return Spliterators.spliterator(this.array, this.start, this.end, 0);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Doubles.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(double[] left, double[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Double.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    public static java.util.List<Double> asList(double... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new DoubleArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    @InlineMe(replacement = "Double.compare(a, b)")
    public static int compare(double a, double b) {
        return Double.compare(a, b);
    }

    public static double[] concat(double[]... arrays) {
        long length = 0;
        for (double[] dArr : arrays) {
            length += (long) dArr.length;
        }
        double[] dArr2 = new double[checkNoOverflow(length)];
        int length2 = 0;
        for (double[] dArr3 : arrays) {
            System.arraycopy(dArr3, 0, dArr2, length2, dArr3.length);
            length2 += dArr3.length;
        }
        return dArr2;
    }

    public static double constrainToRange(double value, double min, double max) {
        if (min <= max) {
            return Math.min(Math.max(value, min), max);
        }
        throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", Double.valueOf(min), Double.valueOf(max)));
    }

    public static boolean contains(double[] array, double target) {
        for (double d : array) {
            if (d == target) {
                return true;
            }
        }
        return false;
    }

    public static double[] ensureCapacity(double[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    @GwtIncompatible
    public static Pattern fpPattern() {
        return Pattern.compile("[+-]?(?:NaN|Infinity|(?:\\d+#(?:\\.\\d*#)?|\\.\\d+#)(?:[eE][+-]?\\d+#)?[fFdD]?|0[xX](?:[0-9a-fA-F]+#(?:\\.[0-9a-fA-F]*#)?|\\.[0-9a-fA-F]+#)[pP][+-]?\\d+#[fFdD]?)".replace("#", "+"));
    }

    public static int hashCode(double value) {
        return Double.valueOf(value).hashCode();
    }

    public static int indexOf(double[] array, double target) {
        return indexOf(array, target, 0, array.length);
    }

    public static boolean isFinite(double value) {
        return Double.NEGATIVE_INFINITY < value && value < Double.POSITIVE_INFINITY;
    }

    public static String join(String separator, double... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 12);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(double[] array, double target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double max(double... array) {
        Preconditions.checkArgument(array.length > 0);
        double dMax = array[0];
        for (int i = 1; i < array.length; i++) {
            dMax = Math.max(dMax, array[i]);
        }
        return dMax;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double min(double... array) {
        Preconditions.checkArgument(array.length > 0);
        double dMin = array[0];
        for (int i = 1; i < array.length; i++) {
            dMin = Math.min(dMin, array[i]);
        }
        return dMin;
    }

    public static void reverse(double[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(double[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static void sortDescending(double[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.INSTANCE;
    }

    public static double[] toArray(java.util.Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).toDoubleArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            dArr[i] = ((Number) obj).doubleValue();
        }
        return dArr;
    }

    @GwtIncompatible
    public static Double tryParse(String string) {
        if (!FLOATING_POINT_PATTERN.matcher(string).matches()) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(string));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DoubleConverter extends Converter<String, Double> implements Serializable {
        public static final Converter<String, Double> INSTANCE = new DoubleConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        public DoubleConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Double value) {
            return value.toString();
        }

        @Override // com.google.common.base.Converter
        public Double doForward(String value) {
            return Double.valueOf(value);
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }

        /* JADX INFO: renamed from: doBackward, reason: avoid collision after fix types in other method */
        public String doBackward2(Double value) {
            return value.toString();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public Double doForward2(String value) {
            return Double.valueOf(value);
        }
    }

    public static int indexOf(double[] array, double target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(double[] array, int distance, int fromIndex, int toIndex) {
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

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(double[] r8, double[] r9) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Doubles.indexOf(double[], double[]):int");
    }

    public static void reverse(double[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            double d = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = d;
            fromIndex++;
        }
    }

    public static void sortDescending(double[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }
}
