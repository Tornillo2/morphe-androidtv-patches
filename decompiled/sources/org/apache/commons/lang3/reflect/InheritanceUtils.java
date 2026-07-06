package org.apache.commons.lang3.reflect;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class InheritanceUtils {
    public static int distance(Class<?> cls, Class<?> cls2) {
        if (cls != null && cls2 != null) {
            if (cls.equals(cls2)) {
                return 0;
            }
            Class<? super Object> superclass = cls.getSuperclass();
            boolean zEquals = cls2.equals(superclass);
            if (zEquals) {
                return zEquals ? 1 : 0;
            }
            int iDistance = distance(superclass, cls2) + (zEquals ? 1 : 0);
            if (iDistance > 0) {
                return iDistance + 1;
            }
        }
        return -1;
    }
}
