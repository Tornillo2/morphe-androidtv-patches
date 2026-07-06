package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.PropertyDefinition;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Companion$intSerializer$1 implements PropertyDefinition.Serializer<Integer> {
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String toString(Integer num) {
        return String.valueOf(num.intValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public Integer fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(str);
        if (intOrNull != null) {
            return intOrNull;
        }
        throw new IllegalArgumentException("Invalid Int: ".concat(str));
    }

    public String toString(int i) {
        return String.valueOf(i);
    }
}
