package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.GraphConstants;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.InlineMe;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"N"})
@Beta
public class ImmutableGraph<N> extends ForwardingGraph<N> {
    public final BaseGraph<N> backingGraph;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder<N> {
        public final MutableGraph<N> mutableGraph;

        public Builder(GraphBuilder<N> graphBuilder) {
            GraphBuilder<N> graphBuilderCopy = graphBuilder.copy();
            graphBuilderCopy.incidentEdgeOrder(ElementOrder.stable());
            this.mutableGraph = new StandardMutableGraph(graphBuilderCopy);
        }

        @CanIgnoreReturnValue
        public Builder<N> addNode(N node) {
            this.mutableGraph.addNode(node);
            return this;
        }

        public ImmutableGraph<N> build() {
            return ImmutableGraph.copyOf(this.mutableGraph);
        }

        @CanIgnoreReturnValue
        public Builder<N> putEdge(N nodeU, N nodeV) {
            this.mutableGraph.putEdge(nodeU, nodeV);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N> putEdge(EndpointPair<N> endpoints) {
            this.mutableGraph.putEdge(endpoints);
            return this;
        }
    }

    public ImmutableGraph(BaseGraph<N> backingGraph) {
        this.backingGraph = backingGraph;
    }

    public static <N> GraphConnections<N, GraphConstants.Presence> connectionsOf(Graph<N> graph, N node) {
        Functions.ConstantFunction constantFunction = new Functions.ConstantFunction(GraphConstants.Presence.EDGE_EXISTS);
        return graph.isDirected() ? DirectedGraphConnections.ofImmutable(node, graph.incidentEdges(node), constantFunction) : UndirectedGraphConnections.ofImmutable(new Maps.AsMapView(graph.adjacentNodes(node), constantFunction));
    }

    public static <N> ImmutableGraph<N> copyOf(Graph<N> graph) {
        return graph instanceof ImmutableGraph ? (ImmutableGraph) graph : new ImmutableGraph<>(new StandardValueGraph(GraphBuilder.from(graph), getNodeConnections(graph), graph.edges().size()));
    }

    public static <N> ImmutableMap<N, GraphConnections<N, GraphConstants.Presence>> getNodeConnections(Graph<N> graph) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : graph.nodes()) {
            builder.put(n, connectionsOf(graph, n));
        }
        return builder.build(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set adjacentNodes(Object node) {
        return super.adjacentNodes(node);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean allowsSelfLoops() {
        return super.allowsSelfLoops();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int degree(Object node) {
        return super.degree(node);
    }

    @Override // com.google.common.graph.ForwardingGraph
    public BaseGraph<N> delegate() {
        return this.backingGraph;
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpoints) {
        return super.hasEdgeConnecting(endpoints);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int inDegree(Object node) {
        return super.inDegree(node);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.stable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ Set incidentEdges(Object node) {
        return super.incidentEdges(node);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean isDirected() {
        return super.isDirected();
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ ElementOrder nodeOrder() {
        return super.nodeOrder();
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int outDegree(Object node) {
        return super.outDegree(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Set predecessors(Object node) {
        return super.predecessors(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Set successors(Object node) {
        return super.successors(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
        return super.hasEdgeConnecting(nodeU, nodeV);
    }

    @InlineMe(replacement = "checkNotNull(graph)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <N> ImmutableGraph<N> copyOf(ImmutableGraph<N> graph) {
        graph.getClass();
        return graph;
    }
}
