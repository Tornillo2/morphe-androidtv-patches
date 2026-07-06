package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.webkit.WebSettings;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransporter;
import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzv {
    public final Application zza;
    public final zzac zzb;
    public final Handler zzc;
    public final Executor zzd;
    public final zzam zze;
    public final zzba zzf;
    public final zzn zzg;
    public final zzz zzh;
    public final zzh zzi;

    public zzv(Application application, zzac zzacVar, Handler handler, Executor executor, zzam zzamVar, zzba zzbaVar, zzn zznVar, zzz zzzVar, zzh zzhVar) {
        this.zza = application;
        this.zzb = zzacVar;
        this.zzc = handler;
        this.zzd = executor;
        this.zze = zzamVar;
        this.zzf = zzbaVar;
        this.zzg = zznVar;
        this.zzh = zzzVar;
        this.zzi = zzhVar;
    }

    public final /* synthetic */ void zza(final ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener) {
        Handler handler = this.zzc;
        onConsentInfoUpdateSuccessListener.getClass();
        handler.post(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzu
            @Override // java.lang.Runnable
            public final void run() {
                onConsentInfoUpdateSuccessListener.onConsentInfoUpdateSuccess();
            }
        });
    }

    public final void zzb(Activity activity, ConsentRequestParameters consentRequestParameters, final ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, final ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        try {
            ConsentDebugSettings consentDebugSettings = consentRequestParameters.getConsentDebugSettings();
            if (consentDebugSettings == null || !consentDebugSettings.isTestDevice()) {
                String strZza = zzbx.zza(this.zza);
                StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 95);
                sb.append("Use new ConsentDebugSettings.Builder().addTestDeviceHashedId(\"");
                sb.append(strZza);
                sb.append("\") to set this as a debug device.");
                Log.i("UserMessagingPlatform", sb.toString());
            }
            zzaa zzaaVarZza = new zzy(this.zzh, zzd(this.zzg.zzd(activity, consentRequestParameters))).zza();
            this.zze.zzf(zzaaVarZza.zza);
            this.zzf.zzb(zzaaVarZza.zzb);
            this.zzi.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzr
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zza(onConsentInfoUpdateSuccessListener);
                }
            });
        } catch (zzj e) {
            this.zzc.post(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzs
                @Override // java.lang.Runnable
                public final void run() {
                    onConsentInfoUpdateFailureListener.onConsentInfoUpdateFailure(e.zza());
                }
            });
        } catch (RuntimeException e2) {
            String strValueOf = String.valueOf(Log.getStackTraceString(e2));
            final zzj zzjVar = new zzj(1, strValueOf.length() != 0 ? "Caught exception when trying to request consent info update: ".concat(strValueOf) : new String("Caught exception when trying to request consent info update: "));
            this.zzc.post(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzt
                @Override // java.lang.Runnable
                public final void run() {
                    onConsentInfoUpdateFailureListener.onConsentInfoUpdateFailure(zzjVar.zza());
                }
            });
        }
    }

    public final void zzc(@Nullable final Activity activity, final ConsentRequestParameters consentRequestParameters, final ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, final ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        this.zzd.execute(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzq
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(activity, consentRequestParameters, onConsentInfoUpdateSuccessListener, onConsentInfoUpdateFailureListener);
            }
        });
    }

    @WorkerThread
    public final zzbw zzd(zzbu zzbuVar) throws zzj {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://fundingchoicesmessages.google.com/a/consent").openConnection();
            httpURLConnection.setRequestProperty("User-Agent", WebSettings.getDefaultUserAgent(this.zza));
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(MetricsTransporter.REQUEST_METHOD);
            httpURLConnection.setRequestProperty("Content-Type", CctTransportBackend.JSON_CONTENT_TYPE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            try {
                JsonWriter jsonWriter = new JsonWriter(outputStreamWriter);
                try {
                    jsonWriter.beginObject();
                    String str = zzbuVar.zza;
                    if (str != null) {
                        jsonWriter.name("admob_app_id");
                        jsonWriter.value(str);
                    }
                    Boolean bool = zzbuVar.zzb;
                    if (bool != null) {
                        jsonWriter.name("is_lat");
                        jsonWriter.value(bool.booleanValue());
                    }
                    String str2 = zzbuVar.zzc;
                    if (str2 != null) {
                        jsonWriter.name("adid");
                        jsonWriter.value(str2);
                    }
                    zzbq zzbqVar = zzbuVar.zzd;
                    if (zzbqVar != null) {
                        jsonWriter.name("device_info");
                        jsonWriter.beginObject();
                        int i = zzbqVar.zzc;
                        if (i != 1) {
                            jsonWriter.name("os_type");
                            if (i == 0) {
                                throw null;
                            }
                            if (i - 1 != 0) {
                                jsonWriter.value("ANDROID");
                            } else {
                                jsonWriter.value("UNKNOWN");
                            }
                        }
                        String str3 = zzbqVar.zza;
                        if (str3 != null) {
                            jsonWriter.name(CctTransportBackend.KEY_MODEL);
                            jsonWriter.value(str3);
                        }
                        Integer num = zzbqVar.zzb;
                        if (num != null) {
                            jsonWriter.name("android_api_level");
                            jsonWriter.value(num);
                        }
                        jsonWriter.endObject();
                    }
                    String str4 = zzbuVar.zze;
                    if (str4 != null) {
                        jsonWriter.name("language_code");
                        jsonWriter.value(str4);
                    }
                    Boolean bool2 = zzbuVar.zzf;
                    if (bool2 != null) {
                        jsonWriter.name("tag_for_under_age_of_consent");
                        jsonWriter.value(bool2.booleanValue());
                    }
                    Map<String, String> map = zzbuVar.zzg;
                    if (!map.isEmpty()) {
                        jsonWriter.name("stored_infos_map");
                        jsonWriter.beginObject();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            jsonWriter.name(entry.getKey());
                            jsonWriter.value(entry.getValue());
                        }
                        jsonWriter.endObject();
                    }
                    zzbs zzbsVar = zzbuVar.zzh;
                    if (zzbsVar != null) {
                        jsonWriter.name("screen_info");
                        jsonWriter.beginObject();
                        Integer num2 = zzbsVar.zza;
                        if (num2 != null) {
                            jsonWriter.name("width");
                            jsonWriter.value(num2);
                        }
                        Integer num3 = zzbsVar.zzb;
                        if (num3 != null) {
                            jsonWriter.name("height");
                            jsonWriter.value(num3);
                        }
                        Double d = zzbsVar.zzc;
                        if (d != null) {
                            jsonWriter.name("density");
                            jsonWriter.value(d);
                        }
                        List<zzbr> list = zzbsVar.zzd;
                        if (!list.isEmpty()) {
                            jsonWriter.name("screen_insets");
                            jsonWriter.beginArray();
                            for (zzbr zzbrVar : list) {
                                jsonWriter.beginObject();
                                Integer num4 = zzbrVar.zza;
                                if (num4 != null) {
                                    jsonWriter.name("top");
                                    jsonWriter.value(num4);
                                }
                                Integer num5 = zzbrVar.zzb;
                                if (num5 != null) {
                                    jsonWriter.name("left");
                                    jsonWriter.value(num5);
                                }
                                Integer num6 = zzbrVar.zzc;
                                if (num6 != null) {
                                    jsonWriter.name("right");
                                    jsonWriter.value(num6);
                                }
                                Integer num7 = zzbrVar.zzd;
                                if (num7 != null) {
                                    jsonWriter.name("bottom");
                                    jsonWriter.value(num7);
                                }
                                jsonWriter.endObject();
                            }
                            jsonWriter.endArray();
                        }
                        jsonWriter.endObject();
                    }
                    zzbo zzboVar = zzbuVar.zzi;
                    if (zzboVar != null) {
                        jsonWriter.name("app_info");
                        jsonWriter.beginObject();
                        String str5 = zzboVar.zza;
                        if (str5 != null) {
                            jsonWriter.name(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME);
                            jsonWriter.value(str5);
                        }
                        String str6 = zzboVar.zzb;
                        if (str6 != null) {
                            jsonWriter.name("publisher_display_name");
                            jsonWriter.value(str6);
                        }
                        String str7 = zzboVar.zzc;
                        if (str7 != null) {
                            jsonWriter.name("version");
                            jsonWriter.value(str7);
                        }
                        jsonWriter.endObject();
                    }
                    zzbt zzbtVar = zzbuVar.zzj;
                    if (zzbtVar != null) {
                        jsonWriter.name("sdk_info");
                        jsonWriter.beginObject();
                        String str8 = zzbtVar.zza;
                        if (str8 != null) {
                            jsonWriter.name("version");
                            jsonWriter.value(str8);
                        }
                        jsonWriter.endObject();
                    }
                    List<zzbp> list2 = zzbuVar.zzk;
                    if (!list2.isEmpty()) {
                        jsonWriter.name("debug_params");
                        jsonWriter.beginArray();
                        Iterator<zzbp> it = list2.iterator();
                        while (it.hasNext()) {
                            int iOrdinal = it.next().ordinal();
                            if (iOrdinal == 0) {
                                jsonWriter.value("DEBUG_PARAM_UNKNOWN");
                            } else if (iOrdinal == 1) {
                                jsonWriter.value("ALWAYS_SHOW");
                            } else if (iOrdinal == 2) {
                                jsonWriter.value("GEO_OVERRIDE_EEA");
                            } else if (iOrdinal == 3) {
                                jsonWriter.value("GEO_OVERRIDE_NON_EEA");
                            } else if (iOrdinal == 4) {
                                jsonWriter.value("PREVIEWING_DEBUG_MESSAGES");
                            }
                        }
                        jsonWriter.endArray();
                    }
                    jsonWriter.endObject();
                    jsonWriter.close();
                    outputStreamWriter.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode != 200) {
                        String next = new Scanner(httpURLConnection.getErrorStream()).useDelimiter("\\A").next();
                        StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 31);
                        sb.append("Http error code - ");
                        sb.append(responseCode);
                        sb.append(".\n");
                        sb.append(next);
                        throw new IOException(sb.toString());
                    }
                    String headerField = httpURLConnection.getHeaderField("x-ump-using-header");
                    if (headerField != null) {
                        zzbw zzbwVarZza = zzbw.zza(new JsonReader(new StringReader(headerField)));
                        zzbwVarZza.zza = new Scanner(httpURLConnection.getInputStream()).useDelimiter("\\A").next();
                        return zzbwVarZza;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    try {
                        bufferedReader.readLine();
                        JsonReader jsonReader = new JsonReader(bufferedReader);
                        try {
                            zzbw zzbwVarZza2 = zzbw.zza(jsonReader);
                            jsonReader.close();
                            bufferedReader.close();
                            return zzbwVarZza2;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (SocketTimeoutException e) {
            throw new zzj(4, "The server timed out.", e);
        } catch (IOException e2) {
            throw new zzj(2, "Error making request.", e2);
        }
    }
}
