package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.nativead.NativeCustomFormatAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbrf extends zzbft {
    public final /* synthetic */ zzbri zza;

    @Override // com.google.android.gms.internal.ads.zzbfu
    public final void zze(zzbfk zzbfkVar, String str) {
        zzbri zzbriVar = this.zza;
        NativeCustomFormatAd.OnCustomClickListener onCustomClickListener = zzbriVar.zzb;
        if (onCustomClickListener == null) {
            return;
        }
        onCustomClickListener.onCustomClick(zzbriVar.zzf(zzbfkVar), str);
    }
}
