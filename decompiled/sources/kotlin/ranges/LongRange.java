package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LongRange extends LongProgression implements ClosedRange<Long>, OpenEndRange<Long> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final LongRange EMPTY = new LongRange(1, 0);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final LongRange getEMPTY() {
            return LongRange.EMPTY;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public LongRange(long j, long j2) {
        super(j, j2, 1L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).longValue());
    }

    @Override // kotlin.ranges.LongProgression
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof LongRange)) {
            return false;
        }
        if (isEmpty() && ((LongRange) obj).isEmpty()) {
            return true;
        }
        LongRange longRange = (LongRange) obj;
        return this.first == longRange.first && this.last == longRange.last;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return Long.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return Long.valueOf(this.first);
    }

    @Override // kotlin.ranges.LongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.first;
        long j2 = ((long) 31) * (j ^ (j >>> 32));
        long j3 = this.last;
        return (int) (j2 + (j3 ^ (j3 >>> 32)));
    }

    @Override // kotlin.ranges.LongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this.first > this.last;
    }

    @Override // kotlin.ranges.LongProgression
    @NotNull
    public String toString() {
        return this.first + ".." + this.last;
    }

    public boolean contains(long j) {
        return this.first <= j && j <= this.last;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Long getEndExclusive() {
        long j = this.last;
        if (j != Long.MAX_VALUE) {
            return Long.valueOf(j + 1);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Long getEndInclusive() {
        return Long.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Long getStart() {
        return Long.valueOf(this.first);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with Long type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static /* synthetic */ void getEndExclusive$annotations() {
    }
}
