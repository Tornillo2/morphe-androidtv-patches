package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet() {
            super(ForwardingNavigableSet.this);
        }
    }

    @Override // java.util.NavigableSet
    public E ceiling(@ParametricNullness E e) {
        return delegate().ceiling(e);
    }

    @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract NavigableSet<E> delegate();

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    @Override // java.util.NavigableSet
    public E floor(@ParametricNullness E e) {
        return delegate().floor(e);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
        return delegate().headSet(toElement, inclusive);
    }

    @Override // java.util.NavigableSet
    public E higher(@ParametricNullness E e) {
        return delegate().higher(e);
    }

    @Override // java.util.NavigableSet
    public E lower(@ParametricNullness E e) {
        return delegate().lower(e);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return delegate().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return delegate().pollLast();
    }

    public E standardCeiling(@ParametricNullness E e) {
        return (E) Iterators.getNext(tailSet(e, true).iterator(), null);
    }

    @ParametricNullness
    public E standardFirst() {
        return iterator().next();
    }

    public E standardFloor(@ParametricNullness E e) {
        return (E) Iterators.getNext(headSet(e, true).descendingIterator(), null);
    }

    public SortedSet<E> standardHeadSet(@ParametricNullness E toElement) {
        return headSet(toElement, false);
    }

    public E standardHigher(@ParametricNullness E e) {
        return (E) Iterators.getNext(tailSet(e, false).iterator(), null);
    }

    @ParametricNullness
    public E standardLast() {
        return descendingIterator().next();
    }

    public E standardLower(@ParametricNullness E e) {
        return (E) Iterators.getNext(headSet(e, false).descendingIterator(), null);
    }

    public E standardPollFirst() {
        return (E) Iterators.pollNext(iterator());
    }

    public E standardPollLast() {
        return (E) Iterators.pollNext(descendingIterator());
    }

    public NavigableSet<E> standardSubSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
        return tailSet(fromElement, fromInclusive).headSet(toElement, toInclusive);
    }

    public SortedSet<E> standardTailSet(@ParametricNullness E fromElement) {
        return tailSet(fromElement, true);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
        return delegate().subSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
        return delegate().tailSet(fromElement, inclusive);
    }

    @Override // com.google.common.collect.ForwardingSortedSet
    public SortedSet<E> standardSubSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
        return subSet(fromElement, true, toElement, false);
    }
}
