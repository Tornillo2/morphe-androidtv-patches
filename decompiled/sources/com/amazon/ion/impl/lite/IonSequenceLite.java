package com.amazon.ion.impl.lite;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonSequenceLite extends IonContainerLite implements IonSequence {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final IonValueLite[] EMPTY_VALUE_ARRAY = new IonValueLite[0];

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SubListView extends AbstractList<IonValue> {
        public final int fromIndex;
        public int size;

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends IonValue> collection) {
            checkForParentModification();
            return super.addAll(i, collection);
        }

        public final void checkForParentModification() {
            if (((AbstractList) this).modCount != IonSequenceLite.this.structuralModificationCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            checkForParentModification();
            int i = this.fromIndex;
            for (int i2 = 0; i2 < this.size; i2++) {
                IonSequenceLite.this.remove(i);
            }
            this.size = 0;
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            checkForParentModification();
            return super.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean containsAll(Collection<?> collection) {
            checkForParentModification();
            return super.containsAll(collection);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            checkForParentModification();
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            checkForParentModification();
            return super.hashCode();
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            checkForParentModification();
            return super.indexOf(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            checkForParentModification();
            return this.size == 0;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<IonValue> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            checkForParentModification();
            return super.lastIndexOf(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<IonValue> listIterator() {
            return listIterator(0);
        }

        public final void rangeCheck(int i) {
            if (i < 0 || i >= this.size) {
                throw new IndexOutOfBoundsException(String.valueOf(i));
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<?> collection) {
            checkForParentModification();
            Iterator<?> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (remove(it.next())) {
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractList
        public void removeRange(int i, int i2) {
            checkForParentModification();
            super.removeRange(i, i2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<?> collection) {
            checkForParentModification();
            if (this.size < 1) {
                return false;
            }
            IdentityHashMap identityHashMap = new IdentityHashMap();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                identityHashMap.put(it.next(), null);
            }
            ArrayList arrayList = new ArrayList(this.size - collection.size());
            for (int i = 0; i < this.size; i++) {
                IonValue ionValue = get(i);
                if (!identityHashMap.containsKey(ionValue)) {
                    arrayList.add(ionValue);
                }
            }
            return removeAll(arrayList);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            checkForParentModification();
            return this.size;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<IonValue> subList(int i, int i2) {
            IonSequenceLite.checkSublistParameters(size(), i, i2);
            checkForParentModification();
            IonSequenceLite ionSequenceLite = IonSequenceLite.this;
            int i3 = this.fromIndex;
            return ionSequenceLite.new SubListView(i + i3, i2 + i3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            checkForParentModification();
            return super.toArray();
        }

        public final int toParentIndex(int i) {
            return i + this.fromIndex;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            checkForParentModification();
            return super.toString();
        }

        public SubListView(int i, int i2) {
            this.fromIndex = i;
            this.size = i2 - i;
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            add((IonValue) obj);
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue get(int i) {
            checkForParentModification();
            rangeCheck(i);
            return IonSequenceLite.this.get(i + this.fromIndex);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<IonValue> listIterator(int i) {
            checkForParentModification();
            return new ListIterator<IonValue>(i) { // from class: com.amazon.ion.impl.lite.IonSequenceLite.SubListView.1
                public int lastReturnedIndex;
                public int nextIndex;
                public final /* synthetic */ int val$index;

                {
                    this.val$index = i;
                    this.lastReturnedIndex = i;
                    this.nextIndex = i;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return this.nextIndex < SubListView.this.size();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return this.nextIndex > 0;
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return this.nextIndex;
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return this.nextIndex - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void add(IonValue ionValue) {
                    SubListView.this.add(this.lastReturnedIndex, ionValue);
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public IonValue next() {
                    int i2 = this.nextIndex;
                    this.nextIndex = i2 + 1;
                    this.lastReturnedIndex = i2;
                    return SubListView.this.get(i2);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.ListIterator
                public IonValue previous() {
                    int i2 = this.nextIndex - 1;
                    this.nextIndex = i2;
                    this.lastReturnedIndex = i2;
                    return SubListView.this.get(i2);
                }

                @Override // java.util.ListIterator
                public void set(IonValue ionValue) {
                    SubListView.this.set(this.lastReturnedIndex, ionValue);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue remove(int i) {
            checkForParentModification();
            rangeCheck(i);
            IonValue ionValueRemove = IonSequenceLite.this.remove(i + this.fromIndex);
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size--;
            return ionValueRemove;
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue set(int i, IonValue ionValue) {
            checkForParentModification();
            rangeCheck(i);
            return IonSequenceLite.this.set(i + this.fromIndex, ionValue);
        }

        public boolean add(IonValue ionValue) {
            checkForParentModification();
            int i = this.size + this.fromIndex;
            if (i == IonSequenceLite.this.size()) {
                IonSequenceLite.this.add(ionValue);
            } else {
                IonSequenceLite.this.add(i, ionValue);
            }
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size++;
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends IonValue> collection) {
            checkForParentModification();
            return super.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            checkForParentModification();
            return (T[]) super.toArray(tArr);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            checkForParentModification();
            int iIndexOf = indexOf(obj);
            if (iIndexOf < 0) {
                return false;
            }
            remove(iIndexOf);
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, IonValue ionValue) {
            checkForParentModification();
            rangeCheck(i);
            IonSequenceLite.this.add(i + this.fromIndex, ionValue);
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size++;
        }
    }

    public IonSequenceLite(ContainerlessContext containerlessContext, Collection<? extends IonValue> collection) throws ContainedValueException, IllegalArgumentException, NullPointerException {
        super(containerlessContext, collection == null);
        if (collection != null) {
            this._children = new IonValueLite[collection.size()];
            Iterator<? extends IonValue> it = collection.iterator();
            while (it.hasNext()) {
                super.add((IonValue) it.next());
            }
        }
    }

    public static void checkSublistParameters(int i, int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex is less than zero");
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("toIndex may not be less than fromIndex");
        }
        if (i3 > i) {
            throw new IndexOutOfBoundsException("toIndex exceeds size");
        }
    }

    public boolean addAll(Collection<? extends IonValue> collection) {
        checkForLock();
        collection.getClass();
        Iterator<? extends IonValue> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            add(it.next());
            z = true;
        }
        return z;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonSequenceLite mo245clone();

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        obj.getClass();
        if (obj instanceof IonValue) {
            return ((IonValue) obj).getContainer() == this;
        }
        throw new ClassCastException();
    }

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public <T extends IonValue> T[] extract(Class<T> cls) {
        checkForLock();
        if (is_true(4)) {
            return null;
        }
        T[] tArr = (T[]) ((IonValue[]) Array.newInstance((Class<?>) cls, size()));
        toArray(tArr);
        clear();
        return tArr;
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ IonValue get(int i) {
        return super.get(i);
    }

    @Override // com.amazon.ion.IonSequence, java.util.List
    public int indexOf(Object obj) {
        obj.getClass();
        _Private_IonValue _private_ionvalue = (_Private_IonValue) obj;
        if (this != _private_ionvalue.getContainer()) {
            return -1;
        }
        return _private_ionvalue.getElementId();
    }

    @Override // com.amazon.ion.IonSequence, java.util.List
    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        checkForLock();
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            int iLastIndexOf = lastIndexOf(it.next());
            if (iLastIndexOf >= 0) {
                remove_child(iLastIndexOf);
                patch_elements_helper(iLastIndexOf);
                z = true;
            }
        }
        return z;
    }

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        checkForLock();
        boolean z = false;
        if (this._child_count < 1) {
            return false;
        }
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            IonValue ionValue = (IonValue) it.next();
            if (this == ionValue.getContainer()) {
                identityHashMap.put(ionValue, ionValue);
            }
        }
        int i = this._child_count;
        while (i > 0) {
            i--;
            IonValueLite ionValueLite = get_child(i);
            if (!identityHashMap.containsKey(ionValueLite)) {
                remove((IonValue) ionValueLite);
                patch_elements_helper(i);
                z = true;
            }
        }
        return z;
    }

    public int sequenceHashCode(int i, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (!is_true(4)) {
            ListIterator<IonValue> listIterator = listIterator(0);
            while (listIterator.hasNext()) {
                IonValueLite ionValueLite = (IonValueLite) listIterator.next();
                int iHashCode = ionValueLite.hashCode(symbolTableProvider) + (i * IonTokenConstsX.KW_ALL_BITS);
                i = ((iHashCode << 29) ^ (iHashCode >> 3)) ^ iHashCode;
            }
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonSequence, java.util.List
    public List<IonValue> subList(int i, int i2) {
        checkSublistParameters(size(), i, i2);
        return new SubListView(i, i2);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        IonType type = getType();
        if (is_true(4)) {
            ionWriter.writeNull(type);
            return;
        }
        ionWriter.stepIn(type);
        writeChildren(ionWriter, this, symbolTableProvider);
        ionWriter.stepOut();
    }

    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        add((IonValue) obj);
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public IonValue remove(int i) {
        checkForLock();
        if (i < 0 || i >= this._child_count) {
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("", i));
        }
        IonValueLite ionValueLite = get_child(i);
        remove_child(i);
        patch_elements_helper(i);
        return ionValueLite;
    }

    @Override // java.util.List
    public IonValue set(int i, IonValue ionValue) {
        checkForLock();
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("", i));
        }
        validateNewChild(ionValue);
        ionValueLite._context = getContextForIndex(ionValue, i);
        IonValueLite ionValueLite2 = set_child(i, ionValueLite);
        ionValueLite._elementid(i);
        ionValueLite2.detachFromContainer();
        return ionValueLite2;
    }

    @Override // java.util.List, java.util.Collection
    public IonValue[] toArray() {
        int i = this._child_count;
        if (i < 1) {
            return EMPTY_VALUE_ARRAY;
        }
        IonValue[] ionValueArr = new IonValue[i];
        System.arraycopy(this._children, 0, ionValueArr, 0, i);
        return ionValueArr;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonSequence
    public boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException {
        super.add(ionValue);
        return true;
    }

    public ValueFactory add() {
        return new _Private_CurriedValueFactory(getSystem()) { // from class: com.amazon.ion.impl.lite.IonSequenceLite.1
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonSequenceLite.this.add(ionValue);
            }
        };
    }

    @Override // java.util.List
    public void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException {
        add(i, (IonValueLite) ionValue);
    }

    public boolean addAll(int i, Collection<? extends IonValue> collection) {
        checkForLock();
        collection.getClass();
        if (i >= 0 && i <= size()) {
            Iterator<? extends IonValue> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                add(i, it.next());
                z = true;
                i++;
            }
            if (z) {
                patch_elements_helper(i);
            }
            return z;
        }
        throw new IndexOutOfBoundsException();
    }

    public IonSequenceLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    public ValueFactory add(final int i) {
        return new _Private_CurriedValueFactory(getSystem()) { // from class: com.amazon.ion.impl.lite.IonSequenceLite.2
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonSequenceLite.this.add(i, ionValue);
                IonSequenceLite.this.patch_elements_helper(i + 1);
            }
        };
    }

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        int i = this._child_count;
        if (tArr.length < i) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
        }
        if (i > 0) {
            System.arraycopy(this._children, 0, tArr, 0, i);
        }
        if (i < tArr.length) {
            tArr[i] = null;
        }
        return tArr;
    }

    public IonSequenceLite(IonSequenceLite ionSequenceLite, IonContext ionContext) {
        super(ionSequenceLite, ionContext, false);
    }

    @Override // com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        checkForLock();
        int iLastIndexOf = lastIndexOf(obj);
        if (iLastIndexOf < 0) {
            return false;
        }
        remove_child(iLastIndexOf);
        patch_elements_helper(iLastIndexOf);
        return true;
    }
}
