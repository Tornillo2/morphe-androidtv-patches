package com.amazon.primevideo.nativebilling;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesUpdatedListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class BillingClientStatusProvider$$ExternalSyntheticLambda0 implements PurchasesUpdatedListener {
    @Override // com.android.billingclient.api.PurchasesUpdatedListener
    public final void onPurchasesUpdated(BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "<unused var>");
    }
}
