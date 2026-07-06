package com.google.android.gms.internal.consent_sdk;

import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbh implements zzch<zzbg> {
    public final zzcl<zzbi> zza;
    public final zzcl<Handler> zzb;
    public final zzcl<zzbm> zzc;

    public zzbh(zzcl<zzbi> zzclVar, zzcl<Handler> zzclVar2, zzcl<zzbm> zzclVar3) {
        this.zza = zzclVar;
        this.zzb = zzclVar2;
        this.zzc = zzclVar3;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzbg zzb() {
        zzbi zzbiVarZzb = this.zza.zzb();
        Handler handler = zzcd.zza;
        zzck.zza(handler);
        return new zzbg(zzbiVarZzb, handler, ((zzbn) this.zzc).zzb());
    }
}
