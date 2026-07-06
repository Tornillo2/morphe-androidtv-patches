package com.amazon.ignitionshared.metrics.startup;

import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Metric extends MetricsRecorder.Recordable {
    public boolean highPriority;
    public String name;
    public String source;

    public Metric(String str) {
        this(str, null, false);
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public String getSource() {
        return this.source;
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public boolean isHighPriority() {
        return this.highPriority;
    }

    public Metric(String str, String str2) {
        this(str, str2, false);
    }

    public Metric(String str, String str2, boolean z) {
        this.name = str;
        this.source = str2;
        this.highPriority = z;
    }
}
