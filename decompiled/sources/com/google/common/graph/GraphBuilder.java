package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Present;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.ImmutableGraph;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock
@Beta
public final class GraphBuilder<N> extends AbstractGraphBuilder<N> {
    public GraphBuilder(boolean directed) {
        super(directed);
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<>(true);
    }

    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        GraphBuilder<N> graphBuilder = new GraphBuilder<>(graph.isDirected());
        graphBuilder.allowsSelfLoops = graph.allowsSelfLoops();
        ElementOrder<N> elementOrderNodeOrder = graph.nodeOrder();
        elementOrderNodeOrder.getClass();
        graphBuilder.nodeOrder = elementOrderNodeOrder;
        graphBuilder.incidentEdgeOrder(graph.incidentEdgeOrder());
        return graphBuilder;
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<>(false);
    }

    @CanIgnoreReturnValue
    public GraphBuilder<N> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new StandardMutableGraph(this);
    }

    public GraphBuilder<N> copy() {
        GraphBuilder<N> graphBuilder = new GraphBuilder<>(this.directed);
        graphBuilder.allowsSelfLoops = this.allowsSelfLoops;
        graphBuilder.nodeOrder = this.nodeOrder;
        graphBuilder.expectedNodeCount = this.expectedNodeCount;
        graphBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
        return graphBuilder;
    }

    @CanIgnoreReturnValue
    public GraphBuilder<N> expectedNodeCount(int expectedNodeCount) {
        Graphs.checkNonNegative(expectedNodeCount);
        this.expectedNodeCount = new Present(Integer.valueOf(expectedNodeCount));
        return this;
    }

    public <N1 extends N> ImmutableGraph.Builder<N1> immutable() {
        return new ImmutableGraph.Builder<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> GraphBuilder<N1> incidentEdgeOrder(ElementOrder<N1> incidentEdgeOrder) {
        ElementOrder.Type type = incidentEdgeOrder.type;
        Preconditions.checkArgument(type == ElementOrder.Type.UNORDERED || type == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", incidentEdgeOrder);
        this.incidentEdgeOrder = incidentEdgeOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> nodeOrder) {
        nodeOrder.getClass();
        this.nodeOrder = nodeOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N1 extends N> GraphBuilder<N1> cast() {
        return this;
    }
}
