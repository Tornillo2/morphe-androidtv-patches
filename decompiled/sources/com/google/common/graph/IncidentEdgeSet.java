package com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class IncidentEdgeSet<N> extends AbstractSet<EndpointPair<N>> {
    public final BaseGraph<N> graph;
    public final N node;

    public IncidentEdgeSet(BaseGraph<N> graph, N node) {
        this.graph = graph;
        this.node = node;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (!(obj instanceof EndpointPair)) {
            return false;
        }
        EndpointPair endpointPair = (EndpointPair) obj;
        if (this.graph.isDirected()) {
            if (!endpointPair.isOrdered()) {
                return false;
            }
            Object objSource = endpointPair.source();
            Object objTarget = endpointPair.target();
            if (this.node.equals(objSource) && this.graph.successors((Object) this.node).contains(objTarget)) {
                return true;
            }
            return this.node.equals(objTarget) && this.graph.predecessors((Object) this.node).contains(objSource);
        }
        if (endpointPair.isOrdered()) {
            return false;
        }
        Set<N> setAdjacentNodes = this.graph.adjacentNodes(this.node);
        N n = endpointPair.nodeU;
        N n2 = endpointPair.nodeV;
        if (this.node.equals(n2) && setAdjacentNodes.contains(n)) {
            return true;
        }
        return this.node.equals(n) && setAdjacentNodes.contains(n2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        if (!this.graph.isDirected()) {
            return this.graph.adjacentNodes(this.node).size();
        }
        return (this.graph.outDegree(this.node) + this.graph.inDegree(this.node)) - (this.graph.successors((Object) this.node).contains(this.node) ? 1 : 0);
    }
}
