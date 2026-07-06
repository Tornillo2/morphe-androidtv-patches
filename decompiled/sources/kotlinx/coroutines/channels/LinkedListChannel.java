package kotlinx.coroutines.channels;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LinkedListChannel<E> extends AbstractChannel<E> {
    public LinkedListChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed<?> receiveOrClosedSendBuffered;
        do {
            Object objOfferInternal = super.offerInternal(e);
            Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
            if (objOfferInternal != symbol) {
                if (objOfferInternal != AbstractChannelKt.OFFER_FAILED) {
                    if (objOfferInternal instanceof Closed) {
                        return objOfferInternal;
                    }
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid offerInternal result ", objOfferInternal));
                }
                receiveOrClosedSendBuffered = sendBuffered(e);
                if (receiveOrClosedSendBuffered == null) {
                }
            }
            return symbol;
        } while (!(receiveOrClosedSendBuffered instanceof Closed));
        return receiveOrClosedSendBuffered;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Object objPerformAtomicTrySelect;
        while (true) {
            if (getHasReceiveOrClosed()) {
                objPerformAtomicTrySelect = super.offerSelectInternal(e, selectInstance);
            } else {
                objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(describeSendBuffered(e));
                if (objPerformAtomicTrySelect == null) {
                    objPerformAtomicTrySelect = AbstractChannelKt.OFFER_SUCCESS;
                }
            }
            if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                return SelectKt.ALREADY_SELECTED;
            }
            Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
            if (objPerformAtomicTrySelect == symbol) {
                return symbol;
            }
            if (objPerformAtomicTrySelect != AbstractChannelKt.OFFER_FAILED && objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                if (objPerformAtomicTrySelect instanceof Closed) {
                    return objPerformAtomicTrySelect;
                }
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid result ", objPerformAtomicTrySelect));
            }
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    /* JADX INFO: renamed from: onCancelIdempotentList-w-w6eGU */
    public void mo2081onCancelIdempotentListww6eGU(@NotNull Object obj, @NotNull Closed<?> closed) throws IllegalAccessException, InvocationTargetException {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException = null;
        if (obj != null) {
            if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException2 = null;
                for (int size = arrayList.size() - 1; -1 < size; size--) {
                    Send send = (Send) arrayList.get(size);
                    if (send instanceof AbstractSendChannel.SendBuffered) {
                        Function1<E, Unit> function1 = this.onUndeliveredElement;
                        undeliveredElementExceptionCallUndeliveredElementCatchingException2 = function1 != null ? OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, ((AbstractSendChannel.SendBuffered) send).element, undeliveredElementExceptionCallUndeliveredElementCatchingException2) : null;
                    } else {
                        send.resumeSendClosed(closed);
                    }
                }
                undeliveredElementExceptionCallUndeliveredElementCatchingException = undeliveredElementExceptionCallUndeliveredElementCatchingException2;
            } else {
                Send send2 = (Send) obj;
                if (send2 instanceof AbstractSendChannel.SendBuffered) {
                    Function1<E, Unit> function12 = this.onUndeliveredElement;
                    if (function12 != null) {
                        undeliveredElementExceptionCallUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function12, ((AbstractSendChannel.SendBuffered) send2).element, null);
                    }
                } else {
                    send2.resumeSendClosed(closed);
                }
            }
        }
        if (undeliveredElementExceptionCallUndeliveredElementCatchingException != null) {
            throw undeliveredElementExceptionCallUndeliveredElementCatchingException;
        }
    }
}
