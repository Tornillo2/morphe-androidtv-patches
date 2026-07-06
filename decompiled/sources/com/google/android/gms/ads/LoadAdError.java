package com.google.android.gms.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LoadAdError extends AdError {

    @Nullable
    public final ResponseInfo zza;

    public LoadAdError(int i, @NonNull String str, @NonNull String str2, @Nullable AdError adError, @Nullable ResponseInfo responseInfo) {
        super(i, str, str2, adError);
        this.zza = responseInfo;
    }

    @Nullable
    public ResponseInfo getResponseInfo() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.AdError
    @NonNull
    public String toString() {
        try {
            return zzb().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }

    @Override // com.google.android.gms.ads.AdError
    @NonNull
    public final JSONObject zzb() throws JSONException {
        JSONObject jSONObjectZzb = super.zzb();
        ResponseInfo responseInfo = this.zza;
        if (responseInfo == null) {
            jSONObjectZzb.put("Response Info", AbstractJsonLexerKt.NULL);
            return jSONObjectZzb;
        }
        jSONObjectZzb.put("Response Info", responseInfo.zzd());
        return jSONObjectZzb;
    }
}
