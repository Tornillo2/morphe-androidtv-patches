package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.livingroom.mediapipelinebackend.MpbLog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class OneTimeMpbLog {
    public boolean emitted;

    public static /* synthetic */ void e$default(OneTimeMpbLog oneTimeMpbLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        oneTimeMpbLog.e(str, th);
    }

    public static /* synthetic */ void i$default(OneTimeMpbLog oneTimeMpbLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        oneTimeMpbLog.i(str, th);
    }

    public static /* synthetic */ void t$default(OneTimeMpbLog oneTimeMpbLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        oneTimeMpbLog.t(str, th);
    }

    public static /* synthetic */ void v$default(OneTimeMpbLog oneTimeMpbLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        oneTimeMpbLog.v(str, th);
    }

    public static /* synthetic */ void w$default(OneTimeMpbLog oneTimeMpbLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        oneTimeMpbLog.w(str, th);
    }

    public final void e(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        printOnce(MpbLog.Level.ERROR, message, th);
    }

    public final void i(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        printOnce(MpbLog.Level.INFO, message, th);
    }

    public final void printOnce(MpbLog.Level level, String str, Throwable th) {
        if (this.emitted) {
            return;
        }
        MpbLog.print(level, str, th);
        this.emitted = true;
    }

    public final void t(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        printOnce(MpbLog.Level.TRACE, message, th);
    }

    public final void v(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        printOnce(MpbLog.Level.VERBOSE, message, th);
    }

    public final void w(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        printOnce(MpbLog.Level.WARN, message, th);
    }
}
