package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.MediationInterscrollerAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzboz extends zzbog {
    public final MediationInterscrollerAd zza;

    public zzboz(MediationInterscrollerAd mediationInterscrollerAd) {
        this.zza = mediationInterscrollerAd;
    }

    @Override // com.google.android.gms.internal.ads.zzboh
    public final IObjectWrapper zze() {
        return new ObjectWrapper(this.zza.getView());
    }

    @Override // com.google.android.gms.internal.ads.zzboh
    public final boolean zzf() {
        return this.zza.shouldDelegateInterscrollerEffect();
    }
}
