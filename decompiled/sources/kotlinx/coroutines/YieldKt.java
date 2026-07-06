package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class YieldKt {
    @Nullable
    public static final Object yield(@NotNull Continuation<? super Unit> continuation) {
        Object obj;
        CoroutineContext context = continuation.getContext();
        JobKt__JobKt.ensureActive(context);
        Continuation continuationIntercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        DispatchedContinuation dispatchedContinuation = continuationIntercepted instanceof DispatchedContinuation ? (DispatchedContinuation) continuationIntercepted : null;
        if (dispatchedContinuation == null) {
            obj = Unit.INSTANCE;
        } else {
            if (dispatchedContinuation.dispatcher.isDispatchNeeded(context)) {
                dispatchedContinuation.dispatchYield$kotlinx_coroutines_core(context, Unit.INSTANCE);
            } else {
                YieldContext yieldContext = new YieldContext();
                CoroutineContext coroutineContextPlus = context.plus(yieldContext);
                Unit unit = Unit.INSTANCE;
                dispatchedContinuation.dispatchYield$kotlinx_coroutines_core(coroutineContextPlus, unit);
                obj = (!yieldContext.dispatcherWasUnconfined || DispatchedContinuationKt.yieldUndispatched(dispatchedContinuation)) ? CoroutineSingletons.COROUTINE_SUSPENDED : unit;
            }
            obj = CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        return obj == CoroutineSingletons.COROUTINE_SUSPENDED ? obj : Unit.INSTANCE;
    }
}
