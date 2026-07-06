package dagger.internal;

import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MapBuilder<K, V> {
    public final Map<K, V> contributions;

    public MapBuilder(int size) {
        this.contributions = DaggerCollections.newLinkedHashMapWithExpectedSize(size);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int size) {
        return new MapBuilder<>(size);
    }

    public Map<K, V> build() {
        return this.contributions.isEmpty() ? Collections.EMPTY_MAP : DesugarCollections.unmodifiableMap(this.contributions);
    }

    public MapBuilder<K, V> put(K key, V value) {
        this.contributions.put(key, value);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.contributions.putAll(map);
        return this;
    }
}
