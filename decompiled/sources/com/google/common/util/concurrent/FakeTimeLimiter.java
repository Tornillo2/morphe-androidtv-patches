package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class FakeTimeLimiter implements TimeLimiter {
    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException {
        return (T) callWithTimeout(callable, j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException {
        callable.getClass();
        timeoutUnit.getClass();
        try {
            return callable.call();
        } catch (Error e) {
            throw new ExecutionError((Throwable) e);
        } catch (RuntimeException e2) {
            throw new UncheckedExecutionException(e2);
        } catch (Exception e3) {
            Platform.restoreInterruptIfIsInterruptedException(e3);
            throw new ExecutionException(e3);
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    public <T> T newProxy(T target, Class<T> interfaceType, long timeoutDuration, TimeUnit timeoutUnit) {
        target.getClass();
        interfaceType.getClass();
        timeoutUnit.getClass();
        return target;
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
        runWithTimeout(runnable, timeoutDuration, timeoutUnit);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
        runnable.getClass();
        timeoutUnit.getClass();
        try {
            runnable.run();
        } catch (Error e) {
            throw new ExecutionError((Throwable) e);
        } catch (Exception e2) {
            throw new UncheckedExecutionException(e2);
        }
    }
}
