package androidx.lifecycle;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DefaultLifecycleObserver extends LifecycleObserver {

    /* JADX INFO: renamed from: androidx.lifecycle.DefaultLifecycleObserver$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    void onCreate(@NotNull LifecycleOwner lifecycleOwner);

    void onDestroy(@NotNull LifecycleOwner lifecycleOwner);

    void onPause(@NotNull LifecycleOwner lifecycleOwner);

    void onResume(@NotNull LifecycleOwner lifecycleOwner);

    void onStart(@NotNull LifecycleOwner lifecycleOwner);

    void onStop(@NotNull LifecycleOwner lifecycleOwner);
}
