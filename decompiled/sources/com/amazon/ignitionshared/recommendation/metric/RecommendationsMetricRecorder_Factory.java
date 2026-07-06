package com.amazon.ignitionshared.recommendation.metric;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
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
public final class RecommendationsMetricRecorder_Factory implements Factory<RecommendationsMetricRecorder> {
    public final Provider<MinervaMetrics> minervaMetricsProvider;

    public RecommendationsMetricRecorder_Factory(Provider<MinervaMetrics> provider) {
        this.minervaMetricsProvider = provider;
    }

    public static RecommendationsMetricRecorder_Factory create(Provider<MinervaMetrics> provider) {
        return new RecommendationsMetricRecorder_Factory(provider);
    }

    public static RecommendationsMetricRecorder newInstance(MinervaMetrics minervaMetrics) {
        return new RecommendationsMetricRecorder(minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationsMetricRecorder get() {
        return new RecommendationsMetricRecorder(this.minervaMetricsProvider.get());
    }
}
