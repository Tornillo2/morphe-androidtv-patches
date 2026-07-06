package com.google.android.gms.ads.rewardedinterstitial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbdb;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbwj;
import com.google.android.gms.internal.ads.zzbzi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class RewardedInterstitialAd {
    public static void load(@NonNull final Context context, @NonNull final String str, @NonNull final AdRequest adRequest, @NonNull final RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkNotNull(str, "AdUnitId cannot be null.");
        Preconditions.checkNotNull(adRequest, "AdRequest cannot be null.");
        Preconditions.checkNotNull(rewardedInterstitialAdLoadCallback, "LoadCallback cannot be null.");
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbk.zza(context);
        if (((Boolean) zzbdb.zzl.zze()).booleanValue()) {
            if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjG)).booleanValue()) {
                zzbzi.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.rewardedinterstitial.zza
                    @Override // java.lang.Runnable
                    public final void run() {
                        Context context2 = context;
                        String str2 = str;
                        AdRequest adRequest2 = adRequest;
                        try {
                            new zzbwj(context2, str2).zza(adRequest2.zza, rewardedInterstitialAdLoadCallback);
                        } catch (IllegalStateException e) {
                            zzbsy.zza(context2).zzf(e, "RewardedInterstitialAd.load");
                        }
                    }
                });
                return;
            }
        }
        new zzbwj(context, str).zza(adRequest.zza, rewardedInterstitialAdLoadCallback);
    }

    @NonNull
    public abstract Bundle getAdMetadata();

    @NonNull
    public abstract String getAdUnitId();

    @Nullable
    public abstract FullScreenContentCallback getFullScreenContentCallback();

    @Nullable
    public abstract OnAdMetadataChangedListener getOnAdMetadataChangedListener();

    @Nullable
    public abstract OnPaidEventListener getOnPaidEventListener();

    @NonNull
    public abstract ResponseInfo getResponseInfo();

    @NonNull
    public abstract RewardItem getRewardItem();

    public abstract void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback);

    public abstract void setImmersiveMode(boolean z);

    public abstract void setOnAdMetadataChangedListener(@Nullable OnAdMetadataChangedListener onAdMetadataChangedListener);

    public abstract void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener);

    public abstract void setServerSideVerificationOptions(@NonNull ServerSideVerificationOptions serverSideVerificationOptions);

    public abstract void show(@NonNull Activity activity, @NonNull OnUserEarnedRewardListener onUserEarnedRewardListener);

    public static void load(@NonNull final Context context, @NonNull final String str, @NonNull final AdManagerAdRequest adManagerAdRequest, @NonNull final RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkNotNull(str, "AdUnitId cannot be null.");
        Preconditions.checkNotNull(adManagerAdRequest, "AdManagerAdRequest cannot be null.");
        Preconditions.checkNotNull(rewardedInterstitialAdLoadCallback, "LoadCallback cannot be null.");
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbk.zza(context);
        if (((Boolean) zzbdb.zzl.zze()).booleanValue()) {
            if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjG)).booleanValue()) {
                zzbzi.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.rewardedinterstitial.zzb
                    @Override // java.lang.Runnable
                    public final void run() {
                        Context context2 = context;
                        String str2 = str;
                        AdManagerAdRequest adManagerAdRequest2 = adManagerAdRequest;
                        try {
                            new zzbwj(context2, str2).zza(adManagerAdRequest2.zza, rewardedInterstitialAdLoadCallback);
                        } catch (IllegalStateException e) {
                            zzbsy.zza(context2).zzf(e, "RewardedInterstitialAdManager.load");
                        }
                    }
                });
                return;
            }
        }
        new zzbwj(context, str).zza(adManagerAdRequest.zza, rewardedInterstitialAdLoadCallback);
    }
}
