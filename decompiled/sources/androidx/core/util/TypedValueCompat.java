package androidx.core.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TypedValueCompat {
    public static final float INCHES_PER_MM = 0.03937008f;
    public static final float INCHES_PER_PT = 0.013888889f;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(34)
    public static class Api34Impl {
        public static float deriveDimension(int i, float f, DisplayMetrics displayMetrics) {
            return TypedValue.deriveDimension(i, f, displayMetrics);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface ComplexDimensionUnit {
    }

    public static float deriveDimension(int i, float f, @NonNull DisplayMetrics displayMetrics) {
        float f2;
        float f3;
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.deriveDimension(i, f, displayMetrics);
        }
        if (i == 0) {
            return f;
        }
        if (i == 1) {
            float f4 = displayMetrics.density;
            if (f4 == 0.0f) {
                return 0.0f;
            }
            return f / f4;
        }
        if (i == 2) {
            float f5 = displayMetrics.scaledDensity;
            if (f5 == 0.0f) {
                return 0.0f;
            }
            return f / f5;
        }
        if (i == 3) {
            float f6 = displayMetrics.xdpi;
            if (f6 == 0.0f) {
                return 0.0f;
            }
            f2 = f / f6;
            f3 = 0.013888889f;
        } else {
            if (i == 4) {
                float f7 = displayMetrics.xdpi;
                if (f7 == 0.0f) {
                    return 0.0f;
                }
                return f / f7;
            }
            if (i != 5) {
                throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid unitToConvertTo ", i));
            }
            float f8 = displayMetrics.xdpi;
            if (f8 == 0.0f) {
                return 0.0f;
            }
            f2 = f / f8;
            f3 = 0.03937008f;
        }
        return f2 / f3;
    }

    public static float dpToPx(float f, @NonNull DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(1, f, displayMetrics);
    }

    @SuppressLint({"WrongConstant"})
    public static int getUnitFromComplexDimension(int i) {
        return i & 15;
    }

    public static float pxToDp(float f, @NonNull DisplayMetrics displayMetrics) {
        return deriveDimension(1, f, displayMetrics);
    }

    public static float pxToSp(float f, @NonNull DisplayMetrics displayMetrics) {
        return deriveDimension(2, f, displayMetrics);
    }

    public static float spToPx(float f, @NonNull DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(2, f, displayMetrics);
    }
}
