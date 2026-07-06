package com.amazon.livingroom.deviceproperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class SystemProperties {
    public static final String TAG = "SystemProperties";
    public final Method getLong;
    public final Method getString;
    public final Class<?> osClass;

    @Inject
    public SystemProperties() {
        Class<?> clsLoadOsClass = loadOsClass();
        this.osClass = clsLoadOsClass;
        this.getString = getMethod(clsLoadOsClass, "get", String.class, String.class);
        this.getLong = getMethod(clsLoadOsClass, "getLong", String.class, Long.TYPE);
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, String.format(Locale.US, "Failed to load method '%s.%s(%s)' - System properties will get default values", cls.getName(), str, Arrays.toString(clsArr)), e);
            return null;
        }
    }

    public static Class<?> loadOsClass() {
        try {
            return Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
            Log.e(TAG, String.format(Locale.US, "Failed to load class '%s' - System properties will get default values", "android.os.SystemProperties"), e);
            return null;
        }
    }

    @NonNull
    public String get(@NonNull String str, @Nullable String str2) {
        Method method;
        Class<?> cls = this.osClass;
        if (cls == null || (method = this.getString) == null) {
            return str2 == null ? "" : str2;
        }
        try {
            return (String) method.invoke(cls, str, str2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, String.format(Locale.US, "Failed to get system property '%s' with default '%s'", str, str2), e);
            return str2 == null ? "" : str2;
        }
    }

    public long getLong(@NonNull String str, long j) {
        Method method;
        Class<?> cls = this.osClass;
        if (cls != null && (method = this.getLong) != null) {
            try {
                return ((Long) method.invoke(cls, str, Long.valueOf(j))).longValue();
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.e(TAG, String.format(Locale.US, "Failed to get system property '%s' with default '%d'", str, Long.valueOf(j)), e);
            }
        }
        return j;
    }
}
