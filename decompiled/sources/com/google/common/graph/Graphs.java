package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.AbstractBaseGraph;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MapIteratorCache;
import com.google.common.graph.Traverser;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class Graphs extends GraphsBridgeMethods {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NodeAndRemainingSuccessors<N> {
        public final N node;
        public Queue<N> remainingSuccessors;

        public NodeAndRemainingSuccessors(N node) {
            this.node = node;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransposedGraph<N> extends ForwardingGraph<N> {
        public final Graph<N> graph;

        /* JADX INFO: renamed from: com.google.common.graph.Graphs$TransposedGraph$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends IncidentEdgeSet<N> {
            public AnonymousClass1(BaseGraph graph, Object node) {
                super(graph, node);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return new Iterators.AnonymousClass6(TransposedGraph.this.delegate().incidentEdges(this.node).iterator(), new Function() { // from class: com.google.common.graph.Graphs$TransposedGraph$1$$ExternalSyntheticLambda0
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        EndpointPair endpointPair = (EndpointPair) obj;
                        return EndpointPair.of((Graph<?>) Graphs.TransposedGraph.this.delegate(), (Object) endpointPair.nodeV, (Object) endpointPair.nodeU);
                    }
                });
            }
        }

        public TransposedGraph(Graph<N> graph) {
            this.graph = graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> incidentEdges(N node) {
            return new AnonymousClass1(this, node);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.ForwardingGraph
        public Graph<N> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        public final Network<N, E> network;

        public TransposedNetwork(Network<N, E> network) {
            this.network = network;
        }

        @Override // com.google.common.graph.ForwardingNetwork
        public Network<N, E> delegate() {
            return this.network;
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N nodeU, N nodeV) {
            return delegate().edgeConnectingOrNull(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N nodeU, N nodeV) {
            return delegate().edgesConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N node) {
            return delegate().outEdges(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E edge) {
            EndpointPair<N> endpointPairIncidentNodes = delegate().incidentNodes(edge);
            return EndpointPair.of((Network<?, ?>) this.network, (Object) endpointPairIncidentNodes.nodeV, (Object) endpointPairIncidentNodes.nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N node) {
            return delegate().inEdges(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(EndpointPair<N> endpoints) {
            return delegate().edgeConnectingOrNull(Graphs.transpose(endpoints));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(EndpointPair<N> endpoints) {
            return delegate().edgesConnecting(Graphs.transpose(endpoints));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        public final ValueGraph<N, V> graph;

        public TransposedValueGraph(ValueGraph<N, V> graph) {
            this.graph = graph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        public V edgeValueOrDefault(N nodeU, N nodeV, V defaultValue) {
            return delegate().edgeValueOrDefault(nodeV, nodeU, defaultValue);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        public V edgeValueOrDefault(EndpointPair<N> endpoints, V defaultValue) {
            return delegate().edgeValueOrDefault(Graphs.transpose(endpoints), defaultValue);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }
    }

    public static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object nextNode, Object previousNode) {
        return graph.isDirected() || !Objects.equal(previousNode, nextNode);
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(int value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    public static int checkPositive(int value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        GraphBuilder graphBuilderFrom = GraphBuilder.from(graph);
        graphBuilderFrom.expectedNodeCount(graph.nodes().size());
        StandardMutableGraph standardMutableGraph = new StandardMutableGraph(graphBuilderFrom);
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            standardMutableGraph.addNode(it.next());
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            standardMutableGraph.putEdge(endpointPair.nodeU, endpointPair.nodeV);
        }
        return standardMutableGraph;
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            if (subgraphHasCycle(graph, mapNewHashMapWithExpectedSize, it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        StandardMutableGraph standardMutableGraph;
        if (iterable instanceof Collection) {
            GraphBuilder graphBuilderFrom = GraphBuilder.from(graph);
            graphBuilderFrom.expectedNodeCount(((Collection) iterable).size());
            standardMutableGraph = new StandardMutableGraph(graphBuilderFrom);
        } else {
            standardMutableGraph = new StandardMutableGraph(GraphBuilder.from(graph));
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            standardMutableGraph.addNode(it.next());
        }
        for (N n : standardMutableGraph.nodes()) {
            for (N n2 : graph.successors((Object) n)) {
                if (standardMutableGraph.nodes().contains(n2)) {
                    standardMutableGraph.putEdge(n, n2);
                }
            }
        }
        return standardMutableGraph;
    }

    public static <N> ImmutableSet<N> reachableNodes(Graph<N> graph, N node) {
        Preconditions.checkArgument(graph.nodes().contains(node), GraphConstants.NODE_NOT_IN_GRAPH, node);
        return ImmutableSet.copyOf(new Traverser.AnonymousClass1(graph, graph).breadthFirst(node));
    }

    public static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> visitedNodes, N startNode) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addLast(new NodeAndRemainingSuccessors(startNode));
        while (!arrayDeque.isEmpty()) {
            NodeAndRemainingSuccessors nodeAndRemainingSuccessors = (NodeAndRemainingSuccessors) arrayDeque.removeLast();
            NodeAndRemainingSuccessors nodeAndRemainingSuccessors2 = (NodeAndRemainingSuccessors) arrayDeque.peekLast();
            arrayDeque.addLast(nodeAndRemainingSuccessors);
            N n = nodeAndRemainingSuccessors.node;
            N n2 = nodeAndRemainingSuccessors2 == null ? null : nodeAndRemainingSuccessors2.node;
            if (nodeAndRemainingSuccessors.remainingSuccessors == null) {
                NodeVisitState nodeVisitState = visitedNodes.get(n);
                if (nodeVisitState == NodeVisitState.COMPLETE) {
                    arrayDeque.removeLast();
                } else {
                    NodeVisitState nodeVisitState2 = NodeVisitState.PENDING;
                    if (nodeVisitState == nodeVisitState2) {
                        return true;
                    }
                    visitedNodes.put(n, nodeVisitState2);
                    nodeAndRemainingSuccessors.remainingSuccessors = new ArrayDeque(graph.successors((Object) n));
                }
            }
            if (!nodeAndRemainingSuccessors.remainingSuccessors.isEmpty()) {
                N nRemove = nodeAndRemainingSuccessors.remainingSuccessors.remove();
                if (canTraverseWithoutReusingEdge(graph, nRemove, n2)) {
                    arrayDeque.addLast(new NodeAndRemainingSuccessors(nRemove));
                }
            }
            arrayDeque.removeLast();
            visitedNodes.put(n, NodeVisitState.COMPLETE);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N> ImmutableGraph<N> transitiveClosure(Graph<N> graph) {
        GraphBuilder graphBuilderFrom = GraphBuilder.from(graph);
        graphBuilderFrom.allowsSelfLoops = true;
        ImmutableGraph.Builder builder = new ImmutableGraph.Builder(graphBuilderFrom);
        if (graph.isDirected()) {
            for (N n : graph.nodes()) {
                UnmodifiableIterator it = reachableNodes((Graph) graph, (Object) n).iterator();
                while (it.hasNext()) {
                    builder.putEdge(n, it.next());
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n2 : graph.nodes()) {
                if (!hashSet.contains(n2)) {
                    ImmutableSet immutableSetReachableNodes = reachableNodes((Graph) graph, (Object) n2);
                    hashSet.addAll(immutableSetReachableNodes);
                    int i = 1;
                    for (Object obj : immutableSetReachableNodes) {
                        int i2 = i + 1;
                        Iterator it2 = ((Iterables.AnonymousClass7) Iterables.limit(immutableSetReachableNodes, i)).iterator();
                        while (true) {
                            Iterators.AnonymousClass7 anonymousClass7 = (Iterators.AnonymousClass7) it2;
                            if (anonymousClass7.hasNext()) {
                                builder.putEdge(obj, anonymousClass7.next());
                            }
                        }
                        i = i2;
                    }
                }
            }
        }
        return ImmutableGraph.copyOf(builder.mutableGraph);
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        return !graph.isDirected() ? graph : graph instanceof TransposedGraph ? ((TransposedGraph) graph).graph : new TransposedGraph(graph);
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(long value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    public static long checkPositive(long value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) graph).graph;
        }
        return new TransposedValueGraph(graph);
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
        ValueGraphBuilder valueGraphBuilderFrom = ValueGraphBuilder.from(graph);
        valueGraphBuilderFrom.expectedNodeCount(graph.nodes().size());
        StandardMutableValueGraph standardMutableValueGraph = new StandardMutableValueGraph(valueGraphBuilderFrom);
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            standardMutableValueGraph.addNode(it.next());
        }
        UnmodifiableIterator<EndpointPair<N>> it2 = ((AbstractBaseGraph.AnonymousClass1) graph.edges()).iterator();
        while (it2.hasNext()) {
            EndpointPair<N> next = it2.next();
            N n = next.nodeU;
            N n2 = next.nodeV;
            V vEdgeValueOrDefault = graph.edgeValueOrDefault(n, n2, null);
            j$.util.Objects.requireNonNull(vEdgeValueOrDefault);
            standardMutableValueGraph.putEdgeValue(n, n2, vEdgeValueOrDefault);
        }
        return standardMutableValueGraph;
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        StandardMutableValueGraph standardMutableValueGraph;
        if (iterable instanceof Collection) {
            ValueGraphBuilder valueGraphBuilderFrom = ValueGraphBuilder.from(valueGraph);
            valueGraphBuilderFrom.expectedNodeCount(((Collection) iterable).size());
            standardMutableValueGraph = new StandardMutableValueGraph(valueGraphBuilderFrom);
        } else {
            standardMutableValueGraph = new StandardMutableValueGraph(ValueGraphBuilder.from(valueGraph));
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            ((StandardMutableValueGraph) standardMutableValueGraph).addNode(it.next());
        }
        UnmodifiableIterator it2 = ((MapIteratorCache.AnonymousClass1) ((StandardValueGraph) standardMutableValueGraph).nodes()).iterator();
        while (true) {
            MapIteratorCache.AnonymousClass1.C00261 c00261 = (MapIteratorCache.AnonymousClass1.C00261) it2;
            if (!c00261.val$entryIterator.hasNext()) {
                return (MutableValueGraph<N, V>) standardMutableValueGraph;
            }
            Object next = c00261.next();
            for (Object obj : valueGraph.successors(next)) {
                if (MapIteratorCache.this.containsKey(obj)) {
                    Object objEdgeValueOrDefault = valueGraph.edgeValueOrDefault(next, obj, null);
                    j$.util.Objects.requireNonNull(objEdgeValueOrDefault);
                    ((StandardMutableValueGraph) standardMutableValueGraph).putEdgeValue(next, obj, objEdgeValueOrDefault);
                }
            }
        }
    }

    public static <N> EndpointPair<N> transpose(EndpointPair<N> endpoints) {
        return endpoints.isOrdered() ? new EndpointPair.Ordered(endpoints.target(), endpoints.source()) : endpoints;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        NetworkBuilder networkBuilderFrom = NetworkBuilder.from(network);
        networkBuilderFrom.expectedNodeCount(network.nodes().size());
        networkBuilderFrom.expectedEdgeCount(network.edges().size());
        StandardMutableNetwork standardMutableNetwork = new StandardMutableNetwork(networkBuilderFrom);
        Iterator<N> it = network.nodes().iterator();
        while (it.hasNext()) {
            standardMutableNetwork.addNode(it.next());
        }
        for (E e : network.edges()) {
            EndpointPair<N> endpointPairIncidentNodes = network.incidentNodes(e);
            standardMutableNetwork.addEdge(endpointPairIncidentNodes.nodeU, endpointPairIncidentNodes.nodeV, e);
        }
        return standardMutableNetwork;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        StandardMutableNetwork standardMutableNetwork;
        if (iterable instanceof Collection) {
            NetworkBuilder networkBuilderFrom = NetworkBuilder.from(network);
            networkBuilderFrom.expectedNodeCount(((Collection) iterable).size());
            standardMutableNetwork = new StandardMutableNetwork(networkBuilderFrom);
        } else {
            standardMutableNetwork = new StandardMutableNetwork(NetworkBuilder.from(network));
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            ((StandardMutableNetwork) standardMutableNetwork).addNode(it.next());
        }
        UnmodifiableIterator it2 = ((MapIteratorCache.AnonymousClass1) ((StandardNetwork) standardMutableNetwork).nodes()).iterator();
        while (true) {
            MapIteratorCache.AnonymousClass1.C00261 c00261 = (MapIteratorCache.AnonymousClass1.C00261) it2;
            if (!c00261.val$entryIterator.hasNext()) {
                return (MutableNetwork<N, E>) standardMutableNetwork;
            }
            Object next = c00261.next();
            for (E e : network.outEdges(next)) {
                Object objAdjacentNode = network.incidentNodes(e).adjacentNode(next);
                if (MapIteratorCache.this.containsKey(objAdjacentNode)) {
                    ((StandardMutableNetwork) standardMutableNetwork).addEdge(next, objAdjacentNode, e);
                }
            }
        }
    }
}
