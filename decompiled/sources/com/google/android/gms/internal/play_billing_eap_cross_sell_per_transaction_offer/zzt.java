package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzt implements zzcx {
    public final WeakReference zza;
    public final zzo zzb = new zzs(this);

    public zzt(zzp zzpVar) {
        this.zza = new WeakReference(zzpVar);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zzp zzpVar = (zzp) this.zza.get();
        boolean zCancel = this.zzb.cancel(z);
        if (!zCancel || zzpVar == null) {
            return zCancel;
        }
        zzpVar.zza();
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        return this.zzb.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzb.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.zzb.isDone();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final boolean zza(Object obj) {
        return this.zzb.zzd(obj);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx
    public final void zzb(@NonNull Runnable runnable, @NonNull Executor executor) {
        this.zzb.zzb(runnable, executor);
    }

    public final boolean zzc(Throwable th) {
        zzg zzgVar = new zzg(th);
        zzd zzdVar = zzo.zzb;
        zzo zzoVar = this.zzb;
        if (!zzdVar.zzd(zzoVar, null, zzgVar)) {
            return false;
        }
        zzo.zzc(zzoVar);
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.zzb.get(j, timeUnit);
    }
}
