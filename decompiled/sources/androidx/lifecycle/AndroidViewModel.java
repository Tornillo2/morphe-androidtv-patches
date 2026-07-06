package androidx.lifecycle;

import android.app.Application;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidViewModel extends ViewModel {

    @NotNull
    public final Application application;

    public AndroidViewModel(@NotNull Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
    }

    @NotNull
    public <T extends Application> T getApplication() {
        T t = (T) this.application;
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.lifecycle.AndroidViewModel.getApplication");
        return t;
    }
}
