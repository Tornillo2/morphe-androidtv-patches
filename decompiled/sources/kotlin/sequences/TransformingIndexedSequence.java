package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TransformingIndexedSequence<T, R> implements Sequence<R> {

    @NotNull
    public final Sequence<T> sequence;

    @NotNull
    public final Function2<Integer, T, R> transformer;

    /* JADX INFO: renamed from: kotlin.sequences.TransformingIndexedSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<R>, KMappedMarker {
        public int index;
        public final Iterator<T> iterator;
        public final /* synthetic */ TransformingIndexedSequence<T, R> this$0;

        public AnonymousClass1(TransformingIndexedSequence<T, R> transformingIndexedSequence) {
            this.this$0 = transformingIndexedSequence;
            this.iterator = transformingIndexedSequence.sequence.iterator();
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
        public R next() {
            Function2<Integer, T, R> function2 = this.this$0.transformer;
            int i = this.index;
            this.index = i + 1;
            if (i >= 0) {
                return (R) function2.invoke(Integer.valueOf(i), this.iterator.next());
            }
            CollectionsKt__CollectionsKt.throwIndexOverflow();
            throw null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TransformingIndexedSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends R> transformer) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        this.sequence = sequence;
        this.transformer = transformer;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<R> iterator() {
        return new AnonymousClass1(this);
    }
}
