package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {

    @LazyInit
    public F function;

    @LazyInit
    public ListenableFuture<? extends I> inputFuture;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        public AsyncTransformFuture(ListenableFuture<? extends I> inputFuture, AsyncFunction<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(Object result) {
            setFuture((ListenableFuture) result);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> function, @ParametricNullness I input) throws Exception {
            ListenableFuture<? extends O> listenableFutureApply = function.apply(input);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", function);
            return listenableFutureApply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setResult(ListenableFuture<? extends O> result) {
            setFuture(result);
        }
    }

    public AbstractTransformFuture(ListenableFuture<? extends I> inputFuture, F function) {
        inputFuture.getClass();
        this.inputFuture = inputFuture;
        function.getClass();
        this.function = function;
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, Function<? super I, ? extends O> function, Executor executor) {
        TransformFuture transformFuture = new TransformFuture((ListenableFuture) input, function);
        input.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }

    public static <I, O> ListenableFuture<O> createAsync(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture((ListenableFuture) input, function);
        input.addListener(asyncTransformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncTransformFuture));
        return asyncTransformFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    @ParametricNullness
    @ForOverride
    public abstract T doTransform(F function, @ParametricNullness I result) throws Exception;

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String str;
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        String strPendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (f == null) {
            if (strPendingToString != null) {
                return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, strPendingToString);
            }
            return null;
        }
        return str + "function=[" + f + "]";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        if (((this.value instanceof AbstractFuture.Cancellation) | (listenableFuture == null)) || (f == null)) {
            return;
        }
        this.inputFuture = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                Object objDoTransform = doTransform(f, Futures.getDone(listenableFuture));
                this.function = null;
                setResult(objDoTransform);
            } catch (Throwable th) {
                try {
                    Platform.restoreInterruptIfIsInterruptedException(th);
                    setException(th);
                } finally {
                    this.function = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (ExecutionException e2) {
            setException(e2.getCause());
        } catch (Exception e3) {
            setException(e3);
        }
    }

    @ForOverride
    public abstract void setResult(@ParametricNullness T result);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        public TransformFuture(ListenableFuture<? extends I> inputFuture, Function<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        @ParametricNullness
        public Object doTransform(Object function, @ParametricNullness Object input) throws Exception {
            return ((Function) function).apply(input);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(@ParametricNullness O result) {
            set(result);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @ParametricNullness
        public O doTransform(Function<? super I, ? extends O> function, @ParametricNullness I input) {
            return function.apply(input);
        }
    }
}
