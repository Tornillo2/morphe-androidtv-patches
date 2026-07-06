package com.amazon.ignitionshared.metrics;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class MinervaMetrics_Factory implements Factory<MinervaMetrics> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<AmazonMinerva> minervaClientProvider;

    public MinervaMetrics_Factory(Provider<AmazonMinerva> provider, Provider<DeviceProperties> provider2, Provider<MetricsRecorder> provider3) {
        this.minervaClientProvider = provider;
        this.devicePropertiesProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static MinervaMetrics_Factory create(Provider<AmazonMinerva> provider, Provider<DeviceProperties> provider2, Provider<MetricsRecorder> provider3) {
        return new MinervaMetrics_Factory(provider, provider2, provider3);
    }

    public static MinervaMetrics newInstance(AmazonMinerva amazonMinerva, DeviceProperties deviceProperties, MetricsRecorder metricsRecorder) {
        return new MinervaMetrics(amazonMinerva, deviceProperties, metricsRecorder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MinervaMetrics get() {
        return new MinervaMetrics(this.minervaClientProvider.get(), this.devicePropertiesProvider.get(), this.metricsRecorderProvider.get());
    }
}
