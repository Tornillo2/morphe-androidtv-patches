package com.amazon.ignitionshared.service;

import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class PeriodicUpdateRecommendationsWorker_MembersInjector implements MembersInjector<PeriodicUpdateRecommendationsWorker> {
    public final Provider<RecommendationHandler> recommendationHandlerProvider;

    public PeriodicUpdateRecommendationsWorker_MembersInjector(Provider<RecommendationHandler> provider) {
        this.recommendationHandlerProvider = provider;
    }

    public static MembersInjector<PeriodicUpdateRecommendationsWorker> create(Provider<RecommendationHandler> provider) {
        return new PeriodicUpdateRecommendationsWorker_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.PeriodicUpdateRecommendationsWorker.recommendationHandler")
    public static void injectRecommendationHandler(PeriodicUpdateRecommendationsWorker periodicUpdateRecommendationsWorker, RecommendationHandler recommendationHandler) {
        periodicUpdateRecommendationsWorker.recommendationHandler = recommendationHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PeriodicUpdateRecommendationsWorker periodicUpdateRecommendationsWorker) {
        periodicUpdateRecommendationsWorker.recommendationHandler = this.recommendationHandlerProvider.get();
    }
}
