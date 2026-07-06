package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzkp {
    public static final zzjo zzb = zzjo.zza();
    public volatile zzlj zza;
    public volatile zzjb zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkp)) {
            return false;
        }
        zzkp zzkpVar = (zzkp) obj;
        zzlj zzljVar = this.zza;
        zzlj zzljVar2 = zzkpVar.zza;
        if (zzljVar == null && zzljVar2 == null) {
            return zzb().equals(zzkpVar.zzb());
        }
        if (zzljVar != null && zzljVar2 != null) {
            return zzljVar.equals(zzljVar2);
        }
        if (zzljVar != null) {
            zzkpVar.zzc(zzljVar.zzbR());
            return zzljVar.equals(zzkpVar.zza);
        }
        zzc(zzljVar2.zzbR());
        return this.zza.equals(zzljVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zziy) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzbz();
        }
        return 0;
    }

    public final zzjb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            try {
                if (this.zzc != null) {
                    return this.zzc;
                }
                if (this.zza == null) {
                    this.zzc = zzjb.zzb;
                } else {
                    this.zzc = this.zza.zzbv();
                }
                return this.zzc;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzc(zzlj zzljVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzljVar;
                    this.zzc = zzjb.zzb;
                } catch (zzkm unused) {
                    this.zza = zzljVar;
                    this.zzc = zzjb.zzb;
                }
            }
        }
    }
}
