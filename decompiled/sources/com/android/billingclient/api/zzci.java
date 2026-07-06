package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzci implements ServiceConnection {
    public final /* synthetic */ zzck zza;

    public /* synthetic */ zzci(zzck zzckVar, zzcj zzcjVar) {
        Objects.requireNonNull(zzckVar);
        this.zza = zzckVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Billing Override Service connected.");
        zzck zzckVar = this.zza;
        zzckVar.zzc = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzat.zzc(iBinder);
        zzckVar.zzb = 2;
        zzckVar.zzaG(26);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "Billing Override Service disconnected.");
        zzck zzckVar = this.zza;
        zzckVar.zzc = null;
        zzckVar.zzb = 0;
    }
}
