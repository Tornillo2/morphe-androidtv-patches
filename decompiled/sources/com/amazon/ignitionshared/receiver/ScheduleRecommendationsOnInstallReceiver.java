package com.amazon.ignitionshared.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ScheduleRecommendationsOnInstallReceiver extends BroadcastReceiver {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_NAME = "RecommendationsRefresh.Install";
    public static final String TAG = "ScheduleRecommendationsOnInstallReceiver";

    @Inject
    public RecommendationsMetricRecorder recommendationsMetricRecorder;

    @Inject
    public RecommendationsScheduler recommendationsScheduler;

    @Inject
    public RequestStructureContentProviderManager requestStructureProviderManager;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @NotNull
    public final RecommendationsMetricRecorder getRecommendationsMetricRecorder() {
        RecommendationsMetricRecorder recommendationsMetricRecorder = this.recommendationsMetricRecorder;
        if (recommendationsMetricRecorder != null) {
            return recommendationsMetricRecorder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationsMetricRecorder");
        throw null;
    }

    @NotNull
    public final RecommendationsScheduler getRecommendationsScheduler() {
        RecommendationsScheduler recommendationsScheduler = this.recommendationsScheduler;
        if (recommendationsScheduler != null) {
            return recommendationsScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationsScheduler");
        throw null;
    }

    @NotNull
    public final RequestStructureContentProviderManager getRequestStructureProviderManager() {
        RequestStructureContentProviderManager requestStructureContentProviderManager = this.requestStructureProviderManager;
        if (requestStructureContentProviderManager != null) {
            return requestStructureContentProviderManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("requestStructureProviderManager");
        throw null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.d(TAG, "RunOnInstallReceiver initiated");
        ApplicationComponent.Companion.getInstance().inject(this);
        getRecommendationsMetricRecorder().recordRefreshMetric(METRIC_NAME, 1);
        getRecommendationsScheduler().scheduleAndRun();
        getRequestStructureProviderManager().updateRequestStructure();
    }

    public final void setRecommendationsMetricRecorder(@NotNull RecommendationsMetricRecorder recommendationsMetricRecorder) {
        Intrinsics.checkNotNullParameter(recommendationsMetricRecorder, "<set-?>");
        this.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }

    public final void setRecommendationsScheduler(@NotNull RecommendationsScheduler recommendationsScheduler) {
        Intrinsics.checkNotNullParameter(recommendationsScheduler, "<set-?>");
        this.recommendationsScheduler = recommendationsScheduler;
    }

    public final void setRequestStructureProviderManager(@NotNull RequestStructureContentProviderManager requestStructureContentProviderManager) {
        Intrinsics.checkNotNullParameter(requestStructureContentProviderManager, "<set-?>");
        this.requestStructureProviderManager = requestStructureContentProviderManager;
    }
}
