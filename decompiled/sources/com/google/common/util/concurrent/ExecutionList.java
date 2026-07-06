package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class ExecutionList {
    public static final LazyLogger log = new LazyLogger(ExecutionList.class);

    @GuardedBy("this")
    public boolean executed;

    @GuardedBy("this")
    public RunnableExecutorPair runnables;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RunnableExecutorPair {
        public final Executor executor;
        public RunnableExecutorPair next;
        public final Runnable runnable;

        public RunnableExecutorPair(Runnable runnable, Executor executor, RunnableExecutorPair next) {
            this.runnable = runnable;
            this.executor = executor;
            this.next = next;
        }
    }

    public static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            try {
                if (this.executed) {
                    executeListener(runnable, executor);
                } else {
                    this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void execute() {
        synchronized (this) {
            try {
                if (this.executed) {
                    return;
                }
                this.executed = true;
                RunnableExecutorPair runnableExecutorPair = this.runnables;
                RunnableExecutorPair runnableExecutorPair2 = null;
                this.runnables = null;
                while (runnableExecutorPair != null) {
                    RunnableExecutorPair runnableExecutorPair3 = runnableExecutorPair.next;
                    runnableExecutorPair.next = runnableExecutorPair2;
                    runnableExecutorPair2 = runnableExecutorPair;
                    runnableExecutorPair = runnableExecutorPair3;
                }
                while (runnableExecutorPair2 != null) {
                    executeListener(runnableExecutorPair2.runnable, runnableExecutorPair2.executor);
                    runnableExecutorPair2 = runnableExecutorPair2.next;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
