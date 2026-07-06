package com.google.common.graph;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.AbstractNetwork;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.InlineMe;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"N", ExifInterface.LONGITUDE_EAST})
@Beta
public final class ImmutableNetwork<N, E> extends StandardNetwork<N, E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder<N, E> {
        public final MutableNetwork<N, E> mutableNetwork;

        public Builder(NetworkBuilder<N, E> networkBuilder) {
            networkBuilder.getClass();
            this.mutableNetwork = new StandardMutableNetwork(networkBuilder);
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(N nodeU, N nodeV, E edge) {
            this.mutableNetwork.addEdge(nodeU, nodeV, edge);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addNode(N node) {
            this.mutableNetwork.addNode(node);
            return this;
        }

        public ImmutableNetwork<N, E> build() {
            return ImmutableNetwork.copyOf(this.mutableNetwork);
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(EndpointPair<N> endpoints, E edge) {
            this.mutableNetwork.addEdge(endpoints, edge);
            return this;
        }
    }

    public ImmutableNetwork(Network<N, E> network) {
        super(NetworkBuilder.from(network), getNodeConnections(network), getEdgeToReferenceNode(network));
    }

    public static <N, E> Function<E, N> adjacentNodeFn(Network<N, E> network, N node) {
        return new ImmutableNetwork$$ExternalSyntheticLambda1(network, node);
    }

    public static <N, E> NetworkConnections<N, E> connectionsOf(Network<N, E> network, N node) {
        if (!network.isDirected()) {
            Maps.AsMapView asMapView = new Maps.AsMapView(network.incidentEdges(node), new ImmutableNetwork$$ExternalSyntheticLambda1(network, node));
            return network.allowsParallelEdges() ? UndirectedMultiNetworkConnections.ofImmutable(asMapView) : UndirectedNetworkConnections.ofImmutable(asMapView);
        }
        Maps.AsMapView asMapView2 = new Maps.AsMapView(network.inEdges(node), new ImmutableNetwork$$ExternalSyntheticLambda0(network));
        Maps.AsMapView asMapView3 = new Maps.AsMapView(network.outEdges(node), new ImmutableNetwork$$ExternalSyntheticLambda2(network));
        int size = network.edgesConnecting(node, node).size();
        return network.allowsParallelEdges() ? DirectedMultiNetworkConnections.ofImmutable(asMapView2, asMapView3, size) : DirectedNetworkConnections.ofImmutable(asMapView2, asMapView3, size);
    }

    public static <N, E> ImmutableNetwork<N, E> copyOf(Network<N, E> network) {
        return network instanceof ImmutableNetwork ? (ImmutableNetwork) network : new ImmutableNetwork<>(network);
    }

    public static <N, E> Map<E, N> getEdgeToReferenceNode(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (E e : network.edges()) {
            builder.put(e, network.incidentNodes(e).nodeU);
        }
        return builder.build(true);
    }

    public static <N, E> Map<N, NetworkConnections<N, E>> getNodeConnections(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : network.nodes()) {
            builder.put(n, connectionsOf(network, n));
        }
        return builder.build(true);
    }

    public static <N, E> Function<E, N> sourceNodeFn(Network<N, E> network) {
        return new ImmutableNetwork$$ExternalSyntheticLambda0(network);
    }

    public static <N, E> Function<E, N> targetNodeFn(Network<N, E> network) {
        return new ImmutableNetwork$$ExternalSyntheticLambda2(network);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set adjacentNodes(Object node) {
        return super.adjacentNodes(node);
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public ElementOrder edgeOrder() {
        return this.edgeOrder;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edgesConnecting(Object nodeU, Object nodeV) {
        return super.edgesConnecting(nodeU, nodeV);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set inEdges(Object node) {
        return super.inEdges(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set incidentEdges(Object node) {
        return super.incidentEdges(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ EndpointPair incidentNodes(Object edge) {
        return super.incidentNodes(edge);
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public ElementOrder nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set outEdges(Object node) {
        return super.outEdges(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Set predecessors(Object node) {
        return super.predecessors(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Set successors(Object node) {
        return super.successors(node);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph<>(new AbstractNetwork.AnonymousClass1());
    }

    @InlineMe(replacement = "checkNotNull(network)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <N, E> ImmutableNetwork<N, E> copyOf(ImmutableNetwork<N, E> network) {
        network.getClass();
        return network;
    }
}
