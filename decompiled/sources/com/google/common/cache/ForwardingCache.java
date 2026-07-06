package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
        public final Cache<K, V> delegate;

        public SimpleForwardingCache(Cache<K, V> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public final Cache<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }
    }

    @Override // com.google.common.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        return delegate().asMap();
    }

    @Override // com.google.common.cache.Cache
    public void cleanUp() {
        delegate().cleanUp();
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Cache<K, V> delegate();

    @Override // com.google.common.cache.Cache
    public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
        return delegate().get(key, valueLoader);
    }

    @Override // com.google.common.cache.Cache
    public ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> keys) {
        return delegate().getAllPresent(keys);
    }

    @Override // com.google.common.cache.Cache
    public V getIfPresent(Object key) {
        return delegate().getIfPresent(key);
    }

    @Override // com.google.common.cache.Cache
    public void invalidate(Object key) {
        delegate().invalidate(key);
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll(Iterable<? extends Object> keys) {
        delegate().invalidateAll(keys);
    }

    @Override // com.google.common.cache.Cache
    public void put(K key, V value) {
        delegate().put(key, value);
    }

    @Override // com.google.common.cache.Cache
    public void putAll(Map<? extends K, ? extends V> m) {
        delegate().putAll(m);
    }

    @Override // com.google.common.cache.Cache
    public long size() {
        return delegate().size();
    }

    @Override // com.google.common.cache.Cache
    public CacheStats stats() {
        return delegate().stats();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll() {
        delegate().invalidateAll();
    }
}
