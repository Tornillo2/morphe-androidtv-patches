package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {

    @VisibleForTesting
    public static final double HASH_FLOODING_FPP = 0.001d;
    public static final int MAX_HASH_BUCKET_LENGTH = 9;

    @VisibleForTesting
    public transient Object[] elements;
    public transient int[] entries;
    public transient int metadata;
    public transient int size;
    public transient Object table;

    public CompactHashSet() {
        init(3);
    }

    public static Object access$100(CompactHashSet compactHashSet, int i) {
        return compactHashSet.requireElements()[i];
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<>(expectedSize);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(@ParametricNullness E object) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            return setDelegateOrNull.add(object);
        }
        int[] iArrRequireEntries = requireEntries();
        Object[] objArrRequireElements = requireElements();
        int i = this.size;
        int i2 = i + 1;
        int iSmearedHash = Hashing.smearedHash(object);
        int iHashTableMask = hashTableMask();
        int i3 = iSmearedHash & iHashTableMask;
        Object obj = this.table;
        Objects.requireNonNull(obj);
        int iTableGet = CompactHashing.tableGet(obj, i3);
        if (iTableGet != 0) {
            int i4 = ~iHashTableMask;
            int i5 = iSmearedHash & i4;
            int i6 = 0;
            while (true) {
                int i7 = iTableGet - 1;
                int i8 = iArrRequireEntries[i7];
                if ((i8 & i4) == i5 && com.google.common.base.Objects.equal(object, objArrRequireElements[i7])) {
                    return false;
                }
                int i9 = i8 & iHashTableMask;
                i6++;
                if (i9 != 0) {
                    iTableGet = i9;
                } else {
                    if (i6 >= 9) {
                        return convertToHashFloodingResistantImplementation().add(object);
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
            Objects.requireNonNull(obj2);
            CompactHashing.tableSet(obj2, i3, i2);
        }
        resizeMeMaybe(i2);
        insertEntry(i, object, iSmearedHash, iHashTableMask);
        this.size = i2;
        incrementModCount();
        return true;
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
        this.elements = new Object[i];
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, 1073741823);
            setDelegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireElements(), 0, this.size, (Object) null);
        Object obj = this.table;
        Objects.requireNonNull(obj);
        CompactHashing.tableClear(obj);
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            return setDelegateOrNull.contains(object);
        }
        int iSmearedHash = Hashing.smearedHash(object);
        int iHashTableMask = hashTableMask();
        Object obj = this.table;
        Objects.requireNonNull(obj);
        int iTableGet = CompactHashing.tableGet(obj, iSmearedHash & iHashTableMask);
        if (iTableGet == 0) {
            return false;
        }
        int i = ~iHashTableMask;
        int i2 = iSmearedHash & i;
        do {
            int i3 = iTableGet - 1;
            int i4 = requireEntries()[i3];
            if ((i4 & i) == i2 && com.google.common.base.Objects.equal(object, requireElements()[i3])) {
                return true;
            }
            iTableGet = i4 & iHashTableMask;
        } while (iTableGet != 0);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> setCreateHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int iFirstEntryIndex = firstEntryIndex();
        while (iFirstEntryIndex >= 0) {
            setCreateHashFloodingResistantDelegate.add(requireElements()[iFirstEntryIndex]);
            iFirstEntryIndex = getSuccessor(iFirstEntryIndex);
        }
        this.table = setCreateHashFloodingResistantDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return setCreateHashFloodingResistantDelegate;
    }

    public final Set<E> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashSet(tableSize, 1.0f);
    }

    @VisibleForTesting
    public Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    public final E element(int i) {
        return (E) requireElements()[i];
    }

    public final int entry(int i) {
        return requireEntries()[i];
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
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

    public void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(expectedSize, 1, 1073741823);
    }

    public void insertEntry(int entryIndex, @ParametricNullness E object, int hash, int mask) {
        setEntry(entryIndex, CompactHashing.maskCombine(hash, 0, mask));
        setElement(entryIndex, object);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @VisibleForTesting
    public boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        Set<E> setDelegateOrNull = delegateOrNull();
        return setDelegateOrNull != null ? setDelegateOrNull.iterator() : new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1
            public int currentIndex;
            public int expectedMetadata;
            public int indexToRemove = -1;

            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            public final void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            public void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public E next() {
                checkForConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i = this.currentIndex;
                this.indexToRemove = i;
                E e = (E) CompactHashSet.this.requireElements()[i];
                this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                return e;
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                incrementExpectedModCount();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.requireElements()[this.indexToRemove]);
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }
        };
    }

    public void moveLastEntry(int dstIndex, int mask) {
        Object obj = this.table;
        Objects.requireNonNull(obj);
        int[] iArrRequireEntries = requireEntries();
        Object[] objArrRequireElements = requireElements();
        int size = size();
        int i = size - 1;
        if (dstIndex >= i) {
            objArrRequireElements[dstIndex] = null;
            iArrRequireEntries[dstIndex] = 0;
            return;
        }
        Object obj2 = objArrRequireElements[i];
        objArrRequireElements[dstIndex] = obj2;
        objArrRequireElements[i] = null;
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
            add(stream.readObject());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            return setDelegateOrNull.remove(object);
        }
        int iHashTableMask = hashTableMask();
        Object obj = this.table;
        Objects.requireNonNull(obj);
        int iRemove = CompactHashing.remove(object, null, iHashTableMask, obj, requireEntries(), requireElements(), null);
        if (iRemove == -1) {
            return false;
        }
        moveLastEntry(iRemove, iHashTableMask);
        this.size--;
        incrementModCount();
        return true;
    }

    public final Object[] requireElements() {
        Object[] objArr = this.elements;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final int[] requireEntries() {
        int[] iArr = this.entries;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    public final Object requireTable() {
        Object obj = this.table;
        Objects.requireNonNull(obj);
        return obj;
    }

    public void resizeEntries(int newCapacity) {
        this.entries = Arrays.copyOf(requireEntries(), newCapacity);
        this.elements = Arrays.copyOf(requireElements(), newCapacity);
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
        Objects.requireNonNull(obj);
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

    public final void setElement(int i, E value) {
        requireElements()[i] = value;
    }

    public final void setEntry(int i, int value) {
        requireEntries()[i] = value;
    }

    public final void setHashTableMask(int mask) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(mask), 31);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        Set<E> setDelegateOrNull = delegateOrNull();
        return setDelegateOrNull != null ? setDelegateOrNull.size() : this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        return setDelegateOrNull != null ? setDelegateOrNull.toArray() : Arrays.copyOf(requireElements(), this.size);
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            Set<E> setCreateHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            setCreateHashFloodingResistantDelegate.addAll(setDelegateOrNull);
            this.table = setCreateHashFloodingResistantDelegate;
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

    @J2ktIncompatible
    public final void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size());
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            stream.writeObject(it.next());
        }
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> compactHashSet = new CompactHashSet<>(collection.size());
        compactHashSet.addAll(collection);
        return compactHashSet;
    }

    public CompactHashSet(int expectedSize) {
        init(expectedSize);
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... elements) {
        CompactHashSet<E> compactHashSet = new CompactHashSet<>(elements.length);
        Collections.addAll(compactHashSet, elements);
        return compactHashSet;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        Set<E> setDelegateOrNull = delegateOrNull();
        if (setDelegateOrNull != null) {
            return (T[]) setDelegateOrNull.toArray(tArr);
        }
        return (T[]) ObjectArrays.toArrayImpl(requireElements(), 0, this.size, tArr);
    }
}
