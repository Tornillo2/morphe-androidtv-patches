package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmScheduler;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Schedulers {
    public static final String GCM_SCHEDULER = "androidx.work.impl.background.gcm.GcmScheduler";
    public static final String TAG = Logger.tagWithPrefix("Schedulers");

    @NonNull
    public static Scheduler createBestAvailableBackgroundScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        if (Build.VERSION.SDK_INT >= 23) {
            SystemJobScheduler systemJobScheduler = new SystemJobScheduler(context, workManager);
            PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
            Logger.get().debug(TAG, "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
            return systemJobScheduler;
        }
        Scheduler schedulerTryCreateGcmBasedScheduler = tryCreateGcmBasedScheduler(context);
        if (schedulerTryCreateGcmBasedScheduler != null) {
            return schedulerTryCreateGcmBasedScheduler;
        }
        SystemAlarmScheduler systemAlarmScheduler = new SystemAlarmScheduler(context);
        PackageManagerHelper.setComponentEnabled(context, SystemAlarmService.class, true);
        Logger.get().debug(TAG, "Created SystemAlarmScheduler", new Throwable[0]);
        return systemAlarmScheduler;
    }

    public static void schedule(@NonNull Configuration configuration, @NonNull WorkDatabase workDatabase, List<Scheduler> schedulers) {
        if (schedulers == null || schedulers.size() == 0) {
            return;
        }
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> eligibleWorkForScheduling = workSpecDao.getEligibleWorkForScheduling(configuration.getMaxSchedulerLimit());
            List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(200);
            ArrayList arrayList = (ArrayList) eligibleWorkForScheduling;
            if (arrayList.size() > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    workSpecDao.markWorkSpecScheduled(((WorkSpec) obj).id, jCurrentTimeMillis);
                }
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            ArrayList arrayList2 = (ArrayList) eligibleWorkForScheduling;
            if (arrayList2.size() > 0) {
                WorkSpec[] workSpecArr = (WorkSpec[]) arrayList2.toArray(new WorkSpec[arrayList2.size()]);
                for (Scheduler scheduler : schedulers) {
                    if (scheduler.hasLimitedSchedulingSlots()) {
                        scheduler.schedule(workSpecArr);
                    }
                }
            }
            ArrayList arrayList3 = (ArrayList) allEligibleWorkSpecsForScheduling;
            if (arrayList3.size() > 0) {
                WorkSpec[] workSpecArr2 = (WorkSpec[]) arrayList3.toArray(new WorkSpec[arrayList3.size()]);
                for (Scheduler scheduler2 : schedulers) {
                    if (!scheduler2.hasLimitedSchedulingSlots()) {
                        scheduler2.schedule(workSpecArr2);
                    }
                }
            }
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    @Nullable
    public static Scheduler tryCreateGcmBasedScheduler(@NonNull Context context) {
        try {
            Scheduler scheduler = (Scheduler) Class.forName(GCM_SCHEDULER).getConstructor(Context.class).newInstance(context);
            Logger.get().debug(TAG, String.format("Created %s", GCM_SCHEDULER), new Throwable[0]);
            return scheduler;
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to create GCM Scheduler", th);
            return null;
        }
    }
}
