package kotlinx.coroutines.flow.internal;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SafeCollector<T> extends ContinuationImpl implements FlowCollector<T>, CoroutineStackFrame {

    @JvmField
    @NotNull
    public final CoroutineContext collectContext;

    @JvmField
    public final int collectContextSize;

    @JvmField
    @NotNull
    public final FlowCollector<T> collector;

    @Nullable
    public Continuation<? super Unit> completion;

    @Nullable
    public CoroutineContext lastEmissionContext;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeCollector(@NotNull FlowCollector<? super T> flowCollector, @NotNull CoroutineContext coroutineContext) {
        super(NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE);
        this.collector = flowCollector;
        this.collectContext = coroutineContext;
        this.collectContextSize = ((Number) coroutineContext.fold(0, SafeCollector$collectContextSize$1.INSTANCE)).intValue();
    }

    public final void checkContext(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, T t) {
        if (coroutineContext2 instanceof DownstreamExceptionContext) {
            exceptionTransparencyViolated((DownstreamExceptionContext) coroutineContext2, t);
            throw null;
        }
        SafeCollector_commonKt.checkContext(this, coroutineContext);
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
        try {
            Object objEmit = emit(continuation, t);
            return objEmit == CoroutineSingletons.COROUTINE_SUSPENDED ? objEmit : Unit.INSTANCE;
        } catch (Throwable th) {
            this.lastEmissionContext = new DownstreamExceptionContext(th, continuation.getContext());
            throw th;
        }
    }

    public final void exceptionTransparencyViolated(DownstreamExceptionContext downstreamExceptionContext, Object obj) {
        throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + downstreamExceptionContext.e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<? super Unit> continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this.lastEmissionContext;
        return coroutineContext == null ? EmptyCoroutineContext.INSTANCE : coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public Object invokeSuspend(@NotNull Object obj) {
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        if (thM583exceptionOrNullimpl != null) {
            this.lastEmissionContext = new DownstreamExceptionContext(thM583exceptionOrNullimpl, getContext());
        }
        Continuation<? super Unit> continuation = this.completion;
        if (continuation != null) {
            continuation.resumeWith(obj);
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }

    public final Object emit(Continuation<? super Unit> continuation, T t) {
        CoroutineContext context = continuation.getContext();
        JobKt__JobKt.ensureActive(context);
        CoroutineContext coroutineContext = this.lastEmissionContext;
        if (coroutineContext != context) {
            checkContext(context, coroutineContext, t);
            this.lastEmissionContext = context;
        }
        this.completion = continuation;
        Object objInvoke = SafeCollectorKt.emitFun.invoke(this.collector, t, this);
        if (!Intrinsics.areEqual(objInvoke, CoroutineSingletons.COROUTINE_SUSPENDED)) {
            this.completion = null;
        }
        return objInvoke;
    }
}
