package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class IndexingSequence<T> implements Sequence<IndexedValue<? extends T>> {

    @NotNull
    public final Sequence<T> sequence;

    /* JADX INFO: renamed from: kotlin.sequences.IndexingSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<IndexedValue<? extends T>>, KMappedMarker {
        public int index;
        public final Iterator<T> iterator;

        public AnonymousClass1(IndexingSequence<T> indexingSequence) {
            this.iterator = indexingSequence.sequence.iterator();
        }

        public final int getIndex() {
            return this.index;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        @Override // java.util.Iterator
        public IndexedValue<T> next() {
            int i = this.index;
            this.index = i + 1;
            if (i >= 0) {
                return new IndexedValue<>(i, this.iterator.next());
            }
            CollectionsKt__CollectionsKt.throwIndexOverflow();
            throw null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IndexingSequence(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<IndexedValue<T>> iterator() {
        return new AnonymousClass1(this);
    }
}
