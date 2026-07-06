package kotlinx.coroutines.selects;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>, CoroutineStackFrame {

    @NotNull
    public final Continuation<R> uCont;
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");

    @NotNull
    volatile /* synthetic */ Object _state = SelectKt.getNOT_SELECTED();

    @NotNull
    private volatile /* synthetic */ Object _result = SelectKt.UNDECIDED;

    @NotNull
    private volatile /* synthetic */ Object _parentHandle = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AtomicSelectOp extends AtomicOp<Object> {

        @JvmField
        @NotNull
        public final AtomicDesc desc;

        @JvmField
        @NotNull
        public final SelectBuilderImpl<?> impl;
        public final long opSequence;

        public AtomicSelectOp(@NotNull SelectBuilderImpl<?> selectBuilderImpl, @NotNull AtomicDesc atomicDesc) {
            this.impl = selectBuilderImpl;
            this.desc = atomicDesc;
            SeqNumber seqNumber = SelectKt.selectOpSequenceNumber;
            seqNumber.getClass();
            this.opSequence = SeqNumber.number$FU.incrementAndGet(seqNumber);
            atomicDesc.atomicOp = this;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(@Nullable Object obj, @Nullable Object obj2) {
            completeSelect(obj2);
            this.desc.complete(this, obj2);
        }

        public final void completeSelect(Object obj) {
            boolean z = obj == null;
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, this.impl, this, z ? null : SelectKt.getNOT_SELECTED()) && z) {
                this.impl.doAfterSelect();
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long getOpSequence() {
            return this.opSequence;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        public Object prepare(@Nullable Object obj) {
            Object objPrepareSelectOp;
            if (obj == null && (objPrepareSelectOp = prepareSelectOp()) != null) {
                return objPrepareSelectOp;
            }
            try {
                return this.desc.prepare(this);
            } catch (Throwable th) {
                if (obj == null) {
                    undoPrepare();
                }
                throw th;
            }
        }

        public final Object prepareSelectOp() {
            SelectBuilderImpl<?> selectBuilderImpl = this.impl;
            while (true) {
                Object obj = selectBuilderImpl._state;
                if (obj == this) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).perform(this.impl);
                } else {
                    if (obj != SelectKt.getNOT_SELECTED()) {
                        return SelectKt.ALREADY_SELECTED;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, this.impl, SelectKt.NOT_SELECTED, this)) {
                        return null;
                    }
                }
            }
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public String toString() {
            return "AtomicSelectOp(sequence=" + this.opSequence + ')';
        }

        public final void undoPrepare() {
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, this.impl, this, SelectKt.getNOT_SELECTED());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DisposeNode extends LockFreeLinkedListNode {

        @JvmField
        @NotNull
        public final DisposableHandle handle;

        public DisposeNode(@NotNull DisposableHandle disposableHandle) {
            this.handle = disposableHandle;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PairSelectOp extends OpDescriptor {

        @JvmField
        @NotNull
        public final LockFreeLinkedListNode.PrepareOp otherOp;

        public PairSelectOp(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            this.otherOp = prepareOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public AtomicOp<?> getAtomicOp() {
            return this.otherOp.desc.getAtomicOp();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @Nullable
        public Object perform(@Nullable Object obj) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            }
            SelectBuilderImpl selectBuilderImpl = (SelectBuilderImpl) obj;
            this.otherOp.finishPrepare();
            Object objDecide = this.otherOp.desc.getAtomicOp().decide(null);
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SelectBuilderImpl._state$FU, selectBuilderImpl, this, objDecide == null ? this.otherOp.desc : SelectKt.getNOT_SELECTED());
            return objDecide;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SelectOnCancelling extends JobCancellingNode {
        public SelectOnCancelling() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (SelectBuilderImpl.this.trySelect()) {
                SelectBuilderImpl.this.resumeSelectWithException(getJob().getCancellationException());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.uCont = continuation;
    }

    private final void initCancellability() {
        Job job = (Job) this.uCont.getContext().get(Job.Key);
        if (job == null) {
            return;
        }
        DisposableHandle disposableHandleInvokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new SelectOnCancelling(), 2, null);
        this._parentHandle = disposableHandleInvokeOnCompletion$default;
        if (isSelected()) {
            disposableHandleInvokeOnCompletion$default.dispose();
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnSelect(@NotNull DisposableHandle disposableHandle) {
        DisposeNode disposeNode = new DisposeNode(disposableHandle);
        if (!isSelected()) {
            addLast(disposeNode);
            if (!isSelected()) {
                return;
            }
        }
        disposableHandle.dispose();
    }

    public final void doAfterSelect() {
        DisposableHandle disposableHandle = (DisposableHandle) this._parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual(nextNode, this); nextNode = nextNode.getNextNode()) {
            if (nextNode instanceof DisposeNode) {
                ((DisposeNode) nextNode).handle.dispose();
            }
        }
    }

    public final void doResume(Function0<? extends Object> function0, Function0<Unit> function02) {
        while (true) {
            Object obj = this._result;
            if (obj == SelectKt.UNDECIDED) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.UNDECIDED, function0.invoke())) {
                    return;
                }
            } else {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj != coroutineSingletons) {
                    throw new IllegalStateException("Already resumed");
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, coroutineSingletons, SelectKt.RESUMED)) {
                    function02.invoke();
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    public final DisposableHandle getParentHandle() {
        return (DisposableHandle) this._parentHandle;
    }

    @PublishedApi
    @Nullable
    public final Object getResult() throws Throwable {
        if (!isSelected()) {
            initCancellability();
        }
        Object obj = this._result;
        Object obj2 = SelectKt.UNDECIDED;
        if (obj == obj2) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _result$FU;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj2, coroutineSingletons)) {
                return coroutineSingletons;
            }
            obj = this._result;
        }
        if (obj == SelectKt.RESUMED) {
            throw new IllegalStateException("Already resumed");
        }
        if (obj instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) obj).cause;
        }
        return obj;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) throws Throwable {
        if (trySelect()) {
            resumeWith(ResultKt.createFailure(th));
            return;
        }
        if (th instanceof CancellationException) {
            return;
        }
        Object result = getResult();
        if ((result instanceof CompletedExceptionally) && ((CompletedExceptionally) result).cause == th) {
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(this.uCont.getContext(), th);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        invoke(selectClause2, null, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean isSelected() {
        while (true) {
            Object obj = this._state;
            if (obj == SelectKt.getNOT_SELECTED()) {
                return false;
            }
            if (!(obj instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long j, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        if (j > 0) {
            disposeOnSelect(DelayKt.getDelay(this.uCont.getContext()).invokeOnTimeout(j, new Runnable() { // from class: kotlinx.coroutines.selects.SelectBuilderImpl$onTimeout$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (this.this$0.trySelect()) {
                        Function1 function12 = function1;
                        SelectBuilderImpl selectBuilderImpl = this.this$0;
                        selectBuilderImpl.getClass();
                        CancellableKt.startCoroutineCancellable(function12, selectBuilderImpl);
                    }
                }
            }, this.uCont.getContext()));
        } else if (trySelect()) {
            UndispatchedKt.startCoroutineUnintercepted(function1, this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @Nullable
    public Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc) {
        return new AtomicSelectOp(this, atomicDesc).perform(null);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void resumeSelectWithException(@NotNull Throwable th) {
        while (true) {
            Object obj = this._result;
            if (obj == SelectKt.UNDECIDED) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.UNDECIDED, new CompletedExceptionally(th, false, 2, null))) {
                    return;
                }
            } else {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj != coroutineSingletons) {
                    throw new IllegalStateException("Already resumed");
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, coroutineSingletons, SelectKt.RESUMED)) {
                    IntrinsicsKt__IntrinsicsJvmKt.intercepted(this.uCont).resumeWith(ResultKt.createFailure(th));
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        while (true) {
            Object obj2 = this._result;
            if (obj2 == SelectKt.UNDECIDED) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, SelectKt.UNDECIDED, CompletionStateKt.toState$default(obj, null, 1, null))) {
                    return;
                }
            } else {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj2 != coroutineSingletons) {
                    throw new IllegalStateException("Already resumed");
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_result$FU, this, coroutineSingletons, SelectKt.RESUMED)) {
                    if (!(obj instanceof Result.Failure)) {
                        this.uCont.resumeWith(obj);
                        return;
                    }
                    Continuation<R> continuation = this.uCont;
                    Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
                    Intrinsics.checkNotNull(thM583exceptionOrNullimpl);
                    continuation.resumeWith(ResultKt.createFailure(thM583exceptionOrNullimpl));
                    return;
                }
            }
        }
    }

    public final void setParentHandle(DisposableHandle disposableHandle) {
        this._parentHandle = disposableHandle;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "SelectInstance(state=" + this._state + ", result=" + this._result + ')';
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect() {
        Object objTrySelectOther = trySelectOther(null);
        if (objTrySelectOther == CancellableContinuationImplKt.RESUME_TOKEN) {
            return true;
        }
        if (objTrySelectOther == null) {
            return false;
        }
        throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Unexpected trySelectIdempotent result ", objTrySelectOther));
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
    
        doAfterSelect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        return kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN;
     */
    @Override // kotlinx.coroutines.selects.SelectInstance
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object trySelectOther(@org.jetbrains.annotations.Nullable kotlinx.coroutines.internal.LockFreeLinkedListNode.PrepareOp r4) {
        /*
            r3 = this;
        L0:
            java.lang.Object r0 = r3._state
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            r2 = 0
            if (r0 != r1) goto L32
            if (r4 != 0) goto L16
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED
            boolean r0 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r0, r3, r1, r2)
            if (r0 != 0) goto L2c
            goto L0
        L16:
            kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp r0 = new kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp
            r0.<init>(r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED
            boolean r1 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r1, r3, r2, r0)
            if (r1 == 0) goto L0
            java.lang.Object r4 = r0.perform(r3)
            if (r4 == 0) goto L2c
            return r4
        L2c:
            r3.doAfterSelect()
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L32:
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r1 == 0) goto L64
            if (r4 == 0) goto L5e
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r1 = r4.desc
            kotlinx.coroutines.internal.AtomicOp r1 = r1.getAtomicOp()
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp
            if (r2 == 0) goto L52
            r2 = r1
            kotlinx.coroutines.selects.SelectBuilderImpl$AtomicSelectOp r2 = (kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp) r2
            kotlinx.coroutines.selects.SelectBuilderImpl<?> r2 = r2.impl
            if (r2 == r3) goto L4a
            goto L52
        L4a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Cannot use matching select clauses on the same object"
            r4.<init>(r0)
            throw r4
        L52:
            r2 = r0
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            boolean r1 = r1.isEarlierThan(r2)
            if (r1 == 0) goto L5e
            java.lang.Object r4 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
            return r4
        L5e:
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            r0.perform(r3)
            goto L0
        L64:
            if (r4 != 0) goto L67
            return r2
        L67:
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r4 = r4.desc
            if (r0 != r4) goto L6e
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L6e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectBuilderImpl.trySelectOther(kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectClause0.registerSelectClause0(this, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause1.registerSelectClause1(this, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause2.registerSelectClause2(this, p, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }
}
