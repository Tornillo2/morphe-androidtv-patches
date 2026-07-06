package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public abstract class AbstractNavigableMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> implements NavigableMap<K, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DescendingMap extends Maps.DescendingMap<K, V> {
        public DescendingMap() {
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return AbstractNavigableMap.this.descendingEntryIterator();
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public NavigableMap<K, V> forward() {
            return AbstractNavigableMap.this;
        }
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(@ParametricNullness K key) {
        return tailMap(key, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(@ParametricNullness K k) {
        return (K) Maps.keyOrNull(ceilingEntry(k));
    }

    public abstract Iterator<Map.Entry<K, V>> descendingEntryIterator();

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        return new DescendingMap();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return (Map.Entry) Iterators.getNext(entryIterator(), null);
    }

    @Override // java.util.SortedMap
    @ParametricNullness
    public K firstKey() {
        Map.Entry<K, V> entryFirstEntry = firstEntry();
        if (entryFirstEntry != null) {
            return entryFirstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(@ParametricNullness K key) {
        return headMap(key, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K floorKey(@ParametricNullness K k) {
        return (K) Maps.keyOrNull(floorEntry(k));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public abstract V get(Object key);

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
        return headMap(toKey, false);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(@ParametricNullness K key) {
        return tailMap(key, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K higherKey(@ParametricNullness K k) {
        return (K) Maps.keyOrNull(higherEntry(k));
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set<K> keySet() {
        return navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return (Map.Entry) Iterators.getNext(descendingEntryIterator(), null);
    }

    @Override // java.util.SortedMap
    @ParametricNullness
    public K lastKey() {
        Map.Entry<K, V> entryLastEntry = lastEntry();
        if (entryLastEntry != null) {
            return entryLastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(@ParametricNullness K key) {
        return headMap(key, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K lowerKey(@ParametricNullness K k) {
        return (K) Maps.keyOrNull(lowerEntry(k));
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        return new Maps.NavigableKeySet((Map) this);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entryIterator());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingEntryIterator());
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> tailMap(@ParametricNullness K fromKey) {
        return tailMap(fromKey, true);
    }
}
