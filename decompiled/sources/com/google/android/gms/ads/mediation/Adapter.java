package com.google.android.gms.ads.mediation;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.MobileAds;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Adapter implements MediationExtrasReceiver {
    @NonNull
    public abstract com.google.android.gms.ads.VersionInfo getSDKVersionInfo();

    @NonNull
    public abstract com.google.android.gms.ads.VersionInfo getVersionInfo();

    public abstract void initialize(@NonNull Context context, @NonNull InitializationCompleteCallback initializationCompleteCallback, @NonNull List<MediationConfiguration> list);

    public void loadAppOpenAd(@NonNull MediationAppOpenAdConfiguration mediationAppOpenAdConfiguration, @NonNull MediationAdLoadCallback<MediationAppOpenAd, MediationAppOpenAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support app open ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadBannerAd(@NonNull MediationBannerAdConfiguration mediationBannerAdConfiguration, @NonNull MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support banner ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadInterscrollerAd(@NonNull MediationBannerAdConfiguration mediationBannerAdConfiguration, @NonNull MediationAdLoadCallback<MediationInterscrollerAd, MediationBannerAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support interscroller ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadInterstitialAd(@NonNull MediationInterstitialAdConfiguration mediationInterstitialAdConfiguration, @NonNull MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support interstitial ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadNativeAd(@NonNull MediationNativeAdConfiguration mediationNativeAdConfiguration, @NonNull MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support native ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadRewardedAd(@NonNull MediationRewardedAdConfiguration mediationRewardedAdConfiguration, @NonNull MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support rewarded ads."), MobileAds.ERROR_DOMAIN, null));
    }

    public void loadRewardedInterstitialAd(@NonNull MediationRewardedAdConfiguration mediationRewardedAdConfiguration, @NonNull MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(new AdError(7, getClass().getSimpleName().concat(" does not support rewarded interstitial ads."), MobileAds.ERROR_DOMAIN, null));
    }
}
