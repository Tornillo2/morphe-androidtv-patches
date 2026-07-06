package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcs extends zzcu {
    public static zzcx zza(Object obj) {
        return new zzcv(obj);
    }

    public static zzcx zzb(zzcx zzcxVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return zzcxVar.isDone() ? zzcxVar : zzdc.zzs(zzcxVar, 28500L, timeUnit, scheduledExecutorService);
    }

    public static void zzc(zzcx zzcxVar, zzcq zzcqVar, Executor executor) {
        zzcxVar.zzb(new zzcr(zzcxVar, zzcqVar), executor);
    }
}
