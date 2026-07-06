package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ReversedList<T> extends AbstractMutableList<T> {

    @NotNull
    public final List<T> delegate;

    /* JADX INFO: renamed from: kotlin.collections.ReversedList$listIterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements ListIterator<T>, KMutableListIterator {
        public final ListIterator<T> delegateIterator;
        public final /* synthetic */ ReversedList<T> this$0;

        public AnonymousClass1(ReversedList<T> reversedList, int i) {
            this.this$0 = reversedList;
            this.delegateIterator = reversedList.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedList, i));
        }

        @Override // java.util.ListIterator
        public void add(T t) {
            this.delegateIterator.add(t);
            this.delegateIterator.previous();
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
            ReversedList<T> reversedList = this.this$0;
            return CollectionsKt__CollectionsKt.getLastIndex(reversedList) - this.delegateIterator.previousIndex();
        }

        @Override // java.util.ListIterator
        public T previous() {
            return this.delegateIterator.next();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            ReversedList<T> reversedList = this.this$0;
            return CollectionsKt__CollectionsKt.getLastIndex(reversedList) - this.delegateIterator.nextIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            this.delegateIterator.remove();
        }

        @Override // java.util.ListIterator
        public void set(T t) {
            this.delegateIterator.set(t);
        }
    }

    public ReversedList(@NotNull List<T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        this.delegate.add(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(this, i), t);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        return this.delegate.get(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.delegate.size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<T> iterator() {
        return new AnonymousClass1(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    @NotNull
    public ListIterator<T> listIterator() {
        return new AnonymousClass1(this, 0);
    }

    @Override // kotlin.collections.AbstractMutableList
    public T removeAt(int i) {
        return this.delegate.remove(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i));
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        return this.delegate.set(CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, i), t);
    }

    @Override // java.util.AbstractList, java.util.List
    @NotNull
    public ListIterator<T> listIterator(int i) {
        return new AnonymousClass1(this, i);
    }
}
