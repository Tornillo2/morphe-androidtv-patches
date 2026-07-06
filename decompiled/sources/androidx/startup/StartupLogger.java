package androidx.startup;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class StartupLogger {
    public static final boolean DEBUG = false;
    public static final String TAG = "StartupLogger";

    public static void e(@NonNull String str, @Nullable Throwable th) {
        Log.e(TAG, str, th);
    }

    public static void i(@NonNull String str) {
        Log.i(TAG, str);
    }

    public static void w(@NonNull String str) {
        Log.w(TAG, str);
    }
}
