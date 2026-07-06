package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class DirectExecutorService extends AbstractListeningExecutorService {
    public final Object lock = new Object();

    @GuardedBy("lock")
    public int runningTasks = 0;

    @GuardedBy("lock")
    public boolean shutdown = false;

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        synchronized (this.lock) {
            while (true) {
                try {
                    if (this.shutdown && this.runningTasks == 0) {
                        return true;
                    }
                    if (nanos <= 0) {
                        return false;
                    }
                    long jNanoTime = System.nanoTime();
                    TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                    nanos -= System.nanoTime() - jNanoTime;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void endTask() {
        synchronized (this.lock) {
            try {
                int i = this.runningTasks - 1;
                this.runningTasks = i;
                if (i == 0) {
                    this.lock.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        startTask();
        try {
            command.run();
        } finally {
            endTask();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            try {
                z = this.shutdown && this.runningTasks == 0;
            } finally {
            }
        }
        return z;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        synchronized (this.lock) {
            try {
                this.shutdown = true;
                if (this.runningTasks == 0) {
                    this.lock.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.EMPTY_LIST;
    }

    public final void startTask() {
        synchronized (this.lock) {
            try {
                if (this.shutdown) {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
                this.runningTasks++;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
