package com.google.android.gms.internal.ads;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbdz extends zzbeh {
    public static final int zza;
    public static final int zzb;
    public static final int zzc;
    public final String zzd;
    public final List zze = new ArrayList();
    public final List zzf = new ArrayList();
    public final int zzg;
    public final int zzh;
    public final int zzi;
    public final int zzj;
    public final int zzk;

    static {
        int iRgb = Color.rgb(12, 174, 206);
        zzc = iRgb;
        zza = Color.rgb(204, 204, 204);
        zzb = iRgb;
    }

    public zzbdz(String str, List list, Integer num, Integer num2, Integer num3, int i, int i2, boolean z) {
        this.zzd = str;
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzbec zzbecVar = (zzbec) list.get(i3);
            this.zze.add(zzbecVar);
            this.zzf.add(zzbecVar);
        }
        this.zzg = num != null ? num.intValue() : zza;
        this.zzh = num2 != null ? num2.intValue() : zzb;
        this.zzi = num3 != null ? num3.intValue() : 12;
        this.zzj = i;
        this.zzk = i2;
    }

    public final int zzb() {
        return this.zzj;
    }

    public final int zzc() {
        return this.zzk;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzh;
    }

    public final int zzf() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzbei
    public final String zzg() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzbei
    public final List zzh() {
        return this.zzf;
    }

    public final List zzi() {
        return this.zze;
    }
}
