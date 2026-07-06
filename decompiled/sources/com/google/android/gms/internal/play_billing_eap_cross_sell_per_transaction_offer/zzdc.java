package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdc extends zzco {
    public zzcx zzd;
    public ScheduledFuture zze;

    public zzdc(zzcx zzcxVar) {
        this.zzd = zzcxVar;
    }

    public static zzcx zzs(zzcx zzcxVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzdc zzdcVar = new zzdc(zzcxVar);
        zzcz zzczVar = new zzcz(zzdcVar);
        zzdcVar.zze = scheduledExecutorService.schedule(zzczVar, 28500L, timeUnit);
        zzcxVar.zzb(zzczVar, zzcn.INSTANCE);
        return zzdcVar;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch
    public final String zzd() {
        zzcx zzcxVar = this.zzd;
        ScheduledFuture scheduledFuture = this.zze;
        if (zzcxVar == null) {
            return null;
        }
        String strM = MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("inputFuture=[", zzcxVar.toString(), "]");
        if (scheduledFuture == null) {
            return strM;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return strM;
        }
        return strM + ", remaining delay=[" + delay + " ms]";
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch
    public final void zzg() {
        zzcx zzcxVar = this.zzd;
        if ((this.valueField instanceof zzch.zza) & (zzcxVar != null)) {
            Object obj = this.valueField;
            zzcxVar.cancel((obj instanceof zzch.zza) && ((zzch.zza) obj).zzc);
        }
        ScheduledFuture scheduledFuture = this.zze;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzd = null;
        this.zze = null;
    }
}
