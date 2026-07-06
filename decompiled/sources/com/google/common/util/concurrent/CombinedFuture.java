package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFuture;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class CombinedFuture<V> extends AggregateFuture<Object, V> {

    @LazyInit
    public CombinedFuture<V>.CombinedFutureInterruptibleTask<?> task;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<ListenableFuture<V>> {
        public final AsyncCallable<V> callable;

        public AsyncCallableInterruptibleTask(AsyncCallable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            callable.getClass();
            this.callable = callable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public ListenableFuture<V> runInterruptibly() throws Exception {
            ListenableFuture<V> listenableFutureCall = this.callable.call();
            Preconditions.checkNotNull(listenableFutureCall, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
            return listenableFutureCall;
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public void setValue(ListenableFuture<V> value) {
            CombinedFuture.this.setFuture(value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<V> {
        public final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            callable.getClass();
            this.callable = callable;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        @ParametricNullness
        public V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public void setValue(@ParametricNullness V value) {
            CombinedFuture.this.set(value);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class CombinedFutureInterruptibleTask<T> extends InterruptibleTask<T> {
        public final Executor listenerExecutor;

        public CombinedFutureInterruptibleTask(Executor listenerExecutor) {
            listenerExecutor.getClass();
            this.listenerExecutor = listenerExecutor;
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblyFailure(Throwable error) {
            CombinedFuture.this.task = null;
            if (error instanceof ExecutionException) {
                CombinedFuture.this.setException(((ExecutionException) error).getCause());
            } else if (error instanceof CancellationException) {
                CombinedFuture.this.cancel(false);
            } else {
                CombinedFuture.this.setException(error);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblySuccess(@ParametricNullness T result) {
            CombinedFuture.this.task = null;
            setValue(result);
        }

        public final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                CombinedFuture.this.setException(e);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return CombinedFuture.this.isDone();
        }

        public abstract void setValue(@ParametricNullness T value);
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, AsyncCallable<V> callable) {
        super(futures, allMustSucceed, false);
        this.task = new AsyncCallableInterruptibleTask(callable, listenerExecutor);
        init();
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void handleAllCompleted() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.execute();
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void interruptTask() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.interruptTask();
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
        super.releaseResources(reason);
        if (reason == AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE) {
            this.task = null;
        }
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, Callable<V> callable) {
        super(futures, allMustSucceed, false);
        this.task = new CallableInterruptibleTask(callable, listenerExecutor);
        init();
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void collectOneValue(int index, Object returnValue) {
    }
}
