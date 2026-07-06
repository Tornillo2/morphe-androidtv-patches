package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaj extends zzd {
    public final zzaj zza = this;
    public final zzcl<Application> zzb;
    public final zzcl<zzam> zzc;
    public final zzcl<zzac> zzd;
    public final zzcl<zzas> zze;
    public final zzcl<zzba> zzf;
    public final zzcl<zzb> zzg;
    public final zzcl<zzn> zzh;
    public final zzcl<zzh> zzi;
    public final zzcl<zzak> zzj;
    public final zzcl<zzz> zzk;
    public final zzcl<zzv> zzl;
    public final zzcl<zzk> zzm;

    public zzaj(Application application, zzaf zzafVar) {
        zzch zzchVarZza = zzci.zza(application);
        this.zzb = zzchVarZza;
        zzcl<zzam> zzclVarZza = zzcg.zza(new zzan(zzchVarZza));
        this.zzc = zzclVarZza;
        zzcl<zzac> zzclVarZza2 = zzcg.zza(zzad.zza);
        this.zzd = zzclVarZza2;
        zzaf zzafVar2 = new zzaf(this);
        this.zze = zzafVar2;
        zzcl<zzba> zzclVarZza3 = zzcg.zza(new zzbb(zzafVar2));
        this.zzf = zzclVarZza3;
        zzc zzcVar = new zzc(zzchVarZza);
        this.zzg = zzcVar;
        zzp zzpVar = new zzp(zzchVarZza, zzcVar, zzclVarZza);
        this.zzh = zzpVar;
        zzar zzarVar = zzaq.zza;
        zzcl<zzh> zzclVarZza4 = zzcg.zza(new zzi(zzarVar));
        this.zzi = zzclVarZza4;
        zzal zzalVar = new zzal(zzchVarZza, zzclVarZza, zzarVar);
        this.zzj = zzalVar;
        zzab zzabVar = new zzab(zzclVarZza4, zzalVar, zzclVarZza);
        this.zzk = zzabVar;
        zzw zzwVar = new zzw(zzchVarZza, zzclVarZza2, zzao.zza, zzarVar, zzclVarZza, zzclVarZza3, zzpVar, zzabVar, zzclVarZza4);
        this.zzl = zzwVar;
        this.zzm = zzcg.zza(new zzl(zzclVarZza, zzwVar, zzclVarZza3));
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzd
    public final zzk zzb() {
        return this.zzm.zzb();
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzd
    public final zzba zzc() {
        return this.zzf.zzb();
    }
}
