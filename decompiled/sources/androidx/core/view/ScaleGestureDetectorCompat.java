package androidx.core.view;

import android.view.ScaleGestureDetector;
import androidx.annotation.NonNull;
import androidx.annotation.ReplaceWith;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ScaleGestureDetectorCompat {
    @Deprecated
    public static boolean isQuickScaleEnabled(Object obj) {
        return ((ScaleGestureDetector) obj).isQuickScaleEnabled();
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object obj, boolean z) {
        ((ScaleGestureDetector) obj).setQuickScaleEnabled(z);
    }

    @ReplaceWith(expression = "scaleGestureDetector.isQuickScaleEnabled()")
    @Deprecated
    public static boolean isQuickScaleEnabled(@NonNull ScaleGestureDetector scaleGestureDetector) {
        return scaleGestureDetector.isQuickScaleEnabled();
    }

    @ReplaceWith(expression = "scaleGestureDetector.setQuickScaleEnabled(enabled)")
    @Deprecated
    public static void setQuickScaleEnabled(@NonNull ScaleGestureDetector scaleGestureDetector, boolean z) {
        scaleGestureDetector.setQuickScaleEnabled(z);
    }
}
