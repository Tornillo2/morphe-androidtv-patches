package androidx.core.content.pm;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class ShortcutInfoChangeListener {
    @AnyThread
    public void onAllShortcutsRemoved() {
    }

    @AnyThread
    public void onShortcutAdded(@NonNull List<ShortcutInfoCompat> list) {
    }

    @AnyThread
    public void onShortcutRemoved(@NonNull List<String> list) {
    }

    @AnyThread
    public void onShortcutUpdated(@NonNull List<ShortcutInfoCompat> list) {
    }

    @AnyThread
    public void onShortcutUsageReported(@NonNull List<String> list) {
    }
}
