package com.amazon.ignitionshared.metrics;

import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class JemallocAllocatorMetricRecorder {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String JEMALLOC_ALLOCATOR_LOAD_ERROR = "JemallocAllocatorLoad.Error";

    @NotNull
    public static final String JEMALLOC_ALLOCATOR_LOAD_SUCCESS = "JemallocAllocatorLoad.Success";

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
    public JemallocAllocatorMetricRecorder(@NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.minervaMetrics = minervaMetrics;
    }

    public final void recordError() {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.JEMALLOC_ALLOCATOR_LOAD_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(JEMALLOC_ALLOCATOR_LOAD_ERROR, 1L);
        metricEventCreateMetricEvent.addLong(JEMALLOC_ALLOCATOR_LOAD_SUCCESS, 0L);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    public final void recordSuccess() {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.JEMALLOC_ALLOCATOR_LOAD_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(JEMALLOC_ALLOCATOR_LOAD_SUCCESS, 1L);
        metricEventCreateMetricEvent.addLong(JEMALLOC_ALLOCATOR_LOAD_ERROR, 0L);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }
}
