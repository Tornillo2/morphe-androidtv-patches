package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
    public final ElementOrder<N> incidentEdgeOrder;

    public StandardMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
        ElementOrder<? super N> elementOrder = abstractGraphBuilder.incidentEdgeOrder;
        elementOrder.getClass();
        this.incidentEdgeOrder = elementOrder;
    }

    @Override // com.google.common.graph.MutableValueGraph
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
    public final GraphConnections<N, V> addNodeInternal(N node) {
        GraphConnections<N, V> graphConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, graphConnectionsNewConnections) == null);
        return graphConnectionsNewConnections;
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return this.incidentEdgeOrder;
    }

    public final GraphConnections<N, V> newConnections() {
        return this.isDirected ? DirectedGraphConnections.of(this.incidentEdgeOrder) : UndirectedGraphConnections.of(this.incidentEdgeOrder);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(N nodeU, N nodeV, V value) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        Preconditions.checkNotNull(value, "value");
        if (!this.allowsSelfLoops) {
            Preconditions.checkArgument(!nodeU.equals(nodeV), GraphConstants.SELF_LOOPS_NOT_ALLOWED, nodeU);
        }
        GraphConnections<N, V> graphConnectionsAddNodeInternal = this.nodeConnections.get(nodeU);
        if (graphConnectionsAddNodeInternal == null) {
            graphConnectionsAddNodeInternal = addNodeInternal(nodeU);
        }
        V vAddSuccessor = graphConnectionsAddNodeInternal.addSuccessor(nodeV, value);
        GraphConnections<N, V> graphConnectionsAddNodeInternal2 = this.nodeConnections.get(nodeV);
        if (graphConnectionsAddNodeInternal2 == null) {
            graphConnectionsAddNodeInternal2 = addNodeInternal(nodeV);
        }
        graphConnectionsAddNodeInternal2.addPredecessor(nodeU, value);
        if (vAddSuccessor == null) {
            long j = this.edgeCount + 1;
            this.edgeCount = j;
            Graphs.checkPositive(j);
        }
        return vAddSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(N nodeU, N nodeV) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(nodeU);
        GraphConnections<N, V> graphConnections2 = this.nodeConnections.get(nodeV);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V vRemoveSuccessor = graphConnections.removeSuccessor(nodeV);
        if (vRemoveSuccessor != null) {
            graphConnections2.removePredecessor(nodeU);
            long j = this.edgeCount - 1;
            this.edgeCount = j;
            Graphs.checkNonNegative(j);
        }
        return vRemoveSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean removeNode(N n) {
        Preconditions.checkNotNull(n, "node");
        GraphConnections graphConnections = (GraphConnections<N, V>) this.nodeConnections.get(n);
        if (graphConnections == null) {
            return false;
        }
        if (this.allowsSelfLoops && graphConnections.removeSuccessor(n) != null) {
            graphConnections.removePredecessor(n);
            this.edgeCount--;
        }
        UnmodifiableIterator it = ImmutableList.copyOf((Collection) graphConnections.successors()).iterator();
        while (it.hasNext()) {
            E next = it.next();
            GraphConnections<N, V> withoutCaching = this.nodeConnections.getWithoutCaching(next);
            Objects.requireNonNull(withoutCaching);
            withoutCaching.removePredecessor(n);
            Objects.requireNonNull(graphConnections.removeSuccessor(next));
            this.edgeCount--;
        }
        if (this.isDirected) {
            UnmodifiableIterator it2 = ImmutableList.copyOf((Collection) graphConnections.predecessors()).iterator();
            while (it2.hasNext()) {
                E next2 = it2.next();
                GraphConnections<N, V> withoutCaching2 = this.nodeConnections.getWithoutCaching(next2);
                Objects.requireNonNull(withoutCaching2);
                Preconditions.checkState(withoutCaching2.removeSuccessor(n) != null);
                graphConnections.removePredecessor(next2);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(n);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return removeEdge(endpoints.nodeU, endpoints.nodeV);
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(EndpointPair<N> endpoints, V value) {
        validateEndpoints(endpoints);
        return putEdgeValue(endpoints.nodeU, endpoints.nodeV, value);
    }
}
