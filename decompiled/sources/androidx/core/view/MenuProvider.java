package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MenuProvider {
    void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater);

    void onMenuClosed(@NonNull Menu menu);

    boolean onMenuItemSelected(@NonNull MenuItem menuItem);

    void onPrepareMenu(@NonNull Menu menu);

    /* JADX INFO: renamed from: androidx.core.view.MenuProvider$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static void $default$onMenuClosed(MenuProvider menuProvider, @NonNull Menu menu) {
        }

        public static void $default$onPrepareMenu(MenuProvider menuProvider, @NonNull Menu menu) {
        }
    }
}
