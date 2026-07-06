package androidx.core.graphics;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PorterDuffKt {
    @NotNull
    public static final PorterDuffColorFilter toColorFilter(@NotNull PorterDuff.Mode mode, int i) {
        return new PorterDuffColorFilter(i, mode);
    }

    @NotNull
    public static final PorterDuffXfermode toXfermode(@NotNull PorterDuff.Mode mode) {
        return new PorterDuffXfermode(mode);
    }
}
