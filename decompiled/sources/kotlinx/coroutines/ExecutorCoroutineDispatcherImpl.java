package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.ConcurrentKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {

    @NotNull
    public final Executor executor;

    public ExecutorCoroutineDispatcherImpl(@NotNull Executor executor) {
        this.executor = executor;
        ConcurrentKt.removeFutureOnCancel(executor);
    }

    public final void cancelJobOnRejection(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        JobKt__JobKt.cancel(coroutineContext, ExceptionsKt.CancellationException("The task was rejected", rejectedExecutionException));
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = this.executor;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* JADX INFO: renamed from: dispatch */
    public void mo2130dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Runnable runnableWrapTask;
        try {
            Executor executor = this.executor;
            AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
            if (abstractTimeSource == null || (runnableWrapTask = abstractTimeSource.wrapTask(runnable)) == null) {
                runnableWrapTask = runnable;
            }
            executor.execute(runnableWrapTask);
        } catch (RejectedExecutionException e) {
            AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.timeSource;
            if (abstractTimeSource2 != null) {
                abstractTimeSource2.unTrackTask();
            }
            cancelJobOnRejection(coroutineContext, e);
            Dispatchers.getIO().mo2130dispatch(coroutineContext, runnable);
        }
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).executor == this.executor;
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this.executor;
    }

    public int hashCode() {
        return System.identityHashCode(this.executor);
    }

    @Override // kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        long j2;
        Runnable runnable2;
        Executor executor = this.executor;
        ScheduledFuture<?> scheduledFutureScheduleBlock = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            j2 = j;
            runnable2 = runnable;
            scheduledFutureScheduleBlock = scheduleBlock(scheduledExecutorService, runnable2, coroutineContext, j2);
        } else {
            j2 = j;
            runnable2 = runnable;
        }
        return scheduledFutureScheduleBlock != null ? new DisposableFutureHandle(scheduledFutureScheduleBlock) : DefaultExecutor.INSTANCE.scheduleInvokeOnTimeout(j2, runnable2);
    }

    public final ScheduledFuture<?> scheduleBlock(ScheduledExecutorService scheduledExecutorService, Runnable runnable, CoroutineContext coroutineContext, long j) {
        try {
            return scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection(coroutineContext, e);
            return null;
        }
    }

    @Override // kotlinx.coroutines.Delay
    /* JADX INFO: renamed from: scheduleResumeAfterDelay */
    public void mo2131scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        long j2;
        Executor executor = this.executor;
        ScheduledFuture<?> scheduledFutureScheduleBlock = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            j2 = j;
            scheduledFutureScheduleBlock = scheduleBlock(scheduledExecutorService, new ResumeUndispatchedRunnable(this, cancellableContinuation), cancellableContinuation.getContext(), j2);
        } else {
            j2 = j;
        }
        if (scheduledFutureScheduleBlock != null) {
            JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, scheduledFutureScheduleBlock);
        } else {
            DefaultExecutor.INSTANCE.mo2131scheduleResumeAfterDelay(j2, cancellableContinuation);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return this.executor.toString();
    }
}
