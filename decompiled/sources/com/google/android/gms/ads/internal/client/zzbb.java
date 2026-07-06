package com.google.android.gms.ads.internal.client;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.FullScreenContentCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbb extends zzch {

    @Nullable
    public final FullScreenContentCallback zza;

    public zzbb(@Nullable FullScreenContentCallback fullScreenContentCallback) {
        this.zza = fullScreenContentCallback;
    }

    @Override // com.google.android.gms.ads.internal.client.zzci
    public final void zzc() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdDismissedFullScreenContent();
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzci
    public final void zzd(zze zzeVar) {
        if (this.zza != null) {
            zzeVar.zza();
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzci
    public final void zzf() {
        FullScreenContentCallback fullScreenContentCallback = this.zza;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdShowedFullScreenContent();
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzci
    public final void zzb() {
    }

    @Override // com.google.android.gms.ads.internal.client.zzci
    public final void zze() {
    }
}
