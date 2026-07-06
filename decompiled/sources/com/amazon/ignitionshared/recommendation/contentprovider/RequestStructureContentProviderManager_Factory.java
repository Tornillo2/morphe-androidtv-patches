package com.amazon.ignitionshared.recommendation.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.pear.PearUpdateStructure;
import com.amazon.ignitionshared.recommendation.BootstrapRequestStructureBuilder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext", "javax.inject.Named"})
public final class RequestStructureContentProviderManager_Factory implements Factory<RequestStructureContentProviderManager> {
    public final Provider<BootstrapRequestStructureBuilder> bootstrapRequestStructureBuilderProvider;
    public final Provider<Context> contextProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<PearUpdateStructure> pearUpdateStructureProvider;
    public final Provider<SharedPreferences> requestStructurePreferenceProvider;

    public RequestStructureContentProviderManager_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<DeviceProperties> provider3, Provider<PearUpdateStructure> provider4, Provider<BootstrapRequestStructureBuilder> provider5, Provider<MinervaMetrics> provider6) {
        this.contextProvider = provider;
        this.requestStructurePreferenceProvider = provider2;
        this.devicePropertiesProvider = provider3;
        this.pearUpdateStructureProvider = provider4;
        this.bootstrapRequestStructureBuilderProvider = provider5;
        this.minervaMetricsProvider = provider6;
    }

    public static RequestStructureContentProviderManager_Factory create(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<DeviceProperties> provider3, Provider<PearUpdateStructure> provider4, Provider<BootstrapRequestStructureBuilder> provider5, Provider<MinervaMetrics> provider6) {
        return new RequestStructureContentProviderManager_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static RequestStructureContentProviderManager newInstance(Context context, SharedPreferences sharedPreferences, DeviceProperties deviceProperties, PearUpdateStructure pearUpdateStructure, BootstrapRequestStructureBuilder bootstrapRequestStructureBuilder, MinervaMetrics minervaMetrics) {
        return new RequestStructureContentProviderManager(context, sharedPreferences, deviceProperties, pearUpdateStructure, bootstrapRequestStructureBuilder, minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RequestStructureContentProviderManager get() {
        return new RequestStructureContentProviderManager(this.contextProvider.get(), this.requestStructurePreferenceProvider.get(), this.devicePropertiesProvider.get(), this.pearUpdateStructureProvider.get(), this.bootstrapRequestStructureBuilderProvider.get(), this.minervaMetricsProvider.get());
    }
}
