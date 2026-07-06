package androidx.core.util;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ContinuationRunnable extends AtomicBoolean implements Runnable {

    @NotNull
    public final Continuation<Unit> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationRunnable(@NotNull Continuation<? super Unit> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(Unit.INSTANCE);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    @NotNull
    public String toString() {
        return "ContinuationRunnable(ran = " + get() + ')';
    }
}
