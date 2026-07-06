package androidx.lifecycle;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface LifecycleRegistryOwner extends LifecycleOwner {

    /* JADX INFO: renamed from: androidx.lifecycle.LifecycleRegistryOwner$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    /* bridge */ /* synthetic */ Lifecycle getLifecycle();

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    LifecycleRegistry getLifecycle();
}
