package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {

    @VisibleForTesting
    public static final double HASH_FLOODING_FPP = 0.001d;
    public static final int MAX_HASH_BUCKET_LENGTH = 9;
    public static final Object NOT_FOUND = new Object();

    @VisibleForTesting
    public transient int[] entries;

    @LazyInit
    public transient Set<Map.Entry<K, V>> entrySetView;

    @LazyInit
    public transient Set<K> keySetView;

    @VisibleForTesting
    public transient Object[] keys;
    public transient int metadata;
    public transient int size;
    public transient Object table;

    @VisibleForTesting
    public transient Object[] values;

    @LazyInit
    public transient Collection<V> valuesView;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        public EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            Map<K, V> mapDelegateOrNull = CompactHashMap.this.delegateOrNull();
            if (mapDelegateOrNull != null) {
                return mapDelegateOrNull.entrySet().contains(o);
            }
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) o;
                int iIndexOf = CompactHashMap.this.indexOf(entry.getKey());
                if (iIndexOf != -1 && Objects.equal(CompactHashMap.this.requireValues()[iIndexOf], entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            Map<K, V> mapDelegateOrNull = CompactHashMap.this.delegateOrNull();
            if (mapDelegateOrNull != null) {
                return mapDelegateOrNull.entrySet().remove(o);
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            if (CompactHashMap.this.needsAllocArrays()) {
                return false;
            }
            int iHashTableMask = CompactHashMap.this.hashTableMask();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object obj = CompactHashMap.this.table;
            j$.util.Objects.requireNonNull(obj);
            int iRemove = CompactHashing.remove(key, value, iHashTableMask, obj, CompactHashMap.this.requireEntries(), CompactHashMap.this.requireKeys(), CompactHashMap.this.requireValues());
            if (iRemove == -1) {
                return false;
            }
            CompactHashMap.this.moveLastEntry(iRemove, iHashTableMask);
            CompactHashMap.access$1210(CompactHashMap.this);
            CompactHashMap.this.incrementModCount();
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class Itr<T> implements Iterator<T> {
        public int currentIndex;
        public int expectedMetadata;
        public int indexToRemove;

        public Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        public final void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }

        @ParametricNullness
        public abstract T getOutput(int entry);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        public void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int i = this.currentIndex;
            this.indexToRemove = i;
            T output = getOutput(i);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return output;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            incrementExpectedModCount();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.requireKeys()[this.indexToRemove]);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class KeySetView extends AbstractSet<K> {
        public KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return CompactHashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            Map<K, V> mapDelegateOrNull = CompactHashMap.this.delegateOrNull();
            return mapDelegateOrNull != null ? mapDelegateOrNull.keySet().remove(o) : CompactHashMap.this.removeHelper(o) != CompactHashMap.NOT_FOUND;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class MapEntry extends AbstractMapEntry<K, V> {

        @ParametricNullness
        public final K key;
        public int lastKnownIndex;

        public MapEntry(int i) {
            this.key = (K) CompactHashMap.access$100(CompactHashMap.this, i);
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getValue() {
            Map<K, V> mapDelegateOrNull = CompactHashMap.this.delegateOrNull();
            if (mapDelegateOrNull != null) {
                return mapDelegateOrNull.get(this.key);
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                return null;
            }
            return (V) CompactHashMap.this.requireValues()[i];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V setValue(@ParametricNullness V v) {
            Map<K, V> mapDelegateOrNull = CompactHashMap.this.delegateOrNull();
            if (mapDelegateOrNull != null) {
                return mapDelegateOrNull.put(this.key, v);
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                CompactHashMap.this.put(this.key, v);
                return null;
            }
            V v2 = (V) CompactHashMap.this.requireValues()[i];
            CompactHashMap.this.setValue(this.lastKnownIndex, v);
            return v2;
        }

        public final void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i != -1 && i < CompactHashMap.this.size()) {
                K k = this.key;
                CompactHashMap compactHashMap = CompactHashMap.this;
                if (Objects.equal(k, compactHashMap.requireKeys()[this.lastKnownIndex])) {
                    return;
                }
            }
            this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ValuesView extends AbstractCollection<V> {
        public ValuesView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return CompactHashMap.this.size();
        }
    }

    public CompactHashMap() {
        init(3);
    }

    public static Object access$100(CompactHashMap compactHashMap, int i) {
        return compactHashMap.requireKeys()[i];
    }

    public static /* synthetic */ int access$1210(CompactHashMap compactHashMap) {
        int i = compactHashMap.size;
        compactHashMap.size = i - 1;
        return i;
    }

    public static Object access$600(CompactHashMap compactHashMap, int i) {
        return compactHashMap.requireValues()[i];
    }

    public static Object access$800(CompactHashMap compactHashMap) {
        Object obj = compactHashMap.table;
        j$.util.Objects.requireNonNull(obj);
        return obj;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<>(expectedSize);
    }

    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    @CanIgnoreReturnValue
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i = this.metadata;
        int iTableSize = CompactHashing.tableSize(i);
        this.table = CompactHashing.createTable(iTableSize);
        setHashTableMask(iTableSize - 1);
        this.entries = new int[i];
        this.keys = new Object[i];
        this.values = new Object[i];
        return i;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, 1073741823);
            mapDelegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireKeys(), 0, this.size, (Object) null);
        Arrays.fill(requireValues(), 0, this.size, (Object) null);
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        CompactHashing.tableClear(obj);
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        return mapDelegateOrNull != null ? mapDelegateOrNull.containsKey(key) : indexOf(key) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            return mapDelegateOrNull.containsValue(value);
        }
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(value, requireValues()[i])) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> mapCreateHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int iFirstEntryIndex = firstEntryIndex();
        while (iFirstEntryIndex >= 0) {
            mapCreateHashFloodingResistantDelegate.put(requireKeys()[iFirstEntryIndex], requireValues()[iFirstEntryIndex]);
            iFirstEntryIndex = getSuccessor(iFirstEntryIndex);
        }
        this.table = mapCreateHashFloodingResistantDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return mapCreateHashFloodingResistantDelegate;
    }

    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    public Map<K, V> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashMap(tableSize, 1.0f);
    }

    public Set<K> createKeySet() {
        return new KeySetView();
    }

    public Collection<V> createValues() {
        return new ValuesView();
    }

    @VisibleForTesting
    public Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final int entry(int i) {
        return requireEntries()[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
        this.entrySetView = setCreateEntrySet;
        return setCreateEntrySet;
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        return mapDelegateOrNull != null ? mapDelegateOrNull.entrySet().iterator() : new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.CompactHashMap.2
            @Override // com.google.common.collect.CompactHashMap.Itr
            public Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            return mapDelegateOrNull.get(obj);
        }
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return null;
        }
        accessEntry(iIndexOf);
        return (V) requireValues()[iIndexOf];
    }

    public int getSuccessor(int entryIndex) {
        int i = entryIndex + 1;
        if (i < this.size) {
            return i;
        }
        return -1;
    }

    public final int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    public void incrementModCount() {
        this.metadata += 32;
    }

    public final int indexOf(Object key) {
        if (needsAllocArrays()) {
            return -1;
        }
        int iSmearedHash = Hashing.smearedHash(key);
        int iHashTableMask = hashTableMask();
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        int iTableGet = CompactHashing.tableGet(obj, iSmearedHash & iHashTableMask);
        if (iTableGet == 0) {
            return -1;
        }
        int i = ~iHashTableMask;
        int i2 = iSmearedHash & i;
        do {
            int i3 = iTableGet - 1;
            int i4 = requireEntries()[i3];
            if ((i4 & i) == i2 && Objects.equal(key, requireKeys()[i3])) {
                return i3;
            }
            iTableGet = i4 & iHashTableMask;
        } while (iTableGet != 0);
        return -1;
    }

    public void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(expectedSize, 1, 1073741823);
    }

    public void insertEntry(int entryIndex, @ParametricNullness K key, @ParametricNullness V value, int hash, int mask) {
        setEntry(entryIndex, CompactHashing.maskCombine(hash, 0, mask));
        setKey(entryIndex, key);
        setValue(entryIndex, value);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final K key(int i) {
        return (K) requireKeys()[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> setCreateKeySet = createKeySet();
        this.keySetView = setCreateKeySet;
        return setCreateKeySet;
    }

    public Iterator<K> keySetIterator() {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        return mapDelegateOrNull != null ? mapDelegateOrNull.keySet().iterator() : new CompactHashMap<K, V>.Itr<K>() { // from class: com.google.common.collect.CompactHashMap.1
            @Override // com.google.common.collect.CompactHashMap.Itr
            @ParametricNullness
            public K getOutput(int i) {
                return (K) CompactHashMap.access$100(CompactHashMap.this, i);
            }
        };
    }

    public void moveLastEntry(int dstIndex, int mask) {
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        int[] iArrRequireEntries = requireEntries();
        Object[] objArrRequireKeys = requireKeys();
        Object[] objArrRequireValues = requireValues();
        int size = size();
        int i = size - 1;
        if (dstIndex >= i) {
            objArrRequireKeys[dstIndex] = null;
            objArrRequireValues[dstIndex] = null;
            iArrRequireEntries[dstIndex] = 0;
            return;
        }
        Object obj2 = objArrRequireKeys[i];
        objArrRequireKeys[dstIndex] = obj2;
        objArrRequireValues[dstIndex] = objArrRequireValues[i];
        objArrRequireKeys[i] = null;
        objArrRequireValues[i] = null;
        iArrRequireEntries[dstIndex] = iArrRequireEntries[i];
        iArrRequireEntries[i] = 0;
        int iSmearedHash = Hashing.smearedHash(obj2) & mask;
        int iTableGet = CompactHashing.tableGet(obj, iSmearedHash);
        if (iTableGet == size) {
            CompactHashing.tableSet(obj, iSmearedHash, dstIndex + 1);
            return;
        }
        while (true) {
            int i2 = iTableGet - 1;
            int i3 = iArrRequireEntries[i2];
            int i4 = i3 & mask;
            if (i4 == size) {
                iArrRequireEntries[i2] = CompactHashing.maskCombine(i3, dstIndex + 1, mask);
                return;
            }
            iTableGet = i4;
        }
    }

    public boolean needsAllocArrays() {
        return this.table == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(@ParametricNullness K k, @ParametricNullness V v) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            return mapDelegateOrNull.put(k, v);
        }
        int[] iArrRequireEntries = requireEntries();
        Object[] objArrRequireKeys = requireKeys();
        Object[] objArrRequireValues = requireValues();
        int i = this.size;
        int i2 = i + 1;
        int iSmearedHash = Hashing.smearedHash(k);
        int iHashTableMask = hashTableMask();
        int i3 = iSmearedHash & iHashTableMask;
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        int iTableGet = CompactHashing.tableGet(obj, i3);
        if (iTableGet != 0) {
            int i4 = ~iHashTableMask;
            int i5 = iSmearedHash & i4;
            int i6 = 0;
            while (true) {
                int i7 = iTableGet - 1;
                int i8 = iArrRequireEntries[i7];
                if ((i8 & i4) == i5 && Objects.equal(k, objArrRequireKeys[i7])) {
                    V v2 = (V) objArrRequireValues[i7];
                    objArrRequireValues[i7] = v;
                    accessEntry(i7);
                    return v2;
                }
                int i9 = i8 & iHashTableMask;
                i6++;
                if (i9 != 0) {
                    k = k;
                    v = v;
                    iTableGet = i9;
                } else {
                    if (i6 >= 9) {
                        return convertToHashFloodingResistantImplementation().put(k, v);
                    }
                    if (i2 > iHashTableMask) {
                        iHashTableMask = resizeTable(iHashTableMask, CompactHashing.newCapacity(iHashTableMask), iSmearedHash, i);
                    } else {
                        iArrRequireEntries[i7] = CompactHashing.maskCombine(i8, i2, iHashTableMask);
                    }
                }
            }
        } else if (i2 > iHashTableMask) {
            iHashTableMask = resizeTable(iHashTableMask, CompactHashing.newCapacity(iHashTableMask), iSmearedHash, i);
        } else {
            Object obj2 = this.table;
            j$.util.Objects.requireNonNull(obj2);
            CompactHashing.tableSet(obj2, i3, i2);
        }
        int i10 = iHashTableMask;
        resizeMeMaybe(i2);
        insertEntry(i, k, v, iSmearedHash, i10);
        this.size = i2;
        incrementModCount();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @J2ktIncompatible
    public final void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        int i = stream.readInt();
        if (i < 0) {
            throw new InvalidObjectException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid size: ", i));
        }
        init(i);
        for (int i2 = 0; i2 < i; i2++) {
            put(stream.readObject(), stream.readObject());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object obj) {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            return mapDelegateOrNull.remove(obj);
        }
        V v = (V) removeHelper(obj);
        if (v == NOT_FOUND) {
            return null;
        }
        return v;
    }

    public final Object removeHelper(Object key) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int iHashTableMask = hashTableMask();
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        int iRemove = CompactHashing.remove(key, null, iHashTableMask, obj, requireEntries(), requireKeys(), null);
        if (iRemove == -1) {
            return NOT_FOUND;
        }
        Object obj2 = requireValues()[iRemove];
        moveLastEntry(iRemove, iHashTableMask);
        this.size--;
        incrementModCount();
        return obj2;
    }

    public final int[] requireEntries() {
        int[] iArr = this.entries;
        j$.util.Objects.requireNonNull(iArr);
        return iArr;
    }

    public final Object[] requireKeys() {
        Object[] objArr = this.keys;
        j$.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    public final Object requireTable() {
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        return obj;
    }

    public final Object[] requireValues() {
        Object[] objArr = this.values;
        j$.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    public void resizeEntries(int newCapacity) {
        this.entries = Arrays.copyOf(requireEntries(), newCapacity);
        this.keys = Arrays.copyOf(requireKeys(), newCapacity);
        this.values = Arrays.copyOf(requireValues(), newCapacity);
    }

    public final void resizeMeMaybe(int newSize) {
        int iMin;
        int length = requireEntries().length;
        if (newSize <= length || (iMin = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        resizeEntries(iMin);
    }

    @CanIgnoreReturnValue
    public final int resizeTable(int oldMask, int newCapacity, int targetHash, int targetEntryIndex) {
        Object objCreateTable = CompactHashing.createTable(newCapacity);
        int i = newCapacity - 1;
        if (targetEntryIndex != 0) {
            CompactHashing.tableSet(objCreateTable, targetHash & i, targetEntryIndex + 1);
        }
        Object obj = this.table;
        j$.util.Objects.requireNonNull(obj);
        int[] iArrRequireEntries = requireEntries();
        for (int i2 = 0; i2 <= oldMask; i2++) {
            int iTableGet = CompactHashing.tableGet(obj, i2);
            while (iTableGet != 0) {
                int i3 = iTableGet - 1;
                int i4 = iArrRequireEntries[i3];
                int i5 = ((~oldMask) & i4) | i2;
                int i6 = i5 & i;
                int iTableGet2 = CompactHashing.tableGet(objCreateTable, i6);
                CompactHashing.tableSet(objCreateTable, i6, iTableGet);
                iArrRequireEntries[i3] = CompactHashing.maskCombine(i5, iTableGet2, i);
                iTableGet = i4 & oldMask;
            }
        }
        this.table = objCreateTable;
        setHashTableMask(i);
        return i;
    }

    public final void setEntry(int i, int value) {
        requireEntries()[i] = value;
    }

    public final void setHashTableMask(int mask) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(mask), 31);
    }

    public final void setKey(int i, K key) {
        requireKeys()[i] = key;
    }

    public final void setValue(int i, V value) {
        requireValues()[i] = value;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        return mapDelegateOrNull != null ? mapDelegateOrNull.size() : this.size;
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        if (mapDelegateOrNull != null) {
            Map<K, V> mapCreateHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            mapCreateHashFloodingResistantDelegate.putAll(mapDelegateOrNull);
            this.table = mapCreateHashFloodingResistantDelegate;
            return;
        }
        int i = this.size;
        if (i < requireEntries().length) {
            resizeEntries(i);
        }
        int iTableSize = CompactHashing.tableSize(i);
        int iHashTableMask = hashTableMask();
        if (iTableSize < iHashTableMask) {
            resizeTable(iHashTableMask, iTableSize, 0, 0);
        }
    }

    public final V value(int i) {
        return (V) requireValues()[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Collection<V> collectionCreateValues = createValues();
        this.valuesView = collectionCreateValues;
        return collectionCreateValues;
    }

    public Iterator<V> valuesIterator() {
        Map<K, V> mapDelegateOrNull = delegateOrNull();
        return mapDelegateOrNull != null ? mapDelegateOrNull.values().iterator() : new CompactHashMap<K, V>.Itr<V>() { // from class: com.google.common.collect.CompactHashMap.3
            @Override // com.google.common.collect.CompactHashMap.Itr
            @ParametricNullness
            public V getOutput(int i) {
                return (V) CompactHashMap.access$600(CompactHashMap.this, i);
            }
        };
    }

    @J2ktIncompatible
    public final void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size());
        Iterator<Map.Entry<K, V>> itEntrySetIterator = entrySetIterator();
        while (itEntrySetIterator.hasNext()) {
            Map.Entry<K, V> next = itEntrySetIterator.next();
            stream.writeObject(next.getKey());
            stream.writeObject(next.getValue());
        }
    }

    public CompactHashMap(int expectedSize) {
        init(expectedSize);
    }

    public void accessEntry(int index) {
    }
}
