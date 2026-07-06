package com.amazon.ignitionshared.metrics.startup;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class MetricsRecorder {
    public ConcurrentLinkedQueue<Recordable> minervaDeferredMetrics = new ConcurrentLinkedQueue<>();
    public MinervaMetrics minervaMetrics;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Recordable {
        public abstract String getSource();

        public abstract boolean isHighPriority();

        public abstract void subscribeToMetricEvent(MetricEvent metricEvent);
    }

    @Inject
    public MetricsRecorder() {
    }

    public MetricGroup createMetricGroup(String str) {
        return new MetricGroup(str);
    }

    public synchronized void recordMinerva(Recordable recordable) {
        try {
            if (this.minervaMetrics != null) {
                recordMinervaMetric(recordable);
            } else {
                this.minervaDeferredMetrics.add(recordable);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void recordMinervaMetric(Recordable recordable) {
        MinervaMetrics minervaMetrics = this.minervaMetrics;
        if (minervaMetrics == null) {
            throw new IllegalStateException("MetricsRecorder tried to record metrics with Minerva, but Minerva client is not initialized.");
        }
        MetricEvent metricEventCreateMetricEvent = minervaMetrics.createMetricEvent(recordable.getSource());
        recordable.subscribeToMetricEvent(metricEventCreateMetricEvent);
        this.minervaMetrics.record(metricEventCreateMetricEvent, recordable.isHighPriority());
    }

    public synchronized void setupMinerva(MinervaMetrics minervaMetrics) {
        try {
            if (this.minervaMetrics != null) {
                throw new IllegalStateException("MinervaMetrics has already been initialized.");
            }
            this.minervaMetrics = minervaMetrics;
            Iterator<Recordable> it = this.minervaDeferredMetrics.iterator();
            while (it.hasNext()) {
                recordMinerva(it.next());
            }
            this.minervaDeferredMetrics = null;
        } catch (Throwable th) {
            throw th;
        }
    }
}
