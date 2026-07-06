package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
public final class PersistableBundleApi22ImplKt {

    @NotNull
    public static final PersistableBundleApi22ImplKt INSTANCE = new PersistableBundleApi22ImplKt();

    @JvmStatic
    public static final void putBoolean(@NotNull PersistableBundle persistableBundle, @Nullable String str, boolean z) {
        persistableBundle.putBoolean(str, z);
    }

    @JvmStatic
    public static final void putBooleanArray(@NotNull PersistableBundle persistableBundle, @Nullable String str, @NotNull boolean[] zArr) {
        persistableBundle.putBooleanArray(str, zArr);
    }
}
