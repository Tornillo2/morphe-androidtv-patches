package com.android.billingclient.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.annotation.Nullable;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbd extends ResultReceiver {
    public final /* synthetic */ InAppMessageResponseListener zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbd(BillingClientImpl billingClientImpl, Handler handler, InAppMessageResponseListener inAppMessageResponseListener) {
        super(handler);
        this.zza = inAppMessageResponseListener;
        Objects.requireNonNull(billingClientImpl);
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, @Nullable Bundle bundle) {
        this.zza.onInAppMessageResponse(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzi(bundle, "BillingClient"));
    }
}
