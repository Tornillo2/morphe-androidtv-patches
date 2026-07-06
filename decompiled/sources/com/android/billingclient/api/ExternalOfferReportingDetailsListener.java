package com.android.billingclient.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@zzj
public interface ExternalOfferReportingDetailsListener {
    void onExternalOfferReportingDetailsResponse(@NonNull BillingResult billingResult, @Nullable ExternalOfferReportingDetails externalOfferReportingDetails);
}
