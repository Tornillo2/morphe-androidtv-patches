package com.amazon.primevideo.nativebilling;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.deviceproperties.DeviceIdProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BillingProviderMetricRecorder {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MINERVA_METRIC_ERROR_COUNT_NAME = "ErrorCount";

    @NotNull
    public static final String MINERVA_METRIC_ERROR_TYPE_NAME = "ErrorType";

    @NotNull
    public static final String MINERVA_METRIC_LATENCY_NAME = "Latency";

    @NotNull
    public static final String MINERVA_METRIC_ORIGIN_NAME = "Origin";

    @NotNull
    public static final String MINERVA_METRIC_STARTED_NAME = "Started";

    @NotNull
    public final String eventOriginName;

    @NotNull
    public final MetricsRecorder metricsRecorder;
    public final MetricGroup minervaMetricGroup;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public BillingProviderMetricRecorder(@NotNull MetricsRecorder metricsRecorder, @NotNull String minervaSchemaId, @NotNull String eventOriginName) {
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        Intrinsics.checkNotNullParameter(minervaSchemaId, "minervaSchemaId");
        Intrinsics.checkNotNullParameter(eventOriginName, "eventOriginName");
        this.metricsRecorder = metricsRecorder;
        this.eventOriginName = eventOriginName;
        MetricGroup metricGroupCreateMetricGroup = metricsRecorder.createMetricGroup(minervaSchemaId);
        this.minervaMetricGroup = metricGroupCreateMetricGroup;
        MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, eventOriginName.concat(".Started"), 1, null, 4, null);
        MetricGroup.startTimerMetric$default(metricGroupCreateMetricGroup, eventOriginName.concat(".Latency"), null, 2, null);
    }

    public static /* synthetic */ void record$default(BillingProviderMetricRecorder billingProviderMetricRecorder, boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        billingProviderMetricRecorder.record(z, str);
    }

    public final void addCounterToMetricGroup(@NotNull String minervaMetricName) {
        Intrinsics.checkNotNullParameter(minervaMetricName, "minervaMetricName");
        MetricGroup.addCounterMetric$default(this.minervaMetricGroup, AbstractResolvableFuture$$ExternalSyntheticOutline1.m(this.eventOriginName, ExternalFourCCMapper.CODEC_NAME_SPLITTER, minervaMetricName), 1, null, 4, null);
    }

    public final void record(boolean z, @Nullable String str) {
        recordSuccessMetric(z);
        if (z) {
            return;
        }
        recordErrorMetric(str);
    }

    public final void recordErrorMetric(String str) {
        MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.APPLICATION_ERROR_SCHEMA_ID);
        if (str == null) {
            str = DeviceIdProvider.UNKNOWN;
        }
        MetricGroup.addStringMetric$default(metricGroupCreateMetricGroup, MINERVA_METRIC_ERROR_TYPE_NAME, str, null, 4, null);
        MetricGroup.addStringMetric$default(metricGroupCreateMetricGroup, "Origin", this.eventOriginName, null, 4, null);
        MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, MINERVA_METRIC_ERROR_COUNT_NAME, 1, null, 4, null);
        this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
    }

    public final void recordSuccessMetric(boolean z) {
        if (z) {
            this.minervaMetricGroup.stopTimerMetric(this.eventOriginName + ".Latency");
        } else {
            this.minervaMetricGroup.removeTimerMetric(this.eventOriginName + ".Latency");
        }
        MetricGroup.addCounterMetric$default(this.minervaMetricGroup, AbstractResolvableFuture$$ExternalSyntheticOutline0.m(this.eventOriginName, ".Success"), z ? 1 : 0, null, 4, null);
        this.metricsRecorder.recordMinerva(this.minervaMetricGroup);
    }
}
