package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class FilteredMultimapValues<K, V> extends AbstractCollection<V> {

    @Weak
    public final FilteredMultimap<K, V> multimap;

    public FilteredMultimapValues(FilteredMultimap<K, V> multimap) {
        multimap.getClass();
        this.multimap = multimap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.multimap.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object o) {
        return this.multimap.containsValue(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        return new Maps.AnonymousClass2(this.multimap.entries().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object o) {
        Predicate<? super Map.Entry<K, V>> predicateEntryPredicate = this.multimap.entryPredicate();
        Iterator<Map.Entry<K, V>> it = this.multimap.unfiltered().entries().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (predicateEntryPredicate.apply(next) && Objects.equal(next.getValue(), o)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(new Predicates.InPredicate(c))));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(new Predicates.NotPredicate(new Predicates.InPredicate(c)))));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.multimap.size();
    }
}
