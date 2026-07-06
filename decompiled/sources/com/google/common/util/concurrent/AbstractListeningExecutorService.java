package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@CheckReturnValue
@GwtIncompatible
public abstract class AbstractListeningExecutorService extends AbstractExecutorService implements ListeningExecutorService {
    @Override // java.util.concurrent.AbstractExecutorService
    @CanIgnoreReturnValue
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, @ParametricNullness T value) {
        return TrustedListenableFutureTask.create(runnable, value);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    @CanIgnoreReturnValue
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return TrustedListenableFutureTask.create(callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    @CanIgnoreReturnValue
    public ListenableFuture<?> submit(Runnable task) {
        return (ListenableFuture) super.submit(task);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    @CanIgnoreReturnValue
    public <T> ListenableFuture<T> submit(Runnable task, @ParametricNullness T result) {
        return (ListenableFuture) super.submit(task, (Object) result);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    @CanIgnoreReturnValue
    public <T> ListenableFuture<T> submit(Callable<T> task) {
        return (ListenableFuture) super.submit((Callable) task);
    }
}
