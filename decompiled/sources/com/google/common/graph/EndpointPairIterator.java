package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.graph.EndpointPair;
import j$.util.Objects;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class EndpointPairIterator<N> extends AbstractIterator<EndpointPair<N>> {
    public final BaseGraph<N> graph;
    public N node;
    public final Iterator<N> nodeIterator;
    public Iterator<N> successorIterator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Directed<N> extends EndpointPairIterator<N> {
        public Directed(BaseGraph<N> graph) {
            super(graph);
        }

        public Directed(BaseGraph baseGraph, AnonymousClass1 anonymousClass1) {
            super(baseGraph);
        }

        @Override // com.google.common.collect.AbstractIterator
        public EndpointPair<N> computeNext() {
            while (!this.successorIterator.hasNext()) {
                if (!advance()) {
                    endOfData();
                    return null;
                }
            }
            N n = this.node;
            Objects.requireNonNull(n);
            return new EndpointPair.Ordered(n, this.successorIterator.next());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Undirected<N> extends EndpointPairIterator<N> {
        public Set<N> visitedNodes;

        public Undirected(BaseGraph<N> graph) {
            super(graph);
            this.visitedNodes = Sets.newHashSetWithExpectedSize(graph.nodes().size() + 1);
        }

        @Override // com.google.common.collect.AbstractIterator
        public EndpointPair<N> computeNext() {
            do {
                Objects.requireNonNull(this.visitedNodes);
                while (this.successorIterator.hasNext()) {
                    N next = this.successorIterator.next();
                    if (!this.visitedNodes.contains(next)) {
                        N n = this.node;
                        Objects.requireNonNull(n);
                        return new EndpointPair.Unordered(next, n);
                    }
                }
                this.visitedNodes.add(this.node);
            } while (advance());
            this.visitedNodes = null;
            endOfData();
            return null;
        }
    }

    public static <N> EndpointPairIterator<N> of(BaseGraph<N> graph) {
        return graph.isDirected() ? new Directed(graph) : new Undirected(graph);
    }

    public final boolean advance() {
        Preconditions.checkState(!this.successorIterator.hasNext());
        if (!this.nodeIterator.hasNext()) {
            return false;
        }
        N next = this.nodeIterator.next();
        this.node = next;
        this.successorIterator = this.graph.successors((Object) next).iterator();
        return true;
    }

    public EndpointPairIterator(BaseGraph<N> graph) {
        this.node = null;
        this.successorIterator = ImmutableSet.of().iterator();
        this.graph = graph;
        this.nodeIterator = graph.nodes().iterator();
    }
}
