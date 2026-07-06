package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TimedSemaphore {
    public static final int NO_LIMIT = 0;
    public static final int THREAD_POOL_SIZE = 1;
    public int acquireCount;
    public final ScheduledExecutorService executorService;
    public int lastCallsPerPeriod;
    public int limit;
    public final boolean ownExecutor;
    public final long period;
    public long periodCount;
    public boolean shutdown;
    public ScheduledFuture<?> task;
    public long totalAcquireCount;
    public final TimeUnit unit;

    public TimedSemaphore(long j, TimeUnit timeUnit, int i) {
        this(null, j, timeUnit, i);
    }

    public synchronized void acquire() throws InterruptedException {
        boolean zAcquirePermit;
        prepareAcquire();
        do {
            zAcquirePermit = acquirePermit();
            if (!zAcquirePermit) {
                wait();
            }
        } while (!zAcquirePermit);
    }

    public final boolean acquirePermit() {
        if (getLimit() > 0 && this.acquireCount >= getLimit()) {
            return false;
        }
        this.acquireCount++;
        return true;
    }

    public synchronized void endOfPeriod() {
        int i = this.acquireCount;
        this.lastCallsPerPeriod = i;
        this.totalAcquireCount += (long) i;
        this.periodCount++;
        this.acquireCount = 0;
        notifyAll();
    }

    public synchronized int getAcquireCount() {
        return this.acquireCount;
    }

    public synchronized int getAvailablePermits() {
        return getLimit() - getAcquireCount();
    }

    public synchronized double getAverageCallsPerPeriod() {
        long j;
        j = this.periodCount;
        return j == 0 ? 0.0d : this.totalAcquireCount / j;
    }

    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    public synchronized int getLastAcquiresPerPeriod() {
        return this.lastCallsPerPeriod;
    }

    public final synchronized int getLimit() {
        return this.limit;
    }

    public long getPeriod() {
        return this.period;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public final void prepareAcquire() {
        if (isShutdown()) {
            throw new IllegalStateException("TimedSemaphore is shut down!");
        }
        if (this.task == null) {
            this.task = startTimer();
        }
    }

    public final synchronized void setLimit(int i) {
        this.limit = i;
    }

    public synchronized void shutdown() {
        try {
            if (!this.shutdown) {
                if (this.ownExecutor) {
                    getExecutorService().shutdownNow();
                }
                ScheduledFuture<?> scheduledFuture = this.task;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                this.shutdown = true;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public ScheduledFuture<?> startTimer() {
        return getExecutorService().scheduleAtFixedRate(new Runnable() { // from class: org.apache.commons.lang3.concurrent.TimedSemaphore.1
            @Override // java.lang.Runnable
            public void run() {
                TimedSemaphore.this.endOfPeriod();
            }
        }, getPeriod(), getPeriod(), getUnit());
    }

    public synchronized boolean tryAcquire() {
        prepareAcquire();
        return acquirePermit();
    }

    public TimedSemaphore(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit, int i) {
        Validate.inclusiveBetween(1L, Long.MAX_VALUE, j, "Time period must be greater than 0!");
        this.period = j;
        this.unit = timeUnit;
        if (scheduledExecutorService != null) {
            this.executorService = scheduledExecutorService;
            this.ownExecutor = false;
        } else {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            this.executorService = scheduledThreadPoolExecutor;
            this.ownExecutor = true;
        }
        setLimit(i);
    }
}
