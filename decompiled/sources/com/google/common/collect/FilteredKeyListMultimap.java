package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class FilteredKeyListMultimap<K, V> extends FilteredKeyMultimap<K, V> implements ListMultimap<K, V> {
    public FilteredKeyListMultimap(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        super(unfiltered, keyPredicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection get(@ParametricNullness Object key) {
        return (List) super.get(key);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection removeAll(Object key) {
        return (List) super.removeAll(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
        return (List) super.replaceValues(key, values);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.FilteredMultimap
    public ListMultimap<K, V> unfiltered() {
        return (ListMultimap) this.unfiltered;
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> get(@ParametricNullness K key) {
        return (List) super.get((Object) key);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> removeAll(Object key) {
        return (List) super.removeAll(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
        return (List) super.replaceValues((Object) key, (Iterable) values);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.FilteredMultimap
    public Multimap unfiltered() {
        return (ListMultimap) this.unfiltered;
    }
}
