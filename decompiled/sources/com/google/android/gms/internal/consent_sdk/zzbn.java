package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbn implements zzch<zzbm> {
    public final zzcl<Application> zza;
    public final zzcl<zzbi> zzb;
    public final zzcl<Handler> zzc;
    public final zzcl<Executor> zzd;
    public final zzcl<zzh> zze;
    public final zzcl<zzak> zzf;
    public final zzcl<zzay> zzg;
    public final zzcl<zzam> zzh;

    public zzbn(zzcl<Application> zzclVar, zzcl<zzbi> zzclVar2, zzcl<Handler> zzclVar3, zzcl<Executor> zzclVar4, zzcl<zzh> zzclVar5, zzcl<zzak> zzclVar6, zzcl<zzay> zzclVar7, zzcl<zzam> zzclVar8) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
        this.zzd = zzclVar4;
        this.zze = zzclVar5;
        this.zzf = zzclVar6;
        this.zzg = zzclVar7;
        this.zzh = zzclVar8;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzbm zzb() {
        Application applicationZzb = this.zza.zzb();
        zzbi zzbiVarZzb = this.zzb.zzb();
        Handler handler = zzcd.zza;
        zzck.zza(handler);
        Executor executor = zzcd.zzb;
        zzck.zza(executor);
        return new zzbm(applicationZzb, zzbiVarZzb, handler, executor, this.zze.zzb(), ((zzal) this.zzf).zzb(), this.zzg.zzb(), this.zzh.zzb());
    }
}
