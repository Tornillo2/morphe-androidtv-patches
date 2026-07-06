package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
    @Override // com.google.common.base.Function
    @Deprecated
    V apply(K key);

    @Override // com.google.common.cache.Cache
    ConcurrentMap<K, V> asMap();

    @CanIgnoreReturnValue
    V get(K key) throws ExecutionException;

    @CanIgnoreReturnValue
    ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException;

    @CanIgnoreReturnValue
    V getUnchecked(K key);

    void refresh(K key);
}
