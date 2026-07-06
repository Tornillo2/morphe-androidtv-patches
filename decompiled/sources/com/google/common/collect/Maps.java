package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.Collector;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Maps {

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1<K, V> extends TransformedIterator<Map.Entry<K, V>, K> {
        public AnonymousClass1(Iterator backingIterator) {
            super(backingIterator);
        }

        @Override // com.google.common.collect.TransformedIterator
        @ParametricNullness
        public K transform(Map.Entry<K, V> entry) {
            return entry.getKey();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2<K, V> extends TransformedIterator<Map.Entry<K, V>, V> {
        public AnonymousClass2(Iterator backingIterator) {
            super(backingIterator);
        }

        @Override // com.google.common.collect.TransformedIterator
        @ParametricNullness
        public V transform(Map.Entry<K, V> entry) {
            return entry.getValue();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3<K, V> extends TransformedIterator<K, Map.Entry<K, V>> {
        public final /* synthetic */ Function val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Iterator backingIterator, final Function val$function) {
            super(backingIterator);
            this.val$function = val$function;
        }

        @Override // com.google.common.collect.TransformedIterator
        public Map.Entry<K, V> transform(@ParametricNullness K key) {
            return new ImmutableEntry(key, this.val$function.apply(key));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass7<K, V> extends AbstractMapEntry<K, V> {
        public final /* synthetic */ Map.Entry val$entry;

        public AnonymousClass7(final Map.Entry val$entry) {
            this.val$entry = val$entry;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getKey() {
            return (K) this.val$entry.getKey();
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getValue() {
            return (V) this.val$entry.getValue();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$8, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass8<K, V> extends UnmodifiableIterator<Map.Entry<K, V>> {
        public final /* synthetic */ Iterator val$entryIterator;

        public AnonymousClass8(final Iterator val$entryIterator) {
            this.val$entryIterator = val$entryIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.val$entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return Maps.unmodifiableEntry((Map.Entry) this.val$entryIterator.next());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AbstractFilteredMap<K, V> extends ViewCachingAbstractMap<K, V> {
        public final Predicate<? super Map.Entry<K, V>> predicate;
        public final Map<K, V> unfiltered;

        public AbstractFilteredMap(Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
            this.unfiltered = unfiltered;
            this.predicate = predicate;
        }

        public boolean apply(Object key, @ParametricNullness V value) {
            return this.predicate.apply(new ImmutableEntry(key, value));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.unfiltered.containsKey(key) && apply(key, this.unfiltered.get(key));
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return new FilteredMapValues(this, this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            V v = this.unfiltered.get(key);
            if (v == null || !apply(key, v)) {
                return null;
            }
            return v;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return entrySet().isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(@ParametricNullness K key, @ParametricNullness V value) {
            Preconditions.checkArgument(apply(key, value));
            return this.unfiltered.put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                Preconditions.checkArgument(apply(entry.getKey(), entry.getValue()));
            }
            this.unfiltered.putAll(map);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            if (containsKey(key)) {
                return this.unfiltered.remove(key);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AsMapView<K, V> extends ViewCachingAbstractMap<K, V> {
        public final Function<? super K, V> function;
        public final Set<K> set;

        public AsMapView(Set<K> set, Function<? super K, V> function) {
            set.getClass();
            this.set = set;
            function.getClass();
            this.function = function;
        }

        public Set<K> backingSet() {
            return this.set;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            backingSet().clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return backingSet().contains(key);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() { // from class: com.google.common.collect.Maps.AsMapView.1EntrySetImpl
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return Maps.asMapEntryIterator(AsMapView.this.backingSet(), AsMapView.this.function);
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return AsMapView.this;
                }
            };
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new AnonymousClass4(backingSet());
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return new Collections2.TransformedCollection(this.set, this.function);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            if (Collections2.safeContains(backingSet(), key)) {
                return this.function.apply(key);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            if (backingSet().remove(key)) {
                return this.function.apply(key);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return backingSet().size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BiMapConverter<A, B> extends Converter<A, B> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final BiMap<A, B> bimap;

        public BiMapConverter(BiMap<A, B> bimap) {
            super(true);
            bimap.getClass();
            this.bimap = bimap;
        }

        public static <X, Y> Y convert(BiMap<X, Y> bimap, X input) {
            Y y = bimap.get(input);
            Preconditions.checkArgument(y != null, "No non-null mapping present for input: %s", input);
            return y;
        }

        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) convert(this.bimap.inverse(), b);
        }

        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return (B) convert(this.bimap, a);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof BiMapConverter) {
                return this.bimap.equals(((BiMapConverter) object).bimap);
            }
            return false;
        }

        public int hashCode() {
            return this.bimap.hashCode();
        }

        public String toString() {
            return "Maps.asConverter(" + this.bimap + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static abstract class DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {

        @LazyInit
        public transient Comparator<? super K> comparator;

        @LazyInit
        public transient Set<Map.Entry<K, V>> entrySet;

        @LazyInit
        public transient NavigableSet<K> navigableKeySet;

        private static <T> Ordering<T> reverse(Comparator<T> forward) {
            return Ordering.from(forward).reverse();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(@ParametricNullness K key) {
            return forward().floorEntry(key);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(@ParametricNullness K key) {
            return forward().floorKey(key);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator = this.comparator;
            if (comparator != null) {
                return comparator;
            }
            Comparator<? super K> comparator2 = forward().comparator();
            if (comparator2 == null) {
                comparator2 = NaturalOrdering.INSTANCE;
            }
            Ordering orderingReverse = reverse(comparator2);
            this.comparator = orderingReverse;
            return orderingReverse;
        }

        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() { // from class: com.google.common.collect.Maps.DescendingMap.1EntrySetImpl
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return DescendingMap.this.entryIterator();
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return DescendingMap.this;
                }
            };
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return forward();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return forward().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return forward();
        }

        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
            this.entrySet = setCreateEntrySet;
            return setCreateEntrySet;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return forward().lastEntry();
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K firstKey() {
            return forward().lastKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(@ParametricNullness K key) {
            return forward().ceilingEntry(key);
        }

        @Override // java.util.NavigableMap
        public K floorKey(@ParametricNullness K key) {
            return forward().ceilingKey(key);
        }

        public abstract NavigableMap<K, V> forward();

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(@ParametricNullness K toKey, boolean inclusive) {
            return forward().tailMap(toKey, inclusive).descendingMap();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(@ParametricNullness K key) {
            return forward().lowerEntry(key);
        }

        @Override // java.util.NavigableMap
        public K higherKey(@ParametricNullness K key) {
            return forward().lowerKey(key);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return forward().firstEntry();
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K lastKey() {
            return forward().firstKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(@ParametricNullness K key) {
            return forward().higherEntry(key);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(@ParametricNullness K key) {
            return forward().higherKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> navigableSet = this.navigableKeySet;
            if (navigableSet != null) {
                return navigableSet;
            }
            NavigableKeySet navigableKeySet = new NavigableKeySet((Map) this);
            this.navigableKeySet = navigableKeySet;
            return navigableKeySet;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return forward().pollLastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return forward().pollFirstEntry();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
            return forward().subMap(toKey, toInclusive, fromKey, fromInclusive).descendingMap();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
            return forward().headMap(fromKey, inclusive).descendingMap();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
        public Collection<V> values() {
            return new Values(this);
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public final Map<K, V> delegate() {
            return forward();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
            return headMap(toKey, false);
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

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum EntryFunction implements Function<Map.Entry<?, ?>, Object> {
        KEY { // from class: com.google.common.collect.Maps.EntryFunction.1
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public Object apply2(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE { // from class: com.google.common.collect.Maps.EntryFunction.2
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public Object apply2(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        };

        EntryFunction(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class EntrySet<K, V> extends Sets.ImprovedAbstractSet<Map.Entry<K, V>> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object objSafeGet = Maps.safeGet(map(), key);
            if (Objects.equal(objSafeGet, entry.getValue())) {
                return objSafeGet != null || map().containsKey(key);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        public abstract Map<K, V> map();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (contains(o) && (o instanceof Map.Entry)) {
                return map().keySet().remove(((Map.Entry) o).getKey());
            }
            return false;
        }

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            try {
                c.getClass();
                return Sets.removeAllImpl(this, c);
            } catch (UnsupportedOperationException unused) {
                return Sets.removeAllImpl(this, c.iterator());
            }
        }

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            try {
                c.getClass();
                return super.retainAll(c);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSetNewHashSetWithExpectedSize = Sets.newHashSetWithExpectedSize(c.size());
                for (Object obj : c) {
                    if (contains(obj) && (obj instanceof Map.Entry)) {
                        hashSetNewHashSetWithExpectedSize.add(((Map.Entry) obj).getKey());
                    }
                }
                return map().keySet().retainAll(hashSetNewHashSetWithExpectedSize);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface EntryTransformer<K, V1, V2> {
        @ParametricNullness
        V2 transformEntry(@ParametricNullness K key, @ParametricNullness V1 value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FilteredEntryBiMap<K, V> extends FilteredEntryMap<K, V> implements BiMap<K, V> {

        @RetainedWith
        public final BiMap<V, K> inverse;

        public FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Map.Entry<K, V>> predicate) {
            super(delegate, predicate);
            this.inverse = new FilteredEntryBiMap(delegate.inverse(), new Maps$FilteredEntryBiMap$$ExternalSyntheticLambda0(predicate), this);
        }

        public static <K, V> Predicate<Map.Entry<V, K>> inversePredicate(Predicate<? super Map.Entry<K, V>> forwardPredicate) {
            return new Maps$FilteredEntryBiMap$$ExternalSyntheticLambda0(forwardPredicate);
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(@ParametricNullness K k, @ParametricNullness V v) {
            Preconditions.checkArgument(apply(k, v));
            return (V) ((BiMap) this.unfiltered).forcePut(k, v);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            return this.inverse;
        }

        public BiMap<K, V> unfiltered() {
            return (BiMap) this.unfiltered;
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Collection values() {
            return this.inverse.keySet();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Set<V> values() {
            return this.inverse.keySet();
        }

        public FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Map.Entry<K, V>> predicate, BiMap<V, K> inverse) {
            super(delegate, predicate);
            this.inverse = inverse;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FilteredEntryMap<K, V> extends AbstractFilteredMap<K, V> {
        public final Set<Map.Entry<K, V>> filteredEntrySet;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class KeySet extends KeySet<K, V> {
            public KeySet() {
                super(FilteredEntryMap.this);
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object o) {
                if (!FilteredEntryMap.this.containsKey(o)) {
                    return false;
                }
                FilteredEntryMap.this.unfiltered.remove(o);
                return true;
            }

            @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                FilteredEntryMap filteredEntryMap = FilteredEntryMap.this;
                return FilteredEntryMap.removeAllKeys(filteredEntryMap.unfiltered, filteredEntryMap.predicate, collection);
            }

            @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                FilteredEntryMap filteredEntryMap = FilteredEntryMap.this;
                return FilteredEntryMap.retainAllKeys(filteredEntryMap.unfiltered, filteredEntryMap.predicate, collection);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return Lists.newArrayList(iterator()).toArray();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
            }
        }

        public FilteredEntryMap(Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
            this.filteredEntrySet = Sets.filter(unfiltered.entrySet(), this.predicate);
        }

        public static <K, V> boolean removeAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate, Collection<?> keyCollection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (entryPredicate.apply(next) && keyCollection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public static <K, V> boolean retainAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate, Collection<?> keyCollection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (entryPredicate.apply(next) && !keyCollection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new KeySet();
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
            public EntrySet() {
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(FilteredEntryMap.this.filteredEntrySet.iterator()) { // from class: com.google.common.collect.Maps.FilteredEntryMap.EntrySet.1
                    @Override // com.google.common.collect.TransformedIterator
                    public Map.Entry<K, V> transform(final Map.Entry<K, V> entry) {
                        return new ForwardingMapEntry<K, V>(this) { // from class: com.google.common.collect.Maps.FilteredEntryMap.EntrySet.1.1
                            public final /* synthetic */ AnonymousClass1 this$2;

                            {
                                this.this$2 = this;
                            }

                            @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                            @ParametricNullness
                            public V setValue(@ParametricNullness V v) {
                                Preconditions.checkArgument(FilteredEntryMap.this.apply(getKey(), v));
                                return (V) super.setValue(v);
                            }

                            @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                            public Map.Entry<K, V> delegate() {
                                return entry;
                            }
                        };
                    }
                };
            }

            public /* synthetic */ EntrySet(FilteredEntryMap filteredEntryMap, AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<Map.Entry<K, V>> delegate() {
                return FilteredEntryMap.this.filteredEntrySet;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class FilteredEntryNavigableMap<K, V> extends AbstractNavigableMap<K, V> {
        public final Predicate<? super Map.Entry<K, V>> entryPredicate;
        public final Map<K, V> filteredDelegate;
        public final NavigableMap<K, V> unfiltered;

        public FilteredEntryNavigableMap(NavigableMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            unfiltered.getClass();
            this.unfiltered = unfiltered;
            this.entryPredicate = entryPredicate;
            this.filteredDelegate = new FilteredEntryMap(unfiltered, entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.filteredDelegate.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.unfiltered.comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.filteredDelegate.containsKey(key);
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return Iterators.filter(this.unfiltered.descendingMap().entrySet().iterator(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.filterEntries((NavigableMap) this.unfiltered.descendingMap(), (Predicate) this.entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Iterators.filter(this.unfiltered.entrySet().iterator(), this.entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return this.filteredDelegate.entrySet();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            return this.filteredDelegate.get(key);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(@ParametricNullness K toKey, boolean inclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.headMap(toKey, inclusive), (Predicate) this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new NavigableKeySet<K, V>(this) { // from class: com.google.common.collect.Maps.FilteredEntryNavigableMap.1
                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMap.removeAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMap.retainAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }
            };
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.descendingMap().entrySet(), this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(@ParametricNullness K key, @ParametricNullness V value) {
            return this.filteredDelegate.put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> m) {
            this.filteredDelegate.putAll(m);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            return this.filteredDelegate.remove(key);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.filteredDelegate.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.subMap(fromKey, fromInclusive, toKey, toInclusive), (Predicate) this.entryPredicate);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.tailMap(fromKey, inclusive), (Predicate) this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            return new FilteredMapValues(this, this.unfiltered, this.entryPredicate);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FilteredEntrySortedMap<K, V> extends FilteredEntryMap<K, V> implements SortedMap<K, V> {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class SortedKeySet extends FilteredEntryMap<K, V>.KeySet implements SortedSet<K> {
            public SortedKeySet() {
                super();
            }

            @Override // java.util.SortedSet
            public Comparator<? super K> comparator() {
                return FilteredEntrySortedMap.this.sortedMap().comparator();
            }

            @Override // java.util.SortedSet
            @ParametricNullness
            public K first() {
                return (K) FilteredEntrySortedMap.this.firstKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> headSet(@ParametricNullness K toElement) {
                return ((FilteredEntrySortedMap) FilteredEntrySortedMap.this.headMap(toElement)).keySet();
            }

            @Override // java.util.SortedSet
            @ParametricNullness
            public K last() {
                return (K) FilteredEntrySortedMap.this.lastKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> subSet(@ParametricNullness K fromElement, @ParametricNullness K toElement) {
                return ((FilteredEntrySortedMap) FilteredEntrySortedMap.this.subMap(fromElement, toElement)).keySet();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> tailSet(@ParametricNullness K fromElement) {
                return ((FilteredEntrySortedMap) FilteredEntrySortedMap.this.tailMap(fromElement)).keySet();
            }
        }

        public FilteredEntrySortedMap(SortedMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K firstKey() {
            return keySet().iterator().next();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
            return new FilteredEntrySortedMap((Map) sortedMap().headMap(toKey), (Predicate) this.predicate);
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K lastKey() {
            SortedMap<K, V> sortedMap = sortedMap();
            while (true) {
                K kLastKey = sortedMap.lastKey();
                if (apply(kLastKey, this.unfiltered.get(kLastKey))) {
                    return kLastKey;
                }
                sortedMap = sortedMap().headMap(kLastKey);
            }
        }

        public SortedMap<K, V> sortedMap() {
            return (SortedMap) this.unfiltered;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
            return new FilteredEntrySortedMap((Map) sortedMap().subMap(fromKey, toKey), (Predicate) this.predicate);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(@ParametricNullness K fromKey) {
            return new FilteredEntrySortedMap((Map) sortedMap().tailMap(fromKey), (Predicate) this.predicate);
        }

        @Override // com.google.common.collect.Maps.FilteredEntryMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<K> createKeySet() {
            return new SortedKeySet();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<K> keySet() {
            return (SortedSet) super.keySet();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FilteredKeyMap<K, V> extends AbstractFilteredMap<K, V> {
        public final Predicate<? super K> keyPredicate;

        public FilteredKeyMap(Map<K, V> unfiltered, Predicate<? super K> keyPredicate, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
            this.keyPredicate = keyPredicate;
        }

        @Override // com.google.common.collect.Maps.AbstractFilteredMap, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.unfiltered.containsKey(key) && this.keyPredicate.apply(key);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), this.predicate);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FilteredMapValues<K, V> extends Values<K, V> {
        public final Predicate<? super Map.Entry<K, V>> predicate;
        public final Map<K, V> unfiltered;

        public FilteredMapValues(Map<K, V> filteredMap, Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
            super(filteredMap);
            this.unfiltered = unfiltered;
            this.predicate = predicate;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && Objects.equal(next.getValue(), o)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && !collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {

        /* JADX INFO: renamed from: com.google.common.collect.Maps$IteratorBasedAbstractMap$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends EntrySet<K, V> {
            public AnonymousClass1() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return IteratorBasedAbstractMap.this.entryIterator();
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public Map<K, V> map() {
                return IteratorBasedAbstractMap.this;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Iterators.clear(entryIterator());
        }

        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public abstract int size();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class KeySet<K, V> extends Sets.ImprovedAbstractSet<K> {

        @Weak
        public final Map<K, V> map;

        public KeySet(Map<K, V> map) {
            map.getClass();
            this.map = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return map().containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new AnonymousClass1(map().entrySet().iterator());
        }

        public Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!contains(o)) {
                return false;
            }
            map().remove(o);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MapDifferenceImpl<K, V> implements MapDifference<K, V> {
        public final Map<K, MapDifference.ValueDifference<V>> differences;
        public final Map<K, V> onBoth;
        public final Map<K, V> onlyOnLeft;
        public final Map<K, V> onlyOnRight;

        public MapDifferenceImpl(Map<K, V> onlyOnLeft, Map<K, V> onlyOnRight, Map<K, V> onBoth, Map<K, MapDifference.ValueDifference<V>> differences) {
            this.onlyOnLeft = Maps.unmodifiableMap(onlyOnLeft);
            this.onlyOnRight = Maps.unmodifiableMap(onlyOnRight);
            this.onBoth = Maps.unmodifiableMap(onBoth);
            this.differences = Maps.unmodifiableMap(differences);
        }

        @Override // com.google.common.collect.MapDifference
        public boolean areEqual() {
            return this.onlyOnLeft.isEmpty() && this.onlyOnRight.isEmpty() && this.differences.isEmpty();
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return this.differences;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesInCommon() {
            return this.onBoth;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnLeft() {
            return this.onlyOnLeft;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnRight() {
            return this.onlyOnRight;
        }

        @Override // com.google.common.collect.MapDifference
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof MapDifference) {
                MapDifference mapDifference = (MapDifference) object;
                if (entriesOnlyOnLeft().equals(mapDifference.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(mapDifference.entriesOnlyOnRight()) && entriesInCommon().equals(mapDifference.entriesInCommon()) && entriesDiffering().equals(mapDifference.entriesDiffering())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.MapDifference
        public int hashCode() {
            return Arrays.hashCode(new Object[]{entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering()});
        }

        public String toString() {
            if (areEqual()) {
                return "equal";
            }
            StringBuilder sb = new StringBuilder("not equal");
            if (!this.onlyOnLeft.isEmpty()) {
                sb.append(": only on left=");
                sb.append(this.onlyOnLeft);
            }
            if (!this.onlyOnRight.isEmpty()) {
                sb.append(": only on right=");
                sb.append(this.onlyOnRight);
            }
            if (!this.differences.isEmpty()) {
                sb.append(": value differences=");
                sb.append(this.differences);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static final class NavigableAsMapView<K, V> extends AbstractNavigableMap<K, V> {
        public final Function<? super K, V> function;
        public final NavigableSet<K> set;

        public NavigableAsMapView(NavigableSet<K> ks, Function<? super K, V> vFunction) {
            ks.getClass();
            this.set = ks;
            vFunction.getClass();
            this.function = vFunction;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.set.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.set.comparator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return new IteratorBasedAbstractMap.AnonymousClass1().iterator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return new NavigableAsMapView(this.set.descendingSet(), this.function);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Maps.asMapEntryIterator(this.set, this.function);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            if (Collections2.safeContains(this.set, key)) {
                return this.function.apply(key);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(@ParametricNullness K toKey, boolean inclusive) {
            return new NavigableAsMapView(this.set.headSet(toKey, inclusive), this.function);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new AnonymousClass6(this.set);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.set.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
            return new NavigableAsMapView(this.set.subSet(fromKey, fromInclusive, toKey, toInclusive), this.function);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
            return new NavigableAsMapView(this.set.tailSet(fromKey, inclusive), this.function);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class NavigableKeySet<K, V> extends SortedKeySet<K, V> implements NavigableSet<K> {
        public NavigableKeySet(NavigableMap<K, V> map) {
            super((Map) map);
        }

        @Override // java.util.NavigableSet
        public K ceiling(@ParametricNullness K e) {
            return map().ceilingKey(e);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return map().descendingKeySet();
        }

        @Override // java.util.NavigableSet
        public K floor(@ParametricNullness K e) {
            return map().floorKey(e);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(@ParametricNullness K toElement, boolean inclusive) {
            return map().headMap(toElement, inclusive).navigableKeySet();
        }

        @Override // java.util.NavigableSet
        public K higher(@ParametricNullness K e) {
            return map().higherKey(e);
        }

        @Override // java.util.NavigableSet
        public K lower(@ParametricNullness K e) {
            return map().lowerKey(e);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Maps.keyOrNull(map().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Maps.keyOrNull(map().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(@ParametricNullness K fromElement, boolean fromInclusive, @ParametricNullness K toElement, boolean toInclusive) {
            return map().subMap(fromElement, fromInclusive, toElement, toInclusive).navigableKeySet();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(@ParametricNullness K fromElement, boolean inclusive) {
            return map().tailMap(fromElement, inclusive).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(@ParametricNullness K toElement) {
            return headSet(toElement, false);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(@ParametricNullness K fromElement, @ParametricNullness K toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(@ParametricNullness K fromElement) {
            return tailSet(fromElement, true);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, com.google.common.collect.Maps.KeySet
        public NavigableMap<K, V> map() {
            return (NavigableMap) this.map;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SortedAsMapView<K, V> extends AsMapView<K, V> implements SortedMap<K, V> {
        public SortedAsMapView(SortedSet<K> set, Function<? super K, V> function) {
            super(set, function);
        }

        @Override // com.google.common.collect.Maps.AsMapView
        public SortedSet<K> backingSet() {
            return (SortedSet) this.set;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return backingSet().comparator();
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K firstKey() {
            return backingSet().first();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
            return new SortedAsMapView((Set) backingSet().headSet(toKey), (Function) this.function);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return new AnonymousClass5(backingSet());
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K lastKey() {
            return backingSet().last();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
            return new SortedAsMapView((Set) backingSet().subSet(fromKey, toKey), (Function) this.function);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(@ParametricNullness K fromKey) {
            return new SortedAsMapView((Set) backingSet().tailSet(fromKey), (Function) this.function);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SortedKeySet<K, V> extends KeySet<K, V> implements SortedSet<K> {
        public SortedKeySet(SortedMap<K, V> map) {
            super(map);
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return map().comparator();
        }

        @Override // java.util.SortedSet
        @ParametricNullness
        public K first() {
            return map().firstKey();
        }

        public SortedSet<K> headSet(@ParametricNullness K toElement) {
            return new SortedKeySet((Map) map().headMap(toElement));
        }

        @Override // java.util.SortedSet
        @ParametricNullness
        public K last() {
            return map().lastKey();
        }

        @Override // com.google.common.collect.Maps.KeySet
        public SortedMap<K, V> map() {
            return (SortedMap) this.map;
        }

        public SortedSet<K> subSet(@ParametricNullness K fromElement, @ParametricNullness K toElement) {
            return new SortedKeySet((Map) map().subMap(fromElement, toElement));
        }

        public SortedSet<K> tailSet(@ParametricNullness K fromElement) {
            return new SortedKeySet((Map) map().tailMap(fromElement));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SortedMapDifferenceImpl<K, V> extends MapDifferenceImpl<K, V> implements SortedMapDifference<K, V> {
        public SortedMapDifferenceImpl(SortedMap<K, V> onlyOnLeft, SortedMap<K, V> onlyOnRight, SortedMap<K, V> onBoth, SortedMap<K, MapDifference.ValueDifference<V>> differences) {
            super(onlyOnLeft, onlyOnRight, onBoth, differences);
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return (SortedMap) this.differences;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesInCommon() {
            return (SortedMap) this.onBoth;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnLeft() {
            return (SortedMap) this.onlyOnLeft;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnRight() {
            return (SortedMap) this.onlyOnRight;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformedEntriesMap<K, V1, V2> extends IteratorBasedAbstractMap<K, V2> {
        public final Map<K, V1> fromMap;
        public final EntryTransformer<? super K, ? super V1, V2> transformer;

        public TransformedEntriesMap(Map<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
            fromMap.getClass();
            this.fromMap = fromMap;
            transformer.getClass();
            this.transformer = transformer;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.fromMap.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.fromMap.containsKey(key);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V2>> entryIterator() {
            return new Iterators.AnonymousClass6(this.fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 get(Object key) {
            V1 v1 = this.fromMap.get(key);
            if (v1 != null || this.fromMap.containsKey(key)) {
                return this.transformer.transformEntry(key, v1);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.fromMap.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 remove(Object obj) {
            if (this.fromMap.containsKey(obj)) {
                return this.transformer.transformEntry(obj, this.fromMap.remove(obj));
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.fromMap.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V2> values() {
            return new Values(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class TransformedEntriesNavigableMap<K, V1, V2> extends TransformedEntriesSortedMap<K, V1, V2> implements NavigableMap<K, V2> {
        public TransformedEntriesNavigableMap(NavigableMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
            super((Map) fromMap, (EntryTransformer) transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> ceilingEntry(@ParametricNullness K key) {
            return transformEntry(fromMap().ceilingEntry(key));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(@ParametricNullness K key) {
            return fromMap().ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return fromMap().descendingKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> descendingMap() {
            return new TransformedEntriesNavigableMap((Map) fromMap().descendingMap(), (EntryTransformer) this.transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> firstEntry() {
            return transformEntry(fromMap().firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> floorEntry(@ParametricNullness K key) {
            return transformEntry(fromMap().floorEntry(key));
        }

        @Override // java.util.NavigableMap
        public K floorKey(@ParametricNullness K key) {
            return fromMap().floorKey(key);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap
        public NavigableMap<K, V1> fromMap() {
            return (NavigableMap) ((SortedMap) this.fromMap);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> higherEntry(@ParametricNullness K key) {
            return transformEntry(fromMap().higherEntry(key));
        }

        @Override // java.util.NavigableMap
        public K higherKey(@ParametricNullness K key) {
            return fromMap().higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lastEntry() {
            return transformEntry(fromMap().lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lowerEntry(@ParametricNullness K key) {
            return transformEntry(fromMap().lowerEntry(key));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(@ParametricNullness K key) {
            return fromMap().lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return fromMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollFirstEntry() {
            return transformEntry(fromMap().pollFirstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollLastEntry() {
            return transformEntry(fromMap().pollLastEntry());
        }

        public final Map.Entry<K, V2> transformEntry(Map.Entry<K, V1> entry) {
            if (entry == null) {
                return null;
            }
            return Maps.transformEntry(this.transformer, entry);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> headMap(@ParametricNullness K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
            return new TransformedEntriesNavigableMap((Map) fromMap().subMap(fromKey, fromInclusive, toKey, toInclusive), (EntryTransformer) this.transformer);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(@ParametricNullness K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> headMap(@ParametricNullness K toKey, boolean inclusive) {
            return new TransformedEntriesNavigableMap((Map) fromMap().headMap(toKey, inclusive), (EntryTransformer) this.transformer);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
            return new TransformedEntriesNavigableMap((Map) fromMap().tailMap(fromKey, inclusive), (EntryTransformer) this.transformer);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
            return subMap(fromKey, true, toKey, false);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformedEntriesSortedMap<K, V1, V2> extends TransformedEntriesMap<K, V1, V2> implements SortedMap<K, V2> {
        public TransformedEntriesSortedMap(SortedMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
            super(fromMap, transformer);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return fromMap().comparator();
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K firstKey() {
            return fromMap().firstKey();
        }

        public SortedMap<K, V1> fromMap() {
            return (SortedMap) this.fromMap;
        }

        public SortedMap<K, V2> headMap(@ParametricNullness K toKey) {
            return new TransformedEntriesSortedMap((Map) fromMap().headMap(toKey), (EntryTransformer) this.transformer);
        }

        @Override // java.util.SortedMap
        @ParametricNullness
        public K lastKey() {
            return fromMap().lastKey();
        }

        public SortedMap<K, V2> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
            return new TransformedEntriesSortedMap((Map) fromMap().subMap(fromKey, toKey), (EntryTransformer) this.transformer);
        }

        public SortedMap<K, V2> tailMap(@ParametricNullness K fromKey) {
            return new TransformedEntriesSortedMap((Map) fromMap().tailMap(fromKey), (EntryTransformer) this.transformer);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final BiMap<? extends K, ? extends V> delegate;

        @RetainedWith
        @LazyInit
        public BiMap<V, K> inverse;
        public final Map<K, V> unmodifiableMap;

        @LazyInit
        public transient Set<V> values;

        public UnmodifiableBiMap(BiMap<? extends K, ? extends V> delegate, BiMap<V, K> inverse) {
            this.unmodifiableMap = DesugarCollections.unmodifiableMap(delegate);
            this.delegate = delegate;
            this.inverse = inverse;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(@ParametricNullness K key, @ParametricNullness V value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap = this.inverse;
            if (biMap != null) {
                return biMap;
            }
            UnmodifiableBiMap unmodifiableBiMap = new UnmodifiableBiMap(this.delegate.inverse(), this);
            this.inverse = unmodifiableBiMap;
            return unmodifiableBiMap;
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<K, V> delegate() {
            return this.unmodifiableMap;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
        public Set<V> values() {
            Set<V> set = this.values;
            if (set != null) {
                return set;
            }
            Set<V> setUnmodifiableSet = DesugarCollections.unmodifiableSet(this.delegate.values());
            this.values = setUnmodifiableSet;
            return setUnmodifiableSet;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        public final Collection<Map.Entry<K, V>> entries;

        public UnmodifiableEntries(Collection<Map.Entry<K, V>> entries) {
            this.entries = entries;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new AnonymousClass8(this.entries.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableEntrySet<K, V> extends UnmodifiableEntries<K, V> implements Set<Map.Entry<K, V>> {
        public UnmodifiableEntrySet(Set<Map.Entry<K, V>> entries) {
            super(entries);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object object) {
            return Sets.equalsImpl(this, object);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class UnmodifiableNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        public final NavigableMap<K, ? extends V> delegate;

        @LazyInit
        public transient UnmodifiableNavigableMap<K, V> descendingMap;

        public UnmodifiableNavigableMap(NavigableMap<K, ? extends V> delegate) {
            this.delegate = delegate;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(@ParametricNullness K key) {
            return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(key));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(@ParametricNullness K key) {
            return this.delegate.ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap = this.descendingMap;
            if (unmodifiableNavigableMap != null) {
                return unmodifiableNavigableMap;
            }
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap2 = new UnmodifiableNavigableMap<>(this.delegate.descendingMap(), this);
            this.descendingMap = unmodifiableNavigableMap2;
            return unmodifiableNavigableMap2;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return Maps.unmodifiableOrNull(this.delegate.firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(@ParametricNullness K key) {
            return Maps.unmodifiableOrNull(this.delegate.floorEntry(key));
        }

        @Override // java.util.NavigableMap
        public K floorKey(@ParametricNullness K key) {
            return this.delegate.floorKey(key);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> headMap(@ParametricNullness K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(@ParametricNullness K key) {
            return Maps.unmodifiableOrNull(this.delegate.higherEntry(key));
        }

        @Override // java.util.NavigableMap
        public K higherKey(@ParametricNullness K key) {
            return this.delegate.higherKey(key);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return Maps.unmodifiableOrNull(this.delegate.lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(@ParametricNullness K key) {
            return Maps.unmodifiableOrNull(this.delegate.lowerEntry(key));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(@ParametricNullness K key) {
            return this.delegate.lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> subMap(@ParametricNullness K fromKey, @ParametricNullness K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(@ParametricNullness K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(@ParametricNullness K toKey, boolean inclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.headMap(toKey, inclusive));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(@ParametricNullness K fromKey, boolean fromInclusive, @ParametricNullness K toKey, boolean toInclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.subMap(fromKey, fromInclusive, toKey, toInclusive));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(@ParametricNullness K fromKey, boolean inclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.tailMap(fromKey, inclusive));
        }

        public UnmodifiableNavigableMap(NavigableMap<K, ? extends V> delegate, UnmodifiableNavigableMap<K, V> descendingMap) {
            this.delegate = delegate;
            this.descendingMap = descendingMap;
        }

        @Override // com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public SortedMap<K, V> delegate() {
            return DesugarCollections.unmodifiableSortedMap(this.delegate);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ValueDifferenceImpl<V> implements MapDifference.ValueDifference<V> {

        @ParametricNullness
        public final V left;

        @ParametricNullness
        public final V right;

        public ValueDifferenceImpl(@ParametricNullness V left, @ParametricNullness V right) {
            this.left = left;
            this.right = right;
        }

        public static <V> MapDifference.ValueDifference<V> create(@ParametricNullness V left, @ParametricNullness V right) {
            return new ValueDifferenceImpl(left, right);
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public boolean equals(Object object) {
            if (!(object instanceof MapDifference.ValueDifference)) {
                return false;
            }
            MapDifference.ValueDifference valueDifference = (MapDifference.ValueDifference) object;
            return Objects.equal(this.left, valueDifference.leftValue()) && Objects.equal(this.right, valueDifference.rightValue());
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.left, this.right});
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        @ParametricNullness
        public V leftValue() {
            return this.left;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        @ParametricNullness
        public V rightValue() {
            return this.right;
        }

        public String toString() {
            return "(" + this.left + ", " + this.right + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Values<K, V> extends AbstractCollection<V> {

        @Weak
        public final Map<K, V> map;

        public Values(Map<K, V> map) {
            map.getClass();
            this.map = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return this.map.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new AnonymousClass2(this.map.entrySet().iterator());
        }

        public final Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            try {
                return super.remove(o);
            } catch (UnsupportedOperationException unused) {
                for (Map.Entry<K, V> entry : this.map.entrySet()) {
                    if (Objects.equal(o, entry.getValue())) {
                        this.map.remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            try {
                c.getClass();
                return super.removeAll(c);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry<K, V> entry : this.map.entrySet()) {
                    if (c.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return this.map.keySet().removeAll(hashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            try {
                c.getClass();
                return super.retainAll(c);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry<K, V> entry : this.map.entrySet()) {
                    if (c.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return this.map.keySet().retainAll(hashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.map.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static abstract class ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {

        @LazyInit
        public transient Set<Map.Entry<K, V>> entrySet;

        @LazyInit
        public transient Set<K> keySet;

        @LazyInit
        public transient Collection<V> values;

        public abstract Set<Map.Entry<K, V>> createEntrySet();

        public Set<K> createKeySet() {
            return new KeySet(this);
        }

        public Collection<V> createValues() {
            return new Values(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
            this.entrySet = setCreateEntrySet;
            return setCreateEntrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> setCreateKeySet = createKeySet();
            this.keySet = setCreateKeySet;
            return setCreateKeySet;
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> collectionCreateValues = createValues();
            this.values = collectionCreateValues;
            return collectionCreateValues;
        }
    }

    public static Set access$200(Set set) {
        return new AnonymousClass4(set);
    }

    public static SortedSet access$300(SortedSet sortedSet) {
        return new AnonymousClass5(sortedSet);
    }

    public static NavigableSet access$400(NavigableSet navigableSet) {
        return new AnonymousClass6(navigableSet);
    }

    public static <A, B> Converter<A, B> asConverter(BiMap<A, B> bimap) {
        return new BiMapConverter(bimap);
    }

    public static <K, V1, V2> Function<Map.Entry<K, V1>, Map.Entry<K, V2>> asEntryToEntryFunction(final EntryTransformer<? super K, ? super V1, V2> transformer) {
        transformer.getClass();
        return new Function() { // from class: com.google.common.collect.Maps$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return Maps.transformEntry(transformer, (Map.Entry) obj);
            }
        };
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new AsMapView(set, function);
    }

    public static <K, V> Iterator<Map.Entry<K, V>> asMapEntryIterator(Set<K> set, Function<? super K, V> function) {
        return new AnonymousClass3(set.iterator(), function);
    }

    public static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
            return expectedSize + 1;
        }
        if (expectedSize < 1073741824) {
            return (int) Math.ceil(((double) expectedSize) / 0.75d);
        }
        return Integer.MAX_VALUE;
    }

    public static <K, V> boolean containsEntryImpl(Collection<Map.Entry<K, V>> c, Object o) {
        if (o instanceof Map.Entry) {
            return c.contains(new AnonymousClass7((Map.Entry) o));
        }
        return false;
    }

    public static boolean containsKeyImpl(Map<?, ?> map, Object key) {
        return Iterators.contains(new AnonymousClass1(map.entrySet().iterator()), key);
    }

    public static boolean containsValueImpl(Map<?, ?> map, Object value) {
        return Iterators.contains(new AnonymousClass2(map.entrySet().iterator()), value);
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right) {
        return left instanceof SortedMap ? difference((SortedMap) left, (Map) right) : difference(left, right, Equivalence.Equals.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void doDifference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence, Map<K, V> map3, Map<K, V> map4, Map<K, V> map5, Map<K, MapDifference.ValueDifference<V>> map6) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (map2.containsKey(key)) {
                V vRemove = map4.remove(key);
                if (equivalence.equivalent(value, vRemove)) {
                    map5.put(key, value);
                } else {
                    map6.put(key, new ValueDifferenceImpl(value, vRemove));
                }
            } else {
                map3.put(key, value);
            }
        }
    }

    public static boolean equalsImpl(Map<?, ?> map, Object object) {
        if (map == object) {
            return true;
        }
        if (object instanceof Map) {
            return map.entrySet().equals(((Map) object).entrySet());
        }
        return false;
    }

    public static <K, V> BiMap<K, V> filterEntries(BiMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        unfiltered.getClass();
        entryPredicate.getClass();
        return unfiltered instanceof FilteredEntryBiMap ? filterFiltered((FilteredEntryBiMap) unfiltered, (Predicate) entryPredicate) : new FilteredEntryBiMap(unfiltered, entryPredicate);
    }

    public static <K, V> Map<K, V> filterFiltered(AbstractFilteredMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryMap(map.unfiltered, Predicates.and(map.predicate, entryPredicate));
    }

    public static <K, V> BiMap<K, V> filterKeys(BiMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        keyPredicate.getClass();
        return filterEntries((BiMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static ImmutableMap<String, String> fromProperties(Properties properties) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            Object objNextElement = enumerationPropertyNames.nextElement();
            j$.util.Objects.requireNonNull(objNextElement);
            String str = (String) objNextElement;
            String property = properties.getProperty(str);
            j$.util.Objects.requireNonNull(property);
            builder.put(str, property);
        }
        return builder.build(true);
    }

    @GwtCompatible(serializable = true)
    public static <K, V> Map.Entry<K, V> immutableEntry(@ParametricNullness K key, @ParametricNullness V value) {
        return new ImmutableEntry(key, value);
    }

    @GwtCompatible(serializable = true)
    public static <K extends Enum<K>, V> ImmutableMap<K, V> immutableEnumMap(Map<K, ? extends V> map) {
        if (map instanceof ImmutableEnumMap) {
            return (ImmutableEnumMap) map;
        }
        Iterator<Map.Entry<K, ? extends V>> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
        }
        Map.Entry<K, ? extends V> next = it.next();
        K key = next.getKey();
        V value = next.getValue();
        CollectPreconditions.checkEntryNotNull(key, value);
        EnumMap enumMap = new EnumMap(Collections.singletonMap(key, value));
        while (it.hasNext()) {
            Map.Entry<K, ? extends V> next2 = it.next();
            K key2 = next2.getKey();
            V value2 = next2.getValue();
            CollectPreconditions.checkEntryNotNull(key2, value2);
            enumMap.put((Enum) key2, (Object) value2);
        }
        return ImmutableEnumMap.asImmutable(enumMap);
    }

    public static <E> ImmutableMap<E, Integer> indexMap(Collection<E> list) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder(list.size());
        Iterator<E> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            builder.put(it.next(), Integer.valueOf(i));
            i++;
        }
        return builder.build(true);
    }

    public static <K> Function<Map.Entry<K, ?>, K> keyFunction() {
        return EntryFunction.KEY;
    }

    public static <K, V> Iterator<K> keyIterator(Iterator<Map.Entry<K, V>> entryIterator) {
        return new AnonymousClass1(entryIterator);
    }

    public static <K> K keyOrNull(Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public static <K> Predicate<Map.Entry<K, ?>> keyPredicateOnEntries(Predicate<? super K> keyPredicate) {
        return new Predicates.CompositionPredicate(keyPredicate, EntryFunction.KEY);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> type) {
        type.getClass();
        return new EnumMap<>(type);
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(capacity(expectedSize));
    }

    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return new LinkedHashMap<>(capacity(expectedSize));
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <E> Comparator<? super E> orNaturalOrder(Comparator<? super E> comparator) {
        return comparator != null ? comparator : NaturalOrdering.INSTANCE;
    }

    public static <K, V> void putAllImpl(Map<K, V> self, Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            self.put(entry.getKey(), entry.getValue());
        }
    }

    public static <K, V> boolean removeEntryImpl(Collection<Map.Entry<K, V>> c, Object o) {
        if (o instanceof Map.Entry) {
            return c.remove(new AnonymousClass7((Map.Entry) o));
        }
        return false;
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> removeOnlyNavigableSet(NavigableSet<E> set) {
        return new AnonymousClass6(set);
    }

    public static <E> Set<E> removeOnlySet(Set<E> set) {
        return new AnonymousClass4(set);
    }

    public static <E> SortedSet<E> removeOnlySortedSet(SortedSet<E> set) {
        return new AnonymousClass5(set);
    }

    public static boolean safeContainsKey(Map<?, ?> map, Object key) {
        map.getClass();
        try {
            return map.containsKey(key);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <V> V safeGet(Map<?, V> map, Object key) {
        map.getClass();
        try {
            return map.get(key);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static <V> V safeRemove(Map<?, V> map, Object key) {
        map.getClass();
        try {
            return map.remove(key);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K extends Comparable<? super K>, V> NavigableMap<K, V> subMap(NavigableMap<K, V> map, Range<K> range) {
        if (map.comparator() != null && map.comparator() != NaturalOrdering.INSTANCE && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(map.comparator().compare(range.lowerBound.endpoint(), range.upperBound.endpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            Comparable comparableEndpoint = range.lowerBound.endpoint();
            BoundType boundTypeTypeAsLowerBound = range.lowerBound.typeAsLowerBound();
            BoundType boundType = BoundType.CLOSED;
            return map.subMap(comparableEndpoint, boundTypeTypeAsLowerBound == boundType, range.upperBound.endpoint(), range.upperBound.typeAsUpperBound() == boundType);
        }
        if (range.hasLowerBound()) {
            return map.tailMap(range.lowerBound.endpoint(), range.lowerBound.typeAsLowerBound() == BoundType.CLOSED);
        }
        if (range.hasUpperBound()) {
            return map.headMap(range.upperBound.endpoint(), range.upperBound.typeAsUpperBound() == BoundType.CLOSED);
        }
        return map;
    }

    @J2ktIncompatible
    public static <K, V> BiMap<K, V> synchronizedBiMap(BiMap<K, V> bimap) {
        return Synchronized.biMap(bimap, null);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> navigableMap) {
        return Synchronized.navigableMap(navigableMap);
    }

    @IgnoreJRERequirement
    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends V> valueFunction) {
        return CollectCollectors.toImmutableEnumMap(keyFunction, valueFunction);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterator<K> keys, Function<? super K, V> valueFunction) {
        valueFunction.getClass();
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (keys.hasNext()) {
            K next = keys.next();
            builder.put(next, valueFunction.apply(next));
        }
        return builder.build(false);
    }

    public static String toStringImpl(Map<?, ?> map) {
        StringBuilder sbNewStringBuilderForCollection = Collections2.newStringBuilderForCollection(map.size());
        sbNewStringBuilderForCollection.append('{');
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!z) {
                sbNewStringBuilderForCollection.append(", ");
            }
            sbNewStringBuilderForCollection.append(entry.getKey());
            sbNewStringBuilderForCollection.append('=');
            sbNewStringBuilderForCollection.append(entry.getValue());
            z = false;
        }
        sbNewStringBuilderForCollection.append('}');
        return sbNewStringBuilderForCollection.toString();
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesMap(fromMap, transformer);
    }

    public static <V2, K, V1> Map.Entry<K, V2> transformEntry(final EntryTransformer<? super K, ? super V1, V2> transformer, final Map.Entry<K, V1> entry) {
        transformer.getClass();
        entry.getClass();
        return new AbstractMapEntry<K, V2>() { // from class: com.google.common.collect.Maps.9
            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            @ParametricNullness
            public K getKey() {
                return (K) entry.getKey();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            @ParametricNullness
            public V2 getValue() {
                return (V2) transformer.transformEntry(entry.getKey(), entry.getValue());
            }
        };
    }

    public static <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> fromMap, final Function<? super V1, V2> function) {
        function.getClass();
        return new TransformedEntriesMap(fromMap, new EntryTransformer() { // from class: com.google.common.collect.Maps$$ExternalSyntheticLambda3
            @Override // com.google.common.collect.Maps.EntryTransformer
            public final Object transformEntry(Object obj, Object obj2) {
                return function.apply(obj2);
            }
        });
    }

    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> values, Function<? super V, K> keyFunction, ImmutableMap.Builder<K, V> builder) {
        keyFunction.getClass();
        while (values.hasNext()) {
            V next = values.next();
            builder.put(keyFunction.apply(next), next);
        }
        try {
            return builder.buildOrThrow();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + ". To index multiple values under a key, use Multimaps.index.");
        }
    }

    public static <K, V> BiMap<K, V> unmodifiableBiMap(BiMap<? extends K, ? extends V> bimap) {
        return new UnmodifiableBiMap(bimap, null);
    }

    public static <K, V> Map.Entry<K, V> unmodifiableEntry(Map.Entry<? extends K, ? extends V> entry) {
        entry.getClass();
        return new AnonymousClass7(entry);
    }

    public static <K, V> UnmodifiableIterator<Map.Entry<K, V>> unmodifiableEntryIterator(Iterator<Map.Entry<K, V>> entryIterator) {
        return new AnonymousClass8(entryIterator);
    }

    public static <K, V> Set<Map.Entry<K, V>> unmodifiableEntrySet(Set<Map.Entry<K, V>> entrySet) {
        return new UnmodifiableEntrySet((Collection) DesugarCollections.unmodifiableSet(entrySet));
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<K, ? extends V> map) {
        return map instanceof SortedMap ? DesugarCollections.unmodifiableSortedMap((SortedMap) map) : DesugarCollections.unmodifiableMap(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> map) {
        map.getClass();
        return map instanceof UnmodifiableNavigableMap ? map : new UnmodifiableNavigableMap(map);
    }

    public static <K, V> Map.Entry<K, V> unmodifiableOrNull(Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return new AnonymousClass7(entry);
    }

    public static <V> Function<Map.Entry<?, V>, V> valueFunction() {
        return EntryFunction.VALUE;
    }

    public static <K, V> Iterator<V> valueIterator(Iterator<Map.Entry<K, V>> entryIterator) {
        return new AnonymousClass2(entryIterator);
    }

    public static <V> V valueOrNull(Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public static <V> Predicate<Map.Entry<?, V>> valuePredicateOnEntries(Predicate<? super V> valuePredicate) {
        return new Predicates.CompositionPredicate(valuePredicate, EntryFunction.VALUE);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4<E> extends ForwardingSet<E> {
        public final /* synthetic */ Set val$set;

        public AnonymousClass4(final Set val$set) {
            this.val$set = val$set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(@ParametricNullness E element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> es) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<E> delegate() {
            return this.val$set;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass6<E> extends ForwardingNavigableSet<E> {
        public final /* synthetic */ NavigableSet val$set;

        public AnonymousClass6(final NavigableSet val$set) {
            this.val$set = val$set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(@ParametricNullness E element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> es) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new AnonymousClass6(super.descendingSet());
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return new AnonymousClass5(super.headSet(toElement));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return new AnonymousClass5(super.subSet(fromElement, toElement));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return new AnonymousClass5(super.tailSet(fromElement));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return new AnonymousClass6(super.headSet(toElement, inclusive));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return new AnonymousClass6(super.subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return new AnonymousClass6(super.tailSet(fromElement, inclusive));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.val$set;
        }
    }

    public static <K, V> SortedMap<K, V> asMap(SortedSet<K> set, Function<? super K, V> function) {
        return new SortedAsMapView((Set) set, (Function) function);
    }

    public static <K, V> SortedMap<K, V> filterValues(SortedMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((SortedMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
        return new TreeMap<>((SortedMap) map);
    }

    @IgnoreJRERequirement
    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        return CollectCollectors.toImmutableEnumMap(keyFunction, valueFunction, mergeFunction);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformEntries(SortedMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesSortedMap((Map) fromMap, (EntryTransformer) transformer);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5<E> extends ForwardingSortedSet<E> {
        public final /* synthetic */ SortedSet val$set;

        public AnonymousClass5(final SortedSet val$set) {
            this.val$set = val$set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(@ParametricNullness E element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> es) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return new AnonymousClass5(super.headSet(toElement));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return new AnonymousClass5(super.subSet(fromElement, toElement));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return new AnonymousClass5(super.tailSet(fromElement));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.val$set;
        }
    }

    public static <K, V> SortedMap<K, V> filterFiltered(FilteredEntrySortedMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntrySortedMap((Map) map.sortedMap(), Predicates.and(map.predicate, entryPredicate));
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        keyPredicate.getClass();
        Predicate predicateKeyPredicateOnEntries = keyPredicateOnEntries(keyPredicate);
        if (unfiltered instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) unfiltered, predicateKeyPredicateOnEntries);
        }
        unfiltered.getClass();
        return new FilteredKeyMap(unfiltered, keyPredicate, predicateKeyPredicateOnEntries);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterValues(NavigableMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((NavigableMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(Comparator<C> comparator) {
        return new TreeMap<>(comparator);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> asMap(NavigableSet<K> set, Function<? super K, V> function) {
        return new NavigableAsMapView(set, function);
    }

    public static <K, V> BiMap<K, V> filterValues(BiMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((BiMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformEntries(NavigableMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesNavigableMap((Map) fromMap, (EntryTransformer) transformer);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformValues(NavigableMap<K, V1> fromMap, final Function<? super V1, V2> function) {
        function.getClass();
        return new TransformedEntriesNavigableMap((Map) fromMap, new EntryTransformer() { // from class: com.google.common.collect.Maps$$ExternalSyntheticLambda2
            @Override // com.google.common.collect.Maps.EntryTransformer
            public final Object transformEntry(Object obj, Object obj2) {
                return function.apply(obj2);
            }
        });
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right, Equivalence<? super V> valueEquivalence) {
        valueEquivalence.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(right);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        doDifference(left, right, valueEquivalence, linkedHashMap, linkedHashMap2, linkedHashMap3, linkedHashMap4);
        return new MapDifferenceImpl(linkedHashMap, linkedHashMap2, linkedHashMap3, linkedHashMap4);
    }

    public static <K, V> Map<K, V> filterEntries(Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        entryPredicate.getClass();
        if (unfiltered instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) unfiltered, entryPredicate);
        }
        unfiltered.getClass();
        return new FilteredEntryMap(unfiltered, entryPredicate);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterFiltered(FilteredEntryNavigableMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryNavigableMap(map.unfiltered, Predicates.and(map.entryPredicate, entryPredicate));
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterable<K> keys, Function<? super K, V> valueFunction) {
        return toMap(keys.iterator(), valueFunction);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformValues(SortedMap<K, V1> fromMap, final Function<? super V1, V2> function) {
        function.getClass();
        return new TransformedEntriesSortedMap((Map) fromMap, new EntryTransformer() { // from class: com.google.common.collect.Maps$$ExternalSyntheticLambda1
            @Override // com.google.common.collect.Maps.EntryTransformer
            public final Object transformEntry(Object obj, Object obj2) {
                return function.apply(obj2);
            }
        });
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterable<V> values, Function<? super V, K> keyFunction) {
        if (values instanceof Collection) {
            return uniqueIndex(values.iterator(), keyFunction, ImmutableMap.builderWithExpectedSize(((Collection) values).size()));
        }
        return uniqueIndex(values.iterator(), keyFunction);
    }

    public static <K, V> BiMap<K, V> filterFiltered(FilteredEntryBiMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryBiMap((BiMap) map.unfiltered, Predicates.and(map.predicate, entryPredicate));
    }

    public static <K, V> SortedMap<K, V> filterKeys(SortedMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        return filterEntries((SortedMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterKeys(NavigableMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        return filterEntries((NavigableMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterEntries(NavigableMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        entryPredicate.getClass();
        if (unfiltered instanceof FilteredEntryNavigableMap) {
            return filterFiltered((FilteredEntryNavigableMap) unfiltered, entryPredicate);
        }
        unfiltered.getClass();
        return new FilteredEntryNavigableMap(unfiltered, entryPredicate);
    }

    public static <K, V> SortedMapDifference<K, V> difference(SortedMap<K, ? extends V> left, Map<? extends K, ? extends V> right) {
        left.getClass();
        right.getClass();
        Comparator<? super K> comparator = left.comparator();
        if (comparator == null) {
            comparator = NaturalOrdering.INSTANCE;
        }
        TreeMap treeMap = new TreeMap(comparator);
        TreeMap treeMap2 = new TreeMap(comparator);
        treeMap2.putAll(right);
        TreeMap treeMap3 = new TreeMap(comparator);
        TreeMap treeMap4 = new TreeMap(comparator);
        doDifference(left, right, Equivalence.Equals.INSTANCE, treeMap, treeMap2, treeMap3, treeMap4);
        return new SortedMapDifferenceImpl((Map) treeMap, (Map) treeMap2, (Map) treeMap3, (Map) treeMap4);
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> values, Function<? super V, K> keyFunction) {
        return uniqueIndex(values, keyFunction, ImmutableMap.builder());
    }

    public static <K, V> SortedMap<K, V> filterEntries(SortedMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        entryPredicate.getClass();
        if (unfiltered instanceof FilteredEntrySortedMap) {
            return filterFiltered((FilteredEntrySortedMap) unfiltered, (Predicate) entryPredicate);
        }
        unfiltered.getClass();
        return new FilteredEntrySortedMap((Map) unfiltered, (Predicate) entryPredicate);
    }
}
