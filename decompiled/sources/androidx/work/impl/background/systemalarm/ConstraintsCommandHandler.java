package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ConstraintsCommandHandler {
    public static final String TAG = Logger.tagWithPrefix("ConstraintsCmdHandler");
    public final Context mContext;
    public final SystemAlarmDispatcher mDispatcher;
    public final int mStartId;
    public final WorkConstraintsTracker mWorkConstraintsTracker;

    public ConstraintsCommandHandler(@NonNull Context context, int startId, @NonNull SystemAlarmDispatcher dispatcher) {
        this.mContext = context;
        this.mStartId = startId;
        this.mDispatcher = dispatcher;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(context, dispatcher.getTaskExecutor(), null);
    }

    @WorkerThread
    public void handleConstraintsChanged() {
        List<WorkSpec> scheduledWork = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, scheduledWork);
        this.mWorkConstraintsTracker.replace(scheduledWork);
        ArrayList arrayList = (ArrayList) scheduledWork;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        long jCurrentTimeMillis = System.currentTimeMillis();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            WorkSpec workSpec = (WorkSpec) obj;
            String str = workSpec.id;
            if (jCurrentTimeMillis >= workSpec.calculateNextRunTime() && (!workSpec.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(str))) {
                arrayList2.add(workSpec);
            }
        }
        int size2 = arrayList2.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            String str2 = ((WorkSpec) obj2).id;
            Intent intentCreateDelayMetIntent = CommandHandler.createDelayMetIntent(this.mContext, str2);
            Logger.get().debug(TAG, String.format("Creating a delay_met command for workSpec with id (%s)", str2), new Throwable[0]);
            SystemAlarmDispatcher systemAlarmDispatcher = this.mDispatcher;
            systemAlarmDispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher, intentCreateDelayMetIntent, this.mStartId));
        }
        this.mWorkConstraintsTracker.reset();
    }
}
