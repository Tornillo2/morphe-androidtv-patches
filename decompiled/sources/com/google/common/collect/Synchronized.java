package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Multiset;
import com.google.common.collect.Synchronized;
import com.google.common.collect.Table;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtCompatible(emulated = true)
public final class Synchronized {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Set<Map.Entry<K, Collection<V>>> asMapEntrySet;
        public transient Collection<Collection<V>> asMapValues;

        public SynchronizedAsMap(Map<K, Collection<V>> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public boolean containsValue(Object o) {
            return values().contains(o);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.mutex) {
                try {
                    if (this.asMapEntrySet == null) {
                        this.asMapEntrySet = new SynchronizedAsMapEntries((Object) ((Map) this.delegate).entrySet(), this.mutex);
                    }
                    set = this.asMapEntrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.mutex) {
                try {
                    if (this.asMapValues == null) {
                        this.asMapValues = new SynchronizedAsMapValues((Object) ((Map) this.delegate).values(), this.mutex);
                    }
                    collection = this.asMapValues;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<V> get(Object key) {
            Collection<V> collectionTypePreservingCollection;
            synchronized (this.mutex) {
                Collection collection = (Collection) super.get(key);
                collectionTypePreservingCollection = collection == null ? null : Synchronized.typePreservingCollection(collection, this.mutex);
            }
            return collectionTypePreservingCollection;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedAsMapValues(Collection<Collection<V>> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Collection<V>> iterator() {
            return new TransformedIterator<Collection<V>, Collection<V>>(super.iterator()) { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapValues.1
                @Override // com.google.common.collect.TransformedIterator
                public Collection<V> transform(Collection<V> from) {
                    return Synchronized.typePreservingCollection(from, SynchronizedAsMapValues.this.mutex);
                }
            };
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements BiMap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @RetainedWith
        public transient BiMap<V, K> inverse;
        public transient Set<V> valueSet;

        public SynchronizedBiMap(BiMap<K, V> delegate, Object mutex, BiMap<V, K> inverse) {
            super((Object) delegate, mutex);
            this.inverse = inverse;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public BiMap<K, V> delegate() {
            return (BiMap) ((Map) this.delegate);
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(@ParametricNullness K key, @ParametricNullness V value) {
            V vForcePut;
            synchronized (this.mutex) {
                vForcePut = delegate().forcePut(key, value);
            }
            return vForcePut;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.mutex) {
                try {
                    if (this.inverse == null) {
                        this.inverse = new SynchronizedBiMap(delegate().inverse(), this.mutex, this);
                    }
                    biMap = this.inverse;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return biMap;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<V> values() {
            Set<V> set;
            synchronized (this.mutex) {
                try {
                    if (this.valueSet == null) {
                        this.valueSet = new SynchronizedSet((Object) delegate().values(), this.mutex);
                    }
                    set = this.valueSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedCollection(Collection<E> delegate, Object mutex) {
            super(delegate, mutex);
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            boolean zAdd;
            synchronized (this.mutex) {
                zAdd = delegate().add(e);
            }
            return zAdd;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> c) {
            boolean zAddAll;
            synchronized (this.mutex) {
                zAddAll = delegate().addAll(c);
            }
            return zAddAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean contains(Object o) {
            boolean zContains;
            synchronized (this.mutex) {
                zContains = delegate().contains(o);
            }
            return zContains;
        }

        public boolean containsAll(Collection<?> c) {
            boolean zContainsAll;
            synchronized (this.mutex) {
                zContainsAll = delegate().containsAll(c);
            }
            return zContainsAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Collection<E> delegate() {
            return (Collection) this.delegate;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = delegate().isEmpty();
            }
            return zIsEmpty;
        }

        public Iterator<E> iterator() {
            return delegate().iterator();
        }

        public boolean remove(Object o) {
            boolean zRemove;
            synchronized (this.mutex) {
                zRemove = delegate().remove(o);
            }
            return zRemove;
        }

        public boolean removeAll(Collection<?> c) {
            boolean zRemoveAll;
            synchronized (this.mutex) {
                zRemoveAll = delegate().removeAll(c);
            }
            return zRemoveAll;
        }

        public boolean retainAll(Collection<?> c) {
            boolean zRetainAll;
            synchronized (this.mutex) {
                zRetainAll = delegate().retainAll(c);
            }
            return zRetainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = delegate().toArray();
            }
            return array;
        }

        public SynchronizedCollection(Collection collection, Object obj, AnonymousClass1 anonymousClass1) {
            super(collection, obj);
        }

        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) delegate().toArray(tArr);
            }
            return tArr2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedDeque(Deque<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.Deque
        public void addFirst(E e) {
            synchronized (this.mutex) {
                ((Deque) super.delegate()).addFirst(e);
            }
        }

        @Override // java.util.Deque
        public void addLast(E e) {
            synchronized (this.mutex) {
                ((Deque) super.delegate()).addLast(e);
            }
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (Deque) super.delegate();
        }

        @Override // java.util.Deque
        public Iterator<E> descendingIterator() {
            Iterator<E> itDescendingIterator;
            synchronized (this.mutex) {
                itDescendingIterator = ((Deque) super.delegate()).descendingIterator();
            }
            return itDescendingIterator;
        }

        @Override // java.util.Deque
        public E getFirst() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).getFirst();
            }
            return e;
        }

        @Override // java.util.Deque
        public E getLast() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).getLast();
            }
            return e;
        }

        @Override // java.util.Deque
        public boolean offerFirst(E e) {
            boolean zOfferFirst;
            synchronized (this.mutex) {
                zOfferFirst = ((Deque) super.delegate()).offerFirst(e);
            }
            return zOfferFirst;
        }

        @Override // java.util.Deque
        public boolean offerLast(E e) {
            boolean zOfferLast;
            synchronized (this.mutex) {
                zOfferLast = ((Deque) super.delegate()).offerLast(e);
            }
            return zOfferLast;
        }

        @Override // java.util.Deque
        public E peekFirst() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).peekFirst();
            }
            return e;
        }

        @Override // java.util.Deque
        public E peekLast() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).peekLast();
            }
            return e;
        }

        @Override // java.util.Deque
        public E pollFirst() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).pollFirst();
            }
            return e;
        }

        @Override // java.util.Deque
        public E pollLast() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).pollLast();
            }
            return e;
        }

        @Override // java.util.Deque
        public E pop() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).pop();
            }
            return e;
        }

        @Override // java.util.Deque
        public void push(E e) {
            synchronized (this.mutex) {
                ((Deque) super.delegate()).push(e);
            }
        }

        @Override // java.util.Deque
        public E removeFirst() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).removeFirst();
            }
            return e;
        }

        @Override // java.util.Deque
        public boolean removeFirstOccurrence(Object o) {
            boolean zRemoveFirstOccurrence;
            synchronized (this.mutex) {
                zRemoveFirstOccurrence = ((Deque) super.delegate()).removeFirstOccurrence(o);
            }
            return zRemoveFirstOccurrence;
        }

        @Override // java.util.Deque
        public E removeLast() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((Deque) super.delegate()).removeLast();
            }
            return e;
        }

        @Override // java.util.Deque
        public boolean removeLastOccurrence(Object o) {
            boolean zRemoveLastOccurrence;
            synchronized (this.mutex) {
                zRemoveLastOccurrence = ((Deque) super.delegate()).removeLastOccurrence(o);
            }
            return zRemoveLastOccurrence;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Collection delegate() {
            return (Deque) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Deque<E> delegate() {
            return (Deque) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Queue delegate() {
            return (Deque) super.delegate();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedList(List<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.List
        public void add(int index, E element) {
            synchronized (this.mutex) {
                delegate().add(index, element);
            }
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends E> c) {
            boolean zAddAll;
            synchronized (this.mutex) {
                zAddAll = delegate().addAll(index, c);
            }
            return zAddAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public List<E> delegate() {
            return (List) ((Collection) this.delegate);
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object o) {
            boolean zEquals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = delegate().equals(o);
            }
            return zEquals;
        }

        @Override // java.util.List
        public E get(int index) {
            E e;
            synchronized (this.mutex) {
                e = delegate().get(index);
            }
            return e;
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = delegate().hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.List
        public int indexOf(Object o) {
            int iIndexOf;
            synchronized (this.mutex) {
                iIndexOf = delegate().indexOf(o);
            }
            return iIndexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object o) {
            int iLastIndexOf;
            synchronized (this.mutex) {
                iLastIndexOf = delegate().lastIndexOf(o);
            }
            return iLastIndexOf;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return delegate().listIterator();
        }

        @Override // java.util.List
        public E remove(int index) {
            E eRemove;
            synchronized (this.mutex) {
                eRemove = delegate().remove(index);
            }
            return eRemove;
        }

        @Override // java.util.List
        public E set(int index, E element) {
            E e;
            synchronized (this.mutex) {
                e = delegate().set(index, element);
            }
            return e;
        }

        @Override // java.util.List
        public List<E> subList(int fromIndex, int toIndex) {
            List<E> list;
            synchronized (this.mutex) {
                list = Synchronized.list(delegate().subList(fromIndex, toIndex), this.mutex);
            }
            return list;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int index) {
            return delegate().listIterator(index);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ListMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedListMultimap(ListMultimap<K, V> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public ListMultimap<K, V> delegate() {
            return (ListMultimap) ((Multimap) this.delegate);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> get(K key) {
            List<V> list;
            synchronized (this.mutex) {
                list = Synchronized.list(delegate().get((Object) key), this.mutex);
            }
            return list;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> removeAll(Object key) {
            List<V> listRemoveAll;
            synchronized (this.mutex) {
                listRemoveAll = delegate().removeAll(key);
            }
            return listRemoveAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public List<V> replaceValues(K key, Iterable<? extends V> values) {
            List<V> listReplaceValues;
            synchronized (this.mutex) {
                listReplaceValues = delegate().replaceValues((Object) key, (Iterable) values);
            }
            return listReplaceValues;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Set<Map.Entry<K, V>> entrySet;
        public transient Set<K> keySet;
        public transient Collection<V> values;

        public SynchronizedMap(Map<K, V> delegate, Object mutex) {
            super(delegate, mutex);
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // java.util.Map
        public boolean containsKey(Object key) {
            boolean zContainsKey;
            synchronized (this.mutex) {
                zContainsKey = delegate().containsKey(key);
            }
            return zContainsKey;
        }

        public boolean containsValue(Object value) {
            boolean zContainsValue;
            synchronized (this.mutex) {
                zContainsValue = delegate().containsValue(value);
            }
            return zContainsValue;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map<K, V> delegate() {
            return (Map) this.delegate;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = new SynchronizedSet((Object) delegate().entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public boolean equals(Object o) {
            boolean zEquals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = delegate().equals(o);
            }
            return zEquals;
        }

        public V get(Object key) {
            V v;
            synchronized (this.mutex) {
                v = delegate().get(key);
            }
            return v;
        }

        @Override // java.util.Map
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = delegate().hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = delegate().isEmpty();
            }
            return zIsEmpty;
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = new SynchronizedSet((Object) delegate().keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public V put(K key, V value) {
            V vPut;
            synchronized (this.mutex) {
                vPut = delegate().put(key, value);
            }
            return vPut;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                delegate().putAll(map);
            }
        }

        @Override // java.util.Map
        public V remove(Object key) {
            V vRemove;
            synchronized (this.mutex) {
                vRemove = delegate().remove(key);
            }
            return vRemove;
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                try {
                    if (this.values == null) {
                        this.values = new SynchronizedCollection((Object) delegate().values(), this.mutex);
                    }
                    collection = this.values;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @VisibleForTesting
    public static final class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient NavigableSet<K> descendingKeySet;
        public transient NavigableMap<K, V> descendingMap;
        public transient NavigableSet<K> navigableKeySet;

        public SynchronizedNavigableMap(NavigableMap<K, V> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).ceilingEntry(key), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            K k2;
            synchronized (this.mutex) {
                k2 = (K) ((NavigableMap) super.delegate()).ceilingKey(k);
            }
            return k2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<K> navigableSet = this.descendingKeySet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    SynchronizedNavigableSet synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableMap) super.delegate()).descendingKeySet(), this.mutex);
                    this.descendingKeySet = synchronizedNavigableSet;
                    return synchronizedNavigableSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            synchronized (this.mutex) {
                try {
                    NavigableMap<K, V> navigableMap = this.descendingMap;
                    if (navigableMap != null) {
                        return navigableMap;
                    }
                    SynchronizedNavigableMap synchronizedNavigableMap = new SynchronizedNavigableMap((Object) ((NavigableMap) super.delegate()).descendingMap(), this.mutex);
                    this.descendingMap = synchronizedNavigableMap;
                    return synchronizedNavigableMap;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).firstEntry(), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).floorEntry(key), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            K k2;
            synchronized (this.mutex) {
                k2 = (K) ((NavigableMap) super.delegate()).floorKey(k);
            }
            return k2;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap((Object) ((NavigableMap) super.delegate()).headMap(toKey, inclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).higherEntry(key), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            K k2;
            synchronized (this.mutex) {
                k2 = (K) ((NavigableMap) super.delegate()).higherKey(k);
            }
            return k2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).lastEntry(), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).lowerEntry(key), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            K k2;
            synchronized (this.mutex) {
                k2 = (K) ((NavigableMap) super.delegate()).lowerKey(k);
            }
            return k2;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<K> navigableSet = this.navigableKeySet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    SynchronizedNavigableSet synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableMap) super.delegate()).navigableKeySet(), this.mutex);
                    this.navigableKeySet = synchronizedNavigableSet;
                    return synchronizedNavigableSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).pollFirstEntry(), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> entryNullableSynchronizedEntry;
            synchronized (this.mutex) {
                entryNullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(((NavigableMap) super.delegate()).pollLastEntry(), this.mutex);
            }
            return entryNullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap((Object) ((NavigableMap) super.delegate()).subMap(fromKey, fromInclusive, toKey, toInclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap((Object) ((NavigableMap) super.delegate()).tailMap(fromKey, inclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public Map delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public NavigableMap<K, V> delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedMap delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @VisibleForTesting
    public static final class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient NavigableSet<E> descendingSet;

        public SynchronizedNavigableSet(NavigableSet<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = (E) ((NavigableSet) super.delegate()).ceiling(e);
            }
            return e2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return ((NavigableSet) super.delegate()).descendingIterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<E> navigableSet = this.descendingSet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    SynchronizedNavigableSet synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableSet) super.delegate()).descendingSet(), this.mutex);
                    this.descendingSet = synchronizedNavigableSet;
                    return synchronizedNavigableSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = (E) ((NavigableSet) super.delegate()).floor(e);
            }
            return e2;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableSet) super.delegate()).headSet(toElement, inclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = (E) ((NavigableSet) super.delegate()).higher(e);
            }
            return e2;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = (E) ((NavigableSet) super.delegate()).lower(e);
            }
            return e2;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((NavigableSet) super.delegate()).pollFirst();
            }
            return e;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E e;
            synchronized (this.mutex) {
                e = (E) ((NavigableSet) super.delegate()).pollLast();
            }
            return e;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableSet) super.delegate()).subSet(fromElement, fromInclusive, toElement, toInclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet((Object) ((NavigableSet) super.delegate()).tailSet(fromElement, inclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Collection delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public NavigableSet<E> delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Set delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedSet delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E toElement) {
            return headSet(toElement, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E fromElement) {
            return tailSet(fromElement, true);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedObject implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Object delegate;
        public final Object mutex;

        public SynchronizedObject(Object delegate, Object mutex) {
            delegate.getClass();
            this.delegate = delegate;
            this.mutex = mutex == null ? this : mutex;
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            synchronized (this.mutex) {
                stream.defaultWriteObject();
            }
        }

        public Object delegate() {
            return this.delegate;
        }

        public String toString() {
            String string;
            synchronized (this.mutex) {
                string = this.delegate.toString();
            }
            return string;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedQueue(Queue<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Queue<E> delegate() {
            return (Queue) ((Collection) this.delegate);
        }

        @Override // java.util.Queue
        public E element() {
            E eElement;
            synchronized (this.mutex) {
                eElement = delegate().element();
            }
            return eElement;
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            boolean zOffer;
            synchronized (this.mutex) {
                zOffer = delegate().offer(e);
            }
            return zOffer;
        }

        @Override // java.util.Queue
        public E peek() {
            E ePeek;
            synchronized (this.mutex) {
                ePeek = delegate().peek();
            }
            return ePeek;
        }

        @Override // java.util.Queue
        public E poll() {
            E ePoll;
            synchronized (this.mutex) {
                ePoll = delegate().poll();
            }
            return ePoll;
        }

        @Override // java.util.Queue
        public E remove() {
            E eRemove;
            synchronized (this.mutex) {
                eRemove = delegate().remove();
            }
            return eRemove;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedRandomAccessList(List<E> list, Object mutex) {
            super((Object) list, mutex);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedSet(Set<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Set<E> delegate() {
            return (Set) ((Collection) this.delegate);
        }

        public boolean equals(Object o) {
            boolean zEquals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = delegate().equals(o);
            }
            return zEquals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = delegate().hashCode();
            }
            return iHashCode;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements SetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Set<Map.Entry<K, V>> entrySet;

        public SynchronizedSetMultimap(SetMultimap<K, V> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public SetMultimap<K, V> delegate() {
            return (SetMultimap) ((Multimap) this.delegate);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = new SynchronizedSet((Object) delegate().entries(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> get(K key) {
            SynchronizedSet synchronizedSet;
            synchronized (this.mutex) {
                synchronizedSet = new SynchronizedSet((Object) delegate().get((Object) key), this.mutex);
            }
            return synchronizedSet;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> removeAll(Object key) {
            Set<V> setRemoveAll;
            synchronized (this.mutex) {
                setRemoveAll = delegate().removeAll(key);
            }
            return setRemoveAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Set<V> replaceValues(K key, Iterable<? extends V> values) {
            Set<V> setReplaceValues;
            synchronized (this.mutex) {
                setReplaceValues = delegate().replaceValues((Object) key, (Iterable) values);
            }
            return setReplaceValues;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedSortedMap(SortedMap<K, V> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedMap<K, V> delegate() {
            return (SortedMap) ((Map) this.delegate);
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K kFirstKey;
            synchronized (this.mutex) {
                kFirstKey = delegate().firstKey();
            }
            return kFirstKey;
        }

        public SortedMap<K, V> headMap(K toKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap((Object) delegate().headMap(toKey), this.mutex);
            }
            return synchronizedSortedMap;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K kLastKey;
            synchronized (this.mutex) {
                kLastKey = delegate().lastKey();
            }
            return kLastKey;
        }

        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap((Object) delegate().subMap(fromKey, toKey), this.mutex);
            }
            return synchronizedSortedMap;
        }

        public SortedMap<K, V> tailMap(K fromKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap((Object) delegate().tailMap(fromKey), this.mutex);
            }
            return synchronizedSortedMap;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements SortedSetMultimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedSortedSetMultimap(SortedSetMultimap<K, V> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public Multimap delegate() {
            return (SortedSetMultimap) super.delegate();
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> comparatorValueComparator;
            synchronized (this.mutex) {
                comparatorValueComparator = ((SortedSetMultimap) super.delegate()).valueComparator();
            }
            return comparatorValueComparator;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public SetMultimap delegate() {
            return (SortedSetMultimap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> get(K key) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet((Object) ((SortedSetMultimap) super.delegate()).get((Object) key), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> removeAll(Object key) {
            SortedSet<V> sortedSetRemoveAll;
            synchronized (this.mutex) {
                sortedSetRemoveAll = ((SortedSetMultimap) super.delegate()).removeAll(key);
            }
            return sortedSetRemoveAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
            SortedSet<V> sortedSetReplaceValues;
            synchronized (this.mutex) {
                sortedSetReplaceValues = ((SortedSetMultimap) super.delegate()).replaceValues((Object) key, (Iterable) values);
            }
            return sortedSetReplaceValues;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (SortedSetMultimap) super.delegate();
        }
    }

    public static SortedSet access$100(SortedSet sortedSet, Object obj) {
        return new SynchronizedSortedSet((Object) sortedSet, obj);
    }

    public static Collection access$500(Collection collection, Object obj) {
        return new SynchronizedCollection((Object) collection, obj);
    }

    public static <K, V> BiMap<K, V> biMap(BiMap<K, V> bimap, Object mutex) {
        return ((bimap instanceof SynchronizedBiMap) || (bimap instanceof ImmutableBiMap)) ? bimap : new SynchronizedBiMap(bimap, mutex, null);
    }

    public static <E> Collection<E> collection(Collection<E> collection, Object mutex) {
        return new SynchronizedCollection((Object) collection, mutex);
    }

    public static <E> Deque<E> deque(Deque<E> deque, Object mutex) {
        return new SynchronizedDeque((Object) deque, mutex);
    }

    public static <E> List<E> list(List<E> list, Object mutex) {
        return list instanceof RandomAccess ? new SynchronizedRandomAccessList((Object) list, mutex) : new SynchronizedList((Object) list, mutex);
    }

    public static <K, V> ListMultimap<K, V> listMultimap(ListMultimap<K, V> multimap, Object mutex) {
        return ((multimap instanceof SynchronizedListMultimap) || (multimap instanceof BaseImmutableMultimap)) ? multimap : new SynchronizedListMultimap((Object) multimap, mutex);
    }

    @VisibleForTesting
    public static <K, V> Map<K, V> map(Map<K, V> map, Object mutex) {
        return new SynchronizedMap((Object) map, mutex);
    }

    public static <K, V> Multimap<K, V> multimap(Multimap<K, V> multimap, Object mutex) {
        return ((multimap instanceof SynchronizedMultimap) || (multimap instanceof BaseImmutableMultimap)) ? multimap : new SynchronizedMultimap((Object) multimap, mutex);
    }

    public static <E> Multiset<E> multiset(Multiset<E> multiset, Object mutex) {
        return ((multiset instanceof SynchronizedMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new SynchronizedMultiset((Object) multiset, mutex);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap) {
        return new SynchronizedNavigableMap((Object) navigableMap, (Object) null);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet) {
        return new SynchronizedNavigableSet((Object) navigableSet, (Object) null);
    }

    @GwtIncompatible
    public static <K, V> Map.Entry<K, V> nullableSynchronizedEntry(Map.Entry<K, V> entry, Object mutex) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry((Object) entry, mutex);
    }

    public static <E> Queue<E> queue(Queue<E> queue, Object mutex) {
        return queue instanceof SynchronizedQueue ? queue : new SynchronizedQueue((Object) queue, mutex);
    }

    @VisibleForTesting
    public static <E> Set<E> set(Set<E> set, Object mutex) {
        return new SynchronizedSet((Object) set, mutex);
    }

    public static <K, V> SetMultimap<K, V> setMultimap(SetMultimap<K, V> multimap, Object mutex) {
        return ((multimap instanceof SynchronizedSetMultimap) || (multimap instanceof BaseImmutableMultimap)) ? multimap : new SynchronizedSetMultimap((Object) multimap, mutex);
    }

    public static <K, V> SortedMap<K, V> sortedMap(SortedMap<K, V> sortedMap, Object mutex) {
        return new SynchronizedSortedMap((Object) sortedMap, mutex);
    }

    public static <E> SortedSet<E> sortedSet(SortedSet<E> set, Object mutex) {
        return new SynchronizedSortedSet((Object) set, mutex);
    }

    public static <K, V> SortedSetMultimap<K, V> sortedSetMultimap(SortedSetMultimap<K, V> multimap, Object mutex) {
        return multimap instanceof SynchronizedSortedSetMultimap ? multimap : new SynchronizedSortedSetMultimap((Object) multimap, mutex);
    }

    public static <R, C, V> Table<R, C, V> table(Table<R, C, V> table, Object mutex) {
        return new SynchronizedTable((Object) table, mutex);
    }

    public static <E> Collection<E> typePreservingCollection(Collection<E> collection, Object mutex) {
        return collection instanceof SortedSet ? new SynchronizedSortedSet(collection, mutex) : collection instanceof Set ? new SynchronizedSet(collection, mutex) : collection instanceof List ? list((List) collection, mutex) : new SynchronizedCollection((Object) collection, mutex);
    }

    public static <E> Set<E> typePreservingSet(Set<E> set, Object mutex) {
        return set instanceof SortedSet ? new SynchronizedSortedSet(set, mutex) : new SynchronizedSet((Object) set, mutex);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static final class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedEntry(Map.Entry<K, V> delegate, Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (Map.Entry) this.delegate;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            boolean zEquals;
            synchronized (this.mutex) {
                zEquals = ((Map.Entry) this.delegate).equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            K k;
            synchronized (this.mutex) {
                k = (K) ((Map.Entry) this.delegate).getKey();
            }
            return k;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            V v;
            synchronized (this.mutex) {
                v = (V) ((Map.Entry) this.delegate).getValue();
            }
            return v;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = ((Map.Entry) this.delegate).hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2;
            synchronized (this.mutex) {
                v2 = (V) ((Map.Entry) this.delegate).setValue(v);
            }
            return v2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map.Entry<K, V> delegate() {
            return (Map.Entry) this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedTable<R, C, V> extends SynchronizedObject implements Table<R, C, V> {
        public static Map $r8$lambda$1i2HAmmRHIuzO4_kH56A2gYm0mc(SynchronizedTable synchronizedTable, Map map) {
            return new SynchronizedMap((Object) map, synchronizedTable.mutex);
        }

        public static Map $r8$lambda$K1pO_QhfsO_ursXLD5BCIbJryiQ(SynchronizedTable synchronizedTable, Map map) {
            return new SynchronizedMap((Object) map, synchronizedTable.mutex);
        }

        public SynchronizedTable(Table<R, C, V> delegate, Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            SynchronizedSet synchronizedSet;
            synchronized (this.mutex) {
                synchronizedSet = new SynchronizedSet((Object) ((Table) this.delegate).cellSet(), this.mutex);
            }
            return synchronizedSet;
        }

        @Override // com.google.common.collect.Table
        public void clear() {
            synchronized (this.mutex) {
                ((Table) this.delegate).clear();
            }
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> column(@ParametricNullness C columnKey) {
            SynchronizedMap synchronizedMap;
            synchronized (this.mutex) {
                synchronizedMap = new SynchronizedMap((Object) ((Table) this.delegate).column(columnKey), this.mutex);
            }
            return synchronizedMap;
        }

        @Override // com.google.common.collect.Table
        public Set<C> columnKeySet() {
            SynchronizedSet synchronizedSet;
            synchronized (this.mutex) {
                synchronizedSet = new SynchronizedSet((Object) ((Table) this.delegate).columnKeySet(), this.mutex);
            }
            return synchronizedSet;
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            SynchronizedMap synchronizedMap;
            synchronized (this.mutex) {
                synchronizedMap = new SynchronizedMap((Object) Maps.transformValues(((Table) this.delegate).columnMap(), new Function() { // from class: com.google.common.collect.Synchronized$SynchronizedTable$$ExternalSyntheticLambda1
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        return Synchronized.SynchronizedTable.$r8$lambda$K1pO_QhfsO_ursXLD5BCIbJryiQ(this.f$0, (Map) obj);
                    }
                }), this.mutex);
            }
            return synchronizedMap;
        }

        @Override // com.google.common.collect.Table
        public boolean contains(Object rowKey, Object columnKey) {
            boolean zContains;
            synchronized (this.mutex) {
                zContains = ((Table) this.delegate).contains(rowKey, columnKey);
            }
            return zContains;
        }

        @Override // com.google.common.collect.Table
        public boolean containsColumn(Object columnKey) {
            boolean zContainsColumn;
            synchronized (this.mutex) {
                zContainsColumn = ((Table) this.delegate).containsColumn(columnKey);
            }
            return zContainsColumn;
        }

        @Override // com.google.common.collect.Table
        public boolean containsRow(Object rowKey) {
            boolean zContainsRow;
            synchronized (this.mutex) {
                zContainsRow = ((Table) this.delegate).containsRow(rowKey);
            }
            return zContainsRow;
        }

        @Override // com.google.common.collect.Table
        public boolean containsValue(Object value) {
            boolean zContainsValue;
            synchronized (this.mutex) {
                zContainsValue = ((Table) this.delegate).containsValue(value);
            }
            return zContainsValue;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Table<R, C, V> delegate() {
            return (Table) this.delegate;
        }

        @Override // com.google.common.collect.Table
        public boolean equals(Object obj) {
            boolean zEquals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = ((Table) this.delegate).equals(obj);
            }
            return zEquals;
        }

        @Override // com.google.common.collect.Table
        public V get(Object obj, Object obj2) {
            V v;
            synchronized (this.mutex) {
                v = (V) ((Table) this.delegate).get(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.Table
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = ((Table) this.delegate).hashCode();
            }
            return iHashCode;
        }

        @Override // com.google.common.collect.Table
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = ((Table) this.delegate).isEmpty();
            }
            return zIsEmpty;
        }

        @Override // com.google.common.collect.Table
        public V put(@ParametricNullness R r, @ParametricNullness C c, @ParametricNullness V v) {
            V v2;
            synchronized (this.mutex) {
                v2 = (V) ((Table) this.delegate).put(r, c, v);
            }
            return v2;
        }

        @Override // com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            synchronized (this.mutex) {
                ((Table) this.delegate).putAll(table);
            }
        }

        @Override // com.google.common.collect.Table
        public V remove(Object obj, Object obj2) {
            V v;
            synchronized (this.mutex) {
                v = (V) ((Table) this.delegate).remove(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> row(@ParametricNullness R rowKey) {
            SynchronizedMap synchronizedMap;
            synchronized (this.mutex) {
                synchronizedMap = new SynchronizedMap((Object) ((Table) this.delegate).row(rowKey), this.mutex);
            }
            return synchronizedMap;
        }

        @Override // com.google.common.collect.Table
        public Set<R> rowKeySet() {
            SynchronizedSet synchronizedSet;
            synchronized (this.mutex) {
                synchronizedSet = new SynchronizedSet((Object) ((Table) this.delegate).rowKeySet(), this.mutex);
            }
            return synchronizedSet;
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            SynchronizedMap synchronizedMap;
            synchronized (this.mutex) {
                synchronizedMap = new SynchronizedMap((Object) Maps.transformValues(((Table) this.delegate).rowMap(), new Function() { // from class: com.google.common.collect.Synchronized$SynchronizedTable$$ExternalSyntheticLambda0
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        return Synchronized.SynchronizedTable.$r8$lambda$1i2HAmmRHIuzO4_kH56A2gYm0mc(this.f$0, (Map) obj);
                    }
                }), this.mutex);
            }
            return synchronizedMap;
        }

        @Override // com.google.common.collect.Table
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = ((Table) this.delegate).size();
            }
            return size;
        }

        @Override // com.google.common.collect.Table
        public Collection<V> values() {
            SynchronizedCollection synchronizedCollection;
            synchronized (this.mutex) {
                synchronizedCollection = new SynchronizedCollection((Object) ((Table) this.delegate).values(), this.mutex);
            }
            return synchronizedCollection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Object delegate() {
            return (Table) this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedAsMapEntries(Set<Map.Entry<K, Collection<V>>> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            boolean zContainsEntryImpl;
            synchronized (this.mutex) {
                zContainsEntryImpl = Maps.containsEntryImpl(delegate(), o);
            }
            return zContainsEntryImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            boolean zContainsAllImpl;
            synchronized (this.mutex) {
                zContainsAllImpl = Collections2.containsAllImpl(delegate(), c);
            }
            return zContainsAllImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSet, java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            boolean zEqualsImpl;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEqualsImpl = Sets.equalsImpl(delegate(), o);
            }
            return zEqualsImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(super.iterator()) { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapEntries.1
                @Override // com.google.common.collect.TransformedIterator
                public Map.Entry<K, Collection<V>> transform(final Map.Entry<K, Collection<V>> entry) {
                    return new ForwardingMapEntry<K, Collection<V>>(this) { // from class: com.google.common.collect.Synchronized.SynchronizedAsMapEntries.1.1
                        public final /* synthetic */ AnonymousClass1 this$1;

                        {
                            this.this$1 = this;
                        }

                        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                        public Map.Entry<K, Collection<V>> delegate() {
                            return entry;
                        }

                        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                        public Collection<V> getValue() {
                            return Synchronized.typePreservingCollection((Collection) entry.getValue(), SynchronizedAsMapEntries.this.mutex);
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            boolean zRemoveEntryImpl;
            synchronized (this.mutex) {
                zRemoveEntryImpl = Maps.removeEntryImpl(delegate(), o);
            }
            return zRemoveEntryImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            boolean zRemoveAll;
            synchronized (this.mutex) {
                zRemoveAll = Iterators.removeAll(delegate().iterator(), c);
            }
            return zRemoveAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            boolean zRetainAll;
            synchronized (this.mutex) {
                zRetainAll = Iterators.retainAll(delegate().iterator(), c);
            }
            return zRetainAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] arrayImpl;
            synchronized (this.mutex) {
                arrayImpl = ObjectArrays.toArrayImpl(delegate());
            }
            return arrayImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) ObjectArrays.toArrayImpl(delegate(), tArr);
            }
            return tArr2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedMultimap<K, V> extends SynchronizedObject implements Multimap<K, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Map<K, Collection<V>> asMap;
        public transient Collection<Map.Entry<K, V>> entries;
        public transient Set<K> keySet;
        public transient Multiset<K> keys;
        public transient Collection<V> valuesCollection;

        public SynchronizedMultimap(Multimap<K, V> delegate, Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.mutex) {
                try {
                    if (this.asMap == null) {
                        this.asMap = new SynchronizedAsMap((Object) delegate().asMap(), this.mutex);
                    }
                    map = this.asMap;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return map;
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsEntry(Object key, Object value) {
            boolean zContainsEntry;
            synchronized (this.mutex) {
                zContainsEntry = delegate().containsEntry(key, value);
            }
            return zContainsEntry;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object key) {
            boolean zContainsKey;
            synchronized (this.mutex) {
                zContainsKey = delegate().containsKey(key);
            }
            return zContainsKey;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsValue(Object value) {
            boolean zContainsValue;
            synchronized (this.mutex) {
                zContainsValue = delegate().containsValue(value);
            }
            return zContainsValue;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Multimap<K, V> delegate() {
            return (Multimap) this.delegate;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.mutex) {
                try {
                    if (this.entries == null) {
                        this.entries = Synchronized.typePreservingCollection(delegate().entries(), this.mutex);
                    }
                    collection = this.entries;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public boolean equals(Object o) {
            boolean zEquals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = delegate().equals(o);
            }
            return zEquals;
        }

        public Collection<V> get(@ParametricNullness K key) {
            Collection<V> collectionTypePreservingCollection;
            synchronized (this.mutex) {
                collectionTypePreservingCollection = Synchronized.typePreservingCollection(delegate().get(key), this.mutex);
            }
            return collectionTypePreservingCollection;
        }

        @Override // com.google.common.collect.Multimap
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = delegate().hashCode();
            }
            return iHashCode;
        }

        @Override // com.google.common.collect.Multimap
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = delegate().isEmpty();
            }
            return zIsEmpty;
        }

        @Override // com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = Synchronized.typePreservingSet(delegate().keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.mutex) {
                try {
                    if (this.keys == null) {
                        this.keys = Synchronized.multiset(delegate().keys(), this.mutex);
                    }
                    multiset = this.keys;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return multiset;
        }

        @Override // com.google.common.collect.Multimap
        public boolean put(@ParametricNullness K key, @ParametricNullness V value) {
            boolean zPut;
            synchronized (this.mutex) {
                zPut = delegate().put(key, value);
            }
            return zPut;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(@ParametricNullness K key, Iterable<? extends V> values) {
            boolean zPutAll;
            synchronized (this.mutex) {
                zPutAll = delegate().putAll(key, values);
            }
            return zPutAll;
        }

        @Override // com.google.common.collect.Multimap
        public boolean remove(Object key, Object value) {
            boolean zRemove;
            synchronized (this.mutex) {
                zRemove = delegate().remove(key, value);
            }
            return zRemove;
        }

        public Collection<V> removeAll(Object key) {
            Collection<V> collectionRemoveAll;
            synchronized (this.mutex) {
                collectionRemoveAll = delegate().removeAll(key);
            }
            return collectionRemoveAll;
        }

        public Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
            Collection<V> collectionReplaceValues;
            synchronized (this.mutex) {
                collectionReplaceValues = delegate().replaceValues(key, values);
            }
            return collectionReplaceValues;
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                try {
                    if (this.valuesCollection == null) {
                        this.valuesCollection = new SynchronizedCollection((Object) delegate().values(), this.mutex);
                    }
                    collection = this.valuesCollection;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean zPutAll;
            synchronized (this.mutex) {
                zPutAll = delegate().putAll(multimap);
            }
            return zPutAll;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements Multiset<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public transient Set<E> elementSet;
        public transient Set<Multiset.Entry<E>> entrySet;

        public SynchronizedMultiset(Multiset<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // com.google.common.collect.Multiset
        public int add(@ParametricNullness E e, int n) {
            int iAdd;
            synchronized (this.mutex) {
                iAdd = delegate().add(e, n);
            }
            return iAdd;
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object o) {
            int iCount;
            synchronized (this.mutex) {
                iCount = delegate().count(o);
            }
            return iCount;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Multiset<E> delegate() {
            return (Multiset) ((Collection) this.delegate);
        }

        @Override // com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.mutex) {
                try {
                    if (this.elementSet == null) {
                        this.elementSet = Synchronized.typePreservingSet(delegate().elementSet(), this.mutex);
                    }
                    set = this.elementSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = Synchronized.typePreservingSet(delegate().entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public boolean equals(Object o) {
            boolean zEquals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = delegate().equals(o);
            }
            return zEquals;
        }

        @Override // java.util.Collection, com.google.common.collect.Multiset
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = delegate().hashCode();
            }
            return iHashCode;
        }

        @Override // com.google.common.collect.Multiset
        public int remove(Object o, int n) {
            int iRemove;
            synchronized (this.mutex) {
                iRemove = delegate().remove(o, n);
            }
            return iRemove;
        }

        @Override // com.google.common.collect.Multiset
        public int setCount(@ParametricNullness E element, int count) {
            int count2;
            synchronized (this.mutex) {
                count2 = delegate().setCount(element, count);
            }
            return count2;
        }

        @Override // com.google.common.collect.Multiset
        public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
            boolean count;
            synchronized (this.mutex) {
                count = delegate().setCount(element, oldCount, newCount);
            }
            return count;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public SynchronizedSortedSet(SortedSet<E> delegate, Object mutex) {
            super((Object) delegate, mutex);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedSet
        public E first() {
            E eFirst;
            synchronized (this.mutex) {
                eFirst = delegate().first();
            }
            return eFirst;
        }

        public SortedSet<E> headSet(E toElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet((Object) delegate().headSet(toElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // java.util.SortedSet
        public E last() {
            E eLast;
            synchronized (this.mutex) {
                eLast = delegate().last();
            }
            return eLast;
        }

        public SortedSet<E> subSet(E fromElement, E toElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet((Object) delegate().subSet(fromElement, toElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        public SortedSet<E> tailSet(E fromElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet((Object) delegate().tailSet(fromElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedSet<E> delegate() {
            return (SortedSet) super.delegate();
        }
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap, Object mutex) {
        return new SynchronizedNavigableMap((Object) navigableMap, mutex);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet, Object mutex) {
        return new SynchronizedNavigableSet((Object) navigableSet, mutex);
    }
}
