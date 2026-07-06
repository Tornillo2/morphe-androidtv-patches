package androidx.work.impl.constraints.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintController<T> implements ConstraintListener<T> {
    public OnConstraintUpdatedCallback mCallback;
    public T mCurrentValue;
    public final List<String> mMatchingWorkSpecIds = new ArrayList();
    public ConstraintTracker<T> mTracker;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnConstraintUpdatedCallback {
        void onConstraintMet(@NonNull List<String> workSpecIds);

        void onConstraintNotMet(@NonNull List<String> workSpecIds);
    }

    public ConstraintController(ConstraintTracker<T> tracker) {
        this.mTracker = tracker;
    }

    public abstract boolean hasConstraint(@NonNull WorkSpec workSpec);

    public abstract boolean isConstrained(@NonNull T currentValue);

    public boolean isWorkSpecConstrained(@NonNull String workSpecId) {
        T t = this.mCurrentValue;
        return t != null && isConstrained(t) && this.mMatchingWorkSpecIds.contains(workSpecId);
    }

    @Override // androidx.work.impl.constraints.ConstraintListener
    public void onConstraintChanged(@Nullable T newValue) {
        this.mCurrentValue = newValue;
        updateCallback(this.mCallback, newValue);
    }

    public void replace(@NonNull Iterable<WorkSpec> workSpecs) {
        this.mMatchingWorkSpecIds.clear();
        for (WorkSpec workSpec : workSpecs) {
            if (hasConstraint(workSpec)) {
                this.mMatchingWorkSpecIds.add(workSpec.id);
            }
        }
        if (this.mMatchingWorkSpecIds.isEmpty()) {
            this.mTracker.removeListener(this);
        } else {
            this.mTracker.addListener(this);
        }
        updateCallback(this.mCallback, this.mCurrentValue);
    }

    public void reset() {
        if (this.mMatchingWorkSpecIds.isEmpty()) {
            return;
        }
        this.mMatchingWorkSpecIds.clear();
        this.mTracker.removeListener(this);
    }

    public void setCallback(@Nullable OnConstraintUpdatedCallback callback) {
        if (this.mCallback != callback) {
            this.mCallback = callback;
            updateCallback(callback, this.mCurrentValue);
        }
    }

    public final void updateCallback(@Nullable OnConstraintUpdatedCallback callback, @Nullable T currentValue) {
        if (this.mMatchingWorkSpecIds.isEmpty() || callback == null) {
            return;
        }
        if (currentValue == null || isConstrained(currentValue)) {
            callback.onConstraintNotMet(this.mMatchingWorkSpecIds);
        } else {
            callback.onConstraintMet(this.mMatchingWorkSpecIds);
        }
    }
}
