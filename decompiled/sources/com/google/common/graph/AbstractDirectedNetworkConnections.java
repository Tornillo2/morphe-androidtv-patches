package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    public final Map<E, N> inEdgeMap;
    public final Map<E, N> outEdgeMap;
    public int selfLoopCount;

    public AbstractDirectedNetworkConnections(Map<E, N> inEdgeMap, Map<E, N> outEdgeMap, int selfLoopCount) {
        inEdgeMap.getClass();
        this.inEdgeMap = inEdgeMap;
        outEdgeMap.getClass();
        this.outEdgeMap = outEdgeMap;
        Graphs.checkNonNegative(selfLoopCount);
        this.selfLoopCount = selfLoopCount;
        Preconditions.checkState(selfLoopCount <= inEdgeMap.size() && selfLoopCount <= outEdgeMap.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        edge.getClass();
        node.getClass();
        if (isSelfLoop) {
            int i = this.selfLoopCount + 1;
            this.selfLoopCount = i;
            Graphs.checkPositive(i);
        }
        Preconditions.checkState(this.inEdgeMap.put(edge, node) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E edge, N node) {
        edge.getClass();
        node.getClass();
        Preconditions.checkState(this.outEdgeMap.put(edge, node) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E edge) {
        N n = this.outEdgeMap.get(edge);
        Objects.requireNonNull(n);
        return n;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return DesugarCollections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() { // from class: com.google.common.graph.AbstractDirectedNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                AbstractDirectedNetworkConnections abstractDirectedNetworkConnections = AbstractDirectedNetworkConnections.this;
                return Iterators.unmodifiableIterator((abstractDirectedNetworkConnections.selfLoopCount == 0 ? FluentIterable.concat(abstractDirectedNetworkConnections.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()) : Sets.union(abstractDirectedNetworkConnections.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet())).iterator());
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return DesugarCollections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (isSelfLoop) {
            int i = this.selfLoopCount - 1;
            this.selfLoopCount = i;
            Graphs.checkNonNegative(i);
        }
        N nRemove = this.inEdgeMap.remove(edge);
        Objects.requireNonNull(nRemove);
        return nRemove;
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E edge) {
        N nRemove = this.outEdgeMap.remove(edge);
        Objects.requireNonNull(nRemove);
        return nRemove;
    }
}
