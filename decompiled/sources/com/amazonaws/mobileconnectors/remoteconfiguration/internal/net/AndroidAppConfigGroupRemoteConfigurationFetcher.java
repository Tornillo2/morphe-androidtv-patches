package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import android.content.Context;
import android.net.TrafficStats;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidAppConfigGroupRemoteConfigurationFetcher implements AppConfigGroupRemoteConfigurationFetcher {
    public static final String QUERY_GROUP_CONFIGURATION_TARGET = "RemoteConfigurationDistributionService.QueryGroupConfiguration";
    public static final int REMOTECONFIG_THREAD_ID = 72284;
    public static final String REQUEST_KEY_APP_CONFIG_GROUP_ID = "appConfigGroupId";
    public static final String REQUEST_KEY_CLIENT_ATTRIBUTES = "clientAttributes";
    public static final String REQUEST_KEY_LAST_SEEN_ENTITY_TAGS = "lastSeenEntityTags";
    public static final String REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    public static final String RESPONSE_KEY_QUERY_CONFIGURATION_RESULTS = "queryConfigurationResults";
    public final Context mContext;
    public final URL mEndpoint;

    public AndroidAppConfigGroupRemoteConfigurationFetcher(Context context, URL url) {
        if (context == null) {
            throw new IllegalArgumentException("The context may not be null.");
        }
        if (url == null) {
            throw new IllegalArgumentException("The endpoint may not be null.");
        }
        this.mContext = context;
        this.mEndpoint = url;
    }

    public Map<String, RemoteConfiguration> buildRemoteConfigurationResponse(JSONObject jSONObject) {
        ConfigurationImpl configurationImpl;
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(RESPONSE_KEY_QUERY_CONFIGURATION_RESULTS);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                boolean z = jSONObject3.getBoolean(NetworkCommonUtils.RESPONSE_KEY_UPDATED);
                String string = jSONObject3.getString(NetworkCommonUtils.RESPONSE_KEY_ENTITY_TAG);
                try {
                    configurationImpl = new ConfigurationImpl(jSONObject3.getString(NetworkCommonUtils.RESPONSE_KEY_CONFIGURATION), new Date());
                } catch (JSONException unused) {
                    configurationImpl = null;
                }
                map.put(next, new RemoteConfigurationImpl(configurationImpl, next, 2, string, z));
            }
            return map;
        } catch (JSONException e) {
            throw new NetworkException("Expected elements missing from the response", e);
        }
    }

    public byte[] buildRequestBody(String str, Attributes attributes, Map<String, String> map, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(REQUEST_KEY_APP_CONFIG_GROUP_ID, str);
            jSONObject.put("localConfigurationInstanceId", str2);
            if (map != null) {
                jSONObject.put(REQUEST_KEY_LAST_SEEN_ENTITY_TAGS, new JSONObject(map));
            }
            Map<String, Object> allAttributes = attributes.getAllAttributes();
            if (allAttributes != null) {
                jSONObject.put("clientAttributes", new JSONObject(allAttributes).toString());
            }
            return jSONObject.toString().getBytes(StandardCharsets.UTF_8);
        } catch (JSONException e) {
            throw new NetworkException("Error building request", e);
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AppConfigGroupRemoteConfigurationFetcher
    public Map<String, RemoteConfiguration> fetch(String str, Attributes attributes, Map<String, String> map, String str2) throws Throwable {
        HttpURLConnection httpURLConnection;
        if (str == null) {
            throw new IllegalArgumentException("The App Configuration Group ID may not be null");
        }
        if (attributes == null) {
            throw new IllegalArgumentException("The attributes may not be null");
        }
        if (!NetworkCommonUtils.isNetworkAvailable(this.mContext)) {
            throw new NetworkException("There is no network connectivity.");
        }
        TrafficStats.setThreadStatsTag(REMOTECONFIG_THREAD_ID);
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) this.mEndpoint.openConnection();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            NetworkCommonUtils.configureConnection(httpURLConnection);
            setHeaders(httpURLConnection);
            NetworkCommonUtils.writeRequest(httpURLConnection, buildRequestBody(str, attributes, map, str2));
            Map<String, RemoteConfiguration> mapBuildRemoteConfigurationResponse = buildRemoteConfigurationResponse(NetworkCommonUtils.readResponse(httpURLConnection));
            httpURLConnection.disconnect();
            return mapBuildRemoteConfigurationResponse;
        } catch (IOException e2) {
            e = e2;
            throw new NetworkException("Unable to open connection", e);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection2 = httpURLConnection;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    public void setHeaders(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Content-Type", NetworkCommonUtils.XAMZ_JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty(NetworkCommonUtils.HEADER_AMZ_TARGET, QUERY_GROUP_CONFIGURATION_TARGET);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.setRequestProperty("User-Agent", NetworkCommonUtils.getArcusUserAgent());
    }
}
