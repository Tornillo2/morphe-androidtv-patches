package com.amazon.minerva.client.thirdparty.api;

import com.amazon.minerva.client.thirdparty.metric.DataPoint;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.metric.TypedValue;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ConcurrentAggregatedMetricEvent extends AggregatedMetricEvent {
    public static final String TAG = "ConcurrentAggregatedMetricEvent";

    public ConcurrentAggregatedMetricEvent(String str, String str2) {
        super(str, str2);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized boolean IsCustomKeyValid(String str) {
        return super.IsCustomKeyValid(str);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent
    public synchronized void addAggregatedDouble(String str, double d, AggregationType aggregationType) {
        super.addAggregatedDouble(str, d, aggregationType);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent
    public synchronized void addAggregatedLong(String str, long j, AggregationType aggregationType) {
        super.addAggregatedLong(str, j, aggregationType);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addBoolean(String str, boolean z) {
        super.addBoolean(str, z);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addDouble(String str, double d) {
        super.addDouble(str, d);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addLong(String str, long j) {
        super.addLong(str, j);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addPredefined(Predefined predefined) {
        super.addPredefined(predefined);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addString(String str, String str2) {
        super.addString(str, str2);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addTimestamp(String str, Date date) {
        super.addTimestamp(str, date);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent, com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void clear() {
        super.clear();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent
    public synchronized void convertAggregatedToKeyValuePairs() {
        super.convertAggregatedToKeyValuePairs();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized Timestamp getClientTimestamp() {
        return this.mClientTimestamp;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent, com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized int getKeyValuePairCount() {
        return super.getKeyValuePairCount();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized Map<String, TypedValue<?>> getKeyValuePairs() {
        return this.mKeyValuePairs;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized List<DataPoint> getKeyValuePairsAsDataPoints() {
        return super.getKeyValuePairsAsDataPoints();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized UUID getMetricEventId() {
        return this.mMetricEventId;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized String getMetricGroupId() {
        return this.mMetricGroupId;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized int getNumInvalidKeyValuePairs() {
        return this.mNumInvalidKeyValuePairs;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized int getSamplingRate() {
        return this.mSamplingRate;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized String getSchemaId() {
        return this.mSchemaId;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void setClientTimestamp(Timestamp timestamp) {
        this.mClientTimestamp = timestamp;
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public synchronized void addTimestamp(String str, Date date, TimeZone timeZone) {
        super.addTimestamp(str, date, timeZone);
    }
}
