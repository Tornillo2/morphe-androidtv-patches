package kotlinx.coroutines.intrinsics;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class UndispatchedKt {
    public static final <T> void startCoroutineUndispatched(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        try {
            CoroutineContext context = completion.getContext();
            Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            try {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1);
                Object objInvoke = function1.invoke(completion);
                if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    completion.resumeWith(objInvoke);
                }
            } finally {
                ThreadContextKt.restoreThreadContext(context, objUpdateThreadContext);
            }
        } catch (Throwable th) {
            completion.resumeWith(ResultKt.createFailure(th));
        }
    }

    public static final <T> void startCoroutineUnintercepted(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        try {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1);
            Object objInvoke = function1.invoke(completion);
            if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                completion.resumeWith(objInvoke);
            }
        } catch (Throwable th) {
            completion.resumeWith(ResultKt.createFailure(th));
        }
    }

    public static final <T> void startDirect(Continuation<? super T> completion, Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        try {
            Object objInvoke = function1.invoke(completion);
            if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                completion.resumeWith(objInvoke);
            }
        } catch (Throwable th) {
            completion.resumeWith(ResultKt.createFailure(th));
        }
    }

    @Nullable
    public static final <T, R> Object startUndispatchedOrReturn(@NotNull ScopeCoroutine<? super T> scopeCoroutine, R r, @NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object completedExceptionally;
        Object objMakeCompletingOnce$kotlinx_coroutines_core;
        try {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
            completedExceptionally = function2.invoke(r, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (completedExceptionally == coroutineSingletons || (objMakeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return coroutineSingletons;
        }
        if (objMakeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) objMakeCompletingOnce$kotlinx_coroutines_core).cause;
        }
        return JobSupportKt.unboxState(objMakeCompletingOnce$kotlinx_coroutines_core);
    }

    @Nullable
    public static final <T, R> Object startUndispatchedOrReturnIgnoreTimeout(@NotNull ScopeCoroutine<? super T> scopeCoroutine, R r, @NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) throws Throwable {
        Object completedExceptionally;
        Object objMakeCompletingOnce$kotlinx_coroutines_core;
        try {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
            completedExceptionally = function2.invoke(r, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (completedExceptionally == coroutineSingletons || (objMakeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return coroutineSingletons;
        }
        if (objMakeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) objMakeCompletingOnce$kotlinx_coroutines_core).cause;
            if (!(th2 instanceof TimeoutCancellationException) || ((TimeoutCancellationException) th2).coroutine != scopeCoroutine) {
                throw th2;
            }
            if (completedExceptionally instanceof CompletedExceptionally) {
                throw ((CompletedExceptionally) completedExceptionally).cause;
            }
        } else {
            completedExceptionally = JobSupportKt.unboxState(objMakeCompletingOnce$kotlinx_coroutines_core);
        }
        return completedExceptionally;
    }

    public static final <T> Object undispatchedResult(ScopeCoroutine<? super T> scopeCoroutine, Function1<? super Throwable, Boolean> function1, Function0<? extends Object> function0) throws Throwable {
        Object completedExceptionally;
        Object objMakeCompletingOnce$kotlinx_coroutines_core;
        try {
            completedExceptionally = function0.invoke();
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (completedExceptionally == coroutineSingletons || (objMakeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return coroutineSingletons;
        }
        if (!(objMakeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(objMakeCompletingOnce$kotlinx_coroutines_core);
        }
        CompletedExceptionally completedExceptionally2 = (CompletedExceptionally) objMakeCompletingOnce$kotlinx_coroutines_core;
        if (function1.invoke(completedExceptionally2.cause).booleanValue()) {
            throw completedExceptionally2.cause;
        }
        if (completedExceptionally instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) completedExceptionally).cause;
        }
        return completedExceptionally;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <R, T> void startCoroutineUnintercepted(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        try {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
            Object objInvoke = function2.invoke(r, completion);
            if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                completion.resumeWith(objInvoke);
            }
        } catch (Throwable th) {
            completion.resumeWith(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <R, T> void startCoroutineUndispatched(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        try {
            CoroutineContext context = completion.getContext();
            Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            try {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
                Object objInvoke = function2.invoke(r, completion);
                if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    completion.resumeWith(objInvoke);
                }
            } finally {
                ThreadContextKt.restoreThreadContext(context, objUpdateThreadContext);
            }
        } catch (Throwable th) {
            completion.resumeWith(ResultKt.createFailure(th));
        }
    }
}
