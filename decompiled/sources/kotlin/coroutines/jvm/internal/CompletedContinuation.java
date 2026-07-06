package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CompletedContinuation implements Continuation<Object> {

    @NotNull
    public static final CompletedContinuation INSTANCE = new CompletedContinuation();

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    @NotNull
    public String toString() {
        return "This continuation is already complete";
    }
}
