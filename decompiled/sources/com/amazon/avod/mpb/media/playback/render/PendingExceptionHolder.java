package com.amazon.avod.mpb.media.playback.render;

import android.os.SystemClock;
import java.lang.Exception;
import java.lang.reflect.InvocationTargetException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PendingExceptionHolder<T extends Exception> {

    @Nullable
    public T pendingException;
    public long throwDeadlineMs;
    public final long throwDelayMs;

    public PendingExceptionHolder(long j) {
        this.throwDelayMs = j;
    }

    public final void clear() {
        this.pendingException = null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: T extends java.lang.Exception */
    public final void throwExceptionIfDeadlineIsReached(@NotNull T exception) throws IllegalAccessException, T, InvocationTargetException {
        Intrinsics.checkNotNullParameter(exception, "exception");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        T t = this.pendingException;
        if (t == null) {
            this.pendingException = exception;
            this.throwDeadlineMs = this.throwDelayMs + jElapsedRealtime;
            t = exception;
        }
        if (jElapsedRealtime >= this.throwDeadlineMs) {
            if (t != exception) {
                ExceptionsKt__ExceptionsKt.addSuppressed(t, exception);
            }
            this.pendingException = null;
            throw t;
        }
    }
}
