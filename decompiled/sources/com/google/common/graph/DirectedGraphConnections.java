package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.DirectedGraphConnections;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import j$.util.DesugarCollections;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    public static final Object PRED = new Object();
    public final Map<N, Object> adjacentNodeValues;
    public final List<NodeConnection<N>> orderedNodeConnections;
    public int predecessorCount;
    public int successorCount;

    /* JADX INFO: renamed from: com.google.common.graph.DirectedGraphConnections$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends AbstractSet<N> {
        public AnonymousClass2() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return DirectedGraphConnections.this.predecessorCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<N> iterator() {
            DirectedGraphConnections directedGraphConnections = DirectedGraphConnections.this;
            List<NodeConnection<N>> list = directedGraphConnections.orderedNodeConnections;
            if (list == null) {
                final Iterator<Map.Entry<N, Object>> it = directedGraphConnections.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                    public final /* synthetic */ AnonymousClass2 this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
            final Iterator<NodeConnection<N>> it2 = list.iterator();
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                public final /* synthetic */ AnonymousClass2 this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.google.common.collect.AbstractIterator
                public N computeNext() {
                    while (it2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) it2.next();
                        if (nodeConnection instanceof NodeConnection.Pred) {
                            return nodeConnection.node;
                        }
                    }
                    endOfData();
                    return null;
                }
            };
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.DirectedGraphConnections$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends AbstractSet<N> {
        public AnonymousClass3() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return DirectedGraphConnections.this.successorCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<N> iterator() {
            DirectedGraphConnections directedGraphConnections = DirectedGraphConnections.this;
            List<NodeConnection<N>> list = directedGraphConnections.orderedNodeConnections;
            if (list == null) {
                final Iterator<Map.Entry<N, Object>> it = directedGraphConnections.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                    public final /* synthetic */ AnonymousClass3 this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
            final Iterator<NodeConnection<N>> it2 = list.iterator();
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                public final /* synthetic */ AnonymousClass3 this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.google.common.collect.AbstractIterator
                public N computeNext() {
                    while (it2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) it2.next();
                        if (nodeConnection instanceof NodeConnection.Succ) {
                            return nodeConnection.node;
                        }
                    }
                    endOfData();
                    return null;
                }
            };
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.DirectedGraphConnections$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass5 {
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

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class NodeConnection<N> {
        public final N node;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Pred<N> extends NodeConnection<N> {
            public Pred(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Pred) {
                    return this.node.equals(((Pred) that).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Pred.class.hashCode();
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Succ<N> extends NodeConnection<N> {
            public Succ(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Succ) {
                    return this.node.equals(((Succ) that).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Succ.class.hashCode();
            }
        }

        public NodeConnection(N node) {
            node.getClass();
            this.node = node;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PredAndSucc {
        public final Object successorValue;

        public PredAndSucc(Object successorValue) {
            this.successorValue = successorValue;
        }
    }

    public static EndpointPair $r8$lambda$40sSJroDhiYt6uvhIlwyeH28Fdc(Object obj, Object obj2) {
        return new EndpointPair.Ordered(obj2, obj);
    }

    public static EndpointPair $r8$lambda$Sh9jpRzfun7MGpZPDFV9VRk2ECU(Object obj, NodeConnection nodeConnection) {
        return nodeConnection instanceof NodeConnection.Succ ? new EndpointPair.Ordered(obj, nodeConnection.node) : new EndpointPair.Ordered(nodeConnection.node, obj);
    }

    /* JADX INFO: renamed from: $r8$lambda$h_XftLlUzMdDRvCaERTOV-4gRhU, reason: not valid java name */
    public static EndpointPair m543$r8$lambda$h_XftLlUzMdDRvCaERTOV4gRhU(Object obj, Object obj2) {
        return new EndpointPair.Ordered(obj, obj2);
    }

    public DirectedGraphConnections(Map<N, Object> adjacentNodeValues, List<NodeConnection<N>> orderedNodeConnections, int predecessorCount, int successorCount) {
        adjacentNodeValues.getClass();
        this.adjacentNodeValues = adjacentNodeValues;
        this.orderedNodeConnections = orderedNodeConnections;
        Graphs.checkNonNegative(predecessorCount);
        this.predecessorCount = predecessorCount;
        Graphs.checkNonNegative(successorCount);
        this.successorCount = successorCount;
        Preconditions.checkState(predecessorCount <= adjacentNodeValues.size() && successorCount <= adjacentNodeValues.size());
    }

    public static boolean isPredecessor(Object value) {
        return value == PRED || (value instanceof PredAndSucc);
    }

    public static boolean isSuccessor(Object value) {
        return (value == PRED || value == null) ? false : true;
    }

    public static <N, V> DirectedGraphConnections<N, V> of(ElementOrder<N> incidentEdgeOrder) {
        ArrayList arrayList;
        int i = AnonymousClass5.$SwitchMap$com$google$common$graph$ElementOrder$Type[incidentEdgeOrder.type.ordinal()];
        if (i == 1) {
            arrayList = null;
        } else {
            if (i != 2) {
                throw new AssertionError(incidentEdgeOrder.type);
            }
            arrayList = new ArrayList();
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(N thisNode, Iterable<EndpointPair<N>> incidentEdges, Function<N, V> successorNodeToValueFn) {
        thisNode.getClass();
        successorNodeToValueFn.getClass();
        HashMap map = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        int i2 = 0;
        for (EndpointPair<N> endpointPair : incidentEdges) {
            if (endpointPair.nodeU.equals(thisNode) && endpointPair.nodeV.equals(thisNode)) {
                map.put(thisNode, new PredAndSucc(successorNodeToValueFn.apply(thisNode)));
                builder.add(new NodeConnection.Pred(thisNode));
                builder.add(new NodeConnection.Succ(thisNode));
                i++;
            } else if (endpointPair.nodeV.equals(thisNode)) {
                N n = endpointPair.nodeU;
                Object objPut = map.put(n, PRED);
                if (objPut != null) {
                    map.put(n, new PredAndSucc(objPut));
                }
                builder.add(new NodeConnection.Pred(n));
                i++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU.equals(thisNode));
                N n2 = endpointPair.nodeV;
                V vApply = successorNodeToValueFn.apply(n2);
                Object objPut2 = map.put(n2, vApply);
                if (objPut2 != null) {
                    Preconditions.checkArgument(objPut2 == PRED);
                    map.put(n2, new PredAndSucc(vApply));
                }
                builder.add(new NodeConnection.Succ(n2));
            }
            i2++;
        }
        return new DirectedGraphConnections<>(map, builder.build(), i, i2);
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N node, V unused) {
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object objPut = map.put(node, obj);
        if (objPut != null) {
            if (objPut instanceof PredAndSucc) {
                this.adjacentNodeValues.put(node, objPut);
                return;
            } else if (objPut == obj) {
                return;
            } else {
                this.adjacentNodeValues.put(node, new PredAndSucc(objPut));
            }
        }
        int i = this.predecessorCount + 1;
        this.predecessorCount = i;
        Graphs.checkPositive(i);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list != null) {
            list.add(new NodeConnection.Pred(node));
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n, V v) {
        Object obj = (V) this.adjacentNodeValues.put(n, v);
        if (obj == null) {
            obj = (V) null;
        } else if (obj instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n, new PredAndSucc(v));
            obj = (V) ((PredAndSucc) obj).successorValue;
        } else if (obj == PRED) {
            this.adjacentNodeValues.put(n, new PredAndSucc(v));
            obj = (V) null;
        }
        if (obj == null) {
            int i = this.successorCount + 1;
            this.successorCount = i;
            Graphs.checkPositive(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Succ(n));
            }
        }
        if (obj == null) {
            return null;
        }
        return (V) obj;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return this.orderedNodeConnections == null ? DesugarCollections.unmodifiableSet(this.adjacentNodeValues.keySet()) : new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator<NodeConnection<N>> it = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet hashSet = new HashSet();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    public final /* synthetic */ AnonymousClass1 this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it.next();
                            if (hashSet.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N thisNode) {
        thisNode.getClass();
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        final Iterator itConcat = list == null ? Iterators.concat(new Iterators.AnonymousClass6(new AnonymousClass2().iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return new EndpointPair.Ordered(obj, thisNode);
            }
        }), new Iterators.AnonymousClass6(new AnonymousClass3().iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return new EndpointPair.Ordered(thisNode, obj);
            }
        })) : new Iterators.AnonymousClass6(list.iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return DirectedGraphConnections.$r8$lambda$Sh9jpRzfun7MGpZPDFV9VRk2ECU(thisNode, (DirectedGraphConnections.NodeConnection) obj);
            }
        });
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
            public final /* synthetic */ DirectedGraphConnections this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.AbstractIterator
            public EndpointPair<N> computeNext() {
                while (itConcat.hasNext()) {
                    EndpointPair<N> endpointPair = (EndpointPair) itConcat.next();
                    if (!endpointPair.nodeU.equals(endpointPair.nodeV) || !atomicBoolean.getAndSet(true)) {
                        return endpointPair;
                    }
                }
                endOfData();
                return null;
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AnonymousClass2();
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N node) {
        node.getClass();
        Object obj = this.adjacentNodeValues.get(node);
        if (obj == PRED) {
            this.adjacentNodeValues.remove(node);
        } else if (!(obj instanceof PredAndSucc)) {
            return;
        } else {
            this.adjacentNodeValues.put(node, ((PredAndSucc) obj).successorValue);
        }
        int i = this.predecessorCount - 1;
        this.predecessorCount = i;
        Graphs.checkNonNegative(i);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list != null) {
            list.remove(new NodeConnection.Pred(node));
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        obj.getClass();
        Object obj3 = (V) this.adjacentNodeValues.get(obj);
        if (obj3 == null || obj3 == (obj2 = PRED)) {
            obj3 = (V) null;
        } else if (obj3 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            obj3 = (V) ((PredAndSucc) obj3).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (obj3 != null) {
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        if (obj3 == null) {
            return null;
        }
        return (V) obj3;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AnonymousClass3();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    public V value(N n) {
        n.getClass();
        V v = (V) this.adjacentNodeValues.get(n);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }
}
