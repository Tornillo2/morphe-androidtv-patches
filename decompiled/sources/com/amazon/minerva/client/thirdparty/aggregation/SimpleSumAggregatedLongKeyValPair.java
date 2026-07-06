package com.amazon.minerva.client.thirdparty.aggregation;

import com.amazon.minerva.client.thirdparty.metric.AggregatedLong;
import com.amazon.minerva.client.thirdparty.utils.CustomMath;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SimpleSumAggregatedLongKeyValPair implements AggregatedLongKeyValPair {
    public long mSum = 0;

    @Override // com.amazon.minerva.client.thirdparty.aggregation.AggregatedLongKeyValPair
    public void addValue(long j) {
        this.mSum = CustomMath.addExact(this.mSum, j);
    }

    @Override // com.amazon.minerva.client.thirdparty.aggregation.AggregatedLongKeyValPair
    public AggregatedLong getAggregatedValue() {
        long j = this.mSum;
        if (j == 0) {
            return null;
        }
        return new AggregatedLong(Collections.singletonList(Long.valueOf(j)), Collections.singletonList(1L));
    }
}
