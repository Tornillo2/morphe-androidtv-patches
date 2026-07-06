package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use GraphBuilder to create a real instance")
@Beta
public interface Graph<N> extends BaseGraph<N> {

    /* JADX INFO: renamed from: com.google.common.graph.Graph$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    Set<N> adjacentNodes(N node);

    boolean allowsSelfLoops();

    @Override // com.google.common.graph.BaseGraph
    int degree(N node);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(Object object);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(EndpointPair<N> endpoints);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N nodeU, N nodeV);

    int hashCode();

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N node);

    @Override // com.google.common.graph.BaseGraph
    ElementOrder<N> incidentEdgeOrder();

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N node);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N node);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    /* bridge */ /* synthetic */ Iterable predecessors(Object node);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N node);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    /* bridge */ /* synthetic */ Iterable successors(Object node);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    Set<N> successors(N node);
}
