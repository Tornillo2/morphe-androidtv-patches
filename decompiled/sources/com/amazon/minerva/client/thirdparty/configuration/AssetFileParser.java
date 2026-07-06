package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AssetFileParser {
    public static final String TAG = "AssetFileParser";
    public long mCheckBatchOpenTimeMillis;
    public int mConnectTimeoutMillis;
    public Context mContext;
    public int mDefaultSamplingRate;
    public int mDefaultThrottleCreditHour;
    public int mDefaultThrottleCreditHourHardLimit;
    public int mDenyListBitSize;
    public String mDenyListBits;
    public JSONObject mDenyListObject;
    public long mExpiryTimeMillis;
    public String mIonFormat;
    public String mKpiRegion;
    public long mMaxBatchEntries;
    public long mMaxBatchOpenTimeMillis;
    public long mMaxBatchSizeBytes;
    public int mMaxKeySizeBytes;
    public int mMaxKeyValuePairCount;
    public int mMaxMetricEventSizeBytes;
    public long mMaxNumberOfBatchFiles;
    public long mMaxStorageSpaceBytes;
    public int mMaxThrottleCredit;
    public int mMaxThrottleCreditHardLimit;
    public int mMaxValueSizeBytes;
    public long mPurgePeriodMillis;
    public int mReadTimeoutMillis;
    public JSONObject mSamplingObject;
    public SharedPreferences mSharedPreferences;
    public JSONObject mStorageObject;
    public JSONObject mThrottleHardLimitsObject;
    public JSONObject mThrottleObject;
    public int mThrottleSwitch;
    public int mThrottleSwitchHardLimit;
    public long mTransmissionPeriodMillis;
    public JSONObject mUploadObject;
    public String mUrlEndpoint;
    public JSONObject mValidationObject;
    public long mWakeLockTimeoutMillis;

    public AssetFileParser(Context context) {
        this.mContext = context;
        this.mSharedPreferences = context.getSharedPreferences(ConfigurationConstant.MINERVA_DEBUG_FILENAME, 0);
        setFallbackConfiguration(parseMetricsConfiguration(DefaultMetricsConfiguration.DEFAULT_CONFIGURATION));
    }

    public JSONObject getDomainMetricsConfiguration(JSONObject jSONObject) throws JSONException {
        if (Build.TYPE.equals("user") || !isDebugModeEnabled()) {
            Log.i(TAG, "Debug mode is turned off, and using Prod configuration.");
            return jSONObject.getJSONObject(MetricsConfigurationConstants.PROD_DOMAIN);
        }
        Log.i(TAG, "Debug mode is turned on, and using Devo configuration.");
        return jSONObject.getJSONObject(MetricsConfigurationConstants.DEVO_DOMAIN);
    }

    public long getFallbackCheckBatchOpenTimeMillis() {
        return this.mCheckBatchOpenTimeMillis;
    }

    public int getFallbackConnectTimeoutMillis() {
        return this.mConnectTimeoutMillis;
    }

    public int getFallbackDefaultSamplingRate() {
        return this.mDefaultSamplingRate;
    }

    public int getFallbackDefaultThrottleCreditHour() {
        return this.mDefaultThrottleCreditHour;
    }

    public int getFallbackDefaultThrottleCreditHourHardLimit() {
        return this.mDefaultThrottleCreditHourHardLimit;
    }

    public int getFallbackDenyListBitSize() {
        return this.mDenyListBitSize;
    }

    public String getFallbackDenyListBits() {
        return this.mDenyListBits;
    }

    public JSONObject getFallbackDenyListConfiguration() {
        return this.mDenyListObject;
    }

    public long getFallbackExpiryTimeMillis() {
        return this.mExpiryTimeMillis;
    }

    public String getFallbackIonFormat() {
        return this.mIonFormat;
    }

    public String getFallbackKpiRegion() {
        return this.mKpiRegion;
    }

    public long getFallbackMaxBatchEntries() {
        return this.mMaxBatchEntries;
    }

    public long getFallbackMaxBatchOpenTimeMillis() {
        return this.mMaxBatchOpenTimeMillis;
    }

    public long getFallbackMaxBatchSizeBytes() {
        return this.mMaxBatchSizeBytes;
    }

    public int getFallbackMaxKeySizeBytes() {
        return this.mMaxKeySizeBytes;
    }

    public int getFallbackMaxKeyValuePairCount() {
        return this.mMaxKeyValuePairCount;
    }

    public int getFallbackMaxMetricEventSizeBytes() {
        return this.mMaxMetricEventSizeBytes;
    }

    public long getFallbackMaxNumberOfBatchFiles() {
        return this.mMaxNumberOfBatchFiles;
    }

    public long getFallbackMaxStorageSpaceBytes() {
        return this.mMaxStorageSpaceBytes;
    }

    public int getFallbackMaxThrottleCredit() {
        return this.mMaxThrottleCredit;
    }

    public int getFallbackMaxThrottleCreditHardLimit() {
        return this.mMaxThrottleCreditHardLimit;
    }

    public int getFallbackMaxValueSizeBytes() {
        return this.mMaxValueSizeBytes;
    }

    public long getFallbackPurgePeriodMillis() {
        return this.mExpiryTimeMillis;
    }

    public int getFallbackReadTimeoutMillis() {
        return this.mReadTimeoutMillis;
    }

    public JSONObject getFallbackSamplingConfiguration() {
        return this.mSamplingObject;
    }

    public JSONObject getFallbackStorageConfiguration() {
        return this.mStorageObject;
    }

    public JSONObject getFallbackThrottleConfiguration() {
        return this.mThrottleObject;
    }

    public JSONObject getFallbackThrottleConfigurationHardLimits() {
        return this.mThrottleHardLimitsObject;
    }

    public int getFallbackThrottleSwitch() {
        return this.mThrottleSwitch;
    }

    public int getFallbackThrottleSwitchHardLimit() {
        return this.mThrottleSwitchHardLimit;
    }

    public long getFallbackTransmissionPeriodMillis() {
        return this.mTransmissionPeriodMillis;
    }

    public JSONObject getFallbackUploadConfiguration() {
        return this.mUploadObject;
    }

    public String getFallbackUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    public JSONObject getFallbackValidationConfiguration() {
        return this.mValidationObject;
    }

    public long getFallbackWakeLockTimeoutMillis() {
        return this.mWakeLockTimeoutMillis;
    }

    public final boolean isDebugModeEnabled() {
        boolean z = this.mSharedPreferences.getBoolean(ConfigurationConstant.MINERVA_CLIENT_DEBUG_MODE, false);
        if (z) {
            Log.i(TAG, "Minerva debug mode is turned on.");
            return z;
        }
        Log.i(TAG, "Minerva debug mode is turned off.");
        return z;
    }

    public JSONObject parseMetricsConfiguration(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                JSONObject domainMetricsConfiguration = getDomainMetricsConfiguration(new JSONObject(new String(bArr)));
                inputStreamOpen.close();
                return domainMetricsConfiguration;
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            Log.i(TAG, "No custom configuration file found");
            return null;
        } catch (IOException e) {
            Log.e(TAG, "An error occurs when reading metrics configuration file: " + str, e);
            return null;
        } catch (JSONException e2) {
            Log.e(TAG, "An error occurs when parsing metrics configuration to Json object.", e2);
            return null;
        }
    }

    public final void setFallbackConfiguration(JSONObject jSONObject) {
        try {
            this.mValidationObject = jSONObject.getJSONObject(MetricsConfigurationConstants.VALIDATION_CONFIGURATION);
            this.mThrottleObject = jSONObject.getJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION);
            this.mThrottleHardLimitsObject = jSONObject.getJSONObject(MetricsConfigurationConstants.THROTTLE_CONFIGURATION_HARD_LIMITS);
            this.mSamplingObject = jSONObject.getJSONObject(MetricsConfigurationConstants.SAMPLING_CONFIGURATION);
            this.mDenyListObject = jSONObject.getJSONObject(MetricsConfigurationConstants.DENYLIST_CONFIGURATION);
            this.mStorageObject = jSONObject.getJSONObject(MetricsConfigurationConstants.STORAGE_CONFIGURATION);
            this.mUploadObject = jSONObject.getJSONObject(MetricsConfigurationConstants.UPLOAD_CONFIGURATION);
            this.mMaxKeyValuePairCount = this.mValidationObject.getInt(MetricsConfigurationConstants.MAX_KEY_VALUE_PAIR_COUNT);
            this.mMaxMetricEventSizeBytes = this.mValidationObject.getInt(MetricsConfigurationConstants.MAX_METRIC_EVENT_SIZE_BYTES);
            this.mMaxKeySizeBytes = this.mValidationObject.getInt(MetricsConfigurationConstants.MAX_KEY_SIZE_BYTES);
            this.mMaxValueSizeBytes = this.mValidationObject.getInt(MetricsConfigurationConstants.MAX_VALUE_SIZE_BYTES);
            this.mThrottleSwitch = this.mThrottleObject.getInt(MetricsConfigurationConstants.THROTTLE_SWITCH);
            this.mMaxThrottleCredit = this.mThrottleObject.getInt(MetricsConfigurationConstants.MAX_THROTTLE_CREDIT);
            this.mDefaultThrottleCreditHour = this.mThrottleObject.getInt(MetricsConfigurationConstants.DEFAULT_THROTTLE_CREDIT_HOUR);
            this.mThrottleSwitchHardLimit = this.mThrottleHardLimitsObject.getInt(MetricsConfigurationConstants.THROTTLE_SWITCH_HARD_LIMIT);
            this.mMaxThrottleCreditHardLimit = this.mThrottleHardLimitsObject.getInt(MetricsConfigurationConstants.MAX_THROTTLE_CREDIT_HARD_LIMIT);
            this.mDefaultThrottleCreditHourHardLimit = this.mThrottleHardLimitsObject.getInt(MetricsConfigurationConstants.DEFAULT_THROTTLE_CREDIT_HOUR_HARD_LIMIT);
            this.mDefaultSamplingRate = this.mSamplingObject.getInt(MetricsConfigurationConstants.DEFAULT_SAMPLING_RATE);
            this.mDenyListBits = this.mDenyListObject.getString(MetricsConfigurationConstants.DENYLIST_BITS);
            this.mDenyListBitSize = this.mDenyListObject.getInt(MetricsConfigurationConstants.BITS_SIZE);
            this.mMaxBatchOpenTimeMillis = this.mStorageObject.getLong(MetricsConfigurationConstants.MAX_BATCH_OPEN_TIME_MILLIS);
            this.mCheckBatchOpenTimeMillis = this.mStorageObject.getLong(MetricsConfigurationConstants.CHECK_BATCH_OPEN_TIME_MILLIS);
            this.mMaxBatchEntries = this.mStorageObject.getLong(MetricsConfigurationConstants.MAX_BATCH_ENTRIES);
            this.mMaxBatchSizeBytes = this.mStorageObject.getLong(MetricsConfigurationConstants.MAX_BATCH_SIZE_BYTES);
            this.mMaxStorageSpaceBytes = this.mStorageObject.getLong(MetricsConfigurationConstants.MAX_STORAGE_SPACE_BYTES);
            this.mMaxNumberOfBatchFiles = this.mStorageObject.getLong(MetricsConfigurationConstants.MAX_NUMBER_OF_BATCH_FILES);
            this.mExpiryTimeMillis = this.mStorageObject.getLong(MetricsConfigurationConstants.EXPIRY_TIME_MILLIS);
            this.mPurgePeriodMillis = this.mStorageObject.getLong(MetricsConfigurationConstants.PURGE_PERIOD_MILLIS);
            this.mTransmissionPeriodMillis = this.mStorageObject.getLong(MetricsConfigurationConstants.TRANSMISSION_PERIOD_MILLIS);
            this.mUrlEndpoint = this.mUploadObject.getString(MetricsConfigurationConstants.URL_ENDPOINT);
            this.mIonFormat = this.mUploadObject.getString(MetricsConfigurationConstants.ION_FORMAT);
            this.mConnectTimeoutMillis = this.mUploadObject.getInt(MetricsConfigurationConstants.CONNECT_TIMEOUT_MILLIS);
            this.mReadTimeoutMillis = this.mUploadObject.getInt(MetricsConfigurationConstants.READ_TIMEOUT_MILLIS);
            this.mWakeLockTimeoutMillis = this.mUploadObject.getLong(MetricsConfigurationConstants.WAKE_LOCK_TIMEOUT_MILLIS);
            this.mKpiRegion = this.mUploadObject.getString(MetricsConfigurationConstants.KPI_REGION);
        } catch (Exception e) {
            Log.e(TAG, "Unable to set fallback default config value from Asset file... " + e);
        }
    }

    public JSONObject parseMetricsConfiguration(String str) {
        try {
            return getDomainMetricsConfiguration(new JSONObject(str));
        } catch (JSONException e) {
            Log.e(TAG, "An error occurs when parsing metrics configuration to Json object.", e);
            return null;
        }
    }
}
