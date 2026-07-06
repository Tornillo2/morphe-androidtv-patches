package com.amazon.ignitionshared.metrics;

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
public final class JemallocAllocatorMetricRecorder_Factory implements Factory<JemallocAllocatorMetricRecorder> {
    public final Provider<MinervaMetrics> minervaMetricsProvider;

    public JemallocAllocatorMetricRecorder_Factory(Provider<MinervaMetrics> provider) {
        this.minervaMetricsProvider = provider;
    }

    public static JemallocAllocatorMetricRecorder_Factory create(Provider<MinervaMetrics> provider) {
        return new JemallocAllocatorMetricRecorder_Factory(provider);
    }

    public static JemallocAllocatorMetricRecorder newInstance(MinervaMetrics minervaMetrics) {
        return new JemallocAllocatorMetricRecorder(minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public JemallocAllocatorMetricRecorder get() {
        return new JemallocAllocatorMetricRecorder(this.minervaMetricsProvider.get());
    }
}
