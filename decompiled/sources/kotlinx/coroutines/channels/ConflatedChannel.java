package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
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
public class ConflatedChannel<E> extends AbstractChannel<E> {

    @NotNull
    public final ReentrantLock lock;

    @Nullable
    public Object value;

    public ConflatedChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
        this.lock = new ReentrantLock();
        this.value = AbstractChannelKt.EMPTY;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(@NotNull Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public String getBufferDebugString() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return "(value=" + this.value + ')';
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.value == AbstractChannelKt.EMPTY;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.value == AbstractChannelKt.EMPTY) {
                do {
                    receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed != null) {
                        if (receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed) {
                            return receiveOrClosedTakeFirstReceiveOrPeekClosed;
                        }
                    }
                } while (receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(e, null) == null);
                reentrantLock.unlock();
                receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(e);
                return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
            }
            UndeliveredElementException undeliveredElementExceptionUpdateValueLocked = updateValueLocked(e);
            if (undeliveredElementExceptionUpdateValueLocked == null) {
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            throw undeliveredElementExceptionUpdateValueLocked;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Object objPerformAtomicTrySelect;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.value == AbstractChannelKt.EMPTY) {
                do {
                    AbstractSendChannel.TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(e);
                    objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
                    if (objPerformAtomicTrySelect == null) {
                        ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
                        reentrantLock.unlock();
                        ReceiveOrClosed<? super E> receiveOrClosed = result;
                        receiveOrClosed.completeResumeReceive(e);
                        return receiveOrClosed.getOfferResult();
                    }
                    if (objPerformAtomicTrySelect != AbstractChannelKt.OFFER_FAILED) {
                    }
                } while (objPerformAtomicTrySelect == AtomicKt.RETRY_ATOMIC);
                if (objPerformAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(objPerformAtomicTrySelect instanceof Closed)) {
                    throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                }
                return objPerformAtomicTrySelect;
            }
            if (!selectInstance.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            UndeliveredElementException undeliveredElementExceptionUpdateValueLocked = updateValueLocked(e);
            if (undeliveredElementExceptionUpdateValueLocked == null) {
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            throw undeliveredElementExceptionUpdateValueLocked;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean z) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            UndeliveredElementException undeliveredElementExceptionUpdateValueLocked = updateValueLocked(AbstractChannelKt.EMPTY);
            reentrantLock.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementExceptionUpdateValueLocked != null) {
                throw undeliveredElementExceptionUpdateValueLocked;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    public Object pollInternal() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object obj = this.value;
            Symbol symbol = AbstractChannelKt.EMPTY;
            if (obj != symbol) {
                this.value = symbol;
                return obj;
            }
            Object closedForSend = getClosedForSend();
            if (closedForSend == null) {
                closedForSend = AbstractChannelKt.POLL_FAILED;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object obj = this.value;
            Symbol symbol = AbstractChannelKt.EMPTY;
            if (obj == symbol) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            if (!selectInstance.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            Object obj2 = this.value;
            this.value = symbol;
            return obj2;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final UndeliveredElementException updateValueLocked(Object obj) {
        Function1<E, Unit> function1;
        Object obj2 = this.value;
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default = null;
        if (obj2 != AbstractChannelKt.EMPTY && (function1 = this.onUndeliveredElement) != null) {
            undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj2, null, 2, null);
        }
        this.value = obj;
        return undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
    }
}
