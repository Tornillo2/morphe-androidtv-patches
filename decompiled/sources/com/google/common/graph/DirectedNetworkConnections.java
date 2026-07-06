package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import j$.util.DesugarCollections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DirectedNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {
    public DirectedNetworkConnections(Map<E, N> inEdgeMap, Map<E, N> outEdgeMap, int selfLoopCount) {
        super(inEdgeMap, outEdgeMap, selfLoopCount);
    }

    public static <N, E> DirectedNetworkConnections<N, E> of() {
        return new DirectedNetworkConnections<>(HashBiMap.create(2), new HashBiMap(2), 0);
    }

    public static <N, E> DirectedNetworkConnections<N, E> ofImmutable(Map<E, N> inEdges, Map<E, N> outEdges, int selfLoopCount) {
        return new DirectedNetworkConnections<>(ImmutableBiMap.copyOf((Map) inEdges), ImmutableBiMap.copyOf((Map) outEdges), selfLoopCount);
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(N node) {
        return new EdgesConnecting(((BiMap) this.outEdgeMap).inverse(), node);
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return DesugarCollections.unmodifiableSet(((BiMap) this.inEdgeMap).values());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return DesugarCollections.unmodifiableSet(((BiMap) this.outEdgeMap).values());
    }
}
