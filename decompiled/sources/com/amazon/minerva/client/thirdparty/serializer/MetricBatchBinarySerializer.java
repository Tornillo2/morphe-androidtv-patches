package com.amazon.minerva.client.thirdparty.serializer;

import com.amazon.ion.IonException;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricBatchBinarySerializer implements MetricBatchSerializer {
    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public List<IonMetricEvent> deserialize(byte[] bArr) throws IOException, IonException {
        return IonMetricEventConverter.convertIonBinaryToIonMetricEvents(bArr);
    }

    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public byte[] serialize(List<IonMetricEvent> list) throws IOException, IonException {
        return IonMetricEventConverter.convertIonMetricEventsToIonBinary(list);
    }
}
