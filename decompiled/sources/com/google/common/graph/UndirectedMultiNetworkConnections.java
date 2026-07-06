package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.DesugarCollections;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UndirectedMultiNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {

    @LazyInit
    public transient Reference<Multiset<N>> adjacentNodesReference;

    public UndirectedMultiNetworkConnections(Map<E, N> incidentEdges) {
        super(incidentEdges);
    }

    private static <T> T getReference(Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public static <N, E> UndirectedMultiNetworkConnections<N, E> of() {
        return new UndirectedMultiNetworkConnections<>(new HashMap(2, 1.0f));
    }

    public static <N, E> UndirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
        return new UndirectedMultiNetworkConnections<>(ImmutableMap.copyOf((Map) incidentEdges));
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        if (isSelfLoop) {
            return;
        }
        addOutEdge(edge, node);
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addOutEdge(E edge, N node) {
        super.addOutEdge(edge, node);
        Multiset multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(node));
        }
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return DesugarCollections.unmodifiableSet(adjacentNodesMultiset().elementSet());
    }

    public final Multiset<N> adjacentNodesMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset hashMultisetCreate = HashMultiset.create(this.incidentEdgeMap.values());
        this.adjacentNodesReference = new SoftReference(hashMultisetCreate);
        return hashMultisetCreate;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(final N node) {
        return new MultiEdgesConnecting<E>(this, this.incidentEdgeMap, node) { // from class: com.google.common.graph.UndirectedMultiNetworkConnections.1
            public final /* synthetic */ UndirectedMultiNetworkConnections this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return this.this$0.adjacentNodesMultiset().count(node);
            }
        };
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (isSelfLoop) {
            return null;
        }
        return removeOutEdge(edge);
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e) {
        N n = (N) super.removeOutEdge(e);
        Multiset multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(n));
        }
        return n;
    }
}
