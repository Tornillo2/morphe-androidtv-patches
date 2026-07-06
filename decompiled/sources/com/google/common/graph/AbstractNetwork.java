package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.graph.AbstractBaseGraph;
import com.google.common.math.IntMath;
import j$.util.DesugarCollections;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public abstract class AbstractNetwork<N, E> implements Network<N, E> {

    /* JADX INFO: renamed from: com.google.common.graph.AbstractNetwork$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AbstractGraph<N> {

        /* JADX INFO: renamed from: com.google.common.graph.AbstractNetwork$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class C00231 extends AbstractSet<EndpointPair<N>> {
            public C00231() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair<?> endpointPair = (EndpointPair) obj;
                return AnonymousClass1.this.isOrderingCompatible(endpointPair) && AnonymousClass1.this.nodes().contains(endpointPair.nodeU) && AnonymousClass1.this.successors((Object) endpointPair.nodeU).contains(endpointPair.nodeV);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<EndpointPair<N>> iterator() {
                return new Iterators.AnonymousClass6(AbstractNetwork.this.edges().iterator(), new Function() { // from class: com.google.common.graph.AbstractNetwork$1$1$$ExternalSyntheticLambda0
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        return AbstractNetwork.this.incidentNodes(obj);
                    }
                });
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return AbstractNetwork.this.edges().size();
            }
        }

        public AnonymousClass1() {
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> adjacentNodes(N node) {
            return AbstractNetwork.this.adjacentNodes(node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> edges() {
            return AbstractNetwork.this.allowsParallelEdges() ? new AbstractBaseGraph.AnonymousClass1() : new C00231();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public ElementOrder<N> incidentEdgeOrder() {
            return ElementOrder.unordered();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractNetwork.this.nodes();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N node) {
            return AbstractNetwork.this.predecessors((Object) node);
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N node) {
            return AbstractNetwork.this.successors((Object) node);
        }
    }

    public static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(final Network<N, E> network) {
        return new Maps.AsMapView(network.edges(), new Function() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda7
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return network.incidentNodes(obj);
            }
        });
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e);
        return (Set<E>) edgeInvalidatableSet(Sets.difference(Sets.union(incidentEdges(endpointPairIncidentNodes.nodeU), incidentEdges(endpointPairIncidentNodes.nodeV)), ImmutableSet.of((Object) e)), e);
    }

    @Override // com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new AnonymousClass1();
    }

    public final Predicate<E> connectedPredicate(N nodePresent, N nodeToCheck) {
        return new AbstractNetwork$$ExternalSyntheticLambda0(this, nodePresent, nodeToCheck);
    }

    @Override // com.google.common.graph.Network
    public int degree(N node) {
        return isDirected() ? IntMath.saturatedAdd(inEdges(node).size(), outEdges(node).size()) : IntMath.saturatedAdd(incidentEdges(node).size(), edgesConnecting(node, node).size());
    }

    @Override // com.google.common.graph.Network
    public E edgeConnectingOrNull(N nodeU, N nodeV) {
        Set<E> setEdgesConnecting = edgesConnecting(nodeU, nodeV);
        int size = setEdgesConnecting.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return setEdgesConnecting.iterator().next();
        }
        throw new IllegalArgumentException(String.format(GraphConstants.MULTIPLE_EDGES_CONNECTING, nodeU, nodeV));
    }

    public final <T> Set<T> edgeInvalidatableSet(Set<T> set, final E edge) {
        return InvalidatableSet.of((Set) set, (Supplier<Boolean>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda5
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return Boolean.valueOf(this.f$0.edges().contains(edge));
            }
        }, (Supplier<String>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda6
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return String.format(GraphConstants.EDGE_REMOVED_FROM_GRAPH, edge);
            }
        });
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        Set<E> setOutEdges = outEdges(n);
        Set<E> setInEdges = inEdges(n2);
        return (Set<E>) nodePairInvalidatableSet(setOutEdges.size() <= setInEdges.size() ? DesugarCollections.unmodifiableSet(Sets.filter(setOutEdges, new AbstractNetwork$$ExternalSyntheticLambda0(this, n, n2))) : DesugarCollections.unmodifiableSet(Sets.filter(setInEdges, new AbstractNetwork$$ExternalSyntheticLambda0(this, n2, n))), n, n2);
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        if (isDirected() == network.isDirected() && nodes().equals(network.nodes())) {
            return ((AbstractMap) edgeIncidentNodesMap(this)).equals(edgeIncidentNodesMap(network));
        }
        return false;
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        endpoints.getClass();
        if (isOrderingCompatible(endpoints)) {
            return hasEdgeConnecting(endpoints.nodeU, endpoints.nodeV);
        }
        return false;
    }

    @Override // com.google.common.graph.Network
    public final int hashCode() {
        return ((AbstractMap) edgeIncidentNodesMap(this)).hashCode();
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N node) {
        return isDirected() ? inEdges(node).size() : degree(node);
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpoints) {
        return endpoints.isOrdered() == isDirected();
    }

    public final <T> Set<T> nodeInvalidatableSet(Set<T> set, final N node) {
        return InvalidatableSet.of((Set) set, (Supplier<Boolean>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return Boolean.valueOf(this.f$0.nodes().contains(node));
            }
        }, (Supplier<String>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return String.format(GraphConstants.NODE_REMOVED_FROM_GRAPH, node);
            }
        });
    }

    public final <T> Set<T> nodePairInvalidatableSet(Set<T> set, final N nodeU, final N nodeV) {
        return InvalidatableSet.of((Set) set, (Supplier<Boolean>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda3
            @Override // com.google.common.base.Supplier
            public final Object get() {
                AbstractNetwork abstractNetwork = this.f$0;
                return Boolean.valueOf(abstractNetwork.nodes().contains(nodeU) && abstractNetwork.nodes().contains(nodeV));
            }
        }, (Supplier<String>) new Supplier() { // from class: com.google.common.graph.AbstractNetwork$$ExternalSyntheticLambda4
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return String.format(GraphConstants.NODE_PAIR_REMOVED_FROM_GRAPH, nodeU, nodeV);
            }
        });
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N node) {
        return isDirected() ? outEdges(node).size() : degree(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors(obj);
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsParallelEdges: " + allowsParallelEdges() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeIncidentNodesMap(this);
    }

    public final void validateEndpoints(EndpointPair<?> endpoints) {
        endpoints.getClass();
        Preconditions.checkArgument(isOrderingCompatible(endpoints), GraphConstants.ENDPOINTS_MISMATCH);
    }

    @Override // com.google.common.graph.Network
    public E edgeConnectingOrNull(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return edgeConnectingOrNull(endpoints.nodeU, endpoints.nodeV);
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        nodeU.getClass();
        nodeV.getClass();
        return nodes().contains(nodeU) && successors((Object) nodeU).contains(nodeV);
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return edgesConnecting(endpoints.nodeU, endpoints.nodeV);
    }
}
