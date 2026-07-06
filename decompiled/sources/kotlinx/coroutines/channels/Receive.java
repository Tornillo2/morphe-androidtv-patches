package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Receive<E> extends LockFreeLinkedListNode implements ReceiveOrClosed<E> {
    @Nullable
    public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
        return null;
    }

    public abstract void resumeReceiveClosed(@NotNull Closed<?> closed);

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @NotNull
    public Symbol getOfferResult() {
        return AbstractChannelKt.OFFER_SUCCESS;
    }
}
