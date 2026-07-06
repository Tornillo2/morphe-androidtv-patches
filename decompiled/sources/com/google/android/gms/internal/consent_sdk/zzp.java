package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzp implements zzch<zzn> {
    public final zzcl<Application> zza;
    public final zzcl<zzb> zzb;
    public final zzcl<zzam> zzc;

    public zzp(zzcl<Application> zzclVar, zzcl<zzb> zzclVar2, zzcl<zzam> zzclVar3) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzn zzb() {
        return new zzn(this.zza.zzb(), ((zzc) this.zzb).zzb(), this.zzc.zzb());
    }
}
