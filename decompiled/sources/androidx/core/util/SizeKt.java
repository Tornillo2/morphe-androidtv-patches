package androidx.core.util;

import android.annotation.SuppressLint;
import android.util.Size;
import android.util.SizeF;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ClassVerificationFailure"})
public final class SizeKt {
    @RequiresApi(21)
    public static final int component1(@NotNull Size size) {
        return size.getWidth();
    }

    @RequiresApi(21)
    public static final int component2(@NotNull Size size) {
        return size.getHeight();
    }

    public static final float component1(@NotNull SizeFCompat sizeFCompat) {
        return sizeFCompat.mWidth;
    }

    public static final float component2(@NotNull SizeFCompat sizeFCompat) {
        return sizeFCompat.mHeight;
    }

    @RequiresApi(21)
    public static final float component1(@NotNull SizeF sizeF) {
        return sizeF.getWidth();
    }

    @RequiresApi(21)
    public static final float component2(@NotNull SizeF sizeF) {
        return sizeF.getHeight();
    }
}
