package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class StandardDescendingMultiset extends DescendingMultiset<E> {
        public StandardDescendingMultiset() {
        }

        @Override // com.google.common.collect.DescendingMultiset
        public SortedMultiset<E> forwardMultiset() {
            return ForwardingSortedMultiset.this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardElementSet extends SortedMultisets.NavigableElementSet<E> {
        public StandardElementSet() {
            super(ForwardingSortedMultiset.this);
        }
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract SortedMultiset<E> delegate();

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        return delegate().descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return delegate().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@ParametricNullness E upperBound, BoundType boundType) {
        return delegate().headMultiset(upperBound, boundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return delegate().lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    public Multiset.Entry<E> standardFirstEntry() {
        Iterator<Multiset.Entry<E>> it = entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it.next();
        return new Multisets.ImmutableEntry(next.getElement(), next.getCount());
    }

    public Multiset.Entry<E> standardLastEntry() {
        Iterator<Multiset.Entry<E>> it = descendingMultiset().entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it.next();
        return new Multisets.ImmutableEntry(next.getElement(), next.getCount());
    }

    public Multiset.Entry<E> standardPollFirstEntry() {
        Iterator<Multiset.Entry<E>> it = entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it.next();
        Multisets.ImmutableEntry immutableEntry = new Multisets.ImmutableEntry(next.getElement(), next.getCount());
        it.remove();
        return immutableEntry;
    }

    public Multiset.Entry<E> standardPollLastEntry() {
        Iterator<Multiset.Entry<E>> it = descendingMultiset().entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it.next();
        Multisets.ImmutableEntry immutableEntry = new Multisets.ImmutableEntry(next.getElement(), next.getCount());
        it.remove();
        return immutableEntry;
    }

    public SortedMultiset<E> standardSubMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
        return tailMultiset(lowerBound, lowerBoundType).headMultiset(upperBound, upperBoundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
        return delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@ParametricNullness E lowerBound, BoundType boundType) {
        return delegate().tailMultiset(lowerBound, boundType);
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public NavigableSet<E> elementSet() {
        return delegate().elementSet();
    }
}
