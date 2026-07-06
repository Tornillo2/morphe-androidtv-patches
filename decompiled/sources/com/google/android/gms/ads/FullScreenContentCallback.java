package com.google.android.gms.ads;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class FullScreenContentCallback {
    public static final int ERROR_CODE_AD_REUSED = 1;
    public static final int ERROR_CODE_APP_NOT_FOREGROUND = 3;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_MEDIATION_SHOW_ERROR = 4;
    public static final int ERROR_CODE_NOT_READY = 2;

    public void onAdClicked() {
    }

    public void onAdDismissedFullScreenContent() {
    }

    public void onAdImpression() {
    }

    public void onAdShowedFullScreenContent() {
    }

    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
    }
}
