package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.AbstractBaseGraph;
import com.google.common.graph.EndpointPair;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {

    /* JADX INFO: renamed from: com.google.common.graph.AbstractBaseGraph$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AbstractSet<EndpointPair<N>> {
        public AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair<?> endpointPair = (EndpointPair) obj;
            return AbstractBaseGraph.this.isOrderingCompatible(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU) && AbstractBaseGraph.this.successors((Object) endpointPair.nodeU).contains(endpointPair.nodeV);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<EndpointPair<N>> iterator() {
            return EndpointPairIterator.of(AbstractBaseGraph.this);
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.AbstractBaseGraph$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends IncidentEdgeSet<N> {
        /* JADX INFO: renamed from: $r8$lambda$57m3nb3wzUVYQTD-5_snwXxfe84, reason: not valid java name */
        public static EndpointPair m537$r8$lambda$57m3nb3wzUVYQTD5_snwXxfe84(AnonymousClass2 anonymousClass2, Object obj) {
            return new EndpointPair.Ordered(anonymousClass2.node, obj);
        }

        /* JADX INFO: renamed from: $r8$lambda$GHsNUtQD2KQiUTYQVeG0ZKZUX-M, reason: not valid java name */
        public static EndpointPair m538$r8$lambda$GHsNUtQD2KQiUTYQVeG0ZKZUXM(AnonymousClass2 anonymousClass2, Object obj) {
            return new EndpointPair.Unordered(obj, anonymousClass2.node);
        }

        /* JADX INFO: renamed from: $r8$lambda$aXAY7HxSjdOtUaJ-tXij-wtm7ko, reason: not valid java name */
        public static EndpointPair m539$r8$lambda$aXAY7HxSjdOtUaJtXijwtm7ko(AnonymousClass2 anonymousClass2, Object obj) {
            return new EndpointPair.Ordered(obj, anonymousClass2.node);
        }

        public AnonymousClass2(BaseGraph graph, Object node) {
            super(graph, node);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<EndpointPair<N>> iterator() {
            return this.graph.isDirected() ? Iterators.unmodifiableIterator(Iterators.concat(new Iterators.AnonymousClass6(this.graph.predecessors((Object) this.node).iterator(), new Function() { // from class: com.google.common.graph.AbstractBaseGraph$2$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return AbstractBaseGraph.AnonymousClass2.m539$r8$lambda$aXAY7HxSjdOtUaJtXijwtm7ko(this.f$0, obj);
                }
            }), new Iterators.AnonymousClass6(new Sets.AnonymousClass3.AnonymousClass1(), new Function() { // from class: com.google.common.graph.AbstractBaseGraph$2$$ExternalSyntheticLambda1
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return AbstractBaseGraph.AnonymousClass2.m537$r8$lambda$57m3nb3wzUVYQTD5_snwXxfe84(this.f$0, obj);
                }
            }))) : Iterators.unmodifiableIterator(new Iterators.AnonymousClass6(this.graph.adjacentNodes(this.node).iterator(), new Function() { // from class: com.google.common.graph.AbstractBaseGraph$2$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return AbstractBaseGraph.AnonymousClass2.m538$r8$lambda$GHsNUtQD2KQiUTYQVeG0ZKZUXM(this.f$0, obj);
                }
            }));
        }
    }

    @Override // com.google.common.graph.BaseGraph
    public int degree(N node) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((Object) node).size(), successors((Object) node).size());
        }
        Set<N> setAdjacentNodes = adjacentNodes(node);
        return IntMath.saturatedAdd(setAdjacentNodes.size(), (allowsSelfLoops() && setAdjacentNodes.contains(node)) ? 1 : 0);
    }

    public long edgeCount() {
        Iterator<N> it = nodes().iterator();
        long jDegree = 0;
        while (it.hasNext()) {
            jDegree += (long) degree(it.next());
        }
        Preconditions.checkState((1 & jDegree) == 0);
        return jDegree >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        endpoints.getClass();
        if (!isOrderingCompatible(endpoints)) {
            return false;
        }
        N n = endpoints.nodeU;
        return nodes().contains(n) && successors((Object) n).contains(endpoints.nodeV);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N node) {
        return isDirected() ? predecessors((Object) node).size() : degree(node);
    }

    @Override // com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        n.getClass();
        Preconditions.checkArgument(nodes().contains(n), GraphConstants.NODE_NOT_IN_GRAPH, n);
        return (Set<EndpointPair<N>>) nodeInvalidatableSet(new AnonymousClass2(this, n), n);
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpoints) {
        return endpoints.isOrdered() == isDirected();
    }

    public final <T> Set<T> nodeInvalidatableSet(Set<T> set, final N node) {
        return InvalidatableSet.of((Set) set, (Supplier<Boolean>) new Supplier() { // from class: com.google.common.graph.AbstractBaseGraph$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return Boolean.valueOf(this.f$0.nodes().contains(node));
            }
        }, (Supplier<String>) new Supplier() { // from class: com.google.common.graph.AbstractBaseGraph$$ExternalSyntheticLambda3
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return String.format(GraphConstants.NODE_REMOVED_FROM_GRAPH, node);
            }
        });
    }

    public final <T> Set<T> nodePairInvalidatableSet(Set<T> set, final N nodeU, final N nodeV) {
        return InvalidatableSet.of((Set) set, (Supplier<Boolean>) new Supplier() { // from class: com.google.common.graph.AbstractBaseGraph$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                AbstractBaseGraph abstractBaseGraph = this.f$0;
                return Boolean.valueOf(abstractBaseGraph.nodes().contains(nodeU) && abstractBaseGraph.nodes().contains(nodeV));
            }
        }, (Supplier<String>) new Supplier() { // from class: com.google.common.graph.AbstractBaseGraph$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return String.format(GraphConstants.NODE_PAIR_REMOVED_FROM_GRAPH, nodeU, nodeV);
            }
        });
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N node) {
        return isDirected() ? successors((Object) node).size() : degree(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors(obj);
    }

    public final void validateEndpoints(EndpointPair<?> endpoints) {
        endpoints.getClass();
        Preconditions.checkArgument(isOrderingCompatible(endpoints), GraphConstants.ENDPOINTS_MISMATCH);
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        nodeU.getClass();
        nodeV.getClass();
        return nodes().contains(nodeU) && successors((Object) nodeU).contains(nodeV);
    }
}
