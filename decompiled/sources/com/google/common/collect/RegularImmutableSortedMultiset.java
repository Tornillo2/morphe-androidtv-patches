package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    public final transient long[] cumulativeCounts;

    @VisibleForTesting
    public final transient RegularImmutableSortedSet<E> elementSet;
    public final transient int length;
    public final transient int offset;
    public static final long[] zeroCumulativeCounts = {0};
    public static final ImmutableSortedMultiset<?> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(NaturalOrdering.INSTANCE);

    public RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> elementSet, long[] cumulativeCounts, int offset, int length) {
        this.elementSet = elementSet;
        this.cumulativeCounts = cumulativeCounts;
        this.offset = offset;
        this.length = length;
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        int iIndexOf = this.elementSet.indexOf(element);
        if (iIndexOf >= 0) {
            return getCount(iIndexOf);
        }
        return 0;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(0);
    }

    public final int getCount(int index) {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return (int) (jArr[(i + index) + 1] - jArr[i + index]);
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        return new Multisets.ImmutableEntry(this.elementSet.elements.get(index), getCount(index));
    }

    public ImmutableSortedMultiset<E> getSubMultiset(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, this.length);
        return from == to ? ImmutableSortedMultiset.emptyMultiset(comparator()) : (from == 0 && to == this.length) ? this : new RegularImmutableSortedMultiset(this.elementSet.getSubSet(from, to), this.cumulativeCounts, this.offset + from, to - from);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(this.length - 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return Ints.saturatedCast(jArr[this.length + i] - jArr[i]);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    public Object writeReplace() {
        return new ImmutableSortedMultiset.SerializedForm(this);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        boundType.getClass();
        return getSubMultiset(0, regularImmutableSortedSet.headIndex(upperBound, boundType == BoundType.CLOSED));
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        boundType.getClass();
        return getSubMultiset(regularImmutableSortedSet.tailIndex(lowerBound, boundType == BoundType.CLOSED), this.length);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public NavigableSet elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public Set elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public SortedSet elementSet() {
        return this.elementSet;
    }

    public RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = zeroCumulativeCounts;
        this.offset = 0;
        this.length = 0;
    }
}
