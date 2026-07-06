package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CommandHandler implements ExecutionListener {
    public static final String ACTION_CONSTRAINTS_CHANGED = "ACTION_CONSTRAINTS_CHANGED";
    public static final String ACTION_DELAY_MET = "ACTION_DELAY_MET";
    public static final String ACTION_EXECUTION_COMPLETED = "ACTION_EXECUTION_COMPLETED";
    public static final String ACTION_RESCHEDULE = "ACTION_RESCHEDULE";
    public static final String ACTION_SCHEDULE_WORK = "ACTION_SCHEDULE_WORK";
    public static final String ACTION_STOP_WORK = "ACTION_STOP_WORK";
    public static final String KEY_NEEDS_RESCHEDULE = "KEY_NEEDS_RESCHEDULE";
    public static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    public static final String TAG = Logger.tagWithPrefix("CommandHandler");
    public static final long WORK_PROCESSING_TIME_IN_MS = 600000;
    public final Context mContext;
    public final Map<String, ExecutionListener> mPendingDelayMet = new HashMap();
    public final Object mLock = new Object();

    public CommandHandler(@NonNull Context context) {
        this.mContext = context;
    }

    public static Intent createConstraintsChangedIntent(@NonNull Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_CONSTRAINTS_CHANGED);
        return intent;
    }

    public static Intent createDelayMetIntent(@NonNull Context context, @NonNull String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_DELAY_MET);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    public static Intent createExecutionCompletedIntent(@NonNull Context context, @NonNull String workSpecId, boolean needsReschedule) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_EXECUTION_COMPLETED);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        intent.putExtra(KEY_NEEDS_RESCHEDULE, needsReschedule);
        return intent;
    }

    public static Intent createRescheduleIntent(@NonNull Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_RESCHEDULE);
        return intent;
    }

    public static Intent createScheduleWorkIntent(@NonNull Context context, @NonNull String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_SCHEDULE_WORK);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    public static Intent createStopWorkIntent(@NonNull Context context, @NonNull String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_STOP_WORK);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    public static boolean hasKeys(@Nullable Bundle bundle, @NonNull String... keys) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : keys) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }

    public final void handleConstraintsChanged(@NonNull Intent intent, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        Logger.get().debug(TAG, String.format("Handling constraints changed %s", intent), new Throwable[0]);
        new ConstraintsCommandHandler(this.mContext, startId, dispatcher).handleConstraintsChanged();
    }

    public final void handleDelayMet(@NonNull Intent intent, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        Bundle extras = intent.getExtras();
        synchronized (this.mLock) {
            try {
                String string = extras.getString("KEY_WORKSPEC_ID");
                Logger logger = Logger.get();
                String str = TAG;
                logger.debug(str, String.format("Handing delay met for %s", string), new Throwable[0]);
                if (this.mPendingDelayMet.containsKey(string)) {
                    Logger.get().debug(str, String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", string), new Throwable[0]);
                } else {
                    DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(this.mContext, startId, string, dispatcher);
                    this.mPendingDelayMet.put(string, delayMetCommandHandler);
                    delayMetCommandHandler.handleProcessWork();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void handleExecutionCompleted(@NonNull Intent intent, int startId) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean(KEY_NEEDS_RESCHEDULE);
        Logger.get().debug(TAG, String.format("Handling onExecutionCompleted %s, %s", intent, Integer.valueOf(startId)), new Throwable[0]);
        onExecuted(string, z);
    }

    public final void handleReschedule(@NonNull Intent intent, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        Logger.get().debug(TAG, String.format("Handling reschedule %s, %s", intent, Integer.valueOf(startId)), new Throwable[0]);
        dispatcher.getWorkManager().rescheduleEligibleWork();
    }

    public final void handleScheduleWorkIntent(@NonNull Intent intent, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, String.format("Handling schedule work for %s", string), new Throwable[0]);
        WorkDatabase workDatabase = dispatcher.getWorkManager().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(string);
            if (workSpec == null) {
                Logger.get().warning(str, "Skipping scheduling " + string + " because it's no longer in the DB", new Throwable[0]);
                return;
            }
            if (workSpec.state.isFinished()) {
                Logger.get().warning(str, "Skipping scheduling " + string + "because it is finished.", new Throwable[0]);
                return;
            }
            long jCalculateNextRunTime = workSpec.calculateNextRunTime();
            if (workSpec.hasConstraints()) {
                Logger.get().debug(str, String.format("Opportunistically setting an alarm for %s at %s", string, Long.valueOf(jCalculateNextRunTime)), new Throwable[0]);
                Alarms.setAlarm(this.mContext, dispatcher.getWorkManager(), string, jCalculateNextRunTime);
                dispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(dispatcher, createConstraintsChangedIntent(this.mContext), startId));
            } else {
                Logger.get().debug(str, String.format("Setting up Alarms for %s at %s", string, Long.valueOf(jCalculateNextRunTime)), new Throwable[0]);
                Alarms.setAlarm(this.mContext, dispatcher.getWorkManager(), string, jCalculateNextRunTime);
            }
            workDatabase.setTransactionSuccessful();
        } finally {
            workDatabase.endTransaction();
        }
    }

    public final void handleStopWork(@NonNull Intent intent, @NonNull SystemAlarmDispatcher dispatcher) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        Logger.get().debug(TAG, String.format("Handing stopWork work for %s", string), new Throwable[0]);
        dispatcher.getWorkManager().stopWork(string);
        Alarms.cancelAlarm(this.mContext, dispatcher.getWorkManager(), string);
        dispatcher.onExecuted(string, false);
    }

    public boolean hasPendingCommands() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mPendingDelayMet.isEmpty();
        }
        return z;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String workSpecId, boolean needsReschedule) {
        synchronized (this.mLock) {
            try {
                ExecutionListener executionListenerRemove = this.mPendingDelayMet.remove(workSpecId);
                if (executionListenerRemove != null) {
                    executionListenerRemove.onExecuted(workSpecId, needsReschedule);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    public void onHandleIntent(@NonNull Intent intent, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        String action = intent.getAction();
        if (ACTION_CONSTRAINTS_CHANGED.equals(action)) {
            handleConstraintsChanged(intent, startId, dispatcher);
            return;
        }
        if (ACTION_RESCHEDULE.equals(action)) {
            handleReschedule(intent, startId, dispatcher);
            return;
        }
        if (!hasKeys(intent.getExtras(), "KEY_WORKSPEC_ID")) {
            Logger.get().error(TAG, String.format("Invalid request for %s, requires %s.", action, "KEY_WORKSPEC_ID"), new Throwable[0]);
            return;
        }
        if (ACTION_SCHEDULE_WORK.equals(action)) {
            handleScheduleWorkIntent(intent, startId, dispatcher);
            return;
        }
        if (ACTION_DELAY_MET.equals(action)) {
            handleDelayMet(intent, startId, dispatcher);
            return;
        }
        if (ACTION_STOP_WORK.equals(action)) {
            handleStopWork(intent, dispatcher);
        } else if (ACTION_EXECUTION_COMPLETED.equals(action)) {
            handleExecutionCompleted(intent, startId);
        } else {
            Logger.get().warning(TAG, String.format("Ignoring intent %s", intent), new Throwable[0]);
        }
    }
}
