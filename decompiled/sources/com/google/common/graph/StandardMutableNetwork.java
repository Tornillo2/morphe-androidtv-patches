package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StandardMutableNetwork<N, E> extends StandardNetwork<N, E> implements MutableNetwork<N, E> {
    public StandardMutableNetwork(NetworkBuilder<? super N, ? super E> builder) {
        super(builder);
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(N nodeU, N nodeV, E edge) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        Preconditions.checkNotNull(edge, "edge");
        if (this.edgeToReferenceNode.containsKey(edge)) {
            EndpointPair<N> endpointPairIncidentNodes = incidentNodes(edge);
            EndpointPair endpointPairOf = EndpointPair.of(this, nodeU, nodeV);
            Preconditions.checkArgument(endpointPairIncidentNodes.equals(endpointPairOf), GraphConstants.REUSING_EDGE, edge, endpointPairIncidentNodes, endpointPairOf);
            return false;
        }
        NetworkConnections<N, E> networkConnectionsAddNodeInternal = this.nodeConnections.get(nodeU);
        if (!this.allowsParallelEdges) {
            Preconditions.checkArgument(networkConnectionsAddNodeInternal == null || !networkConnectionsAddNodeInternal.successors().contains(nodeV), GraphConstants.PARALLEL_EDGES_NOT_ALLOWED, nodeU, nodeV);
        }
        boolean zEquals = nodeU.equals(nodeV);
        if (!this.allowsSelfLoops) {
            Preconditions.checkArgument(!zEquals, GraphConstants.SELF_LOOPS_NOT_ALLOWED, nodeU);
        }
        if (networkConnectionsAddNodeInternal == null) {
            networkConnectionsAddNodeInternal = addNodeInternal(nodeU);
        }
        networkConnectionsAddNodeInternal.addOutEdge(edge, nodeV);
        NetworkConnections<N, E> networkConnectionsAddNodeInternal2 = this.nodeConnections.get(nodeV);
        if (networkConnectionsAddNodeInternal2 == null) {
            networkConnectionsAddNodeInternal2 = addNodeInternal(nodeV);
        }
        networkConnectionsAddNodeInternal2.addInEdge(edge, nodeU, zEquals);
        this.edgeToReferenceNode.put(edge, nodeU);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addNode(N node) {
        Preconditions.checkNotNull(node, "node");
        if (this.nodeConnections.containsKey(node)) {
            return false;
        }
        addNodeInternal(node);
        return true;
    }

    @CanIgnoreReturnValue
    public final NetworkConnections<N, E> addNodeInternal(N node) {
        NetworkConnections<N, E> networkConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, networkConnectionsNewConnections) == null);
        return networkConnectionsNewConnections;
    }

    public final NetworkConnections<N, E> newConnections() {
        return this.isDirected ? this.allowsParallelEdges ? DirectedMultiNetworkConnections.of() : DirectedNetworkConnections.of() : this.allowsParallelEdges ? UndirectedMultiNetworkConnections.of() : UndirectedNetworkConnections.of();
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeEdge(E edge) {
        Preconditions.checkNotNull(edge, "edge");
        N n = this.edgeToReferenceNode.get(edge);
        boolean z = false;
        if (n == null) {
            return false;
        }
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n);
        Objects.requireNonNull(networkConnections);
        N nAdjacentNode = networkConnections.adjacentNode(edge);
        NetworkConnections<N, E> networkConnections2 = this.nodeConnections.get(nAdjacentNode);
        Objects.requireNonNull(networkConnections2);
        networkConnections.removeOutEdge(edge);
        if (this.allowsSelfLoops && n.equals(nAdjacentNode)) {
            z = true;
        }
        networkConnections2.removeInEdge(edge, z);
        this.edgeToReferenceNode.remove(edge);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeNode(N node) {
        Preconditions.checkNotNull(node, "node");
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(node);
        if (networkConnections == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf((Collection) networkConnections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.nodeConnections.remove(node);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpoints, E edge) {
        validateEndpoints(endpoints);
        return addEdge(endpoints.nodeU, endpoints.nodeV, edge);
    }
}
