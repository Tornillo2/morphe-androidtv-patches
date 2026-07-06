package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class GeneratorSequence<T> implements Sequence<T> {

    @NotNull
    public final Function0<T> getInitialValue;

    @NotNull
    public final Function1<T, T> getNextValue;

    /* JADX INFO: renamed from: kotlin.sequences.GeneratorSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        public T nextItem;
        public int nextState = -2;
        public final /* synthetic */ GeneratorSequence<T> this$0;

        public AnonymousClass1(GeneratorSequence<T> generatorSequence) {
            this.this$0 = generatorSequence;
        }

        private final void calcNext() {
            T tInvoke;
            if (this.nextState == -2) {
                tInvoke = this.this$0.getInitialValue.invoke();
            } else {
                Function1<T, T> function1 = this.this$0.getNextValue;
                T t = this.nextItem;
                Intrinsics.checkNotNull(t);
                tInvoke = function1.invoke(t);
            }
            this.nextItem = tInvoke;
            this.nextState = tInvoke == null ? 0 : 1;
        }

        public final T getNextItem() {
            return this.nextItem;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState < 0) {
                calcNext();
            }
            return this.nextState == 1;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.nextState < 0) {
                calcNext();
            }
            if (this.nextState == 0) {
                throw new NoSuchElementException();
            }
            T t = this.nextItem;
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
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
    public GeneratorSequence(@NotNull Function0<? extends T> getInitialValue, @NotNull Function1<? super T, ? extends T> getNextValue) {
        Intrinsics.checkNotNullParameter(getInitialValue, "getInitialValue");
        Intrinsics.checkNotNullParameter(getNextValue, "getNextValue");
        this.getInitialValue = getInitialValue;
        this.getNextValue = getNextValue;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
