package com.amazon.minerva.client.thirdparty.metric;

import android.util.Log;
import com.amazon.ion.IonList;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import j$.util.Objects;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonMetricEventBuilder {
    public static final IonSystem ION_SYSTEM;
    public static final long METRIC_HEADER_SIZE = 128;
    public static final String TAG = "IonMetricEventBuilder";
    public static final ValueFactory VALUE_FACTORY;
    public IonStruct mKeyValuePairs;
    public Integer mLocalOffsetMinutes;
    public IonString mMetricEventId;
    public IonSymbol mMetricGroupId;
    public String mRegion;
    public IonSymbol mSchemaId;
    public Long mUtcTimestamp;
    public boolean dataPointsValid = true;
    public long totalDataPointSize = 0;

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType;

        static {
            int[] iArr = new int[ValueType.values().length];
            $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType = iArr;
            try {
                iArr[ValueType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.TIMESTAMP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.STRING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.SYMBOL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.AGGREGATED_INTEGER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.AGGREGATED_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SizedIonValue {
        public final IonValue ionValue;
        public final long size;

        public SizedIonValue(IonValue ionValue, long j) {
            this.ionValue = ionValue;
            this.size = j;
        }

        public IonValue getIonValue() {
            return this.ionValue;
        }

        public long getSize() {
            return this.size;
        }
    }

    static {
        IonSystemBuilder ionSystemBuilder = IonSystemBuilder.STANDARD;
        ION_SYSTEM = ionSystemBuilder.build();
        VALUE_FACTORY = ionSystemBuilder.build();
    }

    public static IonValue createAggregatedDoubleIonValue(AggregatedDouble aggregatedDouble) {
        IonStruct ionStructNewEmptyStruct = VALUE_FACTORY.newEmptyStruct();
        IonList ionListNewEmptyList = ((_Private_CurriedValueFactory) ionStructNewEmptyStruct.add("values")).newEmptyList();
        IonList ionListNewEmptyList2 = ((_Private_CurriedValueFactory) ionStructNewEmptyStruct.add("counts")).newEmptyList();
        List<Double> values = aggregatedDouble.getValues();
        List<Double> counts = aggregatedDouble.getCounts();
        for (int i = 0; i < values.size(); i++) {
            ionListNewEmptyList.add().newFloat(values.get(i).doubleValue());
            ionListNewEmptyList2.add().newFloat(counts.get(i).doubleValue());
        }
        return ionStructNewEmptyStruct;
    }

    public static IonValue createAggregatedLongIonValue(AggregatedLong aggregatedLong) {
        IonStruct ionStructNewEmptyStruct = VALUE_FACTORY.newEmptyStruct();
        IonList ionListNewEmptyList = ((_Private_CurriedValueFactory) ionStructNewEmptyStruct.add("values")).newEmptyList();
        IonList ionListNewEmptyList2 = ((_Private_CurriedValueFactory) ionStructNewEmptyStruct.add("counts")).newEmptyList();
        List<Long> values = aggregatedLong.getValues();
        List<Long> counts = aggregatedLong.getCounts();
        for (int i = 0; i < values.size(); i++) {
            ionListNewEmptyList.add().newInt(values.get(i));
            ionListNewEmptyList2.add().newInt(counts.get(i));
        }
        return ionStructNewEmptyStruct;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder.SizedIonValue createIonValue(com.amazon.minerva.client.thirdparty.metric.ValueType r7, java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder.createIonValue(com.amazon.minerva.client.thirdparty.metric.ValueType, java.lang.String):com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder$SizedIonValue");
    }

    public IonMetricEvent build() {
        if (this.mRegion == null) {
            throw new IllegalStateException("withRegion should be called before build.");
        }
        IonSymbol ionSymbol = this.mMetricGroupId;
        if (ionSymbol == null) {
            throw new IllegalStateException("withMetricGroupId should be called before build.");
        }
        IonSymbol ionSymbol2 = this.mSchemaId;
        if (ionSymbol2 == null) {
            throw new IllegalStateException("withSchemaId should be called before build.");
        }
        if (this.mMetricEventId == null) {
            throw new IllegalStateException("withMetricEventId should be called before build.");
        }
        Long l = this.mUtcTimestamp;
        if (l == null) {
            throw new IllegalStateException("withUtcTimestamp should be called before build.");
        }
        if (this.mLocalOffsetMinutes == null) {
            throw new IllegalStateException("withLocalOffsetMinutes should be called before build.");
        }
        if (this.mKeyValuePairs == null) {
            throw new IllegalStateException("withDataPoints should be called before build.");
        }
        if (!this.dataPointsValid) {
            throw new IllegalStateException("There are invalid datapoints.");
        }
        IonMetricEvent ionMetricEvent = new IonMetricEvent(ionSymbol, ionSymbol2, ION_SYSTEM.newTimestamp(com.amazon.ion.Timestamp.forMillis(l.longValue(), this.mLocalOffsetMinutes)), this.mMetricEventId, this.mKeyValuePairs);
        ionMetricEvent.mRegion = this.mRegion;
        ionMetricEvent.mSizeInByte = this.totalDataPointSize + 128;
        return ionMetricEvent;
    }

    public IonMetricEventBuilder withDataPoints(List<DataPoint> list) {
        SizedIonValue sizedIonValueCreateIonValue;
        Objects.requireNonNull(list, "parameter dataPoints can not be null.");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("parameter dataPoints can not be empty.");
        }
        this.totalDataPointSize = 0L;
        this.mKeyValuePairs = ION_SYSTEM.newEmptyStruct();
        Iterator<DataPoint> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DataPoint next = it.next();
            if (next != null) {
                try {
                    sizedIonValueCreateIonValue = createIonValue(next.getType(), next.getValue());
                } catch (Exception unused) {
                    Log.e(TAG, String.format("exception happened when creating IonValue from datapoint (%s,%s,%s)", next.getName(), next.getType(), next.getValue()));
                    sizedIonValueCreateIonValue = null;
                }
                if (sizedIonValueCreateIonValue == null) {
                    this.dataPointsValid = false;
                    break;
                }
                this.mKeyValuePairs.put(next.getName(), sizedIonValueCreateIonValue.getIonValue());
                this.totalDataPointSize = sizedIonValueCreateIonValue.getSize() + ((long) next.getName().length()) + this.totalDataPointSize;
            } else {
                this.dataPointsValid = false;
                Log.e(TAG, "datapoint is null.");
                break;
            }
        }
        return this;
    }

    public IonMetricEventBuilder withLocalOffsetMinutes(int i) {
        this.mLocalOffsetMinutes = Integer.valueOf(i);
        return this;
    }

    public IonMetricEventBuilder withMetricEventId(String str) {
        Objects.requireNonNull(str, "parameter metricEventId can not be null.");
        this.mMetricEventId = ION_SYSTEM.newString(str);
        return this;
    }

    public IonMetricEventBuilder withMetricGroupId(String str) {
        Objects.requireNonNull(str, "parameter metricGroupId can not be null.");
        this.mMetricGroupId = ION_SYSTEM.newSymbol(str);
        return this;
    }

    public IonMetricEventBuilder withRegion(String str) {
        Objects.requireNonNull(str, "parameter region can not be null.");
        this.mRegion = str;
        return this;
    }

    public IonMetricEventBuilder withSchemaId(String str) {
        Objects.requireNonNull(str, "parameter schemaId can not be null.");
        this.mSchemaId = ION_SYSTEM.newSymbol(str);
        return this;
    }

    public IonMetricEventBuilder withUtcTimestamp(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("parameter utcTimestamp can not be negative number.");
        }
        this.mUtcTimestamp = Long.valueOf(j);
        return this;
    }
}
