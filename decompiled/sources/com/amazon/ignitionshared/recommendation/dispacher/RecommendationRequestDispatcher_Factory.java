package com.amazon.ignitionshared.recommendation.dispacher;

import com.android.volley.RequestQueue;
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
public final class RecommendationRequestDispatcher_Factory implements Factory<RecommendationRequestDispatcher> {
    public final Provider<RequestQueue> requestQueueProvider;

    public RecommendationRequestDispatcher_Factory(Provider<RequestQueue> provider) {
        this.requestQueueProvider = provider;
    }

    public static RecommendationRequestDispatcher_Factory create(Provider<RequestQueue> provider) {
        return new RecommendationRequestDispatcher_Factory(provider);
    }

    public static RecommendationRequestDispatcher newInstance(RequestQueue requestQueue) {
        return new RecommendationRequestDispatcher(requestQueue);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationRequestDispatcher get() {
        return new RecommendationRequestDispatcher(this.requestQueueProvider.get());
    }
}
