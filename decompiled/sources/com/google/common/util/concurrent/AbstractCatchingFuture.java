package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {

    @LazyInit
    public Class<X> exceptionType;

    @LazyInit
    public F fallback;

    @LazyInit
    public ListenableFuture<? extends V> inputFuture;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        public AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(Object result) {
            setFuture((ListenableFuture) result);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> listenableFutureApply = fallback.apply(cause);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", fallback);
            return listenableFutureApply;
        }

        public void setResult(ListenableFuture<? extends V> result) {
            setFuture(result);
        }
    }

    public AbstractCatchingFuture(ListenableFuture<? extends V> inputFuture, Class<X> exceptionType, F fallback) {
        inputFuture.getClass();
        this.inputFuture = inputFuture;
        exceptionType.getClass();
        this.exceptionType = exceptionType;
        fallback.getClass();
        this.fallback = fallback;
    }

    public static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture((ListenableFuture) input, (Class) exceptionType, fallback);
        input.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    public static <X extends Throwable, V> ListenableFuture<V> createAsync(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture((ListenableFuture) input, (Class) exceptionType, fallback);
        input.addListener(asyncCatchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    @ParametricNullness
    @ForOverride
    public abstract T doFallback(F fallback, X throwable) throws Exception;

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String str;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f = this.fallback;
        String strPendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (cls == null || f == null) {
            if (strPendingToString != null) {
                return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, strPendingToString);
            }
            return null;
        }
        return str + "exceptionType=[" + cls + "], fallback=[" + f + "]";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [F, java.lang.Class<X extends java.lang.Throwable>] */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f = this.fallback;
        if (((f == null) || ((listenableFuture == 0) | (cls == null))) || (this.value instanceof AbstractFuture.Cancellation)) {
            return;
        }
        ?? r3 = (Class<X>) null;
        this.inputFuture = null;
        try {
            th = listenableFuture instanceof InternalFutureFailureAccess ? ((InternalFutureFailureAccess) listenableFuture).tryInternalFastPathGetFailure() : null;
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = new NullPointerException("Future type " + listenableFuture.getClass() + " threw " + e.getClass() + " without a cause");
            }
            th = cause;
        } catch (Throwable th) {
            th = th;
        }
        Object done = th == null ? Futures.getDone(listenableFuture) : null;
        if (th == null) {
            set(done);
            return;
        }
        if (!cls.isInstance(th)) {
            setFuture(listenableFuture);
            return;
        }
        try {
            Object objDoFallback = doFallback(f, th);
            this.exceptionType = null;
            this.fallback = null;
            setResult(objDoFallback);
        } catch (Throwable th2) {
            try {
                Platform.restoreInterruptIfIsInterruptedException(th2);
                setException(th2);
            } finally {
                this.exceptionType = null;
                this.fallback = null;
            }
        }
    }

    @ForOverride
    public abstract void setResult(@ParametricNullness T result);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        public CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        @ParametricNullness
        public Object doFallback(Object fallback, Throwable cause) throws Exception {
            return ((Function) fallback).apply(cause);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(@ParametricNullness V result) {
            set(result);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @ParametricNullness
        public V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return fallback.apply(cause);
        }
    }
}
