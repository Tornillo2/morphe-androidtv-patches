package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/TakeSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,698:1\n1#2:699\n*E\n"})
public final class TakeSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    public final int count;

    @NotNull
    public final Sequence<T> sequence;

    /* JADX INFO: renamed from: kotlin.sequences.TakeSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        public final Iterator<T> iterator;
        public int left;

        public AnonymousClass1(TakeSequence<T> takeSequence) {
            this.left = takeSequence.count;
            this.iterator = takeSequence.sequence.iterator();
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getLeft() {
            return this.left;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.left > 0 && this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this.left;
            if (i == 0) {
                throw new NoSuchElementException();
            }
            this.left = i - 1;
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setLeft(int i) {
            this.left = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TakeSequence(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.count = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> drop(int i) {
        int i2 = this.count;
        return i >= i2 ? EmptySequence.INSTANCE : new SubSequence(this.sequence, i, i2);
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> take(int i) {
        return i >= this.count ? this : new TakeSequence(this.sequence, i);
    }
}
