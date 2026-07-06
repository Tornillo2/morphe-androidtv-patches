package kotlin.time.jdk8;

import j$.time.Instant;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "InstantConversionsJDK8Kt")
public final class InstantConversionsJDK8Kt {
    @SinceKotlin(version = "2.1")
    @ExperimentalTime
    @NotNull
    public static final Instant toJavaInstant(@NotNull kotlin.time.Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Instant instantOfEpochSecond = Instant.ofEpochSecond(instant.epochSeconds, instant.nanosecondsOfSecond);
        Intrinsics.checkNotNullExpressionValue(instantOfEpochSecond, "ofEpochSecond(...)");
        return instantOfEpochSecond;
    }

    @SinceKotlin(version = "2.1")
    @ExperimentalTime
    @NotNull
    public static final kotlin.time.Instant toKotlinInstant(@NotNull Instant instant) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        return kotlin.time.Instant.Companion.fromEpochSeconds(instant.getEpochSecond(), instant.getNano());
    }
}
