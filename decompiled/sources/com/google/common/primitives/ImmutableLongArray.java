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
import j$.util.function.LongConsumer$CC;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@GwtCompatible
public final class ImmutableLongArray implements Serializable {
    public static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0], 0, 0);
    public final long[] array;
    public final int end;
    public final transient int start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AsList extends AbstractList<Long> implements RandomAccess, Serializable, List {
        public final ImmutableLongArray parent;

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
                if (obj instanceof Long) {
                    int i2 = i + 1;
                    if (this.parent.array[i] == ((Long) obj).longValue()) {
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
            if (target instanceof Long) {
                return this.parent.indexOf(((Long) target).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object target) {
            if (target instanceof Long) {
                return this.parent.lastIndexOf(((Long) target).longValue());
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
        public java.util.List<Long> subList(int fromIndex, int toIndex) {
            ImmutableLongArray immutableLongArraySubArray = this.parent.subArray(fromIndex, toIndex);
            immutableLongArraySubArray.getClass();
            return new AsList(immutableLongArraySubArray);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        public AsList(ImmutableLongArray parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int index) {
            return Long.valueOf(this.parent.get(index));
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return Collection.CC.$default$parallelStream(this);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.a
        @IgnoreJRERequirement
        public j$.util.Spliterator<Long> spliterator() {
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

    public static ImmutableLongArray copyOf(long[] values) {
        return values.length == 0 ? EMPTY : new ImmutableLongArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableLongArray of() {
        return EMPTY;
    }

    public java.util.List<Long> asList() {
        return new AsList(this);
    }

    public boolean contains(long target) {
        return indexOf(target) >= 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableLongArray)) {
            return false;
        }
        ImmutableLongArray immutableLongArray = (ImmutableLongArray) object;
        if (length() != immutableLongArray.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (get(i) != immutableLongArray.get(i)) {
                return false;
            }
        }
        return true;
    }

    @IgnoreJRERequirement
    public void forEach(LongConsumer consumer) {
        consumer.getClass();
        for (int i = this.start; i < this.end; i++) {
            consumer.accept(this.array[i]);
        }
    }

    public long get(int index) {
        Preconditions.checkElementIndex(index, length());
        return this.array[this.start + index];
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i = this.start; i < this.end; i++) {
            iHashCode = (iHashCode * 31) + Longs.hashCode(this.array[i]);
        }
        return iHashCode;
    }

    public int indexOf(long target) {
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

    public int lastIndexOf(long target) {
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
    public Spliterator.OfLong spliterator() {
        return Spliterators.spliterator(this.array, this.start, this.end, 1040);
    }

    @IgnoreJRERequirement
    public LongStream stream() {
        return DesugarArrays.stream(this.array, this.start, this.end);
    }

    public ImmutableLongArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, length());
        if (startIndex == endIndex) {
            return EMPTY;
        }
        long[] jArr = this.array;
        int i = this.start;
        return new ImmutableLongArray(jArr, startIndex + i, i + endIndex);
    }

    public long[] toArray() {
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

    public ImmutableLongArray trimmed() {
        return isPartialView() ? new ImmutableLongArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    public ImmutableLongArray(long[] array) {
        this(array, 0, array.length);
    }

    public static ImmutableLongArray of(long e0) {
        return new ImmutableLongArray(new long[]{e0}, 0, 1);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public long[] array;
        public int count = 0;

        public Builder(int initialCapacity) {
            this.array = new long[initialCapacity];
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
        public Builder add(long value) {
            ensureRoomFor(1);
            long[] jArr = this.array;
            int i = this.count;
            jArr[i] = value;
            this.count = i + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(long[] values) {
            ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public ImmutableLongArray build() {
            int i = this.count;
            return i == 0 ? ImmutableLongArray.EMPTY : new ImmutableLongArray(this.array, 0, i);
        }

        public final void ensureRoomFor(int numberToAdd) {
            int i = this.count + numberToAdd;
            long[] jArr = this.array;
            if (i > jArr.length) {
                this.array = Arrays.copyOf(jArr, expandedCapacity(jArr.length, i));
            }
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Long> values) {
            if (values instanceof java.util.Collection) {
                addAll((java.util.Collection<Long>) values);
                return this;
            }
            Iterator<Long> it = values.iterator();
            while (it.hasNext()) {
                add(it.next().longValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(java.util.Collection<Long> values) {
            ensureRoomFor(values.size());
            for (Long l : values) {
                long[] jArr = this.array;
                int i = this.count;
                this.count = i + 1;
                jArr[i] = l.longValue();
            }
            return this;
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [j$.util.Spliterator, j$.util.Spliterator$OfLong] */
        @IgnoreJRERequirement
        @CanIgnoreReturnValue
        public Builder addAll(LongStream stream) {
            ?? Spliterator = stream.spliterator();
            long exactSizeIfKnown = Spliterator.getExactSizeIfKnown();
            if (exactSizeIfKnown > 0) {
                ensureRoomFor(Ints.saturatedCast(exactSizeIfKnown));
            }
            Spliterator.forEachRemaining(new LongConsumer() { // from class: com.google.common.primitives.ImmutableLongArray$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.LongConsumer
                public final void accept(long j) {
                    this.f$0.add(j);
                }

                public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                    return LongConsumer$CC.$default$andThen(this, longConsumer);
                }
            });
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableLongArray values) {
            ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count = values.length() + this.count;
            return this;
        }
    }

    public ImmutableLongArray(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableLongArray copyOf(java.util.Collection<Long> values) {
        return values.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(values));
    }

    public static ImmutableLongArray of(long e0, long e1) {
        return new ImmutableLongArray(new long[]{e0, e1}, 0, 2);
    }

    public static ImmutableLongArray copyOf(Iterable<Long> values) {
        if (values instanceof java.util.Collection) {
            return copyOf((java.util.Collection<Long>) values);
        }
        Builder builder = builder();
        builder.addAll(values);
        return builder.build();
    }

    public static ImmutableLongArray of(long e0, long e1, long e2) {
        return new ImmutableLongArray(new long[]{e0, e1, e2}, 0, 3);
    }

    @IgnoreJRERequirement
    public static ImmutableLongArray copyOf(LongStream stream) {
        long[] array = stream.toArray();
        return array.length == 0 ? EMPTY : new ImmutableLongArray(array, 0, array.length);
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3}, 0, 4);
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3, long e4) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3, e4}, 0, 5);
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3, long e4, long e5) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3, e4, e5}, 0, 6);
    }

    public static ImmutableLongArray of(long first, long... rest) {
        Preconditions.checkArgument(rest.length <= 2147483646, "the total number of elements must fit in an int");
        int length = rest.length + 1;
        long[] jArr = new long[length];
        jArr[0] = first;
        System.arraycopy(rest, 0, jArr, 1, rest.length);
        return new ImmutableLongArray(jArr, 0, length);
    }
}
