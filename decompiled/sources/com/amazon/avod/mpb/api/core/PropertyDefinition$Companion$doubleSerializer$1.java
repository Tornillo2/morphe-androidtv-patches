package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.PropertyDefinition;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Companion$doubleSerializer$1 implements PropertyDefinition.Serializer<Double> {
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String toString(Double d) {
        return String.valueOf(d.doubleValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public Double fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Double doubleOrNull = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(str);
        if (doubleOrNull != null) {
            return doubleOrNull;
        }
        throw new IllegalArgumentException("Invalid Double: ".concat(str));
    }

    public String toString(double d) {
        return String.valueOf(d);
    }
}
