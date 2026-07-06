package com.google.android.gms.internal.consent_sdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzah implements zzas {
    public final zzaj zza;
    public zzbc zzb;

    public /* synthetic */ zzah(zzaj zzajVar, zzaf zzafVar) {
        this.zza = zzajVar;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzas
    public final /* bridge */ /* synthetic */ zzas zza(zzbc zzbcVar) {
        this.zzb = zzbcVar;
        return this;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzas
    public final zzat zzb() {
        zzck.zzb(this.zzb, zzbc.class);
        return new zzai(this.zza, this.zzb, null);
    }
}
