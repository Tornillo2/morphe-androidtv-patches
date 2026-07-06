package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbwh extends zzbvr {

    @Nullable
    public FullScreenContentCallback zza;
    public OnUserEarnedRewardListener zzb;

    public final void zzb(@Nullable FullScreenContentCallback fullScreenContentCallback) {
        this.zza = fullScreenContentCallback;
    }

    public final void zzc(OnUserEarnedRewardListener onUserEarnedRewardListener) {
        this.zzb = onUserEarnedRewardListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzg() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdDismissedFullScreenContent();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzi(zze zzeVar) {
        if (this.zza != null) {
            zzeVar.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzj() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdShowedFullScreenContent();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzk(zzbvm zzbvmVar) {
        OnUserEarnedRewardListener onUserEarnedRewardListener = this.zzb;
        if (onUserEarnedRewardListener != null) {
            onUserEarnedRewardListener.onUserEarnedReward(new zzbvz(zzbvmVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zze() {
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzh(int i) {
    }
}
