package androidx.core.content;

import androidx.core.util.Consumer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface OnTrimMemoryProvider {
    void addOnTrimMemoryListener(@NotNull Consumer<Integer> consumer);

    void removeOnTrimMemoryListener(@NotNull Consumer<Integer> consumer);
}
