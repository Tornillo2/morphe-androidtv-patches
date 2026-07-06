package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    public static final int ENDPOINT = -2;
    public transient int firstEntry;
    public transient int lastEntry;
    public transient int[] predecessor;
    public transient int[] successor;

    public CompactLinkedHashSet() {
    }

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashSet<>(expectedSize);
    }

    @Override // com.google.common.collect.CompactHashSet
    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= size() ? indexRemoved : indexBeforeRemove;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int allocArrays() {
        int iAllocArrays = super.allocArrays();
        this.predecessor = new int[iAllocArrays];
        this.successor = new int[iAllocArrays];
        return iAllocArrays;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        int[] iArr = this.predecessor;
        if (iArr != null && this.successor != null) {
            Arrays.fill(iArr, 0, size(), 0);
            Arrays.fill(this.successor, 0, size(), 0);
        }
        super.clear();
    }

    @Override // com.google.common.collect.CompactHashSet
    @CanIgnoreReturnValue
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> setConvertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return setConvertToHashFloodingResistantImplementation;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int firstEntryIndex() {
        return this.firstEntry;
    }

    public final int getPredecessor(int entry) {
        return requirePredecessors()[entry] - 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int getSuccessor(int entry) {
        return requireSuccessors()[entry] - 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void init(int expectedSize) {
        super.init(expectedSize);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void insertEntry(int entryIndex, @ParametricNullness E object, int hash, int mask) {
        super.insertEntry(entryIndex, object, hash, mask);
        setSucceeds(this.lastEntry, entryIndex);
        setSucceeds(entryIndex, -2);
    }

    @Override // com.google.common.collect.CompactHashSet
    public void moveLastEntry(int dstIndex, int mask) {
        int size = size() - 1;
        super.moveLastEntry(dstIndex, mask);
        setSucceeds(getPredecessor(dstIndex), getSuccessor(dstIndex));
        if (dstIndex < size) {
            setSucceeds(getPredecessor(size), dstIndex);
            setSucceeds(dstIndex, getSuccessor(size));
        }
        requirePredecessors()[size] = 0;
        requireSuccessors()[size] = 0;
    }

    public final int[] requirePredecessors() {
        int[] iArr = this.predecessor;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    public final int[] requireSuccessors() {
        int[] iArr = this.successor;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        this.predecessor = Arrays.copyOf(requirePredecessors(), newCapacity);
        this.successor = Arrays.copyOf(requireSuccessors(), newCapacity);
    }

    public final void setPredecessor(int entry, int pred) {
        requirePredecessors()[entry] = pred + 1;
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
        requireSuccessors()[entry] = succ + 1;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        ObjectArrays.fillArray(this, objArr);
        return objArr;
    }

    public CompactLinkedHashSet(int expectedSize) {
        super(expectedSize);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> compactLinkedHashSet = new CompactLinkedHashSet<>(collection.size());
        compactLinkedHashSet.addAll(collection);
        return compactLinkedHashSet;
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E... elements) {
        CompactLinkedHashSet<E> compactLinkedHashSet = new CompactLinkedHashSet<>(elements.length);
        Collections.addAll(compactLinkedHashSet, elements);
        return compactLinkedHashSet;
    }
}
