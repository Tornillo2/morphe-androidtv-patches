package kotlinx.coroutines.channels;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Closed<E> extends Send implements ReceiveOrClosed<E> {

    @JvmField
    @Nullable
    public final Throwable closeCause;

    public Closed(@Nullable Throwable th) {
        this.closeCause = th;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public Object getOfferResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    public Object getPollResult() {
        return this;
    }

    @NotNull
    public final Throwable getReceiveException() {
        Throwable th = this.closeCause;
        return th == null ? new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
    }

    @NotNull
    public final Throwable getSendException() {
        Throwable th = this.closeCause;
        return th == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "Closed@" + DebugStringsKt.getHexAddress(this) + AbstractJsonLexerKt.BEGIN_LIST + this.closeCause + AbstractJsonLexerKt.END_LIST;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @NotNull
    public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
        Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
        if (prepareOp != null) {
            prepareOp.finishPrepare();
        }
        return symbol;
    }

    @Override // kotlinx.coroutines.channels.Send
    @NotNull
    public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
        Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
        if (prepareOp != null) {
            prepareOp.finishPrepare();
        }
        return symbol;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @NotNull
    public Closed<E> getOfferResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    @NotNull
    public Closed<E> getPollResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public void completeResumeReceive(E e) {
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(@NotNull Closed<?> closed) {
    }
}
