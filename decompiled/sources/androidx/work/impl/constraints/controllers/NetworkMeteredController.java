package androidx.work.impl.constraints.controllers;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NetworkMeteredController extends ConstraintController<NetworkState> {
    public static final String TAG = Logger.tagWithPrefix("NetworkMeteredCtrlr");

    public NetworkMeteredController(Context context, TaskExecutor taskExecutor) {
        super(Trackers.getInstance(context, taskExecutor).getNetworkStateTracker());
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(@NonNull WorkSpec workSpec) {
        return workSpec.constraints.mRequiredNetworkType == NetworkType.METERED;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isConstrained(@NonNull NetworkState state) {
        if (Build.VERSION.SDK_INT >= 26) {
            return (state.isConnected() && state.isMetered()) ? false : true;
        }
        Logger.get().debug(TAG, "Metered network constraint is not supported before API 26, only checking for connected state.", new Throwable[0]);
        return !state.isConnected();
    }
}
