package kotlinx.coroutines.channels;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import androidx.activity.ComponentActivity$$ExternalSyntheticNonNull0;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");

    @JvmField
    @Nullable
    public final Function1<E, Unit> onUndeliveredElement;

    @NotNull
    public final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();

    @NotNull
    private volatile /* synthetic */ Object onCloseHandler = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SendSelect<E, R> extends Send implements DisposableHandle {

        @JvmField
        @NotNull
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;

        @JvmField
        @NotNull
        public final AbstractSendChannel<E> channel;
        public final E pollResult;

        @JvmField
        @NotNull
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(E e, @NotNull AbstractSendChannel<E> abstractSendChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = e;
            this.channel = abstractSendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            CancellableKt.startCoroutineCancellable$default(this.block, this.channel, this.select.getCompletion(), null, 4, null);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() throws IllegalAccessException, InvocationTargetException {
            if (mo2129remove()) {
                undeliveredElement();
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public E getPollResult() {
            return this.pollResult;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed<?> closed) {
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + this.pollResult + ")[" + this.channel + ", " + this.select + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(prepareOp);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void undeliveredElement() throws IllegalAccessException, InvocationTargetException {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                OnUndeliveredElementKt.callUndeliveredElement(function1, this.pollResult, ((SelectBuilderImpl) this.select.getCompletion()).uCont.getContext());
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {

        @JvmField
        public final E element;

        public TryOfferDesc(E e, @NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbolTryResumeReceive = ((ReceiveOrClosed) prepareOp.affected).tryResumeReceive(this.element, prepareOp);
            if (symbolTryResumeReceive == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            Object obj = AtomicKt.RETRY_ATOMIC;
            if (symbolTryResumeReceive == obj) {
                return obj;
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSendChannel(@Nullable Function1<? super E, Unit> function1) {
        this.onUndeliveredElement = function1;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(@Nullable Throwable th) {
        boolean z;
        Closed<?> closed = new Closed<>(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            if (prevNode instanceof Closed) {
                z = false;
                break;
            }
            if (prevNode.addNext(closed, lockFreeLinkedListNode)) {
                z = true;
                break;
            }
        }
        if (!z) {
            closed = (Closed) this.queue.getPrevNode();
        }
        helpClose(closed);
        if (z) {
            invokeOnCloseHandler(th);
        }
        return z;
    }

    public final int countQueueSize() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        int i = 0;
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext(); !Intrinsics.areEqual(nextNode, lockFreeLinkedListHead); nextNode = nextNode.getNextNode()) {
            if (ComponentActivity$$ExternalSyntheticNonNull0.m(nextNode)) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E e) {
        return new SendBufferedDesc(this.queue, e);
    }

    @NotNull
    public final TryOfferDesc<E> describeTryOffer(E e) {
        return new TryOfferDesc<>(e, this.queue);
    }

    @Nullable
    public Object enqueueSend(@NotNull final Send send) {
        int iTryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(send) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            @Nullable
            public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode3) {
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.CONDITION_FALSE;
            }
        };
        do {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
            if (prevNode2 instanceof ReceiveOrClosed) {
                return prevNode2;
            }
            iTryCondAddNext = prevNode2.tryCondAddNext(send, lockFreeLinkedListNode2, condAddOp);
            if (iTryCondAddNext == 1) {
                return null;
            }
        } while (iTryCondAddNext != 2);
        return AbstractChannelKt.ENQUEUE_FAILED;
    }

    @NotNull
    public String getBufferDebugString() {
        return "";
    }

    @Nullable
    public final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        Closed<?> closed = nextNode instanceof Closed ? (Closed) nextNode : null;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    @Nullable
    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        Closed<?> closed = prevNode instanceof Closed ? (Closed) prevNode : null;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>(this) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            public final /* synthetic */ AbstractSendChannel<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, E e, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
                this.this$0.registerSelectSend(selectInstance, e, function2);
            }
        };
    }

    @NotNull
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    public final String getQueueDebugStateString() {
        String string;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            string = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            string = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            string = "SendQueued";
        } else {
            string = "UNEXPECTED:" + nextNode;
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode == nextNode) {
            return string;
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(string, ",queueSize=");
        sbM.append(countQueueSize());
        String string2 = sbM.toString();
        if (!(prevNode instanceof Closed)) {
            return string2;
        }
        return string2 + ",closedForSend=" + prevNode;
    }

    public final void helpClose(Closed<?> closed) {
        Object objM2121constructorimpl$default = InlineList.m2121constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            Receive receive = prevNode instanceof Receive ? (Receive) prevNode : null;
            if (receive == null) {
                break;
            } else if (receive.mo2129remove()) {
                objM2121constructorimpl$default = InlineList.m2126plusFjFbRPM(objM2121constructorimpl$default, receive);
            } else {
                receive.helpRemove();
            }
        }
        if (objM2121constructorimpl$default != null) {
            if (!(objM2121constructorimpl$default instanceof ArrayList)) {
                ((Receive) objM2121constructorimpl$default).resumeReceiveClosed(closed);
                return;
            }
            ArrayList arrayList = (ArrayList) objM2121constructorimpl$default;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
            }
        }
    }

    public final Throwable helpCloseAndGetSendException(Closed<?> closed) {
        helpClose(closed);
        return closed.getSendException();
    }

    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, E e, Closed<?> closed) throws IllegalAccessException, InvocationTargetException {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 == null || (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) == null) {
            continuation.resumeWith(ResultKt.createFailure(sendException));
        } else {
            ExceptionsKt__ExceptionsKt.addSuppressed(undeliveredElementExceptionCallUndeliveredElementCatchingException$default, sendException);
            continuation.resumeWith(ResultKt.createFailure(undeliveredElementExceptionCallUndeliveredElementCatchingException$default));
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, function1)) {
            Object obj = this.onCloseHandler;
            if (obj != AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Another handler was already registered: ", obj));
            }
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        function1.invoke(closedForSend.closeCause);
    }

    public final void invokeOnCloseHandler(Throwable th) {
        Symbol symbol;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (symbol = AbstractChannelKt.HANDLER_INVOKED) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, obj, symbol)) {
            return;
        }
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1);
        ((Function1) obj).invoke(th);
    }

    public abstract boolean isBufferAlwaysFull();

    public abstract boolean isBufferFull();

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(E e) throws IllegalAccessException, InvocationTargetException {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        try {
            return SendChannel.DefaultImpls.offer(this, e);
        } catch (Throwable th) {
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            if (function1 == null || (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) == null) {
                throw th;
            }
            ExceptionsKt__ExceptionsKt.addSuppressed(undeliveredElementExceptionCallUndeliveredElementCatchingException$default, th);
            throw undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        }
    }

    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed;
        do {
            receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (receiveOrClosedTakeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } while (receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(e, null) == null);
        receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(e);
        return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
    }

    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(e);
        Object objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
        if (objPerformAtomicTrySelect != null) {
            return objPerformAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
        result.completeResumeReceive(e);
        return result.getOfferResult();
    }

    public final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
        while (!selectInstance.isSelected()) {
            if (isFullImpl()) {
                SendSelect sendSelect = new SendSelect(e, this, selectInstance, function2);
                Object objEnqueueSend = enqueueSend(sendSelect);
                if (objEnqueueSend == null) {
                    selectInstance.disposeOnSelect(sendSelect);
                    return;
                }
                if (objEnqueueSend instanceof Closed) {
                    Throwable thHelpCloseAndGetSendException = helpCloseAndGetSendException(e, (Closed) objEnqueueSend);
                    StackTraceRecoveryKt.recoverStackTrace(thHelpCloseAndGetSendException);
                    throw thHelpCloseAndGetSendException;
                }
                if (objEnqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(objEnqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + objEnqueueSend + ' ').toString());
                }
            }
            Object objOfferSelectInternal = offerSelectInternal(e, selectInstance);
            if (objOfferSelectInternal == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (objOfferSelectInternal != AbstractChannelKt.OFFER_FAILED && objOfferSelectInternal != AtomicKt.RETRY_ATOMIC) {
                if (objOfferSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                    return;
                } else {
                    if (!(objOfferSelectInternal instanceof Closed)) {
                        throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("offerSelectInternal returned ", objOfferSelectInternal));
                    }
                    Throwable thHelpCloseAndGetSendException2 = helpCloseAndGetSendException(e, (Closed) objOfferSelectInternal);
                    StackTraceRecoveryKt.recoverStackTrace(thHelpCloseAndGetSendException2);
                    throw thHelpCloseAndGetSendException2;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public final Object send(E e, @NotNull Continuation<? super Unit> continuation) throws IllegalAccessException, InvocationTargetException {
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        Object objSendSuspend = sendSuspend(e, continuation);
        return objSendSuspend == CoroutineSingletons.COROUTINE_SUSPENDED ? objSendSuspend : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final ReceiveOrClosed<?> sendBuffered(E e) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        SendBuffered sendBuffered = new SendBuffered(e);
        do {
            prevNode = lockFreeLinkedListHead.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0050 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object sendSuspend(E r3, kotlin.coroutines.Continuation<? super kotlin.Unit> r4) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r2 = this;
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r4)
            kotlinx.coroutines.CancellableContinuationImpl r4 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r4)
        L8:
            boolean r0 = r2.isFullImpl()
            if (r0 == 0) goto L48
            kotlin.jvm.functions.Function1<E, kotlin.Unit> r0 = r2.onUndeliveredElement
            if (r0 != 0) goto L18
            kotlinx.coroutines.channels.SendElement r0 = new kotlinx.coroutines.channels.SendElement
            r0.<init>(r3, r4)
            goto L1f
        L18:
            kotlinx.coroutines.channels.SendElementWithUndeliveredHandler r0 = new kotlinx.coroutines.channels.SendElementWithUndeliveredHandler
            kotlin.jvm.functions.Function1<E, kotlin.Unit> r1 = r2.onUndeliveredElement
            r0.<init>(r3, r4, r1)
        L1f:
            java.lang.Object r1 = r2.enqueueSend(r0)
            if (r1 != 0) goto L29
            kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r4, r0)
            goto L63
        L29:
            boolean r0 = r1 instanceof kotlinx.coroutines.channels.Closed
            if (r0 == 0) goto L33
            kotlinx.coroutines.channels.Closed r1 = (kotlinx.coroutines.channels.Closed) r1
            r2.helpCloseAndResumeWithSendException(r4, r3, r1)
            goto L63
        L33:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            if (r1 == r0) goto L48
            boolean r0 = r1 instanceof kotlinx.coroutines.channels.Receive
            if (r0 == 0) goto L3c
            goto L48
        L3c:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "enqueueSend returned "
            java.lang.String r4 = kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0.m(r4, r1)
            r3.<init>(r4)
            throw r3
        L48:
            java.lang.Object r0 = r2.offerInternal(r3)
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS
            if (r0 != r1) goto L56
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4.resumeWith(r3)
            goto L63
        L56:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED
            if (r0 == r1) goto L8
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.Closed
            if (r1 == 0) goto L6f
            kotlinx.coroutines.channels.Closed r0 = (kotlinx.coroutines.channels.Closed) r0
            r2.helpCloseAndResumeWithSendException(r4, r3, r0)
        L63:
            java.lang.Object r3 = r4.getResult()
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L6c
            return r3
        L6c:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L6f:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "offerInternal returned "
            java.lang.String r4 = kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0.m(r4, r0)
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000b, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L2:
            java.lang.Object r1 = r0.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto Ld
        Lb:
            r1 = r2
            goto L26
        Ld:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 != 0) goto L12
            goto Lb
        L12:
            r2 = r1
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L20
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L20
            goto L26
        L20:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L29
        L26:
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            return r1
        L29:
            r2.helpRemovePrev()
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000b, code lost:
    
        r1 = null;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L2:
            java.lang.Object r1 = r0.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto Ld
        Lb:
            r1 = r2
            goto L26
        Ld:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.Send
            if (r3 != 0) goto L12
            goto Lb
        L12:
            r2 = r1
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L20
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L20
            goto L26
        L20:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L29
        L26:
            kotlinx.coroutines.channels.Send r1 = (kotlinx.coroutines.channels.Send) r1
            return r1
        L29:
            r2.helpRemovePrev()
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + ObjectUtils.AT_SIGN + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* JADX INFO: renamed from: trySend-JP2dKIU, reason: not valid java name */
    public final Object mo2084trySendJP2dKIU(E e) {
        Object objOfferInternal = offerInternal(e);
        if (objOfferInternal == AbstractChannelKt.OFFER_SUCCESS) {
            ChannelResult.Companion companion = ChannelResult.Companion;
            Unit unit = Unit.INSTANCE;
            companion.getClass();
            return unit;
        }
        if (objOfferInternal != AbstractChannelKt.OFFER_FAILED) {
            if (!(objOfferInternal instanceof Closed)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("trySend returned ", objOfferInternal));
            }
            ChannelResult.Companion companion2 = ChannelResult.Companion;
            Closed<?> closed = (Closed) objOfferInternal;
            helpClose(closed);
            Throwable sendException = closed.getSendException();
            companion2.getClass();
            return new ChannelResult.Closed(sendException);
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null) {
            ChannelResult.Companion.getClass();
            return ChannelResult.failed;
        }
        ChannelResult.Companion companion3 = ChannelResult.Companion;
        helpClose(closedForSend);
        Throwable sendException2 = closedForSend.getSendException();
        companion3.getClass();
        return new ChannelResult.Closed(sendException2);
    }

    public final Throwable helpCloseAndGetSendException(E e, Closed<?> closed) throws IllegalAccessException, InvocationTargetException {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        helpClose(closed);
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            ExceptionsKt__ExceptionsKt.addSuppressed(undeliveredElementExceptionCallUndeliveredElementCatchingException$default, closed.getSendException());
            throw undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        }
        return closed.getSendException();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SendBuffered<E> extends Send {

        @JvmField
        public final E element;

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Object getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.element + ')';
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return symbol;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed<?> closed) {
        }
    }

    public void onClosedIdempotent(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
    }
}
