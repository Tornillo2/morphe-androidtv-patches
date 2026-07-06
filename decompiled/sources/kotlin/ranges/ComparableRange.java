package kotlin.ranges;

import java.lang.Comparable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ComparableRange<T extends Comparable<? super T>> implements ClosedRange<T> {

    @NotNull
    public final T endInclusive;

    @NotNull
    public final T start;

    public ComparableRange(@NotNull T start, @NotNull T endInclusive) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
        this.start = start;
        this.endInclusive = endInclusive;
    }

    @Override // kotlin.ranges.ClosedRange
    public boolean contains(@NotNull T t) {
        return ClosedRange.DefaultImpls.contains(this, t);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ComparableRange)) {
            return false;
        }
        if (isEmpty() && ((ComparableRange) obj).isEmpty()) {
            return true;
        }
        ComparableRange comparableRange = (ComparableRange) obj;
        return Intrinsics.areEqual(getStart(), comparableRange.getStart()) && Intrinsics.areEqual(getEndInclusive(), comparableRange.getEndInclusive());
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public T getEndInclusive() {
        return this.endInclusive;
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public T getStart() {
        return this.start;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return getEndInclusive().hashCode() + (getStart().hashCode() * 31);
    }

    @Override // kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return ClosedRange.DefaultImpls.isEmpty(this);
    }

    @NotNull
    public String toString() {
        return getStart() + ".." + getEndInclusive();
    }
}
