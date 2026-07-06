package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.SavedStateHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzak implements zzg {
    public final Application zza;
    public final zzam zzb;
    public final Executor zzc;

    public zzak(Application application, zzam zzamVar, Executor executor) {
        this.zza = application;
        this.zzb = zzamVar;
        this.zzc = executor;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzg
    public final Executor zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzg
    public final boolean zzb(String str, JSONObject jSONObject) {
        int iHashCode = str.hashCode();
        if (iHashCode != 94746189) {
            if (iHashCode == 113399775 && str.equals("write")) {
                zzbz zzbzVar = new zzbz(this.zza);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    Object objOpt = jSONObject.opt(next);
                    String strValueOf = String.valueOf(objOpt);
                    StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 23 + strValueOf.length());
                    sb.append("Writing to storage: [");
                    sb.append(next);
                    sb.append("] ");
                    sb.append(strValueOf);
                    Log.d("UserMessagingPlatform", sb.toString());
                    if (zzbzVar.zzc(next, objOpt)) {
                        this.zzb.zzc.add(next);
                    } else {
                        String strValueOf2 = String.valueOf(next);
                        Log.d("UserMessagingPlatform", strValueOf2.length() != 0 ? "Failed writing key: ".concat(strValueOf2) : new String("Failed writing key: "));
                    }
                }
                this.zzb.zze();
                zzbzVar.zzb();
                return true;
            }
        } else if (str.equals("clear")) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(SavedStateHandle.KEYS);
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                String strValueOf3 = String.valueOf(jSONObject.toString());
                Log.d("UserMessagingPlatform", strValueOf3.length() != 0 ? "Action[clear]: wrong args.".concat(strValueOf3) : new String("Action[clear]: wrong args."));
            } else {
                HashSet hashSet = new HashSet();
                int length = jSONArrayOptJSONArray.length();
                for (int i = 0; i < length; i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (TextUtils.isEmpty(strOptString)) {
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Action[clear]: empty key at index: ");
                        sb2.append(i);
                        Log.d("UserMessagingPlatform", sb2.toString());
                    } else {
                        hashSet.add(strOptString);
                    }
                }
                zzca.zzb(this.zza, hashSet);
            }
            return true;
        }
        return false;
    }
}
