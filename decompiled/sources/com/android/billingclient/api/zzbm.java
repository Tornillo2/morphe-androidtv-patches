package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import org.json.JSONException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbm extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzw {
    public final AlternativeBillingOnlyReportingDetailsListener zza;
    public final zzcn zzb;
    public final int zzc;

    public /* synthetic */ zzbm(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener, zzcn zzcnVar, int i, zzbv zzbvVar) {
        this.zza = alternativeBillingOnlyReportingDetailsListener;
        this.zzb = zzcnVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzx
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            zzcn zzcnVar = this.zzb;
            zzic zzicVar = zzic.NULL_BUNDLE_FROM_CREATE_ALTERNATIVE_BILLING_ONLY_TOKEN_SERVICE_CALL;
            BillingResult billingResult = zzcp.zzh;
            int i = zzcm.zza;
            zzcnVar.zzb(zzcm.zzb(zzicVar, 15, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(billingResult, null);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundle, "BillingClient");
        BillingResult billingResultZza = zzcp.zza(iZzb, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundle, "BillingClient"));
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "createAlternativeBillingOnlyReportingDetailsAsync() failed. Response code: " + iZzb);
            zzcn zzcnVar2 = this.zzb;
            zzic zzicVar2 = zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
            int i2 = zzcm.zza;
            zzcnVar2.zzb(zzcm.zzb(zzicVar2, 15, billingResultZza, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(billingResultZza, null);
            return;
        }
        try {
            this.zza.onAlternativeBillingOnlyTokenResponse(billingResultZza, new AlternativeBillingOnlyReportingDetails(bundle.getString("CREATE_ALTERNATIVE_BILLING_ONLY_REPORTING_DETAILS")));
        } catch (JSONException e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error when parsing invalid alternative billing only reporting details. \n Exception: ", e);
            zzcn zzcnVar3 = this.zzb;
            zzic zzicVar3 = zzic.ERROR_DECODING_ALTERNATIVE_BILLING_ONLY_REPORTING_DETAILS;
            BillingResult billingResult2 = zzcp.zzh;
            int i3 = zzcm.zza;
            zzcnVar3.zzb(zzcm.zzb(zzicVar3, 15, billingResult2, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(billingResult2, null);
        }
    }
}
