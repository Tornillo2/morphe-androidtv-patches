package com.google.android.gms.ads;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.primevideo.nativebilling.BillingProviderMetricRecorder;
import com.google.android.gms.ads.internal.client.zzu;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdapterResponseInfo {
    public final zzu zza;

    @Nullable
    public final AdError zzb;

    public AdapterResponseInfo(zzu zzuVar) {
        this.zza = zzuVar;
        com.google.android.gms.ads.internal.client.zze zzeVar = zzuVar.zzc;
        this.zzb = zzeVar == null ? null : zzeVar.zza();
    }

    @Nullable
    public static AdapterResponseInfo zza(@Nullable zzu zzuVar) {
        if (zzuVar != null) {
            return new AdapterResponseInfo(zzuVar);
        }
        return null;
    }

    @Nullable
    public AdError getAdError() {
        return this.zzb;
    }

    @NonNull
    public String getAdSourceId() {
        return this.zza.zzf;
    }

    @NonNull
    public String getAdSourceInstanceId() {
        return this.zza.zzh;
    }

    @NonNull
    public String getAdSourceInstanceName() {
        return this.zza.zzg;
    }

    @NonNull
    public String getAdSourceName() {
        return this.zza.zze;
    }

    @NonNull
    public String getAdapterClassName() {
        return this.zza.zza;
    }

    @NonNull
    public Bundle getCredentials() {
        return this.zza.zzd;
    }

    public long getLatencyMillis() {
        return this.zza.zzb;
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
    public final JSONObject zzb() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Adapter", this.zza.zza);
        jSONObject.put(BillingProviderMetricRecorder.MINERVA_METRIC_LATENCY_NAME, this.zza.zzb);
        String str = this.zza.zze;
        if (str == null) {
            jSONObject.put("Ad Source Name", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Ad Source Name", str);
        }
        String str2 = this.zza.zzf;
        if (str2 == null) {
            jSONObject.put("Ad Source ID", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Ad Source ID", str2);
        }
        String str3 = this.zza.zzg;
        if (str3 == null) {
            jSONObject.put("Ad Source Instance Name", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Ad Source Instance Name", str3);
        }
        String str4 = this.zza.zzh;
        if (str4 == null) {
            jSONObject.put("Ad Source Instance ID", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Ad Source Instance ID", str4);
        }
        JSONObject jSONObject2 = new JSONObject();
        for (String str5 : this.zza.zzd.keySet()) {
            jSONObject2.put(str5, this.zza.zzd.get(str5));
        }
        jSONObject.put("Credentials", jSONObject2);
        AdError adError = this.zzb;
        if (adError == null) {
            jSONObject.put("Ad Error", AbstractJsonLexerKt.NULL);
            return jSONObject;
        }
        jSONObject.put("Ad Error", adError.zzb());
        return jSONObject;
    }
}
