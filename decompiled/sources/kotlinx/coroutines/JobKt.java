package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public final class JobKt {
    @NotNull
    public static final CompletableJob Job(@Nullable Job job) {
        return new JobImpl(job);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancel(CoroutineContext coroutineContext) {
        JobKt__JobKt.cancel(coroutineContext, (CancellationException) null);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        return JobKt__JobKt.cancelAndJoin(job, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(CoroutineContext coroutineContext) {
        JobKt__JobKt.cancelChildren(coroutineContext, (CancellationException) null);
    }

    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        return JobKt__FutureKt.cancelFutureOnCompletion(job, future);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        return JobKt__JobKt.disposeOnCompletion(job, disposableHandle);
    }

    public static final void ensureActive(@NotNull CoroutineContext coroutineContext) {
        JobKt__JobKt.ensureActive(coroutineContext);
    }

    @NotNull
    public static final Job getJob(@NotNull CoroutineContext coroutineContext) {
        return JobKt__JobKt.getJob(coroutineContext);
    }

    public static final boolean isActive(@NotNull CoroutineContext coroutineContext) {
        return JobKt__JobKt.isActive(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    @JvmName(name = "Job")
    /* JADX INFO: renamed from: Job, reason: collision with other method in class */
    public static final Job m2070Job(Job job) {
        return new JobImpl(job);
    }

    public static final void cancel(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancel(coroutineContext, cancellationException);
    }

    public static final void ensureActive(@NotNull Job job) {
        JobKt__JobKt.ensureActive(job);
    }

    public static final void cancel(@NotNull Job job, @NotNull String str, @Nullable Throwable th) {
        JobKt__JobKt.cancel(job, str, th);
    }

    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final void cancelChildren(Job job) {
        JobKt__JobKt.cancelChildren(job, (CancellationException) null);
    }

    public static final void cancelChildren(@NotNull Job job, @Nullable CancellationException cancellationException) {
        JobKt__JobKt.cancelChildren(job, cancellationException);
    }
}
