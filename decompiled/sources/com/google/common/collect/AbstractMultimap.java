package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractMultimap<K, V> implements Multimap<K, V> {

    @LazyInit
    public transient Map<K, Collection<V>> asMap;

    @LazyInit
    public transient Collection<Map.Entry<K, V>> entries;

    @LazyInit
    public transient Set<K> keySet;

    @LazyInit
    public transient Multiset<K> keys;

    @LazyInit
    public transient Collection<V> values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Entries extends Multimaps.Entries<K, V> {
        public Entries() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractMultimap.this.entryIterator();
        }

        @Override // com.google.common.collect.Multimaps.Entries
        public Multimap<K, V> multimap() {
            return AbstractMultimap.this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class EntrySet extends AbstractMultimap<K, V>.Entries implements Set<Map.Entry<K, V>> {
        public EntrySet() {
            super();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return Sets.equalsImpl(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Values extends AbstractCollection<V> {
        public Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AbstractMultimap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return AbstractMultimap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return AbstractMultimap.this.valueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractMultimap.this.size();
        }
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> mapCreateAsMap = createAsMap();
        this.asMap = mapCreateAsMap;
        return mapCreateAsMap;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(Object key, Object value) {
        Collection<V> collection = asMap().get(key);
        return collection != null && collection.contains(value);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsValue(Object value) {
        Iterator<Collection<V>> it = asMap().values().iterator();
        while (it.hasNext()) {
            if (it.next().contains(value)) {
                return true;
            }
        }
        return false;
    }

    public abstract Map<K, Collection<V>> createAsMap();

    public abstract Collection<Map.Entry<K, V>> createEntries();

    public abstract Set<K> createKeySet();

    public abstract Multiset<K> createKeys();

    public abstract Collection<V> createValues();

    @Override // com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> collection = this.entries;
        if (collection != null) {
            return collection;
        }
        Collection<Map.Entry<K, V>> collectionCreateEntries = createEntries();
        this.entries = collectionCreateEntries;
        return collectionCreateEntries;
    }

    public abstract Iterator<Map.Entry<K, V>> entryIterator();

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    @Override // com.google.common.collect.Multimap
    public int hashCode() {
        return asMap().hashCode();
    }

    @Override // com.google.common.collect.Multimap
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.Multimap
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        Set<K> setCreateKeySet = createKeySet();
        this.keySet = setCreateKeySet;
        return setCreateKeySet;
    }

    @Override // com.google.common.collect.Multimap
    public Multiset<K> keys() {
        Multiset<K> multiset = this.keys;
        if (multiset != null) {
            return multiset;
        }
        Multiset<K> multisetCreateKeys = createKeys();
        this.keys = multisetCreateKeys;
        return multisetCreateKeys;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
        return get(key).add(value);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean zPut = false;
        for (Map.Entry<? extends K, ? extends V> entry : multimap.entries()) {
            zPut |= put(entry.getKey(), entry.getValue());
        }
        return zPut;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean remove(Object key, Object value) {
        Collection<V> collection = asMap().get(key);
        return collection != null && collection.remove(value);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
        values.getClass();
        Collection<V> collectionRemoveAll = removeAll(key);
        putAll(key, values);
        return collectionRemoveAll;
    }

    public String toString() {
        return asMap().toString();
    }

    public Iterator<V> valueIterator() {
        return new Maps.AnonymousClass2(entries().iterator());
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> collectionCreateValues = createValues();
        this.values = collectionCreateValues;
        return collectionCreateValues;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
        values.getClass();
        if (values instanceof Collection) {
            Collection<? extends V> collection = (Collection) values;
            return !collection.isEmpty() && get(key).addAll(collection);
        }
        Iterator<? extends V> it = values.iterator();
        return it.hasNext() && Iterators.addAll(get(key), it);
    }
}
