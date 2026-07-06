package com.amazon.primevideo.service;

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
public final class UpdateRecommendationsJobService_MembersInjector implements MembersInjector<UpdateRecommendationsJobService> {
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;

    public UpdateRecommendationsJobService_MembersInjector(Provider<RecommendationsMetricRecorder> provider) {
        this.recommendationsMetricRecorderProvider = provider;
    }

    public static MembersInjector<UpdateRecommendationsJobService> create(Provider<RecommendationsMetricRecorder> provider) {
        return new UpdateRecommendationsJobService_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.amazon.primevideo.service.UpdateRecommendationsJobService.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(UpdateRecommendationsJobService updateRecommendationsJobService, RecommendationsMetricRecorder recommendationsMetricRecorder) {
        updateRecommendationsJobService.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateRecommendationsJobService updateRecommendationsJobService) {
        updateRecommendationsJobService.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
    }
}
