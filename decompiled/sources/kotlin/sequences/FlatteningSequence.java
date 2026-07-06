package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FlatteningSequence<T, R, E> implements Sequence<E> {

    @NotNull
    public final Function1<R, Iterator<E>> iterator;

    @NotNull
    public final Sequence<T> sequence;

    @NotNull
    public final Function1<T, R> transformer;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class State {
        public static final int DONE = 2;

        @NotNull
        public static final State INSTANCE = new State();
        public static final int READY = 1;
        public static final int UNDEFINED = 0;
    }

    /* JADX INFO: renamed from: kotlin.sequences.FlatteningSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<E>, KMappedMarker {
        public Iterator<? extends E> itemIterator;
        public final Iterator<T> iterator;
        public int state;
        public final /* synthetic */ FlatteningSequence<T, R, E> this$0;

        public AnonymousClass1(FlatteningSequence<T, R, E> flatteningSequence) {
            this.this$0 = flatteningSequence;
            this.iterator = flatteningSequence.sequence.iterator();
        }

        public final boolean ensureItemIterator() {
            Iterator<? extends E> it = this.itemIterator;
            if (it != null && it.hasNext()) {
                this.state = 1;
                return true;
            }
            while (this.iterator.hasNext()) {
                T next = this.iterator.next();
                FlatteningSequence<T, R, E> flatteningSequence = this.this$0;
                Iterator<? extends E> it2 = (Iterator) flatteningSequence.iterator.invoke(flatteningSequence.transformer.invoke(next));
                if (it2.hasNext()) {
                    this.itemIterator = it2;
                    this.state = 1;
                    return true;
                }
            }
            this.state = 2;
            this.itemIterator = null;
            return false;
        }

        public final Iterator<E> getItemIterator() {
            return this.itemIterator;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getState() {
            return this.state;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            int i = this.state;
            if (i == 1) {
                return true;
            }
            if (i == 2) {
                return false;
            }
            return ensureItemIterator();
        }

        @Override // java.util.Iterator
        public E next() {
            int i = this.state;
            if (i == 2) {
                throw new NoSuchElementException();
            }
            if (i == 0 && !ensureItemIterator()) {
                throw new NoSuchElementException();
            }
            this.state = 0;
            Iterator<? extends E> it = this.itemIterator;
            Intrinsics.checkNotNull(it);
            return it.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setItemIterator(Iterator<? extends E> it) {
            this.itemIterator = it;
        }

        public final void setState(int i) {
            this.state = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FlatteningSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> transformer, @NotNull Function1<? super R, ? extends Iterator<? extends E>> iterator) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        this.sequence = sequence;
        this.transformer = transformer;
        this.iterator = iterator;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<E> iterator() {
        return new AnonymousClass1(this);
    }
}
