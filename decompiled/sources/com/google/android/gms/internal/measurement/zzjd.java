package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjd extends zzjf {
    public final byte[] zzb;
    public int zzd;
    public int zze = Integer.MAX_VALUE;
    public int zzc = 0;

    public final int zza(int i) throws zzkm {
        int i2 = this.zze;
        this.zze = 0;
        int i3 = this.zzc + this.zzd;
        this.zzc = i3;
        if (i3 <= 0) {
            this.zzd = 0;
            return i2;
        }
        this.zzd = i3;
        this.zzc = 0;
        return i2;
    }
}
