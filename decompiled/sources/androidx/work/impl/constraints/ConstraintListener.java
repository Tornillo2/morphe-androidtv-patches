package androidx.work.impl.constraints;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ConstraintListener<T> {
    @MainThread
    void onConstraintChanged(@Nullable T newValue);
}
