package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcr implements Runnable {
    public final Future zza;
    public final zzcq zzb;

    public zzcr(Future future, zzcq zzcqVar) {
        this.zza = future;
        this.zzb = zzcqVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        Throwable thZze;
        Future future = this.zza;
        if ((future instanceof zzdd) && (thZze = ((zzdd) future).zze()) != null) {
            this.zzb.zza(thZze);
            return;
        }
        try {
            boolean zIsDone = future.isDone();
            boolean z = false;
            Future future2 = future;
            if (!zIsDone) {
                throw new IllegalStateException(zzbh.zza("Future was expected to be done: %s", future));
            }
            while (true) {
                try {
                    obj = future2.get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    future2 = future2;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            this.zzb.zzb(obj);
        } catch (ExecutionException e) {
            this.zzb.zza(e.getCause());
        } catch (Throwable th2) {
            this.zzb.zza(th2);
        }
    }

    public final String toString() {
        zzbb zzbbVarZza = zzbd.zza(this);
        zzbbVarZza.zza(this.zzb);
        return zzbbVarZza.toString();
    }
}
