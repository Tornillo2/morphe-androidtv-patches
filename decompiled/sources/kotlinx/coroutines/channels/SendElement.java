package kotlinx.coroutines.channels;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SendElement<E> extends Send {

    @JvmField
    @NotNull
    public final CancellableContinuation<Unit> cont;
    public final E pollResult;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElement(E e, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.pollResult = e;
        this.cont = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    public E getPollResult() {
        return this.pollResult;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(@NotNull Closed<?> closed) {
        this.cont.resumeWith(ResultKt.createFailure(closed.getSendException()));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return getClass().getSimpleName() + ObjectUtils.AT_SIGN + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ')';
    }

    @Override // kotlinx.coroutines.channels.Send
    @Nullable
    public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
        if (this.cont.tryResume(Unit.INSTANCE, prepareOp != null ? prepareOp.desc : null) == null) {
            return null;
        }
        if (prepareOp != null) {
            prepareOp.finishPrepare();
        }
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
