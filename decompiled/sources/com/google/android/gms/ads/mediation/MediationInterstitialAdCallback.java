package com.google.android.gms.ads.mediation;

import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdError;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface MediationInterstitialAdCallback extends MediationAdCallback {
    void onAdFailedToShow(@NonNull AdError adError);

    @Deprecated
    void onAdFailedToShow(@NonNull String str);

    void onAdLeftApplication();
}
