package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ReceiveOrClosed<E> {
    void completeResumeReceive(E e);

    @NotNull
    Object getOfferResult();

    @Nullable
    Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp);
}
