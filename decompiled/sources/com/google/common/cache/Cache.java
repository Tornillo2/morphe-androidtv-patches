package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use CacheBuilder.newBuilder().build()")
@GwtCompatible
public interface Cache<K, V> {
    ConcurrentMap<K, V> asMap();

    void cleanUp();

    @CanIgnoreReturnValue
    V get(K key, Callable<? extends V> loader) throws ExecutionException;

    ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> keys);

    @CanIgnoreReturnValue
    V getIfPresent(@CompatibleWith("K") Object key);

    void invalidate(@CompatibleWith("K") Object key);

    void invalidateAll();

    void invalidateAll(Iterable<? extends Object> keys);

    void put(K key, V value);

    void putAll(Map<? extends K, ? extends V> m);

    long size();

    CacheStats stats();
}
