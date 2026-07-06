package com.google.common.graph;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface BaseGraph<N> extends SuccessorsFunction<N>, PredecessorsFunction<N> {

    /* JADX INFO: renamed from: com.google.common.graph.BaseGraph$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    Set<N> adjacentNodes(N node);

    boolean allowsSelfLoops();

    int degree(N node);

    Set<EndpointPair<N>> edges();

    boolean hasEdgeConnecting(EndpointPair<N> endpoints);

    boolean hasEdgeConnecting(N nodeU, N nodeV);

    int inDegree(N node);

    ElementOrder<N> incidentEdgeOrder();

    Set<EndpointPair<N>> incidentEdges(N node);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N node);

    /* bridge */ /* synthetic */ Iterable predecessors(Object node);

    @Override // com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N node);

    @Override // com.google.common.graph.SuccessorsFunction
    /* bridge */ /* synthetic */ Iterable successors(Object node);

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N node);
}
