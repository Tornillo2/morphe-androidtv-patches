package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class UnmodifiableSortedMultiset<E> extends Multisets.UnmodifiableMultiset<E> implements SortedMultiset<E> {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @LazyInit
    public transient UnmodifiableSortedMultiset<E> descendingMultiset;

    public UnmodifiableSortedMultiset(SortedMultiset<E> delegate) {
        super(delegate);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return ((SortedMultiset) this.delegate).comparator();
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Multiset delegate() {
        return (SortedMultiset) this.delegate;
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset = this.descendingMultiset;
        if (unmodifiableSortedMultiset != null) {
            return unmodifiableSortedMultiset;
        }
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset2 = new UnmodifiableSortedMultiset<>((Multiset) ((SortedMultiset) this.delegate).descendingMultiset());
        unmodifiableSortedMultiset2.descendingMultiset = this;
        this.descendingMultiset = unmodifiableSortedMultiset2;
        return unmodifiableSortedMultiset2;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return ((SortedMultiset) this.delegate).firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@ParametricNullness E upperBound, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(((SortedMultiset) this.delegate).headMultiset(upperBound, boundType));
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return ((SortedMultiset) this.delegate).lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(@ParametricNullness E lowerBound, BoundType lowerBoundType, @ParametricNullness E upperBound, BoundType upperBoundType) {
        return Multisets.unmodifiableSortedMultiset(((SortedMultiset) this.delegate).subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType));
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@ParametricNullness E lowerBound, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(((SortedMultiset) this.delegate).tailMultiset(lowerBound, boundType));
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset
    public NavigableSet<E> createElementSet() {
        return Sets.unmodifiableNavigableSet(((SortedMultiset) this.delegate).elementSet());
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public SortedMultiset<E> delegate() {
        return (SortedMultiset) this.delegate;
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public SortedSet elementSet() {
        return (NavigableSet) super.elementSet();
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Object delegate() {
        return (SortedMultiset) this.delegate;
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Collection delegate() {
        return (SortedMultiset) this.delegate;
    }
}
