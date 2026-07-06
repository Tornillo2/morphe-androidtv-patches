package com.amazon.minerva.client.thirdparty.api;

import com.amazon.minerva.client.thirdparty.aggregation.AggregatedDoubleKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.AggregatedLongKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.SimpleAvgAggregatedDoubleKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.SimpleAvgAggregatedLongKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.SimpleSumAggregatedDoubleKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.SimpleSumAggregatedLongKeyValPair;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum AggregationType {
    SIMPLE_AVG,
    SIMPLE_SUM;

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.api.AggregationType$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$minerva$client$thirdparty$api$AggregationType;

        static {
            int[] iArr = new int[AggregationType.values().length];
            $SwitchMap$com$amazon$minerva$client$thirdparty$api$AggregationType = iArr;
            try {
                iArr[AggregationType.SIMPLE_AVG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$AggregationType[AggregationType.SIMPLE_SUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static AggregatedDoubleKeyValPair getAggDoubleKeyPair(AggregationType aggregationType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$minerva$client$thirdparty$api$AggregationType[aggregationType.ordinal()];
        if (i == 1) {
            return new SimpleAvgAggregatedDoubleKeyValPair();
        }
        if (i == 2) {
            return new SimpleSumAggregatedDoubleKeyValPair();
        }
        throw new IllegalArgumentException("Unsupported aggregation function found");
    }

    public static AggregatedLongKeyValPair getAggLongKeyPair(AggregationType aggregationType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$minerva$client$thirdparty$api$AggregationType[aggregationType.ordinal()];
        if (i == 1) {
            return new SimpleAvgAggregatedLongKeyValPair();
        }
        if (i == 2) {
            return new SimpleSumAggregatedLongKeyValPair();
        }
        throw new IllegalArgumentException("Unsupported aggregation function found");
    }
}
