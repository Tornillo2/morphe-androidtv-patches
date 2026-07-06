package androidx.core.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TreeIterator<T> implements Iterator<T>, KMappedMarker {

    @NotNull
    public final Function1<T, Iterator<T>> getChildIterator;

    @NotNull
    public Iterator<? extends T> iterator;

    @NotNull
    public final List<Iterator<T>> stack = new ArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    public TreeIterator(@NotNull Iterator<? extends T> it, @NotNull Function1<? super T, ? extends Iterator<? extends T>> function1) {
        this.getChildIterator = function1;
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T next = this.iterator.next();
        prepareNextIterator(next);
        return next;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void prepareNextIterator(T t) {
        Iterator<T> itInvoke = this.getChildIterator.invoke(t);
        if (itInvoke != null && itInvoke.hasNext()) {
            this.stack.add((Iterator<T>) this.iterator);
            this.iterator = itInvoke;
        } else {
            while (!this.iterator.hasNext() && !this.stack.isEmpty()) {
                this.iterator = (Iterator) CollectionsKt___CollectionsKt.last((List) this.stack);
                CollectionsKt__MutableCollectionsKt.removeLast(this.stack);
            }
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
