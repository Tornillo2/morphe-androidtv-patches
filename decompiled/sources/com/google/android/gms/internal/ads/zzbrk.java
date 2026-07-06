package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.nativead.NativeAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbrk extends zzbgd {
    public final NativeAd.OnNativeAdLoadedListener zza;

    public zzbrk(NativeAd.OnNativeAdLoadedListener onNativeAdLoadedListener) {
        this.zza = onNativeAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbge
    public final void zze(zzbgn zzbgnVar) {
        this.zza.onNativeAdLoaded(new zzbrd(zzbgnVar));
    }
}
