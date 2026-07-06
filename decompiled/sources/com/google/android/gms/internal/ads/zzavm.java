package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzavm extends zzavt {

    @Nullable
    public final AppOpenAd.AppOpenAdLoadCallback zza;
    public final String zzb;

    public zzavm(AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback, String str) {
        this.zza = appOpenAdLoadCallback;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final void zzc(zze zzeVar) {
        if (this.zza != null) {
            this.zza.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final void zzd(zzavr zzavrVar) {
        if (this.zza != null) {
            this.zza.onAdLoaded(new zzavn(zzavrVar, this.zzb));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final void zzb(int i) {
    }
}
