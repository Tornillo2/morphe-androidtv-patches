package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@Beta
public interface PredecessorsFunction<N> {
    Iterable<? extends N> predecessors(N node);
}
