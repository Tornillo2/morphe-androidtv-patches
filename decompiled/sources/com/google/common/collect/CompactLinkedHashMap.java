package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    public static final int ENDPOINT = -2;
    public final boolean accessOrder;
    public transient int firstEntry;
    public transient int lastEntry;

    @VisibleForTesting
    public transient long[] links;

    public CompactLinkedHashMap() {
        this(3, false);
    }

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashMap<>(expectedSize, false);
    }

    @Override // com.google.common.collect.CompactHashMap
    public void accessEntry(int index) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(index), getSuccessor(index));
            setSucceeds(this.lastEntry, index);
            setSucceeds(index, -2);
            incrementModCount();
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= size() ? indexRemoved : indexBeforeRemove;
    }

    @Override // com.google.common.collect.CompactHashMap
    public int allocArrays() {
        int iAllocArrays = super.allocArrays();
        this.links = new long[iAllocArrays];
        return iAllocArrays;
    }

    @Override // com.google.common.collect.CompactHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = this.links;
        if (jArr != null) {
            Arrays.fill(jArr, 0, size(), 0L);
        }
        super.clear();
    }

    @Override // com.google.common.collect.CompactHashMap
    @CanIgnoreReturnValue
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> mapConvertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.links = null;
        return mapConvertToHashFloodingResistantImplementation;
    }

    @Override // com.google.common.collect.CompactHashMap
    public Map<K, V> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashMap(tableSize, 1.0f, this.accessOrder);
    }

    @Override // com.google.common.collect.CompactHashMap
    public int firstEntryIndex() {
        return this.firstEntry;
    }

    public final int getPredecessor(int entry) {
        return ((int) (requireLinks()[entry] >>> 32)) - 1;
    }

    @Override // com.google.common.collect.CompactHashMap
    public int getSuccessor(int entry) {
        return ((int) requireLinks()[entry]) - 1;
    }

    @Override // com.google.common.collect.CompactHashMap
    public void init(int expectedSize) {
        super.init(expectedSize);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashMap
    public void insertEntry(int entryIndex, @ParametricNullness K key, @ParametricNullness V value, int hash, int mask) {
        super.insertEntry(entryIndex, key, value, hash, mask);
        setSucceeds(this.lastEntry, entryIndex);
        setSucceeds(entryIndex, -2);
    }

    public final long link(int i) {
        return requireLinks()[i];
    }

    @Override // com.google.common.collect.CompactHashMap
    public void moveLastEntry(int dstIndex, int mask) {
        int size = size() - 1;
        super.moveLastEntry(dstIndex, mask);
        setSucceeds(getPredecessor(dstIndex), getSuccessor(dstIndex));
        if (dstIndex < size) {
            setSucceeds(getPredecessor(size), dstIndex);
            setSucceeds(dstIndex, getSuccessor(size));
        }
        setLink(size, 0L);
    }

    public final long[] requireLinks() {
        long[] jArr = this.links;
        Objects.requireNonNull(jArr);
        return jArr;
    }

    @Override // com.google.common.collect.CompactHashMap
    public void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        this.links = Arrays.copyOf(requireLinks(), newCapacity);
    }

    public final void setLink(int i, long value) {
        requireLinks()[i] = value;
    }

    public final void setPredecessor(int entry, int pred) {
        setLink(entry, (requireLinks()[entry] & 4294967295L) | (((long) (pred + 1)) << 32));
    }

    public final void setSucceeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            setSuccessor(pred, succ);
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            setPredecessor(succ, pred);
        }
    }

    public final void setSuccessor(int entry, int succ) {
        setLink(entry, (requireLinks()[entry] & ObjectCountHashMap.HASH_MASK) | (((long) (succ + 1)) & 4294967295L));
    }

    public CompactLinkedHashMap(int expectedSize) {
        this(expectedSize, false);
    }

    public CompactLinkedHashMap(int expectedSize, boolean accessOrder) {
        super(expectedSize);
        this.accessOrder = accessOrder;
    }
}
