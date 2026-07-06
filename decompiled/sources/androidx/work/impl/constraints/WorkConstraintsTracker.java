package androidx.work.impl.constraints;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class WorkConstraintsTracker implements ConstraintController.OnConstraintUpdatedCallback {
    public static final String TAG = Logger.tagWithPrefix("WorkConstraintsTracker");

    @Nullable
    public final WorkConstraintsCallback mCallback;
    public final ConstraintController<?>[] mConstraintControllers;
    public final Object mLock;

    public WorkConstraintsTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor, @Nullable WorkConstraintsCallback callback) {
        Context applicationContext = context.getApplicationContext();
        this.mCallback = callback;
        this.mConstraintControllers = new ConstraintController[]{new BatteryChargingController(applicationContext, taskExecutor), new BatteryNotLowController(applicationContext, taskExecutor), new StorageNotLowController(applicationContext, taskExecutor), new NetworkConnectedController(applicationContext, taskExecutor), new NetworkUnmeteredController(applicationContext, taskExecutor), new NetworkNotRoamingController(applicationContext, taskExecutor), new NetworkMeteredController(applicationContext, taskExecutor)};
        this.mLock = new Object();
    }

    public boolean areAllConstraintsMet(@NonNull String workSpecId) {
        synchronized (this.mLock) {
            try {
                for (ConstraintController<?> constraintController : this.mConstraintControllers) {
                    if (constraintController.isWorkSpecConstrained(workSpecId)) {
                        Logger.get().debug(TAG, String.format("Work %s constrained by %s", workSpecId, constraintController.getClass().getSimpleName()), new Throwable[0]);
                        return false;
                    }
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintMet(@NonNull List<String> workSpecIds) {
        synchronized (this.mLock) {
            try {
                ArrayList arrayList = new ArrayList();
                for (String str : workSpecIds) {
                    if (areAllConstraintsMet(str)) {
                        Logger.get().debug(TAG, String.format("Constraints met for %s", str), new Throwable[0]);
                        arrayList.add(str);
                    }
                }
                WorkConstraintsCallback workConstraintsCallback = this.mCallback;
                if (workConstraintsCallback != null) {
                    workConstraintsCallback.onAllConstraintsMet(arrayList);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintNotMet(@NonNull List<String> workSpecIds) {
        synchronized (this.mLock) {
            try {
                WorkConstraintsCallback workConstraintsCallback = this.mCallback;
                if (workConstraintsCallback != null) {
                    workConstraintsCallback.onAllConstraintsNotMet(workSpecIds);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void replace(@NonNull Iterable<WorkSpec> workSpecs) {
        synchronized (this.mLock) {
            try {
                for (ConstraintController<?> constraintController : this.mConstraintControllers) {
                    constraintController.setCallback(null);
                }
                for (ConstraintController<?> constraintController2 : this.mConstraintControllers) {
                    constraintController2.replace(workSpecs);
                }
                for (ConstraintController<?> constraintController3 : this.mConstraintControllers) {
                    constraintController3.setCallback(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void reset() {
        synchronized (this.mLock) {
            try {
                for (ConstraintController<?> constraintController : this.mConstraintControllers) {
                    constraintController.reset();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public WorkConstraintsTracker(@Nullable WorkConstraintsCallback callback, ConstraintController<?>[] controllers) {
        this.mCallback = callback;
        this.mConstraintControllers = controllers;
        this.mLock = new Object();
    }
}
