package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {

    /* JADX INFO: renamed from: com.google.common.util.concurrent.ListeningScheduledExecutorService$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);

    @Override // java.util.concurrent.ScheduledExecutorService
    /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);
}
