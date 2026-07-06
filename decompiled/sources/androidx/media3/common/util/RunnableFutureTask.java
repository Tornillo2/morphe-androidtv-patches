package androidx.media3.common.util;

import androidx.annotation.Nullable;
import java.lang.Exception;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class RunnableFutureTask<R, E extends Exception> implements RunnableFuture<R> {
    public boolean canceled;

    @Nullable
    public Exception exception;

    @Nullable
    public R result;

    @Nullable
    public Thread workThread;
    public final ConditionVariable started = new ConditionVariable();
    public final ConditionVariable finished = new ConditionVariable();
    public final Object cancelLock = new Object();

    public final void blockUntilFinished() {
        this.finished.blockUninterruptible();
    }

    public final void blockUntilStarted() {
        this.started.blockUninterruptible();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        synchronized (this.cancelLock) {
            try {
                if (!this.canceled && !this.finished.isOpen()) {
                    this.canceled = true;
                    cancelWork();
                    Thread thread = this.workThread;
                    if (thread == null) {
                        this.started.open();
                        this.finished.open();
                    } else if (z) {
                        thread.interrupt();
                    }
                    return true;
                }
                return false;
            } finally {
            }
        }
    }

    @UnknownNull
    public abstract R doWork() throws Exception;

    @Override // java.util.concurrent.Future
    @UnknownNull
    public final R get() throws ExecutionException, InterruptedException {
        this.finished.block();
        return getResult();
    }

    @UnknownNull
    public final R getResult() throws ExecutionException {
        if (this.canceled) {
            throw new CancellationException();
        }
        if (this.exception == null) {
            return this.result;
        }
        throw new ExecutionException(this.exception);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.canceled;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.finished.isOpen();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        synchronized (this.cancelLock) {
            try {
                if (this.canceled) {
                    return;
                }
                this.workThread = Thread.currentThread();
                this.started.open();
                try {
                    try {
                        this.result = doWork();
                        synchronized (this.cancelLock) {
                            this.finished.open();
                            this.workThread = null;
                            Thread.interrupted();
                        }
                    } catch (Throwable th) {
                        synchronized (this.cancelLock) {
                            this.finished.open();
                            this.workThread = null;
                            Thread.interrupted();
                            throw th;
                        }
                    }
                } catch (Exception e) {
                    this.exception = e;
                    synchronized (this.cancelLock) {
                        this.finished.open();
                        this.workThread = null;
                        Thread.interrupted();
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // java.util.concurrent.Future
    @UnknownNull
    public final R get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.finished.block(TimeUnit.MILLISECONDS.convert(j, timeUnit))) {
            return getResult();
        }
        throw new TimeoutException();
    }

    public void cancelWork() {
    }
}
