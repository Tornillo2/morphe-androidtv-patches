package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzmt extends zzmu {
    public zzmt(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final void zzc(Object obj, long j, boolean z) {
        if (zzmv.zzb) {
            zzmv.zzD(obj, j, z ? (byte) 1 : (byte) 0);
        } else {
            zzmv.zzE(obj, j, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final void zzd(Object obj, long j, byte b) {
        if (zzmv.zzb) {
            zzmv.zzD(obj, j, b);
        } else {
            zzmv.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zzg(Object obj, long j) {
        return zzmv.zzb ? zzmv.zzt(obj, j) : zzmv.zzu(obj, j);
    }
}
