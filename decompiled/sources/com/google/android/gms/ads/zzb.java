package com.google.android.gms.ads;

import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public final class zzb {
    public static int zza(AdSize adSize) {
        return adSize.zzg;
    }

    public static int zzb(AdSize adSize) {
        return adSize.zzi;
    }

    public static AdSize zzc(int i, int i2, String str) {
        return new AdSize(i, i2, str);
    }

    public static AdSize zzd(int i, int i2) {
        AdSize adSize = new AdSize(i, i2);
        adSize.zzf = true;
        adSize.zzg = i2;
        return adSize;
    }

    public static AdSize zze(int i, int i2) {
        AdSize adSize = new AdSize(i, i2);
        adSize.zzh = true;
        adSize.zzi = i2;
        return adSize;
    }

    public static boolean zzf(AdSize adSize) {
        return adSize.zze;
    }

    public static boolean zzg(AdSize adSize) {
        return adSize.zzf;
    }

    public static boolean zzh(AdSize adSize) {
        return adSize.zzh;
    }
}
