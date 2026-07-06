package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DropWhileSequence<T> implements Sequence<T> {

    @NotNull
    public final Function1<T, Boolean> predicate;

    @NotNull
    public final Sequence<T> sequence;

    /* JADX INFO: renamed from: kotlin.sequences.DropWhileSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        public int dropState = -1;
        public final Iterator<T> iterator;
        public T nextItem;
        public final /* synthetic */ DropWhileSequence<T> this$0;

        public AnonymousClass1(DropWhileSequence<T> dropWhileSequence) {
            this.this$0 = dropWhileSequence;
            this.iterator = dropWhileSequence.sequence.iterator();
        }

        private final void drop() {
            while (this.iterator.hasNext()) {
                T next = this.iterator.next();
                if (!this.this$0.predicate.invoke(next).booleanValue()) {
                    this.nextItem = next;
                    this.dropState = 1;
                    return;
                }
            }
            this.dropState = 0;
        }

        public final int getDropState() {
            return this.dropState;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final T getNextItem() {
            return this.nextItem;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.dropState == -1) {
                drop();
            }
            return this.dropState == 1 || this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.dropState == -1) {
                drop();
            }
            if (this.dropState != 1) {
                return this.iterator.next();
            }
            T t = this.nextItem;
            this.nextItem = null;
            this.dropState = 0;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setDropState(int i) {
            this.dropState = i;
        }

        public final void setNextItem(T t) {
            this.nextItem = t;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DropWhileSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
