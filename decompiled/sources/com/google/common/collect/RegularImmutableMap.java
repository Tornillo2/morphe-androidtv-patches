package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import j$.util.Objects;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.UShort;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    public static final byte ABSENT = -1;
    public static final int BYTE_MASK = 255;
    public static final int BYTE_MAX_SIZE = 128;
    public static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    public static final int SHORT_MASK = 65535;
    public static final int SHORT_MAX_SIZE = 32768;

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @VisibleForTesting
    public final transient Object[] alternatingKeysAndValues;
    public final transient Object hashTable;
    public final transient int size;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient Object[] alternatingKeysAndValues;
        public final transient int keyOffset;
        public final transient ImmutableMap<K, V> map;
        public final transient int size;

        public EntrySet(ImmutableMap<K, V> map, Object[] alternatingKeysAndValues, int keyOffset, int size) {
            this.map = map;
            this.alternatingKeysAndValues = alternatingKeysAndValues;
            this.keyOffset = keyOffset;
            this.size = size;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            if (object instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) object;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value != null && value.equals(this.map.get(key))) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] dst, int offset) {
            return asList().copyIntoArray(dst, offset);
        }

        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return EntrySet.this.size;
                }

                @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
                @J2ktIncompatible
                public Object writeReplace() {
                    return super.writeReplace();
                }

                @Override // java.util.List
                public Map.Entry<K, V> get(int index) {
                    Preconditions.checkElementIndex(index, EntrySet.this.size);
                    EntrySet entrySet = EntrySet.this;
                    int i = index * 2;
                    Object obj = entrySet.alternatingKeysAndValues[entrySet.keyOffset + i];
                    Objects.requireNonNull(obj);
                    EntrySet entrySet2 = EntrySet.this;
                    Object obj2 = entrySet2.alternatingKeysAndValues[i + (entrySet2.keyOffset ^ 1)];
                    Objects.requireNonNull(obj2);
                    return new AbstractMap.SimpleImmutableEntry(obj, obj2);
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient Object[] alternatingKeysAndValues;
        public final transient int offset;
        public final transient int size;

        public KeysOrValuesAsList(Object[] alternatingKeysAndValues, int offset, int size) {
            this.alternatingKeysAndValues = alternatingKeysAndValues;
            this.offset = offset;
            this.size = size;
        }

        @Override // java.util.List
        public Object get(int index) {
            Preconditions.checkElementIndex(index, this.size);
            Object obj = this.alternatingKeysAndValues[(index * 2) + this.offset];
            Objects.requireNonNull(obj);
            return obj;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    public RegularImmutableMap(Object hashTable, Object[] alternatingKeysAndValues, int size) {
        this.hashTable = hashTable;
        this.alternatingKeysAndValues = alternatingKeysAndValues;
        this.size = size;
    }

    public static <K, V> RegularImmutableMap<K, V> create(int n, Object[] alternatingKeysAndValues) {
        return create(n, alternatingKeysAndValues, null);
    }

    public static Object createHashTable(Object[] alternatingKeysAndValues, int n, int tableSize, int keyOffset) {
        ImmutableMap.Builder.DuplicateKey duplicateKey = null;
        int i = 1;
        if (n == 1) {
            Objects.requireNonNull(alternatingKeysAndValues[keyOffset]);
            Objects.requireNonNull(alternatingKeysAndValues[keyOffset ^ 1]);
            return null;
        }
        int i2 = tableSize - 1;
        if (tableSize <= 128) {
            byte[] bArr = new byte[tableSize];
            Arrays.fill(bArr, (byte) -1);
            int i3 = 0;
            for (int i4 = 0; i4 < n; i4++) {
                int i5 = (i4 * 2) + keyOffset;
                int i6 = (i3 * 2) + keyOffset;
                Object obj = alternatingKeysAndValues[i5];
                Objects.requireNonNull(obj);
                Object obj2 = alternatingKeysAndValues[i5 ^ 1];
                Objects.requireNonNull(obj2);
                int iSmear = Hashing.smear(obj.hashCode());
                while (true) {
                    int i7 = iSmear & i2;
                    int i8 = bArr[i7] & 255;
                    if (i8 == 255) {
                        bArr[i7] = (byte) i6;
                        if (i3 < i4) {
                            alternatingKeysAndValues[i6] = obj;
                            alternatingKeysAndValues[i6 ^ 1] = obj2;
                        }
                        i3++;
                    } else {
                        if (obj.equals(alternatingKeysAndValues[i8])) {
                            int i9 = i8 ^ 1;
                            Object obj3 = alternatingKeysAndValues[i9];
                            Objects.requireNonNull(obj3);
                            duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj, obj2, obj3);
                            alternatingKeysAndValues[i9] = obj2;
                            break;
                        }
                        iSmear = i7 + 1;
                    }
                }
            }
            return i3 == n ? bArr : new Object[]{bArr, Integer.valueOf(i3), duplicateKey};
        }
        if (tableSize <= 32768) {
            short[] sArr = new short[tableSize];
            Arrays.fill(sArr, (short) -1);
            int i10 = 0;
            for (int i11 = 0; i11 < n; i11++) {
                int i12 = (i11 * 2) + keyOffset;
                int i13 = (i10 * 2) + keyOffset;
                Object obj4 = alternatingKeysAndValues[i12];
                Objects.requireNonNull(obj4);
                Object obj5 = alternatingKeysAndValues[i12 ^ 1];
                Objects.requireNonNull(obj5);
                int iSmear2 = Hashing.smear(obj4.hashCode());
                while (true) {
                    int i14 = iSmear2 & i2;
                    int i15 = sArr[i14] & UShort.MAX_VALUE;
                    if (i15 == 65535) {
                        sArr[i14] = (short) i13;
                        if (i10 < i11) {
                            alternatingKeysAndValues[i13] = obj4;
                            alternatingKeysAndValues[i13 ^ 1] = obj5;
                        }
                        i10++;
                    } else {
                        if (obj4.equals(alternatingKeysAndValues[i15])) {
                            int i16 = i15 ^ 1;
                            Object obj6 = alternatingKeysAndValues[i16];
                            Objects.requireNonNull(obj6);
                            duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj4, obj5, obj6);
                            alternatingKeysAndValues[i16] = obj5;
                            break;
                        }
                        iSmear2 = i14 + 1;
                    }
                }
            }
            return i10 == n ? sArr : new Object[]{sArr, Integer.valueOf(i10), duplicateKey};
        }
        int[] iArr = new int[tableSize];
        Arrays.fill(iArr, -1);
        int i17 = 0;
        int i18 = 0;
        while (i17 < n) {
            int i19 = (i17 * 2) + keyOffset;
            int i20 = (i18 * 2) + keyOffset;
            Object obj7 = alternatingKeysAndValues[i19];
            Objects.requireNonNull(obj7);
            Object obj8 = alternatingKeysAndValues[i19 ^ i];
            Objects.requireNonNull(obj8);
            int iSmear3 = Hashing.smear(obj7.hashCode());
            while (true) {
                int i21 = iSmear3 & i2;
                int i22 = iArr[i21];
                if (i22 == -1) {
                    iArr[i21] = i20;
                    if (i18 < i17) {
                        alternatingKeysAndValues[i20] = obj7;
                        alternatingKeysAndValues[i20 ^ 1] = obj8;
                    }
                    i18++;
                } else {
                    if (obj7.equals(alternatingKeysAndValues[i22])) {
                        int i23 = i22 ^ 1;
                        Object obj9 = alternatingKeysAndValues[i23];
                        Objects.requireNonNull(obj9);
                        duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj7, obj8, obj9);
                        alternatingKeysAndValues[i23] = obj8;
                        break;
                    }
                    iSmear3 = i21 + 1;
                }
            }
            i17++;
            i = 1;
        }
        return i18 == n ? iArr : new Object[]{iArr, Integer.valueOf(i18), duplicateKey};
    }

    public static Object createHashTableOrThrow(Object[] alternatingKeysAndValues, int n, int tableSize, int keyOffset) {
        Object objCreateHashTable = createHashTable(alternatingKeysAndValues, n, tableSize, keyOffset);
        if (objCreateHashTable instanceof Object[]) {
            throw ((ImmutableMap.Builder.DuplicateKey) ((Object[]) objCreateHashTable)[2]).exception();
        }
        return objCreateHashTable;
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        V v = (V) get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
        if (v == null) {
            return null;
        }
        return v;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMap
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return new ImmutableMap.SerializedForm(this);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList<K> list;
        public final transient ImmutableMap<K, ?> map;

        public KeySet(ImmutableMap<K, ?> map, ImmutableList<K> list) {
            this.map = map;
            this.list = list;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            return this.map.get(object) != null;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] dst, int offset) {
            return this.list.copyIntoArray(dst, offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<K> iterator() {
            return this.list.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return this.list.iterator();
        }
    }

    public static <K, V> RegularImmutableMap<K, V> create(int n, Object[] alternatingKeysAndValues, ImmutableMap.Builder<K, V> builder) {
        if (n == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (n == 1) {
            Objects.requireNonNull(alternatingKeysAndValues[0]);
            Objects.requireNonNull(alternatingKeysAndValues[1]);
            return new RegularImmutableMap<>(null, alternatingKeysAndValues, 1);
        }
        Preconditions.checkPositionIndex(n, alternatingKeysAndValues.length >> 1);
        Object objCreateHashTable = createHashTable(alternatingKeysAndValues, n, ImmutableSet.chooseTableSize(n), 0);
        if (objCreateHashTable instanceof Object[]) {
            Object[] objArr = (Object[]) objCreateHashTable;
            ImmutableMap.Builder.DuplicateKey duplicateKey = (ImmutableMap.Builder.DuplicateKey) objArr[2];
            if (builder == null) {
                throw duplicateKey.exception();
            }
            builder.duplicateKey = duplicateKey;
            Object obj = objArr[0];
            int iIntValue = ((Integer) objArr[1]).intValue();
            alternatingKeysAndValues = Arrays.copyOf(alternatingKeysAndValues, iIntValue * 2);
            objCreateHashTable = obj;
            n = iIntValue;
        }
        return new RegularImmutableMap<>(objCreateHashTable, alternatingKeysAndValues, n);
    }

    public static Object get(Object hashTableObject, Object[] alternatingKeysAndValues, int size, int keyOffset, Object key) {
        if (key == null) {
            return null;
        }
        if (size == 1) {
            Object obj = alternatingKeysAndValues[keyOffset];
            Objects.requireNonNull(obj);
            if (!obj.equals(key)) {
                return null;
            }
            Object obj2 = alternatingKeysAndValues[keyOffset ^ 1];
            Objects.requireNonNull(obj2);
            return obj2;
        }
        if (hashTableObject == null) {
            return null;
        }
        if (hashTableObject instanceof byte[]) {
            byte[] bArr = (byte[]) hashTableObject;
            int length = bArr.length - 1;
            int iSmear = Hashing.smear(key.hashCode());
            while (true) {
                int i = iSmear & length;
                int i2 = bArr[i] & 255;
                if (i2 == 255) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i2])) {
                    return alternatingKeysAndValues[i2 ^ 1];
                }
                iSmear = i + 1;
            }
        } else if (hashTableObject instanceof short[]) {
            short[] sArr = (short[]) hashTableObject;
            int length2 = sArr.length - 1;
            int iSmear2 = Hashing.smear(key.hashCode());
            while (true) {
                int i3 = iSmear2 & length2;
                int i4 = sArr[i3] & UShort.MAX_VALUE;
                if (i4 == 65535) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i4])) {
                    return alternatingKeysAndValues[i4 ^ 1];
                }
                iSmear2 = i3 + 1;
            }
        } else {
            int[] iArr = (int[]) hashTableObject;
            int length3 = iArr.length - 1;
            int iSmear3 = Hashing.smear(key.hashCode());
            while (true) {
                int i5 = iSmear3 & length3;
                int i6 = iArr[i5];
                if (i6 == -1) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i6])) {
                    return alternatingKeysAndValues[i6 ^ 1];
                }
                iSmear3 = i5 + 1;
            }
        }
    }
}
