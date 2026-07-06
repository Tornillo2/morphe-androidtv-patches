package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import com.google.j2objc.annotations.Weak;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class ImmutableMultimap<K, V> extends BaseImmutableMultimap<K, V> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final transient ImmutableMap<K, ? extends ImmutableCollection<V>> map;
    public final transient int size;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EntryCollection<K, V> extends ImmutableCollection<Map.Entry<K, V>> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @Weak
        public final ImmutableMultimap<K, V> multimap;

        public EntryCollection(ImmutableMultimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) object;
            return this.multimap.containsEntry(entry.getKey(), entry.getValue());
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return this.multimap.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.multimap.entryIterator();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    @GwtIncompatible
    public static class FieldSettersHolder {
        public static final Serialization.FieldSetter<? super ImmutableMultimap<?, ?>> MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
        public static final Serialization.FieldSetter<? super ImmutableMultimap<?, ?>> SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Keys extends ImmutableMultiset<K> {
        public Keys() {
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Use KeysSerializedForm");
        }

        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            return ImmutableMultimap.this.containsKey(object);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object element) {
            ImmutableCollection<V> immutableCollection = ImmutableMultimap.this.map.get(element);
            if (immutableCollection == null) {
                return 0;
            }
            return immutableCollection.size();
        }

        @Override // com.google.common.collect.ImmutableMultiset
        public Multiset.Entry<K> getEntry(int index) {
            Map.Entry<K, ? extends ImmutableCollection<V>> entry = ImmutableMultimap.this.map.entrySet().asList().get(index);
            return new Multisets.ImmutableEntry(entry.getKey(), entry.getValue().size());
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return ImmutableMultimap.this.size();
        }

        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new KeysSerializedForm(ImmutableMultimap.this);
        }

        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
        public ImmutableSet<K> elementSet() {
            return ImmutableMultimap.this.keySet();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    @GwtIncompatible
    public static final class KeysSerializedForm implements Serializable {
        public final ImmutableMultimap<?, ?> multimap;

        public KeysSerializedForm(ImmutableMultimap<?, ?> multimap) {
            this.multimap = multimap;
        }

        public Object readResolve() {
            return this.multimap.keys();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Values<K, V> extends ImmutableCollection<V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @Weak
        public final transient ImmutableMultimap<K, V> multimap;

        public Values(ImmutableMultimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            return this.multimap.containsValue(object);
        }

        @Override // com.google.common.collect.ImmutableCollection
        @GwtIncompatible
        public int copyIntoArray(Object[] dst, int offset) {
            UnmodifiableIterator<? extends ImmutableCollection<V>> it = this.multimap.map.values().iterator();
            while (it.hasNext()) {
                offset = it.next().copyIntoArray(dst, offset);
            }
            return offset;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<V> iterator() {
            return this.multimap.valueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return this.multimap.valueIterator();
        }
    }

    public ImmutableMultimap(ImmutableMap<K, ? extends ImmutableCollection<V>> map, int size) {
        this.map = map;
        this.size = size;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> Builder<K, V> builderWithExpectedKeys(int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new Builder<>(expectedKeys);
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        if (multimap instanceof ImmutableMultimap) {
            ImmutableMultimap<K, V> immutableMultimap = (ImmutableMultimap) multimap;
            if (!immutableMultimap.isPartialView()) {
                return immutableMultimap;
            }
        }
        return ImmutableListMultimap.copyOf((Multimap) multimap);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1) {
        return ImmutableListMultimap.of((Object) k1, (Object) v1);
    }

    @Override // com.google.common.collect.Multimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(Object key, Object value) {
        return super.containsEntry(key, value);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean containsValue(Object value) {
        return value != null && super.containsValue(value);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        throw new AssertionError("unreachable");
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public abstract ImmutableCollection<V> get(K key);

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public abstract ImmutableMultimap<V, K> inverse();

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public boolean isPartialView() {
        return this.map.isPartialView();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean putAll(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public /* bridge */ /* synthetic */ Collection removeAll(Object key) {
        removeAll(key);
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public /* bridge */ /* synthetic */ Collection replaceValues(Object key, Iterable values) {
        replaceValues(key, values);
        throw null;
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DoNotMock
    public static class Builder<K, V> {
        public Map<K, ImmutableCollection.Builder<V>> builderMap;
        public int expectedValuesPerKey = 4;
        public Comparator<? super K> keyComparator;
        public Comparator<? super V> valueComparator;

        public Builder() {
        }

        public ImmutableMultimap<K, V> build() {
            Map<K, ImmutableCollection.Builder<V>> map = this.builderMap;
            if (map == null) {
                return EmptyImmutableListMultimap.INSTANCE;
            }
            Collection collectionEntrySet = map.entrySet();
            Comparator<? super K> comparator = this.keyComparator;
            if (comparator != null) {
                collectionEntrySet = Ordering.from(comparator).onKeys().immutableSortedCopy(collectionEntrySet);
            }
            return ImmutableListMultimap.fromMapBuilderEntries(collectionEntrySet, this.valueComparator);
        }

        @CanIgnoreReturnValue
        public Builder<K, V> combine(Builder<K, V> other) {
            Map<K, ImmutableCollection.Builder<V>> map = other.builderMap;
            if (map != null) {
                for (Map.Entry<K, ImmutableCollection.Builder<V>> entry : map.entrySet()) {
                    putAll(entry.getKey(), entry.getValue().build());
                }
            }
            return this;
        }

        public Map<K, ImmutableCollection.Builder<V>> ensureBuilderMapNonNull() {
            Map<K, ImmutableCollection.Builder<V>> map = this.builderMap;
            if (map != null) {
                return map;
            }
            CompactHashMap compactHashMapCreate = CompactHashMap.create();
            this.builderMap = compactHashMapCreate;
            return compactHashMapCreate;
        }

        public int expectedValueCollectionSize(int defaultExpectedValues, Iterable<?> values) {
            return values instanceof Collection ? Math.max(defaultExpectedValues, ((Collection) values).size()) : defaultExpectedValues;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> expectedValuesPerKey(int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            this.expectedValuesPerKey = Math.max(expectedValuesPerKey, 1);
            return this;
        }

        public ImmutableCollection.Builder<V> newValueCollectionBuilderWithExpectedSize(int expectedSize) {
            return ImmutableList.builderWithExpectedSize(expectedSize);
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
            keyComparator.getClass();
            this.keyComparator = keyComparator;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
            valueComparator.getClass();
            this.valueComparator = valueComparator;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K key, V value) {
            CollectPreconditions.checkEntryNotNull(key, value);
            ImmutableCollection.Builder<V> builderNewValueCollectionBuilderWithExpectedSize = ensureBuilderMapNonNull().get(key);
            if (builderNewValueCollectionBuilderWithExpectedSize == null) {
                builderNewValueCollectionBuilderWithExpectedSize = newValueCollectionBuilderWithExpectedSize(this.expectedValuesPerKey);
                ensureBuilderMapNonNull().put(key, builderNewValueCollectionBuilderWithExpectedSize);
            }
            builderNewValueCollectionBuilderWithExpectedSize.add(value);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it = entries.iterator();
            while (it.hasNext()) {
                put(it.next());
            }
            return this;
        }

        public Builder(int expectedKeys) {
            if (expectedKeys > 0) {
                this.builderMap = Maps.newLinkedHashMapWithExpectedSize(expectedKeys);
            }
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, Iterable<? extends V> values) {
            if (key != null) {
                Iterator<? extends V> it = values.iterator();
                if (it.hasNext()) {
                    ImmutableCollection.Builder<V> builderNewValueCollectionBuilderWithExpectedSize = ensureBuilderMapNonNull().get(key);
                    if (builderNewValueCollectionBuilderWithExpectedSize == null) {
                        builderNewValueCollectionBuilderWithExpectedSize = newValueCollectionBuilderWithExpectedSize(expectedValueCollectionSize(this.expectedValuesPerKey, values));
                        ensureBuilderMapNonNull().put(key, builderNewValueCollectionBuilderWithExpectedSize);
                    }
                    while (it.hasNext()) {
                        V next = it.next();
                        CollectPreconditions.checkEntryNotNull(key, next);
                        builderNewValueCollectionBuilderWithExpectedSize.add(next);
                    }
                }
                return this;
            }
            throw new NullPointerException("null key in entry: null=" + Iterables.toString(values));
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, V... values) {
            return putAll(key, Arrays.asList(values));
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry<? extends K, Collection<? extends V>> entry : multimap.asMap().entrySet()) {
                putAll(entry.getKey(), entry.getValue());
            }
            return this;
        }
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2) {
        return ImmutableListMultimap.of((Object) k1, (Object) v1, (Object) k2, (Object) v2);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public ImmutableMap<K, Collection<V>> asMap() {
        return this.map;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public ImmutableCollection<Map.Entry<K, V>> createEntries() {
        return new EntryCollection(this);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public ImmutableMultiset<K> createKeys() {
        return new Keys();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public ImmutableCollection<Map.Entry<K, V>> entries() {
        return (ImmutableCollection) super.entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
        return new UnmodifiableIterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.ImmutableMultimap.1
            public final Iterator<? extends Map.Entry<K, ? extends ImmutableCollection<V>>> asMapItr;
            public K currentKey = null;
            public Iterator<V> valueItr = Iterators.ArrayItr.EMPTY;

            {
                this.asMapItr = ImmutableMultimap.this.map.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.asMapItr.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                if (!this.valueItr.hasNext()) {
                    Map.Entry<K, ? extends ImmutableCollection<V>> next = this.asMapItr.next();
                    this.currentKey = next.getKey();
                    this.valueItr = next.getValue().iterator();
                }
                K k = this.currentKey;
                Objects.requireNonNull(k);
                return new ImmutableEntry(k, this.valueItr.next());
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public ImmutableSet<K> keySet() {
        return this.map.keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public ImmutableMultiset<K> keys() {
        return (ImmutableMultiset) super.keys();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableCollection<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableCollection<V> replaceValues(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public UnmodifiableIterator<V> valueIterator() {
        return new UnmodifiableIterator<V>() { // from class: com.google.common.collect.ImmutableMultimap.2
            public Iterator<? extends ImmutableCollection<V>> valueCollectionItr;
            public Iterator<V> valueItr = Iterators.ArrayItr.EMPTY;

            {
                this.valueCollectionItr = ImmutableMultimap.this.map.values().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.valueCollectionItr.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                if (!this.valueItr.hasNext()) {
                    this.valueItr = this.valueCollectionItr.next().iterator();
                }
                return this.valueItr.next();
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return ImmutableListMultimap.of((Object) k1, (Object) v1, (Object) k2, (Object) v2, (Object) k3, (Object) v3);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableListMultimap.of((Object) k1, (Object) v1, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4);
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
        return ImmutableListMultimap.copyOf((Iterable) entries);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ImmutableListMultimap.of((Object) k1, (Object) v1, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4, (Object) k5, (Object) v5);
    }

    public static <K, V> ImmutableMultimap<K, V> of() {
        return EmptyImmutableListMultimap.INSTANCE;
    }
}
