package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
@GwtCompatible
@Deprecated
public abstract class TreeTraverser<T> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        public final Queue<T> queue;

        public BreadthFirstIterator(T root) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.queue = arrayDeque;
            arrayDeque.add(root);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T tRemove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(tRemove));
            return tRemove;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.queue.element();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class PostOrderIterator extends AbstractIterator<T> {
        public final ArrayDeque<PostOrderNode<T>> stack;

        public PostOrderIterator(T root) {
            ArrayDeque<PostOrderNode<T>> arrayDeque = new ArrayDeque<>();
            this.stack = arrayDeque;
            arrayDeque.addLast(expand(root));
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode<T> last = this.stack.getLast();
                if (!last.childIterator.hasNext()) {
                    this.stack.removeLast();
                    return last.root;
                }
                this.stack.addLast(expand(last.childIterator.next()));
            }
            endOfData();
            return null;
        }

        public final PostOrderNode<T> expand(T t) {
            return new PostOrderNode<>(t, TreeTraverser.this.children(t).iterator());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PostOrderNode<T> {
        public final Iterator<T> childIterator;
        public final T root;

        public PostOrderNode(T root, Iterator<T> childIterator) {
            root.getClass();
            this.root = root;
            childIterator.getClass();
            this.childIterator = childIterator;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class PreOrderIterator extends UnmodifiableIterator<T> {
        public final Deque<Iterator<T>> stack;

        public PreOrderIterator(T root) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.stack = arrayDeque;
            root.getClass();
            arrayDeque.addLast(new Iterators.SingletonIterator(root));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            Iterator<T> last = this.stack.getLast();
            T next = last.next();
            next.getClass();
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator<T> it = TreeTraverser.this.children(next).iterator();
            if (it.hasNext()) {
                this.stack.addLast(it);
            }
            return next;
        }
    }

    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> nodeToChildrenFunction) {
        nodeToChildrenFunction.getClass();
        return new TreeTraverser<T>() { // from class: com.google.common.collect.TreeTraverser.1
            @Override // com.google.common.collect.TreeTraverser
            public Iterable<T> children(T root) {
                return (Iterable) nodeToChildrenFunction.apply(root);
            }
        };
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T root) {
        root.getClass();
        return new FluentIterable<T>(this) { // from class: com.google.common.collect.TreeTraverser.4
            public final /* synthetic */ TreeTraverser this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(root);
            }
        };
    }

    public abstract Iterable<T> children(T root);

    public UnmodifiableIterator<T> postOrderIterator(T root) {
        return new PostOrderIterator(root);
    }

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T root) {
        root.getClass();
        return new FluentIterable<T>(this) { // from class: com.google.common.collect.TreeTraverser.3
            public final /* synthetic */ TreeTraverser this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return this.this$0.postOrderIterator(root);
            }
        };
    }

    public UnmodifiableIterator<T> preOrderIterator(T root) {
        return new PreOrderIterator(root);
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T root) {
        root.getClass();
        return new FluentIterable<T>(this) { // from class: com.google.common.collect.TreeTraverser.2
            public final /* synthetic */ TreeTraverser this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return this.this$0.preOrderIterator(root);
            }
        };
    }
}
