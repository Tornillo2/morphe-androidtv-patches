package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public interface MutableValueGraph<N, V> extends ValueGraph<N, V> {
    @CanIgnoreReturnValue
    boolean addNode(N node);

    @CanIgnoreReturnValue
    V putEdgeValue(EndpointPair<N> endpoints, V value);

    @CanIgnoreReturnValue
    V putEdgeValue(N nodeU, N nodeV, V value);

    @CanIgnoreReturnValue
    V removeEdge(EndpointPair<N> endpoints);

    @CanIgnoreReturnValue
    V removeEdge(N nodeU, N nodeV);

    @CanIgnoreReturnValue
    boolean removeNode(N node);
}
