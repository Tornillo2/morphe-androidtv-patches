package androidx.work.impl.utils.taskexecutor;

import androidx.annotation.RestrictTo;
import androidx.work.impl.utils.SerialExecutor;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface TaskExecutor {
    void executeOnBackgroundThread(Runnable runnable);

    SerialExecutor getBackgroundExecutor();

    Executor getMainThreadExecutor();

    void postToMainThread(Runnable runnable);
}
