package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@InternalCoroutinesApi
public abstract class AbstractCoroutine<T> extends JobSupport implements Job, Continuation<T>, CoroutineScope {

    @NotNull
    public final CoroutineContext context;

    public AbstractCoroutine(@NotNull CoroutineContext coroutineContext, boolean z, boolean z2) {
        super(z2);
        if (z) {
            initParentJob((Job) coroutineContext.get(Job.Key));
        }
        this.context = coroutineContext.plus(this);
    }

    public void afterResume(@Nullable Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.JobSupport
    @NotNull
    public String cancellationExceptionMessage() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, th);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return super.nameString$kotlinx_coroutines_core();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.JobSupport
    public final void onCompletionInternal(@Nullable Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            onCompleted(obj);
        } else {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            onCancelled(completedExceptionally.cause, completedExceptionally.getHandled());
        }
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(@NotNull Object obj) {
        Object objMakeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState$default(obj, null, 1, null));
        if (objMakeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(objMakeCompletingOnce$kotlinx_coroutines_core);
    }

    public final <R> void start(@NotNull CoroutineStart coroutineStart, R r, @NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        coroutineStart.invoke(function2, r, this);
    }

    public static /* synthetic */ void getContext$annotations() {
    }

    public void onCompleted(T t) {
    }

    public void onCancelled(@NotNull Throwable th, boolean z) {
    }
}
