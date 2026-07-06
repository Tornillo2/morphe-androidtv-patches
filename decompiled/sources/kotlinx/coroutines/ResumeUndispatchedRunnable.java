package kotlinx.coroutines;

import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ResumeUndispatchedRunnable implements Runnable {

    @NotNull
    public final CancellableContinuation<Unit> continuation;

    @NotNull
    public final CoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeUndispatchedRunnable(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.dispatcher = coroutineDispatcher;
        this.continuation = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.continuation.resumeUndispatched(this.dispatcher, Unit.INSTANCE);
    }
}
