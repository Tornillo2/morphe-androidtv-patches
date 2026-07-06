package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbaz extends zzbbc {
    public zzbaz(int i, String str, Float f) {
        super(1, str, f, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zza(JSONObject jSONObject) {
        return Float.valueOf((float) jSONObject.optDouble(this.zzb, ((Float) this.zzc).floatValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzb(Bundle bundle) {
        return bundle.containsKey("com.google.android.gms.ads.flag.".concat(this.zzb)) ? Float.valueOf(bundle.getFloat("com.google.android.gms.ads.flag.".concat(this.zzb))) : (Float) this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Object zzc(SharedPreferences sharedPreferences) {
        return Float.valueOf(sharedPreferences.getFloat(this.zzb, ((Float) this.zzc).floatValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzd(SharedPreferences.Editor editor, Object obj) {
        editor.putFloat(this.zzb, ((Float) obj).floatValue());
    }
}
