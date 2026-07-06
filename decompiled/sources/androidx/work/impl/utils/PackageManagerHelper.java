package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PackageManagerHelper {
    public static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    public static boolean isComponentExplicitlyEnabled(Context context, Class<?> klazz) {
        return isComponentExplicitlyEnabled(context, klazz.getName());
    }

    public static void setComponentEnabled(@NonNull Context context, @NonNull Class<?> klazz, boolean enabled) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, klazz.getName()), enabled ? 1 : 2, 1);
            Logger.get().debug(TAG, String.format("%s %s", klazz.getName(), enabled ? "enabled" : "disabled"), new Throwable[0]);
        } catch (Exception e) {
            Logger.get().debug(TAG, String.format("%s could not be %s", klazz.getName(), enabled ? "enabled" : "disabled"), e);
        }
    }

    public static boolean isComponentExplicitlyEnabled(Context context, String className) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, className)) == 1;
    }
}
