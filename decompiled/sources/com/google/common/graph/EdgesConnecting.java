package com.google.common.graph;

import com.google.common.collect.Iterators;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EdgesConnecting<E> extends AbstractSet<E> {
    public final Map<?, E> nodeToOutEdge;
    public final Object targetNode;

    public EdgesConnecting(Map<?, E> nodeToEdgeMap, Object targetNode) {
        nodeToEdgeMap.getClass();
        this.nodeToOutEdge = nodeToEdgeMap;
        targetNode.getClass();
        this.targetNode = targetNode;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object edge) {
        E connectingEdge = getConnectingEdge();
        return connectingEdge != null && connectingEdge.equals(edge);
    }

    public final E getConnectingEdge() {
        return this.nodeToOutEdge.get(this.targetNode);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return getConnectingEdge() == null ? 0 : 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public UnmodifiableIterator<E> iterator() {
        E connectingEdge = getConnectingEdge();
        return connectingEdge == null ? (UnmodifiableIterator<E>) RegularImmutableSet.EMPTY.iterator() : new Iterators.SingletonIterator(connectingEdge);
    }
}
