package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Network;
import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ListenableWorker {

    @NonNull
    public Context mAppContext;
    public boolean mRunInForeground;
    public volatile boolean mStopped;
    public boolean mUsed;

    @NonNull
    public WorkerParameters mWorkerParams;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Result {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final class Retry extends Result {
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                return o != null && Retry.class == o.getClass();
            }

            @Override // androidx.work.ListenableWorker.Result
            @NonNull
            public Data getOutputData() {
                return Data.EMPTY;
            }

            public int hashCode() {
                return 25945934;
            }

            public String toString() {
                return "Retry";
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Result() {
        }

        @NonNull
        public static Result failure() {
            return new Failure();
        }

        @NonNull
        public static Result retry() {
            return new Retry();
        }

        @NonNull
        public static Result success() {
            return new Success();
        }

        @NonNull
        public abstract Data getOutputData();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final class Failure extends Result {
            public final Data mOutputData;

            public Failure(@NonNull Data outputData) {
                this.mOutputData = outputData;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || Failure.class != o.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Failure) o).mOutputData);
            }

            @Override // androidx.work.ListenableWorker.Result
            @NonNull
            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() + 846803280;
            }

            public String toString() {
                return "Failure {mOutputData=" + this.mOutputData + '}';
            }

            public Failure() {
                this(Data.EMPTY);
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final class Success extends Result {
            public final Data mOutputData;

            public Success(@NonNull Data outputData) {
                this.mOutputData = outputData;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || Success.class != o.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Success) o).mOutputData);
            }

            @Override // androidx.work.ListenableWorker.Result
            @NonNull
            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() - 1876823561;
            }

            public String toString() {
                return "Success {mOutputData=" + this.mOutputData + '}';
            }

            public Success() {
                this(Data.EMPTY);
            }
        }

        @NonNull
        public static Result failure(@NonNull Data outputData) {
            return new Failure(outputData);
        }

        @NonNull
        public static Result success(@NonNull Data outputData) {
            return new Success(outputData);
        }
    }

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public ListenableWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        if (appContext == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if (workerParams == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.mAppContext = appContext;
        this.mWorkerParams = workerParams;
    }

    @NonNull
    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.mBackgroundExecutor;
    }

    @NonNull
    public ListenableFuture<ForegroundInfo> getForegroundInfoAsync() {
        SettableFuture settableFutureCreate = SettableFuture.create();
        settableFutureCreate.setException(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return settableFutureCreate;
    }

    @NonNull
    public final UUID getId() {
        return this.mWorkerParams.mId;
    }

    @NonNull
    public final Data getInputData() {
        return this.mWorkerParams.mInputData;
    }

    @Nullable
    @RequiresApi(28)
    public final Network getNetwork() {
        return this.mWorkerParams.mRuntimeExtras.network;
    }

    @IntRange(from = 0)
    public final int getRunAttemptCount() {
        return this.mWorkerParams.mRunAttemptCount;
    }

    @NonNull
    public final Set<String> getTags() {
        return this.mWorkerParams.mTags;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public TaskExecutor getTaskExecutor() {
        return this.mWorkerParams.mWorkTaskExecutor;
    }

    @NonNull
    @RequiresApi(24)
    public final List<String> getTriggeredContentAuthorities() {
        return this.mWorkerParams.mRuntimeExtras.triggeredContentAuthorities;
    }

    @NonNull
    @RequiresApi(24)
    public final List<Uri> getTriggeredContentUris() {
        return this.mWorkerParams.mRuntimeExtras.triggeredContentUris;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkerFactory getWorkerFactory() {
        return this.mWorkerParams.mWorkerFactory;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRunInForeground() {
        return this.mRunInForeground;
    }

    public final boolean isStopped() {
        return this.mStopped;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final boolean isUsed() {
        return this.mUsed;
    }

    @NonNull
    public final ListenableFuture<Void> setForegroundAsync(@NonNull ForegroundInfo foregroundInfo) {
        this.mRunInForeground = true;
        return this.mWorkerParams.mForegroundUpdater.setForegroundAsync(getApplicationContext(), getId(), foregroundInfo);
    }

    @NonNull
    public ListenableFuture<Void> setProgressAsync(@NonNull Data data) {
        return this.mWorkerParams.mProgressUpdater.updateProgress(getApplicationContext(), getId(), data);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRunInForeground(boolean runInForeground) {
        this.mRunInForeground = runInForeground;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void setUsed() {
        this.mUsed = true;
    }

    @NonNull
    @MainThread
    public abstract ListenableFuture<Result> startWork();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void stop() {
        this.mStopped = true;
        onStopped();
    }

    public void onStopped() {
    }
}
