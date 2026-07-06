package kotlinx.coroutines;

import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class JobKt__FutureKt {
    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel(future));
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        return job.invokeOnCompletion(new CancelFutureOnCompletion(future));
    }
}
