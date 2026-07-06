package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.nativead.NativeCustomFormatAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbri {
    public final NativeCustomFormatAd.OnCustomFormatAdLoadedListener zza;

    @Nullable
    public final NativeCustomFormatAd.OnCustomClickListener zzb;

    @Nullable
    @GuardedBy("this")
    public NativeCustomFormatAd zzc;

    public zzbri(NativeCustomFormatAd.OnCustomFormatAdLoadedListener onCustomFormatAdLoadedListener, @Nullable NativeCustomFormatAd.OnCustomClickListener onCustomClickListener) {
        this.zza = onCustomFormatAdLoadedListener;
        this.zzb = onCustomClickListener;
    }

    @Nullable
    public final zzbfu zza() {
        if (this.zzb == null) {
            return null;
        }
        return new zzbrf(this, null);
    }

    public final zzbfx zzb() {
        return new zzbrh(this, null);
    }

    public final synchronized NativeCustomFormatAd zzf(zzbfk zzbfkVar) {
        NativeCustomFormatAd nativeCustomFormatAd = this.zzc;
        if (nativeCustomFormatAd != null) {
            return nativeCustomFormatAd;
        }
        zzbrj zzbrjVar = new zzbrj(zzbfkVar);
        this.zzc = zzbrjVar;
        return zzbrjVar;
    }
}
