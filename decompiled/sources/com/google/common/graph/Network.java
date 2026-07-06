package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use NetworkBuilder to create a real instance")
@Beta
public interface Network<N, E> extends SuccessorsFunction<N>, PredecessorsFunction<N> {

    /* JADX INFO: renamed from: com.google.common.graph.Network$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    Set<E> adjacentEdges(E edge);

    Set<N> adjacentNodes(N node);

    boolean allowsParallelEdges();

    boolean allowsSelfLoops();

    Graph<N> asGraph();

    int degree(N node);

    E edgeConnectingOrNull(EndpointPair<N> endpoints);

    E edgeConnectingOrNull(N nodeU, N nodeV);

    ElementOrder<E> edgeOrder();

    Set<E> edges();

    Set<E> edgesConnecting(EndpointPair<N> endpoints);

    Set<E> edgesConnecting(N nodeU, N nodeV);

    boolean equals(Object object);

    boolean hasEdgeConnecting(EndpointPair<N> endpoints);

    boolean hasEdgeConnecting(N nodeU, N nodeV);

    int hashCode();

    int inDegree(N node);

    Set<E> inEdges(N node);

    Set<E> incidentEdges(N node);

    EndpointPair<N> incidentNodes(E edge);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N node);

    Set<E> outEdges(N node);

    /* bridge */ /* synthetic */ Iterable predecessors(Object node);

    @Override // com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N node);

    @Override // com.google.common.graph.SuccessorsFunction
    /* bridge */ /* synthetic */ Iterable successors(Object node);

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N node);
}
