package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public interface MutableNetwork<N, E> extends Network<N, E> {
    @CanIgnoreReturnValue
    boolean addEdge(EndpointPair<N> endpoints, E edge);

    @CanIgnoreReturnValue
    boolean addEdge(N nodeU, N nodeV, E edge);

    @CanIgnoreReturnValue
    boolean addNode(N node);

    @CanIgnoreReturnValue
    boolean removeEdge(E edge);

    @CanIgnoreReturnValue
    boolean removeNode(N node);
}
