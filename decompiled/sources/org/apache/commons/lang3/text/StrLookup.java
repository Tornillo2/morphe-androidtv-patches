package org.apache.commons.lang3.text;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public abstract class StrLookup<V> {
    public static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);
    public static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = new SystemPropertiesStrLookup();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MapStrLookup<V> extends StrLookup<V> {
        public final Map<String, V> map;

        public MapStrLookup(Map<String, V> map) {
            this.map = map;
        }

        @Override // org.apache.commons.lang3.text.StrLookup
        public String lookup(String str) {
            V v;
            Map<String, V> map = this.map;
            if (map == null || (v = map.get(str)) == null) {
                return null;
            }
            return v.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SystemPropertiesStrLookup extends StrLookup<String> {
        public SystemPropertiesStrLookup() {
        }

        @Override // org.apache.commons.lang3.text.StrLookup
        public String lookup(String str) {
            if (str.isEmpty()) {
                return null;
            }
            try {
                return System.getProperty(str);
            } catch (SecurityException unused) {
                return null;
            }
        }

        public SystemPropertiesStrLookup(AnonymousClass1 anonymousClass1) {
        }
    }

    public static <V> StrLookup<V> mapLookup(Map<String, V> map) {
        return new MapStrLookup(map);
    }

    public static StrLookup<?> noneLookup() {
        return NONE_LOOKUP;
    }

    public static StrLookup<String> systemPropertiesLookup() {
        return SYSTEM_PROPERTIES_LOOKUP;
    }

    public abstract String lookup(String str);
}
