package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        public final LoadingCache<K, V> delegate;

        public SimpleForwardingLoadingCache(LoadingCache<K, V> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public Cache delegate() {
            return this.delegate;
        }

        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public final LoadingCache<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }
    }

    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    public V apply(K key) {
        return delegate().apply(key);
    }

    @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
    public abstract LoadingCache<K, V> delegate();

    @Override // com.google.common.cache.LoadingCache
    @CanIgnoreReturnValue
    public V get(K key) throws ExecutionException {
        return delegate().get(key);
    }

    @Override // com.google.common.cache.LoadingCache
    @CanIgnoreReturnValue
    public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        return delegate().getAll(keys);
    }

    @Override // com.google.common.cache.LoadingCache
    @CanIgnoreReturnValue
    public V getUnchecked(K key) {
        return delegate().getUnchecked(key);
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K key) {
        delegate().refresh(key);
    }
}
