package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ProducerCoroutine<E> extends ChannelCoroutine<E> implements ProducerScope<E> {
    public ProducerCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel) {
        super(coroutineContext, channel, true, true);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCancelled(@NotNull Throwable th, boolean z) {
        if (this._channel.cancel(th) || z) {
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, th);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(@NotNull Unit unit) {
        SendChannel.DefaultImpls.close$default(this._channel, null, 1, null);
    }

    @Override // kotlinx.coroutines.channels.ProducerScope
    public SendChannel getChannel() {
        return this;
    }
}
