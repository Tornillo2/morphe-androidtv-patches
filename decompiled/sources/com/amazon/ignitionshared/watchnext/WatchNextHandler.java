package com.amazon.ignitionshared.watchnext;

import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import java.util.List;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@RequiresApi(api = 26)
public final class WatchNextHandler {

    @NotNull
    public static final String CLEAR_CAROUSEL_METRIC = "WatchNextHandler.ClearCarousel";

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "WatchNextHandler";

    @NotNull
    public static final String UPDATE_CAROUSEL_METRIC = "WatchNextHandler.UpdateCarousel";

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final WatchNextParser parser;

    @NotNull
    public final WatchNextPublisher publisher;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public WatchNextHandler(@NotNull WatchNextPublisher publisher, @NotNull WatchNextParser parser, @NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(publisher, "publisher");
        Intrinsics.checkNotNullParameter(parser, "parser");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.publisher = publisher;
        this.parser = parser;
        this.minervaMetrics = minervaMetrics;
    }

    public final void clearCarousel() {
        Log.d(TAG, "Clearing carousel");
        recordMetric(CLEAR_CAROUSEL_METRIC, this.publisher.clearEntries() ? 1 : 0);
    }

    public final void recordMetric(String str, int i) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.WATCH_NEXT_HANDLER_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(str, i);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.amazon.ignitionshared.watchnext.WatchNextHandler] */
    public final void updateCarousel(@Nullable String str) {
        if (str == null || str.length() == 0) {
            recordMetric(UPDATE_CAROUSEL_METRIC, 0);
            return;
        }
        Log.d(TAG, "Updating carousel");
        List<WatchNextProgram> watchNextMessage = this.parser.parseWatchNextMessage(str);
        recordMetric(UPDATE_CAROUSEL_METRIC, watchNextMessage.isEmpty() ? 0 : this.publisher.upsertEntries(watchNextMessage));
    }
}
