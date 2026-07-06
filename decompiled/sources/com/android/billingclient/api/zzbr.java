package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbr extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzag {
    public final AlternativeBillingOnlyAvailabilityListener zza;
    public final zzcn zzb;
    public final int zzc;

    public /* synthetic */ zzbr(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener, zzcn zzcnVar, int i, zzbv zzbvVar) {
        this.zza = alternativeBillingOnlyAvailabilityListener;
        this.zzb = zzcnVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzah
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            zzcn zzcnVar = this.zzb;
            zzic zzicVar = zzic.NULL_BUNDLE_FROM_IS_ALTERNATIVE_BILLING_ONLY_AVAILABLE_SERVICE_CALL;
            BillingResult billingResult = zzcp.zzh;
            int i = zzcm.zza;
            zzcnVar.zzb(zzcm.zzb(zzicVar, 14, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onAlternativeBillingOnlyAvailabilityResponse(billingResult);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundle, "BillingClient");
        BillingResult billingResultZza = zzcp.zza(iZzb, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundle, "BillingClient"));
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "isAlternativeBillingOnlyAvailableAsync() failed. Response code: " + iZzb);
            zzcn zzcnVar2 = this.zzb;
            zzic zzicVar2 = zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
            int i2 = zzcm.zza;
            zzcnVar2.zzb(zzcm.zzb(zzicVar2, 14, billingResultZza, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
        }
        this.zza.onAlternativeBillingOnlyAvailabilityResponse(billingResultZza);
    }
}
