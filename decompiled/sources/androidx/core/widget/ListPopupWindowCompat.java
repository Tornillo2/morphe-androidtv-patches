package androidx.core.widget;

import android.view.View;
import android.widget.ListPopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.ReplaceWith;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ListPopupWindowCompat {
    @Deprecated
    public static View.OnTouchListener createDragToOpenListener(Object obj, View view) {
        return ((ListPopupWindow) obj).createDragToOpenListener(view);
    }

    @Nullable
    @ReplaceWith(expression = "listPopupWindow.createDragToOpenListener(src)")
    @Deprecated
    public static View.OnTouchListener createDragToOpenListener(@NonNull ListPopupWindow listPopupWindow, @NonNull View view) {
        return listPopupWindow.createDragToOpenListener(view);
    }
}
