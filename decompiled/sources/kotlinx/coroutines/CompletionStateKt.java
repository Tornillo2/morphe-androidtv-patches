package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CompletionStateKt {
    @NotNull
    public static final <T> Object recoverResult(@Nullable Object obj, @NotNull Continuation<? super T> continuation) {
        return obj instanceof CompletedExceptionally ? ResultKt.createFailure(((CompletedExceptionally) obj).cause) : obj;
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @Nullable Function1<? super Throwable, Unit> function1) {
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        return thM583exceptionOrNullimpl == null ? function1 != null ? new CompletedWithCancellation(obj, function1) : obj : new CompletedExceptionally(thM583exceptionOrNullimpl, false, 2, null);
    }

    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, (Function1<? super Throwable, Unit>) function1);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @NotNull CancellableContinuation<?> cancellableContinuation) {
        Throwable thM583exceptionOrNullimpl = Result.m583exceptionOrNullimpl(obj);
        return thM583exceptionOrNullimpl == null ? obj : new CompletedExceptionally(thM583exceptionOrNullimpl, false, 2, null);
    }
}
