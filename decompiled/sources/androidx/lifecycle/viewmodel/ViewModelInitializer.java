package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewModelInitializer<T extends ViewModel> {

    @NotNull
    public final Class<T> clazz;

    @NotNull
    public final Function1<CreationExtras, T> initializer;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewModelInitializer(@NotNull Class<T> clazz, @NotNull Function1<? super CreationExtras, ? extends T> initializer) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        this.clazz = clazz;
        this.initializer = initializer;
    }

    @NotNull
    public final Class<T> getClazz$lifecycle_viewmodel_release() {
        return this.clazz;
    }

    @NotNull
    public final Function1<CreationExtras, T> getInitializer$lifecycle_viewmodel_release() {
        return this.initializer;
    }
}
