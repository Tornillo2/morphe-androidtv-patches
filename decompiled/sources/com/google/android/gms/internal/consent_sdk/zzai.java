package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzai implements zzat {
    public final zzaj zza;
    public final zzai zzb = this;
    public final zzcl<zzbi> zzc;
    public final zzcl<zzbc> zzd;
    public final zzcl<zzay> zze;
    public final zzcl zzf;
    public final zzcl<zzbg> zzg;

    public zzai(zzaj zzajVar, zzbc zzbcVar, zzaf zzafVar) {
        this.zza = zzajVar;
        zzcl<zzbi> zzclVarZza = zzcg.zza(new zzbj(zzajVar.zzb));
        this.zzc = zzclVarZza;
        zzch zzchVarZza = zzci.zza(zzbcVar);
        this.zzd = zzchVarZza;
        zzcf zzcfVar = new zzcf();
        this.zze = zzcfVar;
        zzcl<Application> zzclVar = zzajVar.zzb;
        zzap zzapVar = zzao.zza;
        zzar zzarVar = zzaq.zza;
        zzcl<zzh> zzclVar2 = zzajVar.zzi;
        zzcl<zzak> zzclVar3 = zzajVar.zzj;
        zzcl<zzam> zzclVar4 = zzajVar.zzc;
        zzbn zzbnVar = new zzbn(zzclVar, zzclVarZza, zzapVar, zzarVar, zzclVar2, zzclVar3, zzcfVar, zzclVar4);
        this.zzf = zzbnVar;
        zzbh zzbhVar = new zzbh(zzclVarZza, zzapVar, zzbnVar);
        this.zzg = zzbhVar;
        zzcf.zza(zzcfVar, zzcg.zza(new zzaz(zzclVar, zzajVar.zzd, zzclVarZza, zzclVar4, zzchVarZza, zzbhVar)));
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzat
    public final zzay zza() {
        return this.zze.zzb();
    }
}
