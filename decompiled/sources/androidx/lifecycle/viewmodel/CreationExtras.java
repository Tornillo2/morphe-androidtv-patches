package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CreationExtras {

    @NotNull
    public final Map<Key<?>, Object> map = new LinkedHashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Empty extends CreationExtras {

        @NotNull
        public static final Empty INSTANCE = new Empty();

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        @Nullable
        public <T> T get(@NotNull Key<T> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Key<T> {
    }

    @Nullable
    public abstract <T> T get(@NotNull Key<T> key);

    @NotNull
    public final Map<Key<?>, Object> getMap$lifecycle_viewmodel_release() {
        return this.map;
    }
}
