package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface Deferred<T> extends Job {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static <T, R> R fold(@NotNull Deferred<? extends T> deferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(deferred, r, function2);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) CoroutineContext.Element.DefaultImpls.get(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<?> key) {
            return CoroutineContext.Element.DefaultImpls.minusKey(deferred, key);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        @NotNull
        public static <T> Job plus(@NotNull Deferred<? extends T> deferred, @NotNull Job job) {
            return job;
        }

        @NotNull
        public static <T> CoroutineContext plus(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext coroutineContext) {
            return CoroutineContext.Element.DefaultImpls.plus(deferred, coroutineContext);
        }
    }

    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    @ExperimentalCoroutinesApi
    T getCompleted();

    @ExperimentalCoroutinesApi
    @Nullable
    Throwable getCompletionExceptionOrNull();

    @NotNull
    SelectClause1<T> getOnAwait();
}
