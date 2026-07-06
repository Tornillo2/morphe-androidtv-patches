package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbj implements zzch<zzbi> {
    public final zzcl<Application> zza;

    public zzbj(zzcl<Application> zzclVar) {
        this.zza = zzclVar;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final Object zzb() {
        return new zzbi((Context) this.zza.zzb());
    }
}
