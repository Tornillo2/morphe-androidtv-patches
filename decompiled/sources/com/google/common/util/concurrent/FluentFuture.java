package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.Partially;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.InlineMe;
import j$.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use FluentFuture.from(Futures.immediate*Future) or SettableFuture")
@GwtCompatible(emulated = true)
public abstract class FluentFuture<V> extends GwtFluentFutureCatchingSpecialization<V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class TrustedFuture<V> extends FluentFuture<V> implements AbstractFuture.Trusted<V> {
        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public final void addListener(Runnable listener, Executor executor) {
            super.addListener(listener, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final boolean cancel(boolean mayInterruptIfRunning) {
            return super.cancel(mayInterruptIfRunning);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        @ParametricNullness
        public final V get() throws ExecutionException, InterruptedException {
            return blockingGet();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return this.value instanceof AbstractFuture.Cancellation;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        @ParametricNullness
        public final V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
            return blockingGet(timeout, unit);
        }
    }

    public static <V> FluentFuture<V> from(ListenableFuture<V> future) {
        return future instanceof FluentFuture ? (FluentFuture) future : new ForwardingFluentFuture(future);
    }

    public final void addCallback(FutureCallback<? super V> callback, Executor executor) {
        Futures.addCallback(this, callback, executor);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    @J2ktIncompatible
    public final <X extends Throwable> FluentFuture<V> catching(Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        return (FluentFuture) AbstractCatchingFuture.create(this, exceptionType, fallback, executor);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    @J2ktIncompatible
    public final <X extends Throwable> FluentFuture<V> catchingAsync(Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        return (FluentFuture) AbstractCatchingFuture.createAsync(this, exceptionType, fallback, executor);
    }

    public final <T> FluentFuture<T> transform(Function<? super V, T> function, Executor executor) {
        return (FluentFuture) AbstractTransformFuture.create(this, function, executor);
    }

    public final <T> FluentFuture<T> transformAsync(AsyncFunction<? super V, T> function, Executor executor) {
        return (FluentFuture) AbstractTransformFuture.createAsync(this, function, executor);
    }

    @GwtIncompatible
    @IgnoreJRERequirement
    @J2ktIncompatible
    public final FluentFuture<V> withTimeout(Duration timeout, ScheduledExecutorService scheduledExecutor) {
        return withTimeout(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS, scheduledExecutor);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public final FluentFuture<V> withTimeout(long timeout, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        return (FluentFuture) (isDone() ? this : TimeoutFuture.create(this, timeout, unit, scheduledExecutor));
    }

    @InlineMe(replacement = "checkNotNull(future)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <V> FluentFuture<V> from(FluentFuture<V> future) {
        future.getClass();
        return future;
    }
}
