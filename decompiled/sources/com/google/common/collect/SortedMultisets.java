package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class SortedMultisets {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ElementSet<E> extends Multisets.ElementSet<E> implements SortedSet<E> {

        @Weak
        public final SortedMultiset<E> multiset;

        public ElementSet(SortedMultiset<E> multiset) {
            this.multiset = multiset;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return this.multiset.comparator();
        }

        @Override // java.util.SortedSet
        @ParametricNullness
        public E first() {
            return (E) SortedMultisets.getElementOrThrow(this.multiset.firstEntry());
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return this.multiset.headMultiset(toElement, BoundType.OPEN).elementSet();
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new Multisets.AnonymousClass5(this.multiset.entrySet().iterator());
        }

        @Override // java.util.SortedSet
        @ParametricNullness
        public E last() {
            return (E) SortedMultisets.getElementOrThrow(this.multiset.lastEntry());
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        public Multiset multiset() {
            return this.multiset;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return this.multiset.subMultiset(fromElement, BoundType.CLOSED, toElement, BoundType.OPEN).elementSet();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return this.multiset.tailMultiset(fromElement, BoundType.CLOSED).elementSet();
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        public final SortedMultiset<E> multiset() {
            return this.multiset;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class NavigableElementSet<E> extends ElementSet<E> implements NavigableSet<E> {
        public NavigableElementSet(SortedMultiset<E> multiset) {
            super(multiset);
        }

        @Override // java.util.NavigableSet
        public E ceiling(@ParametricNullness E e) {
            return (E) SortedMultisets.getElementOrNull(this.multiset.tailMultiset(e, BoundType.CLOSED).firstEntry());
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return ((ElementSet) descendingSet()).iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new NavigableElementSet(this.multiset.descendingMultiset());
        }

        @Override // java.util.NavigableSet
        public E floor(@ParametricNullness E e) {
            return (E) SortedMultisets.getElementOrNull(this.multiset.headMultiset(e, BoundType.CLOSED).lastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return new NavigableElementSet(this.multiset.headMultiset(toElement, BoundType.forBoolean(inclusive)));
        }

        @Override // java.util.NavigableSet
        public E higher(@ParametricNullness E e) {
            return (E) SortedMultisets.getElementOrNull(this.multiset.tailMultiset(e, BoundType.OPEN).firstEntry());
        }

        @Override // java.util.NavigableSet
        public E lower(@ParametricNullness E e) {
            return (E) SortedMultisets.getElementOrNull(this.multiset.headMultiset(e, BoundType.OPEN).lastEntry());
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) SortedMultisets.getElementOrNull(this.multiset.pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) SortedMultisets.getElementOrNull(this.multiset.pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return new NavigableElementSet(this.multiset.subMultiset(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return new NavigableElementSet(this.multiset.tailMultiset(fromElement, BoundType.forBoolean(inclusive)));
        }
    }

    public static <E> E getElementOrNull(Multiset.Entry<E> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getElement();
    }

    public static <E> E getElementOrThrow(Multiset.Entry<E> entry) {
        if (entry != null) {
            return entry.getElement();
        }
        throw new NoSuchElementException();
    }
}
