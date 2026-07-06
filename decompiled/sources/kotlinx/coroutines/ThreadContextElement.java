package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ThreadContextElement<S> extends CoroutineContext.Element {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static <S, R> R fold(@NotNull ThreadContextElement<S> threadContextElement, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(threadContextElement, r, function2);
        }

        @Nullable
        public static <S, E extends CoroutineContext.Element> E get(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext.Key<E> key) {
            return (E) CoroutineContext.Element.DefaultImpls.get(threadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext minusKey(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext.Key<?> key) {
            return CoroutineContext.Element.DefaultImpls.minusKey(threadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext plus(@NotNull ThreadContextElement<S> threadContextElement, @NotNull CoroutineContext coroutineContext) {
            return CoroutineContext.Element.DefaultImpls.plus(threadContextElement, coroutineContext);
        }
    }

    void restoreThreadContext(@NotNull CoroutineContext coroutineContext, S s);

    S updateThreadContext(@NotNull CoroutineContext coroutineContext);
}
