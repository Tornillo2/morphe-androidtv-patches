package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class BroadcastReceiverConstraintTracker<T> extends ConstraintTracker<T> {
    public static final String TAG = Logger.tagWithPrefix("BrdcstRcvrCnstrntTrckr");
    public final BroadcastReceiver mBroadcastReceiver;

    public BroadcastReceiverConstraintTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent != null) {
                    BroadcastReceiverConstraintTracker.this.onBroadcastReceive(context2, intent);
                }
            }
        };
    }

    public abstract IntentFilter getIntentFilter();

    public abstract void onBroadcastReceive(Context context, @NonNull Intent intent);

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void startTracking() {
        Logger.get().debug(TAG, String.format("%s: registering receiver", getClass().getSimpleName()), new Throwable[0]);
        this.mAppContext.registerReceiver(this.mBroadcastReceiver, getIntentFilter());
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void stopTracking() {
        Logger.get().debug(TAG, String.format("%s: unregistering receiver", getClass().getSimpleName()), new Throwable[0]);
        this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
    }
}
