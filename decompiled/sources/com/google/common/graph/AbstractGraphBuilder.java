package com.google.common.graph;

import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractGraphBuilder<N> {
    public final boolean directed;
    public boolean allowsSelfLoops = false;
    public ElementOrder<N> nodeOrder = ElementOrder.insertion();
    public ElementOrder<N> incidentEdgeOrder = ElementOrder.unordered();
    public Optional<Integer> expectedNodeCount = Absent.INSTANCE;

    public AbstractGraphBuilder(boolean directed) {
        this.directed = directed;
    }
}
