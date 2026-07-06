package com.amazon.livingroom.deviceproperties;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.ignitionshared.MapSqliteHelper;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class DeviceIdProvider {
    public static final String CACHED_DID_KEY = "DEVICE_ID";
    public static final String DEVICE_ID_ORIGIN = "DeviceIdOrigin_";
    public static final String LOG_TAG = "DeviceIdProvider";
    public static final String UNKNOWN = "Unknown";
    public static final String UUID_PREFIX = "uuid";
    public final Context context;
    public final LocalStorage localStorage;
    public final MapSqliteHelper mapSqliteHelper;
    public final MetricsRecorder metricsRecorder;
    public final SharedPreferences sharedPreferences;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MinervaMetricConstants {
        public static final String DEVICE_ID_NOT_SAVED = "DeviceIdProvider.DeviceIdNotSaved";
        public static final String DID_ORIGIN_KEY = "DeviceIdProvider.DeviceIdOrigin_";
        public static final String GET_DEVICE_ID_FAILED = "DeviceIdProvider.GetDeviceIdFailed";
        public static final String LOCAL_ID_FOUND = "DeviceIdProvider.LocalIDFound";
        public static final String LOCAL_ID_NOT_FOUND = "DeviceIdProvider.LocalIDNotFound";
        public static final String USING_ANDROID_ID_DEVICE_ID = "DeviceIdProvider.UsingAndroidId";
        public static final String USING_NEW_UUID_DEVICE_ID = "DeviceIdProvider.UsingNewUUID";
        public static final String USING_SQLITE_DEVICE_ID = "DeviceIdProvider.UsingSQLiteDeviceId";
    }

    @Inject
    public DeviceIdProvider(@NonNull @ApplicationContext Context context, @NonNull MapSqliteHelper mapSqliteHelper, @NonNull MetricsRecorder metricsRecorder, @NonNull LocalStorage localStorage, @Named(Names.DEVICE_INFO) SharedPreferences sharedPreferences) {
        this.context = context;
        this.mapSqliteHelper = mapSqliteHelper;
        this.metricsRecorder = metricsRecorder;
        this.localStorage = localStorage;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @SuppressLint({"HardwareIds"})
    public synchronized String getDeviceId() {
        String str;
        MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.DEVICE_ID_PROVIDER_SCHEMA_ID);
        try {
            String string = this.sharedPreferences.getString(CACHED_DID_KEY, null);
            if (!TextUtils.isEmpty(string)) {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.DID_ORIGIN_KEY + this.sharedPreferences.getString(DEVICE_ID_ORIGIN, UNKNOWN), 1);
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.LOCAL_ID_FOUND, 1);
                return string;
            }
            metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.LOCAL_ID_NOT_FOUND, 1);
            String deviceId = this.mapSqliteHelper.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.USING_SQLITE_DEVICE_ID, 1);
                str = "SQLite";
            } else if (this.localStorage.isEmpty()) {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.USING_NEW_UUID_DEVICE_ID, 1);
                deviceId = UUID_PREFIX + UUID.randomUUID().toString().replaceAll("-", "");
                str = "UUID";
            } else {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.USING_ANDROID_ID_DEVICE_ID, 1);
                deviceId = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
                str = "AndroidId";
            }
            if (TextUtils.isEmpty(deviceId)) {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.GET_DEVICE_ID_FAILED, 1);
                throw new IllegalStateException("Failed to get Device ID");
            }
            if (!this.sharedPreferences.edit().putString(CACHED_DID_KEY, deviceId).putString(DEVICE_ID_ORIGIN, str).commit()) {
                metricGroupCreateMetricGroup.addCounterMetric(MinervaMetricConstants.DEVICE_ID_NOT_SAVED, 1);
                Log.e(LOG_TAG, "An error occurred while trying to save the device ID in local storage.");
            }
            return deviceId;
        } finally {
            this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
        }
    }
}
