package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import j$.util.Map;
import j$.util.Objects;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ImmutableMap.of or another implementation")
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable, j$.util.Map {
    public static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 912559;

    @RetainedWith
    @LazyInit
    public transient ImmutableSet<Map.Entry<K, V>> entrySet;

    @RetainedWith
    @LazyInit
    public transient ImmutableSet<K> keySet;

    @LazyInit
    public transient ImmutableSetMultimap<K, V> multimapView;

    @RetainedWith
    @LazyInit
    public transient ImmutableCollection<V> values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DoNotMock
    public static class Builder<K, V> {
        public Object[] alternatingKeysAndValues;
        public DuplicateKey duplicateKey;
        public boolean entriesUsed;
        public int size;
        public Comparator<? super V> valueComparator;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class DuplicateKey {
            public final Object key;
            public final Object value1;
            public final Object value2;

            public DuplicateKey(Object key, Object value1, Object value2) {
                this.key = key;
                this.value1 = value1;
                this.value2 = value2;
            }

            public IllegalArgumentException exception() {
                return new IllegalArgumentException("Multiple entries with same key: " + this.key + "=" + this.value1 + " and " + this.key + "=" + this.value2);
            }
        }

        public Builder() {
            this(4);
        }

        private void ensureCapacity(int minCapacity) {
            int i = minCapacity * 2;
            Object[] objArr = this.alternatingKeysAndValues;
            if (i > objArr.length) {
                this.alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i));
                this.entriesUsed = false;
            }
        }

        public static <V> void sortEntries(Object[] alternatingKeysAndValues, int size, Comparator<? super V> valueComparator) {
            Map.Entry[] entryArr = new Map.Entry[size];
            for (int i = 0; i < size; i++) {
                int i2 = i * 2;
                Object obj = alternatingKeysAndValues[i2];
                Objects.requireNonNull(obj);
                Object obj2 = alternatingKeysAndValues[i2 + 1];
                Objects.requireNonNull(obj2);
                entryArr[i] = new AbstractMap.SimpleImmutableEntry(obj, obj2);
            }
            Arrays.sort(entryArr, 0, size, Ordering.from(valueComparator).onResultOf(Maps.EntryFunction.VALUE));
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = i3 * 2;
                alternatingKeysAndValues[i4] = entryArr[i3].getKey();
                alternatingKeysAndValues[i4 + 1] = entryArr[i3].getValue();
            }
        }

        public final ImmutableMap<K, V> build(boolean throwIfDuplicateKeys) {
            Object[] objArrLastEntryForEachKey;
            DuplicateKey duplicateKey;
            DuplicateKey duplicateKey2;
            if (throwIfDuplicateKeys && (duplicateKey2 = this.duplicateKey) != null) {
                throw duplicateKey2.exception();
            }
            int length = this.size;
            if (this.valueComparator == null) {
                objArrLastEntryForEachKey = this.alternatingKeysAndValues;
            } else {
                if (this.entriesUsed) {
                    this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, length * 2);
                }
                objArrLastEntryForEachKey = this.alternatingKeysAndValues;
                if (!throwIfDuplicateKeys) {
                    objArrLastEntryForEachKey = lastEntryForEachKey(objArrLastEntryForEachKey, this.size);
                    if (objArrLastEntryForEachKey.length < this.alternatingKeysAndValues.length) {
                        length = objArrLastEntryForEachKey.length >>> 1;
                    }
                }
                sortEntries(objArrLastEntryForEachKey, length, this.valueComparator);
            }
            this.entriesUsed = true;
            RegularImmutableMap regularImmutableMapCreate = RegularImmutableMap.create(length, objArrLastEntryForEachKey, this);
            if (!throwIfDuplicateKeys || (duplicateKey = this.duplicateKey) == null) {
                return regularImmutableMapCreate;
            }
            throw duplicateKey.exception();
        }

        public ImmutableMap<K, V> buildKeepingLast() {
            return build(false);
        }

        public ImmutableMap<K, V> buildOrThrow() {
            return build(true);
        }

        @CanIgnoreReturnValue
        public Builder<K, V> combine(Builder<K, V> other) {
            other.getClass();
            ensureCapacity(this.size + other.size);
            System.arraycopy(other.alternatingKeysAndValues, 0, this.alternatingKeysAndValues, this.size * 2, other.size * 2);
            this.size += other.size;
            return this;
        }

        public final Object[] lastEntryForEachKey(Object[] localAlternatingKeysAndValues, int size) {
            HashSet hashSet = new HashSet();
            BitSet bitSet = new BitSet();
            for (int i = size - 1; i >= 0; i--) {
                Object obj = localAlternatingKeysAndValues[i * 2];
                Objects.requireNonNull(obj);
                if (!hashSet.add(obj)) {
                    bitSet.set(i);
                }
            }
            if (bitSet.isEmpty()) {
                return localAlternatingKeysAndValues;
            }
            Object[] objArr = new Object[(size - bitSet.cardinality()) * 2];
            int i2 = 0;
            int i3 = 0;
            while (i2 < size * 2) {
                if (bitSet.get(i2 >>> 1)) {
                    i2 += 2;
                } else {
                    int i4 = i3 + 1;
                    int i5 = i2 + 1;
                    Object obj2 = localAlternatingKeysAndValues[i2];
                    Objects.requireNonNull(obj2);
                    objArr[i3] = obj2;
                    i3 += 2;
                    i2 += 2;
                    Object obj3 = localAlternatingKeysAndValues[i5];
                    Objects.requireNonNull(obj3);
                    objArr[i4] = obj3;
                }
            }
            return objArr;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> valueComparator) {
            Preconditions.checkState(this.valueComparator == null, "valueComparator was already set");
            Preconditions.checkNotNull(valueComparator, "valueComparator");
            this.valueComparator = valueComparator;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K key, V value) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(key, value);
            Object[] objArr = this.alternatingKeysAndValues;
            int i = this.size;
            objArr[i * 2] = key;
            objArr[(i * 2) + 1] = value;
            this.size = i + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            return putAll(map.entrySet());
        }

        public Builder(int initialCapacity) {
            this.alternatingKeysAndValues = new Object[initialCapacity * 2];
            this.size = 0;
            this.entriesUsed = false;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
            if (entries instanceof Collection) {
                ensureCapacity(((Collection) entries).size() + this.size);
            }
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it = entries.iterator();
            while (it.hasNext()) {
                put(it.next());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public ImmutableMap<K, V> build() {
            return buildOrThrow();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
            return new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap.1EntrySetImpl
                @Override // com.google.common.collect.ImmutableMapEntrySet
                public ImmutableMap<K, V> map() {
                    return IteratorBasedImmutableMap.this;
                }

                @Override // com.google.common.collect.ImmutableMapEntrySet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
                @J2ktIncompatible
                @GwtIncompatible
                public Object writeReplace() {
                    return super.writeReplace();
                }

                @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
                public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedImmutableMap.this.entryIterator();
                }
            };
        }

        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return new ImmutableMapKeySet(this);
        }

        @Override // com.google.common.collect.ImmutableMap
        public ImmutableCollection<V> createValues() {
            return new ImmutableMapValues(this);
        }

        public abstract UnmodifiableIterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set entrySet() {
            return super.entrySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return super.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.BiMap
        public /* bridge */ /* synthetic */ Collection values() {
            return super.values();
        }

        @Override // com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new SerializedForm(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        public MapViewOfValuesAsSingletonSets() {
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public boolean containsKey(Object key) {
            return ImmutableMap.this.containsKey(key);
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return ImmutableMap.this.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        public UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> entryIterator() {
            final UnmodifiableIterator<Map.Entry<K, V>> it = ImmutableMap.this.entrySet().iterator();
            return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1
                public final /* synthetic */ MapViewOfValuesAsSingletonSets this$1;

                {
                    this.this$1 = this;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<K, ImmutableSet<V>> next() {
                    final Map.Entry entry = (Map.Entry) it.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1.1
                        public final /* synthetic */ AnonymousClass1 this$2;

                        {
                            this.this$2 = this;
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public K getKey() {
                            return (K) entry.getKey();
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public ImmutableSet<V> getValue() {
                            return ImmutableSet.of(entry.getValue());
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isHashCodeFast() {
            return ImmutableMap.this.isHashCodeFast();
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return ImmutableMap.this.isPartialView();
        }

        @Override // java.util.Map
        public int size() {
            return ImmutableMap.this.size();
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new SerializedForm(this);
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public ImmutableSet<V> get(Object key) {
            Object obj = ImmutableMap.this.get(key);
            if (obj == null) {
                return null;
            }
            return ImmutableSet.of(obj);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class SerializedForm<K, V> implements Serializable {
        public static final boolean USE_LEGACY_SERIALIZATION = true;

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Object keys;
        public final Object values;

        public SerializedForm(ImmutableMap<K, V> map) {
            Object[] objArr = new Object[map.size()];
            Object[] objArr2 = new Object[map.size()];
            UnmodifiableIterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                objArr[i] = next.getKey();
                objArr2[i] = next.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Object legacyReadResolve() {
            Object[] objArr = (Object[]) this.keys;
            Object[] objArr2 = (Object[]) this.values;
            Builder<K, V> builderMakeBuilder = makeBuilder(objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                builderMakeBuilder.put(objArr[i], objArr2[i]);
            }
            return builderMakeBuilder.buildOrThrow();
        }

        public Builder<K, V> makeBuilder(int size) {
            return new Builder<>(size);
        }

        public final Object readResolve() {
            Object obj = this.keys;
            if (!(obj instanceof ImmutableSet)) {
                return legacyReadResolve();
            }
            ImmutableSet immutableSet = (ImmutableSet) obj;
            ImmutableCollection immutableCollection = (ImmutableCollection) this.values;
            Builder<K, V> builderMakeBuilder = makeBuilder(immutableSet.size());
            UnmodifiableIterator it = immutableSet.iterator();
            UnmodifiableIterator it2 = immutableCollection.iterator();
            while (it.hasNext()) {
                builderMakeBuilder.put(it.next(), it2.next());
            }
            return builderMakeBuilder.buildOrThrow();
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>(4);
    }

    public static <K, V> Builder<K, V> builderWithExpectedSize(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        return new Builder<>(expectedSize);
    }

    public static void checkNoConflict(boolean safe, String conflictDescription, Object entry1, Object entry2) {
        if (!safe) {
            throw conflictException(conflictDescription, entry1, entry2);
        }
    }

    public static IllegalArgumentException conflictException(String conflictDescription, Object entry1, Object entry2) {
        return new IllegalArgumentException("Multiple entries with same " + conflictDescription + ": " + entry1 + " and " + entry2);
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.isPartialView()) {
                return immutableMap;
            }
        }
        return copyOf(map.entrySet());
    }

    public static <K, V> Map.Entry<K, V> entryOf(K key, V value) {
        CollectPreconditions.checkEntryNotNull(key, value);
        return new AbstractMap.SimpleImmutableEntry(key, value);
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
    }

    @SafeVarargs
    public static <K, V> ImmutableMap<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
        return copyOf(Arrays.asList(entries));
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @IgnoreJRERequirement
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        return CollectCollectors.toImmutableMap(keyFunction, valueFunction);
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return EmptyImmutableSetMultimap.INSTANCE;
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), null);
        this.multimapView = immutableSetMultimap2;
        return immutableSetMultimap2;
    }

    @Override // java.util.Map
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    public abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    public abstract ImmutableSet<K> createKeySet();

    public abstract ImmutableCollection<V> createValues();

    @Override // java.util.Map
    public boolean equals(Object object) {
        return Maps.equalsImpl(this, object);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map
    public abstract V get(Object key);

    @Override // java.util.Map, j$.util.Map
    public final V getOrDefault(Object key, V defaultValue) {
        V v = get(key);
        return v != null ? v : defaultValue;
    }

    @Override // java.util.Map
    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isHashCodeFast() {
        return false;
    }

    public abstract boolean isPartialView();

    public UnmodifiableIterator<K> keyIterator() {
        final UnmodifiableIterator<Map.Entry<K, V>> it = entrySet().iterator();
        return new UnmodifiableIterator<K>(this) { // from class: com.google.common.collect.ImmutableMap.1
            public final /* synthetic */ ImmutableMap this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return (K) ((Map.Entry) it.next()).getKey();
            }
        };
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void putAll(java.util.Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean remove(Object obj, Object obj2) {
        return Map.CC.$default$remove(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        return RegularImmutableMap.create(1, new Object[]{k1, v1}, null);
    }

    @IgnoreJRERequirement
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        return CollectCollectors.toImmutableMap(keyFunction, valueFunction, mergeFunction);
    }

    @Override // java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> immutableSetCreateEntrySet = createEntrySet();
        this.entrySet = immutableSetCreateEntrySet;
        return immutableSetCreateEntrySet;
    }

    @Override // java.util.Map
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> immutableSetCreateKeySet = createKeySet();
        this.keySet = immutableSetCreateKeySet;
        return immutableSetCreateKeySet;
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    @Override // java.util.Map, com.google.common.collect.BiMap
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> immutableCollectionCreateValues = createValues();
        this.values = immutableCollectionCreateValues;
        return immutableCollectionCreateValues;
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
        Builder builder = new Builder(entries instanceof Collection ? ((Collection) entries).size() : 4);
        builder.putAll(entries);
        return builder.build(true);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        return RegularImmutableMap.create(2, new Object[]{k1, v1, k2, v2}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        return RegularImmutableMap.create(3, new Object[]{k1, v1, k2, v2, k3, v3}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        return RegularImmutableMap.create(4, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        return RegularImmutableMap.create(5, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        CollectPreconditions.checkEntryNotNull(k6, v6);
        return RegularImmutableMap.create(6, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        CollectPreconditions.checkEntryNotNull(k6, v6);
        CollectPreconditions.checkEntryNotNull(k7, v7);
        return RegularImmutableMap.create(7, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        CollectPreconditions.checkEntryNotNull(k6, v6);
        CollectPreconditions.checkEntryNotNull(k7, v7);
        CollectPreconditions.checkEntryNotNull(k8, v8);
        return RegularImmutableMap.create(8, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        CollectPreconditions.checkEntryNotNull(k6, v6);
        CollectPreconditions.checkEntryNotNull(k7, v7);
        CollectPreconditions.checkEntryNotNull(k8, v8);
        CollectPreconditions.checkEntryNotNull(k9, v9);
        return RegularImmutableMap.create(9, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9}, null);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
        CollectPreconditions.checkEntryNotNull(k1, v1);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        CollectPreconditions.checkEntryNotNull(k6, v6);
        CollectPreconditions.checkEntryNotNull(k7, v7);
        CollectPreconditions.checkEntryNotNull(k8, v8);
        CollectPreconditions.checkEntryNotNull(k9, v9);
        CollectPreconditions.checkEntryNotNull(k10, v10);
        return RegularImmutableMap.create(10, new Object[]{k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10}, null);
    }
}
