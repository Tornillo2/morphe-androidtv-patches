package com.android.billingclient.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.annotation.Nullable;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbf extends ResultReceiver {
    public final /* synthetic */ ExternalOfferInformationDialogListener zza;
    public final /* synthetic */ BillingClientImpl zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbf(BillingClientImpl billingClientImpl, Handler handler, ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        super(handler);
        this.zza = externalOfferInformationDialogListener;
        Objects.requireNonNull(billingClientImpl);
        this.zzb = billingClientImpl;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, @Nullable Bundle bundle) {
        BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
        builderNewBuilder.zza = i;
        if (i != 0) {
            if (bundle == null) {
                this.zzb.zzaK(this.zza, zzcp.zzh, zzic.NULL_BUNDLE_IN_EXTERNAL_PAYMENT_INFORMATION_DIALOG_RECEIVER, null);
                return;
            }
            builderNewBuilder.zzc = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundle, "BillingClient");
            int i2 = bundle.getInt("INTERNAL_LOG_ERROR_REASON");
            BillingClientImpl billingClientImpl = this.zzb;
            zzic zzicVarZzb = i2 != 0 ? zzic.zzb(i2) : zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
            BillingResult billingResultBuild = builderNewBuilder.build();
            String string = bundle.getString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS");
            int i3 = zzcm.zza;
            billingClientImpl.zzaO(zzcm.zzb(zzicVarZzb, 25, billingResultBuild, string, zzij.BROADCAST_ACTION_UNSPECIFIED));
        }
        this.zza.onExternalOfferInformationDialogResponse(builderNewBuilder.build());
    }
}
