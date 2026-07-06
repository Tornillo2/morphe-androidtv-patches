package com.amazon.minerva.client.thirdparty.transport;

import com.amazon.ion.IonString;
import com.amazon.ion.IonSymbol;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricEventResponse {
    public final IonString metricEventId;
    public final IonSymbol status;

    public MetricEventResponse(IonString ionString, IonSymbol ionSymbol) {
        this.metricEventId = ionString;
        this.status = ionSymbol;
    }

    public IonString getMetricEventId() {
        return this.metricEventId;
    }

    public IonSymbol getStatus() {
        return this.status;
    }
}
