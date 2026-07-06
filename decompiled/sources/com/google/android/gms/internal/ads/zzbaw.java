package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbaw extends zzbbc {
    public zzbaw(int i, String str, Boolean bool) {
        super(i, str, bool, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zza(JSONObject jSONObject) {
        return Boolean.valueOf(jSONObject.optBoolean(this.zzb, ((Boolean) this.zzc).booleanValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzb(Bundle bundle) {
        return bundle.containsKey("com.google.android.gms.ads.flag.".concat(this.zzb)) ? Boolean.valueOf(bundle.getBoolean("com.google.android.gms.ads.flag.".concat(this.zzb))) : (Boolean) this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzc(SharedPreferences sharedPreferences) {
        return Boolean.valueOf(sharedPreferences.getBoolean(this.zzb, ((Boolean) this.zzc).booleanValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzd(SharedPreferences.Editor editor, Object obj) {
        editor.putBoolean(this.zzb, ((Boolean) obj).booleanValue());
    }
}
