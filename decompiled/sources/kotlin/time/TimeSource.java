package kotlin.time;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
public interface TimeSource {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        @NotNull
        ComparableTimeMark markNow();
    }

    @NotNull
    TimeMark markNow();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Monotonic implements WithComparableMarks {

        @NotNull
        public static final Monotonic INSTANCE = new Monotonic();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @SinceKotlin(version = "1.9")
        @JvmInline
        @WasExperimental(markerClass = {ExperimentalTime.class})
        public static final class ValueTimeMark implements ComparableTimeMark {
            public final long reading;

            public /* synthetic */ ValueTimeMark(long j) {
                this.reading = j;
            }

            /* JADX INFO: renamed from: box-impl, reason: not valid java name */
            public static final /* synthetic */ ValueTimeMark m2041boximpl(long j) {
                return new ValueTimeMark(j);
            }

            /* JADX INFO: renamed from: compareTo-6eNON_k, reason: not valid java name */
            public static final int m2042compareTo6eNON_k(long j, long j2) {
                long jM2051minus6eNON_k = m2051minus6eNON_k(j, j2);
                Duration.Companion.getClass();
                return Duration.m1924compareToLRDsOJo(jM2051minus6eNON_k, Duration.ZERO);
            }

            /* JADX INFO: renamed from: compareTo-impl, reason: not valid java name */
            public static int m2043compareToimpl(long j, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return ComparableTimeMark.DefaultImpls.compareTo(new ValueTimeMark(j), other);
            }

            /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
            public static boolean m2046equalsimpl(long j, Object obj) {
                return (obj instanceof ValueTimeMark) && j == ((ValueTimeMark) obj).reading;
            }

            /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
            public static final boolean m2047equalsimpl0(long j, long j2) {
                return j == j2;
            }

            /* JADX INFO: renamed from: hasNotPassedNow-impl, reason: not valid java name */
            public static boolean m2048hasNotPassedNowimpl(long j) {
                return Duration.m1951isNegativeimpl(MonotonicTimeSource.INSTANCE.m2034elapsedFrom6eNON_k(j));
            }

            /* JADX INFO: renamed from: hasPassedNow-impl, reason: not valid java name */
            public static boolean m2049hasPassedNowimpl(long j) {
                return !Duration.m1951isNegativeimpl(MonotonicTimeSource.INSTANCE.m2034elapsedFrom6eNON_k(j));
            }

            /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
            public static int m2050hashCodeimpl(long j) {
                return FloatFloatPair$$ExternalSyntheticBackport0.m(j);
            }

            /* JADX INFO: renamed from: minus-6eNON_k, reason: not valid java name */
            public static final long m2051minus6eNON_k(long j, long j2) {
                MonotonicTimeSource.INSTANCE.getClass();
                return LongSaturatedMathKt.saturatingOriginsDiff(j, j2, DurationUnit.NANOSECONDS);
            }

            /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
            public static String m2055toStringimpl(long j) {
                return "ValueTimeMark(reading=" + j + ')';
            }

            @Override // java.lang.Comparable
            public int compareTo(ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
            }

            @Override // kotlin.time.TimeMark
            /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
            public long mo1915elapsedNowUwyO8pc() {
                return MonotonicTimeSource.INSTANCE.m2034elapsedFrom6eNON_k(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object obj) {
                return m2046equalsimpl(this.reading, obj);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m2048hasNotPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m2049hasPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return FloatFloatPair$$ExternalSyntheticBackport0.m(this.reading);
            }

            /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
            public long m2056minusLRDsOJo(long j) {
                return m2052minusLRDsOJo(this.reading, j);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* JADX INFO: renamed from: minus-UwyO8pc */
            public long mo1917minusUwyO8pc(@NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m2053minusUwyO8pc(this.reading, other);
            }

            /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
            public long m2057plusLRDsOJo(long j) {
                return m2054plusLRDsOJo(this.reading, j);
            }

            public String toString() {
                return m2055toStringimpl(this.reading);
            }

            /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
            public final /* synthetic */ long m2058unboximpl() {
                return this.reading;
            }

            /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
            public static long m2052minusLRDsOJo(long j, long j2) {
                MonotonicTimeSource monotonicTimeSource = MonotonicTimeSource.INSTANCE;
                long jM1969unaryMinusUwyO8pc = Duration.m1969unaryMinusUwyO8pc(j2);
                monotonicTimeSource.getClass();
                return LongSaturatedMathKt.m2030saturatingAddNuflL3o(j, DurationUnit.NANOSECONDS, jM1969unaryMinusUwyO8pc);
            }

            /* JADX INFO: renamed from: minus-UwyO8pc, reason: not valid java name */
            public static long m2053minusUwyO8pc(long j, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (other instanceof ValueTimeMark) {
                    return m2051minus6eNON_k(j, ((ValueTimeMark) other).reading);
                }
                throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m2055toStringimpl(j)) + " and " + other);
            }

            /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
            public static long m2054plusLRDsOJo(long j, long j2) {
                MonotonicTimeSource.INSTANCE.getClass();
                return LongSaturatedMathKt.m2030saturatingAddNuflL3o(j, DurationUnit.NANOSECONDS, j2);
            }

            /* JADX INFO: renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
            public static long m2045elapsedNowUwyO8pc(long j) {
                return MonotonicTimeSource.INSTANCE.m2034elapsedFrom6eNON_k(j);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* JADX INFO: renamed from: compareTo, reason: avoid collision after fix types in other method */
            public int compareTo2(@NotNull ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* JADX INFO: renamed from: minus-LRDsOJo */
            public ComparableTimeMark mo1916minusLRDsOJo(long j) {
                return new ValueTimeMark(m2052minusLRDsOJo(this.reading, j));
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* JADX INFO: renamed from: plus-LRDsOJo */
            public ComparableTimeMark mo1918plusLRDsOJo(long j) {
                return new ValueTimeMark(m2054plusLRDsOJo(this.reading, j));
            }

            @Override // kotlin.time.TimeMark
            /* JADX INFO: renamed from: minus-LRDsOJo */
            public TimeMark mo1916minusLRDsOJo(long j) {
                return new ValueTimeMark(m2052minusLRDsOJo(this.reading, j));
            }

            @Override // kotlin.time.TimeMark
            /* JADX INFO: renamed from: plus-LRDsOJo */
            public TimeMark mo1918plusLRDsOJo(long j) {
                return new ValueTimeMark(m2054plusLRDsOJo(this.reading, j));
            }

            /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
            public static long m2044constructorimpl(long j) {
                return j;
            }
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public ComparableTimeMark markNow() {
            return new ValueTimeMark(MonotonicTimeSource.INSTANCE.m2035markNowz9LOYto());
        }

        /* JADX INFO: renamed from: markNow-z9LOYto, reason: not valid java name */
        public long m2040markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m2035markNowz9LOYto();
        }

        @NotNull
        public String toString() {
            MonotonicTimeSource.INSTANCE.getClass();
            return "TimeSource(System.nanoTime())";
        }

        @Override // kotlin.time.TimeSource
        public TimeMark markNow() {
            return new ValueTimeMark(MonotonicTimeSource.INSTANCE.m2035markNowz9LOYto());
        }
    }
}
