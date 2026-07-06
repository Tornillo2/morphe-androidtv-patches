package kotlin.coroutines;

import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CoroutineContextImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final <E extends CoroutineContext.Element> E getPolymorphicElement(@NotNull CoroutineContext.Element element, @NotNull CoroutineContext.Key<E> key) {
        E e;
        Intrinsics.checkNotNullParameter(element, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (!abstractCoroutineContextKey.isSubKey$kotlin_stdlib(element.getKey()) || (e = (E) abstractCoroutineContextKey.tryCast$kotlin_stdlib(element)) == null) {
                return null;
            }
            return e;
        }
        if (element.getKey() == key) {
            return element;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final CoroutineContext minusPolymorphicKey(@NotNull CoroutineContext.Element element, @NotNull CoroutineContext.Key<?> key) {
        Intrinsics.checkNotNullParameter(element, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!(key instanceof AbstractCoroutineContextKey)) {
            return element.getKey() == key ? EmptyCoroutineContext.INSTANCE : element;
        }
        AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
        return (!abstractCoroutineContextKey.isSubKey$kotlin_stdlib(element.getKey()) || abstractCoroutineContextKey.tryCast$kotlin_stdlib(element) == null) ? element : EmptyCoroutineContext.INSTANCE;
    }
}
