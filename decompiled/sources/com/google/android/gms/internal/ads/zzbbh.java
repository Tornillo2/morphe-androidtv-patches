package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbbh implements zzbdt {
    public final /* synthetic */ zzbbi zza;

    public zzbbh(zzbbi zzbbiVar) {
        this.zza = zzbbiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbdt
    public final Boolean zza(String str, boolean z) {
        try {
            return Boolean.valueOf(this.zza.zze.getBoolean(str, z));
        } catch (ClassCastException unused) {
            return Boolean.valueOf(this.zza.zze.getString(str, String.valueOf(z)));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdt
    public final Double zzb(String str, double d) {
        try {
            return Double.valueOf(this.zza.zze.getFloat(str, (float) d));
        } catch (ClassCastException unused) {
            return Double.valueOf(this.zza.zze.getString(str, String.valueOf(d)));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdt
    public final Long zzc(String str, long j) {
        try {
            return Long.valueOf(this.zza.zze.getLong(str, j));
        } catch (ClassCastException unused) {
            return Long.valueOf(this.zza.zze.getInt(str, (int) j));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdt
    public final String zzd(String str, String str2) {
        return this.zza.zze.getString(str, str2);
    }
}
