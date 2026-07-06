package kotlin.coroutines;

import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
public interface CoroutineContext {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @NotNull
        public static CoroutineContext plus(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) context.fold(coroutineContext, new CoroutineContext$DefaultImpls$$ExternalSyntheticLambda0());
        }

        public static CoroutineContext plus$lambda$0(CoroutineContext acc, Element element) {
            Intrinsics.checkNotNullParameter(acc, "acc");
            Intrinsics.checkNotNullParameter(element, "element");
            CoroutineContext coroutineContextMinusKey = acc.minusKey(element.getKey());
            EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
            if (coroutineContextMinusKey == emptyCoroutineContext) {
                return element;
            }
            ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
            ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContextMinusKey.get(key);
            if (continuationInterceptor == null) {
                return new CombinedContext(coroutineContextMinusKey, element);
            }
            CoroutineContext coroutineContextMinusKey2 = coroutineContextMinusKey.minusKey(key);
            return coroutineContextMinusKey2 == emptyCoroutineContext ? new CombinedContext(element, continuationInterceptor) : new CombinedContext(new CombinedContext(coroutineContextMinusKey2, element), continuationInterceptor);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Element extends CoroutineContext {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class DefaultImpls {
            public static <R> R fold(@NotNull Element element, R r, @NotNull Function2<? super R, ? super Element, ? extends R> operation) {
                Intrinsics.checkNotNullParameter(operation, "operation");
                return operation.invoke(r, element);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Nullable
            public static <E extends Element> E get(@NotNull Element element, @NotNull Key<E> key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (Intrinsics.areEqual(element.getKey(), key)) {
                    return element;
                }
                return null;
            }

            @NotNull
            public static CoroutineContext minusKey(@NotNull Element element, @NotNull Key<?> key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return Intrinsics.areEqual(element.getKey(), key) ? EmptyCoroutineContext.INSTANCE : element;
            }

            @NotNull
            public static CoroutineContext plus(@NotNull Element element, @NotNull CoroutineContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                return DefaultImpls.plus(element, context);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

        @Override // kotlin.coroutines.CoroutineContext
        @Nullable
        <E extends Element> E get(@NotNull Key<E> key);

        @NotNull
        Key<?> getKey();

        @Override // kotlin.coroutines.CoroutineContext
        @NotNull
        CoroutineContext minusKey(@NotNull Key<?> key);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Key<E extends Element> {
    }

    <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

    @Nullable
    <E extends Element> E get(@NotNull Key<E> key);

    @NotNull
    CoroutineContext minusKey(@NotNull Key<?> key);

    @NotNull
    CoroutineContext plus(@NotNull CoroutineContext coroutineContext);
}
