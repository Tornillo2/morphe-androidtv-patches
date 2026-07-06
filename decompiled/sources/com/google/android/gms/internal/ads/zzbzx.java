package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbzx {
    public static Context zza(Context context) throws zzbzw {
        return zzc(context).zzj;
    }

    public static Object zzb(Context context, String str, zzbzv zzbzvVar) throws zzbzw {
        try {
            return zzbzvVar.zza(zzc(context).instantiate(str));
        } catch (Exception e) {
            throw new zzbzw(e);
        }
    }

    public static DynamiteModule zzc(Context context) throws zzbzw {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite");
        } catch (Exception e) {
            throw new zzbzw(e);
        }
    }
}
