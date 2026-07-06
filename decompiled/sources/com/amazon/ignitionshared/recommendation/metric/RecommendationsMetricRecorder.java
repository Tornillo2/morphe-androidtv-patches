package com.amazon.ignitionshared.recommendation.metric;

import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class RecommendationsMetricRecorder {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "RecommendationsMetricRecorder";

    @NotNull
    public final MinervaMetrics minervaMetrics;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public RecommendationsMetricRecorder(@NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.minervaMetrics = minervaMetrics;
    }

    public static /* synthetic */ void recordRefreshMetric$default(RecommendationsMetricRecorder recommendationsMetricRecorder, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        recommendationsMetricRecorder.recordRefreshMetric(str, i);
    }

    @JvmOverloads
    public final void recordRefreshMetric(@NotNull String minervaMetricName) {
        Intrinsics.checkNotNullParameter(minervaMetricName, "minervaMetricName");
        recordRefreshMetric(minervaMetricName, 1);
    }

    @JvmOverloads
    public final void recordRefreshMetric(@NotNull String minervaMetricName, int i) {
        Intrinsics.checkNotNullParameter(minervaMetricName, "minervaMetricName");
        Log.d(TAG, "Logging recommendations refresh metric name= ".concat(minervaMetricName));
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.RECOMMENDATIONS_REFRESH_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(minervaMetricName, i);
        this.minervaMetrics.record(metricEventCreateMetricEvent, true);
    }
}
