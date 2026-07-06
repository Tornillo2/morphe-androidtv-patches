package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface CancellableContinuation<T> extends Continuation<T> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean cancel$default(CancellableContinuation cancellableContinuation, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                th = null;
            }
            return cancellableContinuation.cancel(th);
        }

        public static /* synthetic */ Object tryResume$default(CancellableContinuation cancellableContinuation, Object obj, Object obj2, int i, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
            }
            if ((i & 2) != 0) {
                obj2 = null;
            }
            return cancellableContinuation.tryResume(obj, obj2);
        }
    }

    boolean cancel(@Nullable Throwable th);

    @InternalCoroutinesApi
    void completeResume(@NotNull Object obj);

    @InternalCoroutinesApi
    void initCancellability();

    void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> function1);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    @ExperimentalCoroutinesApi
    void resume(T t, @Nullable Function1<? super Throwable, Unit> function1);

    @ExperimentalCoroutinesApi
    void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t);

    @ExperimentalCoroutinesApi
    void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t, @Nullable Object obj);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T t, @Nullable Object obj, @Nullable Function1<? super Throwable, Unit> function1);

    @InternalCoroutinesApi
    @Nullable
    Object tryResumeWithException(@NotNull Throwable th);
}
