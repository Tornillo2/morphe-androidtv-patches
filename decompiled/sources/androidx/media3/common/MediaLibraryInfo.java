package androidx.media3.common;

import androidx.media3.common.util.UnstableApi;
import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class MediaLibraryInfo {
    public static final boolean ASSERTIONS_ENABLED = true;
    public static final String TAG = "AndroidXMedia3";
    public static final boolean TRACE_ENABLED = true;
    public static final String VERSION = "1.3.1";
    public static final int VERSION_INT = 1003001300;
    public static final String VERSION_SLASHY = "AndroidXMedia3/1.3.1";
    public static final HashSet<String> registeredModules = new HashSet<>();
    public static String registeredModulesString = "media3.common";

    public static synchronized void registerModule(String str) {
        if (registeredModules.add(str)) {
            registeredModulesString += ", " + str;
        }
    }

    public static synchronized String registeredModules() {
        return registeredModulesString;
    }
}
