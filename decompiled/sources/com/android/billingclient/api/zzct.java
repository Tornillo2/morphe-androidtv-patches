package com.android.billingclient.api;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportFactoryImpl;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjg;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzct {
    public boolean zza;
    public Transport zzb;

    public zzct(Context context) {
        try {
            TransportRuntime.initialize(context);
            this.zzb = ((TransportFactoryImpl) TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE)).getTransport("PLAY_BILLING_LIBRARY", zzjg.class, new Encoding("proto"), new zzcs());
        } catch (Throwable unused) {
            this.zza = true;
        }
    }

    public final void zza(zzjg zzjgVar) {
        if (this.zza) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingLogger", "Skipping logging since initialization failed.");
            return;
        }
        try {
            this.zzb.send(Event.ofData(zzjgVar));
        } catch (Throwable unused) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingLogger", "logging failed.");
        }
    }
}
