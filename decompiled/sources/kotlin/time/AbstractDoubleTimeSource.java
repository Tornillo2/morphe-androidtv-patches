package kotlin.time;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.Deprecated;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated(message = "Using AbstractDoubleTimeSource is no longer recommended, use AbstractLongTimeSource instead.")
@SinceKotlin(version = "1.3")
@ExperimentalTime
public abstract class AbstractDoubleTimeSource implements TimeSource.WithComparableMarks {

    @NotNull
    public final DurationUnit unit;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DoubleTimeMark implements ComparableTimeMark {
        public final long offset;
        public final double startedAt;

        @NotNull
        public final AbstractDoubleTimeSource timeSource;

        public /* synthetic */ DoubleTimeMark(double d, AbstractDoubleTimeSource abstractDoubleTimeSource, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, abstractDoubleTimeSource, j);
        }

        @Override // java.lang.Comparable
        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }

        @Override // kotlin.time.TimeMark
        /* JADX INFO: renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
        public long mo1915elapsedNowUwyO8pc() {
            return Duration.m1953minusLRDsOJo(DurationKt.toDuration(this.timeSource.read() - this.startedAt, this.timeSource.unit), this.offset);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof DoubleTimeMark) || !Intrinsics.areEqual(this.timeSource, ((DoubleTimeMark) obj).timeSource)) {
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
            return FloatFloatPair$$ExternalSyntheticBackport0.m(Duration.m1954plusLRDsOJo(DurationKt.toDuration(this.startedAt, this.timeSource.unit), this.offset));
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        @NotNull
        /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
        public ComparableTimeMark mo1916minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m1920minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* JADX INFO: renamed from: minus-UwyO8pc, reason: not valid java name */
        public long mo1917minusUwyO8pc(@NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (other instanceof DoubleTimeMark) {
                DoubleTimeMark doubleTimeMark = (DoubleTimeMark) other;
                if (Intrinsics.areEqual(this.timeSource, doubleTimeMark.timeSource)) {
                    if (Duration.m1930equalsimpl0(this.offset, doubleTimeMark.offset) && Duration.m1950isInfiniteimpl(this.offset)) {
                        Duration.Companion.getClass();
                        return Duration.ZERO;
                    }
                    long jM1953minusLRDsOJo = Duration.m1953minusLRDsOJo(this.offset, doubleTimeMark.offset);
                    long duration = DurationKt.toDuration(this.startedAt - doubleTimeMark.startedAt, this.timeSource.unit);
                    if (!Duration.m1930equalsimpl0(duration, Duration.m1969unaryMinusUwyO8pc(jM1953minusLRDsOJo))) {
                        return Duration.m1954plusLRDsOJo(duration, jM1953minusLRDsOJo);
                    }
                    Duration.Companion.getClass();
                    return Duration.ZERO;
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        @NotNull
        public String toString() {
            return "DoubleTimeMark(" + this.startedAt + DurationUnitKt__DurationUnitKt.shortName(this.timeSource.unit) + " + " + ((Object) Duration.m1965toStringimpl(this.offset)) + ", " + this.timeSource + ')';
        }

        public DoubleTimeMark(double d, AbstractDoubleTimeSource timeSource, long j) {
            Intrinsics.checkNotNullParameter(timeSource, "timeSource");
            this.startedAt = d;
            this.timeSource = timeSource;
            this.offset = j;
        }

        @Override // kotlin.time.TimeMark
        /* JADX INFO: renamed from: minus-LRDsOJo */
        public TimeMark mo1916minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m1920minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
        public ComparableTimeMark mo1918plusLRDsOJo(long j) {
            return new DoubleTimeMark(this.startedAt, this.timeSource, Duration.m1954plusLRDsOJo(this.offset, j));
        }

        @Override // kotlin.time.ComparableTimeMark
        /* JADX INFO: renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(@NotNull ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }
    }

    public AbstractDoubleTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
    }

    @NotNull
    public final DurationUnit getUnit() {
        return this.unit;
    }

    public abstract double read();

    @Override // kotlin.time.TimeSource
    @NotNull
    public ComparableTimeMark markNow() {
        double d = read();
        Duration.Companion.getClass();
        return new DoubleTimeMark(d, this, Duration.ZERO);
    }
}
