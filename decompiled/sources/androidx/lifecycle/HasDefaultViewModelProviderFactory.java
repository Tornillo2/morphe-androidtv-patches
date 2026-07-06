package androidx.lifecycle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface HasDefaultViewModelProviderFactory {

    /* JADX INFO: renamed from: androidx.lifecycle.HasDefaultViewModelProviderFactory$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @NotNull
    CreationExtras getDefaultViewModelCreationExtras();

    @NotNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
}
