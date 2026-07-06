package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbpd extends zzbom {
    public final UnifiedNativeAdMapper zza;

    public zzbpd(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zza = unifiedNativeAdMapper;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final boolean zzA() {
        return this.zza.zzq;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final boolean zzB() {
        return this.zza.zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final double zze() {
        Double d = this.zza.zzg;
        if (d != null) {
            return d.doubleValue();
        }
        return -1.0d;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzf() {
        return this.zza.getMediaContentAspectRatio();
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzg() {
        this.zza.getClass();
        return 0.0f;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzh() {
        this.zza.getClass();
        return 0.0f;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final Bundle zzi() {
        return this.zza.zzo;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final zzdq zzj() {
        VideoController videoController = this.zza.zzj;
        if (videoController != null) {
            return videoController.zza();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final zzbei zzk() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final zzbeq zzl() {
        NativeAd.Image image = this.zza.zzd;
        if (image != null) {
            return new zzbec(image.getDrawable(), image.getUri(), image.getScale(), image.zzb(), image.zza());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final IObjectWrapper zzm() {
        View adChoicesContent = this.zza.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return new ObjectWrapper(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final IObjectWrapper zzn() {
        View view = this.zza.zzm;
        if (view == null) {
            return null;
        }
        return new ObjectWrapper(view);
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    @Nullable
    public final IObjectWrapper zzo() {
        Object obj = this.zza.zzn;
        if (obj == null) {
            return null;
        }
        return new ObjectWrapper(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzp() {
        return this.zza.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzq() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzr() {
        return this.zza.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzs() {
        return this.zza.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzt() {
        return this.zza.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzu() {
        return this.zza.zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final List zzv() {
        List<NativeAd.Image> list = this.zza.zzb;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (NativeAd.Image image : list) {
                arrayList.add(new zzbec(image.getDrawable(), image.getUri(), image.getScale(), image.zzb(), image.zza()));
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzw(IObjectWrapper iObjectWrapper) {
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zza;
        unifiedNativeAdMapper.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzx() {
        this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzy(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zza.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzz(IObjectWrapper iObjectWrapper) {
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zza;
        unifiedNativeAdMapper.getClass();
    }
}
