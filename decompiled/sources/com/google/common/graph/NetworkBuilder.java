package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.graph.ImmutableNetwork;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class NetworkBuilder<N, E> extends AbstractGraphBuilder<N> {
    public boolean allowsParallelEdges;
    public ElementOrder<? super E> edgeOrder;
    public Optional<Integer> expectedEdgeCount;

    public NetworkBuilder(boolean directed) {
        super(directed);
        this.allowsParallelEdges = false;
        this.edgeOrder = ElementOrder.insertion();
        this.expectedEdgeCount = Absent.INSTANCE;
    }

    public static NetworkBuilder<Object, Object> directed() {
        return new NetworkBuilder<>(true);
    }

    public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
        NetworkBuilder<N, E> networkBuilder = new NetworkBuilder<>(network.isDirected());
        networkBuilder.allowsParallelEdges = network.allowsParallelEdges();
        networkBuilder.allowsSelfLoops = network.allowsSelfLoops();
        ElementOrder<N> elementOrderNodeOrder = network.nodeOrder();
        elementOrderNodeOrder.getClass();
        networkBuilder.nodeOrder = elementOrderNodeOrder;
        ElementOrder<E> elementOrderEdgeOrder = network.edgeOrder();
        elementOrderEdgeOrder.getClass();
        networkBuilder.edgeOrder = elementOrderEdgeOrder;
        return networkBuilder;
    }

    public static NetworkBuilder<Object, Object> undirected() {
        return new NetworkBuilder<>(false);
    }

    @CanIgnoreReturnValue
    public NetworkBuilder<N, E> allowsParallelEdges(boolean allowsParallelEdges) {
        this.allowsParallelEdges = allowsParallelEdges;
        return this;
    }

    @CanIgnoreReturnValue
    public NetworkBuilder<N, E> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
        return new StandardMutableNetwork(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> edgeOrder) {
        edgeOrder.getClass();
        this.edgeOrder = edgeOrder;
        return this;
    }

    @CanIgnoreReturnValue
    public NetworkBuilder<N, E> expectedEdgeCount(int expectedEdgeCount) {
        Graphs.checkNonNegative(expectedEdgeCount);
        this.expectedEdgeCount = new Present(Integer.valueOf(expectedEdgeCount));
        return this;
    }

    @CanIgnoreReturnValue
    public NetworkBuilder<N, E> expectedNodeCount(int expectedNodeCount) {
        Graphs.checkNonNegative(expectedNodeCount);
        this.expectedNodeCount = new Present(Integer.valueOf(expectedNodeCount));
        return this;
    }

    public <N1 extends N, E1 extends E> ImmutableNetwork.Builder<N1, E1> immutable() {
        return new ImmutableNetwork.Builder<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> nodeOrder) {
        nodeOrder.getClass();
        this.nodeOrder = nodeOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> cast() {
        return this;
    }
}
