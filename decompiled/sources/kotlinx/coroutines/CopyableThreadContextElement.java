package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static <S, R> R fold(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(copyableThreadContextElement, r, function2);
        }

        @Nullable
        public static <S, E extends CoroutineContext.Element> E get(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext.Key<E> key) {
            return (E) CoroutineContext.Element.DefaultImpls.get(copyableThreadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext minusKey(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext.Key<?> key) {
            return CoroutineContext.Element.DefaultImpls.minusKey(copyableThreadContextElement, key);
        }

        @NotNull
        public static <S> CoroutineContext plus(@NotNull CopyableThreadContextElement<S> copyableThreadContextElement, @NotNull CoroutineContext coroutineContext) {
            return CoroutineContext.Element.DefaultImpls.plus(copyableThreadContextElement, coroutineContext);
        }
    }

    @NotNull
    CopyableThreadContextElement<S> copyForChild();

    @NotNull
    CoroutineContext mergeForChild(@NotNull CoroutineContext.Element element);
}
