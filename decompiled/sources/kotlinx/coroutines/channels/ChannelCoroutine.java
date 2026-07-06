package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ChannelCoroutine<E> extends AbstractCoroutine<Unit> implements Channel<E> {

    @NotNull
    public final Channel<E> _channel;

    public ChannelCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel, boolean z, boolean z2) {
        super(coroutineContext, z, z2);
        this._channel = channel;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void cancel(@Nullable CancellationException cancellationException) throws Throwable {
        if (isCancelled()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    public void cancelInternal(@NotNull Throwable th) throws Throwable {
        CancellationException cancellationException$default = JobSupport.toCancellationException$default(this, th, null, 1, null);
        this._channel.cancel(cancellationException$default);
        cancelImpl$kotlinx_coroutines_core(cancellationException$default);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(@Nullable Throwable th) {
        return this._channel.cancel(th);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceive() {
        return this._channel.getOnReceive();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return this._channel.getOnReceiveCatching();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return this._channel.getOnReceiveOrNull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this._channel.getOnSend();
    }

    @NotNull
    public final Channel<E> get_channel() {
        return this._channel;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @ExperimentalCoroutinesApi
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        this._channel.invokeOnClose(function1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this._channel.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this._channel.isEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public ChannelIterator<E> iterator() {
        return this._channel.iterator();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return this._channel.offer(e);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    @Nullable
    public E poll() {
        return this._channel.poll();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public Object receive(@NotNull Continuation<? super E> continuation) {
        return this._channel.receive(continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    /* JADX INFO: renamed from: receiveCatching-JP2dKIU */
    public Object mo2082receiveCatchingJP2dKIU(@NotNull Continuation<? super ChannelResult<? extends E>> continuation) {
        Object objMo2082receiveCatchingJP2dKIU = this._channel.mo2082receiveCatchingJP2dKIU(continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return objMo2082receiveCatchingJP2dKIU;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    @Nullable
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return this._channel.receiveOrNull(continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        return this._channel.send(e, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    /* JADX INFO: renamed from: tryReceive-PtdJZtk */
    public Object mo2083tryReceivePtdJZtk() {
        return this._channel.mo2083tryReceivePtdJZtk();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo2084trySendJP2dKIU(E e) {
        return this._channel.mo2084trySendJP2dKIU(e);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public /* synthetic */ void cancel() throws Throwable {
        cancelInternal(new JobCancellationException(cancellationExceptionMessage(), null, this));
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final /* synthetic */ boolean cancel(Throwable th) throws Throwable {
        cancelInternal(new JobCancellationException(cancellationExceptionMessage(), null, this));
        return true;
    }

    @NotNull
    public final Channel<E> getChannel() {
        return this;
    }
}
