package com.google.common.graph;

import com.google.common.base.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ImmutableNetwork$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ Network f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ ImmutableNetwork$$ExternalSyntheticLambda1(Network network, Object obj) {
        this.f$0 = network;
        this.f$1 = obj;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return this.f$0.incidentNodes(obj).adjacentNode(this.f$1);
    }
}
