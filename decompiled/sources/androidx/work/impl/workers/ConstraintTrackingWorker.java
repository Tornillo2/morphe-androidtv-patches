package androidx.work.impl.workers;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ConstraintTrackingWorker extends ListenableWorker implements WorkConstraintsCallback {
    public static final String ARGUMENT_CLASS_NAME = "androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME";
    public static final String TAG = Logger.tagWithPrefix("ConstraintTrkngWrkr");
    public volatile boolean mAreConstraintsUnmet;

    @Nullable
    public ListenableWorker mDelegate;
    public SettableFuture<ListenableWorker.Result> mFuture;
    public final Object mLock;
    public WorkerParameters mWorkerParameters;

    public ConstraintTrackingWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        this.mWorkerParameters = workerParams;
        this.mLock = new Object();
        this.mAreConstraintsUnmet = false;
        this.mFuture = SettableFuture.create();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public ListenableWorker getDelegate() {
        return this.mDelegate;
    }

    @Override // androidx.work.ListenableWorker
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public TaskExecutor getTaskExecutor() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkTaskExecutor();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public WorkDatabase getWorkDatabase() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkDatabase();
    }

    @Override // androidx.work.ListenableWorker
    public boolean isRunInForeground() {
        ListenableWorker listenableWorker = this.mDelegate;
        return listenableWorker != null && listenableWorker.isRunInForeground();
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> workSpecIds) {
        Logger.get().debug(TAG, String.format("Constraints changed for %s", workSpecIds), new Throwable[0]);
        synchronized (this.mLock) {
            this.mAreConstraintsUnmet = true;
        }
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
        ListenableWorker listenableWorker = this.mDelegate;
        if (listenableWorker == null || listenableWorker.isStopped()) {
            return;
        }
        this.mDelegate.stop();
    }

    public void setFutureFailed() {
        this.mFuture.set(new ListenableWorker.Result.Failure());
    }

    public void setFutureRetry() {
        this.mFuture.set(new ListenableWorker.Result.Retry());
    }

    public void setupAndRunConstraintTrackingWork() {
        String string = getInputData().getString(ARGUMENT_CLASS_NAME);
        if (TextUtils.isEmpty(string)) {
            Logger.get().error(TAG, "No worker to delegate to.", new Throwable[0]);
            setFutureFailed();
            return;
        }
        ListenableWorker listenableWorkerCreateWorkerWithDefaultFallback = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), string, this.mWorkerParameters);
        this.mDelegate = listenableWorkerCreateWorkerWithDefaultFallback;
        if (listenableWorkerCreateWorkerWithDefaultFallback == null) {
            Logger.get().debug(TAG, "No worker to delegate to.", new Throwable[0]);
            setFutureFailed();
            return;
        }
        WorkSpec workSpec = getWorkDatabase().workSpecDao().getWorkSpec(getId().toString());
        if (workSpec == null) {
            setFutureFailed();
            return;
        }
        WorkConstraintsTracker workConstraintsTracker = new WorkConstraintsTracker(getApplicationContext(), getTaskExecutor(), this);
        workConstraintsTracker.replace(Collections.singletonList(workSpec));
        if (!workConstraintsTracker.areAllConstraintsMet(getId().toString())) {
            Logger.get().debug(TAG, String.format("Constraints not met for delegate %s. Requesting retry.", string), new Throwable[0]);
            setFutureRetry();
            return;
        }
        Logger.get().debug(TAG, String.format("Constraints met for delegate %s", string), new Throwable[0]);
        try {
            final ListenableFuture<ListenableWorker.Result> listenableFutureStartWork = this.mDelegate.startWork();
            listenableFutureStartWork.addListener(new Runnable() { // from class: androidx.work.impl.workers.ConstraintTrackingWorker.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (ConstraintTrackingWorker.this.mLock) {
                        try {
                            if (ConstraintTrackingWorker.this.mAreConstraintsUnmet) {
                                ConstraintTrackingWorker.this.setFutureRetry();
                            } else {
                                ConstraintTrackingWorker.this.mFuture.setFuture(listenableFutureStartWork);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }, getBackgroundExecutor());
        } catch (Throwable th) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, String.format("Delegated worker %s threw exception in startWork.", string), th);
            synchronized (this.mLock) {
                try {
                    if (this.mAreConstraintsUnmet) {
                        Logger.get().debug(str, "Constraints were unmet, Retrying.", new Throwable[0]);
                        setFutureRetry();
                    } else {
                        setFutureFailed();
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.work.ListenableWorker
    @NonNull
    public ListenableFuture<ListenableWorker.Result> startWork() {
        getBackgroundExecutor().execute(new Runnable() { // from class: androidx.work.impl.workers.ConstraintTrackingWorker.1
            @Override // java.lang.Runnable
            public void run() {
                ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork();
            }
        });
        return this.mFuture;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> workSpecIds) {
    }
}
