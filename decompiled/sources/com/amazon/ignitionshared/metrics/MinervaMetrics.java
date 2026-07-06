package com.amazon.ignitionshared.metrics;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class MinervaMetrics {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "MinervaMetrics";

    @NotNull
    public final String applicationPackageName;

    @NotNull
    public final String applicationVersion;

    @NotNull
    public final String firmwareVersion;

    @NotNull
    public final AmazonMinerva minervaClient;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public MinervaMetrics(@NotNull AmazonMinerva minervaClient, @NotNull DeviceProperties deviceProperties, @NotNull MetricsRecorder metricsRecorder) {
        Intrinsics.checkNotNullParameter(minervaClient, "minervaClient");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        this.minervaClient = minervaClient;
        Object obj = deviceProperties.get(DeviceProperties.FIRMWARE_VERSION);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        this.firmwareVersion = (String) obj;
        Object obj2 = deviceProperties.get(DeviceProperties.APPLICATION_VERSION_NAME);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        this.applicationVersion = (String) obj2;
        Object obj3 = deviceProperties.get(DeviceProperties.APPLICATION_PACKAGE_NAME);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        this.applicationPackageName = (String) obj3;
        metricsRecorder.setupMinerva(this);
    }

    public static /* synthetic */ void record$default(MinervaMetrics minervaMetrics, MetricEvent metricEvent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        minervaMetrics.record(metricEvent, z);
    }

    @NotNull
    public final MetricEvent createMetricEvent(@NotNull String schemaId) {
        Intrinsics.checkNotNullParameter(schemaId, "schemaId");
        return new MetricEvent(MinervaConstants.METRIC_GROUP_ID, schemaId);
    }

    public final void flush() {
        Log.i(TAG, "Flushing in-memory metrics to disk");
        this.minervaClient.flush();
    }

    public final void record(@NotNull MetricEvent event, boolean z) {
        Intrinsics.checkNotNullParameter(event, "event");
        event.addPredefined(Predefined.DEVICE_TYPE);
        event.addPredefined(Predefined.MODEL);
        event.addString("applicationVersion", this.applicationVersion);
        event.addString("firmwareVersion", this.firmwareVersion);
        event.addString(Names.APPLICATION_PACKAGE_NAME, this.applicationPackageName);
        Log.d(TAG, "Recording minerva metric event with schema: " + event.getSchemaId() + "is highPriority=" + z + " with these key value pairs: " + event.getKeyValuePairs());
        this.minervaClient.record(event);
        if (z) {
            this.minervaClient.flush();
        }
    }

    public final void shutdown() {
        Log.i(TAG, "Shutting down MinervaMetrics background service");
        this.minervaClient.shutdownWithUpload();
    }

    public final void record(@NotNull MetricEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        record(event, false);
    }
}
