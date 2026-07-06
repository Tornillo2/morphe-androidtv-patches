package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyActorCoroutine<E> extends ActorCoroutine<E> implements SelectClause2<E, SendChannel<? super E>> {

    @NotNull
    public Continuation<? super Unit> continuation;

    public LazyActorCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel, @NotNull Function2<? super ActorScope<E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        super(coroutineContext, channel, false);
        this.continuation = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, this, this);
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(@Nullable Throwable th) {
        boolean zCancel = super.cancel(th);
        start();
        return zCancel;
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    public boolean offer(E e) {
        start();
        return super.offer(e);
    }

    @Override // kotlinx.coroutines.JobSupport
    public void onStart() {
        CancellableKt.startCoroutineCancellable(this.continuation, this);
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, E e, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        start();
        this._channel.getOnSend().registerSelectClause2(selectInstance, e, function2);
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        start();
        Object objSend = super.send(e, continuation);
        return objSend == CoroutineSingletons.COROUTINE_SUSPENDED ? objSend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo2084trySendJP2dKIU(E e) {
        start();
        return super.mo2084trySendJP2dKIU(e);
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this;
    }
}
