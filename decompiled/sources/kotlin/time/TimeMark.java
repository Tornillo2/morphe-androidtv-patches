package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
public interface TimeMark {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static boolean hasNotPassedNow(@NotNull TimeMark timeMark) {
            return Duration.m1951isNegativeimpl(timeMark.mo1915elapsedNowUwyO8pc());
        }

        public static boolean hasPassedNow(@NotNull TimeMark timeMark) {
            return !Duration.m1951isNegativeimpl(timeMark.mo1915elapsedNowUwyO8pc());
        }

        @NotNull
        /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
        public static TimeMark m2038minusLRDsOJo(@NotNull TimeMark timeMark, long j) {
            return timeMark.mo1918plusLRDsOJo(Duration.m1969unaryMinusUwyO8pc(j));
        }

        @NotNull
        /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
        public static TimeMark m2039plusLRDsOJo(@NotNull TimeMark timeMark, long j) {
            return new AdjustedTimeMark(timeMark, j);
        }
    }

    /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
    long mo1915elapsedNowUwyO8pc();

    boolean hasNotPassedNow();

    boolean hasPassedNow();

    @NotNull
    /* JADX INFO: renamed from: minus-LRDsOJo */
    TimeMark mo1916minusLRDsOJo(long j);

    @NotNull
    /* JADX INFO: renamed from: plus-LRDsOJo */
    TimeMark mo1918plusLRDsOJo(long j);
}
