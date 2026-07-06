package androidx.core.os;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ExecutorCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class HandlerExecutor implements Executor {
        public final Handler mHandler;

        public HandlerExecutor(@NonNull Handler handler) {
            handler.getClass();
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            Handler handler = this.mHandler;
            runnable.getClass();
            if (handler.post(runnable)) {
                return;
            }
            throw new RejectedExecutionException(this.mHandler + " is shutting down");
        }
    }

    @NonNull
    public static Executor create(@NonNull Handler handler) {
        return new HandlerExecutor(handler);
    }
}
