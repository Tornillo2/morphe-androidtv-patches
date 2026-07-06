package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.ignitionshared.MapSqliteHelper;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext", "javax.inject.Named"})
public final class DeviceIdProvider_Factory implements Factory<DeviceIdProvider> {
    public final Provider<Context> contextProvider;
    public final Provider<LocalStorage> localStorageProvider;
    public final Provider<MapSqliteHelper> mapSqliteHelperProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<SharedPreferences> sharedPreferencesProvider;

    public DeviceIdProvider_Factory(Provider<Context> provider, Provider<MapSqliteHelper> provider2, Provider<MetricsRecorder> provider3, Provider<LocalStorage> provider4, Provider<SharedPreferences> provider5) {
        this.contextProvider = provider;
        this.mapSqliteHelperProvider = provider2;
        this.metricsRecorderProvider = provider3;
        this.localStorageProvider = provider4;
        this.sharedPreferencesProvider = provider5;
    }

    public static DeviceIdProvider_Factory create(Provider<Context> provider, Provider<MapSqliteHelper> provider2, Provider<MetricsRecorder> provider3, Provider<LocalStorage> provider4, Provider<SharedPreferences> provider5) {
        return new DeviceIdProvider_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static DeviceIdProvider newInstance(Context context, MapSqliteHelper mapSqliteHelper, MetricsRecorder metricsRecorder, LocalStorage localStorage, SharedPreferences sharedPreferences) {
        return new DeviceIdProvider(context, mapSqliteHelper, metricsRecorder, localStorage, sharedPreferences);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceIdProvider get() {
        return new DeviceIdProvider(this.contextProvider.get(), this.mapSqliteHelperProvider.get(), this.metricsRecorderProvider.get(), this.localStorageProvider.get(), this.sharedPreferencesProvider.get());
    }
}
