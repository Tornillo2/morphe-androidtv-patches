package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgv {
    public final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zza;

    @Nullable
    public final NativeCustomTemplateAd.OnCustomClickListener zzb;

    @Nullable
    @GuardedBy("this")
    public NativeCustomTemplateAd zzc;

    public zzbgv(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, @Nullable NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zza = onCustomTemplateAdLoadedListener;
        this.zzb = onCustomClickListener;
    }

    @Nullable
    public final zzbfu zzd() {
        if (this.zzb == null) {
            return null;
        }
        return new zzbgs(this, null);
    }

    public final zzbfx zze() {
        return new zzbgu(this, null);
    }

    public final synchronized NativeCustomTemplateAd zzf(zzbfk zzbfkVar) {
        NativeCustomTemplateAd nativeCustomTemplateAd = this.zzc;
        if (nativeCustomTemplateAd != null) {
            return nativeCustomTemplateAd;
        }
        zzbfl zzbflVar = new zzbfl(zzbfkVar);
        this.zzc = zzbflVar;
        return zzbflVar;
    }
}
