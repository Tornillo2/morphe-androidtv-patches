package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.jspecify.annotations.NullMarked;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
@NullMarked
public class ObjectCountHashMap<K> {
    public static final float DEFAULT_LOAD_FACTOR = 1.0f;
    public static final int DEFAULT_SIZE = 3;
    public static final long HASH_MASK = -4294967296L;
    public static final int MAXIMUM_CAPACITY = 1073741824;
    public static final long NEXT_MASK = 4294967295L;
    public static final int UNSET = -1;

    @VisibleForTesting
    public transient long[] entries;
    public transient Object[] keys;
    public transient float loadFactor;
    public transient int modCount;
    public transient int size;
    public transient int[] table;
    public transient int threshold;
    public transient int[] values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class MapEntry extends Multisets.AbstractEntry<K> {

        @ParametricNullness
        public final K key;
        public int lastKnownIndex;

        public MapEntry(int i) {
            this.key = (K) ObjectCountHashMap.this.keys[i];
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[i];
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public K getElement() {
            return this.key;
        }

        @CanIgnoreReturnValue
        public int setCount(int count) {
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                ObjectCountHashMap.this.put(this.key, count);
                return 0;
            }
            int[] iArr = ObjectCountHashMap.this.values;
            int i2 = iArr[i];
            iArr[i] = count;
            return i2;
        }

        public void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i == -1 || i >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }
    }

    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int expectedSize) {
        return new ObjectCountHashMap<>(expectedSize);
    }

    public static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    public static int getNext(long entry) {
        return (int) entry;
    }

    public static long[] newEntries(int size) {
        long[] jArr = new long[size];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    public static int[] newTable(int size) {
        int[] iArr = new int[size];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public static long swapNext(long entry, int newNext) {
        return (entry & HASH_MASK) | (4294967295L & ((long) newNext));
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    public boolean containsKey(Object key) {
        return indexOf(key) != -1;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > this.entries.length) {
            resizeEntries(minCapacity);
        }
        if (minCapacity >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(minCapacity - 1) << 1));
        }
    }

    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    public int get(Object key) {
        int iIndexOf = indexOf(key);
        if (iIndexOf == -1) {
            return 0;
        }
        return this.values[iIndexOf];
    }

    public Multiset.Entry<K> getEntry(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return new MapEntry(index);
    }

    @ParametricNullness
    public K getKey(int i) {
        Preconditions.checkElementIndex(i, this.size);
        return (K) this.keys[i];
    }

    public int getValue(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return this.values[index];
    }

    public final int hashTableMask() {
        return this.table.length - 1;
    }

    public int indexOf(Object key) {
        int iSmearedHash = Hashing.smearedHash(key);
        int i = this.table[hashTableMask() & iSmearedHash];
        while (i != -1) {
            long j = this.entries[i];
            if (((int) (j >>> 32)) == iSmearedHash && Objects.equal(key, this.keys[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    public void init(int expectedSize, float loadFactor) {
        Preconditions.checkArgument(expectedSize >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(loadFactor > 0.0f, "Illegal load factor");
        int iClosedTableSize = Hashing.closedTableSize(expectedSize, loadFactor);
        this.table = newTable(iClosedTableSize);
        this.loadFactor = loadFactor;
        this.keys = new Object[expectedSize];
        this.values = new int[expectedSize];
        this.entries = newEntries(expectedSize);
        this.threshold = Math.max(1, (int) (iClosedTableSize * loadFactor));
    }

    public void insertEntry(int entryIndex, @ParametricNullness K key, int value, int hash) {
        this.entries[entryIndex] = (((long) hash) << 32) | 4294967295L;
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
    }

    public void moveLastEntry(int dstIndex) {
        int size = size() - 1;
        if (dstIndex >= size) {
            this.keys[dstIndex] = null;
            this.values[dstIndex] = 0;
            this.entries[dstIndex] = -1;
            return;
        }
        Object[] objArr = this.keys;
        objArr[dstIndex] = objArr[size];
        int[] iArr = this.values;
        iArr[dstIndex] = iArr[size];
        objArr[size] = null;
        iArr[size] = 0;
        long[] jArr = this.entries;
        long j = jArr[size];
        jArr[dstIndex] = j;
        jArr[size] = -1;
        int iHashTableMask = hashTableMask() & ((int) (j >>> 32));
        int[] iArr2 = this.table;
        int i = iArr2[iHashTableMask];
        if (i == size) {
            iArr2[iHashTableMask] = dstIndex;
            return;
        }
        while (true) {
            long[] jArr2 = this.entries;
            long j2 = jArr2[i];
            int i2 = (int) j2;
            if (i2 == size) {
                jArr2[i] = swapNext(j2, dstIndex);
                return;
            }
            i = i2;
        }
    }

    public int nextIndex(int index) {
        int i = index + 1;
        if (i < this.size) {
            return i;
        }
        return -1;
    }

    public int nextIndexAfterRemove(int oldNextIndex, int removedIndex) {
        return oldNextIndex - 1;
    }

    @CanIgnoreReturnValue
    public int put(@ParametricNullness K key, int value) {
        CollectPreconditions.checkPositive(value, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int iSmearedHash = Hashing.smearedHash(key);
        int iHashTableMask = hashTableMask() & iSmearedHash;
        int i = this.size;
        int[] iArr2 = this.table;
        int i2 = iArr2[iHashTableMask];
        if (i2 == -1) {
            iArr2[iHashTableMask] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (((int) (j >>> 32)) == iSmearedHash && Objects.equal(key, objArr[i2])) {
                    int i3 = iArr[i2];
                    iArr[i2] = value;
                    return i3;
                }
                int i4 = (int) j;
                if (i4 == -1) {
                    jArr[i2] = swapNext(j, i);
                    break;
                }
                i2 = i4;
            }
        }
        if (i == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i5 = i + 1;
        resizeMeMaybe(i5);
        insertEntry(i, key, value, iSmearedHash);
        this.size = i5;
        if (i >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return 0;
    }

    @CanIgnoreReturnValue
    public int remove(Object key) {
        return remove(key, Hashing.smearedHash(key));
    }

    @CanIgnoreReturnValue
    public int removeEntry(int entryIndex) {
        return remove(this.keys[entryIndex], (int) (this.entries[entryIndex] >>> 32));
    }

    public void resizeEntries(int newCapacity) {
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, newCapacity);
        if (newCapacity > length) {
            Arrays.fill(jArrCopyOf, length, newCapacity, -1L);
        }
        this.entries = jArrCopyOf;
    }

    public final void resizeMeMaybe(int newSize) {
        int length = this.entries.length;
        if (newSize > length) {
            int iMax = Math.max(1, length >>> 1) + length;
            if (iMax < 0) {
                iMax = Integer.MAX_VALUE;
            }
            if (iMax != length) {
                resizeEntries(iMax);
            }
        }
    }

    public final void resizeTable(int newCapacity) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i = ((int) (newCapacity * this.loadFactor)) + 1;
        int[] iArrNewTable = newTable(newCapacity);
        long[] jArr = this.entries;
        int length = iArrNewTable.length - 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            int i3 = (int) (jArr[i2] >>> 32);
            int i4 = i3 & length;
            int i5 = iArrNewTable[i4];
            iArrNewTable[i4] = i2;
            jArr[i2] = (((long) i3) << 32) | (((long) i5) & 4294967295L);
        }
        this.threshold = i;
        this.table = iArrNewTable;
    }

    public void setValue(int index, int newValue) {
        Preconditions.checkElementIndex(index, this.size);
        this.values[index] = newValue;
    }

    public int size() {
        return this.size;
    }

    public final int remove(Object key, int hash) {
        int iHashTableMask = hashTableMask() & hash;
        int i = this.table[iHashTableMask];
        if (i == -1) {
            return 0;
        }
        int i2 = -1;
        while (true) {
            if (((int) (this.entries[i] >>> 32)) == hash && Objects.equal(key, this.keys[i])) {
                int i3 = this.values[i];
                if (i2 == -1) {
                    this.table[iHashTableMask] = (int) this.entries[i];
                } else {
                    long[] jArr = this.entries;
                    jArr[i2] = swapNext(jArr[i2], (int) jArr[i]);
                }
                moveLastEntry(i);
                this.size--;
                this.modCount++;
                return i3;
            }
            int i4 = (int) this.entries[i];
            if (i4 == -1) {
                return 0;
            }
            i2 = i;
            i = i4;
        }
    }

    public ObjectCountHashMap(ObjectCountHashMap<? extends K> map) {
        init(map.size(), 1.0f);
        int iFirstIndex = map.firstIndex();
        while (iFirstIndex != -1) {
            put(map.getKey(iFirstIndex), map.getValue(iFirstIndex));
            iFirstIndex = map.nextIndex(iFirstIndex);
        }
    }

    public ObjectCountHashMap(int capacity) {
        this(capacity, 1.0f);
    }

    public ObjectCountHashMap(int expectedSize, float loadFactor) {
        init(expectedSize, loadFactor);
    }
}
