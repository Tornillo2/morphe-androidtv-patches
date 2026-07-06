package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class StatusRunnable<T> implements Runnable {
    public final SettableFuture<T> mFuture = SettableFuture.create();

    /* JADX INFO: renamed from: androidx.work.impl.utils.StatusRunnable$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ List val$ids;
        public final /* synthetic */ WorkManagerImpl val$workManager;

        public AnonymousClass1(final WorkManagerImpl val$workManager, final List val$ids) {
            this.val$workManager = val$workManager;
            this.val$ids = val$ids;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        public List<WorkInfo> runInternal() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.val$workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(this.val$ids));
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.StatusRunnable$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends StatusRunnable<WorkInfo> {
        public final /* synthetic */ UUID val$id;
        public final /* synthetic */ WorkManagerImpl val$workManager;

        public AnonymousClass2(final WorkManagerImpl val$workManager, final UUID val$id) {
            this.val$workManager = val$workManager;
            this.val$id = val$id;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.work.impl.utils.StatusRunnable
        public WorkInfo runInternal() {
            WorkSpec.WorkInfoPojo workStatusPojoForId = this.val$workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(this.val$id.toString());
            if (workStatusPojoForId != null) {
                return workStatusPojoForId.toWorkInfo();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.StatusRunnable$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ String val$tag;
        public final /* synthetic */ WorkManagerImpl val$workManager;

        public AnonymousClass3(final WorkManagerImpl val$workManager, final String val$tag) {
            this.val$workManager = val$workManager;
            this.val$tag = val$tag;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        public List<WorkInfo> runInternal() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.val$workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(this.val$tag));
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.StatusRunnable$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ String val$name;
        public final /* synthetic */ WorkManagerImpl val$workManager;

        public AnonymousClass4(final WorkManagerImpl val$workManager, final String val$name) {
            this.val$workManager = val$workManager;
            this.val$name = val$name;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        public List<WorkInfo> runInternal() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.val$workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(this.val$name));
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.StatusRunnable$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5 extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ WorkQuery val$querySpec;
        public final /* synthetic */ WorkManagerImpl val$workManager;

        public AnonymousClass5(final WorkManagerImpl val$workManager, final WorkQuery val$querySpec) {
            this.val$workManager = val$workManager;
            this.val$querySpec = val$querySpec;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        public List<WorkInfo> runInternal() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.val$workManager.getWorkDatabase().rawWorkInfoDao().getWorkInfoPojos(RawQueries.workQueryToRawQuery(this.val$querySpec)));
        }
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forStringIds(@NonNull final WorkManagerImpl workManager, @NonNull final List<String> ids) {
        return new AnonymousClass1(workManager, ids);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forTag(@NonNull final WorkManagerImpl workManager, @NonNull final String tag) {
        return new AnonymousClass3(workManager, tag);
    }

    @NonNull
    public static StatusRunnable<WorkInfo> forUUID(@NonNull final WorkManagerImpl workManager, @NonNull final UUID id) {
        return new AnonymousClass2(workManager, id);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forUniqueWork(@NonNull final WorkManagerImpl workManager, @NonNull final String name) {
        return new AnonymousClass4(workManager, name);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forWorkQuerySpec(@NonNull final WorkManagerImpl workManager, @NonNull final WorkQuery querySpec) {
        return new AnonymousClass5(workManager, querySpec);
    }

    @NonNull
    public ListenableFuture<T> getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mFuture.set(runInternal());
        } catch (Throwable th) {
            this.mFuture.setException(th);
        }
    }

    @WorkerThread
    public abstract T runInternal();
}
