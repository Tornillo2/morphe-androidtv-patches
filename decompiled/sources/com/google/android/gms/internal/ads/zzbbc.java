package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzba;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbbc {
    public final int zza;
    public final String zzb;
    public final Object zzc;

    public /* synthetic */ zzbbc(int i, String str, Object obj, zzbbb zzbbbVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        zzba.zza().zzd(this);
    }

    public static zzbbc zzf(int i, String str, float f) {
        return new zzbaz(1, str, Float.valueOf(f));
    }

    public static zzbbc zzg(int i, String str, int i2) {
        return new zzbax(1, str, Integer.valueOf(i2));
    }

    public static zzbbc zzh(int i, String str, long j) {
        return new zzbay(1, str, Long.valueOf(j));
    }

    public static zzbbc zzi(int i, String str, Boolean bool) {
        return new zzbaw(i, str, bool, null);
    }

    public static zzbbc zzj(int i, String str, String str2) {
        return new zzbba(1, str, str2);
    }

    public static zzbbc zzk(int i, String str) {
        zzbbc zzbbcVarZzj = zzj(1, "gads:sdk_core_constants:experiment_id", null);
        zzba.zza.zzb.zzc(zzbbcVarZzj);
        return zzbbcVarZzj;
    }

    public abstract Object zza(JSONObject jSONObject);

    public abstract Object zzb(Bundle bundle);

    public abstract Object zzc(SharedPreferences sharedPreferences);

    public abstract void zzd(SharedPreferences.Editor editor, Object obj);

    public final int zze() {
        return this.zza;
    }

    public final Object zzl() {
        return zzba.zzc().zzb(this);
    }

    public final Object zzm() {
        return this.zzc;
    }

    public final String zzn() {
        return this.zzb;
    }
}
