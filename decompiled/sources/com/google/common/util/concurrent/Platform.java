package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static <V> V get(AbstractFuture<V> future) throws ExecutionException, InterruptedException {
        return future.blockingGet();
    }

    public static void interruptCurrentThread() {
        Thread.currentThread().interrupt();
    }

    public static boolean isInstanceOfThrowableClass(Throwable t, Class<? extends Throwable> expectedClass) {
        return expectedClass.isInstance(t);
    }

    public static void restoreInterruptIfIsInterruptedException(Throwable t) {
        t.getClass();
        if (t instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public static void rethrowIfErrorOtherThanStackOverflow(Throwable t) {
        t.getClass();
        if ((t instanceof Error) && !(t instanceof StackOverflowError)) {
            throw ((Error) t);
        }
    }

    public static <V> V get(AbstractFuture<V> future, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        return future.blockingGet(timeout, unit);
    }
}
