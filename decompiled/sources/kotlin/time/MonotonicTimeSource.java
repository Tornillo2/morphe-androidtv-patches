package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
public final class MonotonicTimeSource implements TimeSource.WithComparableMarks {

    @NotNull
    public static final MonotonicTimeSource INSTANCE = new MonotonicTimeSource();
    public static final long zero = System.nanoTime();

    private final long read() {
        return System.nanoTime() - zero;
    }

    /* JADX INFO: renamed from: adjustReading-6QKq23U, reason: not valid java name */
    public final long m2032adjustReading6QKq23U(long j, long j2) {
        return LongSaturatedMathKt.m2030saturatingAddNuflL3o(j, DurationUnit.NANOSECONDS, j2);
    }

    /* JADX INFO: renamed from: differenceBetween-fRLX17w, reason: not valid java name */
    public final long m2033differenceBetweenfRLX17w(long j, long j2) {
        return LongSaturatedMathKt.saturatingOriginsDiff(j, j2, DurationUnit.NANOSECONDS);
    }

    /* JADX INFO: renamed from: elapsedFrom-6eNON_k, reason: not valid java name */
    public final long m2034elapsedFrom6eNON_k(long j) {
        return LongSaturatedMathKt.saturatingDiff(read(), j, DurationUnit.NANOSECONDS);
    }

    @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
    public ComparableTimeMark markNow() {
        return new TimeSource.Monotonic.ValueTimeMark(read());
    }

    /* JADX INFO: renamed from: markNow-z9LOYto, reason: not valid java name */
    public long m2035markNowz9LOYto() {
        return read();
    }

    @NotNull
    public String toString() {
        return "TimeSource(System.nanoTime())";
    }

    @Override // kotlin.time.TimeSource
    public TimeMark markNow() {
        return new TimeSource.Monotonic.ValueTimeMark(read());
    }
}
