package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import j$.util.DesugarCollections;
import j$.util.Objects;
import j$.util.stream.Collector;
import j$.util.stream.Stream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Multimaps {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AsMap<K, V> extends Maps.ViewCachingAbstractMap<K, Collection<V>> {

        @Weak
        public final Multimap<K, V> multimap;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class EntrySet extends Maps.EntrySet<K, Collection<V>> {
            public EntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                Set<K> setKeySet = AsMap.this.multimap.keySet();
                final Multimap<K, V> multimap = AsMap.this.multimap;
                Objects.requireNonNull(multimap);
                return Maps.asMapEntryIterator(setKeySet, new Function() { // from class: com.google.common.collect.Multimaps$AsMap$EntrySet$$ExternalSyntheticLambda0
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        return multimap.get(obj);
                    }
                });
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object o) {
                if (!contains(o)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) o;
                Objects.requireNonNull(entry);
                AsMap.this.removeValuesForKey(entry.getKey());
                return true;
            }
        }

        public AsMap(Multimap<K, V> multimap) {
            multimap.getClass();
            this.multimap = multimap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.multimap.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.multimap.containsKey(key);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new EntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.multimap.isEmpty();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.multimap.keySet();
        }

        public void removeValuesForKey(Object key) {
            this.multimap.keySet().remove(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.multimap.keySet().size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object key) {
            if (this.multimap.containsKey(key)) {
                return this.multimap.get(key);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object key) {
            if (this.multimap.containsKey(key)) {
                return this.multimap.removeAll(key);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Supplier<? extends List<V>> factory;

        public CustomListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> factory) {
            super(map);
            factory.getClass();
            this.factory = factory;
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
            stream.defaultReadObject();
            Object object = stream.readObject();
            Objects.requireNonNull(object);
            this.factory = (Supplier) object;
            Object object2 = stream.readObject();
            Objects.requireNonNull(object2);
            setMap((Map) object2);
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        @Override // com.google.common.collect.AbstractListMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public List<V> createCollection() {
            return this.factory.get();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Supplier<? extends Collection<V>> factory;

        public CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> factory) {
            super(map);
            factory.getClass();
            this.factory = factory;
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
            stream.defaultReadObject();
            Object object = stream.readObject();
            Objects.requireNonNull(object);
            this.factory = (Supplier) object;
            Object object2 = stream.readObject();
            Objects.requireNonNull(object2);
            setMap((Map) object2);
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> createCollection() {
            return this.factory.get();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            return collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet) collection) : collection instanceof SortedSet ? DesugarCollections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? DesugarCollections.unmodifiableSet((Set) collection) : collection instanceof List ? DesugarCollections.unmodifiableList((List) collection) : DesugarCollections.unmodifiableCollection(collection);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
            return collection instanceof List ? wrapList(key, (List) collection, null) : collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet) collection, null) : collection instanceof SortedSet ? new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet) collection, null) : collection instanceof Set ? new AbstractMapBasedMultimap.WrappedSet(key, (Set) collection) : new AbstractMapBasedMultimap.WrappedCollection(key, collection, null);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Supplier<? extends Set<V>> factory;

        public CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> factory) {
            super(map);
            factory.getClass();
            this.factory = factory;
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
            stream.defaultReadObject();
            Object object = stream.readObject();
            Objects.requireNonNull(object);
            this.factory = (Supplier) object;
            Object object2 = stream.readObject();
            Objects.requireNonNull(object2);
            setMap((Map) object2);
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            return collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet) collection) : collection instanceof SortedSet ? DesugarCollections.unmodifiableSortedSet((SortedSet) collection) : DesugarCollections.unmodifiableSet((Set) collection);
        }

        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> wrapCollection(@ParametricNullness K key, Collection<V> collection) {
            return collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet) collection, null) : collection instanceof SortedSet ? new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet) collection, null) : new AbstractMapBasedMultimap.WrappedSet(key, (Set) collection);
        }

        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public Set<V> createCollection() {
            return this.factory.get();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            multimap().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            return multimap().containsEntry(entry.getKey(), entry.getValue());
        }

        public abstract Multimap<K, V> multimap();

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            return multimap().remove(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return multimap().size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Keys<K, V> extends AbstractMultiset<K> {

        @Weak
        public final Multimap<K, V> multimap;

        public Keys(Multimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.multimap.clear();
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public boolean contains(Object element) {
            return this.multimap.containsKey(element);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object element) {
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), element);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public int distinctElements() {
            return this.multimap.asMap().size();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<K> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public Set<K> elementSet() {
            return this.multimap.keySet();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<Multiset.Entry<K>> entryIterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Multiset.Entry<K>>(this.multimap.asMap().entrySet().iterator()) { // from class: com.google.common.collect.Multimaps.Keys.1
                @Override // com.google.common.collect.TransformedIterator
                public Multiset.Entry<K> transform(final Map.Entry<K, Collection<V>> backingEntry) {
                    return new Multisets.AbstractEntry<K>(this) { // from class: com.google.common.collect.Multimaps.Keys.1.1
                        public final /* synthetic */ AnonymousClass1 this$1;

                        {
                            this.this$1 = this;
                        }

                        @Override // com.google.common.collect.Multiset.Entry
                        public int getCount() {
                            return ((Collection) backingEntry.getValue()).size();
                        }

                        @Override // com.google.common.collect.Multiset.Entry
                        @ParametricNullness
                        public K getElement() {
                            return (K) backingEntry.getKey();
                        }
                    };
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public Iterator<K> iterator() {
            return new Maps.AnonymousClass1(this.multimap.entries().iterator());
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(Object element, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return count(element);
            }
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), element);
            if (collection == null) {
                return 0;
            }
            int size = collection.size();
            if (occurrences >= size) {
                collection.clear();
                return size;
            }
            Iterator it = collection.iterator();
            for (int i = 0; i < occurrences; i++) {
                it.next();
                it.remove();
            }
            return size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return this.multimap.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 7845222491160860175L;
        public final Map<K, V> map;

        public MapMultimap(Map<K, V> map) {
            map.getClass();
            this.map = map;
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            this.map.clear();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean containsEntry(Object key, Object value) {
            return this.map.entrySet().contains(new ImmutableEntry(key, value));
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object key) {
            return this.map.containsKey(key);
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean containsValue(Object value) {
            return this.map.containsValue(value);
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return new AsMap(this);
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Collection<Map.Entry<K, V>> createEntries() {
            throw new AssertionError("unreachable");
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return this.map.keySet();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Multiset<K> createKeys() {
            return new Keys(this);
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Collection<V> createValues() {
            return this.map.values();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return this.map.entrySet().iterator();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public int hashCode() {
            return this.map.hashCode();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean remove(Object key, Object value) {
            return this.map.entrySet().remove(new ImmutableEntry(key, value));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(@ParametricNullness Object key, Iterable values) {
            replaceValues(key, values);
            throw null;
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            return this.map.entrySet();
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> get(@ParametricNullness final K key) {
            return new Sets.ImprovedAbstractSet<V>(this) { // from class: com.google.common.collect.Multimaps.MapMultimap.1
                public final /* synthetic */ MapMultimap this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<V> iterator() {
                    return new Iterator<V>() { // from class: com.google.common.collect.Multimaps.MapMultimap.1.1
                        public int i;

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            if (this.i != 0) {
                                return false;
                            }
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            return anonymousClass1.this$0.map.containsKey(key);
                        }

                        @Override // java.util.Iterator
                        @ParametricNullness
                        public V next() {
                            if (!hasNext()) {
                                throw new NoSuchElementException();
                            }
                            this.i++;
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            return anonymousClass1.this$0.map.get(key);
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            CollectPreconditions.checkRemove(this.i == 1);
                            this.i = -1;
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            anonymousClass1.this$0.map.remove(key);
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return this.this$0.map.containsKey(key) ? 1 : 0;
                }
            };
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> removeAll(Object key) {
            HashSet hashSet = new HashSet(2);
            if (!this.map.containsKey(key)) {
                return hashSet;
            }
            hashSet.add(this.map.remove(key));
            return hashSet;
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TransformedEntriesListMultimap<K, V1, V2> extends TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
        public TransformedEntriesListMultimap(ListMultimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
            super(fromMultimap, transformer);
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V2> get(@ParametricNullness K key) {
            return transform((Object) key, (Collection) this.fromMultimap.get(key));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V2> removeAll(Object key) {
            return transform(key, (Collection) this.fromMultimap.removeAll(key));
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V2> replaceValues(@ParametricNullness K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap
        public List<V2> transform(@ParametricNullness final K key, Collection<V1> values) {
            return Lists.transform((List) values, new Function() { // from class: com.google.common.collect.Multimaps$TransformedEntriesListMultimap$$ExternalSyntheticLambda0
                /* JADX WARN: Type inference incomplete: some casts might be missing */
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return this.f$0.transformer.transformEntry((Object) key, (Object) obj);
                }
            });
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
        public final Multimap<K, V1> fromMultimap;
        public final Maps.EntryTransformer<? super K, ? super V1, V2> transformer;

        public TransformedEntriesMultimap(Multimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
            fromMultimap.getClass();
            this.fromMultimap = fromMultimap;
            transformer.getClass();
            this.transformer = transformer;
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            this.fromMultimap.clear();
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object key) {
            return this.fromMultimap.containsKey(key);
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V2>> createAsMap() {
            return new Maps.TransformedEntriesMap(this.fromMultimap.asMap(), new Maps.EntryTransformer() { // from class: com.google.common.collect.Multimaps$TransformedEntriesMultimap$$ExternalSyntheticLambda1
                @Override // com.google.common.collect.Maps.EntryTransformer
                public final Object transformEntry(Object obj, Object obj2) {
                    return this.f$0.transform(obj, (Collection) obj2);
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Collection<Map.Entry<K, V2>> createEntries() {
            return new AbstractMultimap.Entries();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return this.fromMultimap.keySet();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Multiset<K> createKeys() {
            return this.fromMultimap.keys();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Collection<V2> createValues() {
            return new Collections2.TransformedCollection(this.fromMultimap.entries(), new Function() { // from class: com.google.common.collect.Multimaps$TransformedEntriesMultimap$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    Map.Entry entry = (Map.Entry) obj;
                    return this.f$0.transformer.transformEntry((Object) entry.getKey(), (Object) entry.getValue());
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Iterator<Map.Entry<K, V2>> entryIterator() {
            return new Iterators.AnonymousClass6(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V2> get(@ParametricNullness K key) {
            return transform(key, this.fromMultimap.get(key));
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean isEmpty() {
            return this.fromMultimap.isEmpty();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean put(@ParametricNullness K key, @ParametricNullness V2 value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(@ParametricNullness K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean remove(Object key, Object value) {
            return get(key).remove(value);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V2> removeAll(Object key) {
            return transform(key, this.fromMultimap.removeAll(key));
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V2> replaceValues(@ParametricNullness K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            return this.fromMultimap.size();
        }

        public Collection<V2> transform(@ParametricNullness final K key, Collection<V1> values) {
            Function function = new Function() { // from class: com.google.common.collect.Multimaps$TransformedEntriesMultimap$$ExternalSyntheticLambda2
                /* JADX WARN: Type inference incomplete: some casts might be missing */
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return this.f$0.transformer.transformEntry((Object) key, (Object) obj);
                }
            };
            return values instanceof List ? Lists.transform((List) values, function) : new Collections2.TransformedCollection(values, function);
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public UnmodifiableListMultimap(ListMultimap<K, V> delegate) {
            super(delegate);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public ListMultimap<K, V> delegate() {
            return (ListMultimap) this.delegate;
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection removeAll(Object key) {
            removeAll(key);
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(@ParametricNullness Object key, Iterable values) {
            replaceValues(key, values);
            throw null;
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> get(@ParametricNullness K key) {
            return DesugarCollections.unmodifiableList(delegate().get((Object) key));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Multimap<K, V> delegate;

        @LazyInit
        public transient Collection<Map.Entry<K, V>> entries;

        @LazyInit
        public transient Set<K> keySet;

        @LazyInit
        public transient Multiset<K> keys;

        @LazyInit
        public transient Map<K, Collection<V>> map;

        @LazyInit
        public transient Collection<V> values;

        public UnmodifiableMultimap(Multimap<K, V> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map = this.map;
            if (map != null) {
                return map;
            }
            Map<K, Collection<V>> mapUnmodifiableMap = DesugarCollections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new Multimaps$UnmodifiableMultimap$$ExternalSyntheticLambda0()));
            this.map = mapUnmodifiableMap;
            return mapUnmodifiableMap;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.entries;
            if (collection != null) {
                return collection;
            }
            Collection<Map.Entry<K, V>> collectionUnmodifiableEntries = Multimaps.unmodifiableEntries(this.delegate.entries());
            this.entries = collectionUnmodifiableEntries;
            return collectionUnmodifiableEntries;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V> get(@ParametricNullness K key) {
            return Multimaps.unmodifiableValueCollection(this.delegate.get(key));
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> setUnmodifiableSet = DesugarCollections.unmodifiableSet(this.delegate.keySet());
            this.keySet = setUnmodifiableSet;
            return setUnmodifiableSet;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset = this.keys;
            if (multiset != null) {
                return multiset;
            }
            Multiset<K> multisetUnmodifiableMultiset = Multisets.unmodifiableMultiset(this.delegate.keys());
            this.keys = multisetUnmodifiableMultiset;
            return multisetUnmodifiableMultiset;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> collectionUnmodifiableCollection = DesugarCollections.unmodifiableCollection(this.delegate.values());
            this.values = collectionUnmodifiableCollection;
            return collectionUnmodifiableCollection;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public Multimap<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements SetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public UnmodifiableSetMultimap(SetMultimap<K, V> delegate) {
            super(delegate);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public SetMultimap<K, V> delegate() {
            return (SetMultimap) this.delegate;
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            return Maps.unmodifiableEntrySet(delegate().entries());
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> get(@ParametricNullness K key) {
            return DesugarCollections.unmodifiableSet(delegate().get((Object) key));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
            super((Multimap) delegate);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) ((SetMultimap) this.delegate);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection removeAll(Object key) {
            removeAll(key);
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Collection replaceValues(@ParametricNullness Object key, Iterable values) {
            replaceValues(key, values);
            throw null;
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            return delegate().valueComparator();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set removeAll(Object key) {
            removeAll(key);
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public /* bridge */ /* synthetic */ Set replaceValues(@ParametricNullness Object key, Iterable values) {
            replaceValues(key, values);
            throw null;
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> get(@ParametricNullness K key) {
            return DesugarCollections.unmodifiableSortedSet(delegate().get((Object) key));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> listMultimap) {
        return listMultimap.asMap();
    }

    public static boolean equalsImpl(Multimap<?, ?> multimap, Object object) {
        if (object == multimap) {
            return true;
        }
        if (object instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) object).asMap());
        }
        return false;
    }

    public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        entryPredicate.getClass();
        if (unfiltered instanceof SetMultimap) {
            return filterEntries((SetMultimap) unfiltered, (Predicate) entryPredicate);
        }
        if (unfiltered instanceof FilteredMultimap) {
            return filterFiltered((FilteredMultimap) unfiltered, entryPredicate);
        }
        unfiltered.getClass();
        return new FilteredEntryMultimap(unfiltered, entryPredicate);
    }

    public static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryMultimap(multimap.unfiltered(), Predicates.and(multimap.entryPredicate(), entryPredicate));
    }

    public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof SetMultimap) {
            return filterKeys((SetMultimap) unfiltered, (Predicate) keyPredicate);
        }
        if (unfiltered instanceof ListMultimap) {
            return filterKeys((ListMultimap) unfiltered, (Predicate) keyPredicate);
        }
        if (!(unfiltered instanceof FilteredKeyMultimap)) {
            return unfiltered instanceof FilteredMultimap ? filterFiltered((FilteredMultimap) unfiltered, Maps.keyPredicateOnEntries(keyPredicate)) : new FilteredKeyMultimap(unfiltered, keyPredicate);
        }
        FilteredKeyMultimap filteredKeyMultimap = (FilteredKeyMultimap) unfiltered;
        return new FilteredKeyMultimap(filteredKeyMultimap.unfiltered, Predicates.and(filteredKeyMultimap.keyPredicate, keyPredicate));
    }

    public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries(unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
    }

    @IgnoreJRERequirement
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends Stream<? extends V>> valueFunction, java.util.function.Supplier<M> multimapSupplier) {
        return CollectCollectors.flatteningToMultimap(keyFunction, valueFunction, multimapSupplier);
    }

    public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
        return new MapMultimap(map);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> values, Function<? super V, K> keyFunction) {
        keyFunction.getClass();
        ImmutableListMultimap.Builder builder = new ImmutableListMultimap.Builder();
        while (values.hasNext()) {
            V next = values.next();
            Preconditions.checkNotNull(next, values);
            builder.put((Object) keyFunction.apply(next), (Object) next);
        }
        return builder.build();
    }

    @CanIgnoreReturnValue
    public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> source, M dest) {
        dest.getClass();
        for (Map.Entry<? extends V, ? extends K> entry : source.entries()) {
            dest.put(entry.getValue(), entry.getKey());
        }
        return dest;
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> factory) {
        return new CustomListMultimap(map, factory);
    }

    public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> factory) {
        return new CustomMultimap(map, factory);
    }

    public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> factory) {
        return new CustomSetMultimap(map, factory);
    }

    public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> factory) {
        return new CustomSortedSetMultimap(map, factory);
    }

    @J2ktIncompatible
    public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> multimap) {
        return Synchronized.listMultimap(multimap, null);
    }

    @J2ktIncompatible
    public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
        return Synchronized.multimap(multimap, null);
    }

    @J2ktIncompatible
    public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> multimap) {
        return Synchronized.setMultimap(multimap, null);
    }

    @J2ktIncompatible
    public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> multimap) {
        return Synchronized.sortedSetMultimap(multimap, null);
    }

    @IgnoreJRERequirement
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends V> valueFunction, java.util.function.Supplier<M> multimapSupplier) {
        return CollectCollectors.toMultimap(keyFunction, valueFunction, multimapSupplier);
    }

    public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesMultimap(fromMap, transformer);
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> fromMultimap, final Function<? super V1, V2> function) {
        function.getClass();
        return new TransformedEntriesListMultimap((Multimap) fromMultimap, new Maps.EntryTransformer() { // from class: com.google.common.collect.Multimaps$$ExternalSyntheticLambda0
            @Override // com.google.common.collect.Maps.EntryTransformer
            public final Object transformEntry(Object obj, Object obj2) {
                return function.apply(obj2);
            }
        });
    }

    public static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> entries) {
        return entries instanceof Set ? Maps.unmodifiableEntrySet((Set) entries) : new Maps.UnmodifiableEntries(DesugarCollections.unmodifiableCollection(entries));
    }

    @InlineMe(replacement = "checkNotNull(delegate)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> delegate) {
        delegate.getClass();
        return delegate;
    }

    public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> delegate) {
        return ((delegate instanceof UnmodifiableMultimap) || (delegate instanceof ImmutableMultimap)) ? delegate : new UnmodifiableMultimap(delegate);
    }

    @InlineMe(replacement = "checkNotNull(delegate)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> delegate) {
        delegate.getClass();
        return delegate;
    }

    public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
        return delegate instanceof UnmodifiableSortedSetMultimap ? delegate : new UnmodifiableSortedSetMultimap((Multimap) delegate);
    }

    public static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
        return collection instanceof SortedSet ? DesugarCollections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? DesugarCollections.unmodifiableSet((Set) collection) : collection instanceof List ? DesugarCollections.unmodifiableList((List) collection) : DesugarCollections.unmodifiableCollection(collection);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Supplier<? extends SortedSet<V>> factory;
        public transient Comparator<? super V> valueComparator;

        public CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> factory) {
            super(map);
            factory.getClass();
            this.factory = factory;
            this.valueComparator = factory.get().comparator();
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
            stream.defaultReadObject();
            Object object = stream.readObject();
            Objects.requireNonNull(object);
            Supplier<? extends SortedSet<V>> supplier = (Supplier) object;
            this.factory = supplier;
            this.valueComparator = supplier.get().comparator();
            Object object2 = stream.readObject();
            Objects.requireNonNull(object2);
            setMap((Map) object2);
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            return this.valueComparator;
        }

        @Override // com.google.common.collect.AbstractSortedSetMultimap, com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public SortedSet<V> createCollection() {
            return this.factory.get();
        }
    }

    public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> setMultimap) {
        return setMultimap.asMap();
    }

    public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((SetMultimap) unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesListMultimap((Multimap) fromMap, (Maps.EntryTransformer) transformer);
    }

    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> delegate) {
        return ((delegate instanceof UnmodifiableListMultimap) || (delegate instanceof ImmutableListMultimap)) ? delegate : new UnmodifiableListMultimap((Multimap) delegate);
    }

    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> delegate) {
        return ((delegate instanceof UnmodifiableSetMultimap) || (delegate instanceof ImmutableSetMultimap)) ? delegate : new UnmodifiableSetMultimap((Multimap) delegate);
    }

    public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap.asMap();
    }

    public static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntrySetMultimap((Multimap) multimap.unfiltered(), Predicates.and(multimap.entryPredicate(), entryPredicate));
    }

    @InlineMe(replacement = "checkNotNull(delegate)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> delegate) {
        delegate.getClass();
        return delegate;
    }

    public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
        return multimap.asMap();
    }

    public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> fromMultimap, final Function<? super V1, V2> function) {
        function.getClass();
        return new TransformedEntriesMultimap(fromMultimap, new Maps.EntryTransformer() { // from class: com.google.common.collect.Multimaps$$ExternalSyntheticLambda1
            @Override // com.google.common.collect.Maps.EntryTransformer
            public final Object transformEntry(Object obj, Object obj2) {
                return function.apply(obj2);
            }
        });
    }

    public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        entryPredicate.getClass();
        if (unfiltered instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) unfiltered, (Predicate) entryPredicate);
        }
        unfiltered.getClass();
        return new FilteredEntrySetMultimap((Multimap) unfiltered, (Predicate) entryPredicate);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> values, Function<? super V, K> keyFunction) {
        return index(values.iterator(), keyFunction);
    }

    public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof FilteredKeySetMultimap) {
            return new FilteredKeySetMultimap(r2.unfiltered, Predicates.and(((FilteredKeySetMultimap) unfiltered).keyPredicate, keyPredicate));
        }
        if (unfiltered instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) unfiltered, Maps.keyPredicateOnEntries(keyPredicate));
        }
        return new FilteredKeySetMultimap((Multimap) unfiltered, (Predicate) keyPredicate);
    }

    public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof FilteredKeyListMultimap) {
            return new FilteredKeyListMultimap(r2.unfiltered, Predicates.and(((FilteredKeyListMultimap) unfiltered).keyPredicate, keyPredicate));
        }
        return new FilteredKeyListMultimap((Multimap) unfiltered, (Predicate) keyPredicate);
    }
}
