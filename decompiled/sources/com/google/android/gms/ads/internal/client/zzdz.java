package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.LoadAdError;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdz extends zzaz {
    public final /* synthetic */ zzea zza;

    public zzdz(zzea zzeaVar) {
        this.zza = zzeaVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzaz, com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(LoadAdError loadAdError) {
        zzea zzeaVar = this.zza;
        zzeaVar.zze.zzb(zzeaVar.zzi());
        super.onAdFailedToLoad(loadAdError);
    }

    @Override // com.google.android.gms.ads.internal.client.zzaz, com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        zzea zzeaVar = this.zza;
        zzeaVar.zze.zzb(zzeaVar.zzi());
        super.onAdLoaded();
    }
}
