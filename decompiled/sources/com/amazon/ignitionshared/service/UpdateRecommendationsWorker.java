package com.amazon.ignitionshared.service;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.ignitionshared.pear.RecommendationUpdater;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class UpdateRecommendationsWorker extends Worker {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_NAME = "RecommendationsRefresh.StartJob";
    public static final String TAG = "UpdateRecommendationsWorker";

    @Inject
    public Provider<RecommendationUpdater> recommendationUpdaterProvider;

    @Inject
    public RecommendationsMetricRecorder recommendationsMetricRecorder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateRecommendationsWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        Log.d(TAG, "Starting recommendations job");
        ApplicationComponent.Companion.getInstance().inject(this);
        getRecommendationsMetricRecorder().recordRefreshMetric(METRIC_NAME, 1);
        getRecommendationUpdaterProvider().get().updateRecommendations();
        return new ListenableWorker.Result.Success();
    }

    @NotNull
    public final Provider<RecommendationUpdater> getRecommendationUpdaterProvider() {
        Provider<RecommendationUpdater> provider = this.recommendationUpdaterProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationUpdaterProvider");
        throw null;
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

    public final void setRecommendationUpdaterProvider(@NotNull Provider<RecommendationUpdater> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.recommendationUpdaterProvider = provider;
    }

    public final void setRecommendationsMetricRecorder(@NotNull RecommendationsMetricRecorder recommendationsMetricRecorder) {
        Intrinsics.checkNotNullParameter(recommendationsMetricRecorder, "<set-?>");
        this.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }
}
