package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Trackers {
    public static Trackers sInstance;
    public BatteryChargingTracker mBatteryChargingTracker;
    public BatteryNotLowTracker mBatteryNotLowTracker;
    public NetworkStateTracker mNetworkStateTracker;
    public StorageNotLowTracker mStorageNotLowTracker;

    public Trackers(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        Context applicationContext = context.getApplicationContext();
        this.mBatteryChargingTracker = new BatteryChargingTracker(applicationContext, taskExecutor);
        this.mBatteryNotLowTracker = new BatteryNotLowTracker(applicationContext, taskExecutor);
        this.mNetworkStateTracker = new NetworkStateTracker(applicationContext, taskExecutor);
        this.mStorageNotLowTracker = new StorageNotLowTracker(applicationContext, taskExecutor);
    }

    @NonNull
    public static synchronized Trackers getInstance(Context context, TaskExecutor taskExecutor) {
        try {
            if (sInstance == null) {
                sInstance = new Trackers(context, taskExecutor);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }

    @VisibleForTesting
    public static synchronized void setInstance(@NonNull Trackers trackers) {
        sInstance = trackers;
    }

    @NonNull
    public BatteryChargingTracker getBatteryChargingTracker() {
        return this.mBatteryChargingTracker;
    }

    @NonNull
    public BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.mBatteryNotLowTracker;
    }

    @NonNull
    public NetworkStateTracker getNetworkStateTracker() {
        return this.mNetworkStateTracker;
    }

    @NonNull
    public StorageNotLowTracker getStorageNotLowTracker() {
        return this.mStorageNotLowTracker;
    }
}
