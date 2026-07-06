package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.PropertyDefinition;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Companion$stringSerializer$1 implements PropertyDefinition.Serializer<String> {
    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String fromString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return str;
    }

    /* JADX INFO: renamed from: toString, reason: avoid collision after fix types in other method */
    public String toString2(String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return v;
    }

    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    /* JADX INFO: renamed from: fromString, reason: avoid collision after fix types in other method */
    public String fromString2(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return str;
    }

    @Override // com.amazon.avod.mpb.api.core.PropertyDefinition.Serializer
    public String toString(String str) {
        String v = str;
        Intrinsics.checkNotNullParameter(v, "v");
        return v;
    }
}
