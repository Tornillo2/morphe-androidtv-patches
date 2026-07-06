package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class CancelWorkRunnable implements Runnable {
    public final OperationImpl mOperation = new OperationImpl();

    /* JADX INFO: renamed from: androidx.work.impl.utils.CancelWorkRunnable$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends CancelWorkRunnable {
        public final /* synthetic */ UUID val$id;
        public final /* synthetic */ WorkManagerImpl val$workManagerImpl;

        public AnonymousClass1(final WorkManagerImpl val$workManagerImpl, final UUID val$id) {
            this.val$workManagerImpl = val$workManagerImpl;
            this.val$id = val$id;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void runInternal() {
            WorkDatabase workDatabase = this.val$workManagerImpl.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                cancel(this.val$workManagerImpl, this.val$id.toString());
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                reschedulePendingWorkers(this.val$workManagerImpl);
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.CancelWorkRunnable$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends CancelWorkRunnable {
        public final /* synthetic */ String val$tag;
        public final /* synthetic */ WorkManagerImpl val$workManagerImpl;

        public AnonymousClass2(final WorkManagerImpl val$workManagerImpl, final String val$tag) {
            this.val$workManagerImpl = val$workManagerImpl;
            this.val$tag = val$tag;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void runInternal() {
            WorkDatabase workDatabase = this.val$workManagerImpl.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                ArrayList arrayList = (ArrayList) workDatabase.workSpecDao().getUnfinishedWorkWithTag(this.val$tag);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    cancel(this.val$workManagerImpl, (String) obj);
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                reschedulePendingWorkers(this.val$workManagerImpl);
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.CancelWorkRunnable$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends CancelWorkRunnable {
        public final /* synthetic */ boolean val$allowReschedule;
        public final /* synthetic */ String val$name;
        public final /* synthetic */ WorkManagerImpl val$workManagerImpl;

        public AnonymousClass3(final WorkManagerImpl val$workManagerImpl, final String val$name, final boolean val$allowReschedule) {
            this.val$workManagerImpl = val$workManagerImpl;
            this.val$name = val$name;
            this.val$allowReschedule = val$allowReschedule;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void runInternal() {
            WorkDatabase workDatabase = this.val$workManagerImpl.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                ArrayList arrayList = (ArrayList) workDatabase.workSpecDao().getUnfinishedWorkWithName(this.val$name);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    cancel(this.val$workManagerImpl, (String) obj);
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                if (this.val$allowReschedule) {
                    reschedulePendingWorkers(this.val$workManagerImpl);
                }
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.utils.CancelWorkRunnable$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends CancelWorkRunnable {
        public final /* synthetic */ WorkManagerImpl val$workManagerImpl;

        public AnonymousClass4(final WorkManagerImpl val$workManagerImpl) {
            this.val$workManagerImpl = val$workManagerImpl;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void runInternal() {
            WorkDatabase workDatabase = this.val$workManagerImpl.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                ArrayList arrayList = (ArrayList) workDatabase.workSpecDao().getAllUnfinishedWork();
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    cancel(this.val$workManagerImpl, (String) obj);
                }
                new PreferenceUtils(this.val$workManagerImpl.getWorkDatabase()).setLastCancelAllTimeMillis(System.currentTimeMillis());
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    public static CancelWorkRunnable forAll(@NonNull final WorkManagerImpl workManagerImpl) {
        return new AnonymousClass4(workManagerImpl);
    }

    public static CancelWorkRunnable forId(@NonNull final UUID id, @NonNull final WorkManagerImpl workManagerImpl) {
        return new AnonymousClass1(workManagerImpl, id);
    }

    public static CancelWorkRunnable forName(@NonNull final String name, @NonNull final WorkManagerImpl workManagerImpl, final boolean allowReschedule) {
        return new AnonymousClass3(workManagerImpl, name, allowReschedule);
    }

    public static CancelWorkRunnable forTag(@NonNull final String tag, @NonNull final WorkManagerImpl workManagerImpl) {
        return new AnonymousClass2(workManagerImpl, tag);
    }

    public void cancel(WorkManagerImpl workManagerImpl, String workSpecId) {
        iterativelyCancelWorkAndDependents(workManagerImpl.getWorkDatabase(), workSpecId);
        workManagerImpl.getProcessor().stopAndCancelWork(workSpecId);
        Iterator<Scheduler> it = workManagerImpl.getSchedulers().iterator();
        while (it.hasNext()) {
            it.next().cancel(workSpecId);
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public final void iterativelyCancelWorkAndDependents(WorkDatabase workDatabase, String workSpecId) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        DependencyDao dependencyDao = workDatabase.dependencyDao();
        LinkedList linkedList = new LinkedList();
        linkedList.add(workSpecId);
        while (!linkedList.isEmpty()) {
            String str = (String) linkedList.remove();
            WorkInfo.State state = workSpecDao.getState(str);
            if (state != WorkInfo.State.SUCCEEDED && state != WorkInfo.State.FAILED) {
                workSpecDao.setState(WorkInfo.State.CANCELLED, str);
            }
            linkedList.addAll(dependencyDao.getDependentWorkIds(str));
        }
    }

    public void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            runInternal();
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    public abstract void runInternal();
}
