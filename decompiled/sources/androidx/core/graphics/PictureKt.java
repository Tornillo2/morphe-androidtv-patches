package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Picture;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PictureKt {
    @NotNull
    public static final Picture record(@NotNull Picture picture, int i, int i2, @NotNull Function1<? super Canvas, Unit> function1) {
        try {
            function1.invoke(picture.beginRecording(i, i2));
            return picture;
        } finally {
            picture.endRecording();
        }
    }
}
