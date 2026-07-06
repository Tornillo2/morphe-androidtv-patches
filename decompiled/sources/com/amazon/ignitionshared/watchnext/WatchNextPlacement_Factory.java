package com.amazon.ignitionshared.watchnext;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class WatchNextPlacement_Factory implements Factory<WatchNextPlacement> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<PearWatchNextProgramBuilder> pearWatchNextProgramBuilderProvider;
    public final Provider<Map<String, String>> placementIdMapProvider;
    public final Provider<WatchNextPublisher> watchNextPublisherProvider;

    public WatchNextPlacement_Factory(Provider<WatchNextPublisher> provider, Provider<MinervaMetrics> provider2, Provider<Map<String, String>> provider3, Provider<PearWatchNextProgramBuilder> provider4, Provider<DeviceProperties> provider5) {
        this.watchNextPublisherProvider = provider;
        this.minervaMetricsProvider = provider2;
        this.placementIdMapProvider = provider3;
        this.pearWatchNextProgramBuilderProvider = provider4;
        this.devicePropertiesProvider = provider5;
    }

    public static WatchNextPlacement_Factory create(Provider<WatchNextPublisher> provider, Provider<MinervaMetrics> provider2, Provider<Map<String, String>> provider3, Provider<PearWatchNextProgramBuilder> provider4, Provider<DeviceProperties> provider5) {
        return new WatchNextPlacement_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static WatchNextPlacement newInstance(WatchNextPublisher watchNextPublisher, MinervaMetrics minervaMetrics, Map<String, String> map, PearWatchNextProgramBuilder pearWatchNextProgramBuilder, DeviceProperties deviceProperties) {
        return new WatchNextPlacement(watchNextPublisher, minervaMetrics, map, pearWatchNextProgramBuilder, deviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WatchNextPlacement get() {
        return new WatchNextPlacement(this.watchNextPublisherProvider.get(), this.minervaMetricsProvider.get(), this.placementIdMapProvider.get(), this.pearWatchNextProgramBuilderProvider.get(), this.devicePropertiesProvider.get());
    }
}
