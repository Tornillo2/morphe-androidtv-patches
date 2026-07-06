package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
    public final ExecutionList executionList;

    public ListenableFutureTask(Callable<V> callable) {
        super(callable);
        this.executionList = new ExecutionList();
    }

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<>(callable);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor exec) {
        this.executionList.add(listener, exec);
    }

    @Override // java.util.concurrent.FutureTask
    public void done() {
        this.executionList.execute();
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    @CanIgnoreReturnValue
    @ParametricNullness
    public V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        return nanos <= OverflowAvoidingLockSupport.MAX_NANOSECONDS_THRESHOLD ? (V) super.get(j, timeUnit) : (V) super.get(Math.min(nanos, OverflowAvoidingLockSupport.MAX_NANOSECONDS_THRESHOLD), TimeUnit.NANOSECONDS);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, @ParametricNullness V result) {
        return new ListenableFutureTask<>(runnable, result);
    }

    public ListenableFutureTask(Runnable runnable, @ParametricNullness V result) {
        super(runnable, result);
        this.executionList = new ExecutionList();
    }
}
