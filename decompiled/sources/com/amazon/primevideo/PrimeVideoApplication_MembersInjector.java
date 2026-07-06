package com.amazon.primevideo;

import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.RendererManager;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class PrimeVideoApplication_MembersInjector implements MembersInjector<PrimeVideoApplication> {
    public final Provider<ApplicationAccessTokenProvider> applicationAccessTokenProvider;
    public final Provider<RecommendationHandler> recommendationHandlerProvider;
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;
    public final Provider<RendererManager> rendererManagerProvider;
    public final Provider<IgniteRenderer> rendererProvider;

    public PrimeVideoApplication_MembersInjector(Provider<RendererManager> provider, Provider<IgniteRenderer> provider2, Provider<ApplicationAccessTokenProvider> provider3, Provider<RecommendationsMetricRecorder> provider4, Provider<RecommendationHandler> provider5) {
        this.rendererManagerProvider = provider;
        this.rendererProvider = provider2;
        this.applicationAccessTokenProvider = provider3;
        this.recommendationsMetricRecorderProvider = provider4;
        this.recommendationHandlerProvider = provider5;
    }

    public static MembersInjector<PrimeVideoApplication> create(Provider<RendererManager> provider, Provider<IgniteRenderer> provider2, Provider<ApplicationAccessTokenProvider> provider3, Provider<RecommendationsMetricRecorder> provider4, Provider<RecommendationHandler> provider5) {
        return new PrimeVideoApplication_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @InjectedFieldSignature("com.amazon.primevideo.PrimeVideoApplication.applicationAccessTokenProvider")
    public static void injectApplicationAccessTokenProvider(PrimeVideoApplication primeVideoApplication, ApplicationAccessTokenProvider applicationAccessTokenProvider) {
        primeVideoApplication.applicationAccessTokenProvider = applicationAccessTokenProvider;
    }

    @InjectedFieldSignature("com.amazon.primevideo.PrimeVideoApplication.recommendationHandler")
    public static void injectRecommendationHandler(PrimeVideoApplication primeVideoApplication, Lazy<RecommendationHandler> lazy) {
        primeVideoApplication.recommendationHandler = lazy;
    }

    @InjectedFieldSignature("com.amazon.primevideo.PrimeVideoApplication.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(PrimeVideoApplication primeVideoApplication, Lazy<RecommendationsMetricRecorder> lazy) {
        primeVideoApplication.recommendationsMetricRecorder = lazy;
    }

    @InjectedFieldSignature("com.amazon.primevideo.PrimeVideoApplication.renderer")
    public static void injectRenderer(PrimeVideoApplication primeVideoApplication, IgniteRenderer igniteRenderer) {
        primeVideoApplication.renderer = igniteRenderer;
    }

    @InjectedFieldSignature("com.amazon.primevideo.PrimeVideoApplication.rendererManager")
    public static void injectRendererManager(PrimeVideoApplication primeVideoApplication, RendererManager rendererManager) {
        primeVideoApplication.rendererManager = rendererManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PrimeVideoApplication primeVideoApplication) {
        primeVideoApplication.rendererManager = this.rendererManagerProvider.get();
        primeVideoApplication.renderer = this.rendererProvider.get();
        primeVideoApplication.applicationAccessTokenProvider = this.applicationAccessTokenProvider.get();
        primeVideoApplication.recommendationsMetricRecorder = DoubleCheck.lazy((Provider) this.recommendationsMetricRecorderProvider);
        primeVideoApplication.recommendationHandler = DoubleCheck.lazy((Provider) this.recommendationHandlerProvider);
    }
}
