package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import j$.util.DesugarCollections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
    public UndirectedNetworkConnections(Map<E, N> incidentEdgeMap) {
        super(incidentEdgeMap);
    }

    public static <N, E> UndirectedNetworkConnections<N, E> of() {
        return new UndirectedNetworkConnections<>(HashBiMap.create(2));
    }

    public static <N, E> UndirectedNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
        return new UndirectedNetworkConnections<>(ImmutableBiMap.copyOf((Map) incidentEdges));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return DesugarCollections.unmodifiableSet(((BiMap) this.incidentEdgeMap).values());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(N node) {
        return new EdgesConnecting(((BiMap) this.incidentEdgeMap).inverse(), node);
    }
}
