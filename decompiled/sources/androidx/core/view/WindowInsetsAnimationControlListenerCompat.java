package androidx.core.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface WindowInsetsAnimationControlListenerCompat {
    void onCancelled(@Nullable WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat);

    void onFinished(@NonNull WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat);

    void onReady(@NonNull WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat, int i);
}
