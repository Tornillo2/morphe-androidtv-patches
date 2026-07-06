package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzbcp {
    public final String zza;
    public final Object zzb;
    public final int zzc;

    public zzbcp(String str, Object obj, int i) {
        this.zza = str;
        this.zzb = obj;
        this.zzc = i;
    }

    public static zzbcp zza(String str, double d) {
        return new zzbcp(str, Double.valueOf(d), 3);
    }

    public static zzbcp zzb(String str, long j) {
        return new zzbcp(str, Long.valueOf(j), 2);
    }

    public static zzbcp zzc(String str, String str2) {
        return new zzbcp(str, str2, 4);
    }

    public static zzbcp zzd(String str, boolean z) {
        return new zzbcp(str, Boolean.valueOf(z), 1);
    }

    public final Object zze() {
        zzbdt zzbdtVarZza = zzbdv.zza();
        if (zzbdtVarZza != null) {
            int i = this.zzc - 1;
            return i != 0 ? i != 1 ? i != 2 ? zzbdtVarZza.zzd(this.zza, (String) this.zzb) : zzbdtVarZza.zzb(this.zza, ((Double) this.zzb).doubleValue()) : zzbdtVarZza.zzc(this.zza, ((Long) this.zzb).longValue()) : zzbdtVarZza.zza(this.zza, ((Boolean) this.zzb).booleanValue());
        }
        if (zzbdv.zzb() != null) {
            zzbdv.zzb().zza();
        }
        return this.zzb;
    }
}
