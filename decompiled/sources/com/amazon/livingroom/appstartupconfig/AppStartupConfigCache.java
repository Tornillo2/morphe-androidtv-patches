package com.amazon.livingroom.appstartupconfig;

import android.content.Context;
import android.util.AtomicFile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class AppStartupConfigCache {
    public static final String FILE_NAME = "app-startup-config.json";
    public static final String LOG_TAG = "AppStartupConfigCache";
    public final AtomicFile cacheFile;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Wrapper {
        public static final String KEY_DATA = "data";
        public static final String KEY_LOCALE = "locale";
        public static final String KEY_REFRESH_TOKEN = "refreshToken";
        public static final String KEY_TIMESTAMP = "timestamp";

        @Nullable
        public final JSONObject data;
        public final String locale;
        public final String refreshToken;
        public final long timestamp;

        public Wrapper(long j, String str, String str2, @Nullable JSONObject jSONObject) {
            this.timestamp = j;
            this.data = jSONObject;
            this.locale = str;
            this.refreshToken = str2;
        }

        @Nullable
        public static Wrapper parse(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new Wrapper(jSONObject.optLong("timestamp", Long.MIN_VALUE), jSONObject.optString("locale", Locale.getDefault().toString()), jSONObject.optString(KEY_REFRESH_TOKEN, null), jSONObject.optJSONObject("data"));
            } catch (JSONException unused) {
                Log.e(AppStartupConfigCache.LOG_TAG, "Failed to parse cached AppStartupConfig. The data will need to be requested again.");
                return null;
            }
        }

        @NonNull
        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("timestamp", this.timestamp);
                jSONObject.put("data", this.data);
                jSONObject.put(KEY_REFRESH_TOKEN, this.refreshToken);
                jSONObject.put("locale", this.locale);
            } catch (JSONException e) {
                Log.e(AppStartupConfigCache.LOG_TAG, "Failed to create AppStartupConfig cache wrapper", e);
            }
            return jSONObject.toString();
        }
    }

    @Inject
    public AppStartupConfigCache(@ApplicationContext Context context) {
        this.cacheFile = new AtomicFile(new File(context.getFilesDir(), FILE_NAME));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026 A[Catch: all -> 0x0010, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:14:0x0026, B:8:0x0012, B:9:0x001a), top: B:19:0x0002, inners: #1, #2 }] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.amazon.livingroom.appstartupconfig.AppStartupConfigCache.Wrapper load() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L10 java.io.IOException -> L12 java.io.FileNotFoundException -> L1a
            android.util.AtomicFile r2 = r4.cacheFile     // Catch: java.lang.Throwable -> L10 java.io.IOException -> L12 java.io.FileNotFoundException -> L1a
            byte[] r2 = r2.readFully()     // Catch: java.lang.Throwable -> L10 java.io.IOException -> L12 java.io.FileNotFoundException -> L1a
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L10 java.io.IOException -> L12 java.io.FileNotFoundException -> L1a
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L10 java.io.IOException -> L12 java.io.FileNotFoundException -> L1a
            goto L22
        L10:
            r0 = move-exception
            goto L2c
        L12:
            java.lang.String r1 = com.amazon.livingroom.appstartupconfig.AppStartupConfigCache.LOG_TAG     // Catch: java.lang.Throwable -> L10
            java.lang.String r2 = "Failed to read cached AppStartupConfig from storage"
            com.amazon.reporting.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L10
            goto L21
        L1a:
            java.lang.String r1 = com.amazon.livingroom.appstartupconfig.AppStartupConfigCache.LOG_TAG     // Catch: java.lang.Throwable -> L10
            java.lang.String r2 = "AppStartupConfig cache not found. This is expected on fist application run."
            com.amazon.reporting.Log.i(r1, r2)     // Catch: java.lang.Throwable -> L10
        L21:
            r1 = r0
        L22:
            if (r1 != 0) goto L26
            monitor-exit(r4)
            return r0
        L26:
            com.amazon.livingroom.appstartupconfig.AppStartupConfigCache$Wrapper r0 = com.amazon.livingroom.appstartupconfig.AppStartupConfigCache.Wrapper.parse(r1)     // Catch: java.lang.Throwable -> L10
            monitor-exit(r4)
            return r0
        L2c:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L10
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.livingroom.appstartupconfig.AppStartupConfigCache.load():com.amazon.livingroom.appstartupconfig.AppStartupConfigCache$Wrapper");
    }

    public synchronized void save(@NonNull Wrapper wrapper) {
        try {
            FileOutputStream fileOutputStreamStartWrite = this.cacheFile.startWrite();
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStreamStartWrite, StandardCharsets.UTF_8);
                outputStreamWriter.write(wrapper.toString());
                outputStreamWriter.flush();
                this.cacheFile.finishWrite(fileOutputStreamStartWrite);
            } catch (IOException unused) {
                Log.e(LOG_TAG, "Failed to cache AppStartupConfig to a local file. Future app sessions will need to request it remotely.");
                this.cacheFile.failWrite(fileOutputStreamStartWrite);
            }
        } catch (IOException unused2) {
            Log.e(LOG_TAG, "Failed to open AppStartupConfig cache file for writing. New values will not be cached.");
        }
    }
}
