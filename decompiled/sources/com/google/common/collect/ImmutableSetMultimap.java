package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import j$.util.Objects;
import j$.util.stream.Collector;
import j$.util.stream.Stream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> implements SetMultimap<K, V> {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final transient ImmutableSet<V> emptySet;

    @RetainedWith
    @LazyInit
    public transient ImmutableSet<Map.Entry<K, V>> entries;

    @RetainedWith
    @LazyInit
    public transient ImmutableSetMultimap<V, K> inverse;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<K, V> extends ImmutableMultimap.Builder<K, V> {
        public Builder() {
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder combine(ImmutableMultimap.Builder other) {
            super.combine(other);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        public int expectedValueCollectionSize(int defaultExpectedValues, Iterable<?> values) {
            return values instanceof Set ? Math.max(defaultExpectedValues, ((Set) values).size()) : defaultExpectedValues;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder expectedValuesPerKey(int expectedValuesPerKey) {
            super.expectedValuesPerKey(expectedValuesPerKey);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        public ImmutableCollection.Builder<V> newValueCollectionBuilderWithExpectedSize(int expectedSize) {
            Comparator<? super V> comparator = this.valueComparator;
            return comparator == null ? ImmutableSet.builderWithExpectedSize(expectedSize) : new ImmutableSortedSet.Builder(comparator, expectedSize);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder orderKeysBy(Comparator keyComparator) {
            super.orderKeysBy(keyComparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder orderValuesBy(Comparator valueComparator) {
            super.orderValuesBy(valueComparator);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder put(Object key, Object value) {
            super.put(key, value);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder putAll(Multimap multimap) {
            putAll(multimap);
            return this;
        }

        public Builder(int expectedKeys) {
            super(expectedKeys);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        public ImmutableSetMultimap<K, V> build() {
            Map<K, ImmutableCollection.Builder<V>> map = this.builderMap;
            if (map == null) {
                return EmptyImmutableSetMultimap.INSTANCE;
            }
            Collection collectionEntrySet = map.entrySet();
            Comparator<? super K> comparator = this.keyComparator;
            if (comparator != null) {
                collectionEntrySet = Ordering.from(comparator).onKeys().immutableSortedCopy(collectionEntrySet);
            }
            return ImmutableSetMultimap.fromMapBuilderEntries(collectionEntrySet, this.valueComparator);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> combine(ImmutableMultimap.Builder<K, V> other) {
            super.combine((ImmutableMultimap.Builder) other);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> expectedValuesPerKey(int expectedValuesPerKey) {
            super.expectedValuesPerKey(expectedValuesPerKey);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
            super.orderKeysBy((Comparator) keyComparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
            super.orderValuesBy((Comparator) valueComparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(K key, V value) {
            super.put((Object) key, (Object) value);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder putAll(Object key, Object[] values) {
            putAll(key, values);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder put(Map.Entry entry) {
            super.put(entry);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder putAll(Iterable entries) {
            super.putAll(entries);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
            super.putAll((Iterable) entries);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public ImmutableMultimap.Builder putAll(Object key, Iterable values) {
            super.putAll(key, values);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, Iterable<? extends V> values) {
            super.putAll((Object) key, (Iterable) values);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, V... values) {
            super.putAll((Object) key, (Iterable) Arrays.asList(values));
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry<? extends K, Collection<? extends V>> entry : multimap.asMap().entrySet()) {
                super.putAll((Object) entry.getKey(), (Iterable) entry.getValue());
            }
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

        @Weak
        public final transient ImmutableSetMultimap<K, V> multimap;

        public EntrySet(ImmutableSetMultimap<K, V> multimap) {
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
            return false;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.multimap.entryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return this.multimap.entryIterator();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    @GwtIncompatible
    public static final class SetFieldSettersHolder {
        public static final Serialization.FieldSetter<? super ImmutableSetMultimap<?, ?>> EMPTY_SET_FIELD_SETTER = Serialization.getFieldSetter(ImmutableSetMultimap.class, "emptySet");
    }

    public ImmutableSetMultimap(ImmutableMap<K, ImmutableSet<V>> map, int size, Comparator<? super V> valueComparator) {
        super(map, size);
        this.emptySet = emptySet(valueComparator);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> Builder<K, V> builderWithExpectedKeys(int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new Builder<>(expectedKeys);
    }

    public static <K, V> ImmutableSetMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        return copyOf(multimap, null);
    }

    public static <V> ImmutableSet<V> emptySet(Comparator<? super V> valueComparator) {
        return valueComparator == null ? ImmutableSet.of() : ImmutableSortedSet.emptySet(valueComparator);
    }

    @IgnoreJRERequirement
    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> flatteningToImmutableSetMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends Stream<? extends V>> valuesFunction) {
        return CollectCollectors.flatteningToImmutableSetMultimap(keyFunction, valuesFunction);
    }

    public static <K, V> ImmutableSetMultimap<K, V> fromMapBuilderEntries(Collection<? extends Map.Entry<K, ImmutableCollection.Builder<V>>> mapEntries, Comparator<? super V> valueComparator) {
        if (mapEntries.isEmpty()) {
            return EmptyImmutableSetMultimap.INSTANCE;
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(mapEntries.size());
        int size = 0;
        for (Map.Entry<K, ImmutableCollection.Builder<V>> entry : mapEntries) {
            K key = entry.getKey();
            ImmutableSet immutableSetValueSet = valueSet(valueComparator, ((ImmutableSet.Builder) entry.getValue()).build());
            if (!immutableSetValueSet.isEmpty()) {
                builder.put(key, immutableSetValueSet);
                size = immutableSetValueSet.size() + size;
            }
        }
        return new ImmutableSetMultimap<>(builder.build(true), size, valueComparator);
    }

    public static <K, V> ImmutableSetMultimap<K, V> fromMapEntries(Collection<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> mapEntries, Comparator<? super V> valueComparator) {
        if (mapEntries.isEmpty()) {
            return EmptyImmutableSetMultimap.INSTANCE;
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(mapEntries.size());
        int size = 0;
        for (Map.Entry<? extends K, ? extends Collection<? extends V>> entry : mapEntries) {
            K key = entry.getKey();
            ImmutableSet immutableSetValueSet = valueSet(valueComparator, entry.getValue());
            if (!immutableSetValueSet.isEmpty()) {
                builder.put(key, immutableSetValueSet);
                size = immutableSetValueSet.size() + size;
            }
        }
        return new ImmutableSetMultimap<>(builder.build(true), size, valueComparator);
    }

    public static <K, V> ImmutableSetMultimap<K, V> of() {
        return EmptyImmutableSetMultimap.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @J2ktIncompatible
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        Comparator comparator = (Comparator) stream.readObject();
        int i = stream.readInt();
        if (i < 0) {
            throw new InvalidObjectException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid key count ", i));
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object object = stream.readObject();
            Objects.requireNonNull(object);
            int i4 = stream.readInt();
            if (i4 <= 0) {
                throw new InvalidObjectException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid value count ", i4));
            }
            ImmutableSet.Builder builderValuesBuilder = valuesBuilder(comparator);
            for (int i5 = 0; i5 < i4; i5++) {
                Object object2 = stream.readObject();
                Objects.requireNonNull(object2);
                builderValuesBuilder.add(object2);
            }
            ImmutableSet immutableSetBuild = builderValuesBuilder.build();
            if (immutableSetBuild.size() != i4) {
                throw new InvalidObjectException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Duplicate key-value pairs exist for key ", object));
            }
            builder.put(object, immutableSetBuild);
            i2 += i4;
        }
        try {
            ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set(this, builder.build(true));
            ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set(this, i2);
            SetFieldSettersHolder.EMPTY_SET_FIELD_SETTER.set(this, emptySet(comparator));
        } catch (IllegalArgumentException e) {
            throw ((InvalidObjectException) new InvalidObjectException(e.getMessage()).initCause(e));
        }
    }

    @IgnoreJRERequirement
    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> toImmutableSetMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        return CollectCollectors.toImmutableSetMultimap(keyFunction, valueFunction);
    }

    public static <V> ImmutableSet<V> valueSet(Comparator<? super V> valueComparator, Collection<? extends V> values) {
        return valueComparator == null ? ImmutableSet.copyOf((Collection) values) : ImmutableSortedSet.copyOf((Comparator) valueComparator, (Collection) values);
    }

    public static <V> ImmutableSet.Builder<V> valuesBuilder(Comparator<? super V> valueComparator) {
        return valueComparator == null ? new ImmutableSet.Builder<>(4) : new ImmutableSortedSet.Builder(valueComparator);
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(valueComparator());
        Serialization.writeMultimap(this, stream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ImmutableSetMultimap<V, K> invert() {
        Builder builder = new Builder();
        UnmodifiableIterator it = entries().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            builder.put(entry.getValue(), entry.getKey());
        }
        ImmutableSetMultimap<V, K> immutableSetMultimapBuild = builder.build();
        immutableSetMultimapBuild.inverse = this;
        return immutableSetMultimapBuild;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public ImmutableCollection removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public ImmutableCollection replaceValues(Object key, Iterable values) {
        throw new UnsupportedOperationException();
    }

    public Comparator<? super V> valueComparator() {
        ImmutableSet<V> immutableSet = this.emptySet;
        if (immutableSet instanceof ImmutableSortedSet) {
            return ((ImmutableSortedSet) immutableSet).comparator();
        }
        return null;
    }

    public static <K, V> ImmutableSetMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
        Builder builder = new Builder();
        builder.putAll((Iterable) entries);
        return builder.build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k1, V v1) {
        Builder builder = new Builder();
        builder.put((Object) k1, (Object) v1);
        return builder.build();
    }

    @Override // com.google.common.collect.ImmutableMultimap
    public ImmutableSetMultimap<V, K> inverse() {
        ImmutableSetMultimap<V, K> immutableSetMultimap = this.inverse;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<V, K> immutableSetMultimapInvert = invert();
        this.inverse = immutableSetMultimapInvert;
        return immutableSetMultimapInvert;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final ImmutableSet<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final ImmutableSet<V> replaceValues(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public Collection removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public Collection replaceValues(Object key, Iterable values) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public ImmutableSet<Map.Entry<K, V>> entries() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entries;
        if (immutableSet != null) {
            return immutableSet;
        }
        EntrySet entrySet = new EntrySet(this);
        this.entries = entrySet;
        return entrySet;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public ImmutableSet<V> get(K key) {
        return (ImmutableSet) MoreObjects.firstNonNull((ImmutableSet) this.map.get(key), this.emptySet);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public Set removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public Set replaceValues(Object key, Iterable values) {
        throw new UnsupportedOperationException();
    }

    public static <K, V> ImmutableSetMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap, Comparator<? super V> valueComparator) {
        multimap.getClass();
        if (multimap.isEmpty() && valueComparator == null) {
            return EmptyImmutableSetMultimap.INSTANCE;
        }
        if (multimap instanceof ImmutableSetMultimap) {
            ImmutableSetMultimap<K, V> immutableSetMultimap = (ImmutableSetMultimap) multimap;
            if (!immutableSetMultimap.isPartialView()) {
                return immutableSetMultimap;
            }
        }
        return fromMapEntries(multimap.asMap().entrySet(), valueComparator);
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k1, V v1, K k2, V v2) {
        Builder builder = new Builder();
        builder.put((Object) k1, (Object) v1);
        builder.put((Object) k2, (Object) v2);
        return builder.build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Builder builder = new Builder();
        builder.put((Object) k1, (Object) v1);
        builder.put((Object) k2, (Object) v2);
        builder.put((Object) k3, (Object) v3);
        return builder.build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Builder builder = new Builder();
        builder.put((Object) k1, (Object) v1);
        builder.put((Object) k2, (Object) v2);
        builder.put((Object) k3, (Object) v3);
        builder.put((Object) k4, (Object) v4);
        return builder.build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Builder builder = new Builder();
        builder.put((Object) k1, (Object) v1);
        builder.put((Object) k2, (Object) v2);
        builder.put((Object) k3, (Object) v3);
        builder.put((Object) k4, (Object) v4);
        builder.put((Object) k5, (Object) v5);
        return builder.build();
    }
}
