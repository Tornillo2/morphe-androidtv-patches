package androidx.core.os;

import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class EnvironmentCompat {

    @Deprecated
    public static final String MEDIA_UNKNOWN = "unknown";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static class Api21Impl {
        public static String getExternalStorageState(File file) {
            return Environment.getExternalStorageState(file);
        }
    }

    @NonNull
    public static String getStorageState(@NonNull File file) {
        return Environment.getExternalStorageState(file);
    }
}
