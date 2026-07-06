package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Reflection {
    public static String getPackageName(Class<?> clazz) {
        return getPackageName(clazz.getName());
    }

    public static void initialize(Class<?>... classes) {
        for (Class<?> cls : classes) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static <T> T newProxy(Class<T> interfaceType, InvocationHandler handler) {
        handler.getClass();
        Preconditions.checkArgument(interfaceType.isInterface(), "%s is not an interface", interfaceType);
        return interfaceType.cast(Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler));
    }

    public static String getPackageName(String classFullName) {
        int iLastIndexOf = classFullName.lastIndexOf(46);
        return iLastIndexOf < 0 ? "" : classFullName.substring(0, iLastIndexOf);
    }
}
