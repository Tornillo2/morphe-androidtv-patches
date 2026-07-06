package kotlinx.coroutines.intrinsics;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CancellableKt {
    public static final void dispatcherFailure(Continuation<?> continuation, Throwable th) throws Throwable {
        continuation.resumeWith(ResultKt.createFailure(th));
        throw th;
    }

    public static final void runSafely(Continuation<?> continuation, Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Throwable th) {
            continuation.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }

    @InternalCoroutinesApi
    public static final <T> void startCoroutineCancellable(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        try {
            DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, continuation)), Unit.INSTANCE, null, 2, null);
        } catch (Throwable th) {
            continuation.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }

    public static /* synthetic */ void startCoroutineCancellable$default(Function2 function2, Object obj, Continuation continuation, Function1 function1, int i, Object obj2) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        startCoroutineCancellable(function2, obj, continuation, function1);
    }

    public static final <R, T> void startCoroutineCancellable(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation, @Nullable Function1<? super Throwable, Unit> function1) {
        try {
            DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, continuation)), Unit.INSTANCE, function1);
        } catch (Throwable th) {
            continuation.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }

    public static final void startCoroutineCancellable(@NotNull Continuation<? super Unit> continuation, @NotNull Continuation<?> continuation2) {
        try {
            DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), Unit.INSTANCE, null, 2, null);
        } catch (Throwable th) {
            continuation2.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }
}
