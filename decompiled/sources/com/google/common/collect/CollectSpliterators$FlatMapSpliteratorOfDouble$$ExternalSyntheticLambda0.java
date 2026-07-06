package com.google.common.collect;

import com.google.common.collect.CollectSpliterators;
import j$.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class CollectSpliterators$FlatMapSpliteratorOfDouble$$ExternalSyntheticLambda0 implements CollectSpliterators.FlatMapSpliterator.Factory {
    @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator.Factory
    public final Spliterator newFlatMapSpliterator(Spliterator spliterator, Spliterator spliterator2, Function function, int i, long j) {
        return new CollectSpliterators.FlatMapSpliteratorOfDouble((Spliterator.OfDouble) spliterator, spliterator2, function, i, j);
    }
}
