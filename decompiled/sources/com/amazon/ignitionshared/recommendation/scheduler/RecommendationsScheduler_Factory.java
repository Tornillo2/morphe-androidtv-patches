package com.amazon.ignitionshared.recommendation.scheduler;

import android.content.Context;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class RecommendationsScheduler_Factory implements Factory<RecommendationsScheduler> {
    public final Provider<Context> contextProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<RecommendationHandler> recommendationHandlerProvider;
    public final Provider<RecommendationUpdaterPeriodicWorkRequestBuilder> recommendationUpdaterPeriodicWorkRequestBuilderProvider;
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;

    public RecommendationsScheduler_Factory(Provider<DeviceProperties> provider, Provider<RecommendationsMetricRecorder> provider2, Provider<RecommendationHandler> provider3, Provider<RecommendationUpdaterPeriodicWorkRequestBuilder> provider4, Provider<Context> provider5) {
        this.devicePropertiesProvider = provider;
        this.recommendationsMetricRecorderProvider = provider2;
        this.recommendationHandlerProvider = provider3;
        this.recommendationUpdaterPeriodicWorkRequestBuilderProvider = provider4;
        this.contextProvider = provider5;
    }

    public static RecommendationsScheduler_Factory create(Provider<DeviceProperties> provider, Provider<RecommendationsMetricRecorder> provider2, Provider<RecommendationHandler> provider3, Provider<RecommendationUpdaterPeriodicWorkRequestBuilder> provider4, Provider<Context> provider5) {
        return new RecommendationsScheduler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static RecommendationsScheduler newInstance(DeviceProperties deviceProperties, RecommendationsMetricRecorder recommendationsMetricRecorder, RecommendationHandler recommendationHandler, RecommendationUpdaterPeriodicWorkRequestBuilder recommendationUpdaterPeriodicWorkRequestBuilder, Context context) {
        return new RecommendationsScheduler(deviceProperties, recommendationsMetricRecorder, recommendationHandler, recommendationUpdaterPeriodicWorkRequestBuilder, context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationsScheduler get() {
        return new RecommendationsScheduler(this.devicePropertiesProvider.get(), this.recommendationsMetricRecorderProvider.get(), this.recommendationHandlerProvider.get(), this.recommendationUpdaterPeriodicWorkRequestBuilderProvider.get(), this.contextProvider.get());
    }
}
