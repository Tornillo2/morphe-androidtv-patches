package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.PropertyDefinition;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Companion$booleanSerializer$1 implements PropertyDefinition.Serializer<Boolean> {
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String toString(Boolean bool) {
        return String.valueOf(bool.booleanValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public Boolean fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return Boolean.valueOf(StringsKt__StringsKt.toBooleanStrict(str));
    }

    public String toString(boolean z) {
        return String.valueOf(z);
    }
}
