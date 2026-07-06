package com.amazon.ignitionshared.receiver;

import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata
public final class BootUpReceiver_MembersInjector implements MembersInjector<BootUpReceiver> {
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;
    public final Provider<RecommendationsScheduler> recommendationsSchedulerProvider;
    public final Provider<RequestStructureContentProviderManager> requestStructureProviderManagerProvider;

    public BootUpReceiver_MembersInjector(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationsScheduler> provider2, Provider<RequestStructureContentProviderManager> provider3) {
        this.recommendationsMetricRecorderProvider = provider;
        this.recommendationsSchedulerProvider = provider2;
        this.requestStructureProviderManagerProvider = provider3;
    }

    public static MembersInjector<BootUpReceiver> create(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationsScheduler> provider2, Provider<RequestStructureContentProviderManager> provider3) {
        return new BootUpReceiver_MembersInjector(provider, provider2, provider3);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.BootUpReceiver.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(BootUpReceiver bootUpReceiver, RecommendationsMetricRecorder recommendationsMetricRecorder) {
        bootUpReceiver.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.BootUpReceiver.recommendationsScheduler")
    public static void injectRecommendationsScheduler(BootUpReceiver bootUpReceiver, RecommendationsScheduler recommendationsScheduler) {
        bootUpReceiver.recommendationsScheduler = recommendationsScheduler;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.BootUpReceiver.requestStructureProviderManager")
    public static void injectRequestStructureProviderManager(BootUpReceiver bootUpReceiver, RequestStructureContentProviderManager requestStructureContentProviderManager) {
        bootUpReceiver.requestStructureProviderManager = requestStructureContentProviderManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BootUpReceiver bootUpReceiver) {
        bootUpReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
        bootUpReceiver.recommendationsScheduler = this.recommendationsSchedulerProvider.get();
        bootUpReceiver.requestStructureProviderManager = this.requestStructureProviderManagerProvider.get();
    }
}
