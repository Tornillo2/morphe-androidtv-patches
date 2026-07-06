package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Cut;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.InlineMe;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"C"})
@GwtCompatible
public final class Range<C extends Comparable> extends RangeGwtSerializationDependencies implements Predicate<C>, Serializable {
    public static final Range<Comparable> ALL = new Range<>(Cut.BelowAll.INSTANCE, Cut.AboveAll.INSTANCE);

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final Cut<C> lowerBound;
    public final Cut<C> upperBound;

    /* JADX INFO: renamed from: com.google.common.collect.Range$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RangeLexOrdering extends Ordering<Range<?>> implements Serializable {
        public static final Ordering<?> INSTANCE = new RangeLexOrdering();

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Range<?> left, Range<?> right) {
            return ComparisonChain.ACTIVE.compare(left.lowerBound, right.lowerBound).compare(left.upperBound, right.upperBound).result();
        }
    }

    public Range(Cut<C> lowerBound, Cut<C> upperBound) {
        lowerBound.getClass();
        this.lowerBound = lowerBound;
        upperBound.getClass();
        this.upperBound = upperBound;
        if (lowerBound.compareTo((Cut) upperBound) > 0 || lowerBound == Cut.AboveAll.INSTANCE || upperBound == Cut.BelowAll.INSTANCE) {
            throw new IllegalArgumentException("Invalid range: " + toString(lowerBound, upperBound));
        }
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return (Range<C>) ALL;
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C endpoint) {
        return new Range<>(new Cut.BelowValue(endpoint), Cut.AboveAll.INSTANCE);
    }

    public static <C extends Comparable<?>> Range<C> atMost(C endpoint) {
        return new Range<>(Cut.BelowAll.INSTANCE, new Cut.AboveValue(endpoint));
    }

    public static <C extends Comparable<?>> Range<C> closed(C lower, C upper) {
        return new Range<>(new Cut.BelowValue(lower), new Cut.AboveValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C lower, C upper) {
        return new Range<>(new Cut.BelowValue(lower), new Cut.BelowValue(upper));
    }

    public static int compareOrThrow(Comparable left, Comparable right) {
        return left.compareTo(right);
    }

    public static <C extends Comparable<?>> Range<C> create(Cut<C> lowerBound, Cut<C> upperBound) {
        return new Range<>(lowerBound, upperBound);
    }

    public static <C extends Comparable<?>> Range<C> downTo(C endpoint, BoundType boundType) {
        int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
        if (i == 1) {
            return greaterThan(endpoint);
        }
        if (i == 2) {
            return atLeast(endpoint);
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> values) {
        values.getClass();
        if (values instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) values;
            Comparator comparator = sortedSet.comparator();
            if (NaturalOrdering.INSTANCE.equals(comparator) || comparator == null) {
                return closed((Comparable) sortedSet.first(), (Comparable) sortedSet.last());
            }
        }
        Iterator<C> it = values.iterator();
        C next = it.next();
        next.getClass();
        Comparable comparable = next;
        while (it.hasNext()) {
            C next2 = it.next();
            next2.getClass();
            NaturalOrdering naturalOrdering = NaturalOrdering.INSTANCE;
            next = (Comparable) naturalOrdering.min(next, next2);
            comparable = (Comparable) naturalOrdering.max(comparable, next2);
        }
        return closed(next, comparable);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C endpoint) {
        return new Range<>(new Cut.AboveValue(endpoint), Cut.AboveAll.INSTANCE);
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C endpoint) {
        return new Range<>(Cut.BelowAll.INSTANCE, new Cut.BelowValue(endpoint));
    }

    public static <C extends Comparable<?>> Range<C> open(C lower, C upper) {
        return new Range<>(new Cut.AboveValue(lower), new Cut.BelowValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C lower, C upper) {
        return new Range<>(new Cut.AboveValue(lower), new Cut.AboveValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> range(C lower, BoundType lowerType, C upper, BoundType upperType) {
        lowerType.getClass();
        upperType.getClass();
        BoundType boundType = BoundType.OPEN;
        return new Range<>(lowerType == boundType ? new Cut.AboveValue(lower) : new Cut.BelowValue(lower), upperType == boundType ? new Cut.BelowValue(upper) : new Cut.AboveValue(upper));
    }

    public static <C extends Comparable<?>> Ordering<Range<C>> rangeLexOrdering() {
        return (Ordering<Range<C>>) RangeLexOrdering.INSTANCE;
    }

    public static <C extends Comparable<?>> Range<C> singleton(C value) {
        return closed(value, value);
    }

    public static <C extends Comparable<?>> Range<C> upTo(C endpoint, BoundType boundType) {
        int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
        if (i == 1) {
            return lessThan(endpoint);
        }
        if (i == 2) {
            return atMost(endpoint);
        }
        throw new AssertionError();
    }

    @Override // com.google.common.base.Predicate
    @InlineMe(replacement = "this.contains(input)")
    @Deprecated
    public boolean apply(Object input) {
        return contains((Comparable) input);
    }

    public Range<C> canonical(DiscreteDomain<C> domain) {
        domain.getClass();
        Cut<C> cutCanonical = this.lowerBound.canonical(domain);
        Cut<C> cutCanonical2 = this.upperBound.canonical(domain);
        return (cutCanonical == this.lowerBound && cutCanonical2 == this.upperBound) ? this : new Range<>(cutCanonical, cutCanonical2);
    }

    public boolean contains(C value) {
        value.getClass();
        return this.lowerBound.isLessThan(value) && !this.upperBound.isLessThan(value);
    }

    public boolean containsAll(Iterable<? extends C> values) {
        if (Iterables.isEmpty(values)) {
            return true;
        }
        if (values instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) values;
            Comparator comparator = sortedSet.comparator();
            if (NaturalOrdering.INSTANCE.equals(comparator) || comparator == null) {
                return contains((Comparable) sortedSet.first()) && contains((Comparable) sortedSet.last());
            }
        }
        Iterator<? extends C> it = values.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean encloses(Range<C> other) {
        return this.lowerBound.compareTo((Cut) other.lowerBound) <= 0 && this.upperBound.compareTo((Cut) other.upperBound) >= 0;
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(Object object) {
        if (object instanceof Range) {
            Range range = (Range) object;
            if (this.lowerBound.equals(range.lowerBound) && this.upperBound.equals(range.upperBound)) {
                return true;
            }
        }
        return false;
    }

    public Range<C> gap(Range<C> otherRange) {
        if (this.lowerBound.compareTo((Cut) otherRange.upperBound) >= 0 || otherRange.lowerBound.compareTo((Cut) this.upperBound) >= 0) {
            boolean z = this.lowerBound.compareTo((Cut) otherRange.lowerBound) < 0;
            Range<C> range = z ? this : otherRange;
            if (!z) {
                otherRange = this;
            }
            return new Range<>(range.upperBound, otherRange.lowerBound);
        }
        throw new IllegalArgumentException("Ranges have a nonempty intersection: " + this + ", " + otherRange);
    }

    public boolean hasLowerBound() {
        return this.lowerBound != Cut.BelowAll.INSTANCE;
    }

    public boolean hasUpperBound() {
        return this.upperBound != Cut.AboveAll.INSTANCE;
    }

    public int hashCode() {
        return this.upperBound.hashCode() + (this.lowerBound.hashCode() * 31);
    }

    public Range<C> intersection(Range<C> connectedRange) {
        int iCompareTo = this.lowerBound.compareTo((Cut) connectedRange.lowerBound);
        int iCompareTo2 = this.upperBound.compareTo((Cut) connectedRange.upperBound);
        if (iCompareTo >= 0 && iCompareTo2 <= 0) {
            return this;
        }
        if (iCompareTo <= 0 && iCompareTo2 >= 0) {
            return connectedRange;
        }
        Cut<C> cut = iCompareTo >= 0 ? this.lowerBound : connectedRange.lowerBound;
        Cut<C> cut2 = iCompareTo2 <= 0 ? this.upperBound : connectedRange.upperBound;
        Preconditions.checkArgument(cut.compareTo((Cut) cut2) <= 0, "intersection is undefined for disconnected ranges %s and %s", this, connectedRange);
        return new Range<>(cut, cut2);
    }

    public boolean isConnected(Range<C> other) {
        return this.lowerBound.compareTo((Cut) other.upperBound) <= 0 && other.lowerBound.compareTo((Cut) this.upperBound) <= 0;
    }

    public boolean isEmpty() {
        return this.lowerBound.equals(this.upperBound);
    }

    public Cut<C> lowerBound() {
        return this.lowerBound;
    }

    public BoundType lowerBoundType() {
        return this.lowerBound.typeAsLowerBound();
    }

    public C lowerEndpoint() {
        return (C) this.lowerBound.endpoint();
    }

    public Object readResolve() {
        Range<Comparable> range = ALL;
        return equals(range) ? range : this;
    }

    public Range<C> span(Range<C> other) {
        int iCompareTo = this.lowerBound.compareTo((Cut) other.lowerBound);
        int iCompareTo2 = this.upperBound.compareTo((Cut) other.upperBound);
        if (iCompareTo <= 0 && iCompareTo2 >= 0) {
            return this;
        }
        if (iCompareTo < 0 || iCompareTo2 > 0) {
            return new Range<>(iCompareTo <= 0 ? this.lowerBound : other.lowerBound, iCompareTo2 >= 0 ? this.upperBound : other.upperBound);
        }
        return other;
    }

    public String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    public Cut<C> upperBound() {
        return this.upperBound;
    }

    public BoundType upperBoundType() {
        return this.upperBound.typeAsUpperBound();
    }

    public C upperEndpoint() {
        return (C) this.upperBound.endpoint();
    }

    public static String toString(Cut<?> lowerBound, Cut<?> upperBound) {
        StringBuilder sb = new StringBuilder(16);
        lowerBound.describeAsLowerBound(sb);
        sb.append("..");
        upperBound.describeAsUpperBound(sb);
        return sb.toString();
    }

    @InlineMe(replacement = "this.contains(input)")
    @Deprecated
    public boolean apply(C input) {
        return contains(input);
    }
}
