package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ListenableFutureKt$await$2$1 implements Runnable {
    public final /* synthetic */ CancellableContinuation<R> $cancellableContinuation;
    public final /* synthetic */ ListenableFuture<R> $this_await;

    /* JADX WARN: Multi-variable type inference failed */
    public ListenableFutureKt$await$2$1(CancellableContinuation<? super R> cancellableContinuation, ListenableFuture<R> listenableFuture) {
        this.$cancellableContinuation = cancellableContinuation;
        this.$this_await = listenableFuture;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.$cancellableContinuation.resumeWith(this.$this_await.get());
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.$cancellableContinuation.cancel(cause);
            } else {
                this.$cancellableContinuation.resumeWith(ResultKt.createFailure(cause));
            }
        }
    }
}
