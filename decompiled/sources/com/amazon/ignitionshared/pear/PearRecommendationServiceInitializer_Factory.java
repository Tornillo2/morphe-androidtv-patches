package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.watchnext.WatchNextPublisher;
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
public final class PearRecommendationServiceInitializer_Factory implements Factory<PearRecommendationServiceInitializer> {
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<PearAccessTokenProvider> pearAccessTokenProvider;
    public final Provider<PearUpdateStructure> pearUpdateStructureProvider;
    public final Provider<RecommendationHandler> recommendationHandlerProvider;
    public final Provider<RequestStructureContentProviderManager> requestStructureProviderManagerProvider;
    public final Provider<WatchNextPublisher> watchNextPublisherProvider;

    public PearRecommendationServiceInitializer_Factory(Provider<GMBMessageProcessor> provider, Provider<GMBMessageSender> provider2, Provider<PearUpdateStructure> provider3, Provider<RecommendationHandler> provider4, Provider<PearAccessTokenProvider> provider5, Provider<WatchNextPublisher> provider6, Provider<RequestStructureContentProviderManager> provider7) {
        this.gmbMessageProcessorProvider = provider;
        this.gmbMessageSenderProvider = provider2;
        this.pearUpdateStructureProvider = provider3;
        this.recommendationHandlerProvider = provider4;
        this.pearAccessTokenProvider = provider5;
        this.watchNextPublisherProvider = provider6;
        this.requestStructureProviderManagerProvider = provider7;
    }

    public static PearRecommendationServiceInitializer_Factory create(Provider<GMBMessageProcessor> provider, Provider<GMBMessageSender> provider2, Provider<PearUpdateStructure> provider3, Provider<RecommendationHandler> provider4, Provider<PearAccessTokenProvider> provider5, Provider<WatchNextPublisher> provider6, Provider<RequestStructureContentProviderManager> provider7) {
        return new PearRecommendationServiceInitializer_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static PearRecommendationServiceInitializer newInstance(GMBMessageProcessor gMBMessageProcessor, GMBMessageSender gMBMessageSender, PearUpdateStructure pearUpdateStructure, RecommendationHandler recommendationHandler, PearAccessTokenProvider pearAccessTokenProvider, WatchNextPublisher watchNextPublisher, RequestStructureContentProviderManager requestStructureContentProviderManager) {
        return new PearRecommendationServiceInitializer(gMBMessageProcessor, gMBMessageSender, pearUpdateStructure, recommendationHandler, pearAccessTokenProvider, watchNextPublisher, requestStructureContentProviderManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearRecommendationServiceInitializer get() {
        return new PearRecommendationServiceInitializer(this.gmbMessageProcessorProvider.get(), this.gmbMessageSenderProvider.get(), this.pearUpdateStructureProvider.get(), this.recommendationHandlerProvider.get(), this.pearAccessTokenProvider.get(), this.watchNextPublisherProvider.get(), this.requestStructureProviderManagerProvider.get());
    }
}
