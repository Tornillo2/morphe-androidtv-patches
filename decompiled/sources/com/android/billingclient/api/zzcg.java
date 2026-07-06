package com.android.billingclient.api;

import androidx.core.util.Consumer;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import j$.util.Objects;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcg implements com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcq {
    public final /* synthetic */ Consumer zza;
    public final /* synthetic */ Runnable zzb;
    public final /* synthetic */ zzck zzc;
    public final /* synthetic */ int zzd;

    public zzcg(zzck zzckVar, int i, Consumer consumer, Runnable runnable) {
        this.zzd = i;
        this.zza = consumer;
        this.zzb = runnable;
        Objects.requireNonNull(zzckVar);
        this.zzc = zzckVar;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcq
    public final void zza(Throwable th) {
        if (th instanceof TimeoutException) {
            this.zzc.zzaF(zzic.BILLING_OVERRIDE_SERVICE_CALL_TIMEOUT, 28, zzcp.zzF);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", th);
        } else {
            this.zzc.zzaF(zzic.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, zzcp.zzF);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", th);
        }
        this.zzb.run();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcq
    public final void zzb(Object obj) {
        Integer num = (Integer) obj;
        int iIntValue = num.intValue();
        zzck zzckVar = this.zzc;
        if (iIntValue <= 0) {
            this.zzb.run();
        } else {
            this.zza.accept(zzckVar.zzaD(this.zzd, num.intValue()));
        }
    }
}
