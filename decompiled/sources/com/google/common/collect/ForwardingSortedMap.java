package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingSortedMap<K, V> extends ForwardingMap<K, V> implements SortedMap<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardKeySet extends Maps.SortedKeySet<K, V> {
        public StandardKeySet() {
            super((Map) ForwardingSortedMap.this);
        }
    }

    public static int unsafeCompare(Comparator<?> comparator, Object o1, Object o2) {
        return comparator == null ? ((Comparable) o1).compareTo(o2) : comparator.compare(o1, o2);
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return delegate().comparator();
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public abstract SortedMap<K, V> delegate();

    @Override // java.util.SortedMap
    @ParametricNullness
    public K firstKey() {
        return delegate().firstKey();
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
        return delegate().headMap(toKey);
    }

    @Override // java.util.SortedMap
    @ParametricNullness
    public K lastKey() {
        return delegate().lastKey();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMap
    public boolean standardContainsKey(Object key) {
        return unsafeCompare(comparator(), tailMap(key).firstKey(), key) == 0;
    }

    public SortedMap<K, V> standardSubMap(K fromKey, K toKey) {
        Preconditions.checkArgument(unsafeCompare(comparator(), fromKey, toKey) <= 0, "fromKey must be <= toKey");
        return tailMap(fromKey).headMap(toKey);
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
        return delegate().subMap(fromKey, toKey);
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> tailMap(@ParametricNullness K fromKey) {
        return delegate().tailMap(fromKey);
    }
}
