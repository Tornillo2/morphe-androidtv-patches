package com.amazon.ignitionshared.recommendation.metric;

import com.amazon.minerva.client.thirdparty.transport.UploadResult;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetricResult {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ MetricResult[] $VALUES;
    public static final MetricResult FAIL = new MetricResult("FAIL", 0, 0);
    public static final MetricResult SUCCESS = new MetricResult(UploadResult.SUCCESS, 1, 1);
    public int value;

    public static final /* synthetic */ MetricResult[] $values() {
        return new MetricResult[]{FAIL, SUCCESS};
    }

    static {
        MetricResult[] metricResultArr$values = $values();
        $VALUES = metricResultArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(metricResultArr$values);
    }

    public MetricResult(String str, int i, int i2) {
        this.value = i2;
    }

    @NotNull
    public static EnumEntries<MetricResult> getEntries() {
        return $ENTRIES;
    }

    public static MetricResult valueOf(String str) {
        return (MetricResult) Enum.valueOf(MetricResult.class, str);
    }

    public static MetricResult[] values() {
        return (MetricResult[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
    }
}
