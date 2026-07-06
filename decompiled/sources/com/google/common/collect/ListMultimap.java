package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface ListMultimap<K, V> extends Multimap<K, V> {

    /* JADX INFO: renamed from: com.google.common.collect.ListMultimap$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    Map<K, Collection<V>> asMap();

    boolean equals(Object obj);

    /* bridge */ /* synthetic */ Collection get(@ParametricNullness Object key);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    List<V> get(@ParametricNullness K key);

    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ Collection removeAll(Object key);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    List<V> removeAll(Object key);

    @CanIgnoreReturnValue
    /* bridge */ /* synthetic */ Collection replaceValues(@ParametricNullness Object key, Iterable values);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);
}
