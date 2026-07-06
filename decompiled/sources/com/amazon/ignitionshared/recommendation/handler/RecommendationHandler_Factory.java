package com.amazon.ignitionshared.recommendation.handler;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class RecommendationHandler_Factory implements Factory<RecommendationHandler> {
    public final Provider<Context> contextProvider;

    public RecommendationHandler_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static RecommendationHandler_Factory create(Provider<Context> provider) {
        return new RecommendationHandler_Factory(provider);
    }

    public static RecommendationHandler newInstance(Context context) {
        return new RecommendationHandler(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationHandler get() {
        return new RecommendationHandler(this.contextProvider.get());
    }
}
