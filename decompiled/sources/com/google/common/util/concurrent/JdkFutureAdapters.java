package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.util.concurrent.JdkFutureAdapters;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class JdkFutureAdapters {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ListenableFutureAdapter<V> extends ForwardingFuture<V> implements ListenableFuture<V> {
        public static final Executor defaultAdapterExecutor;
        public static final ThreadFactory threadFactory;
        public final Executor adapterExecutor;
        public final Future<V> delegate;
        public final ExecutionList executionList;
        public final AtomicBoolean hasListeners;

        /* JADX INFO: renamed from: $r8$lambda$aZrPkhpvsxbbUm-nZPUyhuTiYXI, reason: not valid java name */
        public static /* synthetic */ void m562$r8$lambda$aZrPkhpvsxbbUmnZPUyhuTiYXI(ListenableFutureAdapter listenableFutureAdapter) {
            listenableFutureAdapter.getClass();
            try {
                Uninterruptibles.getUninterruptibly(listenableFutureAdapter.delegate);
            } catch (Throwable unused) {
            }
            listenableFutureAdapter.executionList.execute();
        }

        static {
            ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
            threadFactoryBuilder.daemon = Boolean.TRUE;
            threadFactoryBuilder.setNameFormat("ListenableFutureAdapter-thread-%d");
            ThreadFactory threadFactoryDoBuild = ThreadFactoryBuilder.doBuild(threadFactoryBuilder);
            threadFactory = threadFactoryDoBuild;
            defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactoryDoBuild);
        }

        public ListenableFutureAdapter(Future<V> delegate, Executor adapterExecutor) {
            this.executionList = new ExecutionList();
            this.hasListeners = new AtomicBoolean(false);
            delegate.getClass();
            this.delegate = delegate;
            adapterExecutor.getClass();
            this.adapterExecutor = adapterExecutor;
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable listener, Executor exec) {
            this.executionList.add(listener, exec);
            if (this.hasListeners.compareAndSet(false, true)) {
                if (this.delegate.isDone()) {
                    this.executionList.execute();
                } else {
                    this.adapterExecutor.execute(new Runnable() { // from class: com.google.common.util.concurrent.JdkFutureAdapters$ListenableFutureAdapter$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            JdkFutureAdapters.ListenableFutureAdapter.m562$r8$lambda$aZrPkhpvsxbbUmnZPUyhuTiYXI(this.f$0);
                        }
                    });
                }
            }
        }

        @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
        public Future<V> delegate() {
            return this.delegate;
        }

        public ListenableFutureAdapter(Future<V> delegate) {
            this(delegate, defaultAdapterExecutor);
        }
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
        return future instanceof ListenableFuture ? (ListenableFuture) future : new ListenableFutureAdapter(future);
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future, Executor executor) {
        executor.getClass();
        if (future instanceof ListenableFuture) {
            return (ListenableFuture) future;
        }
        return new ListenableFutureAdapter(future, executor);
    }
}
