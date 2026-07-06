package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Cut;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.SortedLists;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Objects;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class ImmutableRangeSet<C extends Comparable> extends AbstractRangeSet<C> implements Serializable {

    @LazyInit
    public transient ImmutableRangeSet<C> complement;
    public final transient ImmutableList<Range<C>> ranges;
    public static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet<>(ImmutableList.of());
    public static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet<>(ImmutableList.of(Range.all()));

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsSet extends ImmutableSortedSet<C> {
        public final DiscreteDomain<C> domain;

        @LazyInit
        public transient Integer size;

        /* JADX INFO: renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends AbstractIterator<C> {
            public Iterator<C> elemItr = Iterators.ArrayItr.EMPTY;
            public final Iterator<Range<C>> rangeItr;

            public AnonymousClass1() {
                this.rangeItr = ImmutableRangeSet.this.ranges.iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public C computeNext() {
                while (!this.elemItr.hasNext()) {
                    if (!this.rangeItr.hasNext()) {
                        endOfData();
                        return null;
                    }
                    this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).iterator();
                }
                return this.elemItr.next();
            }
        }

        /* JADX INFO: renamed from: com.google.common.collect.ImmutableRangeSet$AsSet$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass2 extends AbstractIterator<C> {
            public Iterator<C> elemItr = Iterators.ArrayItr.EMPTY;
            public final Iterator<Range<C>> rangeItr;

            public AnonymousClass2() {
                this.rangeItr = ImmutableRangeSet.this.ranges.reverse().iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public C computeNext() {
                while (!this.elemItr.hasNext()) {
                    if (!this.rangeItr.hasNext()) {
                        endOfData();
                        return null;
                    }
                    this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).descendingIterator();
                }
                return this.elemItr.next();
            }
        }

        public AsSet(DiscreteDomain<C> domain) {
            super(NaturalOrdering.INSTANCE);
            this.domain = domain;
        }

        @J2ktIncompatible
        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Use SerializedForm");
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (o == null) {
                return false;
            }
            try {
                return ImmutableRangeSet.this.contains((Comparable) o);
            } catch (ClassCastException unused) {
                return false;
            }
        }

        @Override // com.google.common.collect.ImmutableSortedSet
        public ImmutableSortedSet<C> createDescendingSet() {
            return new DescendingImmutableSortedSet(this);
        }

        @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
        @GwtIncompatible("NavigableSet")
        public UnmodifiableIterator<C> descendingIterator() {
            return new AnonymousClass2();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSortedSet
        public int indexOf(Object target) {
            if (!contains(target)) {
                return -1;
            }
            Objects.requireNonNull(target);
            Comparable comparable = (Comparable) target;
            UnmodifiableIterator it = ImmutableRangeSet.this.ranges.iterator();
            long size = 0;
            while (it.hasNext()) {
                Range range = (Range) it.next();
                if (range.contains(comparable)) {
                    return Ints.saturatedCast(size + ((long) ContiguousSet.create(range, this.domain).indexOf(comparable)));
                }
                size += (long) ContiguousSet.create(range, this.domain).size();
            }
            throw new AssertionError("impossible");
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableRangeSet.this.ranges.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<C> iterator() {
            return new AnonymousClass1();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            Integer numValueOf = this.size;
            if (numValueOf == null) {
                UnmodifiableIterator it = ImmutableRangeSet.this.ranges.iterator();
                long size = 0;
                while (it.hasNext()) {
                    size += (long) ContiguousSet.create((Range) it.next(), this.domain).size();
                    if (size >= 2147483647L) {
                        break;
                    }
                }
                numValueOf = Integer.valueOf(Ints.saturatedCast(size));
                this.size = numValueOf;
            }
            return numValueOf.intValue();
        }

        public ImmutableSortedSet<C> subSet(Range<C> range) {
            return ImmutableRangeSet.this.subRangeSet((Range) range).asSet(this.domain);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        public Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }

        @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
        @GwtIncompatible("NavigableSet")
        public Iterator descendingIterator() {
            return new AnonymousClass2();
        }

        @Override // com.google.common.collect.ImmutableSortedSet
        public ImmutableSortedSet<C> headSetImpl(C toElement, boolean inclusive) {
            return subSet(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return new AnonymousClass1();
        }

        @Override // com.google.common.collect.ImmutableSortedSet
        public ImmutableSortedSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
            return (fromInclusive || toInclusive || Range.compareOrThrow(fromElement, toElement) != 0) ? subSet(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive))) : RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }

        @Override // com.google.common.collect.ImmutableSortedSet
        public ImmutableSortedSet<C> tailSetImpl(C fromElement, boolean inclusive) {
            return subSet(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AsSetSerializedForm<C extends Comparable> implements Serializable {
        public final DiscreteDomain<C> domain;
        public final ImmutableList<Range<C>> ranges;

        public AsSetSerializedForm(ImmutableList<Range<C>> ranges, DiscreteDomain<C> domain) {
            this.ranges = ranges;
            this.domain = domain;
        }

        public Object readResolve() {
            return new ImmutableRangeSet(this.ranges).asSet(this.domain);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder<C extends Comparable<?>> {
        public final List<Range<C>> ranges = new ArrayList();

        @CanIgnoreReturnValue
        public Builder<C> add(Range<C> range) {
            Preconditions.checkArgument(!range.isEmpty(), "range must not be empty, but was %s", range);
            this.ranges.add(range);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<C> addAll(RangeSet<C> ranges) {
            return addAll(ranges.asRanges());
        }

        public ImmutableRangeSet<C> build() {
            ImmutableList.Builder builder = new ImmutableList.Builder(this.ranges.size());
            Collections.sort(this.ranges, Range.rangeLexOrdering());
            PeekingIterator peekingIterator = Iterators.peekingIterator(this.ranges.iterator());
            while (peekingIterator.hasNext()) {
                Range rangeSpan = (Range) peekingIterator.next();
                while (peekingIterator.hasNext()) {
                    Range<C> range = (Range) peekingIterator.peek();
                    if (rangeSpan.isConnected(range)) {
                        Preconditions.checkArgument(rangeSpan.intersection(range).isEmpty(), "Overlapping ranges not permitted but found %s overlapping %s", rangeSpan, range);
                        rangeSpan = rangeSpan.span((Range) peekingIterator.next());
                    }
                }
                builder.add(rangeSpan);
            }
            ImmutableList immutableListBuild = builder.build();
            return immutableListBuild.isEmpty() ? ImmutableRangeSet.of() : (immutableListBuild.size() == 1 && ((Range) Iterators.getOnlyElement(immutableListBuild.iterator())).equals(Range.ALL)) ? ImmutableRangeSet.all() : new ImmutableRangeSet<>(immutableListBuild);
        }

        @CanIgnoreReturnValue
        public Builder<C> combine(Builder<C> builder) {
            addAll(builder.ranges);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<C> addAll(Iterable<Range<C>> ranges) {
            Iterator<Range<C>> it = ranges.iterator();
            while (it.hasNext()) {
                add(it.next());
            }
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ComplementRanges extends ImmutableList<Range<C>> {
        public final boolean positiveBoundedAbove;
        public final boolean positiveBoundedBelow;
        public final int size;

        /* JADX WARN: Multi-variable type inference failed */
        public ComplementRanges() {
            boolean zHasLowerBound = ((Range) ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
            this.positiveBoundedBelow = zHasLowerBound;
            boolean zHasUpperBound = ((Range) Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
            this.positiveBoundedAbove = zHasUpperBound;
            int size = ImmutableRangeSet.this.ranges.size();
            size = zHasLowerBound ? size : size - 1;
            this.size = zHasUpperBound ? size + 1 : size;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.List
        public Range<C> get(int i) {
            Preconditions.checkElementIndex(i, this.size);
            return Range.create(this.positiveBoundedBelow ? i == 0 ? Cut.BelowAll.INSTANCE : ((Range) ImmutableRangeSet.this.ranges.get(i - 1)).upperBound : ((Range) ImmutableRangeSet.this.ranges.get(i)).upperBound, (this.positiveBoundedAbove && i == this.size + (-1)) ? Cut.AboveAll.INSTANCE : ((Range) ImmutableRangeSet.this.ranges.get(i + (!this.positiveBoundedBelow ? 1 : 0))).lowerBound);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SerializedForm<C extends Comparable> implements Serializable {
        public final ImmutableList<Range<C>> ranges;

        public SerializedForm(ImmutableList<Range<C>> ranges) {
            this.ranges = ranges;
        }

        public Object readResolve() {
            return this.ranges.isEmpty() ? ImmutableRangeSet.of() : this.ranges.equals(ImmutableList.of(Range.all())) ? ImmutableRangeSet.all() : new ImmutableRangeSet(this.ranges);
        }
    }

    public ImmutableRangeSet(ImmutableList<Range<C>> ranges) {
        this.ranges = ranges;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder<>();
    }

    public static <C extends Comparable<?>> ImmutableRangeSet<C> copyOf(Iterable<Range<C>> ranges) {
        Builder builder = new Builder();
        builder.addAll(ranges);
        return builder.build();
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    @IgnoreJRERequirement
    public static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return CollectCollectors.toImmutableRangeSet();
    }

    public static <C extends Comparable<?>> ImmutableRangeSet<C> unionOf(Iterable<Range<C>> ranges) {
        return copyOf(TreeRangeSet.create(ranges));
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void addAll(RangeSet<C> other) {
        throw new UnsupportedOperationException();
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> domain) {
        domain.getClass();
        if (this.ranges.isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range<C> rangeCanonical = span().canonical(domain);
        if (!rangeCanonical.hasLowerBound()) {
            throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
        }
        if (!rangeCanonical.hasUpperBound()) {
            try {
                domain.maxValue();
            } catch (NoSuchElementException unused) {
                throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
            }
        }
        return new AsSet(domain);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean contains(Comparable value) {
        return super.contains(value);
    }

    public ImmutableRangeSet<C> difference(RangeSet<C> other) {
        TreeRangeSet treeRangeSetCreate = TreeRangeSet.create(this);
        treeRangeSetCreate.removeAll(other);
        return copyOf(treeRangeSetCreate);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean encloses(Range<C> otherRange) {
        int iBinarySearch = SortedLists.binarySearch(this.ranges, new ImmutableRangeMap$$ExternalSyntheticLambda1(), otherRange.lowerBound, NaturalOrdering.INSTANCE, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        return iBinarySearch != -1 && this.ranges.get(iBinarySearch).encloses(otherRange);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet other) {
        return super.enclosesAll(other);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final ImmutableList<Range<C>> intersectRanges(final Range<C> range) {
        if (this.ranges.isEmpty() || range.isEmpty()) {
            return ImmutableList.of();
        }
        if (range.encloses(span())) {
            return this.ranges;
        }
        final int iBinarySearch = range.hasLowerBound() ? SortedLists.binarySearch(this.ranges, new ImmutableRangeMap$$ExternalSyntheticLambda0(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : 0;
        final int iBinarySearch2 = (range.hasUpperBound() ? SortedLists.binarySearch(this.ranges, new ImmutableRangeMap$$ExternalSyntheticLambda1(), range.upperBound, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : this.ranges.size()) - iBinarySearch;
        return iBinarySearch2 == 0 ? ImmutableList.of() : (ImmutableList<Range<C>>) new ImmutableList<Range<C>>(this) { // from class: com.google.common.collect.ImmutableRangeSet.1
            public final /* synthetic */ ImmutableRangeSet this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return iBinarySearch2;
            }

            @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
            @J2ktIncompatible
            @GwtIncompatible
            public Object writeReplace() {
                return super.writeReplace();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.List
            public Range<C> get(int index) {
                Preconditions.checkElementIndex(index, iBinarySearch2);
                return (index == 0 || index == iBinarySearch2 + (-1)) ? ((Range) this.this$0.ranges.get(index + iBinarySearch)).intersection(range) : (Range) this.this$0.ranges.get(index + iBinarySearch);
            }
        };
    }

    public ImmutableRangeSet<C> intersection(RangeSet<C> other) {
        TreeRangeSet treeRangeSetCreate = TreeRangeSet.create(this);
        treeRangeSetCreate.removeAll(other.complement());
        return copyOf(treeRangeSetCreate);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean intersects(Range<C> otherRange) {
        int iBinarySearch = SortedLists.binarySearch(this.ranges, new ImmutableRangeMap$$ExternalSyntheticLambda1(), otherRange.lowerBound, NaturalOrdering.INSTANCE, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (iBinarySearch < this.ranges.size() && this.ranges.get(iBinarySearch).isConnected(otherRange) && !this.ranges.get(iBinarySearch).intersection(otherRange).isEmpty()) {
            return true;
        }
        if (iBinarySearch <= 0) {
            return false;
        }
        int i = iBinarySearch - 1;
        return this.ranges.get(i).isConnected(otherRange) && !this.ranges.get(i).intersection(otherRange).isEmpty();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    public boolean isPartialView() {
        return this.ranges.isPartialView();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public Range<C> rangeContaining(C value) {
        int iBinarySearch = SortedLists.binarySearch(this.ranges, new ImmutableRangeMap$$ExternalSyntheticLambda1(), new Cut.BelowValue(value), NaturalOrdering.INSTANCE, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (iBinarySearch != -1) {
            Range<C> range = this.ranges.get(iBinarySearch);
            if (range.contains(value)) {
                return range;
            }
        }
        return null;
    }

    @J2ktIncompatible
    public final void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void removeAll(RangeSet<C> other) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public Range<C> span() {
        if (this.ranges.isEmpty()) {
            throw new NoSuchElementException();
        }
        return new Range<>(this.ranges.get(0).lowerBound, this.ranges.get(r1.size() - 1).upperBound);
    }

    public ImmutableRangeSet<C> union(RangeSet<C> other) {
        return unionOf(FluentIterable.concat(asRanges(), other.asRanges()));
    }

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.ranges);
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(RangeSet<C> rangeSet) {
        rangeSet.getClass();
        if (rangeSet.isEmpty()) {
            return EMPTY;
        }
        if (rangeSet.encloses(Range.all())) {
            return ALL;
        }
        if (rangeSet instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet) rangeSet;
            if (!immutableRangeSet.ranges.isPartialView()) {
                return immutableRangeSet;
            }
        }
        return new ImmutableRangeSet<>(ImmutableList.copyOf((Collection) rangeSet.asRanges()));
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range) {
        range.getClass();
        return range.isEmpty() ? EMPTY : range.equals(Range.ALL) ? ALL : new ImmutableRangeSet<>(ImmutableList.of(range));
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void addAll(Iterable<Range<C>> other) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableSet<Range<C>> asDescendingSetOfRanges() {
        return this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse());
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableSet<Range<C>> asRanges() {
        return this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering());
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> immutableRangeSet = this.complement;
        if (immutableRangeSet != null) {
            return immutableRangeSet;
        }
        if (this.ranges.isEmpty()) {
            ImmutableRangeSet<Comparable<?>> immutableRangeSet2 = ALL;
            this.complement = immutableRangeSet2;
            return immutableRangeSet2;
        }
        if (this.ranges.size() == 1 && this.ranges.get(0).equals(Range.ALL)) {
            ImmutableRangeSet<Comparable<?>> immutableRangeSet3 = EMPTY;
            this.complement = immutableRangeSet3;
            return immutableRangeSet3;
        }
        ImmutableRangeSet<C> immutableRangeSet4 = new ImmutableRangeSet<>(new ComplementRanges(), this);
        this.complement = immutableRangeSet4;
        return immutableRangeSet4;
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable ranges) {
        return super.enclosesAll(ranges);
    }

    @Override // com.google.common.collect.AbstractRangeSet, com.google.common.collect.RangeSet
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void removeAll(Iterable<Range<C>> other) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableRangeSet<C> subRangeSet(Range<C> range) {
        if (!this.ranges.isEmpty()) {
            Range<C> rangeSpan = span();
            if (range.encloses(rangeSpan)) {
                return this;
            }
            if (range.isConnected(rangeSpan)) {
                return new ImmutableRangeSet<>(intersectRanges(range));
            }
        }
        return EMPTY;
    }

    public ImmutableRangeSet(ImmutableList<Range<C>> ranges, ImmutableRangeSet<C> complement) {
        this.ranges = ranges;
        this.complement = complement;
    }
}
