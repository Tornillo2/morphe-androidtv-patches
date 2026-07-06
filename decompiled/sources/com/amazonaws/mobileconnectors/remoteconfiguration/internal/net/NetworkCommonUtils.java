package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NetworkCommonUtils {
    public static final Pattern ARCUS_CLIENT_VERSION_PATTERN = Pattern.compile("[1-9][0-9]*\\.[0-9]+\\.[0-9]+");
    public static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    public static final int DEFAULT_READ_TIMEOUT = 15000;
    public static final String GZIP_ENCODING = "gzip";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_AMZ_TARGET = "X-Amz-Target";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final int HTTP_OK_STATUS_CODE = 200;
    public static final String REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    public static final String RESPONSE_KEY_CONFIGURATION = "resultVariables";
    public static final String RESPONSE_KEY_ENTITY_TAG = "entityTag";
    public static final String RESPONSE_KEY_UPDATED = "updatedConfigurationAvailable";
    public static final String USER_AGENT_PREFIX = "Arcus-Android/";
    public static final String XAMZ_JSON_CONTENT_TYPE = "application/x-amz-json-1.1";

    public static void closeErrorStreamFromConnection(HttpURLConnection httpURLConnection) {
        try {
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                errorStream.close();
            }
        } catch (IOException unused) {
        }
    }

    public static void configureConnection(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches(false);
    }

    public static String getArcusUserAgent() {
        Matcher matcher = ARCUS_CLIENT_VERSION_PATTERN.matcher("");
        return RemoteInput$$ExternalSyntheticOutline0.m(USER_AGENT_PREFIX, matcher.find() ? matcher.group() : "1.3.x");
    }

    public static InputStream getInputStreamFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        return "gzip".equalsIgnoreCase(httpURLConnection.getHeaderField("Content-Encoding")) ? new GZIPInputStream(httpURLConnection.getInputStream()) : httpURLConnection.getInputStream();
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0048 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject readResponse(java.net.HttpURLConnection r3) {
        /*
            int r0 = r3.getResponseCode()     // Catch: java.io.IOException -> L72
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L63
            java.io.InputStream r3 = getInputStreamFromConnection(r3)     // Catch: org.json.JSONException -> L51 java.io.IOException -> L5a
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3b
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3b
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L3b
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L3b
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L27
            r1.<init>()     // Catch: java.lang.Throwable -> L27
        L1d:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L27
            if (r2 == 0) goto L29
            r1.append(r2)     // Catch: java.lang.Throwable -> L27
            goto L1d
        L27:
            r1 = move-exception
            goto L3d
        L29:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L27
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L27
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L27
            r0.close()     // Catch: java.lang.Throwable -> L3b
            if (r3 == 0) goto L3a
            r3.close()     // Catch: org.json.JSONException -> L51 java.io.IOException -> L5a
        L3a:
            return r2
        L3b:
            r0 = move-exception
            goto L46
        L3d:
            r0.close()     // Catch: java.lang.Throwable -> L41
            goto L45
        L41:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch: java.lang.Throwable -> L3b
        L45:
            throw r1     // Catch: java.lang.Throwable -> L3b
        L46:
            if (r3 == 0) goto L50
            r3.close()     // Catch: java.lang.Throwable -> L4c
            goto L50
        L4c:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch: org.json.JSONException -> L51 java.io.IOException -> L5a
        L50:
            throw r0     // Catch: org.json.JSONException -> L51 java.io.IOException -> L5a
        L51:
            r3 = move-exception
            com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException r0 = new com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException
            java.lang.String r1 = "Invalid response format."
            r0.<init>(r1, r3)
            throw r0
        L5a:
            r3 = move-exception
            com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException r0 = new com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException
            java.lang.String r1 = "Error reading response."
            r0.<init>(r1, r3)
            throw r0
        L63:
            closeErrorStreamFromConnection(r3)
            com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException r3 = new com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException
            java.lang.String r1 = "Request unsuccessful. Received code "
            java.lang.String r0 = android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m(r1, r0)
            r3.<init>(r0)
            throw r3
        L72:
            r3 = move-exception
            com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException r0 = new com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException
            java.lang.String r1 = "Unable to get response code."
            r0.<init>(r1, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.NetworkCommonUtils.readResponse(java.net.HttpURLConnection):org.json.JSONObject");
    }

    public static void writeRequest(HttpURLConnection httpURLConnection, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
            } finally {
            }
        } catch (IOException e) {
            throw new NetworkException("Error writing the request", e);
        }
    }
}
