package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ConfigurationManager {
    public static final String APP_CONFIGURATION_ID = "arn:aws:remote-config:us-west-2:455205533140:appConfig:apiz0o87";
    public static final String PERSISTED_CONFIG_VALUES_SHARED_PREFERENCES = "com.amazon.minerva.client.thirdparty.persistedConfigValues";
    public static final String SHARED_PREF_LAST_CUSTOM_ARCUS_SYNC_MILLIS = "lastCustomArcusSyncTimeMillis";
    public static final String SHARED_PREF_LAST_DEFAULT_ARCUS_SYNC_MILLIS = "lastDefaultArcusSyncTimeMillis";
    public static final String TAG = "ConfigurationManager";
    public static final boolean USE_CUSTOM_CONTEXT = true;
    public static final long syncUpConfigurationMillis = 86400000;
    public AssetFileParser mAssetFileParser;
    public Context mContext;
    public RemoteConfigurationManager mCustomRemoteConfigurationManager;
    public CustomRemoteMetricsConfigurationUpdater mCustomRemoteMetricsConfigurationUpdater;
    public RemoteConfigurationManager mDefaultRemoteConfigurationManager;
    public DefaultRemoteMetricsConfigurationUpdater mDefaultRemoteMetricsConfigurationUpdater;
    public MetricsConfigurationHelper mMetricsConfigurationHelper;
    public ScheduledExecutorService mScheduler;
    public SharedPreferences persistedConfigSharedPrefs;

    public ConfigurationManager(Context context) {
        if (context == null) {
            Log.e(TAG, "Context cannot be null.");
            return;
        }
        this.mContext = context;
        this.persistedConfigSharedPrefs = context.getSharedPreferences(PERSISTED_CONFIG_VALUES_SHARED_PREFERENCES, 0);
        this.mAssetFileParser = new AssetFileParser(context);
        MetricsConfigurationHelper metricsConfigurationHelper = new MetricsConfigurationHelper(context, this.mAssetFileParser);
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        AssetFileParser assetFileParser = this.mAssetFileParser;
        SharedPreferences sharedPreferences = this.persistedConfigSharedPrefs;
        this.mDefaultRemoteMetricsConfigurationUpdater = new DefaultRemoteMetricsConfigurationUpdater(metricsConfigurationHelper, assetFileParser, sharedPreferences);
        this.mCustomRemoteMetricsConfigurationUpdater = new CustomRemoteMetricsConfigurationUpdater(metricsConfigurationHelper, assetFileParser, sharedPreferences);
        this.mScheduler = Executors.newSingleThreadScheduledExecutor();
        initializeWithArcus();
    }

    public int[] getClientConfiguration() {
        int length = RemoteConfigurationConstant.values().length;
        int i = RemoteConfigurationConstant.THROTTLE_SWITCH.index;
        int i2 = RemoteConfigurationConstant.MAX_THROTTLE_CREDIT.index;
        int i3 = RemoteConfigurationConstant.DEFAULT_THROTTLE_CREDIT_HOUR.index;
        int i4 = RemoteConfigurationConstant.DEFAULT_SAMPLING_RATE.index;
        int i5 = RemoteConfigurationConstant.MAX_KEY_VALUE_PAIR_COUNT.index;
        int i6 = RemoteConfigurationConstant.MAX_KEY_SIZE.index;
        int i7 = RemoteConfigurationConstant.MAX_VALUE_SIZE.index;
        int i8 = RemoteConfigurationConstant.MAX_METRIC_EVENT_SIZE.index;
        int[] iArr = new int[length];
        iArr[i] = this.mMetricsConfigurationHelper.getThrottleConfiguration().getThrottleSwitch();
        iArr[i2] = this.mMetricsConfigurationHelper.getThrottleConfiguration().getMaxThrottleCredit();
        iArr[i3] = this.mMetricsConfigurationHelper.getThrottleConfiguration().getDefaultThrottleCreditHour();
        iArr[i4] = this.mMetricsConfigurationHelper.getSamplingConfiguration().getDefaultSamplingRate();
        iArr[i5] = this.mMetricsConfigurationHelper.getValidationConfiguration().getMaxKeyValuePairCount();
        iArr[i6] = this.mMetricsConfigurationHelper.getValidationConfiguration().getMaxKeySizeBytes();
        iArr[i7] = this.mMetricsConfigurationHelper.getValidationConfiguration().getMaxValueSizeBytes();
        iArr[i8] = this.mMetricsConfigurationHelper.getValidationConfiguration().getMaxMetricEventSizeBytes();
        return iArr;
    }

    public final long getInitialDelayMillis(boolean z, String str) {
        if (z) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.persistedConfigSharedPrefs.getLong(str, 0L);
            if (jCurrentTimeMillis >= 0 && jCurrentTimeMillis < 86400000) {
                return 86400000 - jCurrentTimeMillis;
            }
        }
        return 0L;
    }

    public MetricsConfigurationHelper getMetricsConfigurationHelper() {
        return this.mMetricsConfigurationHelper;
    }

    public final void initializeWithArcus() {
        String str = TAG;
        Log.i(str, "initialize custom Arcus");
        initializeWithCustomArcus();
        Log.i(str, "initialize default Arcus");
        initializeWithDefaultArcus();
    }

    public final void initializeWithCustomArcus() {
        String str = TAG;
        Log.d(str, "Starting custom Arcus initialization");
        this.mMetricsConfigurationHelper.setConfigurationFromCustomFile();
        String customerArcusAppId = this.mMetricsConfigurationHelper.getLocalConfiguration() != null ? this.mMetricsConfigurationHelper.getLocalConfiguration().getCustomerArcusAppId() : null;
        String appConfigId = CustomDeviceUtil.getInstance().getAppConfigId();
        if (customerArcusAppId == null || customerArcusAppId.isEmpty()) {
            customerArcusAppId = appConfigId;
        }
        Log.d(str, "Using custom Arcus config ID: " + customerArcusAppId);
        if (customerArcusAppId != null) {
            boolean z = true;
            RemoteConfigurationManager remoteConfigurationManagerCreateOrGet = new RemoteConfigurationManager.Builder(this.mContext, customerArcusAppId, true).createOrGet();
            this.mCustomRemoteConfigurationManager = remoteConfigurationManagerCreateOrGet;
            if (this.mMetricsConfigurationHelper.setCustomMetricsConfiguration(remoteConfigurationManagerCreateOrGet.openConfiguration())) {
                Log.i(str, "Custom arcus configuration is cached on the device and is being used");
            } else {
                Log.i(str, "Arcus custom configuration is not available on the device or not valid");
                z = false;
            }
            this.mScheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager.1
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = ConfigurationManager.TAG;
                    Log.i(str2, "periodically sync Custom Arcus App config");
                    String deviceType = CustomDeviceUtil.getInstance().getDeviceType();
                    if (deviceType != null) {
                        ConfigurationManager.this.mCustomRemoteConfigurationManager.openAttributes().addAttribute("deviceType", deviceType);
                    } else {
                        Log.w(str2, "Device Type is null. Cannot add attribute to RemoteConfigurationManager.");
                    }
                    ConfigurationManager configurationManager = ConfigurationManager.this;
                    configurationManager.mCustomRemoteConfigurationManager.sync(configurationManager.mCustomRemoteMetricsConfigurationUpdater);
                }
            }, getInitialDelayMillis(z, SHARED_PREF_LAST_CUSTOM_ARCUS_SYNC_MILLIS), 86400000L, TimeUnit.MILLISECONDS);
        }
    }

    public final void initializeWithDefaultArcus() {
        RemoteConfigurationManager remoteConfigurationManagerCreateOrGet = new RemoteConfigurationManager.Builder(this.mContext, APP_CONFIGURATION_ID, true).createOrGet();
        this.mDefaultRemoteConfigurationManager = remoteConfigurationManagerCreateOrGet;
        boolean metricsConfiguration = this.mMetricsConfigurationHelper.setMetricsConfiguration(remoteConfigurationManagerCreateOrGet.openConfiguration());
        Runnable runnable = new Runnable() { // from class: com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager.2
            @Override // java.lang.Runnable
            public void run() {
                String str = ConfigurationManager.TAG;
                Log.i(str, "Periodically syncing default Arcus App config");
                String deviceType = CustomDeviceUtil.getInstance().getDeviceType();
                if (deviceType != null) {
                    ConfigurationManager.this.mDefaultRemoteConfigurationManager.openAttributes().addAttribute("deviceType", deviceType);
                } else {
                    Log.w(str, "Device ID is null. Cannot add attribute to RemoteConfigurationManager.");
                }
                ConfigurationManager configurationManager = ConfigurationManager.this;
                configurationManager.mDefaultRemoteConfigurationManager.sync(configurationManager.mDefaultRemoteMetricsConfigurationUpdater);
            }
        };
        Log.i(TAG, "Default executor object " + this.mScheduler.toString());
        this.mScheduler.scheduleWithFixedDelay(runnable, getInitialDelayMillis(metricsConfiguration, SHARED_PREF_LAST_DEFAULT_ARCUS_SYNC_MILLIS), 86400000L, TimeUnit.MILLISECONDS);
    }
}
