package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdLoadCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzh extends zzbj {
    public final AdLoadCallback zza;
    public final Object zzb;

    public zzh(AdLoadCallback adLoadCallback, Object obj) {
        this.zza = adLoadCallback;
        this.zzb = obj;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbk
    public final void zzb(zze zzeVar) {
        AdLoadCallback adLoadCallback = this.zza;
        if (adLoadCallback != null) {
            adLoadCallback.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzbk
    public final void zzc() {
        Object obj;
        AdLoadCallback adLoadCallback = this.zza;
        if (adLoadCallback == null || (obj = this.zzb) == null) {
            return;
        }
        adLoadCallback.onAdLoaded(obj);
    }
}
