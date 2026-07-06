package androidx.core.app;

import android.app.ActivityManager;
import androidx.annotation.NonNull;
import androidx.annotation.ReplaceWith;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ActivityManagerCompat {
    @ReplaceWith(expression = "activityManager.isLowRamDevice()")
    @Deprecated
    public static boolean isLowRamDevice(@NonNull ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }
}
