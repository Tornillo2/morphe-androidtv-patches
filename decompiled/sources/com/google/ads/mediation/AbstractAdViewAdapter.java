package com.google.ads.mediation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbzm;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, com.google.android.gms.ads.mediation.zza {

    @NonNull
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdLoader adLoader;

    @NonNull
    protected AdView mAdView;

    @NonNull
    protected InterstitialAd mInterstitialAd;

    public AdRequest buildAdRequest(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.zza.zzg = birthday;
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.zza.zzj = gender;
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            Iterator<String> it = keywords.iterator();
            while (it.hasNext()) {
                builder.addKeyword(it.next());
            }
        }
        if (mediationAdRequest.isTesting()) {
            zzay.zzb();
            builder.zza(zzbzm.zzy(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.zza.zzm = mediationAdRequest.taggedForChildDirectedTreatment() != 1 ? 0 : 1;
        }
        builder.zza.zzn = mediationAdRequest.isDesignedForFamilies();
        builder.addNetworkExtrasBundle(AdMobAdapter.class, buildExtrasBundle(bundle, bundle2));
        return new AdRequest(builder);
    }

    @NonNull
    public abstract Bundle buildExtrasBundle(@NonNull Bundle bundle, @NonNull Bundle bundle2);

    @NonNull
    public String getAdUnitId(@NonNull Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    @NonNull
    public View getBannerView() {
        return this.mAdView;
    }

    @VisibleForTesting
    public InterstitialAd getInterstitialAd() {
        return this.mInterstitialAd;
    }

    @Override // com.google.android.gms.ads.mediation.zza
    @Nullable
    public zzdq getVideoController() {
        AdView adView = this.mAdView;
        if (adView != null) {
            return adView.zza.zze.zza();
        }
        return null;
    }

    @VisibleForTesting
    public AdLoader.Builder newAdLoader(Context context, String str) {
        return new AdLoader.Builder(context, str);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.destroy();
            this.mAdView = null;
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd = null;
        }
        if (this.adLoader != null) {
            this.adLoader = null;
        }
    }

    @Override // com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener
    public void onImmersiveModeUpdated(boolean z) {
        InterstitialAd interstitialAd = this.mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.setImmersiveMode(z);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.pause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.resume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(@NonNull Context context, @NonNull MediationBannerListener mediationBannerListener, @NonNull Bundle bundle, @NonNull AdSize adSize, @NonNull MediationAdRequest mediationAdRequest, @NonNull Bundle bundle2) {
        AdView adView = new AdView(context);
        this.mAdView = adView;
        adView.setAdSize(new AdSize(adSize.zzb, adSize.zzc));
        this.mAdView.setAdUnitId(getAdUnitId(bundle));
        this.mAdView.setAdListener(new zzb(this, mediationBannerListener));
        this.mAdView.loadAd(buildAdRequest(context, mediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(@NonNull Context context, @NonNull MediationInterstitialListener mediationInterstitialListener, @NonNull Bundle bundle, @NonNull MediationAdRequest mediationAdRequest, @NonNull Bundle bundle2) {
        InterstitialAd.load(context, getAdUnitId(bundle), buildAdRequest(context, mediationAdRequest, bundle2, bundle), new zzc(this, mediationInterstitialListener));
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(@NonNull Context context, @NonNull MediationNativeListener mediationNativeListener, @NonNull Bundle bundle, @NonNull NativeMediationAdRequest nativeMediationAdRequest, @NonNull Bundle bundle2) {
        zze zzeVar = new zze(this, mediationNativeListener);
        AdLoader.Builder builderWithAdListener = newAdLoader(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(zzeVar);
        builderWithAdListener.withNativeAdOptions(nativeMediationAdRequest.getNativeAdOptions());
        builderWithAdListener.withNativeAdOptions(nativeMediationAdRequest.getNativeAdRequestOptions());
        if (nativeMediationAdRequest.isUnifiedNativeAdRequested()) {
            builderWithAdListener.forUnifiedNativeAd(zzeVar);
        }
        if (nativeMediationAdRequest.zzb()) {
            for (String str : nativeMediationAdRequest.zza().keySet()) {
                builderWithAdListener.forCustomTemplateAd(str, zzeVar, true != ((Boolean) nativeMediationAdRequest.zza().get(str)).booleanValue() ? null : zzeVar);
            }
        }
        AdLoader adLoaderBuild = builderWithAdListener.build();
        this.adLoader = adLoaderBuild;
        adLoaderBuild.loadAd(buildAdRequest(context, nativeMediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        InterstitialAd interstitialAd = this.mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.show(null);
        }
    }
}
