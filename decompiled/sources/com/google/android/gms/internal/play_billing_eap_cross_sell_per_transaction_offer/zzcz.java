package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcz implements Runnable {
    public zzdc zza;

    public zzcz(zzdc zzdcVar) {
        this.zza = zzdcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcx zzcxVar;
        zzdc zzdcVar = this.zza;
        if (zzdcVar == null || (zzcxVar = zzdcVar.zzd) == null) {
            return;
        }
        this.zza = null;
        if (zzcxVar.isDone()) {
            zzdcVar.zzj(zzcxVar);
            return;
        }
        try {
            ScheduledFuture scheduledFuture = zzdcVar.zze;
            zzdcVar.zze = null;
            String str = "Timed out";
            if (scheduledFuture != null) {
                try {
                    long jAbs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                    if (jAbs > 10) {
                        str = "Timed out (timeout delayed by " + jAbs + " ms after scheduled time)";
                    }
                } catch (Throwable th) {
                    zzdcVar.zzi(new zzda(str));
                    throw th;
                }
            }
            zzdcVar.zzi(new zzda(str + ": " + zzcxVar.toString()));
        } finally {
            zzcxVar.cancel(true);
        }
    }
}
