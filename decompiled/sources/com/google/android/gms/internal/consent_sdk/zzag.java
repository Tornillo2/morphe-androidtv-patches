package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzag {
    public Application zza;

    public /* synthetic */ zzag(zzaf zzafVar) {
    }

    public final zzd zza() {
        zzck.zzb(this.zza, Application.class);
        return new zzaj(this.zza, null);
    }

    public final zzag zzb(Application application) {
        application.getClass();
        this.zza = application;
        return this;
    }

    public zzag() {
    }
}
