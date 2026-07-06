package com.amazon.ignitionshared.metrics.startup;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class CounterMetric extends Metric {
    public int count;

    public CounterMetric(String str) {
        super(str);
        this.count = 0;
    }

    public void add(int i) {
        this.count += i;
    }

    public void setCount(int i) {
        this.count = i;
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public void subscribeToMetricEvent(MetricEvent metricEvent) {
        metricEvent.addLong(this.name, this.count);
    }

    public void subtract(int i) {
        this.count -= i;
    }

    public CounterMetric(String str, int i) {
        super(str);
        this.count = i;
    }

    public CounterMetric(String str, String str2) {
        super(str, str2, false);
        this.count = 0;
    }

    public CounterMetric(String str, String str2, int i) {
        super(str, str2, false);
        this.count = i;
    }

    public CounterMetric(String str, String str2, boolean z) {
        super(str, str2, z);
        this.count = 0;
    }

    public CounterMetric(String str, String str2, int i, boolean z) {
        super(str, str2, z);
        this.count = i;
    }
}
