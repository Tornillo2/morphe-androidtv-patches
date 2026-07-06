package com.google.android.gms.internal.consent_sdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcf<T> implements zzch<T> {
    public zzcl<T> zza;

    public static <T> void zza(zzcl<T> zzclVar, zzcl<T> zzclVar2) {
        zzcf zzcfVar = (zzcf) zzclVar;
        if (zzcfVar.zza != null) {
            throw new IllegalStateException();
        }
        zzcfVar.zza = zzclVar2;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final T zzb() {
        zzcl<T> zzclVar = this.zza;
        if (zzclVar != null) {
            return zzclVar.zzb();
        }
        throw new IllegalStateException();
    }
}
