package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ThreadContextKt {

    @JvmField
    @NotNull
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");

    @NotNull
    public static final Function2<Object, CoroutineContext.Element, Object> countAll = ThreadContextKt$countAll$1.INSTANCE;

    @NotNull
    public static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> findOne = ThreadContextKt$findOne$1.INSTANCE;

    @NotNull
    public static final Function2<ThreadState, CoroutineContext.Element, ThreadState> updateState = ThreadContextKt$updateState$1.INSTANCE;

    public static final void restoreThreadContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (obj == NO_THREAD_ELEMENTS) {
            return;
        }
        if (obj instanceof ThreadState) {
            ((ThreadState) obj).restore(coroutineContext);
            return;
        }
        Object objFold = coroutineContext.fold(null, findOne);
        if (objFold == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        }
        ((ThreadContextElement) objFold).restoreThreadContext(coroutineContext, obj);
    }

    @NotNull
    public static final Object threadContextElements(@NotNull CoroutineContext coroutineContext) {
        Object objFold = coroutineContext.fold(0, countAll);
        Intrinsics.checkNotNull(objFold);
        return objFold;
    }

    @Nullable
    public static final Object updateThreadContext(@NotNull CoroutineContext coroutineContext, @Nullable Object obj) {
        if (obj == null) {
            obj = threadContextElements(coroutineContext);
        }
        return obj == 0 ? NO_THREAD_ELEMENTS : obj instanceof Integer ? coroutineContext.fold(new ThreadState(coroutineContext, ((Number) obj).intValue()), updateState) : ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }
}
