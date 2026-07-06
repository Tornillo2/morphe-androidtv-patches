package androidx.core.content.pm;

import androidx.annotation.AnyThread;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class ShortcutInfoCompatSaver<T> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class NoopImpl extends ShortcutInfoCompatSaver<Void> {
        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void addShortcuts(List list) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void removeAllShortcuts() {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void removeShortcuts(List list) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* JADX INFO: renamed from: addShortcuts, reason: avoid collision after fix types in other method */
        public Void addShortcuts2(List<ShortcutInfoCompat> list) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* JADX INFO: renamed from: removeAllShortcuts, reason: avoid collision after fix types in other method */
        public Void removeAllShortcuts2() {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* JADX INFO: renamed from: removeShortcuts, reason: avoid collision after fix types in other method */
        public Void removeShortcuts2(List<String> list) {
            return null;
        }
    }

    @AnyThread
    public abstract T addShortcuts(List<ShortcutInfoCompat> list);

    @WorkerThread
    public List<ShortcutInfoCompat> getShortcuts() throws Exception {
        return new ArrayList();
    }

    @AnyThread
    public abstract T removeAllShortcuts();

    @AnyThread
    public abstract T removeShortcuts(List<String> list);
}
