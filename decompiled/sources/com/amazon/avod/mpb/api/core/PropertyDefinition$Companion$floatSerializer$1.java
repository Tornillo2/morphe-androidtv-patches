package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.PropertyDefinition;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Companion$floatSerializer$1 implements PropertyDefinition.Serializer<Float> {
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String toString(Float f) {
        return String.valueOf(f.floatValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public Float fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Float floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(str);
        if (floatOrNull != null) {
            return floatOrNull;
        }
        throw new IllegalArgumentException("Invalid Float: ".concat(str));
    }

    public String toString(float f) {
        return String.valueOf(f);
    }
}
