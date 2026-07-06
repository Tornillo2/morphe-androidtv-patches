package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface CustomEventInterstitial extends CustomEvent {
    void requestInterstitialAd(@NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @Nullable String str, @NonNull MediationAdRequest mediationAdRequest, @Nullable Bundle bundle);

    void showInterstitial();
}
