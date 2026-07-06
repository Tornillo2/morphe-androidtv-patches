package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface NetworkConnections<N, E> {
    void addInEdge(E edge, N node, boolean isSelfLoop);

    void addOutEdge(E edge, N node);

    N adjacentNode(E edge);

    Set<N> adjacentNodes();

    Set<E> edgesConnecting(N node);

    Set<E> inEdges();

    Set<E> incidentEdges();

    Set<E> outEdges();

    Set<N> predecessors();

    @CanIgnoreReturnValue
    N removeInEdge(E edge, boolean isSelfLoop);

    @CanIgnoreReturnValue
    N removeOutEdge(E edge);

    Set<N> successors();
}
