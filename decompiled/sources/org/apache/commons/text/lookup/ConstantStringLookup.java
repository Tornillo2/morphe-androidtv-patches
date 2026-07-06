package org.apache.commons.text.lookup;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ClassUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ConstantStringLookup extends AbstractStringLookup {
    public static final char FIELD_SEPRATOR = '.';
    public static final ConstantStringLookup INSTANCE = new ConstantStringLookup();
    public static ConcurrentHashMap<String, String> constantCache = new ConcurrentHashMap<>();

    public static void clear() {
        constantCache.clear();
    }

    public Class<?> fetchClass(String str) throws ClassNotFoundException {
        return ClassUtils.getClass(str);
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public synchronized String lookup(String str) {
        if (str == null) {
            return null;
        }
        String string = constantCache.get(str);
        if (string != null) {
            return string;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            return null;
        }
        try {
            Object objResolveField = resolveField(str.substring(0, iLastIndexOf), str.substring(iLastIndexOf + 1));
            if (objResolveField != null) {
                string = Objects.toString(objResolveField, null);
                constantCache.put(str, string);
            }
            return string;
        } catch (Exception unused) {
            return null;
        }
    }

    public Object resolveField(String str, String str2) throws Exception {
        Class<?> clsFetchClass = fetchClass(str);
        if (clsFetchClass == null) {
            return null;
        }
        return clsFetchClass.getField(str2).get(null);
    }
}
