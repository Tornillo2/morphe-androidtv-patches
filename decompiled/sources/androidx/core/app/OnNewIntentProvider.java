package androidx.core.app;

import android.content.Intent;
import androidx.core.util.Consumer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface OnNewIntentProvider {
    void addOnNewIntentListener(@NotNull Consumer<Intent> consumer);

    void removeOnNewIntentListener(@NotNull Consumer<Intent> consumer);
}
