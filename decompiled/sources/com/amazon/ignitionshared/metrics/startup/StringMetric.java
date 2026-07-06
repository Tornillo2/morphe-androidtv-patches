package com.amazon.ignitionshared.metrics.startup;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class StringMetric extends Metric {

    @NotNull
    public final String value;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StringMetric(@NotNull String name, @NotNull String source, @NotNull String value) {
        this(name, source, value, false, 8, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override // com.amazon.ignitionshared.metrics.startup.MetricsRecorder.Recordable
    public void subscribeToMetricEvent(@NotNull MetricEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        event.addString(this.name, this.value);
    }

    public /* synthetic */ StringMetric(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StringMetric(@NotNull String name, @NotNull String source, @NotNull String value, boolean z) {
        super(name, source, z);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }
}
