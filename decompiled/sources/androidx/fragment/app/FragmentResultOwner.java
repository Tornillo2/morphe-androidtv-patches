package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface FragmentResultOwner {
    void clearFragmentResult(@NonNull String str);

    void clearFragmentResultListener(@NonNull String str);

    void setFragmentResult(@NonNull String str, @NonNull Bundle bundle);

    void setFragmentResultListener(@NonNull String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull FragmentResultListener fragmentResultListener);
}
