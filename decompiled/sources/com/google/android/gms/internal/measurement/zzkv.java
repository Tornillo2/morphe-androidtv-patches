package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzkv extends zzkx {
    public zzkv() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkx
    public final void zza(Object obj, long j) {
        ((zzkj) zzmv.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzkx
    public final void zzb(Object obj, Object obj2, long j) {
        zzkj zzkjVarZzd = (zzkj) zzmv.zzf(obj, j);
        zzkj zzkjVar = (zzkj) zzmv.zzf.zzm(obj2, j);
        int size = zzkjVarZzd.size();
        int size2 = zzkjVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzkjVarZzd.zzc()) {
                zzkjVarZzd = zzkjVarZzd.zzd(size2 + size);
            }
            zzkjVarZzd.addAll(zzkjVar);
        }
        if (size > 0) {
            zzkjVar = zzkjVarZzd;
        }
        zzmv.zzs(obj, j, zzkjVar);
    }

    public /* synthetic */ zzkv(zzku zzkuVar) {
    }
}
