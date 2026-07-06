package androidx.core.widget;

import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.ReplaceWith;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class ListViewCompat {
    @ReplaceWith(expression = "listView.canScrollList(direction)")
    @Deprecated
    public static boolean canScrollList(@NonNull ListView listView, int i) {
        return listView.canScrollList(i);
    }

    @ReplaceWith(expression = "listView.scrollListBy(y)")
    @Deprecated
    public static void scrollListBy(@NonNull ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
