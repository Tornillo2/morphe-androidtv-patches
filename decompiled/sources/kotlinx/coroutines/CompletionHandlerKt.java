package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CompletionHandlerKt {
    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CancelHandlerBase cancelHandlerBase) {
        return cancelHandlerBase;
    }

    public static final void invokeIt(@NotNull Function1<? super Throwable, Unit> function1, @Nullable Throwable th) {
        function1.invoke(th);
    }

    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CompletionHandlerBase completionHandlerBase) {
        return completionHandlerBase;
    }
}
