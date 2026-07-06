package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TakeWhileSequence<T> implements Sequence<T> {

    @NotNull
    public final Function1<T, Boolean> predicate;

    @NotNull
    public final Sequence<T> sequence;

    /* JADX INFO: renamed from: kotlin.sequences.TakeWhileSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        public final Iterator<T> iterator;
        public T nextItem;
        public int nextState = -1;
        public final /* synthetic */ TakeWhileSequence<T> this$0;

        public AnonymousClass1(TakeWhileSequence<T> takeWhileSequence) {
            this.this$0 = takeWhileSequence;
            this.iterator = takeWhileSequence.sequence.iterator();
        }

        private final void calcNext() {
            if (this.iterator.hasNext()) {
                T next = this.iterator.next();
                if (this.this$0.predicate.invoke(next).booleanValue()) {
                    this.nextState = 1;
                    this.nextItem = next;
                    return;
                }
            }
            this.nextState = 0;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final T getNextItem() {
            return this.nextItem;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState == -1) {
                calcNext();
            }
            return this.nextState == 1;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.nextState == -1) {
                calcNext();
            }
            if (this.nextState == 0) {
                throw new NoSuchElementException();
            }
            T t = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setNextItem(T t) {
            this.nextItem = t;
        }

        public final void setNextState(int i) {
            this.nextState = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TakeWhileSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
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
