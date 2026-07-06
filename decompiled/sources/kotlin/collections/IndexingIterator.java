package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class IndexingIterator<T> implements Iterator<IndexedValue<? extends T>>, KMappedMarker {
    public int index;

    @NotNull
    public final Iterator<T> iterator;

    /* JADX WARN: Multi-variable type inference failed */
    public IndexingIterator(@NotNull Iterator<? extends T> iterator) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        this.iterator = iterator;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    @NotNull
    public final IndexedValue<T> next() {
        int i = this.index;
        this.index = i + 1;
        if (i >= 0) {
            return new IndexedValue<>(i, this.iterator.next());
        }
        CollectionsKt__CollectionsKt.throwIndexOverflow();
        throw null;
    }
}
