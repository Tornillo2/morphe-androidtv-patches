package kotlinx.coroutines.channels;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {

    @NotNull
    private volatile /* synthetic */ long _head;

    @NotNull
    private volatile /* synthetic */ int _size;

    @NotNull
    private volatile /* synthetic */ long _tail;

    @NotNull
    public final Object[] buffer;

    @NotNull
    public final ReentrantLock bufferLock;
    public final int capacity;

    @NotNull
    public final List<Subscriber<E>> subscribers;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {

        @NotNull
        private volatile /* synthetic */ long _subHead;

        @NotNull
        public final ArrayBroadcastChannel<E> broadcastChannel;

        @NotNull
        public final ReentrantLock subLock;

        public Subscriber(@NotNull ArrayBroadcastChannel<E> arrayBroadcastChannel) {
            super(null);
            this.broadcastChannel = arrayBroadcastChannel;
            this.subLock = new ReentrantLock();
            this._subHead = 0L;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
        
            r2 = (kotlinx.coroutines.channels.Closed) r1;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean checkOffer() {
            /*
                r8 = this;
                r0 = 0
            L1:
                boolean r1 = r8.needsToCheckOfferWithoutLock()
                r2 = 0
                if (r1 == 0) goto L57
                java.util.concurrent.locks.ReentrantLock r1 = r8.subLock
                boolean r1 = r1.tryLock()
                if (r1 == 0) goto L57
                java.lang.Object r1 = r8.peekUnderLock()     // Catch: java.lang.Throwable -> L2b
                kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> L2b
                if (r1 != r3) goto L1e
            L18:
                java.util.concurrent.locks.ReentrantLock r1 = r8.subLock
                r1.unlock()
                goto L1
            L1e:
                boolean r3 = r1 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L2b
                if (r3 == 0) goto L2d
                r2 = r1
                kotlinx.coroutines.channels.Closed r2 = (kotlinx.coroutines.channels.Closed) r2     // Catch: java.lang.Throwable -> L2b
            L25:
                java.util.concurrent.locks.ReentrantLock r1 = r8.subLock
                r1.unlock()
                goto L57
            L2b:
                r0 = move-exception
                goto L51
            L2d:
                kotlinx.coroutines.channels.ReceiveOrClosed r3 = r8.takeFirstReceiveOrPeekClosed()     // Catch: java.lang.Throwable -> L2b
                if (r3 != 0) goto L34
                goto L25
            L34:
                boolean r4 = r3 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L2b
                if (r4 == 0) goto L39
                goto L25
            L39:
                kotlinx.coroutines.internal.Symbol r2 = r3.tryResumeReceive(r1, r2)     // Catch: java.lang.Throwable -> L2b
                if (r2 != 0) goto L40
                goto L18
            L40:
                long r4 = r8._subHead     // Catch: java.lang.Throwable -> L2b
                r6 = 1
                long r4 = r4 + r6
                r8._subHead = r4     // Catch: java.lang.Throwable -> L2b
                java.util.concurrent.locks.ReentrantLock r0 = r8.subLock
                r0.unlock()
                r3.completeResumeReceive(r1)
                r0 = 1
                goto L1
            L51:
                java.util.concurrent.locks.ReentrantLock r1 = r8.subLock
                r1.unlock()
                throw r0
            L57:
                if (r2 == 0) goto L5e
                java.lang.Throwable r1 = r2.closeCause
                r8.close(r1)
            L5e:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber.checkOffer():boolean");
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
        public boolean close(@Nullable Throwable th) {
            boolean zClose = super.close(th);
            if (!zClose) {
                return zClose;
            }
            ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, this, 1, null);
            ReentrantLock reentrantLock = this.subLock;
            reentrantLock.lock();
            try {
                this._subHead = ((ArrayBroadcastChannel) this.broadcastChannel)._tail;
                return zClose;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final long getSubHead() {
            return this._subHead;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        public boolean isBufferAlwaysEmpty() {
            return false;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        public boolean isBufferAlwaysFull() {
            throw new IllegalStateException("Should not be used");
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        public boolean isBufferEmpty() {
            return this._subHead >= ((ArrayBroadcastChannel) this.broadcastChannel)._tail;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        public boolean isBufferFull() {
            throw new IllegalStateException("Should not be used");
        }

        public final boolean needsToCheckOfferWithoutLock() {
            if (getClosedForReceive() != null) {
                return false;
            }
            return (isBufferEmpty() && this.broadcastChannel.getClosedForReceive() == null) ? false : true;
        }

        public final Object peekUnderLock() {
            long j = this._subHead;
            Closed<?> closedForReceive = this.broadcastChannel.getClosedForReceive();
            if (j < ((ArrayBroadcastChannel) this.broadcastChannel)._tail) {
                E eElementAt = this.broadcastChannel.elementAt(j);
                Closed<?> closedForReceive2 = getClosedForReceive();
                return closedForReceive2 != null ? closedForReceive2 : eElementAt;
            }
            if (closedForReceive != null) {
                return closedForReceive;
            }
            Closed<?> closedForReceive3 = getClosedForReceive();
            return closedForReceive3 == null ? AbstractChannelKt.POLL_FAILED : closedForReceive3;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        @Nullable
        public Object pollInternal() {
            boolean z;
            ReentrantLock reentrantLock = this.subLock;
            reentrantLock.lock();
            try {
                Object objPeekUnderLock = peekUnderLock();
                if ((objPeekUnderLock instanceof Closed) || objPeekUnderLock == AbstractChannelKt.POLL_FAILED) {
                    z = false;
                } else {
                    this._subHead++;
                    z = true;
                }
                reentrantLock.unlock();
                Closed closed = objPeekUnderLock instanceof Closed ? (Closed) objPeekUnderLock : null;
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer() ? true : z) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return objPeekUnderLock;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        @Nullable
        public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
            ReentrantLock reentrantLock = this.subLock;
            reentrantLock.lock();
            try {
                Object objPeekUnderLock = peekUnderLock();
                boolean z = false;
                if (!(objPeekUnderLock instanceof Closed) && objPeekUnderLock != AbstractChannelKt.POLL_FAILED) {
                    if (selectInstance.trySelect()) {
                        this._subHead++;
                        z = true;
                    } else {
                        objPeekUnderLock = SelectKt.getALREADY_SELECTED();
                    }
                }
                reentrantLock.unlock();
                Closed closed = objPeekUnderLock instanceof Closed ? (Closed) objPeekUnderLock : null;
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (checkOffer() ? true : z) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return objPeekUnderLock;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        public final void setSubHead(long j) {
            this._subHead = j;
        }
    }

    public ArrayBroadcastChannel(int i) {
        super(null);
        this.capacity = i;
        if (i < 1) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("ArrayBroadcastChannel capacity must be at least 1, but ", i, " was specified").toString());
        }
        this.bufferLock = new ReentrantLock();
        this.buffer = new Object[i];
        this._head = 0L;
        this._tail = 0L;
        this._size = 0;
        this.subscribers = ConcurrentKt.subscriberList();
    }

    private final int getSize() {
        return this._size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateHead$default(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i, Object obj) {
        if ((i & 1) != 0) {
            subscriber = null;
        }
        if ((i & 2) != 0) {
            subscriber2 = null;
        }
        arrayBroadcastChannel.updateHead(subscriber, subscriber2);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* JADX INFO: renamed from: cancelInternal, reason: merged with bridge method [inline-methods] */
    public final boolean cancel(Throwable th) {
        boolean zClose = close(th);
        Iterator<Subscriber<E>> it = this.subscribers.iterator();
        while (it.hasNext()) {
            it.next().cancel(th);
        }
        return zClose;
    }

    public final void checkSubOffers() {
        Iterator<Subscriber<E>> it = this.subscribers.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            if (it.next().checkOffer()) {
                z = true;
            }
            z2 = true;
        }
        if (z || !z2) {
            updateHead$default(this, null, null, 3, null);
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(@Nullable Throwable th) {
        if (!super.close(th)) {
            return false;
        }
        checkSubOffers();
        return true;
    }

    public final long computeMinHead() {
        Iterator<Subscriber<E>> it = this.subscribers.iterator();
        long j = Long.MAX_VALUE;
        while (it.hasNext()) {
            long subHead = it.next().getSubHead();
            if (j > subHead) {
                j = subHead;
            }
        }
        return j;
    }

    public final E elementAt(long j) {
        return (E) this.buffer[(int) (j % ((long) this.capacity))];
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.buffer.length + ",size=" + this._size + ')';
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final long getHead() {
        return this._head;
    }

    public final long getTail() {
        return this._tail;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public boolean isBufferFull() {
        return this._size >= this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E e) {
        ReentrantLock reentrantLock = this.bufferLock;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int i = this._size;
            if (i >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            long j = this._tail;
            this.buffer[(int) (j % ((long) this.capacity))] = e;
            this._size = i + 1;
            this._tail = j + 1;
            reentrantLock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        ReentrantLock reentrantLock = this.bufferLock;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int i = this._size;
            if (i >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            if (!selectInstance.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            long j = this._tail;
            this.buffer[(int) (j % ((long) this.capacity))] = e;
            this._size = i + 1;
            this._tail = j + 1;
            reentrantLock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Subscriber subscriber = new Subscriber(this);
        updateHead$default(this, subscriber, null, 2, null);
        return subscriber;
    }

    public final void setHead(long j) {
        this._head = j;
    }

    public final void setSize(int i) {
        this._size = i;
    }

    public final void setTail(long j) {
        this._tail = j;
    }

    public final void updateHead(Subscriber<E> subscriber, Subscriber<E> subscriber2) {
        Send sendTakeFirstSendOrPeekClosed;
        while (true) {
            ReentrantLock reentrantLock = this.bufferLock;
            reentrantLock.lock();
            if (subscriber != null) {
                try {
                    subscriber.setSubHead(this._tail);
                    boolean zIsEmpty = this.subscribers.isEmpty();
                    this.subscribers.add(subscriber);
                    if (!zIsEmpty) {
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (subscriber2 != null) {
                this.subscribers.remove(subscriber2);
                if (this._head != subscriber2.getSubHead()) {
                    return;
                }
            }
            long jComputeMinHead = computeMinHead();
            long j = this._tail;
            long j2 = this._head;
            if (jComputeMinHead > j) {
                jComputeMinHead = j;
            }
            if (jComputeMinHead <= j2) {
                return;
            }
            int i = this._size;
            while (j2 < jComputeMinHead) {
                Object[] objArr = this.buffer;
                int i2 = this.capacity;
                objArr[(int) (j2 % ((long) i2))] = null;
                boolean z = i >= i2;
                j2++;
                this._head = j2;
                int i3 = i - 1;
                this._size = i3;
                if (z) {
                    do {
                        sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                        if (sendTakeFirstSendOrPeekClosed != null && !(sendTakeFirstSendOrPeekClosed instanceof Closed)) {
                        }
                    } while (sendTakeFirstSendOrPeekClosed.tryResumeSend(null) == null);
                    this.buffer[(int) (j % ((long) this.capacity))] = sendTakeFirstSendOrPeekClosed.getPollResult();
                    this._size = i;
                    this._tail = j + 1;
                    reentrantLock.unlock();
                    sendTakeFirstSendOrPeekClosed.completeResumeSend();
                    checkSubOffers();
                    subscriber = null;
                    subscriber2 = null;
                }
                i = i3;
            }
            return;
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }

    public static /* synthetic */ void getSubscribers$annotations() {
    }
}
