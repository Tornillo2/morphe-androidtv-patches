package com.google.common.math;

import java.util.function.ObjIntConsumer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Stats$$ExternalSyntheticLambda3 implements ObjIntConsumer {
    @Override // java.util.function.ObjIntConsumer
    public final void accept(Object obj, int i) {
        ((StatsAccumulator) obj).add(i);
    }
}
