package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzln implements zzlu {
    public final zzlj zza;
    public final zzml zzb;
    public final boolean zzc;
    public final zzjp zzd;

    public zzln(zzml zzmlVar, zzjp zzjpVar, zzlj zzljVar) {
        this.zzb = zzmlVar;
        this.zzc = zzjpVar.zzc(zzljVar);
        this.zzd = zzjpVar;
        this.zza = zzljVar;
    }

    public static zzln zzc(zzml zzmlVar, zzjp zzjpVar, zzlj zzljVar) {
        return new zzln(zzmlVar, zzjpVar, zzljVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(Object obj) {
        zzml zzmlVar = this.zzb;
        int iZzb = zzmlVar.zzb(zzmlVar.zzc(obj));
        if (!this.zzc) {
            return iZzb;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(Object obj) {
        int iHashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return iHashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final Object zze() {
        return this.zza.zzbI().zzaG();
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzf(Object obj) {
        this.zzb.zzg(obj);
        this.zzd.zzb(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzg(Object obj, Object obj2) {
        zzlw.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            this.zzd.zza(obj2);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzio zzioVar) throws IOException {
        zzkc zzkcVar = (zzkc) obj;
        if (zzkcVar.zzc == zzmm.zza) {
            zzkcVar.zzc = zzmm.zze();
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzi(Object obj, zznd zzndVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzj(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzk(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }
}
