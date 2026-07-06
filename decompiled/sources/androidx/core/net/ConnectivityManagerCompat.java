package androidx.core.net;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityManagerCompat {
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static int getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
            return connectivityManager.getRestrictBackgroundStatus();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface RestrictBackgroundStatus {
    }

    @Nullable
    @RequiresPermission(DefaultConnectivityMonitorFactory.NETWORK_PERMISSION)
    @SuppressLint({"ReferencesDeprecated"})
    public static NetworkInfo getNetworkInfoFromBroadcast(@NonNull ConnectivityManager connectivityManager, @NonNull Intent intent) {
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo != null) {
            return connectivityManager.getNetworkInfo(networkInfo.getType());
        }
        return null;
    }

    public static int getRestrictBackgroundStatus(@NonNull ConnectivityManager connectivityManager) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getRestrictBackgroundStatus(connectivityManager);
        }
        return 3;
    }

    @ReplaceWith(expression = "cm.isActiveNetworkMetered()")
    @RequiresPermission(DefaultConnectivityMonitorFactory.NETWORK_PERMISSION)
    @Deprecated
    public static boolean isActiveNetworkMetered(@NonNull ConnectivityManager connectivityManager) {
        return connectivityManager.isActiveNetworkMetered();
    }
}
