package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.graph.AbstractBaseGraph;
import j$.util.Objects;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public abstract class AbstractValueGraph<N, V> extends AbstractBaseGraph<N> implements ValueGraph<N, V> {
    public static Object $r8$lambda$oVmkGzcVNGd_66RgVHa6EjncIpQ(ValueGraph valueGraph, EndpointPair endpointPair) {
        Object objEdgeValueOrDefault = valueGraph.edgeValueOrDefault(endpointPair.nodeU, endpointPair.nodeV, null);
        Objects.requireNonNull(objEdgeValueOrDefault);
        return objEdgeValueOrDefault;
    }

    public static <N, V> Map<EndpointPair<N>, V> edgeValueMap(final ValueGraph<N, V> graph) {
        return new Maps.AsMapView(graph.edges(), new Function() { // from class: com.google.common.graph.AbstractValueGraph$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return AbstractValueGraph.$r8$lambda$oVmkGzcVNGd_66RgVHa6EjncIpQ(graph, (EndpointPair) obj);
            }
        });
    }

    @Override // com.google.common.graph.ValueGraph
    public Graph<N> asGraph() {
        return new AbstractGraph<N>() { // from class: com.google.common.graph.AbstractValueGraph.1
            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> adjacentNodes(N node) {
                return AbstractValueGraph.this.adjacentNodes(node);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean allowsSelfLoops() {
                return AbstractValueGraph.this.allowsSelfLoops();
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int degree(N node) {
                return AbstractValueGraph.this.degree(node);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public Set<EndpointPair<N>> edges() {
                return AbstractValueGraph.this.edges();
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int inDegree(N node) {
                return AbstractValueGraph.this.inDegree(node);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public ElementOrder<N> incidentEdgeOrder() {
                return AbstractValueGraph.this.incidentEdgeOrder();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean isDirected() {
                return AbstractValueGraph.this.isDirected();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public ElementOrder<N> nodeOrder() {
                return AbstractValueGraph.this.nodeOrder();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> nodes() {
                return AbstractValueGraph.this.nodes();
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int outDegree(N node) {
                return AbstractValueGraph.this.outDegree(node);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
            public Set<N> predecessors(N node) {
                return AbstractValueGraph.this.predecessors((Object) node);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
            public Set<N> successors(N node) {
                return AbstractValueGraph.this.successors((Object) node);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int degree(Object node) {
        return super.degree(node);
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set edges() {
        return new AbstractBaseGraph.AnonymousClass1();
    }

    @Override // com.google.common.graph.ValueGraph
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueGraph)) {
            return false;
        }
        ValueGraph valueGraph = (ValueGraph) obj;
        if (isDirected() == valueGraph.isDirected() && nodes().equals(valueGraph.nodes())) {
            return ((AbstractMap) edgeValueMap(this)).equals(edgeValueMap(valueGraph));
        }
        return false;
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpoints) {
        return super.hasEdgeConnecting(endpoints);
    }

    @Override // com.google.common.graph.ValueGraph
    public final int hashCode() {
        return ((AbstractMap) edgeValueMap(this)).hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int inDegree(Object node) {
        return super.inDegree(node);
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ Set incidentEdges(Object node) {
        return super.incidentEdges(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int outDegree(Object node) {
        return super.outDegree(node);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors(obj);
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeValueMap(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
        return super.hasEdgeConnecting(nodeU, nodeV);
    }
}
