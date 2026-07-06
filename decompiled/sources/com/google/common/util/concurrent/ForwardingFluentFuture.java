package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class ForwardingFluentFuture<V> extends FluentFuture<V> {
    public final ListenableFuture<V> delegate;

    public ForwardingFluentFuture(ListenableFuture<V> delegate) {
        delegate.getClass();
        this.delegate = delegate;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.delegate.cancel(mayInterruptIfRunning);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    @ParametricNullness
    public V get() throws ExecutionException, InterruptedException {
        return this.delegate.get();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean isDone() {
        return this.delegate.isDone();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String toString() {
        return this.delegate.toString();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    @ParametricNullness
    public V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.delegate.get(timeout, unit);
    }
}
