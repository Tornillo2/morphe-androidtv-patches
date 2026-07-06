package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class FilteredEntrySetMultimap<K, V> extends FilteredEntryMultimap<K, V> implements FilteredSetMultimap<K, V> {
    public FilteredEntrySetMultimap(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
        super(unfiltered, predicate);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection entries() {
        return (Set) super.entries();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection get(@ParametricNullness Object key) {
        return (Set) super.get(key);
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection removeAll(Object key) {
        return (Set) super.removeAll(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
        return (Set) super.replaceValues(key, values);
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.FilteredMultimap
    public Multimap unfiltered() {
        return (SetMultimap) this.unfiltered;
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.AbstractMultimap
    public Set<Map.Entry<K, V>> createEntries() {
        return Sets.filter(((SetMultimap) this.unfiltered).entries(), this.predicate);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Set<Map.Entry<K, V>> entries() {
        return (Set) super.entries();
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> get(@ParametricNullness K key) {
        return (Set) super.get((Object) key);
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> removeAll(Object key) {
        return (Set) super.removeAll(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
        return (Set) super.replaceValues((Object) key, (Iterable) values);
    }

    @Override // com.google.common.collect.FilteredEntryMultimap, com.google.common.collect.FilteredMultimap
    public SetMultimap<K, V> unfiltered() {
        return (SetMultimap) this.unfiltered;
    }
}
