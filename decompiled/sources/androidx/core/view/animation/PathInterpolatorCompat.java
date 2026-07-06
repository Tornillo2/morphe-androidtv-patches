package androidx.core.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PathInterpolatorCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static class Api21Impl {
        public static Interpolator createPathInterpolator(Path path) {
            return new PathInterpolator(path);
        }

        public static Interpolator createPathInterpolator(float f, float f2) {
            return new PathInterpolator(f, f2);
        }

        public static Interpolator createPathInterpolator(float f, float f2, float f3, float f4) {
            return new PathInterpolator(f, f2, f3, f4);
        }
    }

    @NonNull
    public static Interpolator create(@NonNull Path path) {
        return new PathInterpolator(path);
    }

    @NonNull
    public static Interpolator create(float f, float f2) {
        return new PathInterpolator(f, f2);
    }

    @NonNull
    public static Interpolator create(float f, float f2, float f3, float f4) {
        return new PathInterpolator(f, f2, f3, f4);
    }
}
