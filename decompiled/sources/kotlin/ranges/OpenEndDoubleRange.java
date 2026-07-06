package kotlin.ranges;

import com.amazon.avod.mpb.api.sample.VideoMetadata$$ExternalSyntheticBackport0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class OpenEndDoubleRange implements OpenEndRange<Double> {
    public final double _endExclusive;
    public final double _start;

    public OpenEndDoubleRange(double d, double d2) {
        this._start = d;
        this._endExclusive = d2;
    }

    private final boolean lessThanOrEquals(double d, double d2) {
        return d <= d2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).doubleValue());
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof OpenEndDoubleRange)) {
            return false;
        }
        if (isEmpty() && ((OpenEndDoubleRange) obj).isEmpty()) {
            return true;
        }
        OpenEndDoubleRange openEndDoubleRange = (OpenEndDoubleRange) obj;
        return this._start == openEndDoubleRange._start && this._endExclusive == openEndDoubleRange._endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable getEndExclusive() {
        return Double.valueOf(this._endExclusive);
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable getStart() {
        return Double.valueOf(this._start);
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return VideoMetadata$$ExternalSyntheticBackport0.m(this._endExclusive) + (VideoMetadata$$ExternalSyntheticBackport0.m(this._start) * 31);
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this._start >= this._endExclusive;
    }

    @NotNull
    public String toString() {
        return this._start + "..<" + this._endExclusive;
    }

    public boolean contains(double d) {
        return d >= this._start && d < this._endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Double getEndExclusive() {
        return Double.valueOf(this._endExclusive);
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Double getStart() {
        return Double.valueOf(this._start);
    }
}
