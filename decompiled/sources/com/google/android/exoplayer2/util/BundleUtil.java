package com.google.android.exoplayer2.util;

import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BundleUtil {
    public static final String TAG = "BundleUtil";

    @Nullable
    public static Method getIBinderMethod;

    @Nullable
    public static Method putIBinderMethod;

    @Nullable
    public static IBinder getBinder(Bundle bundle, @Nullable String str) {
        return Util.SDK_INT >= 18 ? bundle.getBinder(str) : getBinderByReflection(bundle, str);
    }

    @Nullable
    public static IBinder getBinderByReflection(Bundle bundle, @Nullable String str) {
        Method method = getIBinderMethod;
        if (method == null) {
            try {
                Method method2 = Bundle.class.getMethod("getIBinder", String.class);
                getIBinderMethod = method2;
                method2.setAccessible(true);
                method = getIBinderMethod;
            } catch (NoSuchMethodException e) {
                Log.i("BundleUtil", "Failed to retrieve getIBinder method", e);
                return null;
            }
        }
        try {
            return (IBinder) method.invoke(bundle, str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.i("BundleUtil", "Failed to invoke getIBinder via reflection", e2);
            return null;
        }
    }

    public static void putBinder(Bundle bundle, @Nullable String str, @Nullable IBinder iBinder) {
        if (Util.SDK_INT >= 18) {
            bundle.putBinder(str, iBinder);
        } else {
            putBinderByReflection(bundle, str, iBinder);
        }
    }

    public static void putBinderByReflection(Bundle bundle, @Nullable String str, @Nullable IBinder iBinder) {
        Method method = putIBinderMethod;
        if (method == null) {
            try {
                Method method2 = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                putIBinderMethod = method2;
                method2.setAccessible(true);
                method = putIBinderMethod;
            } catch (NoSuchMethodException e) {
                Log.i("BundleUtil", "Failed to retrieve putIBinder method", e);
                return;
            }
        }
        try {
            method.invoke(bundle, str, iBinder);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.i("BundleUtil", "Failed to invoke putIBinder via reflection", e2);
        }
    }
}
