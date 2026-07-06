package androidx.core.text;

import android.text.Spanned;
import android.text.SpannedString;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SpannedStringKt {
    public static final <T> T[] getSpans(Spanned spanned, int i, int i2) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static Object[] getSpans$default(Spanned spanned, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            spanned.length();
        }
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final Spanned toSpanned(@NotNull CharSequence charSequence) {
        return SpannedString.valueOf(charSequence);
    }
}
