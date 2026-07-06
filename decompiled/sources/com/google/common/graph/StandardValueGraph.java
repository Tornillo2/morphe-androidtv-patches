package com.google.common.graph;

import com.google.common.graph.MapIteratorCache;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StandardValueGraph<N, V> extends AbstractValueGraph<N, V> {
    public final boolean allowsSelfLoops;
    public long edgeCount;
    public final boolean isDirected;
    public final MapIteratorCache<N, GraphConnections<N, V>> nodeConnections;
    public final ElementOrder<N> nodeOrder;

    public StandardValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder, Map<N, GraphConnections<N, V>> map, long j) {
        this.isDirected = abstractGraphBuilder.directed;
        this.allowsSelfLoops = abstractGraphBuilder.allowsSelfLoops;
        ElementOrder<? super N> elementOrder = abstractGraphBuilder.nodeOrder;
        elementOrder.getClass();
        this.nodeOrder = elementOrder;
        this.nodeConnections = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        Graphs.checkNonNegative(j);
        this.edgeCount = j;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).adjacentNodes(), n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public final GraphConnections<N, V> checkedConnections(N node) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(node);
        if (graphConnections != null) {
            return graphConnections;
        }
        throw new IllegalArgumentException("Node " + node + " is not an element of this graph.");
    }

    public final boolean containsNode(N node) {
        return this.nodeConnections.containsKey(node);
    }

    @Override // com.google.common.graph.AbstractBaseGraph
    public long edgeCount() {
        return this.edgeCount;
    }

    public V edgeValueOrDefault(EndpointPair<N> endpoints, V defaultValue) {
        validateEndpoints(endpoints);
        return edgeValueOrDefaultInternal(endpoints.nodeU, endpoints.nodeV, defaultValue);
    }

    public final V edgeValueOrDefaultInternal(N nodeU, N nodeV, V defaultValue) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(nodeU);
        V vValue = graphConnections == null ? null : graphConnections.value(nodeV);
        return vValue == null ? defaultValue : vValue;
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        endpoints.getClass();
        return isOrderingCompatible(endpoints) && hasEdgeConnectingInternal(endpoints.nodeU, endpoints.nodeV);
    }

    public final boolean hasEdgeConnectingInternal(N nodeU, N nodeV) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(nodeU);
        return graphConnections != null && graphConnections.successors().contains(nodeV);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        final GraphConnections<N, V> graphConnectionsCheckedConnections = checkedConnections(n);
        return (Set<EndpointPair<N>>) nodeInvalidatableSet(new IncidentEdgeSet<N>(this, this, n) { // from class: com.google.common.graph.StandardValueGraph.1
            public final /* synthetic */ StandardValueGraph this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return graphConnectionsCheckedConnections.incidentEdgeIterator(this.node);
            }
        }, n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        MapIteratorCache<N, GraphConnections<N, V>> mapIteratorCache = this.nodeConnections;
        mapIteratorCache.getClass();
        return new MapIteratorCache.AnonymousClass1();
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).predecessors(), n);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).successors(), n);
    }

    public V edgeValueOrDefault(N nodeU, N nodeV, V defaultValue) {
        nodeU.getClass();
        nodeV.getClass();
        return edgeValueOrDefaultInternal(nodeU, nodeV, defaultValue);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        nodeU.getClass();
        nodeV.getClass();
        return hasEdgeConnectingInternal(nodeU, nodeV);
    }

    public StandardValueGraph(AbstractGraphBuilder<? super N> builder) {
        this(builder, builder.nodeOrder.createMap(builder.expectedNodeCount.or(10).intValue()), 0L);
    }
}
