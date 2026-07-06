package com.amazon.minerva.client.thirdparty.configuration;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DefaultRemoteMetricsConfigurationUpdater implements ConfigurationSyncCallback {
    public static final String TAG = "DefaultRemoteMetricsConfigurationUpdater";
    public AssetFileParser mAssetFileParser;
    public MetricsConfigurationHelper mMetricsConfigurationHelper;
    public SharedPreferences mSharedPreferences;

    public DefaultRemoteMetricsConfigurationUpdater(MetricsConfigurationHelper metricsConfigurationHelper, AssetFileParser assetFileParser, SharedPreferences sharedPreferences) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mAssetFileParser = assetFileParser;
        this.mSharedPreferences = sharedPreferences;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationModified(Configuration configuration) {
        this.mMetricsConfigurationHelper.setMetricsConfiguration(configuration);
        Log.i(TAG, "Remote configuration is changed, and update local metric configurations.");
        this.mSharedPreferences.edit().putLong(ConfigurationManager.SHARED_PREF_LAST_DEFAULT_ARCUS_SYNC_MILLIS, System.currentTimeMillis()).apply();
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationUnmodified(Configuration configuration) {
        this.mMetricsConfigurationHelper.setMetricsConfiguration(configuration);
        Log.i(TAG, "Remote configuration is unchanged, but still update local metric configurations to ensure the match.");
        this.mSharedPreferences.edit().putLong(ConfigurationManager.SHARED_PREF_LAST_DEFAULT_ARCUS_SYNC_MILLIS, System.currentTimeMillis()).apply();
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onFailure(Exception exc) {
        Log.e(TAG, "Remote configuration sync failed, and wait for next cycle.", exc);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onThrottle(long j) {
        Log.w(TAG, String.format("Remote configuration sync was throttled, and can retry in %d minutes.", Long.valueOf((j / 1000) / 60)));
    }
}
