package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ReversedListReadOnly<T> extends AbstractList<T> {

    @NotNull
    public final List<T> delegate;

    /* JADX INFO: renamed from: kotlin.collections.ReversedListReadOnly$listIterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements ListIterator<T>, KMappedMarker {
        public final ListIterator<T> delegateIterator;
        public final /* synthetic */ ReversedListReadOnly<T> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(ReversedListReadOnly<? extends T> reversedListReadOnly, int i) {
            this.this$0 = reversedListReadOnly;
            this.delegateIterator = reversedListReadOnly.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedListReadOnly, i));
        }

        @Override // java.util.ListIterator
        public void add(T t) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final ListIterator<T> getDelegateIterator() {
            return this.delegateIterator;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.delegateIterator.hasPrevious();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.delegateIterator.hasNext();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public T next() {
            return this.delegateIterator.previous();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            ReversedListReadOnly<T> reversedListReadOnly = this.this$0;
            return CollectionsKt__CollectionsKt.getLastIndex(reversedListReadOnly) - this.delegateIterator.previousIndex();
        }

        @Override // java.util.ListIterator
        public T previous() {
            return this.delegateIterator.next();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            ReversedListReadOnly<T> reversedListReadOnly = this.this$0;
            return CollectionsKt__CollectionsKt.getLastIndex(reversedListReadOnly) - this.delegateIterator.nextIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public void set(T t) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ReversedListReadOnly(@NotNull List<? extends T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int i) {
        return this.delegate.get(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.delegate.size();
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<T> iterator() {
        return listIterator(0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public ListIterator<T> listIterator(int i) {
        return new AnonymousClass1(this, i);
    }
}
