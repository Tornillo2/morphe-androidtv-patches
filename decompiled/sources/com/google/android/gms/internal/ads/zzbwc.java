package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbwc extends zzbvv {
    public final RewardedAdLoadCallback zza;
    public final RewardedAd zzb;

    public zzbwc(RewardedAdLoadCallback rewardedAdLoadCallback, RewardedAd rewardedAd) {
        this.zza = rewardedAdLoadCallback;
        this.zzb = rewardedAd;
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zzf(zze zzeVar) {
        if (this.zza != null) {
            this.zza.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zzg() {
        RewardedAdLoadCallback rewardedAdLoadCallback = this.zza;
        if (rewardedAdLoadCallback != null) {
            rewardedAdLoadCallback.onAdLoaded(this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvw
    public final void zze(int i) {
    }
}
