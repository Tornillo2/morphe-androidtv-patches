package androidx.core.util;

import android.annotation.SuppressLint;
import android.util.Range;
import androidx.annotation.RequiresApi;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ClassVerificationFailure"})
public final class RangeKt {
    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> and(@NotNull Range<T> range, @NotNull Range<T> range2) {
        return range.intersect(range2);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> range, @NotNull T t) {
        return range.extend(t);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> rangeTo(@NotNull T t, @NotNull T t2) {
        return new Range<>(t, t2);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(@NotNull final Range<T> range) {
        return (ClosedRange<T>) new ClosedRange<T>() { // from class: androidx.core.util.RangeKt.toClosedRange.1
            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            @Override // kotlin.ranges.ClosedRange
            public boolean contains(Comparable comparable) {
                return ClosedRange.DefaultImpls.contains(this, comparable);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getEndInclusive() {
                return range.getUpper();
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getStart() {
                return range.getLower();
            }

            @Override // kotlin.ranges.ClosedRange
            public boolean isEmpty() {
                return ClosedRange.DefaultImpls.isEmpty(this);
            }
        };
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> toRange(@NotNull ClosedRange<T> closedRange) {
        return new Range<>(closedRange.getStart(), closedRange.getEndInclusive());
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> range, @NotNull Range<T> range2) {
        return range.extend(range2);
    }
}
