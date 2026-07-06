package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.Traverser;
import com.google.errorprone.annotations.DoNotMock;
import j$.util.Objects;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Call forGraph or forTree, passing a lambda or a Graph with the desired edges (built with GraphBuilder)")
@Beta
public abstract class Traverser<N> {
    public final SuccessorsFunction<N> successorFunction;

    /* JADX INFO: renamed from: com.google.common.graph.Traverser$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends Traverser<N> {
        public final /* synthetic */ SuccessorsFunction val$graph;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SuccessorsFunction successorFunction, final SuccessorsFunction val$graph) {
            super(successorFunction);
            this.val$graph = val$graph;
        }

        @Override // com.google.common.graph.Traverser
        public Traversal<N> newTraversal() {
            return Traversal.inGraph(this.val$graph);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum InsertionOrder {
        FRONT { // from class: com.google.common.graph.Traverser.InsertionOrder.1
            @Override // com.google.common.graph.Traverser.InsertionOrder
            public <T> void insertInto(Deque<T> deque, T value) {
                deque.addFirst(value);
            }
        },
        BACK { // from class: com.google.common.graph.Traverser.InsertionOrder.2
            @Override // com.google.common.graph.Traverser.InsertionOrder
            public <T> void insertInto(Deque<T> deque, T value) {
                deque.addLast(value);
            }
        };

        public abstract <T> void insertInto(Deque<T> deque, T value);

        InsertionOrder(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Traversal<N> {
        public final SuccessorsFunction<N> successorFunction;

        /* JADX INFO: renamed from: com.google.common.graph.Traverser$Traversal$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass2 extends Traversal<N> {
            public AnonymousClass2(SuccessorsFunction successorFunction) {
                super(successorFunction);
            }

            @Override // com.google.common.graph.Traverser.Traversal
            public N visitNext(Deque<Iterator<? extends N>> horizon) {
                Iterator<? extends N> first = horizon.getFirst();
                if (!first.hasNext()) {
                    horizon.removeFirst();
                    return null;
                }
                N next = first.next();
                next.getClass();
                return next;
            }
        }

        public Traversal(SuccessorsFunction<N> successorFunction) {
            this.successorFunction = successorFunction;
        }

        public static <N> Traversal<N> inGraph(SuccessorsFunction<N> graph) {
            final HashSet hashSet = new HashSet();
            return new Traversal<N>(graph) { // from class: com.google.common.graph.Traverser.Traversal.1
                @Override // com.google.common.graph.Traverser.Traversal
                public N visitNext(Deque<Iterator<? extends N>> horizon) {
                    Iterator<? extends N> first = horizon.getFirst();
                    while (first.hasNext()) {
                        N next = first.next();
                        Objects.requireNonNull(next);
                        if (hashSet.add(next)) {
                            return next;
                        }
                    }
                    horizon.removeFirst();
                    return null;
                }
            };
        }

        public static <N> Traversal<N> inTree(SuccessorsFunction<N> tree) {
            return new AnonymousClass2(tree);
        }

        public final Iterator<N> breadthFirst(Iterator<? extends N> startNodes) {
            return topDown(startNodes, InsertionOrder.BACK);
        }

        public final Iterator<N> postOrder(Iterator<? extends N> startNodes) {
            final ArrayDeque arrayDeque = new ArrayDeque();
            final ArrayDeque arrayDeque2 = new ArrayDeque();
            arrayDeque2.add(startNodes);
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.Traverser.Traversal.4
                public final /* synthetic */ Traversal this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // com.google.common.collect.AbstractIterator
                public N computeNext() {
                    while (true) {
                        N n = (N) this.this$0.visitNext(arrayDeque2);
                        if (n == null) {
                            if (!arrayDeque.isEmpty()) {
                                return (N) arrayDeque.pop();
                            }
                            endOfData();
                            return null;
                        }
                        Iterator<? extends N> it = this.this$0.successorFunction.successors(n).iterator();
                        if (!it.hasNext()) {
                            return n;
                        }
                        arrayDeque2.addFirst(it);
                        arrayDeque.push(n);
                    }
                }
            };
        }

        public final Iterator<N> preOrder(Iterator<? extends N> startNodes) {
            return topDown(startNodes, InsertionOrder.FRONT);
        }

        public final Iterator<N> topDown(Iterator<? extends N> startNodes, final InsertionOrder order) {
            final ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.add(startNodes);
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.Traverser.Traversal.3
                public final /* synthetic */ Traversal this$0;

                {
                    this.this$0 = this;
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // com.google.common.collect.AbstractIterator
                public N computeNext() {
                    do {
                        N n = (N) this.this$0.visitNext(arrayDeque);
                        if (n != null) {
                            Iterator<? extends N> it = this.this$0.successorFunction.successors(n).iterator();
                            if (it.hasNext()) {
                                order.insertInto(arrayDeque, it);
                            }
                            return n;
                        }
                    } while (!arrayDeque.isEmpty());
                    endOfData();
                    return null;
                }
            };
        }

        public abstract N visitNext(Deque<Iterator<? extends N>> horizon);
    }

    public /* synthetic */ Traverser(SuccessorsFunction successorsFunction, AnonymousClass1 anonymousClass1) {
        this(successorsFunction);
    }

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> graph) {
        return new AnonymousClass1(graph, graph);
    }

    public static <N> Traverser<N> forTree(final SuccessorsFunction<N> tree) {
        if (tree instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) tree).isDirected(), "Undirected graphs can never be trees.");
        }
        if (tree instanceof Network) {
            Preconditions.checkArgument(((Network) tree).isDirected(), "Undirected networks can never be trees.");
        }
        return new Traverser<N>(tree) { // from class: com.google.common.graph.Traverser.2
            @Override // com.google.common.graph.Traverser
            public Traversal<N> newTraversal() {
                return new Traversal.AnonymousClass2(tree);
            }
        };
    }

    public final Iterable<N> breadthFirst(N startNode) {
        return breadthFirst((Iterable) ImmutableSet.of(startNode));
    }

    public final Iterable<N> depthFirstPostOrder(N startNode) {
        return depthFirstPostOrder((Iterable) ImmutableSet.of(startNode));
    }

    public final Iterable<N> depthFirstPreOrder(N startNode) {
        return depthFirstPreOrder((Iterable) ImmutableSet.of(startNode));
    }

    public abstract Traversal<N> newTraversal();

    public final ImmutableSet<N> validate(Iterable<? extends N> startNodes) {
        ImmutableSet<N> immutableSetCopyOf = ImmutableSet.copyOf(startNodes);
        UnmodifiableIterator<N> it = immutableSetCopyOf.iterator();
        while (it.hasNext()) {
            this.successorFunction.successors(it.next());
        }
        return immutableSetCopyOf;
    }

    public Traverser(SuccessorsFunction<N> successorFunction) {
        successorFunction.getClass();
        this.successorFunction = successorFunction;
    }

    public final Iterable<N> breadthFirst(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> immutableSetValidate = validate(startNodes);
        return new Iterable() { // from class: com.google.common.graph.Traverser$$ExternalSyntheticLambda1
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.f$0.newTraversal().topDown(immutableSetValidate.iterator(), Traverser.InsertionOrder.BACK);
            }
        };
    }

    public final Iterable<N> depthFirstPostOrder(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> immutableSetValidate = validate(startNodes);
        return new Iterable() { // from class: com.google.common.graph.Traverser$$ExternalSyntheticLambda2
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.f$0.newTraversal().postOrder(immutableSetValidate.iterator());
            }
        };
    }

    public final Iterable<N> depthFirstPreOrder(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> immutableSetValidate = validate(startNodes);
        return new Iterable() { // from class: com.google.common.graph.Traverser$$ExternalSyntheticLambda0
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.f$0.newTraversal().topDown(immutableSetValidate.iterator(), Traverser.InsertionOrder.FRONT);
            }
        };
    }
}
