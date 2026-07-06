package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class OpenEndFloatRange implements OpenEndRange<Float> {
    public final float _endExclusive;
    public final float _start;

    public OpenEndFloatRange(float f, float f2) {
        this._start = f;
        this._endExclusive = f2;
    }

    private final boolean lessThanOrEquals(float f, float f2) {
        return f <= f2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).floatValue());
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof OpenEndFloatRange)) {
            return false;
        }
        if (isEmpty() && ((OpenEndFloatRange) obj).isEmpty()) {
            return true;
        }
        OpenEndFloatRange openEndFloatRange = (OpenEndFloatRange) obj;
        return this._start == openEndFloatRange._start && this._endExclusive == openEndFloatRange._endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable getEndExclusive() {
        return Float.valueOf(this._endExclusive);
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable getStart() {
        return Float.valueOf(this._start);
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return Float.floatToIntBits(this._endExclusive) + (Float.floatToIntBits(this._start) * 31);
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this._start >= this._endExclusive;
    }

    @NotNull
    public String toString() {
        return this._start + "..<" + this._endExclusive;
    }

    public boolean contains(float f) {
        return f >= this._start && f < this._endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Float getEndExclusive() {
        return Float.valueOf(this._endExclusive);
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Float getStart() {
        return Float.valueOf(this._start);
    }
}
