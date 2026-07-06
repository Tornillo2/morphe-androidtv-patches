package androidx.core.graphics.drawable;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ClassVerificationFailure"})
public final class IconKt {
    @RequiresApi(26)
    @NotNull
    public static final Icon toAdaptiveIcon(@NotNull Bitmap bitmap) {
        return Icon.createWithAdaptiveBitmap(bitmap);
    }

    @RequiresApi(26)
    @NotNull
    public static final Icon toIcon(@NotNull Bitmap bitmap) {
        return Icon.createWithBitmap(bitmap);
    }

    @RequiresApi(26)
    @NotNull
    public static final Icon toIcon(@NotNull Uri uri) {
        return Icon.createWithContentUri(uri);
    }

    @RequiresApi(26)
    @NotNull
    public static final Icon toIcon(@NotNull byte[] bArr) {
        return Icon.createWithData(bArr, 0, bArr.length);
    }
}
