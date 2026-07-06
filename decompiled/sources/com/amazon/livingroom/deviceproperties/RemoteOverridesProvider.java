package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.ignitionshared.Singleton;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider;
import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class RemoteOverridesProvider implements OverridableDeviceProperties.OverridesProvider {
    public static final String CONFIG_REQUESTS_METRIC = "RemoteOverrides.ConfigRequests";
    public static final long GET_APP_STARTUP_CONFIG_TIMEOUT_MS = 1000;
    public static final String LOG_TAG = "RemoteOverridesProvider";
    public static final String VALUE_KEY = "value";
    public final AppStartupConfigProvider appStartupConfigProvider;
    public final Context context;
    public final MetricsRecorder metricsRecorder;
    public final Singleton<Map<String, String>> overrides = new Singleton<>(new Singleton.Factory() { // from class: com.amazon.livingroom.deviceproperties.RemoteOverridesProvider$$ExternalSyntheticLambda0
        @Override // com.amazon.ignitionshared.Singleton.Factory
        public final Object createInstance(Context context) {
            return this.f$0.getOverrides(context);
        }
    });

    @Inject
    public RemoteOverridesProvider(@ApplicationContext Context context, AppStartupConfigProvider appStartupConfigProvider, MetricsRecorder metricsRecorder) {
        this.context = context;
        this.appStartupConfigProvider = appStartupConfigProvider;
        this.metricsRecorder = metricsRecorder;
    }

    @NonNull
    public final Map<String, String> extractNestedOverrides(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return Collections.EMPTY_MAP;
        }
        HashMap map = new HashMap(jSONObject.length());
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
            if (jSONObjectOptJSONObject == null) {
                Log.w(LOG_TAG, "Nested property override for '" + next + "' object is null");
            } else if (jSONObjectOptJSONObject.isNull("value")) {
                Log.w(LOG_TAG, "Nested property override for '" + next + "' value is null");
            } else {
                map.put(next, jSONObjectOptJSONObject.optString("value"));
            }
        }
        return map;
    }

    @NonNull
    public final Map<String, String> extractOverrides(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return new HashMap();
        }
        HashMap map = new HashMap(jSONObject.length());
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (jSONObject.isNull(next)) {
                Log.w(LOG_TAG, "Property override for '" + next + "'is null");
            } else {
                map.put(next, jSONObject.optString(next));
            }
        }
        return map;
    }

    @Nullable
    public final JSONObject getACMNestedOverridesJson(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONObject("extraConfig").getJSONObject("devicePropertyOverridesNested");
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to extract nested device property overrides from ACM.", e);
            return new JSONObject();
        }
    }

    public final JSONObject getAppStartupConfig() {
        MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.REMOTE_OVERRIDES_SCHEMA_ID);
        JSONObject appStartupConfig = this.appStartupConfigProvider.getAppStartupConfig(1000L, TimeUnit.MILLISECONDS);
        if (appStartupConfig != null) {
            metricGroupCreateMetricGroup.addCounterMetric(CONFIG_REQUESTS_METRIC, 1);
            this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
            return appStartupConfig;
        }
        Log.w(LOG_TAG, "No AppStartupConfig available. Default values will be used for the current application session.");
        metricGroupCreateMetricGroup.addCounterMetric(CONFIG_REQUESTS_METRIC, 0);
        this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
        return null;
    }

    @Nullable
    public final JSONObject getDISOverridesJson(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("deviceConfig").getJSONObject("additionalConfig").getJSONArray("values");
            int length = jSONArray.length();
            JSONObject jSONObject2 = null;
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    if ("devicePropertyOverrides".equals(jSONObject3.getString("key"))) {
                        jSONObject2 = jSONObject3;
                    }
                } catch (JSONException unused) {
                }
            }
            if (jSONObject2 != null) {
                return new JSONObject(jSONObject2.getString("value"));
            }
            Log.i(LOG_TAG, "AppStartupConfig contains no device property overrides");
            return null;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to extract device property overrides from DIS.", e);
            return null;
        }
    }

    @Override // com.amazon.livingroom.deviceproperties.OverridableDeviceProperties.OverridesProvider
    @NonNull
    public Map<String, String> getOverrides() {
        return this.overrides.getInstance(this.context);
    }

    @NonNull
    public final Map<String, String> getOverrides(Context context) {
        JSONObject appStartupConfig = getAppStartupConfig();
        if (appStartupConfig == null) {
            return Collections.EMPTY_MAP;
        }
        JSONObject dISOverridesJson = getDISOverridesJson(appStartupConfig);
        JSONObject aCMNestedOverridesJson = getACMNestedOverridesJson(appStartupConfig);
        Map<String, String> mapExtractOverrides = extractOverrides(dISOverridesJson);
        HashMap map = (HashMap) mapExtractOverrides;
        map.putAll(extractNestedOverrides(aCMNestedOverridesJson));
        if (map.isEmpty()) {
            Log.w(LOG_TAG, "No overrides provided. Default values will be used for the current application session.");
        }
        return mapExtractOverrides;
    }
}
