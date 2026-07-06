package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SerialExecutor implements Executor {
    public volatile Runnable mActive;
    public final Executor mExecutor;
    public final ArrayDeque<Task> mTasks = new ArrayDeque<>();
    public final Object mLock = new Object();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Task implements Runnable {
        public final Runnable mRunnable;
        public final SerialExecutor mSerialExecutor;

        public Task(@NonNull SerialExecutor serialExecutor, @NonNull Runnable runnable) {
            this.mSerialExecutor = serialExecutor;
            this.mRunnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mRunnable.run();
            } finally {
                this.mSerialExecutor.scheduleNext();
            }
        }
    }

    public SerialExecutor(@NonNull Executor executor) {
        this.mExecutor = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable command) {
        synchronized (this.mLock) {
            try {
                this.mTasks.add(new Task(this, command));
                if (this.mActive == null) {
                    scheduleNext();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    @VisibleForTesting
    public Executor getDelegatedExecutor() {
        return this.mExecutor;
    }

    public boolean hasPendingTasks() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mTasks.isEmpty();
        }
        return z;
    }

    public void scheduleNext() {
        synchronized (this.mLock) {
            try {
                Task taskPoll = this.mTasks.poll();
                this.mActive = taskPoll;
                if (taskPoll != null) {
                    this.mExecutor.execute(this.mActive);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
