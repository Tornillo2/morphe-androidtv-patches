package com.google.common.graph;

import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MapIteratorCache<K, V> {
    public final Map<K, V> backingMap;
    public volatile transient Map.Entry<K, V> cacheEntry;

    /* JADX INFO: renamed from: com.google.common.graph.MapIteratorCache$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AbstractSet<K> {

        /* JADX INFO: renamed from: com.google.common.graph.MapIteratorCache$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class C00261 extends UnmodifiableIterator<K> {
            public final /* synthetic */ AnonymousClass1 this$1;
            public final /* synthetic */ Iterator val$entryIterator;

            public C00261(final AnonymousClass1 this$1, final Iterator val$entryIterator) {
                this.val$entryIterator = val$entryIterator;
                this.this$1 = this$1;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.val$entryIterator.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                Map.Entry<K, V> entry = (Map.Entry) this.val$entryIterator.next();
                MapIteratorCache.this.cacheEntry = entry;
                return entry.getKey();
            }
        }

        public AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object key) {
            return MapIteratorCache.this.containsKey(key);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapIteratorCache.this.backingMap.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<K> iterator() {
            return new C00261(this, MapIteratorCache.this.backingMap.entrySet().iterator());
        }
    }

    public MapIteratorCache(Map<K, V> backingMap) {
        backingMap.getClass();
        this.backingMap = backingMap;
    }

    public final void clear() {
        clearCache();
        this.backingMap.clear();
    }

    public void clearCache() {
        this.cacheEntry = null;
    }

    public final boolean containsKey(Object key) {
        return getIfCached(key) != null || this.backingMap.containsKey(key);
    }

    public V get(Object key) {
        key.getClass();
        V ifCached = getIfCached(key);
        return ifCached == null ? this.backingMap.get(key) : ifCached;
    }

    public V getIfCached(Object key) {
        Map.Entry<K, V> entry = this.cacheEntry;
        if (entry == null || entry.getKey() != key) {
            return null;
        }
        return entry.getValue();
    }

    public final V getWithoutCaching(Object key) {
        key.getClass();
        return this.backingMap.get(key);
    }

    @CanIgnoreReturnValue
    public final V put(K key, V value) {
        key.getClass();
        value.getClass();
        clearCache();
        return this.backingMap.put(key, value);
    }

    @CanIgnoreReturnValue
    public final V remove(Object key) {
        key.getClass();
        clearCache();
        return this.backingMap.remove(key);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AnonymousClass1();
    }
}
