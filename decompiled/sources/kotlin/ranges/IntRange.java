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
public final class IntRange extends IntProgression implements ClosedRange<Integer>, OpenEndRange<Integer> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final IntRange EMPTY = new IntRange(1, 0, 1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final IntRange getEMPTY() {
            return IntRange.EMPTY;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public IntRange(int i, int i2) {
        super(i, i2, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).intValue());
    }

    @Override // kotlin.ranges.IntProgression
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof IntRange)) {
            return false;
        }
        if (isEmpty() && ((IntRange) obj).isEmpty()) {
            return true;
        }
        IntRange intRange = (IntRange) obj;
        return this.first == intRange.first && this.last == intRange.last;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return Integer.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return Integer.valueOf(this.first);
    }

    @Override // kotlin.ranges.IntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.first * 31) + this.last;
    }

    @Override // kotlin.ranges.IntProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this.first > this.last;
    }

    @Override // kotlin.ranges.IntProgression
    @NotNull
    public String toString() {
        return this.first + ".." + this.last;
    }

    public boolean contains(int i) {
        return this.first <= i && i <= this.last;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Integer getEndExclusive() {
        int i = this.last;
        if (i != Integer.MAX_VALUE) {
            return Integer.valueOf(i + 1);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Integer getEndInclusive() {
        return Integer.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Integer getStart() {
        return Integer.valueOf(this.first);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with Int type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static /* synthetic */ void getEndExclusive$annotations() {
    }
}
