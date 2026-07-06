package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MergingSequence<T1, T2, V> implements Sequence<V> {

    @NotNull
    public final Sequence<T1> sequence1;

    @NotNull
    public final Sequence<T2> sequence2;

    @NotNull
    public final Function2<T1, T2, V> transform;

    /* JADX INFO: renamed from: kotlin.sequences.MergingSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<V>, KMappedMarker {
        public final Iterator<T1> iterator1;
        public final Iterator<T2> iterator2;
        public final /* synthetic */ MergingSequence<T1, T2, V> this$0;

        public AnonymousClass1(MergingSequence<T1, T2, V> mergingSequence) {
            this.this$0 = mergingSequence;
            this.iterator1 = mergingSequence.sequence1.iterator();
            this.iterator2 = mergingSequence.sequence2.iterator();
        }

        public final Iterator<T1> getIterator1() {
            return this.iterator1;
        }

        public final Iterator<T2> getIterator2() {
            return this.iterator2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator1.hasNext() && this.iterator2.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return (V) this.this$0.transform.invoke(this.iterator1.next(), this.iterator2.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MergingSequence(@NotNull Sequence<? extends T1> sequence1, @NotNull Sequence<? extends T2> sequence2, @NotNull Function2<? super T1, ? super T2, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sequence1, "sequence1");
        Intrinsics.checkNotNullParameter(sequence2, "sequence2");
        Intrinsics.checkNotNullParameter(transform, "transform");
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.transform = transform;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<V> iterator() {
        return new AnonymousClass1(this);
    }
}
