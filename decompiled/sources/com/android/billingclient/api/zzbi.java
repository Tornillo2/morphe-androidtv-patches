package com.android.billingclient.api;

import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbi implements BillingClientStateListener {
    public final /* synthetic */ com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zza;
    public final /* synthetic */ BillingClientImpl zzb;

    public zzbi(BillingClientImpl billingClientImpl, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zzpVar) {
        this.zza = zzpVar;
        Objects.requireNonNull(billingClientImpl);
        this.zzb = billingClientImpl;
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingServiceDisconnected() {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Reconnection attempt failed.");
        try {
            this.zza.zzb(zzcp.zzj);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception setting completer.", th);
        }
        BillingClientImpl billingClientImpl = this.zzb;
        if (billingClientImpl.zzG != null) {
            billingClientImpl.zzag(new Runnable() { // from class: com.android.billingclient.api.zzbg
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        this.zza.zzb.zzG.onBillingServiceDisconnected();
                    } catch (Throwable th2) {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception calling onBillingServiceDisconnected.", th2);
                    }
                }
            });
        }
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingSetupFinished(final BillingResult billingResult) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Reconnection finished with result: " + billingResult.zza);
        try {
            this.zza.zzb(billingResult);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception setting completer.", th);
        }
        BillingClientImpl billingClientImpl = this.zzb;
        if (billingClientImpl.zzG != null) {
            billingClientImpl.zzag(new Runnable() { // from class: com.android.billingclient.api.zzbh
                @Override // java.lang.Runnable
                public final void run() {
                    zzbi zzbiVar = this.zza;
                    try {
                        zzbiVar.zzb.zzG.onBillingSetupFinished(billingResult);
                    } catch (Throwable th2) {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception calling onBillingSetupFinished.", th2);
                    }
                }
            });
        }
    }
}
