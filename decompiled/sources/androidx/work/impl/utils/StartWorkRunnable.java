package androidx.work.impl.utils;

import androidx.annotation.RestrictTo;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class StartWorkRunnable implements Runnable {
    public WorkerParameters.RuntimeExtras mRuntimeExtras;
    public WorkManagerImpl mWorkManagerImpl;
    public String mWorkSpecId;

    public StartWorkRunnable(WorkManagerImpl workManagerImpl, String workSpecId, WorkerParameters.RuntimeExtras runtimeExtras) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkSpecId = workSpecId;
        this.mRuntimeExtras = runtimeExtras;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.mWorkManagerImpl.getProcessor().startWork(this.mWorkSpecId, this.mRuntimeExtras);
    }
}
