package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbax extends zzbbc {
    public zzbax(int i, String str, Integer num) {
        super(1, str, num, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zza(JSONObject jSONObject) {
        return Integer.valueOf(jSONObject.optInt(this.zzb, ((Integer) this.zzc).intValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzb(Bundle bundle) {
        return bundle.containsKey("com.google.android.gms.ads.flag.".concat(this.zzb)) ? Integer.valueOf(bundle.getInt("com.google.android.gms.ads.flag.".concat(this.zzb))) : (Integer) this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzc(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(this.zzb, ((Integer) this.zzc).intValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzd(SharedPreferences.Editor editor, Object obj) {
        editor.putInt(this.zzb, ((Integer) obj).intValue());
    }
}
