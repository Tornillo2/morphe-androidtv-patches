package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Itr<E> implements ChannelIterator<E> {

        @JvmField
        @NotNull
        public final AbstractChannel<E> channel;

        @Nullable
        public Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            this.channel = abstractChannel;
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            Object obj = this.result;
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            if (obj != symbol) {
                return Boolean.valueOf(hasNextResult(obj));
            }
            Object objPollInternal = this.channel.pollInternal();
            this.result = objPollInternal;
            return objPollInternal != symbol ? Boolean.valueOf(hasNextResult(objPollInternal)) : hasNextSuspend(continuation);
        }

        public final boolean hasNextResult(Object obj) throws Throwable {
            if (!(obj instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) obj;
            if (closed.closeCause == null) {
                return false;
            }
            Throwable receiveException = closed.getReceiveException();
            StackTraceRecoveryKt.recoverStackTrace(receiveException);
            throw receiveException;
        }

        public final Object hasNextSuspend(Continuation<? super Boolean> continuation) {
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, orCreateCancellableContinuation);
            while (true) {
                if (this.channel.enqueueReceiveInternal(receiveHasNext)) {
                    this.channel.removeReceiveOnCancel(orCreateCancellableContinuation, receiveHasNext);
                    break;
                }
                Object objPollInternal = this.channel.pollInternal();
                this.result = objPollInternal;
                if (objPollInternal instanceof Closed) {
                    Closed closed = (Closed) objPollInternal;
                    if (closed.closeCause == null) {
                        orCreateCancellableContinuation.resumeWith(Boolean.FALSE);
                    } else {
                        orCreateCancellableContinuation.resumeWith(ResultKt.createFailure(closed.getReceiveException()));
                    }
                } else if (objPollInternal != AbstractChannelKt.POLL_FAILED) {
                    Boolean bool = Boolean.TRUE;
                    Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
                    orCreateCancellableContinuation.resume(bool, function1 != null ? new OnUndeliveredElementKt.AnonymousClass1(function1, objPollInternal, orCreateCancellableContinuation.getContext()) : null);
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            return result;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = "next")
        public /* synthetic */ Object next(Continuation continuation) {
            return ChannelIterator.DefaultImpls.next(this, continuation);
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() throws Throwable {
            E e = (E) this.result;
            if (e instanceof Closed) {
                Throwable receiveException = ((Closed) e).getReceiveException();
                StackTraceRecoveryKt.recoverStackTrace(receiveException);
                throw receiveException;
            }
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            if (e == symbol) {
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
            this.result = symbol;
            return e;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ReceiveElement<E> extends Receive<E> {

        @JvmField
        @NotNull
        public final CancellableContinuation<Object> cont;

        @JvmField
        public final int receiveMode;

        public ReceiveElement(@NotNull CancellableContinuation<Object> cancellableContinuation, int i) {
            this.cont = cancellableContinuation;
            this.receiveMode = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            if (this.receiveMode != 1) {
                this.cont.resumeWith(ResultKt.createFailure(closed.getReceiveException()));
                return;
            }
            CancellableContinuation<Object> cancellableContinuation = this.cont;
            ChannelResult.Companion companion = ChannelResult.Companion;
            Throwable th = closed.closeCause;
            companion.getClass();
            cancellableContinuation.resumeWith(new ChannelResult(new ChannelResult.Closed(th)));
        }

        @Nullable
        public final Object resumeValue(E e) {
            if (this.receiveMode != 1) {
                return e;
            }
            ChannelResult.Companion.getClass();
            return new ChannelResult(e);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.receiveMode + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            if (this.cont.tryResume(resumeValue(e), prepareOp != null ? prepareOp.desc : null, resumeOnCancellationFun(e)) == null) {
                return null;
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReceiveElementWithUndeliveredHandler<E> extends ReceiveElement<E> {

        @JvmField
        @NotNull
        public final Function1<E, Unit> onUndeliveredElement;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveElementWithUndeliveredHandler(@NotNull CancellableContinuation<Object> cancellableContinuation, int i, @NotNull Function1<? super E, Unit> function1) {
            super(cancellableContinuation, i);
            this.onUndeliveredElement = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            return new OnUndeliveredElementKt.AnonymousClass1(this.onUndeliveredElement, e, this.cont.getContext());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ReceiveHasNext<E> extends Receive<E> {

        @JvmField
        @NotNull
        public final CancellableContinuation<Boolean> cont;

        @JvmField
        @NotNull
        public final Itr<E> iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveHasNext(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            this.iterator.result = e;
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            Function1<E, Unit> function1 = this.iterator.channel.onUndeliveredElement;
            if (function1 != null) {
                return new OnUndeliveredElementKt.AnonymousClass1(function1, e, this.cont.getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object objTryResume$default = closed.closeCause == null ? CancellableContinuation.DefaultImpls.tryResume$default(this.cont, Boolean.FALSE, null, 2, null) : this.cont.tryResumeWithException(closed.getReceiveException());
            if (objTryResume$default != null) {
                this.iterator.result = closed;
                this.cont.completeResume(objTryResume$default);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveHasNext@" + DebugStringsKt.getHexAddress(this);
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            if (this.cont.tryResume(Boolean.TRUE, prepareOp != null ? prepareOp.desc : null, resumeOnCancellationFun(e)) == null) {
                return null;
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {

        @JvmField
        @NotNull
        public final Function2<Object, Continuation<? super R>, Object> block;

        @JvmField
        @NotNull
        public final AbstractChannel<E> channel;

        @JvmField
        public final int receiveMode;

        @JvmField
        @NotNull
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveSelect(@NotNull AbstractChannel<E> abstractChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
            this.channel = abstractChannel;
            this.select = selectInstance;
            this.block = function2;
            this.receiveMode = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            Object channelResult;
            Function2<Object, Continuation<? super R>, Object> function2 = this.block;
            if (this.receiveMode == 1) {
                ChannelResult.Companion.getClass();
                channelResult = new ChannelResult(e);
            } else {
                channelResult = e;
            }
            CancellableKt.startCoroutineCancellable(function2, channelResult, this.select.getCompletion(), resumeOnCancellationFun(e));
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (mo2129remove()) {
                this.channel.getClass();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                return new OnUndeliveredElementKt.AnonymousClass1(function1, e, ((SelectBuilderImpl) this.select.getCompletion()).uCont.getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            if (this.select.trySelect()) {
                int i = this.receiveMode;
                if (i == 0) {
                    this.select.resumeSelectWithException(closed.getReceiveException());
                    return;
                }
                if (i != 1) {
                    return;
                }
                Function2<Object, Continuation<? super R>, Object> function2 = this.block;
                ChannelResult.Companion companion = ChannelResult.Companion;
                Throwable th = closed.closeCause;
                companion.getClass();
                CancellableKt.startCoroutineCancellable$default(function2, new ChannelResult(new ChannelResult.Closed(th)), this.select.getCompletion(), null, 4, null);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + AbstractJsonLexerKt.BEGIN_LIST + this.select + ",receiveMode=" + this.receiveMode + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(prepareOp);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {

        @NotNull
        public final Receive<?> receive;

        public RemoveReceiveOnCancel(@NotNull Receive<?> receive) {
            this.receive = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "RemoveReceiveOnCancel[" + this.receive + AbstractJsonLexerKt.END_LIST;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (this.receive.mo2129remove()) {
                AbstractChannel.this.getClass();
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof Send) {
                return null;
            }
            return AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbolTryResumeSend = ((Send) prepareOp.affected).tryResumeSend(prepareOp);
            if (symbolTryResumeSend == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            Object obj = AtomicKt.RETRY_ATOMIC;
            if (symbolTryResumeSend == obj) {
                return obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            ((Send) lockFreeLinkedListNode).undeliveredElement();
        }
    }

    public AbstractChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: cancelInternal$kotlinx_coroutines_core, reason: merged with bridge method [inline-methods] */
    public final boolean cancel(@Nullable Throwable th) {
        boolean zClose = cancel(th);
        onCancelIdempotent(zClose);
        return zClose;
    }

    @NotNull
    public final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc<>((LockFreeLinkedListNode) this.queue);
    }

    public final boolean enqueueReceive(Receive<? super E> receive) {
        return enqueueReceiveInternal(receive);
    }

    public boolean enqueueReceiveInternal(@NotNull final Receive<? super E> receive) {
        int iTryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (!isBufferAlwaysEmpty()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                @Nullable
                public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
                    if (this.isBufferEmpty()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.CONDITION_FALSE;
                }
            };
            do {
                LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode.getPrevNode();
                if (prevNode2 instanceof Send) {
                    return false;
                }
                iTryCondAddNext = prevNode2.tryCondAddNext(receive, lockFreeLinkedListNode, condAddOp);
                if (iTryCondAddNext != 1) {
                }
            } while (iTryCondAddNext != 2);
            return false;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        do {
            prevNode = lockFreeLinkedListNode2.getPrevNode();
            if (prevNode instanceof Send) {
                return false;
            }
        } while (!prevNode.addNext(receive, lockFreeLinkedListNode2));
        return true;
    }

    public final <R> boolean enqueueReceiveSelect(SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
        ReceiveSelect receiveSelect = new ReceiveSelect(this, selectInstance, function2, i);
        boolean zEnqueueReceiveInternal = enqueueReceiveInternal(receiveSelect);
        if (zEnqueueReceiveInternal) {
            selectInstance.disposeOnSelect(receiveSelect);
        }
        return zEnqueueReceiveInternal;
    }

    public final boolean getHasReceiveOrClosed() {
        return this.queue.getNextNode() instanceof ReceiveOrClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>(this) { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            public final /* synthetic */ AbstractChannel<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
                this.this$0.registerSelectReceiveMode(selectInstance, 0, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return new SelectClause1<ChannelResult<? extends E>>(this) { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveCatching$1
            public final /* synthetic */ AbstractChannel<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super ChannelResult<? extends E>, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
                this.this$0.registerSelectReceiveMode(selectInstance, 1, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return new ReceiveChannel$onReceiveOrNull$1(this);
    }

    public abstract boolean isBufferAlwaysEmpty();

    public abstract boolean isBufferEmpty();

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return isEmptyImpl();
    }

    public final boolean isEmptyImpl() {
        return !(this.queue.getNextNode() instanceof Send) && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr(this);
    }

    public void onCancelIdempotent(boolean z) {
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null) {
            throw new IllegalStateException("Cannot happen");
        }
        Object objM2121constructorimpl$default = InlineList.m2121constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
            if (prevNode instanceof LockFreeLinkedListHead) {
                mo2081onCancelIdempotentListww6eGU(objM2121constructorimpl$default, closedForSend);
                return;
            } else if (prevNode.mo2129remove()) {
                objM2121constructorimpl$default = InlineList.m2126plusFjFbRPM(objM2121constructorimpl$default, (Send) prevNode);
            } else {
                prevNode.helpRemove();
            }
        }
    }

    /* JADX INFO: renamed from: onCancelIdempotentList-w-w6eGU, reason: not valid java name */
    public void mo2081onCancelIdempotentListww6eGU(@NotNull Object obj, @NotNull Closed<?> closed) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            ((Send) obj).resumeSendClosed(closed);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            } else {
                ((Send) arrayList.get(size)).resumeSendClosed(closed);
            }
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    @Nullable
    public E poll() {
        return (E) ReceiveChannel.DefaultImpls.poll(this);
    }

    @Nullable
    public Object pollInternal() {
        while (true) {
            Send sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (sendTakeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            if (sendTakeFirstSendOrPeekClosed.tryResumeSend(null) != null) {
                sendTakeFirstSendOrPeekClosed.completeResumeSend();
                return sendTakeFirstSendOrPeekClosed.getPollResult();
            }
            sendTakeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    @Nullable
    public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        TryPollDesc<E> tryPollDescDescribeTryPoll = describeTryPoll();
        Object objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(tryPollDescDescribeTryPoll);
        if (objPerformAtomicTrySelect != null) {
            return objPerformAtomicTrySelect;
        }
        tryPollDescDescribeTryPoll.getResult().completeResumeSend();
        return tryPollDescDescribeTryPoll.getResult().getPollResult();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object objPollInternal = pollInternal();
        return (objPollInternal == AbstractChannelKt.POLL_FAILED || (objPollInternal instanceof Closed)) ? receiveSuspend(0, continuation) : objPollInternal;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object mo2082receiveCatchingJP2dKIU(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.coroutines.channels.ChannelResult<? extends E>> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L5c
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.pollInternal()
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED
            if (r5 == r2) goto L53
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.Closed
            if (r0 == 0) goto L4d
            kotlinx.coroutines.channels.ChannelResult$Companion r0 = kotlinx.coroutines.channels.ChannelResult.Companion
            kotlinx.coroutines.channels.Closed r5 = (kotlinx.coroutines.channels.Closed) r5
            java.lang.Throwable r5 = r5.closeCause
            r0.getClass()
            kotlinx.coroutines.channels.ChannelResult$Closed r0 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r0.<init>(r5)
            return r0
        L4d:
            kotlinx.coroutines.channels.ChannelResult$Companion r0 = kotlinx.coroutines.channels.ChannelResult.Companion
            r0.getClass()
            return r5
        L53:
            r0.label = r3
            java.lang.Object r5 = r4.receiveSuspend(r3, r0)
            if (r5 != r1) goto L5c
            return r1
        L5c:
            kotlinx.coroutines.channels.ChannelResult r5 = (kotlinx.coroutines.channels.ChannelResult) r5
            java.lang.Object r5 = r5.holder
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.mo2082receiveCatchingJP2dKIU(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    @Nullable
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return ReceiveChannel.DefaultImpls.receiveOrNull(this, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Object receiveSuspend(int i, Continuation<? super R> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        ReceiveElement receiveElement = this.onUndeliveredElement == null ? new ReceiveElement(orCreateCancellableContinuation, i) : new ReceiveElementWithUndeliveredHandler(orCreateCancellableContinuation, i, this.onUndeliveredElement);
        while (true) {
            if (enqueueReceiveInternal(receiveElement)) {
                removeReceiveOnCancel(orCreateCancellableContinuation, receiveElement);
                break;
            }
            Object objPollInternal = pollInternal();
            if (objPollInternal instanceof Closed) {
                receiveElement.resumeReceiveClosed((Closed) objPollInternal);
                break;
            }
            if (objPollInternal != AbstractChannelKt.POLL_FAILED) {
                orCreateCancellableContinuation.resume(receiveElement.resumeValue(objPollInternal), receiveElement.resumeOnCancellationFun(objPollInternal));
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    public final <R> void registerSelectReceiveMode(SelectInstance<? super R> selectInstance, int i, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
        while (!selectInstance.isSelected()) {
            if (!isEmptyImpl()) {
                Object objPollSelectInternal = pollSelectInternal(selectInstance);
                if (objPollSelectInternal == SelectKt.getALREADY_SELECTED()) {
                    return;
                }
                if (objPollSelectInternal != AbstractChannelKt.POLL_FAILED && objPollSelectInternal != AtomicKt.RETRY_ATOMIC) {
                    tryStartBlockUnintercepted(function2, selectInstance, i, objPollSelectInternal);
                }
            } else if (enqueueReceiveSelect(selectInstance, function2, i)) {
                return;
            }
        }
    }

    public final void removeReceiveOnCancel(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(receive));
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        return super.takeFirstReceiveOrPeekClosed();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    /* JADX INFO: renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public final Object mo2083tryReceivePtdJZtk() {
        Object objPollInternal = pollInternal();
        if (objPollInternal == AbstractChannelKt.POLL_FAILED) {
            ChannelResult.Companion.getClass();
            return ChannelResult.failed;
        }
        if (!(objPollInternal instanceof Closed)) {
            ChannelResult.Companion.getClass();
            return objPollInternal;
        }
        ChannelResult.Companion companion = ChannelResult.Companion;
        Throwable th = ((Closed) objPollInternal).closeCause;
        companion.getClass();
        return new ChannelResult.Closed(th);
    }

    public final <R> void tryStartBlockUnintercepted(Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i, Object obj) throws Throwable {
        boolean z = obj instanceof Closed;
        if (!z) {
            if (i != 1) {
                UndispatchedKt.startCoroutineUnintercepted(function2, obj, selectInstance.getCompletion());
                return;
            }
            ChannelResult.Companion companion = ChannelResult.Companion;
            if (z) {
                Throwable th = ((Closed) obj).closeCause;
                companion.getClass();
                obj = new ChannelResult.Closed(th);
            } else {
                companion.getClass();
            }
            UndispatchedKt.startCoroutineUnintercepted(function2, new ChannelResult(obj), selectInstance.getCompletion());
            return;
        }
        if (i == 0) {
            Throwable receiveException = ((Closed) obj).getReceiveException();
            StackTraceRecoveryKt.recoverStackTrace(receiveException);
            throw receiveException;
        }
        if (i == 1 && selectInstance.trySelect()) {
            ChannelResult.Companion companion2 = ChannelResult.Companion;
            Throwable th2 = ((Closed) obj).closeCause;
            companion2.getClass();
            UndispatchedKt.startCoroutineUnintercepted(function2, new ChannelResult(new ChannelResult.Closed(th2)), selectInstance.getCompletion());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(@Nullable CancellationException cancellationException) {
        if (isClosedForReceive()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new CancellationException(getClass().getSimpleName().concat(" was cancelled"));
        }
        cancel(cancellationException);
    }

    public void onReceiveDequeued() {
    }

    public void onReceiveEnqueued() {
    }
}
