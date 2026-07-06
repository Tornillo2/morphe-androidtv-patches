package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzy {
    public final Context zza;
    public final PurchasesUpdatedListener zzb;
    public final zzb zzc;
    public final UserChoiceBillingListener zzd;
    public final zzcn zze;
    public final zzx zzf = new zzx(this, true);
    public final zzx zzg = new zzx(this, false);
    public boolean zzh;

    public zzy(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzcu zzcuVar, zzb zzbVar, UserChoiceBillingListener userChoiceBillingListener, zzcn zzcnVar) {
        this.zza = context;
        this.zzb = purchasesUpdatedListener;
        this.zzc = zzbVar;
        this.zzd = userChoiceBillingListener;
        this.zze = zzcnVar;
    }

    @Nullable
    public final PurchasesUpdatedListener zzd() {
        return this.zzb;
    }

    public final void zzf() {
        zzx zzxVar = this.zzf;
        Context context = this.zza;
        zzxVar.zzc(context);
        this.zzg.zzc(context);
    }

    public final void zzg(boolean z) {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter intentFilter2 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intentFilter2.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzh = z;
        zzx zzxVar = this.zzg;
        Context context = this.zza;
        zzxVar.zza(context, intentFilter2);
        if (this.zzh) {
            this.zzf.zzb(context, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST");
        } else {
            this.zzf.zza(context, intentFilter);
        }
    }
}
