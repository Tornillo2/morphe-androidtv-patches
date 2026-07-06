package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaz implements zzch<zzay> {
    public final zzcl<Application> zza;
    public final zzcl<zzac> zzb;
    public final zzcl<zzbi> zzc;
    public final zzcl<zzam> zzd;
    public final zzcl<zzbc> zze;
    public final zzcl<zzbg> zzf;

    public zzaz(zzcl<Application> zzclVar, zzcl<zzac> zzclVar2, zzcl<zzbi> zzclVar3, zzcl<zzam> zzclVar4, zzcl<zzbc> zzclVar5, zzcl<zzbg> zzclVar6) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
        this.zzd = zzclVar4;
        this.zze = zzclVar5;
        this.zzf = zzclVar6;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzay(this.zza.zzb(), this.zzb.zzb(), this.zzc.zzb(), this.zzd.zzb(), this.zze.zzb(), this.zzf);
    }
}
