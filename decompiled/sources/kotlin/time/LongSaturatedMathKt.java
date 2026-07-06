package kotlin.time;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nlongSaturatedMath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,81:1\n80#1:82\n80#1:83\n80#1:84\n80#1:85\n80#1:86\n80#1:87\n*S KotlinDebug\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n14#1:82\n17#1:83\n36#1:84\n46#1:85\n53#1:86\n57#1:87\n*E\n"})
public final class LongSaturatedMathKt {
    /* JADX INFO: renamed from: checkInfiniteSumDefined-PjuGub4, reason: not valid java name */
    public static final long m2029checkInfiniteSumDefinedPjuGub4(long j, long j2, long j3) {
        if (!Duration.m1950isInfiniteimpl(j2) || (j ^ j3) >= 0) {
            return j;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    public static final long infinityOfSign(long j) {
        Duration.Companion.getClass();
        return j < 0 ? Duration.NEG_INFINITE : Duration.INFINITE;
    }

    public static final boolean isSaturated(long j) {
        return ((j - 1) | 1) == Long.MAX_VALUE;
    }

    /* JADX INFO: renamed from: saturatingAdd-NuflL3o, reason: not valid java name */
    public static final long m2030saturatingAddNuflL3o(long j, @NotNull DurationUnit unit, long j2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long jM1964toLongimpl = Duration.m1964toLongimpl(j2, unit);
        if (((j - 1) | 1) == Long.MAX_VALUE) {
            m2029checkInfiniteSumDefinedPjuGub4(j, j2, jM1964toLongimpl);
            return j;
        }
        if (((jM1964toLongimpl - 1) | 1) == Long.MAX_VALUE) {
            return m2031saturatingAddInHalvesNuflL3o(j, unit, j2);
        }
        long j3 = j + jM1964toLongimpl;
        return ((j ^ j3) & (jM1964toLongimpl ^ j3)) < 0 ? j < 0 ? Long.MIN_VALUE : Long.MAX_VALUE : j3;
    }

    /* JADX INFO: renamed from: saturatingAddInHalves-NuflL3o, reason: not valid java name */
    public static final long m2031saturatingAddInHalvesNuflL3o(long j, DurationUnit durationUnit, long j2) {
        long jM1928divUwyO8pc = Duration.m1928divUwyO8pc(j2, 2);
        long jM1964toLongimpl = Duration.m1964toLongimpl(jM1928divUwyO8pc, durationUnit);
        return (1 | (jM1964toLongimpl - 1)) == Long.MAX_VALUE ? jM1964toLongimpl : m2030saturatingAddNuflL3o(m2030saturatingAddNuflL3o(j, durationUnit, jM1928divUwyO8pc), durationUnit, Duration.m1953minusLRDsOJo(j2, jM1928divUwyO8pc));
    }

    public static final long saturatingDiff(long j, long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (1 | (j2 - 1)) == Long.MAX_VALUE ? Duration.m1969unaryMinusUwyO8pc(infinityOfSign(j2)) : saturatingFiniteDiff(j, j2, unit);
    }

    public static final long saturatingFiniteDiff(long j, long j2, DurationUnit durationUnit) {
        long j3 = j - j2;
        if (((j3 ^ j) & (~(j3 ^ j2))) >= 0) {
            return DurationKt.toDuration(j3, durationUnit);
        }
        DurationUnit durationUnit2 = DurationUnit.MILLISECONDS;
        if (durationUnit.compareTo(durationUnit2) >= 0) {
            return Duration.m1969unaryMinusUwyO8pc(infinityOfSign(j3));
        }
        long jConvertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(1L, durationUnit2, durationUnit);
        long j4 = (j / jConvertDurationUnit) - (j2 / jConvertDurationUnit);
        long j5 = (j % jConvertDurationUnit) - (j2 % jConvertDurationUnit);
        Duration.Companion companion = Duration.Companion;
        return Duration.m1954plusLRDsOJo(DurationKt.toDuration(j4, durationUnit2), DurationKt.toDuration(j5, durationUnit));
    }

    public static final long saturatingOriginsDiff(long j, long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (((j2 - 1) | 1) != Long.MAX_VALUE) {
            return (1 | (j - 1)) == Long.MAX_VALUE ? infinityOfSign(j) : saturatingFiniteDiff(j, j2, unit);
        }
        if (j != j2) {
            return Duration.m1969unaryMinusUwyO8pc(infinityOfSign(j2));
        }
        Duration.Companion.getClass();
        return Duration.ZERO;
    }
}
