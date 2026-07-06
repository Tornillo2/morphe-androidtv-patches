package kotlinx.coroutines.channels;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractChannel;
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
public class ArrayChannel<E> extends AbstractChannel<E> {

    @NotNull
    public Object[] buffer;
    public final int capacity;
    public int head;

    @NotNull
    public final ReentrantLock lock;

    @NotNull
    public final BufferOverflow onBufferOverflow;

    @NotNull
    private volatile /* synthetic */ int size;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ArrayChannel(int i, @NotNull BufferOverflow bufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (i < 1) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("ArrayChannel capacity must be at least 1, but ", i, " was specified").toString());
        }
        this.lock = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i, 8)];
        ArraysKt___ArraysJvmKt.fill$default(objArr, AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
        this.buffer = objArr;
        this.size = 0;
    }

    private final void ensureCapacity(int i) {
        Object[] objArr = this.buffer;
        if (i >= objArr.length) {
            int iMin = Math.min(objArr.length * 2, this.capacity);
            Object[] objArr2 = new Object[iMin];
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr3 = this.buffer;
                objArr2[i2] = objArr3[(this.head + i2) % objArr3.length];
            }
            Arrays.fill(objArr2, i, iMin, AbstractChannelKt.EMPTY);
            this.buffer = objArr2;
            this.head = 0;
        }
    }

    public final void enqueueElement(int i, E e) {
        if (i < this.capacity) {
            ensureCapacity(i);
            Object[] objArr = this.buffer;
            objArr[(this.head + i) % objArr.length] = e;
        } else {
            Object[] objArr2 = this.buffer;
            int i2 = this.head;
            objArr2[i2 % objArr2.length] = null;
            objArr2[(i + i2) % objArr2.length] = e;
            this.head = (i2 + 1) % objArr2.length;
        }
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
    @Nullable
    public Object enqueueSend(@NotNull Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
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
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
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
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            Symbol symbolUpdateBufferSize = updateBufferSize(i);
            if (symbolUpdateBufferSize != null) {
                return symbolUpdateBufferSize;
            }
            if (i == 0) {
                do {
                    receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed != null) {
                        if (receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed) {
                            this.size = i;
                            return receiveOrClosedTakeFirstReceiveOrPeekClosed;
                        }
                    }
                } while (receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(e, null) == null);
                this.size = i;
                reentrantLock.unlock();
                receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(e);
                return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
            }
            enqueueElement(i, e);
            return AbstractChannelKt.OFFER_SUCCESS;
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
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            Symbol symbolUpdateBufferSize = updateBufferSize(i);
            if (symbolUpdateBufferSize != null) {
                return symbolUpdateBufferSize;
            }
            if (i == 0) {
                do {
                    AbstractSendChannel.TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(e);
                    objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
                    if (objPerformAtomicTrySelect == null) {
                        this.size = i;
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
                this.size = i;
                return objPerformAtomicTrySelect;
            }
            if (selectInstance.trySelect()) {
                enqueueElement(i, e);
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            this.size = i;
            return SelectKt.getALREADY_SELECTED();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean z) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException = null;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = this.buffer[this.head];
                if (function1 != null && obj != AbstractChannelKt.EMPTY) {
                    undeliveredElementExceptionCallUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementExceptionCallUndeliveredElementCatchingException);
                }
                Object[] objArr = this.buffer;
                int i3 = this.head;
                objArr[i3] = AbstractChannelKt.EMPTY;
                this.head = (i3 + 1) % objArr.length;
            }
            this.size = 0;
            reentrantLock.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementExceptionCallUndeliveredElementCatchingException != null) {
                throw undeliveredElementExceptionCallUndeliveredElementCatchingException;
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
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i2 = this.head;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object pollResult = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (i == this.capacity) {
                Send send2 = null;
                while (true) {
                    Send sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (sendTakeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    if (sendTakeFirstSendOrPeekClosed.tryResumeSend(null) != null) {
                        pollResult = sendTakeFirstSendOrPeekClosed.getPollResult();
                        send = sendTakeFirstSendOrPeekClosed;
                        z = true;
                        break;
                    }
                    sendTakeFirstSendOrPeekClosed.undeliveredElement();
                    send2 = sendTakeFirstSendOrPeekClosed;
                }
            }
            if (pollResult != AbstractChannelKt.POLL_FAILED && !(pollResult instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i) % objArr2.length] = pollResult;
            }
            this.head = (this.head + 1) % this.buffer.length;
            if (z) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        boolean z;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i2 = this.head;
            Object obj = objArr[i2];
            Object result = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object pollResult = AbstractChannelKt.POLL_FAILED;
            if (i == this.capacity) {
                while (true) {
                    AbstractChannel.TryPollDesc<E> tryPollDescDescribeTryPoll = describeTryPoll();
                    Object objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(tryPollDescDescribeTryPoll);
                    if (objPerformAtomicTrySelect != null) {
                        if (objPerformAtomicTrySelect == AbstractChannelKt.POLL_FAILED) {
                            break;
                        }
                        if (objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                            if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                                this.size = i;
                                this.buffer[this.head] = obj;
                                return objPerformAtomicTrySelect;
                            }
                            if (!(objPerformAtomicTrySelect instanceof Closed)) {
                                throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                            }
                            pollResult = objPerformAtomicTrySelect;
                            result = pollResult;
                        }
                    } else {
                        result = tryPollDescDescribeTryPoll.getResult();
                        pollResult = ((Send) result).getPollResult();
                        break;
                    }
                }
                z = true;
            } else {
                z = false;
            }
            if (pollResult != AbstractChannelKt.POLL_FAILED && !(pollResult instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i) % objArr2.length] = pollResult;
            } else if (!selectInstance.trySelect()) {
                this.size = i;
                this.buffer[this.head] = obj;
                return SelectKt.getALREADY_SELECTED();
            }
            this.head = (this.head + 1) % this.buffer.length;
            if (z) {
                Intrinsics.checkNotNull(result);
                ((Send) result).completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final Symbol updateBufferSize(int i) {
        if (i < this.capacity) {
            this.size = i + 1;
            return null;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i2 == 1) {
            return AbstractChannelKt.OFFER_FAILED;
        }
        if (i2 == 2) {
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        if (i2 == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }
}
