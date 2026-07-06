package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalCoroutinesApi
public final class SendingCollector<T> implements FlowCollector<T> {

    @NotNull
    public final SendChannel<T> channel;

    /* JADX WARN: Multi-variable type inference failed */
    public SendingCollector(@NotNull SendChannel<? super T> sendChannel) {
        this.channel = sendChannel;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
        Object objSend = this.channel.send(t, continuation);
        return objSend == CoroutineSingletons.COROUTINE_SUSPENDED ? objSend : Unit.INSTANCE;
    }
}
