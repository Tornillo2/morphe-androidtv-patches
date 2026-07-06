package com.amazon.primevideo.service;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.primevideo.di.PrimeVideoApplicationComponent;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"SpecifyJobSchedulerIdRange"})
public final class UpdateRecommendationsJobService extends JobService {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_NAME = "RecommendationsRefresh.ClearJobService";
    public static final String TAG = "UpdateRecommendationsJobService";

    @Inject
    public RecommendationsMetricRecorder recommendationsMetricRecorder;

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

    @Override // android.app.job.JobService
    public boolean onStartJob(@NotNull JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        Log.d(TAG, "Clearing old recommendations job");
        PrimeVideoApplicationComponent.Companion.getInstance().inject(this);
        getRecommendationsMetricRecorder().recordRefreshMetric(METRIC_NAME, 1);
        Object systemService = getApplicationContext().getSystemService("jobscheduler");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        ((JobScheduler) systemService).cancel(params.getJobId());
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@NotNull JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return false;
    }

    public final void setRecommendationsMetricRecorder(@NotNull RecommendationsMetricRecorder recommendationsMetricRecorder) {
        Intrinsics.checkNotNullParameter(recommendationsMetricRecorder, "<set-?>");
        this.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }
}
