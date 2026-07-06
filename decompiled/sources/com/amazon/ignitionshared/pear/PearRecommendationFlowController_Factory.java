package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class PearRecommendationFlowController_Factory implements Factory<PearRecommendationFlowController> {
    public final Provider<PearAccessTokenProvider> pearAccessTokenProvider;
    public final Provider<PearUpdateStructure> pearUpdateStructureProvider;
    public final Provider<PearUriBuilder> pearUriBuilderProvider;
    public final Provider<RecommendationRequestDispatcher> recommendationRequestDispatcherProvider;

    public PearRecommendationFlowController_Factory(Provider<RecommendationRequestDispatcher> provider, Provider<PearUpdateStructure> provider2, Provider<PearAccessTokenProvider> provider3, Provider<PearUriBuilder> provider4) {
        this.recommendationRequestDispatcherProvider = provider;
        this.pearUpdateStructureProvider = provider2;
        this.pearAccessTokenProvider = provider3;
        this.pearUriBuilderProvider = provider4;
    }

    public static PearRecommendationFlowController_Factory create(Provider<RecommendationRequestDispatcher> provider, Provider<PearUpdateStructure> provider2, Provider<PearAccessTokenProvider> provider3, Provider<PearUriBuilder> provider4) {
        return new PearRecommendationFlowController_Factory(provider, provider2, provider3, provider4);
    }

    public static PearRecommendationFlowController newInstance(RecommendationRequestDispatcher recommendationRequestDispatcher, PearUpdateStructure pearUpdateStructure, PearAccessTokenProvider pearAccessTokenProvider, PearUriBuilder pearUriBuilder) {
        return new PearRecommendationFlowController(recommendationRequestDispatcher, pearUpdateStructure, pearAccessTokenProvider, pearUriBuilder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearRecommendationFlowController get() {
        return new PearRecommendationFlowController(this.recommendationRequestDispatcherProvider.get(), this.pearUpdateStructureProvider.get(), this.pearAccessTokenProvider.get(), this.pearUriBuilderProvider.get());
    }
}
