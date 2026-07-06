package com.google.android.gms.internal.ads;

import android.util.Base64;
import android.util.JsonWriter;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbzs {
    public static final /* synthetic */ int zza = 0;

    @GuardedBy("lock")
    public static boolean zzc = false;

    @GuardedBy("lock")
    public static boolean zzd = false;
    public final List zzg;
    public static final Object zzb = new Object();
    public static final Clock zze = DefaultClock.zza;
    public static final Set zzf = new HashSet(Arrays.asList(new String[0]));

    public zzbzs() {
        this(null);
    }

    public static void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zzr(jsonWriter, map);
        if (bArr != null) {
            jsonWriter.name("body").value(Base64.encodeToString(bArr, 0));
        }
        jsonWriter.endObject();
    }

    public static /* synthetic */ void zzb(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value(i);
        jsonWriter.endObject();
        zzr(jsonWriter, map);
        jsonWriter.endObject();
    }

    public static void zzi() {
        synchronized (zzb) {
            zzc = false;
            zzd = false;
            zzbzt.zzj("Ad debug logging enablement is out of date.");
        }
    }

    public static void zzj(boolean z) {
        synchronized (zzb) {
            zzc = true;
            zzd = z;
        }
    }

    public static boolean zzk() {
        boolean z;
        synchronized (zzb) {
            try {
                z = false;
                if (zzc && zzd) {
                    z = true;
                }
            } finally {
            }
        }
        return z;
    }

    public static boolean zzl() {
        boolean z;
        synchronized (zzb) {
            z = zzc;
        }
        return z;
    }

    public static synchronized void zzm(String str) {
        try {
            zzbzt.zzi("GMA Debug BEGIN");
            int i = 0;
            while (i < str.length()) {
                int i2 = i + 4000;
                zzbzt.zzi("GMA Debug CONTENT ".concat(String.valueOf(str.substring(i, Math.min(i2, str.length())))));
                i = i2;
            }
            zzbzt.zzi("GMA Debug FINISH");
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void zzr(JsonWriter jsonWriter, @Nullable Map map) throws IOException {
        if (map == null) {
            return;
        }
        jsonWriter.name("headers").beginArray();
        Iterator it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            if (!zzf.contains(str)) {
                if (!(entry.getValue() instanceof List)) {
                    if (!(entry.getValue() instanceof String)) {
                        zzbzt.zzg("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                        break;
                    }
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(str);
                    jsonWriter.name("value").value((String) entry.getValue());
                    jsonWriter.endObject();
                } else {
                    for (String str2 : (List) entry.getValue()) {
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(str);
                        jsonWriter.name("value").value(str2);
                        jsonWriter.endObject();
                    }
                }
            }
        }
        jsonWriter.endArray();
    }

    public final void zzc(HttpURLConnection httpURLConnection, @Nullable byte[] bArr) {
        if (zzk()) {
            zzp(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void zzd(String str, String str2, @Nullable Map map, @Nullable byte[] bArr) {
        if (zzk()) {
            zzp(str, "GET", map, bArr);
        }
    }

    public final void zze(HttpURLConnection httpURLConnection, int i) {
        if (zzk()) {
            String responseMessage = null;
            zzq(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= 300) {
                try {
                    responseMessage = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    zzbzt.zzj("Can not get error message from error HttpURLConnection\n".concat(String.valueOf(e.getMessage())));
                }
                zzo(responseMessage);
            }
        }
    }

    public final void zzf(@Nullable Map map, int i) {
        if (zzk()) {
            zzq(map, i);
            if (i < 200 || i >= 300) {
                zzo(null);
            }
        }
    }

    public final void zzg(@Nullable String str) {
        if (zzk() && str != null) {
            zzh(str.getBytes());
        }
    }

    public final void zzh(final byte[] bArr) {
        zzn("onNetworkResponseBody", new zzbzr() { // from class: com.google.android.gms.internal.ads.zzbzq
            @Override // com.google.android.gms.internal.ads.zzbzr
            public final void zza(JsonWriter jsonWriter) throws IOException {
                byte[] bArr2 = bArr;
                int i = zzbzs.zza;
                jsonWriter.name("params").beginObject();
                int length = bArr2.length;
                String strEncodeToString = Base64.encodeToString(bArr2, 0);
                if (length < 10000) {
                    jsonWriter.name("body").value(strEncodeToString);
                } else {
                    String strZze = zzbzm.zze(strEncodeToString);
                    if (strZze != null) {
                        jsonWriter.name("bodydigest").value(strZze);
                    }
                }
                jsonWriter.name("bodylength").value(length);
                jsonWriter.endObject();
            }
        });
    }

    public final void zzn(String str, zzbzr zzbzrVar) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name("timestamp").value(zze.currentTimeMillis());
            jsonWriter.name(NotificationCompat.CATEGORY_EVENT).value(str);
            jsonWriter.name("components").beginArray();
            Iterator it = this.zzg.iterator();
            while (it.hasNext()) {
                jsonWriter.value((String) it.next());
            }
            jsonWriter.endArray();
            zzbzrVar.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            zzbzt.zzh("unable to log", e);
        }
        zzm(stringWriter.toString());
    }

    public final void zzo(@Nullable final String str) {
        zzn("onNetworkRequestError", new zzbzr() { // from class: com.google.android.gms.internal.ads.zzbzo
            @Override // com.google.android.gms.internal.ads.zzbzr
            public final void zza(JsonWriter jsonWriter) throws IOException {
                String str2 = str;
                int i = zzbzs.zza;
                jsonWriter.name("params").beginObject();
                if (str2 != null) {
                    jsonWriter.name("error_description").value(str2);
                }
                jsonWriter.endObject();
            }
        });
    }

    public final void zzp(final String str, final String str2, @Nullable final Map map, @Nullable final byte[] bArr) {
        zzn("onNetworkRequest", new zzbzr() { // from class: com.google.android.gms.internal.ads.zzbzp
            @Override // com.google.android.gms.internal.ads.zzbzr
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzbzs.zza(str, str2, map, bArr, jsonWriter);
            }
        });
    }

    public final void zzq(@Nullable final Map map, final int i) {
        zzn("onNetworkResponse", new zzbzr() { // from class: com.google.android.gms.internal.ads.zzbzn
            @Override // com.google.android.gms.internal.ads.zzbzr
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzbzs.zzb(i, map, jsonWriter);
            }
        });
    }

    public zzbzs(@Nullable String str) {
        this.zzg = !zzk() ? new ArrayList() : Arrays.asList("network_request_".concat(String.valueOf(UUID.randomUUID().toString())));
    }
}
