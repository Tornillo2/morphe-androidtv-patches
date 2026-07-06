package kotlinx.coroutines.channels;

import java.lang.reflect.InvocationTargetException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SendElementWithUndeliveredHandler<E> extends SendElement<E> {

    @JvmField
    @NotNull
    public final Function1<E, Unit> onUndeliveredElement;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElementWithUndeliveredHandler(E e, @NotNull CancellableContinuation<? super Unit> cancellableContinuation, @NotNull Function1<? super E, Unit> function1) {
        super(e, cancellableContinuation);
        this.onUndeliveredElement = function1;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    /* JADX INFO: renamed from: remove */
    public boolean mo2129remove() throws IllegalAccessException, InvocationTargetException {
        if (!super.mo2129remove()) {
            return false;
        }
        undeliveredElement();
        return true;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void undeliveredElement() throws IllegalAccessException, InvocationTargetException {
        OnUndeliveredElementKt.callUndeliveredElement(this.onUndeliveredElement, this.pollResult, this.cont.getContext());
    }
}
