package com.google.android.gms.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AdError {

    @NonNull
    public static final String UNDEFINED_DOMAIN = "undefined";
    public final int zza;

    @NonNull
    public final String zzb;

    @NonNull
    public final String zzc;

    @Nullable
    public final AdError zzd;

    public AdError(int i, @NonNull String str, @NonNull String str2, @Nullable AdError adError) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = adError;
    }

    @Nullable
    public AdError getCause() {
        return this.zzd;
    }

    public int getCode() {
        return this.zza;
    }

    @NonNull
    public String getDomain() {
        return this.zzc;
    }

    @NonNull
    public String getMessage() {
        return this.zzb;
    }

    @NonNull
    public String toString() {
        try {
            return zzb().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }

    @NonNull
    public final com.google.android.gms.ads.internal.client.zze zza() {
        AdError adError = this.zzd;
        return new com.google.android.gms.ads.internal.client.zze(this.zza, this.zzb, this.zzc, adError == null ? null : new com.google.android.gms.ads.internal.client.zze(adError.zza, adError.zzb, adError.zzc, null, null), null);
    }

    @NonNull
    public JSONObject zzb() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Code", this.zza);
        jSONObject.put("Message", this.zzb);
        jSONObject.put("Domain", this.zzc);
        AdError adError = this.zzd;
        if (adError == null) {
            jSONObject.put("Cause", AbstractJsonLexerKt.NULL);
            return jSONObject;
        }
        jSONObject.put("Cause", adError.zzb());
        return jSONObject;
    }

    public AdError(int i, @NonNull String str, @NonNull String str2) {
        this(i, str, str2, null);
    }
}
