package com.google.common.graph;

import com.google.common.base.Preconditions;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    public final Map<E, N> incidentEdgeMap;

    public AbstractUndirectedNetworkConnections(Map<E, N> incidentEdgeMap) {
        incidentEdgeMap.getClass();
        this.incidentEdgeMap = incidentEdgeMap;
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        if (isSelfLoop) {
            return;
        }
        addOutEdge(edge, node);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E edge, N node) {
        Preconditions.checkState(this.incidentEdgeMap.put(edge, node) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E edge) {
        N n = this.incidentEdgeMap.get(edge);
        Objects.requireNonNull(n);
        return n;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return DesugarCollections.unmodifiableSet(this.incidentEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (isSelfLoop) {
            return null;
        }
        return removeOutEdge(edge);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E edge) {
        N nRemove = this.incidentEdgeMap.remove(edge);
        Objects.requireNonNull(nRemove);
        return nRemove;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return adjacentNodes();
    }
}
