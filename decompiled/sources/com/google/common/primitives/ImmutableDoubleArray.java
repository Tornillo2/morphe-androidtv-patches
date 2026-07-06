package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.DoubleConsumer$CC;
import j$.util.stream.DoubleStream;
import j$.util.stream.Stream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@GwtCompatible
public final class ImmutableDoubleArray implements Serializable {
    public static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0], 0, 0);
    public final double[] array;
    public final int end;
    public final transient int start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AsList extends AbstractList<Double> implements RandomAccess, Serializable, List {
        public final ImmutableDoubleArray parent;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return indexOf(target) >= 0;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object instanceof AsList) {
                return this.parent.equals(((AsList) object).parent);
            }
            if (!(object instanceof java.util.List)) {
                return false;
            }
            java.util.List list = (java.util.List) object;
            if (size() != list.size()) {
                return false;
            }
            int i = this.parent.start;
            for (Object obj : list) {
                if (obj instanceof Double) {
                    int i2 = i + 1;
                    if (ImmutableDoubleArray.areEqual(this.parent.array[i], ((Double) obj).doubleValue())) {
                        i = i2;
                    }
                }
                return false;
            }
            return true;
        }

        @Override // java.lang.Iterable, j$.util.Collection, j$.lang.a
        public /* synthetic */ void forEach(Consumer consumer) {
            Iterable$CC.$default$forEach(this, consumer);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            return this.parent.hashCode();
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            if (target instanceof Double) {
                return this.parent.indexOf(((Double) target).doubleValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object target) {
            if (target instanceof Double) {
                return this.parent.lastIndexOf(((Double) target).doubleValue());
            }
            return -1;
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
            return this.parent.length();
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
            ImmutableDoubleArray immutableDoubleArraySubArray = this.parent.subArray(fromIndex, toIndex);
            immutableDoubleArraySubArray.getClass();
            return new AsList(immutableDoubleArraySubArray);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        public AsList(ImmutableDoubleArray parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int index) {
            return Double.valueOf(this.parent.get(index));
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public j$.util.Spliterator<Double> spliterator() {
            return this.parent.spliterator();
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }
    }

    public static boolean areEqual(double a, double b) {
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
    }

    public static Builder builder(int initialCapacity) {
        Preconditions.checkArgument(initialCapacity >= 0, "Invalid initialCapacity: %s", initialCapacity);
        return new Builder(initialCapacity);
    }

    public static ImmutableDoubleArray copyOf(double[] values) {
        return values.length == 0 ? EMPTY : new ImmutableDoubleArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableDoubleArray of() {
        return EMPTY;
    }

    public java.util.List<Double> asList() {
        return new AsList(this);
    }

    public boolean contains(double target) {
        return indexOf(target) >= 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableDoubleArray)) {
            return false;
        }
        ImmutableDoubleArray immutableDoubleArray = (ImmutableDoubleArray) object;
        if (length() != immutableDoubleArray.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!areEqual(get(i), immutableDoubleArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    @IgnoreJRERequirement
    public void forEach(DoubleConsumer consumer) {
        consumer.getClass();
        for (int i = this.start; i < this.end; i++) {
            consumer.accept(this.array[i]);
        }
    }

    public double get(int index) {
        Preconditions.checkElementIndex(index, length());
        return this.array[this.start + index];
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i = this.start; i < this.end; i++) {
            iHashCode = (iHashCode * 31) + Doubles.hashCode(this.array[i]);
        }
        return iHashCode;
    }

    public int indexOf(double target) {
        for (int i = this.start; i < this.end; i++) {
            if (areEqual(this.array[i], target)) {
                return i - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public final boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    public int lastIndexOf(double target) {
        int i = this.end;
        do {
            i--;
            if (i < this.start) {
                return -1;
            }
        } while (!areEqual(this.array[i], target));
        return i - this.start;
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    @IgnoreJRERequirement
    public Spliterator.OfDouble spliterator() {
        return Spliterators.spliterator(this.array, this.start, this.end, 1040);
    }

    @IgnoreJRERequirement
    public DoubleStream stream() {
        return DesugarArrays.stream(this.array, this.start, this.end);
    }

    public ImmutableDoubleArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, length());
        if (startIndex == endIndex) {
            return EMPTY;
        }
        double[] dArr = this.array;
        int i = this.start;
        return new ImmutableDoubleArray(dArr, startIndex + i, i + endIndex);
    }

    public double[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(length() * 5);
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

    public ImmutableDoubleArray trimmed() {
        return isPartialView() ? new ImmutableDoubleArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    public ImmutableDoubleArray(double[] array) {
        this(array, 0, array.length);
    }

    public static ImmutableDoubleArray of(double e0) {
        return new ImmutableDoubleArray(new double[]{e0}, 0, 1);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public double[] array;
        public int count = 0;

        public Builder(int initialCapacity) {
            this.array = new double[initialCapacity];
        }

        public static int expandedCapacity(int oldCapacity, int minCapacity) {
            if (minCapacity < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int iHighestOneBit = oldCapacity + (oldCapacity >> 1) + 1;
            if (iHighestOneBit < minCapacity) {
                iHighestOneBit = Integer.highestOneBit(minCapacity - 1) << 1;
            }
            if (iHighestOneBit < 0) {
                return Integer.MAX_VALUE;
            }
            return iHighestOneBit;
        }

        @CanIgnoreReturnValue
        public Builder add(double value) {
            ensureRoomFor(1);
            double[] dArr = this.array;
            int i = this.count;
            dArr[i] = value;
            this.count = i + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(double[] values) {
            ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public ImmutableDoubleArray build() {
            int i = this.count;
            return i == 0 ? ImmutableDoubleArray.EMPTY : new ImmutableDoubleArray(this.array, 0, i);
        }

        public final void ensureRoomFor(int numberToAdd) {
            int i = this.count + numberToAdd;
            double[] dArr = this.array;
            if (i > dArr.length) {
                this.array = Arrays.copyOf(dArr, expandedCapacity(dArr.length, i));
            }
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Double> values) {
            if (values instanceof java.util.Collection) {
                addAll((java.util.Collection<Double>) values);
                return this;
            }
            Iterator<Double> it = values.iterator();
            while (it.hasNext()) {
                add(it.next().doubleValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(java.util.Collection<Double> values) {
            ensureRoomFor(values.size());
            for (Double d : values) {
                double[] dArr = this.array;
                int i = this.count;
                this.count = i + 1;
                dArr[i] = d.doubleValue();
            }
            return this;
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [j$.util.Spliterator, j$.util.Spliterator$OfDouble] */
        @IgnoreJRERequirement
        @CanIgnoreReturnValue
        public Builder addAll(DoubleStream stream) {
            ?? Spliterator = stream.spliterator();
            long exactSizeIfKnown = Spliterator.getExactSizeIfKnown();
            if (exactSizeIfKnown > 0) {
                ensureRoomFor(Ints.saturatedCast(exactSizeIfKnown));
            }
            Spliterator.forEachRemaining(new DoubleConsumer() { // from class: com.google.common.primitives.ImmutableDoubleArray$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.DoubleConsumer
                public final void accept(double d) {
                    this.f$0.add(d);
                }

                public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                    return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
                }
            });
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableDoubleArray values) {
            ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count = values.length() + this.count;
            return this;
        }
    }

    public ImmutableDoubleArray(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableDoubleArray copyOf(java.util.Collection<Double> values) {
        return values.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(values));
    }

    public static ImmutableDoubleArray of(double e0, double e1) {
        return new ImmutableDoubleArray(new double[]{e0, e1}, 0, 2);
    }

    public static ImmutableDoubleArray copyOf(Iterable<Double> values) {
        if (values instanceof java.util.Collection) {
            return copyOf((java.util.Collection<Double>) values);
        }
        Builder builder = builder();
        builder.addAll(values);
        return builder.build();
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2}, 0, 3);
    }

    @IgnoreJRERequirement
    public static ImmutableDoubleArray copyOf(DoubleStream stream) {
        double[] array = stream.toArray();
        return array.length == 0 ? EMPTY : new ImmutableDoubleArray(array, 0, array.length);
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3}, 0, 4);
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4}, 0, 5);
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4, double e5) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4, e5}, 0, 6);
    }

    public static ImmutableDoubleArray of(double first, double... rest) {
        Preconditions.checkArgument(rest.length <= 2147483646, "the total number of elements must fit in an int");
        int length = rest.length + 1;
        double[] dArr = new double[length];
        dArr[0] = first;
        System.arraycopy(rest, 0, dArr, 1, rest.length);
        return new ImmutableDoubleArray(dArr, 0, length);
    }
}
