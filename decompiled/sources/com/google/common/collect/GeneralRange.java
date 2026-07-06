package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class GeneralRange<T> implements Serializable {
    public final Comparator<? super T> comparator;
    public final boolean hasLowerBound;
    public final boolean hasUpperBound;
    public final BoundType lowerBoundType;
    public final T lowerEndpoint;

    @LazyInit
    public transient GeneralRange<T> reverse;
    public final BoundType upperBoundType;
    public final T upperEndpoint;

    /* JADX WARN: Multi-variable type inference failed */
    public GeneralRange(Comparator<? super T> comparator, boolean hasLowerBound, T lowerEndpoint, BoundType lowerBoundType, boolean hasUpperBound, T upperEndpoint, BoundType upperBoundType) {
        comparator.getClass();
        this.comparator = comparator;
        this.hasLowerBound = hasLowerBound;
        this.hasUpperBound = hasUpperBound;
        this.lowerEndpoint = lowerEndpoint;
        lowerBoundType.getClass();
        this.lowerBoundType = lowerBoundType;
        this.upperEndpoint = upperEndpoint;
        upperBoundType.getClass();
        this.upperBoundType = upperBoundType;
        if (hasLowerBound) {
            comparator.compare(lowerEndpoint, lowerEndpoint);
        }
        if (hasUpperBound) {
            comparator.compare(upperEndpoint, upperEndpoint);
        }
        if (hasLowerBound && hasUpperBound) {
            int iCompare = comparator.compare(lowerEndpoint, upperEndpoint);
            Preconditions.checkArgument(iCompare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", lowerEndpoint, upperEndpoint);
            if (iCompare == 0) {
                BoundType boundType = BoundType.OPEN;
                Preconditions.checkArgument((lowerBoundType == boundType && upperBoundType == boundType) ? false : true);
            }
        }
    }

    public static <T> GeneralRange<T> all(Comparator<? super T> comparator) {
        BoundType boundType = BoundType.OPEN;
        return new GeneralRange<>(comparator, false, null, boundType, false, null, boundType);
    }

    public static <T> GeneralRange<T> downTo(Comparator<? super T> comparator, @ParametricNullness T endpoint, BoundType boundType) {
        return new GeneralRange<>(comparator, true, endpoint, boundType, false, null, BoundType.OPEN);
    }

    public static <T extends Comparable> GeneralRange<T> from(Range<T> range) {
        return new GeneralRange<>(NaturalOrdering.INSTANCE, range.hasLowerBound(), range.hasLowerBound() ? range.lowerBound.endpoint() : null, range.hasLowerBound() ? range.lowerBound.typeAsLowerBound() : BoundType.OPEN, range.hasUpperBound(), range.hasUpperBound() ? range.upperBound.endpoint() : null, range.hasUpperBound() ? range.upperBound.typeAsUpperBound() : BoundType.OPEN);
    }

    public static <T> GeneralRange<T> range(Comparator<? super T> comparator, @ParametricNullness T lower, BoundType lowerType, @ParametricNullness T upper, BoundType upperType) {
        return new GeneralRange<>(comparator, true, lower, lowerType, true, upper, upperType);
    }

    public static <T> Comparator<T> reverseComparator(Comparator<T> comparator) {
        return Ordering.from(comparator).reverse();
    }

    public static <T> GeneralRange<T> upTo(Comparator<? super T> comparator, @ParametricNullness T endpoint, BoundType boundType) {
        return new GeneralRange<>(comparator, false, null, BoundType.OPEN, true, endpoint, boundType);
    }

    public Comparator<? super T> comparator() {
        return this.comparator;
    }

    public boolean contains(@ParametricNullness T t) {
        return (tooLow(t) || tooHigh(t)) ? false : true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GeneralRange) {
            GeneralRange generalRange = (GeneralRange) obj;
            if (this.comparator.equals(generalRange.comparator) && this.hasLowerBound == generalRange.hasLowerBound && this.hasUpperBound == generalRange.hasUpperBound && this.lowerBoundType.equals(generalRange.lowerBoundType) && this.upperBoundType.equals(generalRange.upperBoundType) && Objects.equal(this.lowerEndpoint, generalRange.lowerEndpoint) && Objects.equal(this.upperEndpoint, generalRange.upperEndpoint)) {
                return true;
            }
        }
        return false;
    }

    public BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    public T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    public BoundType getUpperBoundType() {
        return this.upperBoundType;
    }

    public T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    public boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    public boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.comparator, this.lowerEndpoint, this.lowerBoundType, this.upperEndpoint, this.upperBoundType});
    }

    public GeneralRange<T> intersect(GeneralRange<T> generalRange) {
        int iCompare;
        int iCompare2;
        T t;
        int iCompare3;
        BoundType boundType;
        generalRange.getClass();
        Preconditions.checkArgument(this.comparator.equals(generalRange.comparator));
        boolean z = this.hasLowerBound;
        T t2 = this.lowerEndpoint;
        BoundType boundType2 = this.lowerBoundType;
        if (!z) {
            z = generalRange.hasLowerBound;
            t2 = generalRange.lowerEndpoint;
            boundType2 = generalRange.lowerBoundType;
        } else if (generalRange.hasLowerBound && ((iCompare = this.comparator.compare(t2, generalRange.lowerEndpoint)) < 0 || (iCompare == 0 && generalRange.lowerBoundType == BoundType.OPEN))) {
            t2 = generalRange.lowerEndpoint;
            boundType2 = generalRange.lowerBoundType;
        }
        boolean z2 = z;
        boolean z3 = this.hasUpperBound;
        T t3 = this.upperEndpoint;
        BoundType boundType3 = this.upperBoundType;
        if (!z3) {
            z3 = generalRange.hasUpperBound;
            t3 = generalRange.upperEndpoint;
            boundType3 = generalRange.upperBoundType;
        } else if (generalRange.hasUpperBound && ((iCompare2 = this.comparator.compare(t3, generalRange.upperEndpoint)) > 0 || (iCompare2 == 0 && generalRange.upperBoundType == BoundType.OPEN))) {
            t3 = generalRange.upperEndpoint;
            boundType3 = generalRange.upperBoundType;
        }
        boolean z4 = z3;
        T t4 = t3;
        if (z2 && z4 && ((iCompare3 = this.comparator.compare(t2, t4)) > 0 || (iCompare3 == 0 && boundType2 == (boundType = BoundType.OPEN) && boundType3 == boundType))) {
            boundType2 = BoundType.OPEN;
            boundType3 = BoundType.CLOSED;
            t = t4;
        } else {
            t = t2;
        }
        return new GeneralRange<>(this.comparator, z2, t, boundType2, z4, t4, boundType3);
    }

    public boolean isEmpty() {
        if (this.hasUpperBound && tooLow(this.upperEndpoint)) {
            return true;
        }
        return this.hasLowerBound && tooHigh(this.lowerEndpoint);
    }

    public GeneralRange<T> reverse() {
        GeneralRange<T> generalRange = this.reverse;
        if (generalRange != null) {
            return generalRange;
        }
        GeneralRange<T> generalRange2 = new GeneralRange<>(reverseComparator(this.comparator), this.hasUpperBound, this.upperEndpoint, this.upperBoundType, this.hasLowerBound, this.lowerEndpoint, this.lowerBoundType);
        generalRange2.reverse = this;
        this.reverse = generalRange2;
        return generalRange2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        BoundType boundType = this.lowerBoundType;
        BoundType boundType2 = BoundType.CLOSED;
        sb.append(boundType == boundType2 ? AbstractJsonLexerKt.BEGIN_LIST : '(');
        sb.append(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        sb.append(',');
        sb.append(this.hasUpperBound ? this.upperEndpoint : "∞");
        sb.append(this.upperBoundType == boundType2 ? AbstractJsonLexerKt.END_LIST : ')');
        return sb.toString();
    }

    public boolean tooHigh(@ParametricNullness T t) {
        if (!this.hasUpperBound) {
            return false;
        }
        int iCompare = this.comparator.compare(t, this.upperEndpoint);
        return ((iCompare == 0) & (this.upperBoundType == BoundType.OPEN)) | (iCompare > 0);
    }

    public boolean tooLow(@ParametricNullness T t) {
        if (!this.hasLowerBound) {
            return false;
        }
        int iCompare = this.comparator.compare(t, this.lowerEndpoint);
        return ((iCompare == 0) & (this.lowerBoundType == BoundType.OPEN)) | (iCompare < 0);
    }
}
