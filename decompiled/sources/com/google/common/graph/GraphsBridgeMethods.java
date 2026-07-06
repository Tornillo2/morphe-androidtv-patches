package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public abstract class GraphsBridgeMethods {
    public static <N> Set<N> reachableNodes(Graph<N> graph, N node) {
        return Graphs.reachableNodes((Graph) graph, (Object) node);
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        return Graphs.transitiveClosure((Graph) graph);
    }
}
