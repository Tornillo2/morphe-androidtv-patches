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
public final class ScheduleRecommendationsOnInstallReceiver_MembersInjector implements MembersInjector<ScheduleRecommendationsOnInstallReceiver> {
    public final Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;
    public final Provider<RecommendationsScheduler> recommendationsSchedulerProvider;
    public final Provider<RequestStructureContentProviderManager> requestStructureProviderManagerProvider;

    public ScheduleRecommendationsOnInstallReceiver_MembersInjector(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationsScheduler> provider2, Provider<RequestStructureContentProviderManager> provider3) {
        this.recommendationsMetricRecorderProvider = provider;
        this.recommendationsSchedulerProvider = provider2;
        this.requestStructureProviderManagerProvider = provider3;
    }

    public static MembersInjector<ScheduleRecommendationsOnInstallReceiver> create(Provider<RecommendationsMetricRecorder> provider, Provider<RecommendationsScheduler> provider2, Provider<RequestStructureContentProviderManager> provider3) {
        return new ScheduleRecommendationsOnInstallReceiver_MembersInjector(provider, provider2, provider3);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.ScheduleRecommendationsOnInstallReceiver.recommendationsMetricRecorder")
    public static void injectRecommendationsMetricRecorder(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver, RecommendationsMetricRecorder recommendationsMetricRecorder) {
        scheduleRecommendationsOnInstallReceiver.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.ScheduleRecommendationsOnInstallReceiver.recommendationsScheduler")
    public static void injectRecommendationsScheduler(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver, RecommendationsScheduler recommendationsScheduler) {
        scheduleRecommendationsOnInstallReceiver.recommendationsScheduler = recommendationsScheduler;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.receiver.ScheduleRecommendationsOnInstallReceiver.requestStructureProviderManager")
    public static void injectRequestStructureProviderManager(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver, RequestStructureContentProviderManager requestStructureContentProviderManager) {
        scheduleRecommendationsOnInstallReceiver.requestStructureProviderManager = requestStructureContentProviderManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver) {
        scheduleRecommendationsOnInstallReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
        scheduleRecommendationsOnInstallReceiver.recommendationsScheduler = this.recommendationsSchedulerProvider.get();
        scheduleRecommendationsOnInstallReceiver.requestStructureProviderManager = this.requestStructureProviderManagerProvider.get();
    }
}
