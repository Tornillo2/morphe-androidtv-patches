package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
public interface ComparableTimeMark extends TimeMark, Comparable<ComparableTimeMark> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static int compareTo(@NotNull ComparableTimeMark comparableTimeMark, @NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            long jMo1917minusUwyO8pc = comparableTimeMark.mo1917minusUwyO8pc(other);
            Duration.Companion.getClass();
            return Duration.m1924compareToLRDsOJo(jMo1917minusUwyO8pc, Duration.ZERO);
        }

        public static boolean hasNotPassedNow(@NotNull ComparableTimeMark comparableTimeMark) {
            return TimeMark.DefaultImpls.hasNotPassedNow(comparableTimeMark);
        }

        public static boolean hasPassedNow(@NotNull ComparableTimeMark comparableTimeMark) {
            return TimeMark.DefaultImpls.hasPassedNow(comparableTimeMark);
        }

        @NotNull
        /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
        public static ComparableTimeMark m1920minusLRDsOJo(@NotNull ComparableTimeMark comparableTimeMark, long j) {
            return comparableTimeMark.mo1918plusLRDsOJo(Duration.m1969unaryMinusUwyO8pc(j));
        }
    }

    int compareTo(@NotNull ComparableTimeMark comparableTimeMark);

    boolean equals(@Nullable Object obj);

    int hashCode();

    @Override // kotlin.time.TimeMark
    @NotNull
    /* JADX INFO: renamed from: minus-LRDsOJo */
    ComparableTimeMark mo1916minusLRDsOJo(long j);

    /* JADX INFO: renamed from: minus-UwyO8pc */
    long mo1917minusUwyO8pc(@NotNull ComparableTimeMark comparableTimeMark);

    @Override // kotlin.time.TimeMark
    @NotNull
    /* JADX INFO: renamed from: plus-LRDsOJo */
    ComparableTimeMark mo1918plusLRDsOJo(long j);
}
