package kotlin.time;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "2.1")
@SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Instant.kt\nkotlin/time/InstantKt\n+ 4 Duration.kt\nkotlin/time/Duration\n*L\n1#1,799:1\n1#2:800\n738#3,14:801\n721#3,6:815\n738#3,14:821\n721#3,6:835\n721#3,6:842\n549#4:841\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n*L\n148#1:801,14\n151#1:815,6\n159#1:821,14\n162#1:835,6\n186#1:842,6\n182#1:841\n*E\n"})
@ExperimentalTime
public final class Instant implements Comparable<Instant>, Serializable {
    public final long epochSeconds;
    public final int nanosecondsOfSecond;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Instant MIN = new Instant(InstantKt.MIN_SECOND, 0);

    @NotNull
    public static final Instant MAX = new Instant(InstantKt.MAX_SECOND, 999999999);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n+ 2 Instant.kt\nkotlin/time/InstantKt\n*L\n1#1,799:1\n721#2,6:800\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n*L\n308#1:800,6\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        public static /* synthetic */ Instant fromEpochSeconds$default(Companion companion, long j, long j2, int i, Object obj) {
            if ((i & 2) != 0) {
                j2 = 0;
            }
            return companion.fromEpochSeconds(j, j2);
        }

        @NotNull
        public final Instant fromEpochMilliseconds(long j) {
            long j2 = j / 1000;
            if ((j ^ 1000) < 0 && j2 * 1000 != j) {
                j2--;
            }
            long j3 = j % 1000;
            return j2 < InstantKt.MIN_SECOND ? Instant.MIN : j2 > InstantKt.MAX_SECOND ? Instant.MAX : fromEpochSeconds(j2, (int) ((j3 + (1000 & (((j3 ^ 1000) & ((-j3) | j3)) >> 63))) * ((long) 1000000)));
        }

        @NotNull
        public final Instant fromEpochSeconds(long j, long j2) {
            long j3 = j2 / 1000000000;
            if ((j2 ^ 1000000000) < 0 && j3 * 1000000000 != j2) {
                j3--;
            }
            long j4 = j + j3;
            if ((j ^ j4) < 0 && (j3 ^ j) >= 0) {
                Instant.Companion.getClass();
                return j > 0 ? Instant.MAX : Instant.MIN;
            }
            if (j4 < InstantKt.MIN_SECOND) {
                return Instant.MIN;
            }
            if (j4 > InstantKt.MAX_SECOND) {
                return Instant.MAX;
            }
            long j5 = j2 % 1000000000;
            return new Instant(j4, (int) (j5 + ((((j5 ^ 1000000000) & ((-j5) | j5)) >> 63) & 1000000000)));
        }

        @NotNull
        public final Instant getDISTANT_FUTURE() {
            return fromEpochSeconds(InstantKt.DISTANT_FUTURE_SECONDS, 0);
        }

        @NotNull
        public final Instant getDISTANT_PAST() {
            return fromEpochSeconds(InstantKt.DISTANT_PAST_SECONDS, 999999999);
        }

        @NotNull
        public final Instant getMAX$kotlin_stdlib() {
            return Instant.MAX;
        }

        @NotNull
        public final Instant getMIN$kotlin_stdlib() {
            return Instant.MIN;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use Clock.System.now() instead", replaceWith = @ReplaceWith(expression = "Clock.System.now()", imports = {"kotlin.time.Clock"}))
        @NotNull
        public final Instant now() {
            throw new NotImplementedError(null, 1, null);
        }

        @NotNull
        public final Instant parse(@NotNull CharSequence input) {
            Intrinsics.checkNotNullParameter(input, "input");
            return InstantKt.parseIso(input);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @NotNull
        public final Instant fromEpochSeconds(long j, int i) {
            return fromEpochSeconds(j, i);
        }
    }

    public Instant(long j, int i) {
        this.epochSeconds = j;
        this.nanosecondsOfSecond = i;
        if (InstantKt.MIN_SECOND > j || j >= 31556889864403200L) {
            throw new IllegalArgumentException("Instant exceeds minimum or maximum instant");
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) obj;
        return this.epochSeconds == instant.epochSeconds && this.nanosecondsOfSecond == instant.nanosecondsOfSecond;
    }

    public final long getEpochSeconds() {
        return this.epochSeconds;
    }

    public final int getNanosecondsOfSecond() {
        return this.nanosecondsOfSecond;
    }

    public int hashCode() {
        return (this.nanosecondsOfSecond * 51) + FloatFloatPair$$ExternalSyntheticBackport0.m(this.epochSeconds);
    }

    @NotNull
    /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
    public final Instant m2023minusLRDsOJo(long j) {
        return m2025plusLRDsOJo(Duration.m1969unaryMinusUwyO8pc(j));
    }

    /* JADX INFO: renamed from: minus-UwyO8pc, reason: not valid java name */
    public final long m2024minusUwyO8pc(@NotNull Instant other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Duration.Companion companion = Duration.Companion;
        return Duration.m1954plusLRDsOJo(DurationKt.toDuration(this.epochSeconds - other.epochSeconds, DurationUnit.SECONDS), DurationKt.toDuration(this.nanosecondsOfSecond - other.nanosecondsOfSecond, DurationUnit.NANOSECONDS));
    }

    @NotNull
    /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
    public final Instant m2025plusLRDsOJo(long j) {
        long jM1939getInWholeSecondsimpl = Duration.m1939getInWholeSecondsimpl(j);
        int iM1941getNanosecondsComponentimpl = Duration.m1941getNanosecondsComponentimpl(j);
        if (jM1939getInWholeSecondsimpl == 0 && iM1941getNanosecondsComponentimpl == 0) {
            return this;
        }
        long j2 = this.epochSeconds;
        long j3 = j2 + jM1939getInWholeSecondsimpl;
        if ((j2 ^ j3) >= 0 || (jM1939getInWholeSecondsimpl ^ j2) < 0) {
            return Companion.fromEpochSeconds(j3, this.nanosecondsOfSecond + iM1941getNanosecondsComponentimpl);
        }
        return Duration.m1952isPositiveimpl(j) ? MAX : MIN;
    }

    public final long toEpochMilliseconds() {
        long j = this.epochSeconds;
        long j2 = 1000;
        if (j >= 0) {
            if (j != 1) {
                if (j != 0) {
                    long j3 = j * 1000;
                    if (j3 / 1000 != j) {
                        return Long.MAX_VALUE;
                    }
                    j2 = j3;
                } else {
                    j2 = 0;
                }
            }
            long j4 = this.nanosecondsOfSecond / 1000000;
            long j5 = j2 + j4;
            if ((j2 ^ j5) >= 0 || (j4 ^ j2) < 0) {
                return j5;
            }
            return Long.MAX_VALUE;
        }
        long j6 = j + 1;
        if (j6 != 1) {
            if (j6 != 0) {
                long j7 = j6 * 1000;
                if (j7 / 1000 != j6) {
                    return Long.MIN_VALUE;
                }
                j2 = j7;
            } else {
                j2 = 0;
            }
        }
        long j8 = (this.nanosecondsOfSecond / 1000000) - 1000;
        long j9 = j2 + j8;
        if ((j2 ^ j9) >= 0 || (j8 ^ j2) < 0) {
            return j9;
        }
        return Long.MIN_VALUE;
    }

    @NotNull
    public String toString() {
        return InstantKt.formatIso(this);
    }

    public final Object writeReplace() {
        return InstantJvmKt.serializedInstant(this);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Instant other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int iCompare = Intrinsics.compare(this.epochSeconds, other.epochSeconds);
        return iCompare != 0 ? iCompare : Intrinsics.compare(this.nanosecondsOfSecond, other.nanosecondsOfSecond);
    }
}
