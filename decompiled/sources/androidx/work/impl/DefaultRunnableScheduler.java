package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.HandlerCompat;
import androidx.work.RunnableScheduler;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DefaultRunnableScheduler implements RunnableScheduler {
    public final Handler mHandler;

    public DefaultRunnableScheduler() {
        this.mHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    @Override // androidx.work.RunnableScheduler
    public void cancel(@NonNull Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }

    @NonNull
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // androidx.work.RunnableScheduler
    public void scheduleWithDelay(long delayInMillis, @NonNull Runnable runnable) {
        this.mHandler.postDelayed(runnable, delayInMillis);
    }

    @VisibleForTesting
    public DefaultRunnableScheduler(@NonNull Handler handler) {
        this.mHandler = handler;
    }
}
