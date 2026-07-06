package kotlin.ranges;

import java.lang.Comparable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.OpenEndRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ComparableOpenEndRange<T extends Comparable<? super T>> implements OpenEndRange<T> {

    @NotNull
    public final T endExclusive;

    @NotNull
    public final T start;

    public ComparableOpenEndRange(@NotNull T start, @NotNull T endExclusive) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endExclusive, "endExclusive");
        this.start = start;
        this.endExclusive = endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean contains(@NotNull T t) {
        return OpenEndRange.DefaultImpls.contains(this, t);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ComparableOpenEndRange)) {
            return false;
        }
        if (isEmpty() && ((ComparableOpenEndRange) obj).isEmpty()) {
            return true;
        }
        ComparableOpenEndRange comparableOpenEndRange = (ComparableOpenEndRange) obj;
        return Intrinsics.areEqual(getStart(), comparableOpenEndRange.getStart()) && Intrinsics.areEqual(getEndExclusive(), comparableOpenEndRange.getEndExclusive());
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public T getEndExclusive() {
        return this.endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public T getStart() {
        return this.start;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return getEndExclusive().hashCode() + (getStart().hashCode() * 31);
    }

    @Override // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return OpenEndRange.DefaultImpls.isEmpty(this);
    }

    @NotNull
    public String toString() {
        return getStart() + "..<" + getEndExclusive();
    }
}
