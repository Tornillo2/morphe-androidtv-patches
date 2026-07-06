package com.amazon.ignitionshared.metrics.startup;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetricGroup extends MetricsRecorder.Recordable {
    public boolean highPriority;

    @NotNull
    public final List<Metric> metrics;

    @NotNull
    public final String source;

    @NotNull
    public final Map<String, TimerMetric> timers;

    public MetricGroup(@NotNull String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.metrics = new ArrayList();
        this.timers = new LinkedHashMap();
        this.source = source;
        this.highPriority = false;
    }

    public static /* synthetic */ void addCounterMetric$default(MetricGroup metricGroup, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = metricGroup.source;
        }
        metricGroup.addCounterMetric(str, i, str2);
    }

    public static /* synthetic */ void addStringMetric$default(MetricGroup metricGroup, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = metricGroup.source;
        }
        metricGroup.addStringMetric(str, str2, str3);
    }

    public static /* synthetic */ void startTimerMetric$default(MetricGroup metricGroup, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = metricGroup.source;
        }
        metricGroup.startTimerMetric(str, str2);
    }

    @JvmOverloads
    public final void addCounterMetric(@NotNull String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        addCounterMetric$default(this, name, i, null, 4, null);
    }

    @JvmOverloads
    public final void addStringMetric(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        addStringMetric$default(this, name, value, null, 4, null);
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    @NotNull
    public String getSource() {
        return this.source;
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public boolean isHighPriority() {
        return this.highPriority;
    }

    public final void removeTimerMetric(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.timers.remove(name);
    }

    public final void setHighPriority(boolean z) {
        this.highPriority = z;
    }

    @JvmOverloads
    public final void startTimerMetric(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        startTimerMetric$default(this, name, null, 2, null);
    }

    public final void stopTimerMetric(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        TimerMetric timerMetric = this.timers.get(name);
        if (timerMetric != null) {
            timerMetric.stopTimer();
            this.metrics.add(timerMetric);
        }
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public void subscribeToMetricEvent(@NotNull MetricEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Iterator<Metric> it = this.metrics.iterator();
        while (it.hasNext()) {
            it.next().subscribeToMetricEvent(event);
        }
    }

    @JvmOverloads
    public final void addCounterMetric(@NotNull String name, int i, @NotNull String source) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        this.metrics.add(new CounterMetric(name, source, i));
    }

    @JvmOverloads
    public final void addStringMetric(@NotNull String name, @NotNull String value, @NotNull String source) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(source, "source");
        this.metrics.add(new StringMetric(name, source, value, false, 8, null));
    }

    @JvmOverloads
    public final void startTimerMetric(@NotNull String name, @NotNull String source) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        TimerMetric timerMetric = new TimerMetric(name, source);
        timerMetric.startTimer();
        this.timers.put(name, timerMetric);
    }

    public MetricGroup(@NotNull String source, boolean z) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.metrics = new ArrayList();
        this.timers = new LinkedHashMap();
        this.source = source;
        this.highPriority = z;
    }
}
