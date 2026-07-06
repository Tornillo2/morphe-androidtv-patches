package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.ThreadLocalElement;
import kotlinx.coroutines.internal.ThreadLocalKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ThreadContextElementKt {
    @NotNull
    public static final <T> ThreadContextElement<T> asContextElement(@NotNull ThreadLocal<T> threadLocal, T t) {
        return new ThreadLocalElement(t, threadLocal);
    }

    public static ThreadContextElement asContextElement$default(ThreadLocal threadLocal, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = threadLocal.get();
        }
        return new ThreadLocalElement(obj, threadLocal);
    }

    @Nullable
    public static final Object ensurePresent(@NotNull ThreadLocal<?> threadLocal, @NotNull Continuation<? super Unit> continuation) {
        if (continuation.getContext().get(new ThreadLocalKey(threadLocal)) != null) {
            return Unit.INSTANCE;
        }
        throw new IllegalStateException(("ThreadLocal " + threadLocal + " is missing from context " + continuation.getContext()).toString());
    }

    public static final Object ensurePresent$$forInline(ThreadLocal<?> threadLocal, Continuation<? super Unit> continuation) {
        throw null;
    }

    @Nullable
    public static final Object isPresent(@NotNull ThreadLocal<?> threadLocal, @NotNull Continuation<? super Boolean> continuation) {
        return Boolean.valueOf(continuation.getContext().get(new ThreadLocalKey(threadLocal)) != null);
    }

    public static final Object isPresent$$forInline(ThreadLocal<?> threadLocal, Continuation<? super Boolean> continuation) {
        throw null;
    }
}
