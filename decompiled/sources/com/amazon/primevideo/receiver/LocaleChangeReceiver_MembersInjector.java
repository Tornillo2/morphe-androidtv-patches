package com.amazon.primevideo.receiver;

import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
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
public final class LocaleChangeReceiver_MembersInjector implements MembersInjector<LocaleChangeReceiver> {
    public final Provider<RecommendationHandler> recommendationHandlerProvider;
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;

    public LocaleChangeReceiver_MembersInjector(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationHandler> provider2) {
        this.recommendationsMetricRecorderProvider = provider;
        this.recommendationHandlerProvider = provider2;
    }

    public static MembersInjector<LocaleChangeReceiver> create(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationHandler> provider2) {
        return new LocaleChangeReceiver_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.amazon.primevideo.receiver.LocaleChangeReceiver.recommendationHandler")
    public static void injectRecommendationHandler(LocaleChangeReceiver localeChangeReceiver, RecommendationHandler recommendationHandler) {
        localeChangeReceiver.recommendationHandler = recommendationHandler;
    }

    @InjectedFieldSignature("com.amazon.primevideo.receiver.LocaleChangeReceiver.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(LocaleChangeReceiver localeChangeReceiver, RecommendationsMetricRecorder recommendationsMetricRecorder) {
        localeChangeReceiver.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LocaleChangeReceiver localeChangeReceiver) {
        localeChangeReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
        localeChangeReceiver.recommendationHandler = this.recommendationHandlerProvider.get();
    }
}
