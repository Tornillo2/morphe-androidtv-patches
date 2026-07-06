package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.ads.zzbgp;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbrs;
import com.google.android.gms.internal.ads.zzbwb;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzbzz;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzay {
    public static final zzay zza = new zzay();
    public final zzbzm zzb;
    public final zzaw zzc;
    public final String zzd;
    public final zzbzz zze;
    public final Random zzf;

    public zzay() {
        zzbzm zzbzmVar = new zzbzm();
        zzaw zzawVar = new zzaw(new zzk(), new zzi(), new zzeq(), new zzbgp(), new zzbwb(), new zzbrs(), new zzbgq());
        String strZzd = zzbzm.zzd();
        zzbzz zzbzzVar = new zzbzz(0, 231700000, true, false, false);
        Random random = new Random();
        this.zzb = zzbzmVar;
        this.zzc = zzawVar;
        this.zzd = strZzd;
        this.zze = zzbzzVar;
        this.zzf = random;
    }

    public static zzaw zza() {
        return zza.zzc;
    }

    public static zzbzm zzb() {
        return zza.zzb;
    }

    public static zzbzz zzc() {
        return zza.zze;
    }

    public static String zzd() {
        return zza.zzd;
    }

    public static Random zze() {
        return zza.zzf;
    }
}
