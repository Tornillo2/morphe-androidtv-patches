package androidx.core.util;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidXContinuationConsumer<T> extends AtomicBoolean implements Consumer<T> {

    @NotNull
    public final Continuation<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidXContinuationConsumer(@NotNull Continuation<? super T> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // androidx.core.util.Consumer
    public void accept(T t) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(t);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    @NotNull
    public String toString() {
        return "ContinuationConsumer(resultAccepted = " + get() + ')';
    }
}
