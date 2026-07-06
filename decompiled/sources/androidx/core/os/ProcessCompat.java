package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProcessCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Api19Impl {
        public static Method sMethodUserHandleIsAppMethod;
        public static boolean sResolved;
        public static final Object sResolvedLock = new Object();

        @SuppressLint({"DiscouragedPrivateApi"})
        public static boolean isApplicationUid(int i) {
            try {
                synchronized (sResolvedLock) {
                    try {
                        if (!sResolved) {
                            sResolved = true;
                            sMethodUserHandleIsAppMethod = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                        }
                    } finally {
                    }
                }
                Method method = sMethodUserHandleIsAppMethod;
                if (method != null) {
                    Boolean bool = (Boolean) method.invoke(null, Integer.valueOf(i));
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static boolean isApplicationUid(int i) {
            return Process.isApplicationUid(i);
        }
    }

    public static boolean isApplicationUid(int i) {
        return Build.VERSION.SDK_INT >= 24 ? Api24Impl.isApplicationUid(i) : Api19Impl.isApplicationUid(i);
    }
}
