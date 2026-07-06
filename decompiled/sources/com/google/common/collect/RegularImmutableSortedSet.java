package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    public static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = new RegularImmutableSortedSet<>(ImmutableList.of(), NaturalOrdering.INSTANCE);

    @VisibleForTesting
    public final transient ImmutableList<E> elements;

    public RegularImmutableSortedSet(ImmutableList<E> elements, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = elements;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return this.elements;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E ceiling(E element) {
        int iTailIndex = tailIndex(element, true);
        if (iTailIndex == this.elements.size()) {
            return null;
        }
        return this.elements.get(iTailIndex);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object o) {
        if (o != null) {
            try {
                if (unsafeBinarySearch(o) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> targets) {
        if (targets instanceof Multiset) {
            targets = ((Multiset) targets).elementSet();
        }
        if (!SortedIterables.hasSameComparator(this.comparator, targets) || targets.size() <= 1) {
            return super.containsAll(targets);
        }
        UnmodifiableIterator<E> it = this.elements.iterator();
        Iterator<?> it2 = targets.iterator();
        if (!it.hasNext()) {
            return false;
        }
        Object next = it2.next();
        E next2 = it.next();
        while (true) {
            try {
                int iCompare = this.comparator.compare(next2, next);
                if (iCompare < 0) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    next2 = it.next();
                } else if (iCompare == 0) {
                    if (!it2.hasNext()) {
                        return true;
                    }
                    next = it2.next();
                } else if (iCompare > 0) {
                    return false;
                }
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        return this.elements.copyIntoArray(dst, offset);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> createDescendingSet() {
        Comparator comparatorReverseOrder = Collections.reverseOrder(this.comparator);
        return isEmpty() ? ImmutableSortedSet.emptySet(comparatorReverseOrder) : new RegularImmutableSortedSet(this.elements.reverse(), comparatorReverseOrder);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set set = (Set) object;
        if (this.elements.size() != set.size()) {
            return false;
        }
        if (isEmpty()) {
            return true;
        }
        if (!SortedIterables.hasSameComparator(this.comparator, set)) {
            return containsAll(set);
        }
        Iterator<E> it = set.iterator();
        try {
            UnmodifiableIterator<E> it2 = this.elements.iterator();
            while (it2.hasNext()) {
                E next = it2.next();
                E next2 = it.next();
                if (next2 == null || this.comparator.compare(next, next2) != 0) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NoSuchElementException unused) {
            return false;
        }
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(0);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E floor(E element) {
        int iHeadIndex = headIndex(element, true) - 1;
        if (iHeadIndex == -1) {
            return null;
        }
        return this.elements.get(iHeadIndex);
    }

    public RegularImmutableSortedSet<E> getSubSet(int newFromIndex, int newToIndex) {
        return (newFromIndex == 0 && newToIndex == this.elements.size()) ? this : newFromIndex < newToIndex ? new RegularImmutableSortedSet<>(this.elements.subList(newFromIndex, newToIndex), this.comparator) : ImmutableSortedSet.emptySet(this.comparator);
    }

    public int headIndex(E toElement, boolean inclusive) {
        ImmutableList<E> immutableList = this.elements;
        toElement.getClass();
        int iBinarySearch = Collections.binarySearch(immutableList, toElement, this.comparator);
        return iBinarySearch >= 0 ? inclusive ? iBinarySearch + 1 : iBinarySearch : ~iBinarySearch;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive) {
        return getSubSet(0, headIndex(toElement, inclusive));
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E higher(E element) {
        int iTailIndex = tailIndex(element, false);
        if (iTailIndex == this.elements.size()) {
            return null;
        }
        return this.elements.get(iTailIndex);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public int indexOf(Object target) {
        int iBinarySearch;
        if (target == null) {
            return -1;
        }
        try {
            iBinarySearch = Collections.binarySearch(this.elements, target, this.comparator);
        } catch (ClassCastException unused) {
        }
        if (iBinarySearch >= 0) {
            return iBinarySearch;
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object[] internalArray() {
        return this.elements.internalArray();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayEnd() {
        return this.elements.internalArrayEnd();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayStart() {
        return this.elements.internalArrayStart();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.elements.isPartialView();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(r0.size() - 1);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E lower(E element) {
        int iHeadIndex = headIndex(element, false) - 1;
        if (iHeadIndex == -1) {
            return null;
        }
        return this.elements.get(iHeadIndex);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.elements.size();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return tailSetImpl(fromElement, fromInclusive).headSetImpl(toElement, toInclusive);
    }

    public int tailIndex(E fromElement, boolean inclusive) {
        ImmutableList<E> immutableList = this.elements;
        fromElement.getClass();
        int iBinarySearch = Collections.binarySearch(immutableList, fromElement, this.comparator);
        return iBinarySearch >= 0 ? inclusive ? iBinarySearch : iBinarySearch + 1 : ~iBinarySearch;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive) {
        return getSubSet(tailIndex(fromElement, inclusive), this.elements.size());
    }

    public final int unsafeBinarySearch(Object key) throws ClassCastException {
        return Collections.binarySearch(this.elements, key, this.comparator);
    }

    public Comparator<Object> unsafeComparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return super.writeReplace();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public Iterator iterator() {
        return this.elements.iterator();
    }
}
