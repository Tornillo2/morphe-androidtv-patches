package androidx.work;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AddedAbstractMethod"})
public abstract class WorkManager {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkManager() {
    }

    @NonNull
    @Deprecated
    public static WorkManager getInstance() {
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance();
        if (workManagerImpl != null) {
            return workManagerImpl;
        }
        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public static void initialize(@NonNull Context context, @NonNull Configuration configuration) {
        WorkManagerImpl.initialize(context, configuration);
    }

    @NonNull
    public final WorkContinuation beginUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull OneTimeWorkRequest work) {
        return beginUniqueWork(uniqueWorkName, existingWorkPolicy, Collections.singletonList(work));
    }

    @NonNull
    public abstract WorkContinuation beginUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<OneTimeWorkRequest> work);

    @NonNull
    public final WorkContinuation beginWith(@NonNull OneTimeWorkRequest work) {
        return beginWith(Collections.singletonList(work));
    }

    @NonNull
    public abstract WorkContinuation beginWith(@NonNull List<OneTimeWorkRequest> work);

    @NonNull
    public abstract Operation cancelAllWork();

    @NonNull
    public abstract Operation cancelAllWorkByTag(@NonNull String tag);

    @NonNull
    public abstract Operation cancelUniqueWork(@NonNull String uniqueWorkName);

    @NonNull
    public abstract Operation cancelWorkById(@NonNull UUID id);

    @NonNull
    public abstract PendingIntent createCancelPendingIntent(@NonNull UUID id);

    @NonNull
    public final Operation enqueue(@NonNull WorkRequest workRequest) {
        return enqueue(Collections.singletonList(workRequest));
    }

    @NonNull
    public abstract Operation enqueue(@NonNull List<? extends WorkRequest> requests);

    @NonNull
    public abstract Operation enqueueUniquePeriodicWork(@NonNull String uniqueWorkName, @NonNull ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, @NonNull PeriodicWorkRequest periodicWork);

    @NonNull
    public Operation enqueueUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull OneTimeWorkRequest work) {
        return enqueueUniqueWork(uniqueWorkName, existingWorkPolicy, Collections.singletonList(work));
    }

    @NonNull
    public abstract Operation enqueueUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<OneTimeWorkRequest> work);

    @NonNull
    public abstract ListenableFuture<Long> getLastCancelAllTimeMillis();

    @NonNull
    public abstract LiveData<Long> getLastCancelAllTimeMillisLiveData();

    @NonNull
    public abstract ListenableFuture<WorkInfo> getWorkInfoById(@NonNull UUID id);

    @NonNull
    public abstract LiveData<WorkInfo> getWorkInfoByIdLiveData(@NonNull UUID id);

    @NonNull
    public abstract ListenableFuture<List<WorkInfo>> getWorkInfos(@NonNull WorkQuery workQuery);

    @NonNull
    public abstract ListenableFuture<List<WorkInfo>> getWorkInfosByTag(@NonNull String tag);

    @NonNull
    public abstract LiveData<List<WorkInfo>> getWorkInfosByTagLiveData(@NonNull String tag);

    @NonNull
    public abstract ListenableFuture<List<WorkInfo>> getWorkInfosForUniqueWork(@NonNull String uniqueWorkName);

    @NonNull
    public abstract LiveData<List<WorkInfo>> getWorkInfosForUniqueWorkLiveData(@NonNull String uniqueWorkName);

    @NonNull
    public abstract LiveData<List<WorkInfo>> getWorkInfosLiveData(@NonNull WorkQuery workQuery);

    @NonNull
    public abstract Operation pruneWork();

    @NonNull
    public static WorkManager getInstance(@NonNull Context context) {
        return WorkManagerImpl.getInstance(context);
    }
}
