package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@RestrictsSuspension
public abstract class SequenceScope<T> {
    @Nullable
    public abstract Object yield(T t, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Iterable<? extends T> iterable, @NotNull Continuation<? super Unit> continuation) {
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return Unit.INSTANCE;
        }
        Object objYieldAll = yieldAll(iterable.iterator(), continuation);
        return objYieldAll == CoroutineSingletons.COROUTINE_SUSPENDED ? objYieldAll : Unit.INSTANCE;
    }

    @Nullable
    public abstract Object yieldAll(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Sequence<? extends T> sequence, @NotNull Continuation<? super Unit> continuation) {
        Object objYieldAll = yieldAll(sequence.iterator(), continuation);
        return objYieldAll == CoroutineSingletons.COROUTINE_SUSPENDED ? objYieldAll : Unit.INSTANCE;
    }
}
