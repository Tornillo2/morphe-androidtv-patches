package com.google.android.gms.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbzt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ResponseInfo {

    @Nullable
    public final zzdn zza;
    public final List zzb = new ArrayList();

    @Nullable
    public AdapterResponseInfo zzc;

    public ResponseInfo(@Nullable zzdn zzdnVar) {
        this.zza = zzdnVar;
        if (zzdnVar != null) {
            try {
                List listZzj = zzdnVar.zzj();
                if (listZzj != null) {
                    Iterator it = listZzj.iterator();
                    while (it.hasNext()) {
                        AdapterResponseInfo adapterResponseInfoZza = AdapterResponseInfo.zza((zzu) it.next());
                        if (adapterResponseInfoZza != null) {
                            this.zzb.add(adapterResponseInfoZza);
                        }
                    }
                }
            } catch (RemoteException e) {
                zzbzt.zzh("Could not forward getAdapterResponseInfo to ResponseInfo.", e);
            }
        }
        zzdn zzdnVar2 = this.zza;
        if (zzdnVar2 == null) {
            return;
        }
        try {
            zzu zzuVarZzf = zzdnVar2.zzf();
            if (zzuVarZzf != null) {
                this.zzc = AdapterResponseInfo.zza(zzuVarZzf);
            }
        } catch (RemoteException e2) {
            zzbzt.zzh("Could not forward getLoadedAdapterResponse to ResponseInfo.", e2);
        }
    }

    @Nullable
    public static ResponseInfo zza(@Nullable zzdn zzdnVar) {
        if (zzdnVar != null) {
            return new ResponseInfo(zzdnVar);
        }
        return null;
    }

    @NonNull
    public static ResponseInfo zzb(@Nullable zzdn zzdnVar) {
        return new ResponseInfo(zzdnVar);
    }

    @NonNull
    public List<AdapterResponseInfo> getAdapterResponses() {
        return this.zzb;
    }

    @Nullable
    public AdapterResponseInfo getLoadedAdapterResponseInfo() {
        return this.zzc;
    }

    @Nullable
    public String getMediationAdapterClassName() {
        try {
            zzdn zzdnVar = this.zza;
            if (zzdnVar != null) {
                return zzdnVar.zzg();
            }
            return null;
        } catch (RemoteException e) {
            zzbzt.zzh("Could not forward getMediationAdapterClassName to ResponseInfo.", e);
            return null;
        }
    }

    @NonNull
    public Bundle getResponseExtras() {
        try {
            zzdn zzdnVar = this.zza;
            if (zzdnVar != null) {
                return zzdnVar.zze();
            }
        } catch (RemoteException e) {
            zzbzt.zzh("Could not forward getResponseExtras to ResponseInfo.", e);
        }
        return new Bundle();
    }

    @Nullable
    public String getResponseId() {
        try {
            zzdn zzdnVar = this.zza;
            if (zzdnVar != null) {
                return zzdnVar.zzi();
            }
            return null;
        } catch (RemoteException e) {
            zzbzt.zzh("Could not forward getResponseId to ResponseInfo.", e);
            return null;
        }
    }

    @NonNull
    public String toString() {
        try {
            return zzd().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }

    @Nullable
    @VisibleForTesting
    public final zzdn zzc() {
        return this.zza;
    }

    @NonNull
    public final JSONObject zzd() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String responseId = getResponseId();
        if (responseId == null) {
            jSONObject.put("Response ID", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Response ID", responseId);
        }
        String mediationAdapterClassName = getMediationAdapterClassName();
        if (mediationAdapterClassName == null) {
            jSONObject.put("Mediation Adapter Class Name", AbstractJsonLexerKt.NULL);
        } else {
            jSONObject.put("Mediation Adapter Class Name", mediationAdapterClassName);
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.zzb.iterator();
        while (it.hasNext()) {
            jSONArray.put(((AdapterResponseInfo) it.next()).zzb());
        }
        jSONObject.put("Adapter Responses", jSONArray);
        AdapterResponseInfo adapterResponseInfo = this.zzc;
        if (adapterResponseInfo != null) {
            jSONObject.put("Loaded Adapter Response", adapterResponseInfo.zzb());
        }
        Bundle responseExtras = getResponseExtras();
        if (responseExtras != null) {
            jSONObject.put("Response Extras", zzay.zzb().zzh(responseExtras));
        }
        return jSONObject;
    }
}
