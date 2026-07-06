package androidx.core.view;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MenuHost {
    void addMenuProvider(@NonNull MenuProvider menuProvider);

    void addMenuProvider(@NonNull MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner);

    @SuppressLint({"LambdaLast"})
    void addMenuProvider(@NonNull MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.State state);

    void invalidateMenu();

    void removeMenuProvider(@NonNull MenuProvider menuProvider);
}
