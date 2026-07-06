package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzw implements zzch<zzv> {
    public final zzcl<Application> zza;
    public final zzcl<zzac> zzb;
    public final zzcl<Handler> zzc;
    public final zzcl<Executor> zzd;
    public final zzcl<zzam> zze;
    public final zzcl<zzba> zzf;
    public final zzcl<zzn> zzg;
    public final zzcl<zzz> zzh;
    public final zzcl<zzh> zzi;

    public zzw(zzcl<Application> zzclVar, zzcl<zzac> zzclVar2, zzcl<Handler> zzclVar3, zzcl<Executor> zzclVar4, zzcl<zzam> zzclVar5, zzcl<zzba> zzclVar6, zzcl<zzn> zzclVar7, zzcl<zzz> zzclVar8, zzcl<zzh> zzclVar9) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
        this.zzd = zzclVar4;
        this.zze = zzclVar5;
        this.zzf = zzclVar6;
        this.zzg = zzclVar7;
        this.zzh = zzclVar8;
        this.zzi = zzclVar9;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzv zzb() {
        Application applicationZzb = this.zza.zzb();
        zzac zzacVarZzb = this.zzb.zzb();
        Handler handler = zzcd.zza;
        zzck.zza(handler);
        Executor executor = zzcd.zzb;
        zzck.zza(executor);
        return new zzv(applicationZzb, zzacVarZzb, handler, executor, this.zze.zzb(), this.zzf.zzb(), ((zzp) this.zzg).zzb(), ((zzab) this.zzh).zzb(), this.zzi.zzb());
    }
}
