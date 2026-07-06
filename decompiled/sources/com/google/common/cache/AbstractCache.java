package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SimpleStatsCounter implements StatsCounter {
        public final LongAddable hitCount = LongAddables.create();
        public final LongAddable missCount = LongAddables.create();
        public final LongAddable loadSuccessCount = LongAddables.create();
        public final LongAddable loadExceptionCount = LongAddables.create();
        public final LongAddable totalLoadTime = LongAddables.create();
        public final LongAddable evictionCount = LongAddables.create();

        public static long negativeToMaxValue(long value) {
            if (value >= 0) {
                return value;
            }
            return Long.MAX_VALUE;
        }

        public void incrementBy(StatsCounter other) {
            CacheStats cacheStatsSnapshot = other.snapshot();
            this.hitCount.add(cacheStatsSnapshot.hitCount);
            this.missCount.add(cacheStatsSnapshot.missCount);
            this.loadSuccessCount.add(cacheStatsSnapshot.loadSuccessCount);
            this.loadExceptionCount.add(cacheStatsSnapshot.loadExceptionCount);
            this.totalLoadTime.add(cacheStatsSnapshot.totalLoadTime);
            this.evictionCount.add(cacheStatsSnapshot.evictionCount);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordEviction() {
            this.evictionCount.increment();
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordHits(int count) {
            this.hitCount.add(count);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadException(long loadTime) {
            this.loadExceptionCount.increment();
            this.totalLoadTime.add(loadTime);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadSuccess(long loadTime) {
            this.loadSuccessCount.increment();
            this.totalLoadTime.add(loadTime);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordMisses(int count) {
            this.missCount.add(count);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public CacheStats snapshot() {
            return new CacheStats(negativeToMaxValue(this.hitCount.sum()), negativeToMaxValue(this.missCount.sum()), negativeToMaxValue(this.loadSuccessCount.sum()), negativeToMaxValue(this.loadExceptionCount.sum()), negativeToMaxValue(this.totalLoadTime.sum()), negativeToMaxValue(this.evictionCount.sum()));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface StatsCounter {
        void recordEviction();

        void recordHits(int count);

        void recordLoadException(long loadTime);

        void recordLoadSuccess(long loadTime);

        void recordMisses(int count);

        CacheStats snapshot();
    }

    @Override // com.google.common.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> keys) {
        V ifPresent;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : keys) {
            if (!linkedHashMap.containsKey(obj) && (ifPresent = getIfPresent(obj)) != null) {
                linkedHashMap.put(obj, ifPresent);
            }
        }
        return ImmutableMap.copyOf((Map) linkedHashMap);
    }

    @Override // com.google.common.cache.Cache
    public void invalidate(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll(Iterable<? extends Object> keys) {
        Iterator<? extends Object> it = keys.iterator();
        if (it.hasNext()) {
            invalidate(it.next());
            throw null;
        }
    }

    @Override // com.google.common.cache.Cache
    public void put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void putAll(Map<? extends K, ? extends V> m) {
        Iterator<Map.Entry<? extends K, ? extends V>> it = m.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<? extends K, ? extends V> next = it.next();
            put(next.getKey(), next.getValue());
            throw null;
        }
    }

    @Override // com.google.common.cache.Cache
    public long size() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public CacheStats stats() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void cleanUp() {
    }
}
