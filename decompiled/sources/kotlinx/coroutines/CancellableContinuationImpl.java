package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@PublishedApi
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");

    @NotNull
    private volatile /* synthetic */ int _decision;

    @NotNull
    private volatile /* synthetic */ Object _state;

    @NotNull
    public final CoroutineContext context;

    @NotNull
    public final Continuation<T> delegate;

    @Nullable
    public DisposableHandle parentHandle;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(@NotNull Continuation<? super T> continuation, int i) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
        this._decision = 0;
        this._state = Active.INSTANCE;
    }

    private final boolean isReusable() {
        return DispatchedTaskKt.isReusableMode(this.resumeMode) && ((DispatchedContinuation) this.delegate).isReusable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resumeImpl$default(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        cancellableContinuationImpl.resumeImpl(obj, i, function1);
    }

    private final boolean trySuspend() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    public final Void alreadyResumedError(Object obj) {
        throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Already resumed, but proposed with update ", obj));
    }

    public final void callCancelHandler(@NotNull CancelHandler cancelHandler, @Nullable Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void callCancelHandlerSafely(Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Throwable th) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th));
        }
    }

    public final void callOnCancellation(@NotNull Function1<? super Throwable, Unit> function1, @NotNull Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(@Nullable Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new CancelledContinuation(this, th, z)));
        CancelHandler cancelHandler = z ? (CancelHandler) obj : null;
        if (cancelHandler != null) {
            callCancelHandler(cancelHandler, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(@Nullable Object obj, @NotNull Throwable th) {
        Throwable th2;
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof CompletedExceptionally) {
                return;
            }
            if (obj2 instanceof CompletedContinuation) {
                CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                if (completedContinuation.getCancelled()) {
                    throw new IllegalStateException("Must be called at most once");
                }
                Throwable th3 = th;
                th2 = th3;
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, CompletedContinuation.copy$default(completedContinuation, null, null, null, null, th3, 15, null))) {
                    completedContinuation.invokeHandlers(this, th2);
                    return;
                }
            } else {
                th2 = th;
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, new CompletedContinuation(obj2, null, null, null, th2, 14, null))) {
                    return;
                }
            }
            th = th2;
        }
    }

    public final boolean cancelLater(Throwable th) {
        if (isReusable()) {
            return ((DispatchedContinuation) this.delegate).postponeCancellation(th);
        }
        return false;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(@NotNull Object obj) {
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this.parentHandle = NonDisposableHandle.INSTANCE;
    }

    public final void detachChildIfNonResuable() {
        if (isReusable()) {
            return;
        }
        detachChild$kotlinx_coroutines_core();
    }

    public final void dispatchResume(int i) {
        if (tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, i);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    @NotNull
    public Throwable getContinuationCancellationCause(@NotNull Job job) {
        return job.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Throwable getExceptionalResult$kotlinx_coroutines_core(@Nullable Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            return exceptionalResult$kotlinx_coroutines_core;
        }
        return null;
    }

    @PublishedApi
    @Nullable
    public final Object getResult() {
        Job job;
        boolean zIsReusable = isReusable();
        if (trySuspend()) {
            if (this.parentHandle == null) {
                installParentHandle();
            }
            if (zIsReusable) {
                releaseClaimedReusableContinuation();
            }
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        if (zIsReusable) {
            releaseClaimedReusableContinuation();
        }
        Object obj = this._state;
        if (obj instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) obj).cause;
        }
        if (!DispatchedTaskKt.isCancellableMode(this.resumeMode) || (job = (Job) getContext().get(Job.Key)) == null || job.isActive()) {
            return getSuccessfulResult$kotlinx_coroutines_core(obj);
        }
        CancellationException cancellationException = job.getCancellationException();
        cancelCompletedResult$kotlinx_coroutines_core(obj, cancellationException);
        throw cancellationException;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        return this._state;
    }

    public final String getStateDebugRepresentation() {
        Object obj = this._state;
        return obj instanceof NotCompleted ? "Active" : obj instanceof CancelledContinuation ? "Cancelled" : "Completed";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object obj) {
        return obj instanceof CompletedContinuation ? (T) ((CompletedContinuation) obj).result : obj;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void initCancellability() {
        DisposableHandle disposableHandleInstallParentHandle = installParentHandle();
        if (disposableHandleInstallParentHandle != null && isCompleted()) {
            disposableHandleInstallParentHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final DisposableHandle installParentHandle() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null) {
            return null;
        }
        DisposableHandle disposableHandleInvokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(this), 2, null);
        this.parentHandle = disposableHandleInvokeOnCompletion$default;
        return disposableHandleInvokeOnCompletion$default;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1) {
        CancelHandler cancelHandlerMakeCancelHandler = makeCancelHandler(function1);
        while (true) {
            Object obj = this._state;
            if (obj instanceof Active) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, cancelHandlerMakeCancelHandler)) {
                    return;
                }
            } else {
                if (obj instanceof CancelHandler) {
                    multipleHandlersError(function1, obj);
                    throw null;
                }
                if (obj instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                    if (!completedExceptionally.makeHandled()) {
                        multipleHandlersError(function1, obj);
                        throw null;
                    }
                    if (obj instanceof CancelledContinuation) {
                        callCancelHandler(function1, completedExceptionally.cause);
                        return;
                    }
                    return;
                }
                if (obj instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj;
                    if (completedContinuation.cancelHandler != null) {
                        multipleHandlersError(function1, obj);
                        throw null;
                    }
                    if (cancelHandlerMakeCancelHandler instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (completedContinuation.getCancelled()) {
                        callCancelHandler(function1, completedContinuation.cancelCause);
                        return;
                    } else {
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, CompletedContinuation.copy$default(completedContinuation, null, cancelHandlerMakeCancelHandler, null, null, null, 29, null))) {
                            return;
                        }
                    }
                } else {
                    if (cancelHandlerMakeCancelHandler instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new CompletedContinuation(obj, cancelHandlerMakeCancelHandler, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return this._state instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return this._state instanceof CancelledContinuation;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(this._state instanceof NotCompleted);
    }

    public final CancelHandler makeCancelHandler(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    public final void multipleHandlersError(Function1<? super Throwable, Unit> function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    @NotNull
    public String nameString() {
        return "CancellableContinuation";
    }

    public final void parentCancelled$kotlinx_coroutines_core(@NotNull Throwable th) {
        if (cancelLater(th)) {
            return;
        }
        cancel(th);
        detachChildIfNonResuable();
    }

    public final void releaseClaimedReusableContinuation() {
        Throwable thTryReleaseClaimedContinuation;
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation == null || (thTryReleaseClaimedContinuation = dispatchedContinuation.tryReleaseClaimedContinuation(this)) == null) {
            return;
        }
        detachChild$kotlinx_coroutines_core();
        cancel(thTryReleaseClaimedContinuation);
    }

    @JvmName(name = "resetStateReusable")
    public final boolean resetStateReusable() {
        Object obj = this._state;
        if ((obj instanceof CompletedContinuation) && ((CompletedContinuation) obj).idempotentResume != null) {
            detachChild$kotlinx_coroutines_core();
            return false;
        }
        this._decision = 0;
        this._state = Active.INSTANCE;
        return true;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T t, @Nullable Function1<? super Throwable, Unit> function1) {
        resumeImpl(t, this.resumeMode, function1);
    }

    public final void resumeImpl(Object obj, int i, Function1<? super Throwable, Unit> function1) {
        while (true) {
            Object obj2 = this._state;
            if (!(obj2 instanceof NotCompleted)) {
                Object obj3 = obj;
                Function1<? super Throwable, Unit> function12 = function1;
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation.makeResumed()) {
                        if (function12 != null) {
                            callOnCancellation(function12, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                alreadyResumedError(obj3);
                throw null;
            }
            Object obj4 = obj;
            int i2 = i;
            Function1<? super Throwable, Unit> function13 = function1;
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj2, resumedState((NotCompleted) obj2, obj4, i2, function13, null))) {
                detachChildIfNonResuable();
                dispatchResume(i2);
                return;
            } else {
                obj = obj4;
                i = i2;
                function1 = function13;
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl$default(this, t, (dispatchedContinuation != null ? dispatchedContinuation.dispatcher : null) == coroutineDispatcher ? 4 : this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th) {
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl$default(this, new CompletedExceptionally(th, false, 2, null), (dispatchedContinuation != null ? dispatchedContinuation.dispatcher : null) == coroutineDispatcher ? 4 : this.resumeMode, null, 4, null);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        resumeImpl$default(this, CompletionStateKt.toState(obj, this), this.resumeMode, null, 4, null);
    }

    public final Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1<? super Throwable, Unit> function1, Object obj2) {
        if (obj instanceof CompletedExceptionally) {
            return obj;
        }
        if ((DispatchedTaskKt.isCancellableMode(i) || obj2 != null) && (function1 != null || (((notCompleted instanceof CancelHandler) && !(notCompleted instanceof BeforeResumeCancelHandler)) || obj2 != null))) {
            return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, null, 16, null);
        }
        return obj;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        return this._state;
    }

    @NotNull
    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + getStateDebugRepresentation() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResume(T t, @Nullable Object obj) {
        return tryResumeImpl(t, obj, null);
    }

    public final Symbol tryResumeImpl(Object obj, Object obj2, Function1<? super Throwable, Unit> function1) {
        while (true) {
            Object obj3 = this._state;
            if (!(obj3 instanceof NotCompleted)) {
                Object obj4 = obj2;
                if ((obj3 instanceof CompletedContinuation) && obj4 != null && ((CompletedContinuation) obj3).idempotentResume == obj4) {
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
                return null;
            }
            Object obj5 = obj;
            Object obj6 = obj2;
            Function1<? super Throwable, Unit> function12 = function1;
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj3, resumedState((NotCompleted) obj3, obj5, this.resumeMode, function12, obj6))) {
                detachChildIfNonResuable();
                return CancellableContinuationImplKt.RESUME_TOKEN;
            }
            obj = obj5;
            function1 = function12;
            obj2 = obj6;
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResumeWithException(@NotNull Throwable th) {
        return tryResumeImpl(new CompletedExceptionally(th, false, 2, null), null, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResume(T t, @Nullable Object obj, @Nullable Function1<? super Throwable, Unit> function1) {
        return tryResumeImpl(t, obj, function1);
    }

    private final boolean tryResume() {
        do {
            int i = this._decision;
            if (i != 0) {
                if (i == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }

    public final void callCancelHandler(Function1<? super Throwable, Unit> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }
}
