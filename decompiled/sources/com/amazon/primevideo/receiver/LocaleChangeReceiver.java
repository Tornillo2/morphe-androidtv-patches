package com.amazon.primevideo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.primevideo.di.PrimeVideoApplicationComponent;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LocaleChangeReceiver extends BroadcastReceiver {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_NAME = "RecommendationsRefresh.LocaleChange";
    public static final String TAG = "LocaleChangeReceiver";

    @Inject
    public RecommendationHandler recommendationHandler;

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
    public final RecommendationHandler getRecommendationHandler() {
        RecommendationHandler recommendationHandler = this.recommendationHandler;
        if (recommendationHandler != null) {
            return recommendationHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationHandler");
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

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.d(TAG, "LocaleChangeReceiver initiated");
        PrimeVideoApplicationComponent.Companion.getInstance().inject(this);
        String action = intent.getAction();
        if (action == null || !StringsKt__StringsJVMKt.endsWith$default(action, "android.intent.action.LOCALE_CHANGED", false, 2, null)) {
            return;
        }
        getRecommendationsMetricRecorder().recordRefreshMetric(METRIC_NAME, 1);
        getRecommendationHandler().updateRecommendations();
    }

    public final void setRecommendationHandler(@NotNull RecommendationHandler recommendationHandler) {
        Intrinsics.checkNotNullParameter(recommendationHandler, "<set-?>");
        this.recommendationHandler = recommendationHandler;
    }

    public final void setRecommendationsMetricRecorder(@NotNull RecommendationsMetricRecorder recommendationsMetricRecorder) {
        Intrinsics.checkNotNullParameter(recommendationsMetricRecorder, "<set-?>");
        this.recommendationsMetricRecorder = recommendationsMetricRecorder;
    }
}
