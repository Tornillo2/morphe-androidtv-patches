package com.amazon.ignitionshared.service;

import com.amazon.ignitionshared.pear.RecommendationUpdater;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class UpdateRecommendationsWorker_MembersInjector implements MembersInjector<UpdateRecommendationsWorker> {
    public final Provider<RecommendationUpdater> recommendationUpdaterProvider;
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;

    public UpdateRecommendationsWorker_MembersInjector(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationUpdater> provider2) {
        this.recommendationsMetricRecorderProvider = provider;
        this.recommendationUpdaterProvider = provider2;
    }

    public static MembersInjector<UpdateRecommendationsWorker> create(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationUpdater> provider2) {
        return new UpdateRecommendationsWorker_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.UpdateRecommendationsWorker.recommendationUpdaterProvider")
    public static void injectRecommendationUpdaterProvider(UpdateRecommendationsWorker updateRecommendationsWorker, javax.inject.Provider<RecommendationUpdater> provider) {
        updateRecommendationsWorker.recommendationUpdaterProvider = provider;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.service.UpdateRecommendationsWorker.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(UpdateRecommendationsWorker updateRecommendationsWorker, RecommendationsMetricRecorder recommendationsMetricRecorder) {
        updateRecommendationsWorker.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateRecommendationsWorker updateRecommendationsWorker) {
        updateRecommendationsWorker.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
        updateRecommendationsWorker.recommendationUpdaterProvider = this.recommendationUpdaterProvider;
    }
}
