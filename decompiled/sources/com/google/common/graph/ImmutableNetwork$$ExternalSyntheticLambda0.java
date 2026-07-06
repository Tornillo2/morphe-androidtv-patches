package com.google.common.graph;

import com.google.common.base.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ImmutableNetwork$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Network f$0;

    public /* synthetic */ ImmutableNetwork$$ExternalSyntheticLambda0(Network network) {
        this.f$0 = network;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return this.f$0.incidentNodes(obj).source();
    }
}
