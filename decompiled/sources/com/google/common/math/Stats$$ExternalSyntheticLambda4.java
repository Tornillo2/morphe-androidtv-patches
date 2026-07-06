package com.google.common.math;

import java.util.function.ObjDoubleConsumer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Stats$$ExternalSyntheticLambda4 implements ObjDoubleConsumer {
    @Override // java.util.function.ObjDoubleConsumer
    public final void accept(Object obj, double d) {
        ((StatsAccumulator) obj).add(d);
    }
}
