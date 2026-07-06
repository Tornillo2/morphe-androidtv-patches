package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkerFactory {
    public static final String TAG = Logger.tagWithPrefix("WorkerFactory");

    /* JADX INFO: renamed from: androidx.work.WorkerFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends WorkerFactory {
        @Override // androidx.work.WorkerFactory
        @Nullable
        public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
            return null;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static WorkerFactory getDefaultWorkerFactory() {
        return new AnonymousClass1();
    }

    @Nullable
    public abstract ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final ListenableWorker createWorkerWithDefaultFallback(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        Class clsAsSubclass;
        ListenableWorker listenableWorkerCreateWorker = createWorker(appContext, workerClassName, workerParameters);
        if (listenableWorkerCreateWorker == null) {
            try {
                clsAsSubclass = Class.forName(workerClassName).asSubclass(ListenableWorker.class);
            } catch (Throwable th) {
                Logger.get().error(TAG, RemoteInput$$ExternalSyntheticOutline0.m("Invalid class: ", workerClassName), th);
                clsAsSubclass = null;
            }
            if (clsAsSubclass != null) {
                try {
                    listenableWorkerCreateWorker = (ListenableWorker) clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(appContext, workerParameters);
                } catch (Throwable th2) {
                    Logger.get().error(TAG, RemoteInput$$ExternalSyntheticOutline0.m("Could not instantiate ", workerClassName), th2);
                }
            }
        }
        if (listenableWorkerCreateWorker == null || !listenableWorkerCreateWorker.isUsed()) {
            return listenableWorkerCreateWorker;
        }
        throw new IllegalStateException(String.format("WorkerFactory (%s) returned an instance of a ListenableWorker (%s) which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.", getClass().getName(), workerClassName));
    }
}
