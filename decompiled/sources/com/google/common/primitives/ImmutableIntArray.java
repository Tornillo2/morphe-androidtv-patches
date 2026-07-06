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
import j$.util.function.IntConsumer$CC;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@GwtCompatible
public final class ImmutableIntArray implements Serializable {
    public static final ImmutableIntArray EMPTY = new ImmutableIntArray(new int[0], 0, 0);
    public final int[] array;
    public final int end;
    public final transient int start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AsList extends AbstractList<Integer> implements RandomAccess, Serializable, List {
        public final ImmutableIntArray parent;

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
                if (obj instanceof Integer) {
                    int i2 = i + 1;
                    if (this.parent.array[i] == ((Integer) obj).intValue()) {
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
            if (target instanceof Integer) {
                return this.parent.indexOf(((Integer) target).intValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object target) {
            if (target instanceof Integer) {
                return this.parent.lastIndexOf(((Integer) target).intValue());
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
        public java.util.List<Integer> subList(int fromIndex, int toIndex) {
            ImmutableIntArray immutableIntArraySubArray = this.parent.subArray(fromIndex, toIndex);
            immutableIntArraySubArray.getClass();
            return new AsList(immutableIntArraySubArray);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        public AsList(ImmutableIntArray parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int index) {
            return Integer.valueOf(this.parent.get(index));
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public j$.util.Spliterator<Integer> spliterator() {
            return this.parent.spliterator();
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }
    }

    public static Builder builder(int initialCapacity) {
        Preconditions.checkArgument(initialCapacity >= 0, "Invalid initialCapacity: %s", initialCapacity);
        return new Builder(initialCapacity);
    }

    public static ImmutableIntArray copyOf(int[] values) {
        return values.length == 0 ? EMPTY : new ImmutableIntArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableIntArray of() {
        return EMPTY;
    }

    public java.util.List<Integer> asList() {
        return new AsList(this);
    }

    public boolean contains(int target) {
        return indexOf(target) >= 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableIntArray)) {
            return false;
        }
        ImmutableIntArray immutableIntArray = (ImmutableIntArray) object;
        if (length() != immutableIntArray.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (get(i) != immutableIntArray.get(i)) {
                return false;
            }
        }
        return true;
    }

    @IgnoreJRERequirement
    public void forEach(IntConsumer consumer) {
        consumer.getClass();
        for (int i = this.start; i < this.end; i++) {
            consumer.accept(this.array[i]);
        }
    }

    public int get(int index) {
        Preconditions.checkElementIndex(index, length());
        return this.array[this.start + index];
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = this.start; i2 < this.end; i2++) {
            i = (i * 31) + this.array[i2];
        }
        return i;
    }

    public int indexOf(int target) {
        for (int i = this.start; i < this.end; i++) {
            if (this.array[i] == target) {
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

    public int lastIndexOf(int target) {
        int i;
        int i2 = this.end;
        do {
            i2--;
            i = this.start;
            if (i2 < i) {
                return -1;
            }
        } while (this.array[i2] != target);
        return i2 - i;
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    @IgnoreJRERequirement
    public Spliterator.OfInt spliterator() {
        return Spliterators.spliterator(this.array, this.start, this.end, 1040);
    }

    @IgnoreJRERequirement
    public IntStream stream() {
        return DesugarArrays.stream(this.array, this.start, this.end);
    }

    public ImmutableIntArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, length());
        if (startIndex == endIndex) {
            return EMPTY;
        }
        int[] iArr = this.array;
        int i = this.start;
        return new ImmutableIntArray(iArr, startIndex + i, i + endIndex);
    }

    public int[] toArray() {
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

    public ImmutableIntArray trimmed() {
        return isPartialView() ? new ImmutableIntArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    public ImmutableIntArray(int[] array) {
        this(array, 0, array.length);
    }

    public static ImmutableIntArray copyOf(java.util.Collection<Integer> values) {
        return values.isEmpty() ? EMPTY : new ImmutableIntArray(Ints.toArray(values));
    }

    public static ImmutableIntArray of(int e0) {
        return new ImmutableIntArray(new int[]{e0}, 0, 1);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public int[] array;
        public int count = 0;

        public Builder(int initialCapacity) {
            this.array = new int[initialCapacity];
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
        public Builder add(int value) {
            ensureRoomFor(1);
            int[] iArr = this.array;
            int i = this.count;
            iArr[i] = value;
            this.count = i + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(int[] values) {
            ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public ImmutableIntArray build() {
            int i = this.count;
            return i == 0 ? ImmutableIntArray.EMPTY : new ImmutableIntArray(this.array, 0, i);
        }

        public final void ensureRoomFor(int numberToAdd) {
            int i = this.count + numberToAdd;
            int[] iArr = this.array;
            if (i > iArr.length) {
                this.array = Arrays.copyOf(iArr, expandedCapacity(iArr.length, i));
            }
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Integer> values) {
            if (values instanceof java.util.Collection) {
                addAll((java.util.Collection<Integer>) values);
                return this;
            }
            Iterator<Integer> it = values.iterator();
            while (it.hasNext()) {
                add(it.next().intValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(java.util.Collection<Integer> values) {
            ensureRoomFor(values.size());
            for (Integer num : values) {
                int[] iArr = this.array;
                int i = this.count;
                this.count = i + 1;
                iArr[i] = num.intValue();
            }
            return this;
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [j$.util.Spliterator, j$.util.Spliterator$OfInt] */
        @IgnoreJRERequirement
        @CanIgnoreReturnValue
        public Builder addAll(IntStream stream) {
            ?? Spliterator = stream.spliterator();
            long exactSizeIfKnown = Spliterator.getExactSizeIfKnown();
            if (exactSizeIfKnown > 0) {
                ensureRoomFor(Ints.saturatedCast(exactSizeIfKnown));
            }
            Spliterator.forEachRemaining(new IntConsumer() { // from class: com.google.common.primitives.ImmutableIntArray$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    this.f$0.add(i);
                }

                public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                    return IntConsumer$CC.$default$andThen(this, intConsumer);
                }
            });
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableIntArray values) {
            ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count = values.length() + this.count;
            return this;
        }
    }

    public ImmutableIntArray(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableIntArray copyOf(Iterable<Integer> values) {
        if (values instanceof java.util.Collection) {
            return copyOf((java.util.Collection<Integer>) values);
        }
        Builder builder = builder();
        builder.addAll(values);
        return builder.build();
    }

    public static ImmutableIntArray of(int e0, int e1) {
        return new ImmutableIntArray(new int[]{e0, e1}, 0, 2);
    }

    @IgnoreJRERequirement
    public static ImmutableIntArray copyOf(IntStream stream) {
        int[] array = stream.toArray();
        return array.length == 0 ? EMPTY : new ImmutableIntArray(array, 0, array.length);
    }

    public static ImmutableIntArray of(int e0, int e1, int e2) {
        return new ImmutableIntArray(new int[]{e0, e1, e2}, 0, 3);
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3}, 0, 4);
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3, int e4) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3, e4}, 0, 5);
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3, int e4, int e5) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3, e4, e5}, 0, 6);
    }

    public static ImmutableIntArray of(int first, int... rest) {
        Preconditions.checkArgument(rest.length <= 2147483646, "the total number of elements must fit in an int");
        int length = rest.length + 1;
        int[] iArr = new int[length];
        iArr[0] = first;
        System.arraycopy(rest, 0, iArr, 1, rest.length);
        return new ImmutableIntArray(iArr, 0, length);
    }
}
