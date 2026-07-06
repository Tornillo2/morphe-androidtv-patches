package org.apache.commons.text.lookup;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class MapStringLookup<V> implements StringLookup {
    public final Map<String, V> map;

    public MapStringLookup(Map<String, V> map) {
        this.map = map;
    }

    public static <T> MapStringLookup<T> on(Map<String, T> map) {
        return new MapStringLookup<>(map);
    }

    public Map<String, V> getMap() {
        return this.map;
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        Map<String, V> map = this.map;
        if (map == null) {
            return null;
        }
        try {
            V v = map.get(str);
            if (v != null) {
                return v.toString();
            }
        } catch (NullPointerException unused) {
        }
        return null;
    }

    public String toString() {
        return MapStringLookup.class.getName() + " [map=" + this.map + "]";
    }
}
