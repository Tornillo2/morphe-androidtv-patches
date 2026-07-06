package com.google.common.graph;

import com.google.common.graph.GraphConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StandardMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
    public final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    public StandardMutableGraph(AbstractGraphBuilder<? super N> builder) {
        this.backingValueGraph = new StandardMutableValueGraph(builder);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean addNode(N node) {
        return this.backingValueGraph.addNode(node);
    }

    @Override // com.google.common.graph.ForwardingGraph
    public BaseGraph<N> delegate() {
        return this.backingValueGraph;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(N nodeU, N nodeV) {
        return this.backingValueGraph.putEdgeValue(nodeU, nodeV, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(N nodeU, N nodeV) {
        return this.backingValueGraph.removeEdge(nodeU, nodeV) != null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeNode(N node) {
        return this.backingValueGraph.removeNode(node);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return putEdge(endpoints.nodeU, endpoints.nodeV);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return removeEdge(endpoints.nodeU, endpoints.nodeV);
    }
}
