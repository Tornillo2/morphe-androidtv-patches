package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.MapIteratorCache;
import j$.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StandardNetwork<N, E> extends AbstractNetwork<N, E> {
    public final boolean allowsParallelEdges;
    public final boolean allowsSelfLoops;
    public final ElementOrder<E> edgeOrder;
    public final MapIteratorCache<E, N> edgeToReferenceNode;
    public final boolean isDirected;
    public final MapIteratorCache<N, NetworkConnections<N, E>> nodeConnections;
    public final ElementOrder<N> nodeOrder;

    public StandardNetwork(NetworkBuilder<? super N, ? super E> networkBuilder, Map<N, NetworkConnections<N, E>> map, Map<E, N> map2) {
        this.isDirected = networkBuilder.directed;
        this.allowsParallelEdges = networkBuilder.allowsParallelEdges;
        this.allowsSelfLoops = networkBuilder.allowsSelfLoops;
        ElementOrder<? super N> elementOrder = networkBuilder.nodeOrder;
        elementOrder.getClass();
        this.nodeOrder = elementOrder;
        ElementOrder<? super Object> elementOrder2 = networkBuilder.edgeOrder;
        elementOrder2.getClass();
        this.edgeOrder = elementOrder2;
        this.nodeConnections = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        this.edgeToReferenceNode = new MapIteratorCache<>(map2);
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).adjacentNodes(), n);
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public final NetworkConnections<N, E> checkedConnections(N node) {
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(node);
        if (networkConnections != null) {
            return networkConnections;
        }
        throw new IllegalArgumentException(String.format(GraphConstants.NODE_NOT_IN_GRAPH, node));
    }

    public final N checkedReferenceNode(E edge) {
        N n = this.edgeToReferenceNode.get(edge);
        if (n != null) {
            return n;
        }
        throw new IllegalArgumentException(String.format(GraphConstants.EDGE_NOT_IN_GRAPH, edge));
    }

    public final boolean containsEdge(E edge) {
        return this.edgeToReferenceNode.containsKey(edge);
    }

    public final boolean containsNode(N node) {
        return this.nodeConnections.containsKey(node);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        MapIteratorCache<E, N> mapIteratorCache = this.edgeToReferenceNode;
        mapIteratorCache.getClass();
        return new MapIteratorCache.AnonymousClass1();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        NetworkConnections<N, E> networkConnectionsCheckedConnections = checkedConnections(n);
        if (!this.allowsSelfLoops && n == n2) {
            return ImmutableSet.of();
        }
        Preconditions.checkArgument(this.nodeConnections.containsKey(n2), GraphConstants.NODE_NOT_IN_GRAPH, n2);
        return (Set<E>) nodePairInvalidatableSet(networkConnectionsCheckedConnections.edgesConnecting(n2), n, n2);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n) {
        return (Set<E>) nodeInvalidatableSet(checkedConnections(n).inEdges(), n);
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n) {
        return (Set<E>) nodeInvalidatableSet(checkedConnections(n).incidentEdges(), n);
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E edge) {
        N nCheckedReferenceNode = checkedReferenceNode(edge);
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(nCheckedReferenceNode);
        Objects.requireNonNull(networkConnections);
        return EndpointPair.of(this, nCheckedReferenceNode, networkConnections.adjacentNode(edge));
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        MapIteratorCache<N, NetworkConnections<N, E>> mapIteratorCache = this.nodeConnections;
        mapIteratorCache.getClass();
        return new MapIteratorCache.AnonymousClass1();
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n) {
        return (Set<E>) nodeInvalidatableSet(checkedConnections(n).outEdges(), n);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).predecessors(), n);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return (Set<N>) nodeInvalidatableSet(checkedConnections(n).successors(), n);
    }

    public StandardNetwork(NetworkBuilder<? super N, ? super E> builder) {
        this(builder, builder.nodeOrder.createMap(builder.expectedNodeCount.or(10).intValue()), builder.edgeOrder.createMap(builder.expectedEdgeCount.or(20).intValue()));
    }
}
