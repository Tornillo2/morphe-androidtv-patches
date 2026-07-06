package kotlin.coroutines;

import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ContinuationKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> Continuation<T> Continuation(final CoroutineContext context, final Function1<? super Result<? extends T>, Unit> resumeWith) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resumeWith, "resumeWith");
        return new Continuation<T>() { // from class: kotlin.coroutines.ContinuationKt.Continuation.1
            @Override // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return context;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(Object obj) {
                resumeWith.invoke(new Result<>(obj));
            }
        };
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> Continuation<Unit> createCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(function1, "<this>");
        Intrinsics.checkNotNullParameter(completion, "completion");
        return new SafeContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, completion)), CoroutineSingletons.COROUTINE_SUSPENDED);
    }

    public static final CoroutineContext getCoroutineContext() {
        throw new NotImplementedError("Implemented as intrinsic");
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> void resume(Continuation<? super T> continuation, T t) {
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        continuation.resumeWith(t);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> void resumeWithException(Continuation<? super T> continuation, Throwable exception) {
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        Intrinsics.checkNotNullParameter(exception, "exception");
        continuation.resumeWith(ResultKt.createFailure(exception));
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void startCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(function1, "<this>");
        Intrinsics.checkNotNullParameter(completion, "completion");
        IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, completion)).resumeWith(Unit.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> Object suspendCoroutine(Function1<? super Continuation<? super T>, Unit> function1, Continuation<? super T> continuation) throws Throwable {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        function1.invoke(safeContinuation);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == CoroutineSingletons.COROUTINE_SUSPENDED) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    @SinceKotlin(version = "1.3")
    public static final <R, T> void startCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(function2, "<this>");
        Intrinsics.checkNotNullParameter(completion, "completion");
        IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, completion)).resumeWith(Unit.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <R, T> Continuation<Unit> createCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(function2, "<this>");
        Intrinsics.checkNotNullParameter(completion, "completion");
        return new SafeContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, completion)), CoroutineSingletons.COROUTINE_SUSPENDED);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static /* synthetic */ void getCoroutineContext$annotations() {
    }
}
