package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricsConfigurationHelper {
    public static final String ORIGIN = "configuration";
    public static final MinervaLogger log = new MinervaLogger("MetricsConfigurationHelper");
    public ThrottleConfiguration mActiveThrottleConfiguration;
    public AssetFileParser mAssetFileParser;
    public Context mContext;
    public StorageConfiguration mCustomStorageConfiguration;
    public ThrottleConfiguration mCustomThrottleConfiguration;
    public UploadConfiguration mCustomUploadConfiguration;
    public DenyListConfiguration mDenyListConfiguration;
    public LocalConfiguration mLocalConfiguration;
    public MultiProcessConfiguration mMultiProcessConfiguration;
    public SamplingConfiguration mSamplingConfiguration;
    public StorageConfiguration mStorageConfiguration;
    public ThrottleConfiguration mThrottleConfiguration;
    public ThrottleConfiguration mThrottleHardLimitsConfiguration;
    public UploadConfiguration mUploadConfiguration;
    public ValidationConfiguration mValidationConfiguration;

    public MetricsConfigurationHelper(Context context, AssetFileParser assetFileParser) {
        this.mContext = context;
        this.mAssetFileParser = assetFileParser;
        setMetricsConfigurationJson(assetFileParser.parseMetricsConfiguration(DefaultMetricsConfiguration.DEFAULT_CONFIGURATION));
    }

    public DenyListConfiguration getDenyListConfiguration() {
        return this.mDenyListConfiguration;
    }

    public LocalConfiguration getLocalConfiguration() {
        return this.mLocalConfiguration;
    }

    public MultiProcessConfiguration getMultiProcessConfiguration() {
        return this.mMultiProcessConfiguration;
    }

    public SamplingConfiguration getSamplingConfiguration() {
        return this.mSamplingConfiguration;
    }

    public StorageConfiguration getStorageConfiguration() {
        StorageConfiguration storageConfiguration = this.mCustomStorageConfiguration;
        return storageConfiguration != null ? storageConfiguration : this.mStorageConfiguration;
    }

    public ThrottleConfiguration getThrottleConfiguration() {
        return this.mActiveThrottleConfiguration;
    }

    public UploadConfiguration getUploadConfiguration() {
        UploadConfiguration uploadConfiguration = this.mCustomUploadConfiguration;
        return uploadConfiguration != null ? uploadConfiguration : this.mUploadConfiguration;
    }

    public ValidationConfiguration getValidationConfiguration() {
        return this.mValidationConfiguration;
    }

    public final void setActiveThrottle() {
        ThrottleConfiguration throttleConfiguration = this.mCustomThrottleConfiguration;
        if (throttleConfiguration == null) {
            this.mActiveThrottleConfiguration = new ThrottleConfiguration(this.mThrottleConfiguration.getThrottleSwitch(), this.mThrottleConfiguration.getMaxThrottleCredit(), this.mThrottleConfiguration.getDefaultThrottleCreditHour());
        } else {
            this.mActiveThrottleConfiguration = new ThrottleConfiguration(throttleConfiguration.getThrottleSwitch(), this.mCustomThrottleConfiguration.getMaxThrottleCredit(), this.mCustomThrottleConfiguration.getDefaultThrottleCreditHour(), this.mThrottleHardLimitsConfiguration);
        }
        log.debug("configuration".concat(String.format(Locale.US, ", ACTIVE throttle configuration (last printout is the active one used) throttleSwitch: %d, maxThrottleCredit: %d, defaultThrottleCreditHour: %d", Integer.valueOf(this.mActiveThrottleConfiguration.getThrottleSwitch()), Integer.valueOf(this.mActiveThrottleConfiguration.getMaxThrottleCredit()), Integer.valueOf(this.mActiveThrottleConfiguration.getDefaultThrottleCreditHour()))));
    }

    public void setConfigurationFromCustomFile() {
        log.info("configuration, set custom starter config from customConfiguration file");
        JSONObject metricsConfiguration = this.mAssetFileParser.parseMetricsConfiguration(this.mContext, MetricsConfigurationConstants.CUSTOM_CONFIGURATION_FILE_NAME);
        setCustomMetricsConfigurationJson(metricsConfiguration);
        setLocalConfigurationJson(metricsConfiguration);
    }

    public boolean setCustomMetricsConfiguration(Configuration configuration) {
        JSONObject asJsonObject = configuration.getAsJsonObject();
        if (asJsonObject.length() == 0) {
            return false;
        }
        try {
            return setCustomMetricsConfigurationJson(this.mAssetFileParser.getDomainMetricsConfiguration(asJsonObject));
        } catch (JSONException e) {
            log.error("configuration".concat(String.format(", An error occurs when retrieving domain configuration. %s", e.getMessage())));
            return false;
        }
    }

    public boolean setCustomMetricsConfigurationJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        setCustomStorageConfiguration(jSONObject);
        setCustomUploadConfiguration(jSONObject);
        setCustomThrottleConfiguration(jSONObject);
        setActiveThrottle();
        setMultiProcessConfiguration(jSONObject);
        return true;
    }

    public final void setCustomStorageConfiguration(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optJSONObject(MetricsConfigurationConstants.STORAGE_CONFIGURATION) == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.STORAGE_CONFIGURATION);
        long jOptLong = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_OPEN_TIME_MILLIS, this.mAssetFileParser.getFallbackMaxBatchOpenTimeMillis());
        long jOptLong2 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.CHECK_BATCH_OPEN_TIME_MILLIS, this.mAssetFileParser.getFallbackCheckBatchOpenTimeMillis());
        long jOptLong3 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_ENTRIES, this.mAssetFileParser.getFallbackMaxBatchEntries());
        long jOptLong4 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_SIZE_BYTES, this.mAssetFileParser.getFallbackMaxBatchSizeBytes());
        long jOptLong5 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_STORAGE_SPACE_BYTES, this.mAssetFileParser.getFallbackMaxStorageSpaceBytes());
        long jOptLong6 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_NUMBER_OF_BATCH_FILES, this.mAssetFileParser.getFallbackMaxNumberOfBatchFiles());
        long jOptLong7 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.EXPIRY_TIME_MILLIS, this.mAssetFileParser.getFallbackExpiryTimeMillis());
        long jOptLong8 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.PURGE_PERIOD_MILLIS, this.mAssetFileParser.getFallbackPurgePeriodMillis());
        long jOptLong9 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.TRANSMISSION_PERIOD_MILLIS, this.mAssetFileParser.getFallbackTransmissionPeriodMillis());
        log.debug("configuration".concat(String.format(Locale.US, ", custom storage configuration, maxBatchOpenTimeMillis: %d, checkBatchOpenTimeMillis: %d, maxBatchEntries: %d, maxBatchSizeBytes: %d, maxStorageSpaceBytes: %d, maxNumberOfBatchFiles: %d, expiryTimeMillis: %d, purgePeriodMillis: %d, transmissionPeriodMillis: %d", Long.valueOf(jOptLong), Long.valueOf(jOptLong2), Long.valueOf(jOptLong3), Long.valueOf(jOptLong4), Long.valueOf(jOptLong5), Long.valueOf(jOptLong6), Long.valueOf(jOptLong7), Long.valueOf(jOptLong8), Long.valueOf(jOptLong9))));
        this.mCustomStorageConfiguration = new StorageConfiguration(jOptLong, jOptLong2, jOptLong3, jOptLong4, jOptLong5, jOptLong6, jOptLong7, jOptLong8, jOptLong9);
    }

    public void setCustomThrottleConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION) : this.mAssetFileParser.getFallbackThrottleConfiguration();
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.THROTTLE_SWITCH, this.mAssetFileParser.getFallbackThrottleSwitch());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_THROTTLE_CREDIT, this.mAssetFileParser.getFallbackMaxThrottleCredit());
        int iOptInt3 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.DEFAULT_THROTTLE_CREDIT_HOUR, this.mAssetFileParser.getFallbackDefaultThrottleCreditHour());
        log.debug("configuration".concat(String.format(Locale.US, ", custom throttle configuration, throttleSwitch: %d, maxThrottleCount: %d, defaultThrottleCreditHour: %d", Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Integer.valueOf(iOptInt3))));
        this.mCustomThrottleConfiguration = new ThrottleConfiguration(iOptInt, iOptInt2, iOptInt3);
    }

    public final void setCustomUploadConfiguration(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optJSONObject(MetricsConfigurationConstants.UPLOAD_CONFIGURATION) == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.UPLOAD_CONFIGURATION);
        String strOptString = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.URL_ENDPOINT, this.mAssetFileParser.getFallbackUrlEndpoint());
        String strOptString2 = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.ION_FORMAT, this.mAssetFileParser.getFallbackIonFormat());
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.CONNECT_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackConnectTimeoutMillis());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.READ_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackReadTimeoutMillis());
        long jOptLong = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.WAKE_LOCK_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackWakeLockTimeoutMillis());
        String strOptString3 = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.KPI_REGION, this.mAssetFileParser.getFallbackKpiRegion());
        log.debug("configuration".concat(String.format(Locale.US, ", custom upload configuration, urlEndpoint: %s, ionFormat: %s, connectTimeout: %d, readTimeout: %d, wakeLockTimeout: %d, kpiRegion: %s", strOptString, strOptString2, Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Long.valueOf(jOptLong), strOptString3)));
        this.mCustomUploadConfiguration = new UploadConfiguration(strOptString, strOptString2, iOptInt, iOptInt2, jOptLong, strOptString3);
    }

    public final void setDenyListConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.DENYLIST_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.DENYLIST_CONFIGURATION) : this.mAssetFileParser.getFallbackDenyListConfiguration();
        String strOptString = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.DENYLIST_BITS, this.mAssetFileParser.getFallbackDenyListBits());
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.BITS_SIZE, this.mAssetFileParser.getFallbackDenyListBitSize());
        log.debug("configuration".concat(String.format(Locale.US, ", denylist configuration denyListBits: %s, bitsSize: %d", strOptString, Integer.valueOf(iOptInt))));
        this.mDenyListConfiguration = new DenyListConfiguration(jSONObjectOptJSONObject.optJSONArray(MetricsConfigurationConstants.EXPLICIT_DENYLIST), strOptString, iOptInt);
    }

    public final void setLocalConfigurationJson(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.LOCAL_CONFIGURATION)) == null) {
            return;
        }
        this.mLocalConfiguration = new LocalConfiguration(jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.LOCAL_CUSTOMER_ARCUS_APP_ID));
    }

    public boolean setMetricsConfiguration(Configuration configuration) {
        JSONObject asJsonObject = configuration.getAsJsonObject();
        if (asJsonObject.length() == 0) {
            return false;
        }
        try {
            return setMetricsConfigurationJson(this.mAssetFileParser.getDomainMetricsConfiguration(asJsonObject));
        } catch (JSONException e) {
            log.error("configuration".concat(String.format(", An error occurs when retrieving domain configuration. %s", e.getMessage())));
            return false;
        }
    }

    public boolean setMetricsConfigurationJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        setValidationConfiguration(jSONObject);
        setThrottleConfiguration(jSONObject);
        setThrottleHardLimitsConfiguration(jSONObject);
        setActiveThrottle();
        setSamplingConfiguration(jSONObject);
        setDenyListConfiguration(jSONObject);
        setStorageConfiguration(jSONObject);
        setUploadConfiguration(jSONObject);
        return true;
    }

    public void setMultiProcessConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.MULTI_PROCESS_CONFIGURATION);
        if (jSONObjectOptJSONObject != null) {
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray(MetricsConfigurationConstants.SECONDARY_PROCESSES);
            HashSet hashSet = new HashSet();
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (strOptString == null || strOptString.isEmpty()) {
                        log.warn("setMultiProcessConfiguration: Invalid configuration found!");
                    } else {
                        hashSet.add(strOptString);
                        log.debug("setMultiProcessConfiguration: Adding process: " + strOptString + " to list");
                    }
                }
            }
            this.mMultiProcessConfiguration = new MultiProcessConfiguration(jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.MAIN_PROCESS_NAME), hashSet);
        }
    }

    public final void setSamplingConfiguration(JSONObject jSONObject) {
        int iOptInt = (jSONObject.optJSONObject(MetricsConfigurationConstants.SAMPLING_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.SAMPLING_CONFIGURATION) : this.mAssetFileParser.getFallbackSamplingConfiguration()).optInt(MetricsConfigurationConstants.DEFAULT_SAMPLING_RATE, this.mAssetFileParser.getFallbackDefaultSamplingRate());
        log.debug("configuration".concat(String.format(Locale.US, ", sampling configuration, defaultSamplingRate : %d", Integer.valueOf(iOptInt))));
        this.mSamplingConfiguration = new SamplingConfiguration(iOptInt);
    }

    public final void setStorageConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.STORAGE_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.STORAGE_CONFIGURATION) : this.mAssetFileParser.getFallbackStorageConfiguration();
        long jOptLong = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_OPEN_TIME_MILLIS, this.mAssetFileParser.getFallbackMaxBatchOpenTimeMillis());
        long jOptLong2 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.CHECK_BATCH_OPEN_TIME_MILLIS, this.mAssetFileParser.getFallbackCheckBatchOpenTimeMillis());
        long jOptLong3 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_ENTRIES, this.mAssetFileParser.getFallbackMaxBatchEntries());
        long jOptLong4 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_BATCH_SIZE_BYTES, this.mAssetFileParser.getFallbackMaxBatchSizeBytes());
        long jOptLong5 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_STORAGE_SPACE_BYTES, this.mAssetFileParser.getFallbackMaxStorageSpaceBytes());
        long jOptLong6 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.MAX_NUMBER_OF_BATCH_FILES, this.mAssetFileParser.getFallbackMaxNumberOfBatchFiles());
        long jOptLong7 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.EXPIRY_TIME_MILLIS, this.mAssetFileParser.getFallbackExpiryTimeMillis());
        long jOptLong8 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.PURGE_PERIOD_MILLIS, this.mAssetFileParser.getFallbackPurgePeriodMillis());
        long jOptLong9 = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.TRANSMISSION_PERIOD_MILLIS, this.mAssetFileParser.getFallbackTransmissionPeriodMillis());
        log.debug("configuration".concat(String.format(Locale.US, ", storage configuration: maxBatchOpenTimeMillis: %d, checkBatchOpenTimeMillis: %d, maxBatchEntries: %d, maxBatchSizeBytes: %d, maxStorageSpaceBytes: %d, maxNumberOfBatchFiles: %d, expiryTimeMillis: %d, purgePeriodMillis: %d, transmissionPeriodMillis: %d", Long.valueOf(jOptLong), Long.valueOf(jOptLong2), Long.valueOf(jOptLong3), Long.valueOf(jOptLong4), Long.valueOf(jOptLong5), Long.valueOf(jOptLong6), Long.valueOf(jOptLong7), Long.valueOf(jOptLong8), Long.valueOf(jOptLong9))));
        this.mStorageConfiguration = new StorageConfiguration(jOptLong, jOptLong2, jOptLong3, jOptLong4, jOptLong5, jOptLong6, jOptLong7, jOptLong8, jOptLong9);
    }

    public final void setThrottleConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION) : this.mAssetFileParser.getFallbackThrottleConfiguration();
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.THROTTLE_SWITCH, this.mAssetFileParser.getFallbackThrottleSwitch());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_THROTTLE_CREDIT, this.mAssetFileParser.getFallbackMaxThrottleCredit());
        int iOptInt3 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.DEFAULT_THROTTLE_CREDIT_HOUR, this.mAssetFileParser.getFallbackDefaultThrottleCreditHour());
        log.debug("configuration".concat(String.format(Locale.US, ", throttle configuration, throttleSwitch: %d, maxThrottleCredit: %d, defaultThrottleCreditHour: %d", Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Integer.valueOf(iOptInt3))));
        this.mThrottleConfiguration = new ThrottleConfiguration(iOptInt, iOptInt2, iOptInt3);
    }

    public final void setThrottleHardLimitsConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION_HARD_LIMITS) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION_HARD_LIMITS) : this.mAssetFileParser.getFallbackThrottleConfigurationHardLimits();
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.THROTTLE_SWITCH_HARD_LIMIT, this.mAssetFileParser.getFallbackThrottleSwitchHardLimit());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_THROTTLE_CREDIT_HARD_LIMIT, this.mAssetFileParser.getFallbackMaxThrottleCreditHardLimit());
        int iOptInt3 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.DEFAULT_THROTTLE_CREDIT_HOUR_HARD_LIMIT, this.mAssetFileParser.getFallbackDefaultThrottleCreditHourHardLimit());
        log.debug("configuration".concat(String.format(Locale.US, ", throttle hard limit configuration, throttleSwitchHardLimit: %d, maxThrottleCreditHardLimit: %d, defaultThrottleCreditHourHardLimit: %d", Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Integer.valueOf(iOptInt3))));
        this.mThrottleHardLimitsConfiguration = new ThrottleConfiguration(iOptInt, iOptInt2, iOptInt3);
    }

    public final void setUploadConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.UPLOAD_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.UPLOAD_CONFIGURATION) : this.mAssetFileParser.getFallbackUploadConfiguration();
        String strOptString = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.URL_ENDPOINT, this.mAssetFileParser.getFallbackUrlEndpoint());
        String strOptString2 = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.ION_FORMAT, this.mAssetFileParser.getFallbackIonFormat());
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.CONNECT_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackConnectTimeoutMillis());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.READ_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackReadTimeoutMillis());
        long jOptLong = jSONObjectOptJSONObject.optLong(MetricsConfigurationConstants.WAKE_LOCK_TIMEOUT_MILLIS, this.mAssetFileParser.getFallbackWakeLockTimeoutMillis());
        String strOptString3 = jSONObjectOptJSONObject.optString(MetricsConfigurationConstants.KPI_REGION, this.mAssetFileParser.getFallbackKpiRegion());
        log.debug("configuration".concat(String.format(Locale.US, ", upload configuration: urlEndpoint: %s, ionFormat: %s, connectTimeout: %d, readTimeout: %d, wakeLockTimeout: %d, kpiRegion: %s", strOptString, strOptString2, Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Long.valueOf(jOptLong), strOptString3)));
        this.mUploadConfiguration = new UploadConfiguration(strOptString, strOptString2, iOptInt, iOptInt2, jOptLong, strOptString3);
    }

    public final void setValidationConfiguration(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MetricsConfigurationConstants.VALIDATION_CONFIGURATION) != null ? jSONObject.optJSONObject(MetricsConfigurationConstants.VALIDATION_CONFIGURATION) : this.mAssetFileParser.getFallbackValidationConfiguration();
        int iOptInt = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_KEY_VALUE_PAIR_COUNT, this.mAssetFileParser.getFallbackMaxKeyValuePairCount());
        int iOptInt2 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_METRIC_EVENT_SIZE_BYTES, this.mAssetFileParser.getFallbackMaxMetricEventSizeBytes());
        int iOptInt3 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_KEY_SIZE_BYTES, this.mAssetFileParser.getFallbackMaxKeySizeBytes());
        int iOptInt4 = jSONObjectOptJSONObject.optInt(MetricsConfigurationConstants.MAX_VALUE_SIZE_BYTES, this.mAssetFileParser.getFallbackMaxValueSizeBytes());
        log.debug("configuration".concat(String.format(Locale.US, ", validation configuration, maxKeyValuePairCount: %d, maxMetricEventSizeBytes: %d, maxKeySizeBytes: %d, maxValueSizeBytes: %d", Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2), Integer.valueOf(iOptInt3), Integer.valueOf(iOptInt4))));
        this.mValidationConfiguration = new ValidationConfiguration(iOptInt, iOptInt2, iOptInt3, iOptInt4);
    }
}
