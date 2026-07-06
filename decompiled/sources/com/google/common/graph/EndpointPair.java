package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.text.AlphabetConverter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"N"})
@Beta
public abstract class EndpointPair<N> implements Iterable<N> {
    public final N nodeU;
    public final N nodeV;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Ordered<N> extends EndpointPair<N> {
        public Ordered(N source, N target) {
            super(source, target);
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean equals(Object obj) {
            if (obj != this) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair endpointPair = (EndpointPair) obj;
                if (true != endpointPair.isOrdered() || !this.nodeU.equals(endpointPair.source()) || !this.nodeV.equals(endpointPair.target())) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.nodeU, this.nodeV});
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return true;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            return this.nodeU;
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            return this.nodeV;
        }

        public String toString() {
            return "<" + this.nodeU + AlphabetConverter.ARROW + this.nodeV + ">";
        }

        public Ordered(Object obj, Object obj2, AnonymousClass1 anonymousClass1) {
            super(obj, obj2);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Unordered<N> extends EndpointPair<N> {
        public Unordered(N nodeU, N nodeV) {
            super(nodeU, nodeV);
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair endpointPair = (EndpointPair) obj;
            if (endpointPair.isOrdered()) {
                return false;
            }
            return this.nodeU.equals(endpointPair.nodeU) ? this.nodeV.equals(endpointPair.nodeV) : this.nodeU.equals(endpointPair.nodeV) && this.nodeV.equals(endpointPair.nodeU);
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return this.nodeV.hashCode() + this.nodeU.hashCode();
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return false;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            throw new UnsupportedOperationException(GraphConstants.NOT_AVAILABLE_ON_UNDIRECTED);
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            throw new UnsupportedOperationException(GraphConstants.NOT_AVAILABLE_ON_UNDIRECTED);
        }

        public String toString() {
            return "[" + this.nodeU + ", " + this.nodeV + "]";
        }

        public Unordered(Object obj, Object obj2, AnonymousClass1 anonymousClass1) {
            super(obj, obj2);
        }
    }

    public static <N> EndpointPair<N> of(Graph<?> graph, N nodeU, N nodeV) {
        return graph.isDirected() ? new Ordered(nodeU, nodeV) : new Unordered(nodeV, nodeU);
    }

    public static <N> EndpointPair<N> ordered(N source, N target) {
        return new Ordered(source, target);
    }

    public static <N> EndpointPair<N> unordered(N nodeU, N nodeV) {
        return new Unordered(nodeV, nodeU);
    }

    public final N adjacentNode(N node) {
        if (node.equals(this.nodeU)) {
            return this.nodeV;
        }
        if (node.equals(this.nodeV)) {
            return this.nodeU;
        }
        throw new IllegalArgumentException("EndpointPair " + this + " does not contain node " + node);
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract boolean isOrdered();

    public final N nodeU() {
        return this.nodeU;
    }

    public final N nodeV() {
        return this.nodeV;
    }

    public abstract N source();

    public abstract N target();

    public EndpointPair(N nodeU, N nodeV) {
        nodeU.getClass();
        this.nodeU = nodeU;
        nodeV.getClass();
        this.nodeV = nodeV;
    }

    @Override // java.lang.Iterable
    public final UnmodifiableIterator<N> iterator() {
        return Iterators.forArrayWithPosition(new Object[]{this.nodeU, this.nodeV}, 0);
    }

    public static <N> EndpointPair<N> of(Network<?, ?> network, N nodeU, N nodeV) {
        if (network.isDirected()) {
            return new Ordered(nodeU, nodeV);
        }
        return new Unordered(nodeV, nodeU);
    }
}
