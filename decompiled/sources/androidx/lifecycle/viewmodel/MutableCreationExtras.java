package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MutableCreationExtras extends CreationExtras {
    /* JADX WARN: Multi-variable type inference failed */
    public MutableCreationExtras() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.lifecycle.viewmodel.CreationExtras
    @Nullable
    public <T> T get(@NotNull CreationExtras.Key<T> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) this.map.get(key);
    }

    public final <T> void set(@NotNull CreationExtras.Key<T> key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.map.put(key, t);
    }

    public MutableCreationExtras(@NotNull CreationExtras initialExtras) {
        Intrinsics.checkNotNullParameter(initialExtras, "initialExtras");
        this.map.putAll(initialExtras.map);
    }

    public /* synthetic */ MutableCreationExtras(CreationExtras creationExtras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CreationExtras.Empty.INSTANCE : creationExtras);
    }
}
