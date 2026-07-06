package androidx.core.content;

import android.content.res.Configuration;
import androidx.core.util.Consumer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface OnConfigurationChangedProvider {
    void addOnConfigurationChangedListener(@NotNull Consumer<Configuration> consumer);

    void removeOnConfigurationChangedListener(@NotNull Consumer<Configuration> consumer);
}
