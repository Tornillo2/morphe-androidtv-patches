package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import j$.util.Objects;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbc implements Callable {
    public final /* synthetic */ PurchasesResponseListener zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ BillingClientImpl zzc;

    public zzbc(BillingClientImpl billingClientImpl, PurchasesResponseListener purchasesResponseListener, String str, boolean z) {
        this.zza = purchasesResponseListener;
        this.zzb = str;
        Objects.requireNonNull(billingClientImpl);
        this.zzc = billingClientImpl;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws Exception {
        BillingClientImpl billingClientImpl = this.zzc;
        if (!billingClientImpl.zzaX(30000L)) {
            zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
            BillingResult billingResult = zzcp.zzj;
            billingClientImpl.zzbd(zzicVar, 9, billingResult);
            this.zza.onQueryPurchasesResponse(billingResult, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk());
            return null;
        }
        String str = this.zzb;
        if (TextUtils.isEmpty(str)) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Please provide a valid product type.");
            zzic zzicVar2 = zzic.EMPTY_PRODUCT_TYPE;
            BillingResult billingResult2 = zzcp.zze;
            billingClientImpl.zzbd(zzicVar2, 9, billingResult2);
            this.zza.onQueryPurchasesResponse(billingResult2, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk());
            return null;
        }
        zzdb zzdbVarZzbb = billingClientImpl.zzbb(str, false, 9);
        List<Purchase> list = zzdbVarZzbb.zza;
        if (list != null) {
            this.zza.onQueryPurchasesResponse(zzdbVarZzbb.zzb, list);
            return null;
        }
        this.zza.onQueryPurchasesResponse(zzdbVarZzbb.zzb, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk());
        return null;
    }
}
