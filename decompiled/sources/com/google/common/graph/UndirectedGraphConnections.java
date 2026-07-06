package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UndirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    public final Map<N, V> adjacentNodeValues;

    /* JADX INFO: renamed from: com.google.common.graph.UndirectedGraphConnections$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            $SwitchMap$com$google$common$graph$ElementOrder$Type = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static EndpointPair $r8$lambda$ZxIcNmaVl7Lu6Gu_zjSH7hnRJiI(Object obj, Object obj2) {
        return new EndpointPair.Unordered(obj2, obj);
    }

    public UndirectedGraphConnections(Map<N, V> adjacentNodeValues) {
        adjacentNodeValues.getClass();
        this.adjacentNodeValues = adjacentNodeValues;
    }

    public static <N, V> UndirectedGraphConnections<N, V> of(ElementOrder<N> incidentEdgeOrder) {
        int i = AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type[incidentEdgeOrder.type.ordinal()];
        if (i == 1) {
            return new UndirectedGraphConnections<>(new HashMap(2, 1.0f));
        }
        if (i == 2) {
            return new UndirectedGraphConnections<>(new LinkedHashMap(2, 1.0f));
        }
        throw new AssertionError(incidentEdgeOrder.type);
    }

    public static <N, V> UndirectedGraphConnections<N, V> ofImmutable(Map<N, V> adjacentNodeValues) {
        return new UndirectedGraphConnections<>(ImmutableMap.copyOf((Map) adjacentNodeValues));
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N node, V value) {
        addSuccessor(node, value);
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N node, V value) {
        return this.adjacentNodeValues.put(node, value);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return DesugarCollections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N thisNode) {
        return new Iterators.AnonymousClass6(this.adjacentNodeValues.keySet().iterator(), new Function() { // from class: com.google.common.graph.UndirectedGraphConnections$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return new EndpointPair.Unordered(obj, thisNode);
            }
        });
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N node) {
        removeSuccessor(node);
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(N node) {
        return this.adjacentNodeValues.remove(node);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N node) {
        return this.adjacentNodeValues.get(node);
    }
}
