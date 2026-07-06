package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use FakeTimeLimiter")
@J2ktIncompatible
@GwtIncompatible
public interface TimeLimiter {
    @CanIgnoreReturnValue
    @ParametricNullness
    <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException, TimeoutException;

    @CanIgnoreReturnValue
    @ParametricNullness
    <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException, InterruptedException, TimeoutException;

    <T> T newProxy(T target, Class<T> interfaceType, long timeoutDuration, TimeUnit timeoutUnit);

    void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException;

    void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws InterruptedException, TimeoutException;
}
