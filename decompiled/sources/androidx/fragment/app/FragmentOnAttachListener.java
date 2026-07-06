package androidx.fragment.app;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface FragmentOnAttachListener {
    @MainThread
    void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);
}
