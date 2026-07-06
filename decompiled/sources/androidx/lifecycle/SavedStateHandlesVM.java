package androidx.lifecycle;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandlesVM extends ViewModel {

    @NotNull
    public final Map<String, SavedStateHandle> handles = new LinkedHashMap();

    @NotNull
    public final Map<String, SavedStateHandle> getHandles() {
        return this.handles;
    }
}
