package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CompletableDeferredImpl<T> extends JobSupport implements CompletableDeferred<T>, SelectClause1<T> {
    public CompletableDeferredImpl(@Nullable Job job) {
        super(true);
        initParentJob(job);
    }

    @Override // kotlinx.coroutines.Deferred
    @Nullable
    public Object await(@NotNull Continuation<? super T> continuation) throws Throwable {
        Object objAwaitInternal$kotlinx_coroutines_core = awaitInternal$kotlinx_coroutines_core(continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return objAwaitInternal$kotlinx_coroutines_core;
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean complete(T t) {
        return makeCompleting$kotlinx_coroutines_core(t);
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean completeExceptionally(@NotNull Throwable th) {
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(th, false, 2, null));
    }

    @Override // kotlinx.coroutines.Deferred
    public T getCompleted() {
        return (T) getCompletedInternal$kotlinx_coroutines_core();
    }

    @Override // kotlinx.coroutines.selects.SelectClause1
    public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        registerSelectClause1Internal$kotlinx_coroutines_core(selectInstance, function2);
    }

    @Override // kotlinx.coroutines.Deferred
    @NotNull
    public SelectClause1<T> getOnAwait() {
        return this;
    }
}
