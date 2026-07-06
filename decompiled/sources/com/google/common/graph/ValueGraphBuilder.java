package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Present;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.ImmutableValueGraph;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class ValueGraphBuilder<N, V> extends AbstractGraphBuilder<N> {
    public ValueGraphBuilder(boolean directed) {
        super(directed);
    }

    public static ValueGraphBuilder<Object, Object> directed() {
        return new ValueGraphBuilder<>(true);
    }

    public static <N, V> ValueGraphBuilder<N, V> from(ValueGraph<N, V> graph) {
        ValueGraphBuilder<N, V> valueGraphBuilder = new ValueGraphBuilder<>(graph.isDirected());
        valueGraphBuilder.allowsSelfLoops = graph.allowsSelfLoops();
        ElementOrder<N> elementOrderNodeOrder = graph.nodeOrder();
        elementOrderNodeOrder.getClass();
        valueGraphBuilder.nodeOrder = elementOrderNodeOrder;
        valueGraphBuilder.incidentEdgeOrder(graph.incidentEdgeOrder());
        return valueGraphBuilder;
    }

    public static ValueGraphBuilder<Object, Object> undirected() {
        return new ValueGraphBuilder<>(false);
    }

    @CanIgnoreReturnValue
    public ValueGraphBuilder<N, V> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public <N1 extends N, V1 extends V> MutableValueGraph<N1, V1> build() {
        return new StandardMutableValueGraph(this);
    }

    public ValueGraphBuilder<N, V> copy() {
        ValueGraphBuilder<N, V> valueGraphBuilder = new ValueGraphBuilder<>(this.directed);
        valueGraphBuilder.allowsSelfLoops = this.allowsSelfLoops;
        valueGraphBuilder.nodeOrder = this.nodeOrder;
        valueGraphBuilder.expectedNodeCount = this.expectedNodeCount;
        valueGraphBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
        return valueGraphBuilder;
    }

    @CanIgnoreReturnValue
    public ValueGraphBuilder<N, V> expectedNodeCount(int expectedNodeCount) {
        Graphs.checkNonNegative(expectedNodeCount);
        this.expectedNodeCount = new Present(Integer.valueOf(expectedNodeCount));
        return this;
    }

    public <N1 extends N, V1 extends V> ImmutableValueGraph.Builder<N1, V1> immutable() {
        return new ImmutableValueGraph.Builder<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> ValueGraphBuilder<N1, V> incidentEdgeOrder(ElementOrder<N1> incidentEdgeOrder) {
        ElementOrder.Type type = incidentEdgeOrder.type;
        Preconditions.checkArgument(type == ElementOrder.Type.UNORDERED || type == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", incidentEdgeOrder);
        this.incidentEdgeOrder = incidentEdgeOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> ValueGraphBuilder<N1, V> nodeOrder(ElementOrder<N1> nodeOrder) {
        nodeOrder.getClass();
        this.nodeOrder = nodeOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N1 extends N, V1 extends V> ValueGraphBuilder<N1, V1> cast() {
        return this;
    }
}
