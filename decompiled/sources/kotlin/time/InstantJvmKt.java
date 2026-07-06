package kotlin.time;

import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class InstantJvmKt {

    @NotNull
    public static final Clock systemClock = PlatformImplementationsKt.IMPLEMENTATIONS.getSystemClock();

    @ExperimentalTime
    @NotNull
    public static final Object serializedInstant(@NotNull Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        return new InstantSerialized(instant.epochSeconds, instant.nanosecondsOfSecond);
    }

    @ExperimentalTime
    @NotNull
    public static final Instant systemClockNow() {
        return systemClock.now();
    }

    @ExperimentalTime
    public static /* synthetic */ void getSystemClock$annotations() {
    }
}
