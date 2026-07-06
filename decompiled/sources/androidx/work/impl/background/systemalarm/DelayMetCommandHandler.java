package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DelayMetCommandHandler implements WorkConstraintsCallback, ExecutionListener, WorkTimer.TimeLimitExceededListener {
    public static final int STATE_INITIAL = 0;
    public static final int STATE_START_REQUESTED = 1;
    public static final int STATE_STOP_REQUESTED = 2;
    public static final String TAG = Logger.tagWithPrefix("DelayMetCommandHandler");
    public final Context mContext;
    public final SystemAlarmDispatcher mDispatcher;
    public final int mStartId;

    @Nullable
    public PowerManager.WakeLock mWakeLock;
    public final WorkConstraintsTracker mWorkConstraintsTracker;
    public final String mWorkSpecId;
    public boolean mHasConstraints = false;
    public int mCurrentState = 0;
    public final Object mLock = new Object();

    public DelayMetCommandHandler(@NonNull Context context, int startId, @NonNull String workSpecId, @NonNull SystemAlarmDispatcher dispatcher) {
        this.mContext = context;
        this.mStartId = startId;
        this.mDispatcher = dispatcher;
        this.mWorkSpecId = workSpecId;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(context, dispatcher.getTaskExecutor(), this);
    }

    public final void cleanUp() {
        synchronized (this.mLock) {
            try {
                this.mWorkConstraintsTracker.reset();
                this.mDispatcher.getWorkTimer().stopTimer(this.mWorkSpecId);
                PowerManager.WakeLock wakeLock = this.mWakeLock;
                if (wakeLock != null && wakeLock.isHeld()) {
                    Logger.get().debug(TAG, String.format("Releasing wakelock %s for WorkSpec %s", this.mWakeLock, this.mWorkSpecId), new Throwable[0]);
                    this.mWakeLock.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    public void handleProcessWork() {
        this.mWakeLock = WakeLocks.newWakeLock(this.mContext, String.format("%s (%s)", this.mWorkSpecId, Integer.valueOf(this.mStartId)));
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, String.format("Acquiring wakelock %s for WorkSpec %s", this.mWakeLock, this.mWorkSpecId), new Throwable[0]);
        this.mWakeLock.acquire();
        WorkSpec workSpec = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(this.mWorkSpecId);
        if (workSpec == null) {
            stopWork();
            return;
        }
        boolean zHasConstraints = workSpec.hasConstraints();
        this.mHasConstraints = zHasConstraints;
        if (zHasConstraints) {
            this.mWorkConstraintsTracker.replace(Collections.singletonList(workSpec));
        } else {
            Logger.get().debug(str, String.format("No constraints for %s", this.mWorkSpecId), new Throwable[0]);
            onAllConstraintsMet(Collections.singletonList(this.mWorkSpecId));
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> workSpecIds) {
        if (workSpecIds.contains(this.mWorkSpecId)) {
            synchronized (this.mLock) {
                try {
                    if (this.mCurrentState == 0) {
                        this.mCurrentState = 1;
                        Logger.get().debug(TAG, String.format("onAllConstraintsMet for %s", this.mWorkSpecId), new Throwable[0]);
                        if (this.mDispatcher.getProcessor().startWork(this.mWorkSpecId)) {
                            this.mDispatcher.getWorkTimer().startTimer(this.mWorkSpecId, CommandHandler.WORK_PROCESSING_TIME_IN_MS, this);
                        } else {
                            cleanUp();
                        }
                    } else {
                        Logger.get().debug(TAG, String.format("Already started work for %s", this.mWorkSpecId), new Throwable[0]);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> workSpecIds) {
        stopWork();
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String workSpecId, boolean needsReschedule) {
        Logger.get().debug(TAG, String.format("onExecuted %s, %s", workSpecId, Boolean.valueOf(needsReschedule)), new Throwable[0]);
        cleanUp();
        if (needsReschedule) {
            Intent intentCreateScheduleWorkIntent = CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkSpecId);
            SystemAlarmDispatcher systemAlarmDispatcher = this.mDispatcher;
            systemAlarmDispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher, intentCreateScheduleWorkIntent, this.mStartId));
        }
        if (this.mHasConstraints) {
            Intent intentCreateConstraintsChangedIntent = CommandHandler.createConstraintsChangedIntent(this.mContext);
            SystemAlarmDispatcher systemAlarmDispatcher2 = this.mDispatcher;
            systemAlarmDispatcher2.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher2, intentCreateConstraintsChangedIntent, this.mStartId));
        }
    }

    @Override // androidx.work.impl.utils.WorkTimer.TimeLimitExceededListener
    public void onTimeLimitExceeded(@NonNull String workSpecId) {
        Logger.get().debug(TAG, String.format("Exceeded time limits on execution for %s", workSpecId), new Throwable[0]);
        stopWork();
    }

    public final void stopWork() {
        synchronized (this.mLock) {
            try {
                if (this.mCurrentState < 2) {
                    this.mCurrentState = 2;
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, String.format("Stopping work for WorkSpec %s", this.mWorkSpecId), new Throwable[0]);
                    Intent intentCreateStopWorkIntent = CommandHandler.createStopWorkIntent(this.mContext, this.mWorkSpecId);
                    SystemAlarmDispatcher systemAlarmDispatcher = this.mDispatcher;
                    systemAlarmDispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher, intentCreateStopWorkIntent, this.mStartId));
                    if (this.mDispatcher.getProcessor().isEnqueued(this.mWorkSpecId)) {
                        Logger.get().debug(str, String.format("WorkSpec %s needs to be rescheduled", this.mWorkSpecId), new Throwable[0]);
                        Intent intentCreateScheduleWorkIntent = CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkSpecId);
                        SystemAlarmDispatcher systemAlarmDispatcher2 = this.mDispatcher;
                        systemAlarmDispatcher2.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher2, intentCreateScheduleWorkIntent, this.mStartId));
                    } else {
                        Logger.get().debug(str, String.format("Processor does not have WorkSpec %s. No need to reschedule ", this.mWorkSpecId), new Throwable[0]);
                    }
                } else {
                    Logger.get().debug(TAG, String.format("Already stopped work for %s", this.mWorkSpecId), new Throwable[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
