package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class RemoteOverridesProvider_Factory implements Factory<RemoteOverridesProvider> {
    public final Provider<AppStartupConfigProvider> appStartupConfigProvider;
    public final Provider<Context> contextProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;

    public RemoteOverridesProvider_Factory(Provider<Context> provider, Provider<AppStartupConfigProvider> provider2, Provider<MetricsRecorder> provider3) {
        this.contextProvider = provider;
        this.appStartupConfigProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static RemoteOverridesProvider_Factory create(Provider<Context> provider, Provider<AppStartupConfigProvider> provider2, Provider<MetricsRecorder> provider3) {
        return new RemoteOverridesProvider_Factory(provider, provider2, provider3);
    }

    public static RemoteOverridesProvider newInstance(Context context, AppStartupConfigProvider appStartupConfigProvider, MetricsRecorder metricsRecorder) {
        return new RemoteOverridesProvider(context, appStartupConfigProvider, metricsRecorder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RemoteOverridesProvider get() {
        return new RemoteOverridesProvider(this.contextProvider.get(), this.appStartupConfigProvider.get(), this.metricsRecorderProvider.get());
    }
}
