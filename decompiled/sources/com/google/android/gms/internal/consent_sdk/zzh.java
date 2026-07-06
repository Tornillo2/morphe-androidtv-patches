package com.google.android.gms.internal.consent_sdk;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzh {
    public final Executor zza;

    public zzh(Executor executor) {
        this.zza = executor;
    }

    @AnyThread
    public final Executor zza() {
        return this.zza;
    }

    @AnyThread
    public final void zzb(@Nullable final String str, @Nullable final String str2, final zzg... zzgVarArr) {
        this.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zze
            @Override // java.lang.Runnable
            public final void run() {
                final JSONObject jSONObject;
                String str3 = str;
                String str4 = str2;
                zzg[] zzgVarArr2 = zzgVarArr;
                if (TextUtils.isEmpty(str3)) {
                    Log.d("UserMessagingPlatform", "Error on action: empty action name");
                    return;
                }
                final String lowerCase = str3.toLowerCase();
                if (TextUtils.isEmpty(str4)) {
                    jSONObject = new JSONObject();
                } else {
                    try {
                        jSONObject = new JSONObject(str4);
                    } catch (JSONException unused) {
                        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 32 + String.valueOf(str4).length());
                        sb.append("Action[");
                        sb.append(lowerCase);
                        sb.append("]: failed to parse args: ");
                        sb.append(str4);
                        Log.d("UserMessagingPlatform", sb.toString());
                        return;
                    }
                }
                String strValueOf = String.valueOf(jSONObject);
                StringBuilder sb2 = new StringBuilder(String.valueOf(lowerCase).length() + 10 + strValueOf.length());
                sb2.append("Action[");
                sb2.append(lowerCase);
                sb2.append("]: ");
                sb2.append(strValueOf);
                Log.d("UserMessagingPlatform", sb2.toString());
                for (final zzg zzgVar : zzgVarArr2) {
                    FutureTask futureTask = new FutureTask(new Callable() { // from class: com.google.android.gms.internal.consent_sdk.zzf
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return Boolean.valueOf(zzgVar.zzb(lowerCase, jSONObject));
                        }
                    });
                    zzgVar.zza().execute(futureTask);
                    try {
                    } catch (InterruptedException e) {
                        StringBuilder sb3 = new StringBuilder(String.valueOf(lowerCase).length() + 33);
                        sb3.append("Thread interrupted for Action[");
                        sb3.append(lowerCase);
                        sb3.append("]: ");
                        Log.d("UserMessagingPlatform", sb3.toString(), e);
                    } catch (ExecutionException e2) {
                        StringBuilder sb4 = new StringBuilder(String.valueOf(lowerCase).length() + 24);
                        sb4.append("Failed to run Action[");
                        sb4.append(lowerCase);
                        sb4.append("]: ");
                        Log.d("UserMessagingPlatform", sb4.toString(), e2.getCause());
                    }
                    if (((Boolean) futureTask.get()).booleanValue()) {
                        return;
                    }
                }
            }
        });
    }
}
