package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class LinkedHashMultimap<K, V> extends LinkedHashMultimapGwtSerializationDependencies<K, V> {
    public static final int DEFAULT_KEY_CAPACITY = 16;
    public static final int DEFAULT_VALUE_SET_CAPACITY = 2;

    @VisibleForTesting
    public static final double VALUE_SET_LOAD_FACTOR = 1.0d;

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public transient ValueEntry<K, V> multimapHeaderEntry;

    @VisibleForTesting
    public transient int valueSetCapacity;

    /* JADX INFO: renamed from: com.google.common.collect.LinkedHashMultimap$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Iterator<Map.Entry<K, V>> {
        public ValueEntry<K, V> nextEntry;
        public ValueEntry<K, V> toRemove;

        public AnonymousClass1() {
            ValueEntry<K, V> valueEntry = LinkedHashMultimap.this.multimapHeaderEntry.successorInMultimap;
            Objects.requireNonNull(valueEntry);
            this.nextEntry = valueEntry;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextEntry != LinkedHashMultimap.this.multimapHeaderEntry;
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
            LinkedHashMultimap linkedHashMultimap = LinkedHashMultimap.this;
            ValueEntry<K, V> valueEntry = this.toRemove;
            linkedHashMultimap.remove(valueEntry.key, valueEntry.value);
            this.toRemove = null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ValueEntry<K, V> valueEntry = this.nextEntry;
            this.toRemove = valueEntry;
            ValueEntry<K, V> valueEntry2 = valueEntry.successorInMultimap;
            Objects.requireNonNull(valueEntry2);
            this.nextEntry = valueEntry2;
            return valueEntry;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class ValueEntry<K, V> extends ImmutableEntry<K, V> implements ValueSetLink<K, V> {
        public ValueEntry<K, V> nextInValueBucket;
        public ValueEntry<K, V> predecessorInMultimap;
        public ValueSetLink<K, V> predecessorInValueSet;
        public final int smearedValueHash;
        public ValueEntry<K, V> successorInMultimap;
        public ValueSetLink<K, V> successorInValueSet;

        public ValueEntry(@ParametricNullness K key, @ParametricNullness V value, int smearedValueHash, ValueEntry<K, V> nextInValueBucket) {
            super(key, value);
            this.smearedValueHash = smearedValueHash;
            this.nextInValueBucket = nextInValueBucket;
        }

        public static <K, V> ValueEntry<K, V> newHeader() {
            return new ValueEntry<>(null, null, 0, null);
        }

        public ValueEntry<K, V> getPredecessorInMultimap() {
            ValueEntry<K, V> valueEntry = this.predecessorInMultimap;
            Objects.requireNonNull(valueEntry);
            return valueEntry;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public ValueSetLink<K, V> getPredecessorInValueSet() {
            ValueSetLink<K, V> valueSetLink = this.predecessorInValueSet;
            Objects.requireNonNull(valueSetLink);
            return valueSetLink;
        }

        public ValueEntry<K, V> getSuccessorInMultimap() {
            ValueEntry<K, V> valueEntry = this.successorInMultimap;
            Objects.requireNonNull(valueEntry);
            return valueEntry;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public ValueSetLink<K, V> getSuccessorInValueSet() {
            ValueSetLink<K, V> valueSetLink = this.successorInValueSet;
            Objects.requireNonNull(valueSetLink);
            return valueSetLink;
        }

        public boolean matchesValue(Object v, int smearedVHash) {
            return this.smearedValueHash == smearedVHash && com.google.common.base.Objects.equal(this.value, v);
        }

        public void setPredecessorInMultimap(ValueEntry<K, V> multimapPredecessor) {
            this.predecessorInMultimap = multimapPredecessor;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public void setPredecessorInValueSet(ValueSetLink<K, V> entry) {
            this.predecessorInValueSet = entry;
        }

        public void setSuccessorInMultimap(ValueEntry<K, V> multimapSuccessor) {
            this.successorInMultimap = multimapSuccessor;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public void setSuccessorInValueSet(ValueSetLink<K, V> entry) {
            this.successorInValueSet = entry;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public final class ValueSet extends Sets.ImprovedAbstractSet<V> implements ValueSetLink<K, V> {

        @VisibleForTesting
        public ValueEntry<K, V>[] hashTable;

        @ParametricNullness
        public final K key;
        public int size = 0;
        public int modCount = 0;
        public ValueSetLink<K, V> firstEntry = this;
        public ValueSetLink<K, V> lastEntry = this;

        public ValueSet(@ParametricNullness K key, int expectedValues) {
            this.key = key;
            this.hashTable = new ValueEntry[Hashing.closedTableSize(expectedValues, 1.0d)];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(@ParametricNullness V value) {
            int iSmearedHash = Hashing.smearedHash(value);
            int iMask = mask() & iSmearedHash;
            ValueEntry<K, V> valueEntry = this.hashTable[iMask];
            for (ValueEntry<K, V> valueEntry2 = valueEntry; valueEntry2 != null; valueEntry2 = valueEntry2.nextInValueBucket) {
                if (valueEntry2.matchesValue(value, iSmearedHash)) {
                    return false;
                }
            }
            ValueEntry<K, V> valueEntry3 = new ValueEntry<>(this.key, value, iSmearedHash, valueEntry);
            LinkedHashMultimap.succeedsInValueSet(this.lastEntry, valueEntry3);
            LinkedHashMultimap.succeedsInValueSet(valueEntry3, this);
            ValueEntry<K, V> valueEntry4 = LinkedHashMultimap.this.multimapHeaderEntry.predecessorInMultimap;
            Objects.requireNonNull(valueEntry4);
            valueEntry4.successorInMultimap = valueEntry3;
            valueEntry3.predecessorInMultimap = valueEntry4;
            ValueEntry<K, V> valueEntry5 = LinkedHashMultimap.this.multimapHeaderEntry;
            valueEntry3.successorInMultimap = valueEntry5;
            valueEntry5.predecessorInMultimap = valueEntry3;
            this.hashTable[iMask] = valueEntry3;
            this.size++;
            this.modCount++;
            rehashIfNecessary();
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Arrays.fill(this.hashTable, (Object) null);
            this.size = 0;
            for (ValueSetLink<K, V> successorInValueSet = this.firstEntry; successorInValueSet != this; successorInValueSet = successorInValueSet.getSuccessorInValueSet()) {
                LinkedHashMultimap.deleteFromMultimap((ValueEntry) successorInValueSet);
            }
            LinkedHashMultimap.succeedsInValueSet(this, this);
            this.modCount++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            int iSmearedHash = Hashing.smearedHash(o);
            for (ValueEntry<K, V> valueEntry = this.hashTable[mask() & iSmearedHash]; valueEntry != null; valueEntry = valueEntry.nextInValueBucket) {
                if (valueEntry.matchesValue(o, iSmearedHash)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.lastEntry;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.firstEntry;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new Iterator<V>() { // from class: com.google.common.collect.LinkedHashMultimap.ValueSet.1
                public int expectedModCount;
                public ValueSetLink<K, V> nextEntry;
                public ValueEntry<K, V> toRemove;

                {
                    this.nextEntry = ValueSet.this.firstEntry;
                    this.expectedModCount = ValueSet.this.modCount;
                }

                public final void checkForComodification() {
                    if (ValueSet.this.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkForComodification();
                    return this.nextEntry != ValueSet.this;
                }

                @Override // java.util.Iterator
                @ParametricNullness
                public V next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    ValueEntry<K, V> valueEntry = (ValueEntry) this.nextEntry;
                    V v = valueEntry.value;
                    this.toRemove = valueEntry;
                    this.nextEntry = valueEntry.getSuccessorInValueSet();
                    return v;
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkForComodification();
                    Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
                    ValueSet.this.remove(this.toRemove.value);
                    this.expectedModCount = ValueSet.this.modCount;
                    this.toRemove = null;
                }
            };
        }

        public final int mask() {
            return this.hashTable.length - 1;
        }

        public final void rehashIfNecessary() {
            if (Hashing.needsResizing(this.size, this.hashTable.length, 1.0d)) {
                int length = this.hashTable.length * 2;
                ValueEntry<K, V>[] valueEntryArr = new ValueEntry[length];
                this.hashTable = valueEntryArr;
                int i = length - 1;
                for (ValueSetLink<K, V> successorInValueSet = this.firstEntry; successorInValueSet != this; successorInValueSet = successorInValueSet.getSuccessorInValueSet()) {
                    ValueEntry<K, V> valueEntry = (ValueEntry) successorInValueSet;
                    int i2 = valueEntry.smearedValueHash & i;
                    valueEntry.nextInValueBucket = valueEntryArr[i2];
                    valueEntryArr[i2] = valueEntry;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        public boolean remove(Object o) {
            int iSmearedHash = Hashing.smearedHash(o);
            int iMask = mask() & iSmearedHash;
            ValueEntry<K, V> valueEntry = null;
            for (ValueEntry<K, V> valueEntry2 = this.hashTable[iMask]; valueEntry2 != null; valueEntry2 = valueEntry2.nextInValueBucket) {
                if (valueEntry2.matchesValue(o, iSmearedHash)) {
                    if (valueEntry == null) {
                        this.hashTable[iMask] = valueEntry2.nextInValueBucket;
                    } else {
                        valueEntry.nextInValueBucket = valueEntry2.nextInValueBucket;
                    }
                    LinkedHashMultimap.deleteFromValueSet(valueEntry2);
                    LinkedHashMultimap.deleteFromMultimap(valueEntry2);
                    this.size--;
                    this.modCount++;
                    return true;
                }
                valueEntry = valueEntry2;
            }
            return false;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public void setPredecessorInValueSet(ValueSetLink<K, V> entry) {
            this.lastEntry = entry;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.ValueSetLink
        public void setSuccessorInValueSet(ValueSetLink<K, V> entry) {
            this.firstEntry = entry;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ValueSetLink<K, V> {
        ValueSetLink<K, V> getPredecessorInValueSet();

        ValueSetLink<K, V> getSuccessorInValueSet();

        void setPredecessorInValueSet(ValueSetLink<K, V> entry);

        void setSuccessorInValueSet(ValueSetLink<K, V> entry);
    }

    public LinkedHashMultimap(int keyCapacity, int valueSetCapacity) {
        super(CompactLinkedHashMap.createWithExpectedSize(keyCapacity));
        this.valueSetCapacity = 2;
        CollectPreconditions.checkNonnegative(valueSetCapacity, "expectedValuesPerKey");
        this.valueSetCapacity = valueSetCapacity;
        ValueEntry<K, V> valueEntryNewHeader = ValueEntry.newHeader();
        this.multimapHeaderEntry = valueEntryNewHeader;
        valueEntryNewHeader.successorInMultimap = valueEntryNewHeader;
        valueEntryNewHeader.predecessorInMultimap = valueEntryNewHeader;
    }

    public static void access$400(ValueEntry valueEntry, ValueEntry valueEntry2) {
        valueEntry.successorInMultimap = valueEntry2;
        valueEntry2.predecessorInMultimap = valueEntry;
    }

    public static <K, V> LinkedHashMultimap<K, V> create() {
        return new LinkedHashMultimap<>(16, 2);
    }

    public static <K, V> void deleteFromMultimap(ValueEntry<K, V> entry) {
        ValueEntry<K, V> valueEntry = entry.predecessorInMultimap;
        Objects.requireNonNull(valueEntry);
        ValueEntry<K, V> valueEntry2 = entry.successorInMultimap;
        Objects.requireNonNull(valueEntry2);
        valueEntry.successorInMultimap = valueEntry2;
        valueEntry2.predecessorInMultimap = valueEntry;
    }

    public static <K, V> void deleteFromValueSet(ValueSetLink<K, V> entry) {
        succeedsInValueSet(entry.getPredecessorInValueSet(), entry.getSuccessorInValueSet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @J2ktIncompatible
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        ValueEntry<K, V> valueEntryNewHeader = ValueEntry.newHeader();
        this.multimapHeaderEntry = valueEntryNewHeader;
        valueEntryNewHeader.successorInMultimap = valueEntryNewHeader;
        valueEntryNewHeader.predecessorInMultimap = valueEntryNewHeader;
        this.valueSetCapacity = 2;
        int i = stream.readInt();
        CompactLinkedHashMap compactLinkedHashMapCreateWithExpectedSize = CompactLinkedHashMap.createWithExpectedSize(12);
        for (int i2 = 0; i2 < i; i2++) {
            Object object = stream.readObject();
            compactLinkedHashMapCreateWithExpectedSize.put(object, createCollection(object));
        }
        int i3 = stream.readInt();
        for (int i4 = 0; i4 < i3; i4++) {
            Object object2 = stream.readObject();
            Object object3 = stream.readObject();
            Collection collection = (Collection) compactLinkedHashMapCreateWithExpectedSize.get(object2);
            Objects.requireNonNull(collection);
            collection.add(object3);
        }
        setMap(compactLinkedHashMapCreateWithExpectedSize);
    }

    public static <K, V> void succeedsInMultimap(ValueEntry<K, V> pred, ValueEntry<K, V> succ) {
        pred.successorInMultimap = succ;
        succ.predecessorInMultimap = pred;
    }

    public static <K, V> void succeedsInValueSet(ValueSetLink<K, V> pred, ValueSetLink<K, V> succ) {
        pred.setSuccessorInValueSet(succ);
        succ.setPredecessorInValueSet(pred);
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(super.keySet().size());
        Iterator<K> it = super.keySet().iterator();
        while (it.hasNext()) {
            stream.writeObject(it.next());
        }
        stream.writeInt(this.totalSize);
        for (Map.Entry<K, V> entry : super.entries()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public void clear() {
        super.clear();
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        valueEntry.successorInMultimap = valueEntry;
        valueEntry.predecessorInMultimap = valueEntry;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(Object key, Object value) {
        return super.containsEntry(key, value);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
    public Collection createCollection() {
        return CompactLinkedHashSet.createWithExpectedSize(this.valueSetCapacity);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Set get(@ParametricNullness Object key) {
        return super.get(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
        return super.put(key, value);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean remove(Object key, Object value) {
        return super.remove(key, value);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Set removeAll(Object key) {
        return super.removeAll(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
        return super.replaceValues(key, values);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
    public Iterator<V> valueIterator() {
        return new Maps.AnonymousClass2(new AnonymousClass1());
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    public static <K, V> LinkedHashMultimap<K, V> create(int expectedKeys, int expectedValuesPerKey) {
        return new LinkedHashMultimap<>(Maps.capacity(expectedKeys), Maps.capacity(expectedValuesPerKey));
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean putAll(@ParametricNullness Object key, Iterable values) {
        return super.putAll(key, values);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    public Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values) {
        return super.replaceValues((Object) key, (Iterable) values);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
    public Set<V> createCollection() {
        return CompactLinkedHashSet.createWithExpectedSize(this.valueSetCapacity);
    }

    public static <K, V> LinkedHashMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        LinkedHashMultimap<K, V> linkedHashMultimapCreate = create(multimap.keySet().size(), 2);
        super.putAll(multimap);
        return linkedHashMultimapCreate;
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public Collection<V> createCollection(@ParametricNullness K key) {
        return new ValueSet(key, this.valueSetCapacity);
    }
}
