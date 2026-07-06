package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import org.json.JSONException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbp extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzac {
    public final BillingConfigResponseListener zza;
    public final zzcn zzb;
    public final int zzc;

    public /* synthetic */ zzbp(BillingConfigResponseListener billingConfigResponseListener, zzcn zzcnVar, int i, zzbv zzbvVar) {
        this.zza = billingConfigResponseListener;
        this.zzb = zzcnVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzad
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            zzcn zzcnVar = this.zzb;
            zzic zzicVar = zzic.NULL_BUNDLE_FROM_GET_BILLING_CONFIG_SERVICE_CALL;
            BillingResult billingResult = zzcp.zzh;
            int i = zzcm.zza;
            zzcnVar.zzb(zzcm.zzb(zzicVar, 13, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onBillingConfigResponse(billingResult, null);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundle, "BillingClient");
        String strZzj = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundle, "BillingClient");
        BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
        builderNewBuilder.zza = iZzb;
        builderNewBuilder.zzc = strZzj;
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "getBillingConfig() failed. Response code: " + iZzb);
            BillingResult billingResultBuild = builderNewBuilder.build();
            zzcn zzcnVar2 = this.zzb;
            zzic zzicVar2 = zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
            int i2 = zzcm.zza;
            zzcnVar2.zzb(zzcm.zzb(zzicVar2, 13, billingResultBuild, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onBillingConfigResponse(billingResultBuild, null);
            return;
        }
        if (!bundle.containsKey("BILLING_CONFIG")) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "getBillingConfig() returned a bundle with neither an error nor a billing config response");
            builderNewBuilder.zza = 6;
            BillingResult billingResultBuild2 = builderNewBuilder.build();
            zzcn zzcnVar3 = this.zzb;
            zzic zzicVar3 = zzic.MISSING_BILLING_CONFIG_IN_GET_BILLING_CONFIG_RESPONSE;
            int i3 = zzcm.zza;
            zzcnVar3.zzb(zzcm.zzb(zzicVar3, 13, billingResultBuild2, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onBillingConfigResponse(billingResultBuild2, null);
            return;
        }
        try {
            this.zza.onBillingConfigResponse(builderNewBuilder.build(), new BillingConfig(bundle.getString("BILLING_CONFIG")));
        } catch (JSONException e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Got a JSON exception trying to decode BillingConfig. \n Exception: ", e);
            zzcn zzcnVar4 = this.zzb;
            zzic zzicVar4 = zzic.ERROR_DECODING_BILLING_CONFIG_DATA;
            BillingResult billingResult2 = zzcp.zzh;
            int i4 = zzcm.zza;
            zzcnVar4.zzb(zzcm.zzb(zzicVar4, 13, billingResult2, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzc);
            this.zza.onBillingConfigResponse(billingResult2, null);
        }
    }
}
