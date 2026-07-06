package com.google.ads.mediation;

import android.view.View;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.zzg;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zza extends UnifiedNativeAdMapper {
    public final UnifiedNativeAd zza;

    public zza(UnifiedNativeAd unifiedNativeAd) {
        this.zza = unifiedNativeAd;
        super.zza = unifiedNativeAd.getHeadline();
        this.zzb = unifiedNativeAd.getImages();
        this.zzc = unifiedNativeAd.getBody();
        this.zzd = unifiedNativeAd.getIcon();
        this.zze = unifiedNativeAd.getCallToAction();
        this.zzf = unifiedNativeAd.getAdvertiser();
        this.zzg = unifiedNativeAd.getStarRating();
        this.zzh = unifiedNativeAd.getStore();
        this.zzi = unifiedNativeAd.getPrice();
        this.zzn = unifiedNativeAd.zza();
        this.zzp = true;
        this.zzq = true;
        this.zzj = unifiedNativeAd.getVideoController();
    }

    @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
    public final void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
        if ((view instanceof zzg) || ((com.google.android.gms.ads.formats.zze) com.google.android.gms.ads.formats.zze.zza.get(view)) != null) {
            throw null;
        }
    }
}
