package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import org.json.JSONException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbn extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzy {
    public final ExternalOfferReportingDetailsListener zza;
    public final zzcn zzb;
    public final int zzc;

    public /* synthetic */ zzbn(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, zzcn zzcnVar, int i, zzbv zzbvVar) {
        this.zza = externalOfferReportingDetailsListener;
        this.zzb = zzcnVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzz
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            zzcn zzcnVar = this.zzb;
            zzic zzicVar = zzic.NULL_BUNDLE_FROM_CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS_SERVICE_CALL;
            BillingResult billingResult = zzcp.zzh;
            int i = zzcm.zza;
            zzcnVar.zzb(zzcm.zzb(zzicVar, 24, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(billingResult, null);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundle, "BillingClient");
        BillingResult billingResultZza = zzcp.zza(iZzb, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundle, "BillingClient"));
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "createExternalOfferReportingDetailsAsync() failed. Response code: " + iZzb);
            zzcn zzcnVar2 = this.zzb;
            zzic zzicVar2 = zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
            int i2 = zzcm.zza;
            zzcnVar2.zzb(zzcm.zzb(zzicVar2, 24, billingResultZza, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(billingResultZza, null);
            return;
        }
        try {
            this.zza.onExternalOfferReportingDetailsResponse(billingResultZza, new ExternalOfferReportingDetails(bundle.getString("CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS")));
        } catch (JSONException e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error when parsing invalid external offer reporting details. \n Exception: ", e);
            zzcn zzcnVar3 = this.zzb;
            zzic zzicVar3 = zzic.ERROR_DECODING_EXTERNAL_OFFER_REPORTING_DETAILS;
            BillingResult billingResult2 = zzcp.zzh;
            int i3 = zzcm.zza;
            zzcnVar3.zzb(zzcm.zzb(zzicVar3, 24, billingResult2, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(billingResult2, null);
        }
    }
}
