package androidx.work.impl;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.impl.model.WorkSpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface Scheduler {
    public static final int MAX_GREEDY_SCHEDULER_LIMIT = 200;
    public static final int MAX_SCHEDULER_LIMIT = 50;

    void cancel(@NonNull String workSpecId);

    boolean hasLimitedSchedulingSlots();

    void schedule(@NonNull WorkSpec... workSpecs);
}
