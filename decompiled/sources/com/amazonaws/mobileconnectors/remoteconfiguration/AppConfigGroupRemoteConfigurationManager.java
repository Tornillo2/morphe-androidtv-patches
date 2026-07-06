package com.amazonaws.mobileconnectors.remoteconfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ArcusThrottler;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.AttributesImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.GroupConfigurationDb;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.LocalConfigInstanceIdGenerationHelper;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AndroidAppConfigGroupRemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AppConfigGroupRemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RequestThrottledException;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AppConfigGroupRemoteConfigurationManager {
    public static final String DEFAULT_ENDPOINT = "https://arcus-uswest.amazon.com";
    public static final String KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    public static final String REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX = "configuration.prefs";
    public static final String TAG = "AppConfigGroupRemoteConfigurationManager";
    public final String mAppConfigGroupId;
    public final Attributes mAttributes;
    public final GroupConfigurationDb mConfigurationDb;
    public int mLastSuccessfulSyncAttributeHash;
    public final AppConfigGroupRemoteConfigurationFetcher mRemoteConfigurationFetcher;
    public final SharedPreferences mSharedPreferences;
    public final ArcusThrottler mThrottler;

    /* JADX WARN: Illegal instructions before constructor call */
    public AppConfigGroupRemoteConfigurationManager(Builder builder) {
        Context context = builder.mContext;
        String str = builder.mAppConfigGroupId;
        this(context, str, builder.mDefaultConfiguration, GroupConfigurationDb.getOrCreateInstance(context, str), "https://arcus-uswest.amazon.com", false);
    }

    public static Builder forAppConfigGroupId(Context context, String str) {
        return new Builder(context, str);
    }

    public static AppConfigGroupRemoteConfigurationManager getInstance(String str) {
        validateAppConfigGroupId(str);
        return Builder.MANAGERS.get(str);
    }

    public static void validateAppConfigGroupId(String str) {
        Checks.checkArgument(str != null, "The App Configuration Group ID may not be null");
        if (!Pattern.compile("[a-z0-9]{12}").matcher(str).matches()) {
            throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Invalid app config group id: ", str));
        }
    }

    public final Map<String, String> convertToAppConfigVsConfigValueMap(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject.getString(next));
        }
        return map;
    }

    public final Map<String, RemoteConfiguration> getAndValidateConfiguration() {
        GroupConfigurationDb groupConfigurationDb = this.mConfigurationDb;
        if (groupConfigurationDb == null) {
            Log.w(TAG, "Database is uninitialized");
            return new HashMap();
        }
        try {
            Map<String, RemoteConfiguration> remoteConfiguration = groupConfigurationDb.readRemoteConfiguration();
            HashMap map = new HashMap();
            for (Map.Entry entry : ((HashMap) remoteConfiguration).entrySet()) {
                String str = (String) entry.getKey();
                RemoteConfiguration remoteConfiguration2 = (RemoteConfiguration) entry.getValue();
                try {
                    new JSONObject(remoteConfiguration2.getConfiguration().getAsJsonString());
                    map.put(str, remoteConfiguration2);
                } catch (JSONException unused) {
                    Log.w(TAG, "Removing corrupted configuration from local database.");
                    this.mConfigurationDb.deleteConfiguration(str);
                }
            }
            return map;
        } catch (ConfigurationNotFoundException unused2) {
            Log.d(TAG, "Configuration was not found in local database.");
            return new HashMap();
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

    public final String getPreferencesFileNameForAppConfigGroup() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.mAppConfigGroupId, "_configuration.prefs");
    }

    public Attributes openAttributes() {
        return this.mAttributes;
    }

    public Map<String, Configuration> openConfiguration() {
        return this.mConfigurationDb.readConfiguration();
    }

    public void overwriteConfiguration(JSONObject jSONObject) throws IllegalArgumentException {
        Checks.checkNotNull(jSONObject, "The Configuration cannot be null");
        try {
            Map<String, String> mapConvertToAppConfigVsConfigValueMap = convertToAppConfigVsConfigValueMap(jSONObject);
            HashMap map = new HashMap();
            for (Map.Entry entry : ((HashMap) mapConvertToAppConfigVsConfigValueMap).entrySet()) {
                String str = (String) entry.getKey();
                map.put(str, new RemoteConfigurationImpl(new ConfigurationImpl((String) entry.getValue(), new Date()), str, 3, null, false));
            }
            this.mConfigurationDb.saveConfiguration(map);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid config", e);
        }
    }

    public final void setLocalConfigurationInstanceId(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString("localConfigurationInstanceId", str);
        editorEdit.apply();
    }

    public void setRampingKey(String str) {
        if (str == null || str.isEmpty() || str.length() > 64) {
            throw new IllegalArgumentException("The ramping key cannot be null/empty and its length is limited to 64 characters.");
        }
        setLocalConfigurationInstanceId(LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(str));
    }

    public void sync(AppConfigGroupSyncCallback appConfigGroupSyncCallback) {
        Checks.checkArgument(appConfigGroupSyncCallback != null, "ConfigurationSyncCallback cannot be null");
        validateAndSyncOnNewThread(appConfigGroupSyncCallback);
    }

    public final synchronized void syncOnCurrentThread(Map<String, RemoteConfiguration> map, AppConfigGroupSyncCallback appConfigGroupSyncCallback) {
        try {
            Attributes attributesM345clone = this.mAttributes.m345clone();
            if (this.mThrottler.isSyncAllowedRightNow() || (this.mThrottler.mCause == 10 && this.mLastSuccessfulSyncAttributeHash != attributesM345clone.hashCode())) {
                HashMap map2 = new HashMap();
                for (Map.Entry<String, RemoteConfiguration> entry : map.entrySet()) {
                    map2.put(entry.getKey(), entry.getValue().getEntityTag());
                }
                try {
                    try {
                        Map<String, RemoteConfiguration> mapFetch = this.mRemoteConfigurationFetcher.fetch(this.mAppConfigGroupId, attributesM345clone, map2, getOrGenerateLocalConfigurationInstanceId());
                        this.mLastSuccessfulSyncAttributeHash = attributesM345clone.hashCode();
                        this.mThrottler.updateSyncTimeAfterSuccess();
                        HashMap map3 = new HashMap();
                        HashMap map4 = new HashMap(map);
                        boolean z = false;
                        for (Map.Entry<String, RemoteConfiguration> entry2 : mapFetch.entrySet()) {
                            String key = entry2.getKey();
                            RemoteConfiguration value = entry2.getValue();
                            if (value.isUpdate()) {
                                z = true;
                            } else {
                                RemoteConfigurationImpl remoteConfigurationImpl = new RemoteConfigurationImpl(new ConfigurationImpl(map.get(key).getConfiguration().getAsJsonString(), new Date()), key, 2, value.getEntityTag(), false);
                                entry2.setValue(remoteConfigurationImpl);
                                value = remoteConfigurationImpl;
                            }
                            map4.remove(key);
                            map3.put(key, value.getConfiguration());
                        }
                        Iterator it = map4.keySet().iterator();
                        while (it.hasNext()) {
                            this.mConfigurationDb.deleteConfiguration((String) it.next());
                        }
                        this.mConfigurationDb.saveConfiguration(mapFetch);
                        if (z) {
                            appConfigGroupSyncCallback.onConfigurationModified(map3);
                        } else {
                            appConfigGroupSyncCallback.onConfigurationUnmodified(map3);
                        }
                        return;
                    } catch (Exception e) {
                        this.mThrottler.updateSyncTimeAfterFailure();
                        appConfigGroupSyncCallback.onFailure(e);
                        return;
                    }
                } catch (RequestThrottledException unused) {
                    this.mThrottler.updateSyncTimeAfterThrottle(0L);
                    appConfigGroupSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
                    return;
                }
            }
            appConfigGroupSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void validateAndSyncOnNewThread(final AppConfigGroupSyncCallback appConfigGroupSyncCallback) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.amazonaws.mobileconnectors.remoteconfiguration.AppConfigGroupRemoteConfigurationManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppConfigGroupRemoteConfigurationManager appConfigGroupRemoteConfigurationManager = this.f$0;
                appConfigGroupRemoteConfigurationManager.syncOnCurrentThread(appConfigGroupRemoteConfigurationManager.getAndValidateConfiguration(), appConfigGroupSyncCallback);
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    public AppConfigGroupRemoteConfigurationManager(Context context, String str, JSONObject jSONObject, GroupConfigurationDb groupConfigurationDb, String str2, boolean z) {
        this.mThrottler = new ArcusThrottler();
        Checks.checkArgument(context != null, "appContext cannot be null");
        validateAppConfigGroupId(str);
        context = z ? context : context.getApplicationContext();
        try {
            URL url = new URL(str2);
            this.mAppConfigGroupId = str;
            this.mSharedPreferences = context.getSharedPreferences(getPreferencesFileNameForAppConfigGroup(), 0);
            AttributesImpl attributesImpl = new AttributesImpl(context);
            this.mAttributes = attributesImpl;
            this.mLastSuccessfulSyncAttributeHash = attributesImpl.hashCode();
            this.mConfigurationDb = groupConfigurationDb;
            this.mRemoteConfigurationFetcher = new AndroidAppConfigGroupRemoteConfigurationFetcher(context, url);
            if (jSONObject != null) {
                try {
                    Map<String, String> mapConvertToAppConfigVsConfigValueMap = convertToAppConfigVsConfigValueMap(jSONObject);
                    Map<String, RemoteConfiguration> remoteConfiguration = groupConfigurationDb.readRemoteConfiguration();
                    HashMap map = new HashMap();
                    HashMap map2 = (HashMap) mapConvertToAppConfigVsConfigValueMap;
                    for (Map.Entry entry : map2.entrySet()) {
                        String str3 = (String) entry.getKey();
                        String str4 = (String) entry.getValue();
                        if (!((HashMap) remoteConfiguration).containsKey(str3)) {
                            map.put(str3, new RemoteConfigurationImpl(new ConfigurationImpl(str4, new Date()), str3, 1, null, false));
                        }
                    }
                    for (Map.Entry entry2 : ((HashMap) remoteConfiguration).entrySet()) {
                        String str5 = (String) entry2.getKey();
                        RemoteConfiguration remoteConfiguration2 = (RemoteConfiguration) entry2.getValue();
                        if (remoteConfiguration2 != null && remoteConfiguration2.getOrigin() != 1) {
                            Log.d(TAG, String.format("Skipping default configuration saving for appconfigID: %s", str5));
                        } else {
                            map.put(str5, new RemoteConfigurationImpl(new ConfigurationImpl((String) map2.get(str5), new Date()), str5, 1, null, false));
                        }
                    }
                    try {
                        Log.d(TAG, "Saving default configuration");
                        if (map.isEmpty()) {
                            return;
                        }
                        groupConfigurationDb.saveConfiguration(map);
                    } catch (SQLiteCantOpenDatabaseException e) {
                        Log.e(TAG, "Saving default configuration failed due to error opening database");
                        throw new IllegalStateException("Database operation failed due to error opening database", e);
                    } catch (SQLiteDiskIOException e2) {
                        Log.e(TAG, "Saving default configuration failed due to disk error");
                        throw new IllegalStateException("Database operation failed due to disk IO error while accessing database", e2);
                    } catch (SQLiteFullException e3) {
                        Log.e(TAG, "Saving default configuration failed due to disk full");
                        throw new IllegalStateException("Database operation failed due to full storage", e3);
                    }
                } catch (JSONException e4) {
                    throw new IllegalArgumentException("Invalid default config", e4);
                }
            }
        } catch (MalformedURLException e5) {
            throw new IllegalArgumentException("Invalid endpoint", e5);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public static final ConcurrentHashMap<String, AppConfigGroupRemoteConfigurationManager> MANAGERS = new ConcurrentHashMap<>();
        public final String mAppConfigGroupId;
        public final Context mContext;
        public boolean mCustomContext;
        public JSONObject mDefaultConfiguration;

        public Builder(Context context, String str) {
            this.mDefaultConfiguration = new JSONObject();
            this.mCustomContext = false;
            if (context == null) {
                throw new IllegalArgumentException("The Context may not be null");
            }
            if (str == null) {
                throw new IllegalArgumentException("The App Configuration Group ID may not be null");
            }
            this.mContext = context;
            this.mAppConfigGroupId = str;
        }

        public AppConfigGroupRemoteConfigurationManager createOrGet() {
            AppConfigGroupRemoteConfigurationManager.validateAppConfigGroupId(this.mAppConfigGroupId);
            ConcurrentHashMap<String, AppConfigGroupRemoteConfigurationManager> concurrentHashMap = MANAGERS;
            if (!concurrentHashMap.containsKey(this.mAppConfigGroupId)) {
                ensureBuilderReady();
                concurrentHashMap.putIfAbsent(this.mAppConfigGroupId, new AppConfigGroupRemoteConfigurationManager(this));
            }
            return concurrentHashMap.get(this.mAppConfigGroupId);
        }

        public void ensureBuilderReady() {
            if (this.mContext == null) {
                throw new IllegalStateException("The Context may not be null");
            }
            if (this.mAppConfigGroupId == null) {
                throw new IllegalStateException("The App Configuration Group ID may not be null");
            }
            if (this.mDefaultConfiguration == null) {
                throw new IllegalStateException("The default configuration may not be null");
            }
        }

        public Builder withDefaultConfiguration(JSONObject jSONObject) {
            Checks.checkArgument(jSONObject != null, "Default configuration cannot be null");
            this.mDefaultConfiguration = jSONObject;
            return this;
        }

        public Builder(Context context, String str, boolean z) {
            this(context, str);
            this.mCustomContext = z;
        }
    }
}
