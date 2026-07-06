package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class RecommendationUpdater_Factory implements Factory<RecommendationUpdater> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<PearRecommendationFlowController> pearRecommendationFlowControllerProvider;
    public final Provider<PearResponseParser> pearResponseParserProvider;
    public final Provider<RecommendationsScheduler> pearSchedulerProvider;
    public final Provider<PearUpdateStructure> pearUpdateStructureProvider;
    public final Provider<Set<PearPlacement>> placementsSetProvider;
    public final Provider<RecommendationPublisher> recommendationPublisherProvider;

    public RecommendationUpdater_Factory(Provider<PearResponseParser> provider, Provider<PearUpdateStructure> provider2, Provider<MinervaMetrics> provider3, Provider<PearRecommendationFlowController> provider4, Provider<DeviceProperties> provider5, Provider<RecommendationPublisher> provider6, Provider<RecommendationsScheduler> provider7, Provider<Set<PearPlacement>> provider8) {
        this.pearResponseParserProvider = provider;
        this.pearUpdateStructureProvider = provider2;
        this.minervaMetricsProvider = provider3;
        this.pearRecommendationFlowControllerProvider = provider4;
        this.devicePropertiesProvider = provider5;
        this.recommendationPublisherProvider = provider6;
        this.pearSchedulerProvider = provider7;
        this.placementsSetProvider = provider8;
    }

    public static RecommendationUpdater_Factory create(Provider<PearResponseParser> provider, Provider<PearUpdateStructure> provider2, Provider<MinervaMetrics> provider3, Provider<PearRecommendationFlowController> provider4, Provider<DeviceProperties> provider5, Provider<RecommendationPublisher> provider6, Provider<RecommendationsScheduler> provider7, Provider<Set<PearPlacement>> provider8) {
        return new RecommendationUpdater_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static RecommendationUpdater newInstance(PearResponseParser pearResponseParser, PearUpdateStructure pearUpdateStructure, MinervaMetrics minervaMetrics, PearRecommendationFlowController pearRecommendationFlowController, DeviceProperties deviceProperties, RecommendationPublisher recommendationPublisher, RecommendationsScheduler recommendationsScheduler, Set<PearPlacement> set) {
        return new RecommendationUpdater(pearResponseParser, pearUpdateStructure, minervaMetrics, pearRecommendationFlowController, deviceProperties, recommendationPublisher, recommendationsScheduler, set);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationUpdater get() {
        return new RecommendationUpdater(this.pearResponseParserProvider.get(), this.pearUpdateStructureProvider.get(), this.minervaMetricsProvider.get(), this.pearRecommendationFlowControllerProvider.get(), this.devicePropertiesProvider.get(), this.recommendationPublisherProvider.get(), this.pearSchedulerProvider.get(), this.placementsSetProvider.get());
    }
}
