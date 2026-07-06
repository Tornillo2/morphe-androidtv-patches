package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import androidx.core.os.BuildCompat;
import androidx.work.Configuration;
import androidx.work.InitializationExceptionHandler;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ForceStopRunnable implements Runnable {

    @VisibleForTesting
    public static final String ACTION_FORCE_STOP_RESCHEDULE = "ACTION_FORCE_STOP_RESCHEDULE";
    public static final int ALARM_ID = -1;
    public static final long BACKOFF_DURATION_MS = 300;

    @VisibleForTesting
    public static final int MAX_ATTEMPTS = 3;
    public static final String TAG = Logger.tagWithPrefix("ForceStopRunnable");
    public static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    public final Context mContext;
    public int mRetryCount = 0;
    public final WorkManagerImpl mWorkManager;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        public static final String TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NonNull Context context, @Nullable Intent intent) {
            if (intent == null || !ForceStopRunnable.ACTION_FORCE_STOP_RESCHEDULE.equals(intent.getAction())) {
                return;
            }
            Logger.get().verbose(TAG, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
            ForceStopRunnable.setAlarm(context);
        }
    }

    public ForceStopRunnable(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManager;
    }

    @VisibleForTesting
    public static Intent getIntent(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) BroadcastReceiver.class));
        intent.setAction(ACTION_FORCE_STOP_RESCHEDULE);
        return intent;
    }

    public static PendingIntent getPendingIntent(Context context, int flags) {
        return PendingIntent.getBroadcast(context, -1, getIntent(context), flags);
    }

    @SuppressLint({"ClassVerificationFailure"})
    public static void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = getPendingIntent(context, BuildCompat.isAtLeastS() ? 167772160 : 134217728);
        long jCurrentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager != null) {
            alarmManager.setExact(0, jCurrentTimeMillis, pendingIntent);
        }
    }

    @VisibleForTesting
    public boolean cleanUp() {
        boolean zReconcileJobs = Build.VERSION.SDK_INT >= 23 ? SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager) : false;
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkProgressDao workProgressDao = workDatabase.workProgressDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            boolean zIsEmpty = ((ArrayList) runningWork).isEmpty();
            if (!zIsEmpty) {
                ArrayList arrayList = (ArrayList) runningWork;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    WorkSpec workSpec = (WorkSpec) obj;
                    workSpecDao.setState(WorkInfo.State.ENQUEUED, workSpec.id);
                    workSpecDao.markWorkSpecScheduled(workSpec.id, -1L);
                }
            }
            workProgressDao.deleteAll();
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            return !zIsEmpty || zReconcileJobs;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    @VisibleForTesting
    public void forceStopRunnable() {
        boolean zCleanUp = cleanUp();
        if (shouldRescheduleWorkers()) {
            Logger.get().debug(TAG, "Rescheduling Workers.", new Throwable[0]);
            this.mWorkManager.rescheduleEligibleWork();
            this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
        } else if (isForceStopped()) {
            Logger.get().debug(TAG, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.mWorkManager.rescheduleEligibleWork();
        } else if (zCleanUp) {
            Logger.get().debug(TAG, "Found unfinished work, scheduling it.", new Throwable[0]);
            Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        }
    }

    @SuppressLint({"ClassVerificationFailure"})
    @VisibleForTesting
    public boolean isForceStopped() {
        try {
            PendingIntent pendingIntent = getPendingIntent(this.mContext, BuildCompat.isAtLeastS() ? 570425344 : 536870912);
            if (Build.VERSION.SDK_INT >= 30) {
                if (pendingIntent != null) {
                    pendingIntent.cancel();
                }
                List<ApplicationExitInfo> historicalProcessExitReasons = ((ActivityManager) this.mContext.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getHistoricalProcessExitReasons(null, 0, 0);
                if (historicalProcessExitReasons != null && !historicalProcessExitReasons.isEmpty()) {
                    for (int i = 0; i < historicalProcessExitReasons.size(); i++) {
                        if (ForceStopRunnable$$ExternalSyntheticApiModelOutline0.m(historicalProcessExitReasons.get(i)).getReason() == 10) {
                            return true;
                        }
                    }
                }
            } else if (pendingIntent == null) {
                setAlarm(this.mContext);
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            e = e;
            Logger.get().warning(TAG, "Ignoring exception", e);
            return true;
        } catch (SecurityException e2) {
            e = e2;
            Logger.get().warning(TAG, "Ignoring exception", e);
            return true;
        }
    }

    @VisibleForTesting
    public boolean multiProcessChecks() {
        Configuration configuration = this.mWorkManager.getConfiguration();
        if (TextUtils.isEmpty(configuration.mDefaultProcessName)) {
            Logger.get().debug(TAG, "The default process name was not specified.", new Throwable[0]);
            return true;
        }
        boolean zIsDefaultProcess = ProcessUtils.isDefaultProcess(this.mContext, configuration);
        Logger.get().debug(TAG, String.format("Is default app process = %s", Boolean.valueOf(zIsDefaultProcess)), new Throwable[0]);
        return zIsDefaultProcess;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        try {
            if (multiProcessChecks()) {
                while (true) {
                    WorkDatabasePathHelper.migrateDatabase(this.mContext);
                    Logger.get().debug(TAG, "Performing cleanup operations.", new Throwable[0]);
                    try {
                        forceStopRunnable();
                        break;
                    } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteTableLockedException e) {
                        i = this.mRetryCount + 1;
                        this.mRetryCount = i;
                        if (i >= 3) {
                            Logger logger = Logger.get();
                            String str = TAG;
                            logger.error(str, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                            IllegalStateException illegalStateException = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                            InitializationExceptionHandler initializationExceptionHandler = this.mWorkManager.getConfiguration().mExceptionHandler;
                            if (initializationExceptionHandler == null) {
                                throw illegalStateException;
                            }
                            Logger.get().debug(str, "Routing exception to the specified exception handler", illegalStateException);
                            initializationExceptionHandler.handleException(illegalStateException);
                        } else {
                            Logger.get().debug(TAG, String.format("Retrying after %s", Long.valueOf(((long) i) * 300)), e);
                            sleep(((long) this.mRetryCount) * 300);
                        }
                    }
                    Logger.get().debug(TAG, String.format("Retrying after %s", Long.valueOf(((long) i) * 300)), e);
                    sleep(((long) this.mRetryCount) * 300);
                }
            }
        } finally {
            this.mWorkManager.onForceStopRunnableCompleted();
        }
    }

    @VisibleForTesting
    public boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    @VisibleForTesting
    public void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException unused) {
        }
    }
}
