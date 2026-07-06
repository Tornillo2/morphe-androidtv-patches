package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ActorCoroutine<E> extends ChannelCoroutine<E> implements ActorScope<E> {
    public ActorCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel, boolean z) {
        super(coroutineContext, channel, false, z);
        initParentJob((Job) coroutineContext.get(Job.Key));
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean handleJobException(@NotNull Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, th);
        return true;
    }

    @Override // kotlinx.coroutines.JobSupport
    public void onCancelling(@Nullable Throwable th) {
        Channel<E> channel = this._channel;
        if (th != null) {
            CancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            if (CancellationException == null) {
                CancellationException = ExceptionsKt.CancellationException(getClass().getSimpleName().concat(" was cancelled"), th);
            }
        }
        channel.cancel(CancellationException);
    }
}
