package com.google.common.util.concurrent;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
public abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    public static final int MAX_BUSY_WAIT_SPINS = 1000;
    public static final Runnable DONE = new DoNothingRunnable();
    public static final Runnable PARKED = new DoNothingRunnable();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        public final InterruptibleTask<?> task;

        @VisibleForTesting
        public Thread getOwner() {
            return getExclusiveOwnerThread();
        }

        public final void setOwner(Thread thread) {
            setExclusiveOwnerThread(thread);
        }

        public String toString() {
            return this.task.toString();
        }

        public Blocker(InterruptibleTask<?> task) {
            this.task = task;
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DoNothingRunnable implements Runnable {
        public DoNothingRunnable() {
        }

        public DoNothingRunnable(AnonymousClass1 anonymousClass1) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public abstract void afterRanInterruptiblyFailure(Throwable error);

    public abstract void afterRanInterruptiblySuccess(@ParametricNullness T result);

    public final void interruptTask() {
        Runnable runnable = get();
        if (runnable instanceof Thread) {
            Blocker blocker = new Blocker(this);
            blocker.setExclusiveOwnerThread(Thread.currentThread());
            if (compareAndSet(runnable, blocker)) {
                try {
                    ((Thread) runnable).interrupt();
                } finally {
                    if (getAndSet(DONE) == PARKED) {
                        LockSupport.unpark((Thread) runnable);
                    }
                }
            }
        }
    }

    public abstract boolean isDone();

    @Override // java.lang.Runnable
    public final void run() {
        Thread threadCurrentThread = Thread.currentThread();
        T tRunInterruptibly = null;
        if (compareAndSet(null, threadCurrentThread)) {
            boolean zIsDone = isDone();
            if (!zIsDone) {
                try {
                    tRunInterruptibly = runInterruptibly();
                } catch (Throwable th) {
                    try {
                        Platform.restoreInterruptIfIsInterruptedException(th);
                        if (!compareAndSet(threadCurrentThread, DONE)) {
                            waitForInterrupt(threadCurrentThread);
                        }
                        if (zIsDone) {
                            return;
                        }
                        afterRanInterruptiblyFailure(th);
                        return;
                    } finally {
                        if (!compareAndSet(threadCurrentThread, DONE)) {
                            waitForInterrupt(threadCurrentThread);
                        }
                        if (!zIsDone) {
                            afterRanInterruptiblySuccess(null);
                        }
                    }
                }
            }
        }
    }

    @ParametricNullness
    public abstract T runInterruptibly() throws Exception;

    public abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable instanceof Blocker) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, ", ");
        sbM.append(toPendingString());
        return sbM.toString();
    }

    public final void waitForInterrupt(Thread currentThread) {
        Runnable runnable = get();
        Blocker blocker = null;
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = runnable instanceof Blocker;
            if (!z2 && runnable != PARKED) {
                break;
            }
            if (z2) {
                blocker = (Blocker) runnable;
            }
            i++;
            if (i > 1000) {
                Runnable runnable2 = PARKED;
                if (runnable == runnable2 || compareAndSet(runnable, runnable2)) {
                    z = Thread.interrupted() || z;
                    LockSupport.park(blocker);
                }
            } else {
                Thread.yield();
            }
            runnable = get();
        }
        if (z) {
            currentThread.interrupt();
        }
    }
}
