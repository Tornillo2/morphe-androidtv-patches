package com.google.common.collect;

import com.google.common.collect.ImmutableTable;
import j$.util.function.BiFunction$CC;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class TableCollectors$$ExternalSyntheticLambda9 implements BinaryOperator {
    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        ImmutableTable.Builder builder = (ImmutableTable.Builder) obj;
        builder.combine((ImmutableTable.Builder) obj2);
        return builder;
    }
}
