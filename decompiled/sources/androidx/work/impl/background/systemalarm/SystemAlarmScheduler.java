package androidx.work.impl.background.systemalarm;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemAlarmScheduler implements Scheduler {
    public static final String TAG = Logger.tagWithPrefix("SystemAlarmScheduler");
    public final Context mContext;

    public SystemAlarmScheduler(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String workSpecId) {
        this.mContext.startService(CommandHandler.createStopWorkIntent(this.mContext, workSpecId));
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecs) {
        for (WorkSpec workSpec : workSpecs) {
            scheduleWorkSpec(workSpec);
        }
    }

    public final void scheduleWorkSpec(@NonNull WorkSpec workSpec) {
        Logger.get().debug(TAG, String.format("Scheduling work with workSpecId %s", workSpec.id), new Throwable[0]);
        this.mContext.startService(CommandHandler.createScheduleWorkIntent(this.mContext, workSpec.id));
    }
}
