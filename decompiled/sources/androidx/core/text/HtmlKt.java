package androidx.core.text;

import android.text.Html;
import android.text.Spanned;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class HtmlKt {
    @NotNull
    public static final Spanned parseAsHtml(@NotNull String str, int i, @Nullable Html.ImageGetter imageGetter, @Nullable Html.TagHandler tagHandler) {
        return HtmlCompat.fromHtml(str, i, imageGetter, tagHandler);
    }

    public static /* synthetic */ Spanned parseAsHtml$default(String str, int i, Html.ImageGetter imageGetter, Html.TagHandler tagHandler, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            imageGetter = null;
        }
        if ((i2 & 4) != 0) {
            tagHandler = null;
        }
        return HtmlCompat.fromHtml(str, i, imageGetter, tagHandler);
    }

    @NotNull
    public static final String toHtml(@NotNull Spanned spanned, int i) {
        return HtmlCompat.toHtml(spanned, i);
    }

    public static /* synthetic */ String toHtml$default(Spanned spanned, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return HtmlCompat.toHtml(spanned, i);
    }
}
