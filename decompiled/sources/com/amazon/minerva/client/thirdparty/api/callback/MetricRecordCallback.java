package com.amazon.minerva.client.thirdparty.api.callback;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MetricRecordCallback {
    void onError(MetricRecordStatus metricRecordStatus, MetricEvent metricEvent);
}
