package kotlin.time;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {

    @NotNull
    public final DurationUnit unit;

    @NotNull
    public final Lazy zero$delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,202:1\n80#2:203\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n*L\n67#1:203\n*E\n"})
    public static final class LongTimeMark implements ComparableTimeMark {
        public final long offset;
        public final long startedAt;

        @NotNull
        public final AbstractLongTimeSource timeSource;

        public /* synthetic */ LongTimeMark(long j, AbstractLongTimeSource abstractLongTimeSource, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, abstractLongTimeSource, j2);
        }

        @Override // java.lang.Comparable
        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }

        @Override // kotlin.time.TimeMark
        /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
        public long mo1915elapsedNowUwyO8pc() {
            return Duration.m1953minusLRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(this.timeSource.adjustedRead(), this.startedAt, this.timeSource.unit), this.offset);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof LongTimeMark) || !Intrinsics.areEqual(this.timeSource, ((LongTimeMark) obj).timeSource)) {
                return false;
            }
            long jMo1917minusUwyO8pc = mo1917minusUwyO8pc((ComparableTimeMark) obj);
            Duration.Companion.getClass();
            return Duration.m1930equalsimpl0(jMo1917minusUwyO8pc, Duration.ZERO);
        }

        @Override // kotlin.time.TimeMark
        public boolean hasNotPassedNow() {
            return TimeMark.DefaultImpls.hasNotPassedNow(this);
        }

        @Override // kotlin.time.TimeMark
        public boolean hasPassedNow() {
            return TimeMark.DefaultImpls.hasPassedNow(this);
        }

        @Override // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return FloatFloatPair$$ExternalSyntheticBackport0.m(this.startedAt) + (Duration.m1946hashCodeimpl(this.offset) * 37);
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        @NotNull
        /* JADX INFO: renamed from: minus-LRDsOJo */
        public ComparableTimeMark mo1916minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m1920minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* JADX INFO: renamed from: minus-UwyO8pc */
        public long mo1917minusUwyO8pc(@NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (other instanceof LongTimeMark) {
                LongTimeMark longTimeMark = (LongTimeMark) other;
                if (Intrinsics.areEqual(this.timeSource, longTimeMark.timeSource)) {
                    return Duration.m1954plusLRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(this.startedAt, longTimeMark.startedAt, this.timeSource.unit), Duration.m1953minusLRDsOJo(this.offset, longTimeMark.offset));
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        @NotNull
        public String toString() {
            return "LongTimeMark(" + this.startedAt + DurationUnitKt__DurationUnitKt.shortName(this.timeSource.unit) + " + " + ((Object) Duration.m1965toStringimpl(this.offset)) + ", " + this.timeSource + ')';
        }

        public LongTimeMark(long j, AbstractLongTimeSource timeSource, long j2) {
            Intrinsics.checkNotNullParameter(timeSource, "timeSource");
            this.startedAt = j;
            this.timeSource = timeSource;
            this.offset = j2;
        }

        @Override // kotlin.time.TimeMark
        /* JADX INFO: renamed from: minus-LRDsOJo */
        public TimeMark mo1916minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m1920minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* JADX INFO: renamed from: plus-LRDsOJo */
        public ComparableTimeMark mo1918plusLRDsOJo(long j) {
            DurationUnit durationUnit = this.timeSource.unit;
            if (Duration.m1950isInfiniteimpl(j)) {
                long jM2030saturatingAddNuflL3o = LongSaturatedMathKt.m2030saturatingAddNuflL3o(this.startedAt, durationUnit, j);
                AbstractLongTimeSource abstractLongTimeSource = this.timeSource;
                Duration.Companion.getClass();
                return new LongTimeMark(jM2030saturatingAddNuflL3o, abstractLongTimeSource, Duration.ZERO);
            }
            long jM1968truncateToUwyO8pc$kotlin_stdlib = Duration.m1968truncateToUwyO8pc$kotlin_stdlib(j, durationUnit);
            long jM1954plusLRDsOJo = Duration.m1954plusLRDsOJo(Duration.m1953minusLRDsOJo(j, jM1968truncateToUwyO8pc$kotlin_stdlib), this.offset);
            long jM2030saturatingAddNuflL3o2 = LongSaturatedMathKt.m2030saturatingAddNuflL3o(this.startedAt, durationUnit, jM1968truncateToUwyO8pc$kotlin_stdlib);
            long jM1968truncateToUwyO8pc$kotlin_stdlib2 = Duration.m1968truncateToUwyO8pc$kotlin_stdlib(jM1954plusLRDsOJo, durationUnit);
            long jM2030saturatingAddNuflL3o3 = LongSaturatedMathKt.m2030saturatingAddNuflL3o(jM2030saturatingAddNuflL3o2, durationUnit, jM1968truncateToUwyO8pc$kotlin_stdlib2);
            long jM1953minusLRDsOJo = Duration.m1953minusLRDsOJo(jM1954plusLRDsOJo, jM1968truncateToUwyO8pc$kotlin_stdlib2);
            long jM1938getInWholeNanosecondsimpl = Duration.m1938getInWholeNanosecondsimpl(jM1953minusLRDsOJo);
            if (jM2030saturatingAddNuflL3o3 != 0 && jM1938getInWholeNanosecondsimpl != 0 && (jM2030saturatingAddNuflL3o3 ^ jM1938getInWholeNanosecondsimpl) < 0) {
                long duration = DurationKt.toDuration(Long.signum(jM1938getInWholeNanosecondsimpl), durationUnit);
                jM2030saturatingAddNuflL3o3 = LongSaturatedMathKt.m2030saturatingAddNuflL3o(jM2030saturatingAddNuflL3o3, durationUnit, duration);
                jM1953minusLRDsOJo = Duration.m1953minusLRDsOJo(jM1953minusLRDsOJo, duration);
            }
            if ((1 | (jM2030saturatingAddNuflL3o3 - 1)) == Long.MAX_VALUE) {
                Duration.Companion.getClass();
                jM1953minusLRDsOJo = Duration.ZERO;
            }
            return new LongTimeMark(jM2030saturatingAddNuflL3o3, this.timeSource, jM1953minusLRDsOJo);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* JADX INFO: renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(@NotNull ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }
    }

    public AbstractLongTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
        this.zero$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: kotlin.time.AbstractLongTimeSource$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(this.f$0.read());
            }
        });
    }

    public final long adjustedRead() {
        return read() - getZero();
    }

    @NotNull
    public final DurationUnit getUnit() {
        return this.unit;
    }

    public final long getZero() {
        return ((Number) this.zero$delegate.getValue()).longValue();
    }

    public abstract long read();

    @Override // kotlin.time.TimeSource
    @NotNull
    public ComparableTimeMark markNow() {
        long jAdjustedRead = adjustedRead();
        Duration.Companion.getClass();
        return new LongTimeMark(jAdjustedRead, this, Duration.ZERO);
    }
}
