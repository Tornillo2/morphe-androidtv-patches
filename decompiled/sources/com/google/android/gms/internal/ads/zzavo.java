package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzavo extends zzavx {

    @Nullable
    public FullScreenContentCallback zza;

    @Override // com.google.android.gms.internal.ads.zzavy
    public final void zzc() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdDismissedFullScreenContent();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzavy
    public final void zzd(zze zzeVar) {
        if (this.zza != null) {
            zzeVar.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzavy
    public final void zzf() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdShowedFullScreenContent();
        }
    }

    public final void zzg(@Nullable FullScreenContentCallback fullScreenContentCallback) {
        this.zza = fullScreenContentCallback;
    }

    @Override // com.google.android.gms.internal.ads.zzavy
    public final void zzb() {
    }

    @Override // com.google.android.gms.internal.ads.zzavy
    public final void zze() {
    }
}
