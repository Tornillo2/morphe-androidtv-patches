package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher;
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
public final class PearAccessTokenProvider_Factory implements Factory<PearAccessTokenProvider> {
    public final Provider<PearUpdateStructure> pearUpdateStructureProvider;
    public final Provider<PearUriBuilder> pearUriBuilderProvider;
    public final Provider<RecommendationRequestDispatcher> recommendationRequestDispatcherProvider;

    public PearAccessTokenProvider_Factory(Provider<PearUpdateStructure> provider, Provider<RecommendationRequestDispatcher> provider2, Provider<PearUriBuilder> provider3) {
        this.pearUpdateStructureProvider = provider;
        this.recommendationRequestDispatcherProvider = provider2;
        this.pearUriBuilderProvider = provider3;
    }

    public static PearAccessTokenProvider_Factory create(Provider<PearUpdateStructure> provider, Provider<RecommendationRequestDispatcher> provider2, Provider<PearUriBuilder> provider3) {
        return new PearAccessTokenProvider_Factory(provider, provider2, provider3);
    }

    public static PearAccessTokenProvider newInstance(PearUpdateStructure pearUpdateStructure, RecommendationRequestDispatcher recommendationRequestDispatcher, PearUriBuilder pearUriBuilder) {
        return new PearAccessTokenProvider(pearUpdateStructure, recommendationRequestDispatcher, pearUriBuilder);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearAccessTokenProvider get() {
        return new PearAccessTokenProvider(this.pearUpdateStructureProvider.get(), this.recommendationRequestDispatcherProvider.get(), this.pearUriBuilderProvider.get());
    }
}
