package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingMultimap<K, V> extends ForwardingObject implements Multimap<K, V> {
    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Map<K, Collection<V>> asMap() {
        return delegate().asMap();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        delegate().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(Object key, Object value) {
        return delegate().containsEntry(key, value);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(Object key) {
        return delegate().containsKey(key);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsValue(Object value) {
        return delegate().containsValue(value);
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Multimap<K, V> delegate();

    @Override // com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return delegate().entries();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(Object object) {
        return object == this || delegate().equals(object);
    }

    public Collection<V> get(@ParametricNullness K key) {
        return delegate().get(key);
    }

    @Override // com.google.common.collect.Multimap
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // com.google.common.collect.Multimap
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // com.google.common.collect.Multimap
    public Set<K> keySet() {
        return delegate().keySet();
    }

    @Override // com.google.common.collect.Multimap
    public Multiset<K> keys() {
        return delegate().keys();
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
        return delegate().put(key, value);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
        return delegate().putAll(key, values);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean remove(Object key, Object value) {
        return delegate().remove(key, value);
    }

    @CanIgnoreReturnValue
    public Collection<V> removeAll(Object key) {
        return delegate().removeAll(key);
    }

    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
        return delegate().replaceValues(key, values);
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return delegate().size();
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> values() {
        return delegate().values();
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        return delegate().putAll(multimap);
    }
}
