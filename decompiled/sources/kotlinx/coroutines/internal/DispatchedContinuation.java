package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");

    @NotNull
    private volatile /* synthetic */ Object _reusableCancellableContinuation;

    @JvmField
    @Nullable
    public Object _state;

    @JvmField
    @NotNull
    public final Continuation<T> continuation;

    @JvmField
    @NotNull
    public final Object countOrElement;

    @JvmField
    @NotNull
    public final CoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Continuation<? super T> continuation) {
        super(-1);
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.UNDEFINED;
        this.countOrElement = ThreadContextKt.threadContextElements(continuation.getContext());
        this._reusableCancellableContinuation = null;
    }

    public final void awaitReusability() {
        while (this._reusableCancellableContinuation == DispatchedContinuationKt.REUSABLE_CLAIMED) {
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(@Nullable Object obj, @NotNull Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).onCancellation.invoke(th);
        }
    }

    @Nullable
    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = DispatchedContinuationKt.REUSABLE_CLAIMED;
                return null;
            }
            if (obj instanceof CancellableContinuationImpl) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) obj;
                }
            } else if (obj != DispatchedContinuationKt.REUSABLE_CLAIMED && !(obj instanceof Throwable)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Inconsistent state ", obj));
            }
        }
    }

    public final void dispatchYield$kotlinx_coroutines_core(@NotNull CoroutineContext coroutineContext, T t) {
        this._state = t;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(coroutineContext, this);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    public final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object obj = this._reusableCancellableContinuation;
        if (obj instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) obj;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final boolean isReusable() {
        return this._reusableCancellableContinuation != null;
    }

    public final boolean postponeCancellation(@NotNull Throwable th) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (Intrinsics.areEqual(obj, symbol)) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, symbol, th)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void release() {
        awaitReusability();
        CancellableContinuationImpl<?> reusableCancellableContinuation = getReusableCancellableContinuation();
        if (reusableCancellableContinuation != null) {
            reusableCancellableContinuation.detachChild$kotlinx_coroutines_core();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0086 A[Catch: all -> 0x0061, DONT_GENERATE, TryCatch #0 {all -> 0x0061, blocks: (B:10:0x003c, B:12:0x004c, B:14:0x0052, B:27:0x0089, B:17:0x0063, B:19:0x0073, B:24:0x0080, B:26:0x0086, B:32:0x0096, B:35:0x009f, B:34:0x009c, B:22:0x0079), top: B:42:0x003c, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void resumeCancellableWith(@org.jetbrains.annotations.NotNull java.lang.Object r7, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r8) {
        /*
            r6 = this;
            java.lang.Object r8 = kotlinx.coroutines.CompletionStateKt.toState(r7, r8)
            kotlinx.coroutines.CoroutineDispatcher r0 = r6.dispatcher
            kotlin.coroutines.Continuation<T> r1 = r6.continuation
            kotlin.coroutines.CoroutineContext r1 = r1.getContext()
            boolean r0 = r0.isDispatchNeeded(r1)
            r1 = 1
            if (r0 == 0) goto L23
            r6._state = r8
            r6.resumeMode = r1
            kotlinx.coroutines.CoroutineDispatcher r7 = r6.dispatcher
            kotlin.coroutines.Continuation<T> r8 = r6.continuation
            kotlin.coroutines.CoroutineContext r8 = r8.getContext()
            r7.mo2130dispatch(r8, r6)
            return
        L23:
            kotlinx.coroutines.ThreadLocalEventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.INSTANCE
            kotlinx.coroutines.EventLoop r0 = r0.getEventLoop$kotlinx_coroutines_core()
            boolean r2 = r0.isUnconfinedLoopActive()
            if (r2 == 0) goto L38
            r6._state = r8
            r6.resumeMode = r1
            r0.dispatchUnconfined(r6)
            goto La4
        L38:
            r0.incrementUseCount(r1)
            r2 = 0
            kotlin.coroutines.Continuation<T> r3 = r6.continuation     // Catch: java.lang.Throwable -> L61
            kotlin.coroutines.CoroutineContext r3 = r3.getContext()     // Catch: java.lang.Throwable -> L61
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.Key     // Catch: java.lang.Throwable -> L61
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L61
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L63
            boolean r4 = r3.isActive()     // Catch: java.lang.Throwable -> L61
            if (r4 != 0) goto L63
            java.util.concurrent.CancellationException r7 = r3.getCancellationException()     // Catch: java.lang.Throwable -> L61
            r6.cancelCompletedResult$kotlinx_coroutines_core(r8, r7)     // Catch: java.lang.Throwable -> L61
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)     // Catch: java.lang.Throwable -> L61
            r6.resumeWith(r7)     // Catch: java.lang.Throwable -> L61
            goto L89
        L61:
            r7 = move-exception
            goto La0
        L63:
            kotlin.coroutines.Continuation<T> r8 = r6.continuation     // Catch: java.lang.Throwable -> L61
            java.lang.Object r3 = r6.countOrElement     // Catch: java.lang.Throwable -> L61
            kotlin.coroutines.CoroutineContext r4 = r8.getContext()     // Catch: java.lang.Throwable -> L61
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L61
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch: java.lang.Throwable -> L61
            if (r3 == r5) goto L78
            kotlinx.coroutines.UndispatchedCoroutine r8 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r8, r4, r3)     // Catch: java.lang.Throwable -> L61
            goto L79
        L78:
            r8 = r2
        L79:
            kotlin.coroutines.Continuation<T> r5 = r6.continuation     // Catch: java.lang.Throwable -> L93
            r5.resumeWith(r7)     // Catch: java.lang.Throwable -> L93
            if (r8 == 0) goto L86
            boolean r7 = r8.clearThreadContext()     // Catch: java.lang.Throwable -> L61
            if (r7 == 0) goto L89
        L86:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L61
        L89:
            boolean r7 = r0.processUnconfinedEvent()     // Catch: java.lang.Throwable -> L61
            if (r7 != 0) goto L89
        L8f:
            r0.decrementUseCount(r1)
            goto La4
        L93:
            r7 = move-exception
            if (r8 == 0) goto L9c
            boolean r8 = r8.clearThreadContext()     // Catch: java.lang.Throwable -> L61
            if (r8 == 0) goto L9f
        L9c:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch: java.lang.Throwable -> L61
        L9f:
            throw r7     // Catch: java.lang.Throwable -> L61
        La0:
            r6.handleFatalException(r7, r2)     // Catch: java.lang.Throwable -> La5
            goto L8f
        La4:
            return
        La5:
            r7 = move-exception
            r0.decrementUseCount(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuation.resumeCancellableWith(java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    public final boolean resumeCancelled(@Nullable Object obj) {
        Job job = (Job) this.continuation.getContext().get(Job.Key);
        if (job == null || job.isActive()) {
            return false;
        }
        CancellationException cancellationException = job.getCancellationException();
        cancelCompletedResult$kotlinx_coroutines_core(obj, cancellationException);
        resumeWith(ResultKt.createFailure(cancellationException));
        return true;
    }

    public final void resumeUndispatchedWith(@NotNull Object obj) {
        Continuation<T> continuation = this.continuation;
        Object obj2 = this.countOrElement;
        CoroutineContext context = continuation.getContext();
        Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
        UndispatchedCoroutine<?> undispatchedCoroutineUpdateUndispatchedCompletion = objUpdateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation, context, objUpdateThreadContext) : null;
        try {
            this.continuation.resumeWith(obj);
        } finally {
            if (undispatchedCoroutineUpdateUndispatchedCompletion == null || undispatchedCoroutineUpdateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, objUpdateThreadContext);
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        CoroutineContext context = this.continuation.getContext();
        Object state$default = CompletionStateKt.toState$default(obj, null, 1, null);
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = state$default;
            this.resumeMode = 0;
            this.dispatcher.mo2130dispatch(context, this);
            return;
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state$default;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            CoroutineContext context2 = this.continuation.getContext();
            Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(context2, this.countOrElement);
            try {
                this.continuation.resumeWith(obj);
                while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent()) {
                }
            } finally {
                ThreadContextKt.restoreThreadContext(context2, objUpdateThreadContext);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        Object obj = this._state;
        this._state = DispatchedContinuationKt.UNDEFINED;
        return obj;
    }

    @NotNull
    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + AbstractJsonLexerKt.END_LIST;
    }

    @Nullable
    public final Throwable tryReleaseClaimedContinuation(@NotNull CancellableContinuation<?> cancellableContinuation) {
        Symbol symbol;
        do {
            Object obj = this._reusableCancellableContinuation;
            symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (obj != symbol) {
                if (!(obj instanceof Throwable)) {
                    throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Inconsistent state ", obj));
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, null)) {
                    return (Throwable) obj;
                }
                throw new IllegalArgumentException("Failed requirement.");
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, symbol, cancellableContinuation));
        return null;
    }

    public static /* synthetic */ void get_state$kotlinx_coroutines_core$annotations() {
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }
}
