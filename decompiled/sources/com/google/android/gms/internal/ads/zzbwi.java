package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbwi extends zzbvv {
    public final RewardedInterstitialAdLoadCallback zza;
    public final zzbwj zzb;

    public zzbwi(RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback, zzbwj zzbwjVar) {
        this.zza = rewardedInterstitialAdLoadCallback;
        this.zzb = zzbwjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zzf(zze zzeVar) {
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zza;
        if (rewardedInterstitialAdLoadCallback != null) {
            rewardedInterstitialAdLoadCallback.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zzg() {
        zzbwj zzbwjVar;
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zza;
        if (rewardedInterstitialAdLoadCallback == null || (zzbwjVar = this.zzb) == null) {
            return;
        }
        rewardedInterstitialAdLoadCallback.onAdLoaded(zzbwjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zze(int i) {
    }
}
