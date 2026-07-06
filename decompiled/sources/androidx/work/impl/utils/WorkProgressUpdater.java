package androidx.work.impl.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkProgressUpdater implements ProgressUpdater {
    public static final String TAG = Logger.tagWithPrefix("WorkProgressUpdater");
    public final TaskExecutor mTaskExecutor;
    public final WorkDatabase mWorkDatabase;

    public WorkProgressUpdater(@NonNull WorkDatabase workDatabase, @NonNull TaskExecutor taskExecutor) {
        this.mWorkDatabase = workDatabase;
        this.mTaskExecutor = taskExecutor;
    }

    @Override // androidx.work.ProgressUpdater
    @NonNull
    public ListenableFuture<Void> updateProgress(@NonNull final Context context, @NonNull final UUID id, @NonNull final Data data) {
        final SettableFuture settableFutureCreate = SettableFuture.create();
        this.mTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.utils.WorkProgressUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                WorkSpec workSpec;
                String string = id.toString();
                Logger logger = Logger.get();
                String str = WorkProgressUpdater.TAG;
                logger.debug(str, String.format("Updating progress for %s (%s)", id, data), new Throwable[0]);
                WorkProgressUpdater.this.mWorkDatabase.beginTransaction();
                try {
                    workSpec = WorkProgressUpdater.this.mWorkDatabase.workSpecDao().getWorkSpec(string);
                } finally {
                    try {
                    } finally {
                    }
                }
                if (workSpec == null) {
                    throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                }
                if (workSpec.state == WorkInfo.State.RUNNING) {
                    WorkProgressUpdater.this.mWorkDatabase.workProgressDao().insert(new WorkProgress(string, data));
                } else {
                    Logger.get().warning(str, String.format("Ignoring setProgressAsync(...). WorkSpec (%s) is not in a RUNNING state.", string), new Throwable[0]);
                }
                settableFutureCreate.set(null);
                WorkProgressUpdater.this.mWorkDatabase.setTransactionSuccessful();
            }
        });
        return settableFutureCreate;
    }
}
