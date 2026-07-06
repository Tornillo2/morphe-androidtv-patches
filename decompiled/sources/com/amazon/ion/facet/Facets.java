package com.amazon.ion.facet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Facets {
    public static <T> T asFacet(Class<T> cls, Faceted faceted) {
        if (faceted == null) {
            return null;
        }
        return (T) faceted.asFacet(cls);
    }

    public static <T> T assumeFacet(Class<T> cls, Faceted faceted) {
        T t;
        if (faceted == null || (t = (T) faceted.asFacet(cls)) == null) {
            throw new UnsupportedFacetException(cls, faceted);
        }
        return t;
    }

    public static <T> T asFacet(Class<T> cls, Object obj) {
        if (obj instanceof Faceted) {
            return (T) ((Faceted) obj).asFacet(cls);
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public static <T> T assumeFacet(Class<T> cls, Object obj) {
        if (obj instanceof Faceted) {
            T t = (T) ((Faceted) obj).asFacet(cls);
            if (t != null) {
                return t;
            }
        } else if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        throw new UnsupportedFacetException(cls, obj);
    }
}
