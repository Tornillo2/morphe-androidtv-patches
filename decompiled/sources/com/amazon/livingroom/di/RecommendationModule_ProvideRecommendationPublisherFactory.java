package com.amazon.livingroom.di;

import android.content.ComponentName;
import android.content.Context;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext", "javax.inject.Named"})
public final class RecommendationModule_ProvideRecommendationPublisherFactory implements Factory<RecommendationPublisher> {
    public final Provider<String> applicationNameProvider;
    public final Provider<Integer> colorProvider;
    public final Provider<Context> contextProvider;
    public final Provider<ComponentName> deepLinkActivityNameProvider;
    public final Provider<String> defaultChannelIdProvider;
    public final Provider<Integer> iconProvider;

    public RecommendationModule_ProvideRecommendationPublisherFactory(Provider<Context> provider, Provider<ComponentName> provider2, Provider<String> provider3, Provider<Integer> provider4, Provider<Integer> provider5, Provider<String> provider6) {
        this.contextProvider = provider;
        this.deepLinkActivityNameProvider = provider2;
        this.applicationNameProvider = provider3;
        this.iconProvider = provider4;
        this.colorProvider = provider5;
        this.defaultChannelIdProvider = provider6;
    }

    public static RecommendationModule_ProvideRecommendationPublisherFactory create(Provider<Context> provider, Provider<ComponentName> provider2, Provider<String> provider3, Provider<Integer> provider4, Provider<Integer> provider5, Provider<String> provider6) {
        return new RecommendationModule_ProvideRecommendationPublisherFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static RecommendationPublisher provideRecommendationPublisher(Context context, ComponentName componentName, String str, int i, int i2, String str2) {
        return RecommendationModule.INSTANCE.provideRecommendationPublisher(context, componentName, str, i, i2, str2);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationPublisher get() {
        return RecommendationModule.INSTANCE.provideRecommendationPublisher(this.contextProvider.get(), this.deepLinkActivityNameProvider.get(), this.applicationNameProvider.get(), this.iconProvider.get().intValue(), this.colorProvider.get().intValue(), this.defaultChannelIdProvider.get());
    }
}
