package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkTimer {
    public static final String TAG = Logger.tagWithPrefix("WorkTimer");
    public final ThreadFactory mBackgroundThreadFactory;
    public final ScheduledExecutorService mExecutorService;
    public final Map<String, TimeLimitExceededListener> mListeners;
    public final Object mLock;
    public final Map<String, WorkTimerRunnable> mTimerMap;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(@NonNull String workSpecId);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class WorkTimerRunnable implements Runnable {
        public static final String TAG = "WrkTimerRunnable";
        public final String mWorkSpecId;
        public final WorkTimer mWorkTimer;

        public WorkTimerRunnable(@NonNull WorkTimer workTimer, @NonNull String workSpecId) {
            this.mWorkTimer = workTimer;
            this.mWorkSpecId = workSpecId;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.mWorkTimer.mLock) {
                try {
                    if (this.mWorkTimer.mTimerMap.remove(this.mWorkSpecId) != null) {
                        TimeLimitExceededListener timeLimitExceededListenerRemove = this.mWorkTimer.mListeners.remove(this.mWorkSpecId);
                        if (timeLimitExceededListenerRemove != null) {
                            timeLimitExceededListenerRemove.onTimeLimitExceeded(this.mWorkSpecId);
                        }
                    } else {
                        Logger.get().debug(TAG, String.format("Timer with %s is already marked as complete.", this.mWorkSpecId), new Throwable[0]);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public WorkTimer() {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: androidx.work.impl.utils.WorkTimer.1
            public int mThreadsCreated = 0;

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(@NonNull Runnable r) {
                Thread threadNewThread = Executors.defaultThreadFactory().newThread(r);
                threadNewThread.setName("WorkManager-WorkTimer-thread-" + this.mThreadsCreated);
                this.mThreadsCreated = this.mThreadsCreated + 1;
                return threadNewThread;
            }
        };
        this.mBackgroundThreadFactory = threadFactory;
        this.mTimerMap = new HashMap();
        this.mListeners = new HashMap();
        this.mLock = new Object();
        this.mExecutorService = Executors.newSingleThreadScheduledExecutor(threadFactory);
    }

    @NonNull
    @VisibleForTesting
    public ScheduledExecutorService getExecutorService() {
        return this.mExecutorService;
    }

    @NonNull
    @VisibleForTesting
    public synchronized Map<String, TimeLimitExceededListener> getListeners() {
        return this.mListeners;
    }

    @NonNull
    @VisibleForTesting
    public synchronized Map<String, WorkTimerRunnable> getTimerMap() {
        return this.mTimerMap;
    }

    public void onDestroy() {
        if (this.mExecutorService.isShutdown()) {
            return;
        }
        this.mExecutorService.shutdownNow();
    }

    public void startTimer(@NonNull final String workSpecId, long processingTimeMillis, @NonNull TimeLimitExceededListener listener) {
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Starting timer for %s", workSpecId), new Throwable[0]);
            stopTimer(workSpecId);
            WorkTimerRunnable workTimerRunnable = new WorkTimerRunnable(this, workSpecId);
            this.mTimerMap.put(workSpecId, workTimerRunnable);
            this.mListeners.put(workSpecId, listener);
            this.mExecutorService.schedule(workTimerRunnable, processingTimeMillis, TimeUnit.MILLISECONDS);
        }
    }

    public void stopTimer(@NonNull final String workSpecId) {
        synchronized (this.mLock) {
            try {
                if (this.mTimerMap.remove(workSpecId) != null) {
                    Logger.get().debug(TAG, String.format("Stopping timer for %s", workSpecId), new Throwable[0]);
                    this.mListeners.remove(workSpecId);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
