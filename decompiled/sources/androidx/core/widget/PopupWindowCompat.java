package androidx.core.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PopupWindowCompat {
    public static final String TAG = "PopupWindowCompatApi21";
    public static Method sGetWindowLayoutTypeMethod;
    public static boolean sGetWindowLayoutTypeMethodAttempted;
    public static Field sOverlapAnchorField;
    public static boolean sOverlapAnchorFieldAttempted;
    public static Method sSetWindowLayoutTypeMethod;
    public static boolean sSetWindowLayoutTypeMethodAttempted;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        public static boolean getOverlapAnchor(PopupWindow popupWindow) {
            return popupWindow.getOverlapAnchor();
        }

        public static int getWindowLayoutType(PopupWindow popupWindow) {
            return popupWindow.getWindowLayoutType();
        }

        public static void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
            popupWindow.setOverlapAnchor(z);
        }

        public static void setWindowLayoutType(PopupWindow popupWindow, int i) {
            popupWindow.setWindowLayoutType(i);
        }
    }

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getOverlapAnchor(popupWindow);
        }
        if (!sOverlapAnchorFieldAttempted) {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                sOverlapAnchorField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
            }
            sOverlapAnchorFieldAttempted = true;
        }
        Field field = sOverlapAnchorField;
        if (field == null) {
            return false;
        }
        try {
            return ((Boolean) field.get(popupWindow)).booleanValue();
        } catch (IllegalAccessException e2) {
            Log.i(TAG, "Could not get overlap anchor field in PopupWindow", e2);
            return false;
        }
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getWindowLayoutType(popupWindow);
        }
        if (!sGetWindowLayoutTypeMethodAttempted) {
            try {
                Method declaredMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", null);
                sGetWindowLayoutTypeMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception unused) {
            }
            sGetWindowLayoutTypeMethodAttempted = true;
        }
        Method method = sGetWindowLayoutTypeMethod;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(popupWindow, null)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setOverlapAnchor(popupWindow, z);
            return;
        }
        if (!sOverlapAnchorFieldAttempted) {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                sOverlapAnchorField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
            }
            sOverlapAnchorFieldAttempted = true;
        }
        Field field = sOverlapAnchorField;
        if (field != null) {
            try {
                field.set(popupWindow, Boolean.valueOf(z));
            } catch (IllegalAccessException e2) {
                Log.i(TAG, "Could not set overlap anchor field in PopupWindow", e2);
            }
        }
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setWindowLayoutType(popupWindow, i);
            return;
        }
        if (!sSetWindowLayoutTypeMethodAttempted) {
            try {
                Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                sSetWindowLayoutTypeMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception unused) {
            }
            sSetWindowLayoutTypeMethodAttempted = true;
        }
        Method method = sSetWindowLayoutTypeMethod;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i));
            } catch (Exception unused2) {
            }
        }
    }

    @ReplaceWith(expression = "popup.showAsDropDown(anchor, xoff, yoff, gravity)")
    @Deprecated
    public static void showAsDropDown(@NonNull PopupWindow popupWindow, @NonNull View view, int i, int i2, int i3) {
        popupWindow.showAsDropDown(view, i, i2, i3);
    }
}
