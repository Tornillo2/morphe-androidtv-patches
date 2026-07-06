package com.amazon.ignitionshared.metrics.startup;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TimerMetric extends Metric {
    public long startTime;
    public boolean timerStarted;
    public long totalElapsedTime;

    public TimerMetric(String str) {
        super(str);
        this.startTime = 0L;
        this.totalElapsedTime = 0L;
        this.timerStarted = false;
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.timerStarted = true;
    }

    public void stopTimer() {
        if (this.timerStarted) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.totalElapsedTime = (jCurrentTimeMillis - this.startTime) + this.totalElapsedTime;
            this.timerStarted = false;
        }
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public void subscribeToMetricEvent(MetricEvent metricEvent) {
        metricEvent.addLong(this.name, this.totalElapsedTime);
    }

    public TimerMetric(String str, String str2) {
        super(str, str2, false);
        this.startTime = 0L;
        this.totalElapsedTime = 0L;
        this.timerStarted = false;
    }

    public TimerMetric(String str, String str2, boolean z) {
        super(str, str2, z);
        this.startTime = 0L;
        this.totalElapsedTime = 0L;
        this.timerStarted = false;
    }
}
