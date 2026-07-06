package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzal implements zzch<zzak> {
    public final zzcl<Application> zza;
    public final zzcl<zzam> zzb;
    public final zzcl<Executor> zzc;

    public zzal(zzcl<Application> zzclVar, zzcl<zzam> zzclVar2, zzcl<Executor> zzclVar3) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzak zzb() {
        Application applicationZzb = this.zza.zzb();
        zzam zzamVarZzb = this.zzb.zzb();
        Executor executor = zzcd.zzb;
        zzck.zza(executor);
        return new zzak(applicationZzb, zzamVarZzb, executor);
    }
}
