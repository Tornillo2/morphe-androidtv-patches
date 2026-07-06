package com.amazon.ignitionshared.service;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.livingroom.di.ApplicationComponent;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PeriodicUpdateRecommendationsWorker extends Worker {

    @Inject
    public RecommendationHandler recommendationHandler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodicUpdateRecommendationsWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        ApplicationComponent.Companion.getInstance().inject(this);
        getRecommendationHandler().updateRecommendations();
        return new ListenableWorker.Result.Success();
    }

    @NotNull
    public final RecommendationHandler getRecommendationHandler() {
        RecommendationHandler recommendationHandler = this.recommendationHandler;
        if (recommendationHandler != null) {
            return recommendationHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationHandler");
        throw null;
    }

    public final void setRecommendationHandler(@NotNull RecommendationHandler recommendationHandler) {
        Intrinsics.checkNotNullParameter(recommendationHandler, "<set-?>");
        this.recommendationHandler = recommendationHandler;
    }
}
