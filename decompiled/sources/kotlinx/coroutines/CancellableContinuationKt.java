package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CancellableContinuationKt {
    @InternalCoroutinesApi
    public static final void disposeOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull DisposableHandle disposableHandle) {
        cancellableContinuation.invokeOnCancellation(new DisposeOnCancel(disposableHandle));
    }

    @NotNull
    public static final <T> CancellableContinuationImpl<T> getOrCreateCancellableContinuation(@NotNull Continuation<? super T> continuation) {
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl<>(continuation, 1);
        }
        CancellableContinuationImpl<T> cancellableContinuationImplClaimReusableCancellableContinuation = ((DispatchedContinuation) continuation).claimReusableCancellableContinuation();
        if (cancellableContinuationImplClaimReusableCancellableContinuation != null) {
            if (!cancellableContinuationImplClaimReusableCancellableContinuation.resetStateReusable()) {
                cancellableContinuationImplClaimReusableCancellableContinuation = null;
            }
            if (cancellableContinuationImplClaimReusableCancellableContinuation != null) {
                return cancellableContinuationImplClaimReusableCancellableContinuation;
            }
        }
        return new CancellableContinuationImpl<>(continuation, 2);
    }

    public static final void removeOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        cancellableContinuation.invokeOnCancellation(new RemoveOnCancel(lockFreeLinkedListNode));
    }

    @Nullable
    public static final <T> Object suspendCancellableCoroutine(@NotNull Function1<? super CancellableContinuation<? super T>, Unit> function1, @NotNull Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        function1.invoke(cancellableContinuationImpl);
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    public static final <T> Object suspendCancellableCoroutine$$forInline(Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        function1.invoke(cancellableContinuationImpl);
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    @Nullable
    public static final <T> Object suspendCancellableCoroutineReusable(@NotNull Function1<? super CancellableContinuation<? super T>, Unit> function1, @NotNull Continuation<? super T> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        function1.invoke(orCreateCancellableContinuation);
        Object result = orCreateCancellableContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }

    public static final <T> Object suspendCancellableCoroutineReusable$$forInline(Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        function1.invoke(orCreateCancellableContinuation);
        Object result = orCreateCancellableContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }
}
