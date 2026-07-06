package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,747:1\n1#2:748\n*E\n"})
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, KMutableMap {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final MapBuilder Empty;
    public static final int INITIAL_CAPACITY = 8;
    public static final int INITIAL_MAX_PROBE_DISTANCE = 2;
    public static final int MAGIC = -1640531527;
    public static final int TOMBSTONE = -1;

    @Nullable
    public MapBuilderEntries<K, V> entriesView;

    @NotNull
    public int[] hashArray;
    public int hashShift;
    public boolean isReadOnly;

    @NotNull
    public K[] keysArray;

    @Nullable
    public MapBuilderKeys<K> keysView;
    public int length;
    public int maxProbeDistance;
    public int modCount;

    @NotNull
    public int[] presenceArray;
    public int size;

    @Nullable
    public V[] valuesArray;

    @Nullable
    public MapBuilderValues<V> valuesView;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final int computeHashSize(int i) {
            if (i < 1) {
                i = 1;
            }
            return Integer.highestOneBit(i * 3);
        }

        public final int computeShift(int i) {
            return Integer.numberOfLeadingZeros(i) + 1;
        }

        @NotNull
        public final MapBuilder getEmpty$kotlin_stdlib() {
            return MapBuilder.Empty;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EntriesItr<K, V> extends Itr<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EntriesItr(@NotNull MapBuilder<K, V> map) {
            super(map);
            Intrinsics.checkNotNullParameter(map, "map");
        }

        public final void nextAppendString(@NotNull StringBuilder sb) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            if (this.index >= this.map.length) {
                throw new NoSuchElementException();
            }
            int i = this.index;
            this.index = i + 1;
            this.lastIndex = i;
            MapBuilder<K, V> mapBuilder = this.map;
            K k = mapBuilder.keysArray[i];
            if (k == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(k);
            }
            sb.append('=');
            V[] vArr = this.map.valuesArray;
            Intrinsics.checkNotNull(vArr);
            V v = vArr[this.lastIndex];
            if (v == this.map) {
                sb.append("(this Map)");
            } else {
                sb.append(v);
            }
            initNext$kotlin_stdlib();
        }

        public final int nextHashCode$kotlin_stdlib() {
            if (this.index >= this.map.length) {
                throw new NoSuchElementException();
            }
            int i = this.index;
            this.index = i + 1;
            this.lastIndex = i;
            K k = this.map.keysArray[i];
            int iHashCode = k != null ? k.hashCode() : 0;
            V[] vArr = this.map.valuesArray;
            Intrinsics.checkNotNull(vArr);
            V v = vArr[this.lastIndex];
            int iHashCode2 = iHashCode ^ (v != null ? v.hashCode() : 0);
            initNext$kotlin_stdlib();
            return iHashCode2;
        }

        @Override // java.util.Iterator
        @NotNull
        public EntryRef<K, V> next() {
            checkForComodification$kotlin_stdlib();
            int i = this.index;
            MapBuilder<K, V> mapBuilder = this.map;
            if (i >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            this.index = i + 1;
            this.lastIndex = i;
            EntryRef<K, V> entryRef = new EntryRef<>(mapBuilder, i);
            initNext$kotlin_stdlib();
            return entryRef;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EntryRef<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        public final int expectedModCount;
        public final int index;

        @NotNull
        public final MapBuilder<K, V> map;

        public EntryRef(@NotNull MapBuilder<K, V> map, int i) {
            Intrinsics.checkNotNullParameter(map, "map");
            this.map = map;
            this.index = i;
            this.expectedModCount = map.modCount;
        }

        private final void checkForComodification() {
            if (this.map.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
            }
        }

        @Override // java.util.Map.Entry
        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            checkForComodification();
            return this.map.keysArray[this.index];
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            checkForComodification();
            V[] vArr = this.map.valuesArray;
            Intrinsics.checkNotNull(vArr);
            return vArr[this.index];
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K key = getKey();
            int iHashCode = key != null ? key.hashCode() : 0;
            V value = getValue();
            return iHashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            checkForComodification();
            this.map.checkIsMutable$kotlin_stdlib();
            V[] vArrAllocateValuesArray = this.map.allocateValuesArray();
            int i = this.index;
            V v2 = vArrAllocateValuesArray[i];
            vArrAllocateValuesArray[i] = v;
            return v2;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,747:1\n1#2:748\n*E\n"})
    public static class Itr<K, V> {
        public int expectedModCount;
        public int index;
        public int lastIndex;

        @NotNull
        public final MapBuilder<K, V> map;

        public Itr(@NotNull MapBuilder<K, V> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            this.map = map;
            this.lastIndex = -1;
            this.expectedModCount = map.modCount;
            initNext$kotlin_stdlib();
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (this.map.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        @NotNull
        public final MapBuilder<K, V> getMap$kotlin_stdlib() {
            return this.map;
        }

        public final boolean hasNext() {
            return this.index < this.map.length;
        }

        public final void initNext$kotlin_stdlib() {
            while (this.index < this.map.length) {
                int[] iArr = this.map.presenceArray;
                int i = this.index;
                if (iArr[i] >= 0) {
                    return;
                } else {
                    this.index = i + 1;
                }
            }
        }

        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.");
            }
            this.map.checkIsMutable$kotlin_stdlib();
            this.map.removeEntryAt(this.lastIndex);
            this.lastIndex = -1;
            this.expectedModCount = this.map.modCount;
        }

        public final void setIndex$kotlin_stdlib(int i) {
            this.index = i;
        }

        public final void setLastIndex$kotlin_stdlib(int i) {
            this.lastIndex = i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class KeysItr<K, V> extends Itr<K, V> implements Iterator<K>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KeysItr(@NotNull MapBuilder<K, V> map) {
            super(map);
            Intrinsics.checkNotNullParameter(map, "map");
        }

        @Override // java.util.Iterator
        public K next() {
            checkForComodification$kotlin_stdlib();
            int i = this.index;
            MapBuilder<K, V> mapBuilder = this.map;
            if (i >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            this.index = i + 1;
            this.lastIndex = i;
            K k = mapBuilder.keysArray[i];
            initNext$kotlin_stdlib();
            return k;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ValuesItr<K, V> extends Itr<K, V> implements Iterator<V>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValuesItr(@NotNull MapBuilder<K, V> map) {
            super(map);
            Intrinsics.checkNotNullParameter(map, "map");
        }

        @Override // java.util.Iterator
        public V next() {
            checkForComodification$kotlin_stdlib();
            int i = this.index;
            MapBuilder<K, V> mapBuilder = this.map;
            if (i >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            this.index = i + 1;
            this.lastIndex = i;
            V[] vArr = mapBuilder.valuesArray;
            Intrinsics.checkNotNull(vArr);
            V v = vArr[this.lastIndex];
            initNext$kotlin_stdlib();
            return v;
        }
    }

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.isReadOnly = true;
        Empty = mapBuilder;
    }

    public MapBuilder(K[] kArr, V[] vArr, int[] iArr, int[] iArr2, int i, int i2) {
        this.keysArray = kArr;
        this.valuesArray = vArr;
        this.presenceArray = iArr;
        this.hashArray = iArr2;
        this.maxProbeDistance = i;
        this.length = i2;
        this.hashShift = Companion.computeShift(iArr2.length);
    }

    private final void ensureCapacity(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        K[] kArr = this.keysArray;
        if (i > kArr.length) {
            int iNewCapacity$kotlin_stdlib = AbstractList.Companion.newCapacity$kotlin_stdlib(kArr.length, i);
            this.keysArray = (K[]) ListBuilderKt.copyOfUninitializedElements(this.keysArray, iNewCapacity$kotlin_stdlib);
            V[] vArr = this.valuesArray;
            this.valuesArray = vArr != null ? (V[]) ListBuilderKt.copyOfUninitializedElements(vArr, iNewCapacity$kotlin_stdlib) : null;
            int[] iArrCopyOf = Arrays.copyOf(this.presenceArray, iNewCapacity$kotlin_stdlib);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
            this.presenceArray = iArrCopyOf;
            int iComputeHashSize = Companion.computeHashSize(iNewCapacity$kotlin_stdlib);
            if (iComputeHashSize > this.hashArray.length) {
                rehash(iComputeHashSize);
            }
        }
    }

    private final void ensureExtraCapacity(int i) {
        if (shouldCompact(i)) {
            compact(true);
        } else {
            ensureCapacity(this.length + i);
        }
    }

    private final void registerModification() {
        this.modCount++;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int iHash = hash(k);
            int i = this.maxProbeDistance * 2;
            int length = this.hashArray.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.hashArray;
                int i3 = iArr[iHash];
                if (i3 <= 0) {
                    int i4 = this.length;
                    K[] kArr = this.keysArray;
                    if (i4 < kArr.length) {
                        int i5 = i4 + 1;
                        this.length = i5;
                        kArr[i4] = k;
                        this.presenceArray[i4] = iHash;
                        iArr[iHash] = i5;
                        this.size++;
                        registerModification();
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                    ensureExtraCapacity(1);
                } else {
                    if (Intrinsics.areEqual(this.keysArray[i3 - 1], k)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        rehash(this.hashArray.length * 2);
                        break;
                    }
                    iHash = iHash == 0 ? this.hashArray.length - 1 : iHash - 1;
                }
            }
        }
    }

    public final V[] allocateValuesArray() {
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            return vArr;
        }
        V[] vArr2 = (V[]) ListBuilderKt.arrayOfUninitializedElements(this.keysArray.length);
        this.valuesArray = vArr2;
        return vArr2;
    }

    @NotNull
    public final Map<K, V> build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if (this.size > 0) {
            return this;
        }
        MapBuilder mapBuilder = Empty;
        Intrinsics.checkNotNull(mapBuilder, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return mapBuilder;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public void clear() {
        checkIsMutable$kotlin_stdlib();
        int i = this.length - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.presenceArray;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    this.hashArray[i3] = 0;
                    iArr[i2] = -1;
                }
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            ListBuilderKt.resetRange(vArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
        registerModification();
    }

    public final void compact(boolean z) {
        int i;
        V[] vArr = this.valuesArray;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.length;
            if (i2 >= i) {
                break;
            }
            int[] iArr = this.presenceArray;
            int i4 = iArr[i2];
            if (i4 >= 0) {
                K[] kArr = this.keysArray;
                kArr[i3] = kArr[i2];
                if (vArr != null) {
                    vArr[i3] = vArr[i2];
                }
                if (z) {
                    iArr[i3] = i4;
                    this.hashArray[i4] = i3 + 1;
                }
                i3++;
            }
            i2++;
        }
        ListBuilderKt.resetRange(this.keysArray, i3, i);
        if (vArr != null) {
            ListBuilderKt.resetRange(vArr, i3, this.length);
        }
        this.length = i3;
    }

    public final boolean containsAllEntries$kotlin_stdlib(@NotNull Collection<?> m) {
        Intrinsics.checkNotNullParameter(m, "m");
        for (Object obj : m) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        int iFindKey = findKey(entry.getKey());
        if (iFindKey < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.checkNotNull(vArr);
        return Intrinsics.areEqual(vArr[iFindKey], entry.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return findKey(obj) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return findValue(obj) >= 0;
    }

    public final boolean contentEquals(Map<?, ?> map) {
        return this.size == map.size() && containsAllEntries$kotlin_stdlib(map.entrySet());
    }

    @NotNull
    public final EntriesItr<K, V> entriesIterator$kotlin_stdlib() {
        return new EntriesItr<>(this);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof Map) && contentEquals((Map) obj);
        }
        return true;
    }

    public final int findKey(K k) {
        int iHash = hash(k);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[iHash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(this.keysArray[i3], k)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iHash = iHash == 0 ? this.hashArray.length - 1 : iHash - 1;
        }
    }

    public final int findValue(V v) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                V[] vArr = this.valuesArray;
                Intrinsics.checkNotNull(vArr);
                if (Intrinsics.areEqual(vArr[i], v)) {
                    return i;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Nullable
    public V get(Object obj) {
        int iFindKey = findKey(obj);
        if (iFindKey < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.checkNotNull(vArr);
        return vArr[iFindKey];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        MapBuilderEntries<K, V> mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries != null) {
            return mapBuilderEntries;
        }
        MapBuilderEntries<K, V> mapBuilderEntries2 = new MapBuilderEntries<>(this);
        this.entriesView = mapBuilderEntries2;
        return mapBuilderEntries2;
    }

    public final int getHashSize() {
        return this.hashArray.length;
    }

    @NotNull
    public Set<K> getKeys() {
        MapBuilderKeys<K> mapBuilderKeys = this.keysView;
        if (mapBuilderKeys != null) {
            return mapBuilderKeys;
        }
        MapBuilderKeys<K> mapBuilderKeys2 = new MapBuilderKeys<>(this);
        this.keysView = mapBuilderKeys2;
        return mapBuilderKeys2;
    }

    public int getSize() {
        return this.size;
    }

    @NotNull
    public Collection<V> getValues() {
        MapBuilderValues<V> mapBuilderValues = this.valuesView;
        if (mapBuilderValues != null) {
            return mapBuilderValues;
        }
        MapBuilderValues<V> mapBuilderValues2 = new MapBuilderValues<>(this);
        this.valuesView = mapBuilderValues2;
        return mapBuilderValues2;
    }

    public final int hash(K k) {
        return ((k != null ? k.hashCode() : 0) * (-1640531527)) >>> this.hashShift;
    }

    @Override // java.util.Map
    public int hashCode() {
        EntriesItr entriesItr = new EntriesItr(this);
        int iNextHashCode$kotlin_stdlib = 0;
        while (entriesItr.hasNext()) {
            iNextHashCode$kotlin_stdlib += entriesItr.nextHashCode$kotlin_stdlib();
        }
        return iNextHashCode$kotlin_stdlib;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @NotNull
    public final KeysItr<K, V> keysIterator$kotlin_stdlib() {
        return new KeysItr<>(this);
    }

    @Override // java.util.Map
    @Nullable
    public V put(K k, V v) {
        checkIsMutable$kotlin_stdlib();
        int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(k);
        V[] vArrAllocateValuesArray = allocateValuesArray();
        if (iAddKey$kotlin_stdlib >= 0) {
            vArrAllocateValuesArray[iAddKey$kotlin_stdlib] = v;
            return null;
        }
        int i = (-iAddKey$kotlin_stdlib) - 1;
        V v2 = vArrAllocateValuesArray[i];
        vArrAllocateValuesArray[i] = v;
        return v2;
    }

    @Override // java.util.Map
    public void putAll(@NotNull Map<? extends K, ? extends V> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        checkIsMutable$kotlin_stdlib();
        putAllEntries(from.entrySet());
    }

    public final boolean putAllEntries(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        boolean z = false;
        if (collection.isEmpty()) {
            return false;
        }
        ensureExtraCapacity(collection.size());
        Iterator<? extends Map.Entry<? extends K, ? extends V>> it = collection.iterator();
        while (it.hasNext()) {
            if (putEntry(it.next())) {
                z = true;
            }
        }
        return z;
    }

    public final boolean putEntry(Map.Entry<? extends K, ? extends V> entry) {
        int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
        V[] vArrAllocateValuesArray = allocateValuesArray();
        if (iAddKey$kotlin_stdlib >= 0) {
            vArrAllocateValuesArray[iAddKey$kotlin_stdlib] = entry.getValue();
            return true;
        }
        int i = (-iAddKey$kotlin_stdlib) - 1;
        if (Intrinsics.areEqual(entry.getValue(), vArrAllocateValuesArray[i])) {
            return false;
        }
        vArrAllocateValuesArray[i] = entry.getValue();
        return true;
    }

    public final boolean putRehash(int i) {
        int iHash = hash(this.keysArray[i]);
        int i2 = this.maxProbeDistance;
        while (true) {
            int[] iArr = this.hashArray;
            if (iArr[iHash] == 0) {
                iArr[iHash] = i + 1;
                this.presenceArray[i] = iHash;
                return true;
            }
            i2--;
            if (i2 < 0) {
                return false;
            }
            iHash = iHash == 0 ? iArr.length - 1 : iHash - 1;
        }
    }

    public final void rehash(int i) {
        registerModification();
        int i2 = 0;
        if (this.length > this.size) {
            compact(false);
        }
        this.hashArray = new int[i];
        this.hashShift = Companion.computeShift(i);
        while (i2 < this.length) {
            int i3 = i2 + 1;
            if (!putRehash(i2)) {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
            i2 = i3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Nullable
    public V remove(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int iFindKey = findKey(obj);
        if (iFindKey < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.checkNotNull(vArr);
        V v = vArr[iFindKey];
        removeEntryAt(iFindKey);
        return v;
    }

    public final boolean removeEntry$kotlin_stdlib(@NotNull Map.Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int iFindKey = findKey(entry.getKey());
        if (iFindKey < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.checkNotNull(vArr);
        if (!Intrinsics.areEqual(vArr[iFindKey], entry.getValue())) {
            return false;
        }
        removeEntryAt(iFindKey);
        return true;
    }

    public final void removeEntryAt(int i) {
        ListBuilderKt.resetAt(this.keysArray, i);
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            vArr[i] = null;
        }
        removeHashAt(this.presenceArray[i]);
        this.presenceArray[i] = -1;
        this.size--;
        registerModification();
    }

    public final void removeHashAt(int i) {
        int i2 = this.maxProbeDistance * 2;
        int length = this.hashArray.length / 2;
        if (i2 > length) {
            i2 = length;
        }
        int i3 = i2;
        int i4 = 0;
        int i5 = i;
        do {
            i = i == 0 ? this.hashArray.length - 1 : i - 1;
            i4++;
            if (i4 > this.maxProbeDistance) {
                this.hashArray[i5] = 0;
                return;
            }
            int[] iArr = this.hashArray;
            int i6 = iArr[i];
            if (i6 == 0) {
                iArr[i5] = 0;
                return;
            }
            if (i6 < 0) {
                iArr[i5] = -1;
            } else {
                int i7 = i6 - 1;
                int iHash = hash(this.keysArray[i7]) - i;
                int[] iArr2 = this.hashArray;
                if ((iHash & (iArr2.length - 1)) >= i4) {
                    iArr2[i5] = i6;
                    this.presenceArray[i7] = i5;
                }
                i3--;
            }
            i5 = i;
            i4 = 0;
            i3--;
        } while (i3 >= 0);
        this.hashArray[i5] = -1;
    }

    public final boolean removeKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        int iFindKey = findKey(k);
        if (iFindKey < 0) {
            return false;
        }
        removeEntryAt(iFindKey);
        return true;
    }

    public final boolean removeValue$kotlin_stdlib(V v) {
        checkIsMutable$kotlin_stdlib();
        int iFindValue = findValue(v);
        if (iFindValue < 0) {
            return false;
        }
        removeEntryAt(iFindValue);
        return true;
    }

    public final boolean shouldCompact(int i) {
        K[] kArr = this.keysArray;
        int length = kArr.length;
        int i2 = this.length;
        int i3 = length - i2;
        int i4 = i2 - this.size;
        return i3 < i && i3 + i4 >= i && i4 >= kArr.length / 4;
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder((this.size * 3) + 2);
        sb.append("{");
        EntriesItr entriesItr = new EntriesItr(this);
        int i = 0;
        while (entriesItr.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            entriesItr.nextAppendString(sb);
            i++;
        }
        sb.append("}");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    @NotNull
    public final ValuesItr<K, V> valuesIterator$kotlin_stdlib() {
        return new ValuesItr<>(this);
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int i) {
        this(ListBuilderKt.arrayOfUninitializedElements(i), null, new int[i], new int[Companion.computeHashSize(i)], 2, 0);
    }
}
