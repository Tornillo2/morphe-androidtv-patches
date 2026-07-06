package com.amazon.minerva.client.thirdparty.aggregation;

import com.amazon.minerva.client.thirdparty.metric.AggregatedDouble;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SimpleAvgAggregatedDoubleKeyValPair implements AggregatedDoubleKeyValPair {
    public double mSum = 0.0d;
    public double mCount = 0.0d;

    @Override // com.amazon.minerva.client.thirdparty.aggregation.AggregatedDoubleKeyValPair
    public void addValue(double d) {
        double d2 = this.mSum + d;
        if (Double.isInfinite(d2) || Double.isNaN(d2)) {
            throw new ArithmeticException("Value added would overflow Double, dropped");
        }
        this.mSum = d2;
        this.mCount += 1.0d;
    }

    @Override // com.amazon.minerva.client.thirdparty.aggregation.AggregatedDoubleKeyValPair
    public AggregatedDouble getAggregatedValue() {
        double d = this.mCount;
        if (d == 0.0d) {
            return null;
        }
        return new AggregatedDouble(Collections.singletonList(Double.valueOf(this.mSum / d)), Collections.singletonList(Double.valueOf(this.mCount)));
    }
}
