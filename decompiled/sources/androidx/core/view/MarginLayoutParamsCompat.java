package androidx.core.view;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.ReplaceWith;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class MarginLayoutParamsCompat {
    @Deprecated
    public static int getLayoutDirection(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        int layoutDirection = marginLayoutParams.getLayoutDirection();
        if (layoutDirection == 0 || layoutDirection == 1) {
            return layoutDirection;
        }
        return 0;
    }

    @ReplaceWith(expression = "lp.getMarginEnd()")
    @Deprecated
    public static int getMarginEnd(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginEnd();
    }

    @ReplaceWith(expression = "lp.getMarginStart()")
    @Deprecated
    public static int getMarginStart(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginStart();
    }

    @ReplaceWith(expression = "lp.isMarginRelative()")
    @Deprecated
    public static boolean isMarginRelative(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.isMarginRelative();
    }

    @ReplaceWith(expression = "lp.resolveLayoutDirection(layoutDirection)")
    @Deprecated
    public static void resolveLayoutDirection(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.resolveLayoutDirection(i);
    }

    @ReplaceWith(expression = "lp.setLayoutDirection(layoutDirection)")
    @Deprecated
    public static void setLayoutDirection(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.setLayoutDirection(i);
    }

    @ReplaceWith(expression = "lp.setMarginEnd(marginEnd)")
    @Deprecated
    public static void setMarginEnd(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.setMarginEnd(i);
    }

    @ReplaceWith(expression = "lp.setMarginStart(marginStart)")
    @Deprecated
    public static void setMarginStart(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.setMarginStart(i);
    }
}
