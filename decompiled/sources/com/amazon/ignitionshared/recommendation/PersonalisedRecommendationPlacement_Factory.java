package com.amazon.ignitionshared.recommendation;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
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
public final class PersonalisedRecommendationPlacement_Factory implements Factory<PersonalisedRecommendationPlacement> {
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<Map<String, String>> placementIdMapProvider;
    public final Provider<RecommendationPublisher> recommendationPublisherProvider;

    public PersonalisedRecommendationPlacement_Factory(Provider<RecommendationPublisher> provider, Provider<MinervaMetrics> provider2, Provider<Map<String, String>> provider3) {
        this.recommendationPublisherProvider = provider;
        this.minervaMetricsProvider = provider2;
        this.placementIdMapProvider = provider3;
    }

    public static PersonalisedRecommendationPlacement_Factory create(Provider<RecommendationPublisher> provider, Provider<MinervaMetrics> provider2, Provider<Map<String, String>> provider3) {
        return new PersonalisedRecommendationPlacement_Factory(provider, provider2, provider3);
    }

    public static PersonalisedRecommendationPlacement newInstance(RecommendationPublisher recommendationPublisher, MinervaMetrics minervaMetrics, Map<String, String> map) {
        return new PersonalisedRecommendationPlacement(recommendationPublisher, minervaMetrics, map);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PersonalisedRecommendationPlacement get() {
        return new PersonalisedRecommendationPlacement(this.recommendationPublisherProvider.get(), this.minervaMetricsProvider.get(), this.placementIdMapProvider.get());
    }
}
