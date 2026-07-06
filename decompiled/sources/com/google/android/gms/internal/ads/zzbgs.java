package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgs extends zzbft {
    public final /* synthetic */ zzbgv zza;

    @Override // com.google.android.gms.internal.ads.zzbfu
    public final void zze(zzbfk zzbfkVar, String str) {
        zzbgv zzbgvVar = this.zza;
        NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener = zzbgvVar.zzb;
        if (onCustomClickListener == null) {
            return;
        }
        onCustomClickListener.onCustomClick(zzbgvVar.zzf(zzbfkVar), str);
    }
}
