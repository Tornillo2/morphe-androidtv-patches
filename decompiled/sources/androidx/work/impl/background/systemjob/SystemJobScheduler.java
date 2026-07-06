package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemJobScheduler implements Scheduler {
    public static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    public final Context mContext;
    public final JobScheduler mJobScheduler;
    public final SystemJobInfoConverter mSystemJobInfoConverter;
    public final WorkManagerImpl mWorkManager;

    public SystemJobScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        this(context, workManager, (JobScheduler) context.getSystemService("jobscheduler"), new SystemJobInfoConverter(context));
    }

    public static void cancelAll(@NonNull Context context) {
        List<JobInfo> pendingJobs;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null || (pendingJobs = getPendingJobs(context, jobScheduler)) == null) {
            return;
        }
        ArrayList arrayList = (ArrayList) pendingJobs;
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            cancelJobById(jobScheduler, ((JobInfo) obj).getId());
        }
    }

    public static void cancelJobById(@NonNull JobScheduler jobScheduler, int id) {
        try {
            jobScheduler.cancel(id);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(id)), th);
        }
    }

    @Nullable
    public static List<Integer> getPendingJobIds(@NonNull Context context, @NonNull JobScheduler jobScheduler, @NonNull String workSpecId) {
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        if (pendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = (ArrayList) pendingJobs;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            JobInfo jobInfo = (JobInfo) obj;
            if (workSpecId.equals(getWorkSpecIdFromJobInfo(jobInfo))) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    @Nullable
    public static List<JobInfo> getPendingJobs(@NonNull Context context, @NonNull JobScheduler jobScheduler) {
        List<JobInfo> allPendingJobs;
        try {
            allPendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            Logger.get().error(TAG, "getAllPendingJobs() is not reliable on this device.", th);
            allPendingJobs = null;
        }
        if (allPendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allPendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : allPendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    @Nullable
    public static String getWorkSpecIdFromJobInfo(@NonNull JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (extras.containsKey(SystemJobInfoConverter.EXTRA_WORK_SPEC_ID)) {
                return extras.getString(SystemJobInfoConverter.EXTRA_WORK_SPEC_ID);
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static boolean reconcileJobs(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        boolean z;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        List<String> workSpecIds = workManager.getWorkDatabase().systemIdInfoDao().getWorkSpecIds();
        int i = 0;
        HashSet hashSet = new HashSet(pendingJobs != null ? ((ArrayList) pendingJobs).size() : 0);
        if (pendingJobs != null) {
            ArrayList arrayList = (ArrayList) pendingJobs;
            if (!arrayList.isEmpty()) {
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    JobInfo jobInfo = (JobInfo) obj;
                    String workSpecIdFromJobInfo = getWorkSpecIdFromJobInfo(jobInfo);
                    if (TextUtils.isEmpty(workSpecIdFromJobInfo)) {
                        cancelJobById(jobScheduler, jobInfo.getId());
                    } else {
                        hashSet.add(workSpecIdFromJobInfo);
                    }
                }
            }
        }
        ArrayList arrayList2 = (ArrayList) workSpecIds;
        int size2 = arrayList2.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                z = false;
                break;
            }
            Object obj2 = arrayList2.get(i3);
            i3++;
            if (!hashSet.contains((String) obj2)) {
                Logger.get().debug(TAG, "Reconciling jobs", new Throwable[0]);
                z = true;
                break;
            }
        }
        if (!z) {
            return z;
        }
        WorkDatabase workDatabase = workManager.getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            int size3 = arrayList2.size();
            while (i < size3) {
                Object obj3 = arrayList2.get(i);
                i++;
                workSpecDao.markWorkSpecScheduled((String) obj3, -1L);
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            return z;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String workSpecId) {
        List<Integer> pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpecId);
        if (pendingJobIds != null) {
            ArrayList arrayList = (ArrayList) pendingJobIds;
            if (arrayList.isEmpty()) {
                return;
            }
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                cancelJobById(this.mJobScheduler, ((Integer) obj).intValue());
            }
            this.mWorkManager.getWorkDatabase().systemIdInfoDao().removeSystemIdInfo(workSpecId);
        }
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecs) {
        List<Integer> pendingJobIds;
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        for (WorkSpec workSpec : workSpecs) {
            workDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else if (workSpec2.state != WorkInfo.State.ENQUEUED) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else {
                    SystemIdInfo systemIdInfo = workDatabase.systemIdInfoDao().getSystemIdInfo(workSpec.id);
                    int iNextJobSchedulerIdWithRange = systemIdInfo != null ? systemIdInfo.systemId : idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().mMinJobSchedulerId, this.mWorkManager.getConfiguration().mMaxJobSchedulerId);
                    if (systemIdInfo == null) {
                        this.mWorkManager.getWorkDatabase().systemIdInfoDao().insertSystemIdInfo(new SystemIdInfo(workSpec.id, iNextJobSchedulerIdWithRange));
                    }
                    scheduleInternal(workSpec, iNextJobSchedulerIdWithRange);
                    if (Build.VERSION.SDK_INT == 23 && (pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpec.id)) != null) {
                        ArrayList arrayList = (ArrayList) pendingJobIds;
                        int iIndexOf = arrayList.indexOf(Integer.valueOf(iNextJobSchedulerIdWithRange));
                        if (iIndexOf >= 0) {
                            arrayList.remove(iIndexOf);
                        }
                        scheduleInternal(workSpec, !arrayList.isEmpty() ? ((Integer) arrayList.get(0)).intValue() : idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().mMinJobSchedulerId, this.mWorkManager.getConfiguration().mMaxJobSchedulerId));
                    }
                    workDatabase.setTransactionSuccessful();
                }
            } finally {
                workDatabase.endTransaction();
            }
        }
    }

    @VisibleForTesting
    public void scheduleInternal(WorkSpec workSpec, int jobId) {
        JobInfo jobInfoConvert = this.mSystemJobInfoConverter.convert(workSpec, jobId);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, String.format("Scheduling work ID %s Job ID %s", workSpec.id, Integer.valueOf(jobId)), new Throwable[0]);
        try {
            if (this.mJobScheduler.schedule(jobInfoConvert) == 0) {
                Logger.get().warning(str, String.format("Unable to schedule work ID %s", workSpec.id), new Throwable[0]);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy == OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) {
                    workSpec.expedited = false;
                    Logger.get().debug(str, String.format("Scheduling a non-expedited job (work ID %s)", workSpec.id), new Throwable[0]);
                    scheduleInternal(workSpec, jobId);
                }
            }
        } catch (IllegalStateException e) {
            List<JobInfo> pendingJobs = getPendingJobs(this.mContext, this.mJobScheduler);
            String str2 = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(pendingJobs != null ? ((ArrayList) pendingJobs).size() : 0), Integer.valueOf(((ArrayList) this.mWorkManager.getWorkDatabase().workSpecDao().getScheduledWork()).size()), Integer.valueOf(this.mWorkManager.getConfiguration().getMaxSchedulerLimit()));
            Logger.get().error(TAG, str2, new Throwable[0]);
            throw new IllegalStateException(str2, e);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format("Unable to schedule %s", workSpec), th);
        }
    }

    @VisibleForTesting
    public SystemJobScheduler(Context context, WorkManagerImpl workManager, JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mContext = context;
        this.mWorkManager = workManager;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }
}
