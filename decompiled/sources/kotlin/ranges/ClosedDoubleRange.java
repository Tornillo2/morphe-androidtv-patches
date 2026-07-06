package kotlin.ranges;

import com.amazon.avod.mpb.api.sample.VideoMetadata$$ExternalSyntheticBackport0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ClosedDoubleRange implements ClosedFloatingPointRange<Double> {
    public final double _endInclusive;
    public final double _start;

    public ClosedDoubleRange(double d, double d2) {
        this._start = d;
        this._endInclusive = d2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).doubleValue());
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ClosedDoubleRange)) {
            return false;
        }
        if (isEmpty() && ((ClosedDoubleRange) obj).isEmpty()) {
            return true;
        }
        ClosedDoubleRange closedDoubleRange = (ClosedDoubleRange) obj;
        return this._start == closedDoubleRange._start && this._endInclusive == closedDoubleRange._endInclusive;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return Double.valueOf(this._endInclusive);
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return Double.valueOf(this._start);
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return VideoMetadata$$ExternalSyntheticBackport0.m(this._endInclusive) + (VideoMetadata$$ExternalSyntheticBackport0.m(this._start) * 31);
    }

    @Override // kotlin.ranges.ClosedFloatingPointRange, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this._start > this._endInclusive;
    }

    public boolean lessThanOrEquals(double d, double d2) {
        return d <= d2;
    }

    @NotNull
    public String toString() {
        return this._start + ".." + this._endInclusive;
    }

    public boolean contains(double d) {
        return d >= this._start && d <= this._endInclusive;
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Double getEndInclusive() {
        return Double.valueOf(this._endInclusive);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Double getStart() {
        return Double.valueOf(this._start);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.ClosedFloatingPointRange
    public /* bridge */ /* synthetic */ boolean lessThanOrEquals(Comparable comparable, Comparable comparable2) {
        return lessThanOrEquals(((Number) comparable).doubleValue(), ((Number) comparable2).doubleValue());
    }
}
