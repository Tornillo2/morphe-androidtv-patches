package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.work.ListenableWorker;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Worker extends ListenableWorker {
    public SettableFuture<ListenableWorker.Result> mFuture;

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public Worker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @WorkerThread
    public abstract ListenableWorker.Result doWork();

    @Override // androidx.work.ListenableWorker
    @NonNull
    public final ListenableFuture<ListenableWorker.Result> startWork() {
        this.mFuture = SettableFuture.create();
        getBackgroundExecutor().execute(new Runnable() { // from class: androidx.work.Worker.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Worker.this.mFuture.set(Worker.this.doWork());
                } catch (Throwable th) {
                    Worker.this.mFuture.setException(th);
                }
            }
        });
        return this.mFuture;
    }
}
