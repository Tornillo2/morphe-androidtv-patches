package com.google.android.datatransport.runtime.dagger.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Preconditions {
    public static <T> void checkBuilderRequirement(T t, Class<T> cls) {
        if (t != null) {
            return;
        }
        throw new IllegalStateException(cls.getCanonicalName() + " must be set");
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull(T t, String str, Object obj) {
        String strValueOf;
        if (t != null) {
            return t;
        }
        if (str.contains("%s")) {
            if (str.indexOf("%s") == str.lastIndexOf("%s")) {
                if (obj instanceof Class) {
                    strValueOf = ((Class) obj).getCanonicalName();
                } else {
                    strValueOf = String.valueOf(obj);
                }
                throw new NullPointerException(str.replace("%s", strValueOf));
            }
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        }
        throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
    }
}
