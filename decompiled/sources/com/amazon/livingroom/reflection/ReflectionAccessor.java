package com.amazon.livingroom.reflection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ReflectionAccessor {
    public static Class<?> loadClass(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
