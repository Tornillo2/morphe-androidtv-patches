package com.google.common.graph;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MapRetrievalCache<K, V> extends MapIteratorCache<K, V> {
    public volatile transient CacheEntry<K, V> cacheEntry1;
    public volatile transient CacheEntry<K, V> cacheEntry2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CacheEntry<K, V> {
        public final K key;
        public final V value;

        public CacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MapRetrievalCache(Map<K, V> backingMap) {
        super(backingMap);
    }

    public final void addToCache(K key, V value) {
        addToCache(new CacheEntry<>(key, value));
    }

    @Override // com.google.common.graph.MapIteratorCache
    public void clearCache() {
        this.cacheEntry = null;
        this.cacheEntry1 = null;
        this.cacheEntry2 = null;
    }

    @Override // com.google.common.graph.MapIteratorCache
    public V get(Object obj) {
        obj.getClass();
        V ifCached = getIfCached(obj);
        if (ifCached != null) {
            return ifCached;
        }
        V v = this.backingMap.get(obj);
        if (v != null) {
            addToCache(obj, v);
        }
        return v;
    }

    @Override // com.google.common.graph.MapIteratorCache
    public V getIfCached(Object obj) {
        V v = (V) super.getIfCached(obj);
        if (v != null) {
            return v;
        }
        CacheEntry<K, V> cacheEntry = this.cacheEntry1;
        if (cacheEntry != null && cacheEntry.key == obj) {
            return cacheEntry.value;
        }
        CacheEntry<K, V> cacheEntry2 = this.cacheEntry2;
        if (cacheEntry2 == null || cacheEntry2.key != obj) {
            return null;
        }
        addToCache(cacheEntry2);
        return cacheEntry2.value;
    }

    public final void addToCache(CacheEntry<K, V> entry) {
        this.cacheEntry2 = this.cacheEntry1;
        this.cacheEntry1 = entry;
    }
}
