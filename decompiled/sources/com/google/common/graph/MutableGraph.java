package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public interface MutableGraph<N> extends Graph<N> {
    @CanIgnoreReturnValue
    boolean addNode(N node);

    @CanIgnoreReturnValue
    boolean putEdge(EndpointPair<N> endpoints);

    @CanIgnoreReturnValue
    boolean putEdge(N nodeU, N nodeV);

    @CanIgnoreReturnValue
    boolean removeEdge(EndpointPair<N> endpoints);

    @CanIgnoreReturnValue
    boolean removeEdge(N nodeU, N nodeV);

    @CanIgnoreReturnValue
    boolean removeNode(N node);
}
