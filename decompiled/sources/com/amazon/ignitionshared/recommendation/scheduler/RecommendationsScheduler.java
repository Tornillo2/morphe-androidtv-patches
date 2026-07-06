package com.amazon.ignitionshared.recommendation.scheduler;

import android.content.Context;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.Operation;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.futures.AbstractFuture;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.metric.MetricResult;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRecommendationsScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RecommendationsScheduler.kt\ncom/amazon/ignitionshared/recommendation/scheduler/RecommendationsScheduler\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"})
public final class RecommendationsScheduler {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MINERVA_METRIC_NAME = "RecommendationsRefresh.ScheduleRecommendationsUpdate";
    public static final String TAG = "RecommendationsScheduler";

    @NotNull
    public static final String UPDATE_RECOMMENDATIONS_PERIODIC_WORK = "UpdateRecommendationsWorker";

    @NotNull
    public final Context context;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final RecommendationHandler recommendationHandler;

    @NotNull
    public final RecommendationUpdaterPeriodicWorkRequestBuilder recommendationUpdaterPeriodicWorkRequestBuilder;

    @NotNull
    public final RecommendationsMetricRecorder recommendationsMetricRecorder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public RecommendationsScheduler(@NotNull DeviceProperties deviceProperties, @NotNull RecommendationsMetricRecorder recommendationsMetricRecorder, @NotNull RecommendationHandler recommendationHandler, @NotNull RecommendationUpdaterPeriodicWorkRequestBuilder recommendationUpdaterPeriodicWorkRequestBuilder, @ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(recommendationsMetricRecorder, "recommendationsMetricRecorder");
        Intrinsics.checkNotNullParameter(recommendationHandler, "recommendationHandler");
        Intrinsics.checkNotNullParameter(recommendationUpdaterPeriodicWorkRequestBuilder, "recommendationUpdaterPeriodicWorkRequestBuilder");
        Intrinsics.checkNotNullParameter(context, "context");
        this.deviceProperties = deviceProperties;
        this.recommendationsMetricRecorder = recommendationsMetricRecorder;
        this.recommendationHandler = recommendationHandler;
        this.recommendationUpdaterPeriodicWorkRequestBuilder = recommendationUpdaterPeriodicWorkRequestBuilder;
        this.context = context;
    }

    public static /* synthetic */ void schedule$default(RecommendationsScheduler recommendationsScheduler, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = recommendationsScheduler.getDefaultRefreshPeriodInMinutes();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        recommendationsScheduler.schedule(j, z);
    }

    public final long getDefaultRefreshPeriodInMinutes() {
        Object obj = this.deviceProperties.get(DeviceProperties.PEAR_REFRESH_INTERVAL_IN_MINUTES);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Number) obj).longValue();
    }

    public final void schedule(long j, boolean z) {
        Object objCreateFailure;
        int i;
        Log.d(TAG, "Scheduling recommendations update job");
        try {
            objCreateFailure = (Operation.State.SUCCESS) ((AbstractFuture) WorkManagerImpl.getInstance(this.context).enqueueUniquePeriodicWork(UPDATE_RECOMMENDATIONS_PERIODIC_WORK, z ? ExistingPeriodicWorkPolicy.REPLACE : ExistingPeriodicWorkPolicy.KEEP, this.recommendationUpdaterPeriodicWorkRequestBuilder.buildWithIntervalInMinutes(j)).getResult()).get();
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        boolean z2 = objCreateFailure instanceof Result.Failure;
        if (!z2) {
            i = MetricResult.SUCCESS.value;
        } else {
            if (!z2) {
                throw new NoWhenBranchMatchedException();
            }
            i = MetricResult.FAIL.value;
        }
        this.recommendationsMetricRecorder.recordRefreshMetric(MINERVA_METRIC_NAME, i);
    }

    public final void scheduleAndRun() {
        schedule$default(this, 0L, false, 3, null);
        this.recommendationHandler.updateRecommendations();
    }
}
