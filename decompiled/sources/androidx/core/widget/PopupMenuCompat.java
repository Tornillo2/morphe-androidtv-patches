package androidx.core.widget;

import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PopupMenuCompat {
    @Nullable
    public static View.OnTouchListener getDragToOpenListener(@NonNull Object obj) {
        return ((PopupMenu) obj).getDragToOpenListener();
    }
}
