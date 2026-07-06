package com.amazon.minerva.client.thirdparty.api;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.aggregation.AggregatedDoubleKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.AggregatedLongKeyValPair;
import com.amazon.minerva.client.thirdparty.metric.AggregatedDouble;
import com.amazon.minerva.client.thirdparty.metric.AggregatedLong;
import com.amazon.minerva.client.thirdparty.metric.ValueType;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AggregatedMetricEvent extends MetricEvent {
    public static final String TAG = "AggregatedMetricEvent";
    public Map<String, AggregatedDoubleKeyValPair> mAggregatedDoubles;
    public Map<String, AggregatedLongKeyValPair> mAggregatedLongs;

    public AggregatedMetricEvent(String str, String str2) {
        super(str, str2);
        this.mAggregatedLongs = new HashMap();
        this.mAggregatedDoubles = new HashMap();
    }

    public void addAggregatedDouble(String str, double d, AggregationType aggregationType) {
        if (IsCustomKeyValid(str)) {
            AggregatedDoubleKeyValPair aggDoubleKeyPair = this.mAggregatedDoubles.get(str);
            if (aggDoubleKeyPair == null) {
                aggDoubleKeyPair = AggregationType.getAggDoubleKeyPair(aggregationType);
                this.mAggregatedDoubles.put(str, aggDoubleKeyPair);
            }
            try {
                aggDoubleKeyPair.addValue(d);
            } catch (ArithmeticException e) {
                Log.e(TAG, "Error when trying to add value, value not added", e);
            }
        }
    }

    public void addAggregatedLong(String str, long j, AggregationType aggregationType) {
        if (IsCustomKeyValid(str)) {
            AggregatedLongKeyValPair aggLongKeyPair = this.mAggregatedLongs.get(str);
            if (aggLongKeyPair == null) {
                aggLongKeyPair = AggregationType.getAggLongKeyPair(aggregationType);
                this.mAggregatedLongs.put(str, aggLongKeyPair);
            }
            try {
                aggLongKeyPair.addValue(j);
            } catch (ArithmeticException e) {
                Log.e(TAG, "Error when trying to add value, value not added", e);
            }
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public void clear() {
        this.mAggregatedLongs.clear();
        this.mAggregatedDoubles.clear();
        super.clear();
    }

    public void convertAggregatedToKeyValuePairs() {
        for (Map.Entry<String, AggregatedLongKeyValPair> entry : this.mAggregatedLongs.entrySet()) {
            AggregatedLong aggregatedValue = entry.getValue().getAggregatedValue();
            if (aggregatedValue != null) {
                if (aggregatedValue.getCounts().size() == 1 && aggregatedValue.getCounts().get(0).longValue() == 1) {
                    super.addLong(entry.getKey(), aggregatedValue.getValues().get(0).longValue());
                } else {
                    this.mKeyValuePairs.put(entry.getKey(), ValueType.AGGREGATED_INTEGER.of(aggregatedValue));
                }
            }
        }
        for (Map.Entry<String, AggregatedDoubleKeyValPair> entry2 : this.mAggregatedDoubles.entrySet()) {
            AggregatedDouble aggregatedValue2 = entry2.getValue().getAggregatedValue();
            if (aggregatedValue2 != null) {
                if (aggregatedValue2.getCounts().size() != 1 || aggregatedValue2.getCounts().get(0).doubleValue() >= 2.0d) {
                    this.mKeyValuePairs.put(entry2.getKey(), ValueType.AGGREGATED_FLOAT.of(aggregatedValue2));
                } else {
                    super.addDouble(entry2.getKey(), aggregatedValue2.getValues().get(0).doubleValue());
                }
            }
        }
        this.mAggregatedLongs.clear();
        this.mAggregatedDoubles.clear();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public int getKeyValuePairCount() {
        return this.mAggregatedDoubles.size() + this.mAggregatedLongs.size() + this.mKeyValuePairs.size();
    }

    public AggregatedMetricEvent(String str, String str2, int i) {
        super(str, str2, i);
        this.mAggregatedLongs = new HashMap();
        this.mAggregatedDoubles = new HashMap();
    }
}
