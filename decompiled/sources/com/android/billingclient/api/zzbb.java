package com.android.billingclient.api;

import j$.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbb implements ThreadFactory {
    public final ThreadFactory zza;
    public final AtomicInteger zzb;

    public zzbb(BillingClientImpl billingClientImpl) {
        Objects.requireNonNull(billingClientImpl);
        this.zza = Executors.defaultThreadFactory();
        this.zzb = new AtomicInteger(1);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        AtomicInteger atomicInteger = this.zzb;
        Thread threadNewThread = this.zza.newThread(runnable);
        threadNewThread.setName("PlayBillingLibrary-" + atomicInteger.getAndIncrement());
        return threadNewThread;
    }
}
