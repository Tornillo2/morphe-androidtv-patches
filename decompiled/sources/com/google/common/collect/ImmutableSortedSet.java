package com.google.common.collect;

import android.R;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.SortedSet;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedSet<E> extends ImmutableSet<E> implements NavigableSet<E>, SortedIterable<E>, SortedSet {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 912559;
    public final transient Comparator<? super E> comparator;

    @GwtIncompatible
    @LazyInit
    public transient ImmutableSortedSet<E> descendingSet;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        public final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> comparator) {
            super(4);
            comparator.getClass();
            this.comparator = comparator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.ArrayBasedBuilder add(Object element) {
            super.add(element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder addAll(Iterable elements) {
            super.addAll(elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        @CanIgnoreReturnValue
        public ImmutableSet.Builder combine(ImmutableSet.Builder builder) {
            super.combine(builder);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder add(Object element) {
            super.add(element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableSet.Builder addAll(Iterable elements) {
            super.addAll(elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        @CanIgnoreReturnValue
        public Builder<E> combine(ImmutableSet.Builder<E> builder) {
            super.combine((ImmutableSet.Builder) builder);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableSet.Builder add(Object element) {
            super.add(element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            super.addAll((Iterable) elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> immutableSortedSetConstruct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = ((RegularImmutableSortedSet) immutableSortedSetConstruct).elements.size();
            this.forceCopy = true;
            return immutableSortedSetConstruct;
        }

        public Builder(Comparator<? super E> comparator, int expectedKeys) {
            super(expectedKeys);
            comparator.getClass();
            this.comparator = comparator;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            super.add((Object) element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder addAll(Iterator elements) {
            super.addAll(elements);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder add(Object[] elements) {
            super.add(elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableSet.Builder addAll(Iterator elements) {
            super.addAll(elements);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableSet.Builder add(Object[] elements) {
            super.add(elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll((Iterator) elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... elements) {
            super.add((Object[]) elements);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class SerializedForm<E> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Comparator<? super E> comparator;
        public final Object[] elements;

        public SerializedForm(Comparator<? super E> comparator, Object[] elements) {
            this.comparator = comparator;
            this.elements = elements;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Object readResolve() {
            Builder builder = new Builder(this.comparator);
            builder.add(this.elements);
            return builder.build();
        }
    }

    public ImmutableSortedSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @DoNotCall("Use naturalOrder")
    @Deprecated
    public static <E> Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Use naturalOrder (which does not accept an expected size)")
    @Deprecated
    public static <E> Builder<E> builderWithExpectedSize(int expectedSize) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator, int i, E... eArr) {
        if (i == 0) {
            return emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull(eArr, i);
        Arrays.sort(eArr, 0, i, comparator);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            R.color colorVar = (Object) eArr[i3];
            if (comparator.compare(colorVar, (Object) eArr[i2 - 1]) != 0) {
                eArr[i2] = colorVar;
                i2++;
            }
        }
        Arrays.fill(eArr, i2, i, (Object) null);
        if (i2 < eArr.length / 2) {
            eArr = (E[]) Arrays.copyOf(eArr, i2);
        }
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i2), comparator);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> elements) {
        return copyOf(NaturalOrdering.INSTANCE, elements);
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(java.util.SortedSet<E> sortedSet) {
        Comparator comparator = SortedIterables.comparator(sortedSet);
        ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) sortedSet);
        return immutableListCopyOf.isEmpty() ? emptySet(comparator) : new RegularImmutableSortedSet(immutableListCopyOf, comparator);
    }

    public static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator) {
        return NaturalOrdering.INSTANCE.equals(comparator) ? (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.NATURAL_EMPTY_SET : new RegularImmutableSortedSet<>(RegularImmutableList.EMPTY, comparator);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(NaturalOrdering.INSTANCE);
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream unused) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Collections.reverseOrder());
    }

    @IgnoreJRERequirement
    @DoNotCall("Use toImmutableSortedSet")
    @Deprecated
    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        throw new UnsupportedOperationException();
    }

    @IgnoreJRERequirement
    public static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(Comparator<? super E> comparator) {
        return CollectCollectors.toImmutableSortedSet(comparator);
    }

    public E ceiling(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, true), null);
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @GwtIncompatible
    public abstract ImmutableSortedSet<E> createDescendingSet();

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public abstract UnmodifiableIterator<E> descendingIterator();

    public E first() {
        return iterator().next();
    }

    public E floor(E e) {
        return (E) Iterators.getNext(headSet((Object) e, true).descendingIterator(), null);
    }

    public abstract ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive);

    @GwtIncompatible
    public E higher(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, false), null);
    }

    public abstract int indexOf(Object target);

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    public E last() {
        return descendingIterator().next();
    }

    @GwtIncompatible
    public E lower(E e) {
        return (E) Iterators.getNext(headSet((Object) e, false).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    public abstract ImmutableSortedSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

    public abstract ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive);

    public int unsafeCompare(Object a, Object b) {
        return this.comparator.compare(a, b);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray(ImmutableCollection.EMPTY_ARRAY));
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1) {
        return new RegularImmutableSortedSet(ImmutableList.of(e1), NaturalOrdering.INSTANCE);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> immutableSortedSetCreateDescendingSet = createDescendingSet();
        this.descendingSet = immutableSortedSetCreateDescendingSet;
        immutableSortedSetCreateDescendingSet.descendingSet = this;
        return immutableSortedSetCreateDescendingSet;
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> elements) {
        return copyOf((Comparator) NaturalOrdering.INSTANCE, (Iterable) elements);
    }

    public static int unsafeCompare(Comparator<?> comparator, Object a, Object b) {
        return comparator.compare(a, b);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> headSet(E toElement) {
        return headSet((Object) toElement, false);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> subSet(E fromElement, E toElement) {
        return subSet((Object) fromElement, true, (Object) toElement, false);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> tailSet(E fromElement) {
        return tailSet((Object) fromElement, true);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> headSet(E toElement, boolean inclusive) {
        toElement.getClass();
        return headSetImpl(toElement, inclusive);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        fromElement.getClass();
        toElement.getClass();
        Preconditions.checkArgument(this.comparator.compare(fromElement, toElement) <= 0);
        return subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> tailSet(E fromElement, boolean inclusive) {
        fromElement.getClass();
        return tailSetImpl(fromElement, inclusive);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> elements) {
        return copyOf(NaturalOrdering.INSTANCE, elements);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;TE;[TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5, Comparable e6, Comparable... remaining) {
        int length = remaining.length + 6;
        Comparable[] comparableArr = new Comparable[length];
        comparableArr[0] = e1;
        comparableArr[1] = e2;
        comparableArr[2] = e3;
        comparableArr[3] = e4;
        comparableArr[4] = e5;
        comparableArr[5] = e6;
        System.arraycopy(remaining, 0, comparableArr, 6, remaining.length);
        return construct(NaturalOrdering.INSTANCE, length, comparableArr);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>([TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet copyOf(Comparable[] elements) {
        return construct(NaturalOrdering.INSTANCE, elements.length, (Comparable[]) elements.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> elements) {
        Builder builder = new Builder(comparator);
        builder.addAll((Iterator) elements);
        return builder.build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Collection<? extends E> elements) {
        return copyOf((Comparator) comparator, (Iterable) elements);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> elements) {
        comparator.getClass();
        if (SortedIterables.hasSameComparator(comparator, elements) && (elements instanceof ImmutableSortedSet)) {
            ImmutableSortedSet<E> immutableSortedSet = (ImmutableSortedSet) elements;
            if (!immutableSortedSet.isPartialView()) {
                return immutableSortedSet;
            }
        }
        Object[] array = Iterables.toArray(elements);
        return construct(comparator, array.length, array);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1, Comparable e2) {
        return construct(NaturalOrdering.INSTANCE, 2, e1, e2);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3) {
        return construct(NaturalOrdering.INSTANCE, 3, e1, e2, e3);
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <Z> ImmutableSortedSet<Z> copyOf(Z[] elements) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4) {
        return construct(NaturalOrdering.INSTANCE, 4, e1, e2, e3, e4);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5) {
        return construct(NaturalOrdering.INSTANCE, 5, e1, e2, e3, e4, e5);
    }

    @DoNotCall("Pass a parameter of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1, E e2) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall("Pass parameters of type Comparable")
    @Deprecated
    public static <E> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E... remaining) {
        throw new UnsupportedOperationException();
    }
}
