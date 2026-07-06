package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableSortedSet;
import com.google.errorprone.annotations.DoNotCall;
import j$.util.Objects;
import java.lang.Comparable;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    public final DiscreteDomain<C> domain;

    public ContiguousSet(DiscreteDomain<C> domain) {
        super(NaturalOrdering.INSTANCE);
        this.domain = domain;
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    public static ContiguousSet<Integer> closed(int lower, int upper) {
        return create(Range.closed(Integer.valueOf(lower), Integer.valueOf(upper)), DiscreteDomain.IntegerDomain.INSTANCE);
    }

    public static ContiguousSet<Integer> closedOpen(int lower, int upper) {
        return create(Range.closedOpen(Integer.valueOf(lower), Integer.valueOf(upper)), DiscreteDomain.IntegerDomain.INSTANCE);
    }

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> domain) {
        range.getClass();
        domain.getClass();
        try {
            Range<C> rangeIntersection = !range.hasLowerBound() ? range.intersection(Range.atLeast(domain.minValue())) : range;
            if (!range.hasUpperBound()) {
                rangeIntersection = rangeIntersection.intersection(Range.atMost(domain.maxValue()));
            }
            if (!rangeIntersection.isEmpty()) {
                Comparable comparableLeastValueAbove = range.lowerBound.leastValueAbove(domain);
                Objects.requireNonNull(comparableLeastValueAbove);
                Comparable comparableGreatestValueBelow = range.upperBound.greatestValueBelow(domain);
                Objects.requireNonNull(comparableGreatestValueBelow);
                if (comparableLeastValueAbove.compareTo(comparableGreatestValueBelow) <= 0) {
                    return new RegularContiguousSet(rangeIntersection, domain);
                }
            }
            return new EmptyContiguousSet(domain);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> headSetImpl(C toElement, boolean inclusive);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> other);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType lowerBoundType, BoundType upperBoundType);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> tailSetImpl(C fromElement, boolean inclusive);

    @Override // java.util.AbstractCollection
    public String toString() {
        return range().toString();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return super.writeReplace();
    }

    public static ContiguousSet<Long> closed(long lower, long upper) {
        return create(Range.closed(Long.valueOf(lower), Long.valueOf(upper)), DiscreteDomain.LongDomain.INSTANCE);
    }

    public static ContiguousSet<Long> closedOpen(long lower, long upper) {
        return create(Range.closedOpen(Long.valueOf(lower), Long.valueOf(upper)), DiscreteDomain.LongDomain.INSTANCE);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> headSet(C toElement) {
        toElement.getClass();
        return headSetImpl((Comparable) toElement, false);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> subSet(C fromElement, C toElement) {
        fromElement.getClass();
        toElement.getClass();
        Preconditions.checkArgument(comparator().compare(fromElement, toElement) <= 0);
        return subSetImpl((Comparable) fromElement, true, (Comparable) toElement, false);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> tailSet(C fromElement) {
        fromElement.getClass();
        return tailSetImpl((Comparable) fromElement, true);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> headSet(C toElement, boolean inclusive) {
        toElement.getClass();
        return headSetImpl((Comparable) toElement, inclusive);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> tailSet(C fromElement, boolean inclusive) {
        fromElement.getClass();
        return tailSetImpl((Comparable) fromElement, inclusive);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> subSet(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        fromElement.getClass();
        toElement.getClass();
        Preconditions.checkArgument(comparator().compare(fromElement, toElement) <= 0);
        return subSetImpl((Comparable) fromElement, fromInclusive, (Comparable) toElement, toInclusive);
    }
}
