package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.callback.LogCallback;
import java.util.Arrays;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MpbLog {

    @NotNull
    public static final MpbLog INSTANCE = new MpbLog();

    @NotNull
    public static LogCallback logCallback = new MpbLogKt$$ExternalSyntheticLambda0();

    @JvmStatic
    public static final void devf(@NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_VERBOSE, null, format, Arrays.copyOf(args, args.length));
    }

    @JvmStatic
    public static final void errorf(@NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_ERROR, null, format, Arrays.copyOf(args, args.length));
    }

    @JvmStatic
    public static final void exceptionf(@NotNull Throwable throwable, @NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_ERROR, throwable, format, Arrays.copyOf(args, args.length));
    }

    @JvmStatic
    public static final void logf(@NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_INFO, null, format, Arrays.copyOf(args, args.length));
    }

    @JvmStatic
    public static final void warnf(@NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_WARN, null, format, Arrays.copyOf(args, args.length));
    }

    public final void initialize(@NotNull LogCallback logCallback2) {
        Intrinsics.checkNotNullParameter(logCallback2, "logCallback");
        logCallback = logCallback2;
    }

    public final void reset() {
        logCallback = new MpbLogKt$$ExternalSyntheticLambda0();
    }

    @JvmStatic
    public static final void warnf(@Nullable Throwable th, @NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        logCallback.onLog(LogCallback.LogLevel.LEVEL_WARN, th, format, Arrays.copyOf(args, args.length));
    }
}
