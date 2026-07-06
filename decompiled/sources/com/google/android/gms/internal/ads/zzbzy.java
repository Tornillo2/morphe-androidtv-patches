package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.internal.client.zzay;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.lang3.time.GmtTimeZone;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzbzy implements zzbzl {

    @Nullable
    public final String zza;

    public zzbzy(@Nullable String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbzl
    @WorkerThread
    public boolean zza(String str) {
        boolean z = false;
        try {
            zzbzt.zze("Pinging URL: " + str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                zzay.zzb();
                String str2 = this.zza;
                httpURLConnection.setConnectTimeout(GmtTimeZone.MILLISECONDS_PER_MINUTE);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setReadTimeout(GmtTimeZone.MILLISECONDS_PER_MINUTE);
                if (str2 != null) {
                    httpURLConnection.setRequestProperty("User-Agent", str2);
                }
                httpURLConnection.setUseCaches(false);
                zzbzs zzbzsVar = new zzbzs(null);
                zzbzsVar.zzc(httpURLConnection, null);
                int responseCode = httpURLConnection.getResponseCode();
                zzbzsVar.zze(httpURLConnection, responseCode);
                if (responseCode < 200 || responseCode >= 300) {
                    zzbzt.zzj("Received non-success response code " + responseCode + " from pinging URL: " + str);
                } else {
                    z = true;
                }
                return z;
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e = e;
            zzbzt.zzj("Error while pinging URL: " + str + ". " + e.getMessage());
            return false;
        } catch (IndexOutOfBoundsException e2) {
            zzbzt.zzj("Error while parsing ping URL: " + str + ". " + e2.getMessage());
            return false;
        } catch (RuntimeException e3) {
            e = e3;
            zzbzt.zzj("Error while pinging URL: " + str + ". " + e.getMessage());
            return false;
        } finally {
        }
    }

    public zzbzy() {
        this.zza = null;
    }
}
