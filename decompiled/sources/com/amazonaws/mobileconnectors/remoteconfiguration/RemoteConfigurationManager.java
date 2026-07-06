package com.amazonaws.mobileconnectors.remoteconfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.MalformedAppConfigIdException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ArcusThrottler;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.Arn;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.AttributesImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationDb;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.LocalConfigInstanceIdGenerationHelper;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AndroidRemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RequestThrottledException;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class RemoteConfigurationManager {
    public static final String DEFAULT_ENDPOINT = "https://arcus-uswest.amazon.com";
    public static final String KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    public static final String REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX = "configuration.prefs";
    public static final String TAG = "RemoteConfigurationManager";
    public final String mAppConfigId;
    public final Attributes mAttributes;
    public final ConfigurationDb mConfigurationDb;
    public int mLastSuccessfulSyncAttributeHash;
    public final RemoteConfigurationFetcher mRemoteConfigurationFetcher;
    public final SharedPreferences mSharedPreferences;
    public final ArcusThrottler mThrottler;

    public static Builder forAppId(Context context, String str) {
        return new Builder(context, str);
    }

    public static RemoteConfigurationManager getInstance(String str) {
        Checks.checkNotNull(str, "The App Configuration ID may not be null");
        return Builder.MANAGERS.get(str);
    }

    public static void verifyAppConfigId(String str) {
        try {
            Arn.fromArn(str);
        } catch (IllegalArgumentException e) {
            throw new MalformedAppConfigIdException("Invalid appConfigId ARN.", e);
        }
    }

    public final RemoteConfiguration getAndValidateConfiguration() {
        ConfigurationDb configurationDb = this.mConfigurationDb;
        if (configurationDb == null) {
            return null;
        }
        try {
            RemoteConfiguration remoteConfiguration = configurationDb.readRemoteConfiguration(this.mAppConfigId);
            if (remoteConfiguration == null) {
                return null;
            }
            new JSONObject(((RemoteConfigurationImpl) remoteConfiguration).mConfiguration.getAsJsonString());
            return remoteConfiguration;
        } catch (ConfigurationNotFoundException unused) {
            Log.d(TAG, "Configuration was not found in local database.");
            return null;
        } catch (JSONException unused2) {
            Log.w(TAG, "Removing corrupted configuration from local database.");
            this.mConfigurationDb.deleteConfiguration();
            return null;
        }
    }

    public final String getOrGenerateLocalConfigurationInstanceId() {
        String string = this.mSharedPreferences.getString("localConfigurationInstanceId", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strGenerateLocalConfigInstanceId = LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(UUID.randomUUID().toString());
        setLocalConfigurationInstanceId(strGenerateLocalConfigInstanceId);
        return strGenerateLocalConfigInstanceId;
    }

    public final String getPreferencesFileNameForAppConfig() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.mAppConfigId, "_configuration.prefs");
    }

    public Attributes openAttributes() {
        return this.mAttributes;
    }

    public Configuration openConfiguration() {
        return this.mConfigurationDb.readConfiguration();
    }

    public void overwriteConfiguration(JSONObject jSONObject) throws Throwable {
        Checks.checkNotNull(jSONObject, "The Configuration cannot be null");
        this.mConfigurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), this.mAppConfigId, 3, null, false));
    }

    public final void setLocalConfigurationInstanceId(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString("localConfigurationInstanceId", str);
        editorEdit.apply();
    }

    public void setRampingKey(String str) {
        if (str == null) {
            throw new NullPointerException("The ramping key cannot be null.");
        }
        if (str.isEmpty() || str.length() > 64) {
            throw new IllegalArgumentException("The ramping key cannot be empty and its length is limited to 64 characters.");
        }
        setLocalConfigurationInstanceId(LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(str));
    }

    public void sync(ConfigurationSyncCallback configurationSyncCallback) {
        Checks.checkNotNull(configurationSyncCallback, "ConfigurationSyncCallback cannot be null");
        validateAndSyncOnNewThread(configurationSyncCallback);
    }

    public final synchronized void syncOnCurrentThread(RemoteConfiguration remoteConfiguration, ConfigurationSyncCallback configurationSyncCallback) {
        try {
            Attributes attributesM345clone = this.mAttributes.m345clone();
            if (this.mThrottler.isSyncAllowedRightNow() || (this.mThrottler.mCause == 10 && this.mLastSuccessfulSyncAttributeHash != attributesM345clone.hashCode())) {
                try {
                    RemoteConfiguration remoteConfigurationFetch = this.mRemoteConfigurationFetcher.fetch(this.mAppConfigId, attributesM345clone, remoteConfiguration != null ? remoteConfiguration.getEntityTag() : null, getOrGenerateLocalConfigurationInstanceId());
                    this.mLastSuccessfulSyncAttributeHash = attributesM345clone.hashCode();
                    this.mThrottler.updateSyncTimeAfterSuccess();
                    if (remoteConfigurationFetch.isUpdate()) {
                        this.mConfigurationDb.saveConfiguration(remoteConfigurationFetch);
                        configurationSyncCallback.onConfigurationModified(remoteConfigurationFetch.getConfiguration());
                        return;
                    } else {
                        ConfigurationImpl configurationImpl = new ConfigurationImpl(remoteConfiguration.getConfiguration().getAsJsonString(), new Date());
                        this.mConfigurationDb.saveConfiguration(new RemoteConfigurationImpl(configurationImpl, remoteConfiguration.getAppConfigurationId(), remoteConfiguration.getOrigin(), remoteConfiguration.getEntityTag(), false));
                        configurationSyncCallback.onConfigurationUnmodified(configurationImpl);
                        return;
                    }
                } catch (RequestThrottledException unused) {
                    this.mThrottler.updateSyncTimeAfterThrottle(0L);
                    configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
                    return;
                } catch (Exception e) {
                    this.mThrottler.updateSyncTimeAfterFailure();
                    configurationSyncCallback.onFailure(e);
                    return;
                }
            }
            configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void validateAndSyncOnNewThread(final ConfigurationSyncCallback configurationSyncCallback) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RemoteConfigurationManager remoteConfigurationManager = this.f$0;
                remoteConfigurationManager.syncOnCurrentThread(remoteConfigurationManager.getAndValidateConfiguration(), configurationSyncCallback);
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    public RemoteConfigurationManager(Builder builder) {
        this(builder.mContext, builder.mAppConfigId, builder.mDefaultConfiguration, builder.mCustomContext);
    }

    public static Builder forAppId(Context context, String str, boolean z) {
        return new Builder(context, str, z);
    }

    public RemoteConfigurationManager(Context context, String str, JSONObject jSONObject) {
        this(context, str, jSONObject, ConfigurationDb.getOrCreateInstance(context, str), "https://arcus-uswest.amazon.com", false);
    }

    public RemoteConfigurationManager(Context context, String str, JSONObject jSONObject, boolean z) {
        this(context, str, jSONObject, ConfigurationDb.getOrCreateInstance(context, str), "https://arcus-uswest.amazon.com", z);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public static final ConcurrentHashMap<String, RemoteConfigurationManager> MANAGERS = new ConcurrentHashMap<>();
        public final String mAppConfigId;
        public final Context mContext;
        public boolean mCustomContext;
        public JSONObject mDefaultConfiguration;

        public Builder(Context context, String str) {
            this.mDefaultConfiguration = new JSONObject();
            this.mCustomContext = false;
            if (context == null) {
                throw new NullPointerException("The Context may not be null");
            }
            if (str == null) {
                throw new NullPointerException("The App Configuration ID may not be null");
            }
            RemoteConfigurationManager.verifyAppConfigId(str);
            this.mContext = context;
            this.mAppConfigId = str;
        }

        public RemoteConfigurationManager createOrGet() {
            String str = this.mAppConfigId;
            if (str == null) {
                throw new IllegalStateException("An App Configuration ID must be specified");
            }
            ConcurrentHashMap<String, RemoteConfigurationManager> concurrentHashMap = MANAGERS;
            if (!concurrentHashMap.containsKey(str)) {
                ensureBuilderReady();
                concurrentHashMap.putIfAbsent(this.mAppConfigId, new RemoteConfigurationManager(this));
            }
            return concurrentHashMap.get(this.mAppConfigId);
        }

        public void ensureBuilderReady() {
            if (this.mContext == null) {
                throw new IllegalStateException("The Context may not be null");
            }
            if (this.mAppConfigId == null) {
                throw new IllegalStateException("The App Configuration ID may not be null");
            }
            if (this.mDefaultConfiguration == null) {
                throw new IllegalStateException("The default configuration may not be null");
            }
        }

        public Builder withDefaultConfiguration(JSONObject jSONObject) {
            if (jSONObject == null) {
                throw new NullPointerException("The default configuration may not be null");
            }
            this.mDefaultConfiguration = jSONObject;
            return this;
        }

        public Builder(Context context, String str, boolean z) {
            this(context, str);
            this.mCustomContext = z;
        }
    }

    public RemoteConfigurationManager(Context context, String str, JSONObject jSONObject, ConfigurationDb configurationDb, String str2, boolean z) throws Throwable {
        this.mThrottler = new ArcusThrottler();
        Checks.checkNotNull(context, "appContext cannot be null");
        Checks.checkNotNull(str, "appConfigId cannot be null");
        verifyAppConfigId(str);
        context = z ? context : context.getApplicationContext();
        try {
            URL url = new URL(str2);
            this.mAppConfigId = str;
            this.mSharedPreferences = context.getSharedPreferences(getPreferencesFileNameForAppConfig(), 0);
            AttributesImpl attributesImpl = new AttributesImpl(context);
            this.mAttributes = attributesImpl;
            this.mLastSuccessfulSyncAttributeHash = attributesImpl.hashCode();
            this.mConfigurationDb = configurationDb;
            this.mRemoteConfigurationFetcher = new AndroidRemoteConfigurationFetcher(context, url);
            if (jSONObject != null) {
                RemoteConfiguration remoteConfiguration = configurationDb.readRemoteConfiguration(str);
                if (remoteConfiguration != null && ((RemoteConfigurationImpl) remoteConfiguration).mOrigin != 1) {
                    Log.d(TAG, "Skipping default configuration saving");
                    return;
                }
                Log.d(TAG, "Saving default configuration");
                try {
                    configurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), str, 1, null, false));
                } catch (SQLiteFullException e) {
                    Log.e(TAG, "Saving default configuration failed due to disk full");
                    throw new IllegalStateException("Database operation failed due to full storage", e);
                }
            }
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("Invalid endpoint", e2);
        }
    }
}
