package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TileServiceCompat {
    public static TileServiceWrapper sTileServiceWrapper;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static void startActivityAndCollapse(TileService tileService, Intent intent) {
            tileService.startActivityAndCollapse(intent);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(34)
    public static class Api34Impl {
        public static void startActivityAndCollapse(TileService tileService, PendingIntent pendingIntent) {
            tileService.startActivityAndCollapse(pendingIntent);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface TileServiceWrapper {
        void startActivityAndCollapse(PendingIntent pendingIntent);

        void startActivityAndCollapse(Intent intent);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void clearTileServiceWrapper() {
        sTileServiceWrapper = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void setTileServiceWrapper(@NonNull TileServiceWrapper tileServiceWrapper) {
        sTileServiceWrapper = tileServiceWrapper;
    }

    public static void startActivityAndCollapse(@NonNull TileService tileService, @NonNull PendingIntentActivityWrapper pendingIntentActivityWrapper) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            TileServiceWrapper tileServiceWrapper = sTileServiceWrapper;
            if (tileServiceWrapper != null) {
                tileServiceWrapper.startActivityAndCollapse(pendingIntentActivityWrapper.getPendingIntent());
                return;
            } else {
                Api34Impl.startActivityAndCollapse(tileService, pendingIntentActivityWrapper.getPendingIntent());
                return;
            }
        }
        if (i >= 24) {
            TileServiceWrapper tileServiceWrapper2 = sTileServiceWrapper;
            if (tileServiceWrapper2 != null) {
                tileServiceWrapper2.startActivityAndCollapse(pendingIntentActivityWrapper.getIntent());
            } else {
                Api24Impl.startActivityAndCollapse(tileService, pendingIntentActivityWrapper.getIntent());
            }
        }
    }
}
