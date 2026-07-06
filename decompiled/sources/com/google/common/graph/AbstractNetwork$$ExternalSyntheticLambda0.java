package com.google.common.graph;

import com.google.common.base.Predicate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class AbstractNetwork$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ AbstractNetwork f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ AbstractNetwork$$ExternalSyntheticLambda0(AbstractNetwork abstractNetwork, Object obj, Object obj2) {
        this.f$0 = abstractNetwork;
        this.f$1 = obj;
        this.f$2 = obj2;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        AbstractNetwork abstractNetwork = this.f$0;
        return abstractNetwork.incidentNodes(obj).adjacentNode(this.f$1).equals(this.f$2);
    }
}
