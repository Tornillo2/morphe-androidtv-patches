package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.InlineMe;
import j$.util.List;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess, j$.util.List {
    public static final UnmodifiableListIterator<Object> EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = -889275714;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            super(4);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder add(Object[] elements) {
            addAll(elements, elements.length);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder addAll(Iterable elements) {
            super.addAll(elements);
            return this;
        }

        public ImmutableList<E> buildSorted(Comparator<? super E> comparator) {
            this.forceCopy = true;
            Arrays.sort(this.contents, 0, this.size, comparator);
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        @CanIgnoreReturnValue
        public Builder<E> combine(Builder<E> other) {
            addAll(other.contents, other.size);
            return this;
        }

        public Builder(int capacity) {
            super(capacity);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... elements) {
            addAll(elements, elements.length);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            super.addAll((Iterable) elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableList<E> build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.ArrayBasedBuilder add(Object element) {
            super.add(element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder addAll(Iterator elements) {
            super.addAll(elements);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public ImmutableCollection.Builder add(Object element) {
            super.add(element);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll((Iterator) elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            super.add((Object) element);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Itr<E> extends AbstractIndexedListIterator<E> {
        public final ImmutableList<E> list;

        public Itr(ImmutableList<E> list, int index) {
            super(list.size(), index);
            this.list = list;
        }

        @Override // com.google.common.collect.AbstractIndexedListIterator
        public E get(int index) {
            return this.list.get(index);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ReverseImmutableList<E> extends ImmutableList<E> {
        public final transient ImmutableList<E> forwardList;

        public ReverseImmutableList(ImmutableList<E> backingList) {
            this.forwardList = backingList;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            return this.forwardList.contains(object);
        }

        @Override // java.util.List
        public E get(int index) {
            Preconditions.checkElementIndex(index, size());
            return this.forwardList.get(reverseIndex(index));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object object) {
            int iLastIndexOf = this.forwardList.lastIndexOf(object);
            if (iLastIndexOf >= 0) {
                return reverseIndex(iLastIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return listIterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object object) {
            int iIndexOf = this.forwardList.indexOf(object);
            if (iIndexOf >= 0) {
                return reverseIndex(iIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ListIterator listIterator() {
            return listIterator(0);
        }

        @Override // com.google.common.collect.ImmutableList
        public ImmutableList<E> reverse() {
            return this.forwardList;
        }

        public final int reverseIndex(int index) {
            return (size() - 1) - index;
        }

        public final int reversePosition(int index) {
            return size() - index;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int index) {
            return super.listIterator(index);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            return this.forwardList.subList(size() - toIndex, size() - fromIndex).reverse();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class SerializedForm implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public SerializedForm(Object[] elements) {
            this.elements = elements;
        }

        public Object readResolve() {
            return ImmutableList.copyOf(this.elements);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SubList extends ImmutableList<E> {
        public final transient int length;
        public final transient int offset;

        public SubList(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }

        @Override // java.util.List
        public E get(int index) {
            Preconditions.checkElementIndex(index, this.length);
            return ImmutableList.this.get(index + this.offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public Object[] internalArray() {
            return ImmutableList.this.internalArray();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int internalArrayEnd() {
            return ImmutableList.this.internalArrayStart() + this.offset + this.length;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int internalArrayStart() {
            return ImmutableList.this.internalArrayStart() + this.offset;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public Iterator iterator() {
            return listIterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ListIterator listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.length;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int index) {
            return super.listIterator(index);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i = this.offset;
            return immutableList.subList(fromIndex + i, toIndex + i);
        }
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr, int i) {
        return i == 0 ? (ImmutableList<E>) RegularImmutableList.EMPTY : new RegularImmutableList(objArr, i);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>(4);
    }

    public static <E> Builder<E> builderWithExpectedSize(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        return new Builder<>(expectedSize);
    }

    public static <E> ImmutableList<E> construct(Object... elements) {
        ObjectArrays.checkElementsNotNull(elements, elements.length);
        return asImmutableList(elements, elements.length);
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> elements) {
        if (!(elements instanceof ImmutableCollection)) {
            return construct(elements.toArray());
        }
        ImmutableList<E> immutableListAsList = ((ImmutableCollection) elements).asList();
        if (!immutableListAsList.isPartialView()) {
            return immutableListAsList;
        }
        Object[] array = immutableListAsList.toArray(ImmutableCollection.EMPTY_ARRAY);
        return asImmutableList(array, array.length);
    }

    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) RegularImmutableList.EMPTY;
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<? super E>> ImmutableList<E> sortedCopyOf(Iterable<? extends E> elements) {
        Comparable[] comparableArr = (Comparable[]) Iterables.toArray(elements, new Comparable[0]);
        ObjectArrays.checkElementsNotNull(comparableArr, comparableArr.length);
        Arrays.sort(comparableArr);
        return asImmutableList(comparableArr, comparableArr.length);
    }

    @IgnoreJRERequirement
    public static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return CollectCollectors.toImmutableList();
    }

    @Override // java.util.List
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean addAll(int index, Collection<? extends E> newElements) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        int size = size();
        for (int i = 0; i < size; i++) {
            dst[offset + i] = get(i);
        }
        return offset + size;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~(get(i2).hashCode() + (i * 31)));
        }
        return i;
    }

    @Override // java.util.List
    public int indexOf(Object object) {
        if (object == null) {
            return -1;
        }
        return Lists.indexOfImpl(this, object);
    }

    @Override // java.util.List
    public int lastIndexOf(Object object) {
        if (object == null) {
            return -1;
        }
        return Lists.lastIndexOfImpl(this, object);
    }

    @Override // java.util.List
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public /* synthetic */ void replaceAll(UnaryOperator unaryOperator) {
        List.CC.$default$replaceAll(this, unaryOperator);
    }

    public ImmutableList<E> reverse() {
        return size() <= 1 ? this : new ReverseImmutableList(this);
    }

    @Override // java.util.List
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public /* synthetic */ void sort(Comparator comparator) {
        List.CC.$default$sort(this, comparator);
    }

    public ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex - fromIndex);
    }

    @Override // com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(toArray(ImmutableCollection.EMPTY_ARRAY));
    }

    public static <E> ImmutableList<E> of(E e1) {
        Object[] objArr = {e1};
        ObjectArrays.checkElementsNotNull(objArr, 1);
        return asImmutableList(objArr, 1);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? (ImmutableList<E>) RegularImmutableList.EMPTY : subListUnchecked(i, i2);
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] elements) {
        return asImmutableList(elements, elements.length);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator(int i) {
        Preconditions.checkPositionIndex(i, size());
        if (isEmpty()) {
            return (UnmodifiableListIterator<E>) EMPTY_ITR;
        }
        return new Itr(this, i);
    }

    public static <E> ImmutableList<E> of(E e1, E e2) {
        Object[] objArr = {e1, e2};
        ObjectArrays.checkElementsNotNull(objArr, 2);
        return asImmutableList(objArr, 2);
    }

    public static <E> ImmutableList<E> sortedCopyOf(Comparator<? super E> comparator, Iterable<? extends E> elements) {
        comparator.getClass();
        Object[] array = Iterables.toArray(elements);
        ObjectArrays.checkElementsNotNull(array, array.length);
        Arrays.sort(array, comparator);
        return asImmutableList(array, array.length);
    }

    public static <E> ImmutableList<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return (ImmutableList<E>) RegularImmutableList.EMPTY;
        }
        E next = it.next();
        if (!it.hasNext()) {
            return of((Object) next);
        }
        Builder builder = new Builder(4);
        builder.add((Object) next);
        builder.addAll((Iterator) it);
        return builder.build();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3) {
        Object[] objArr = {e1, e2, e3};
        ObjectArrays.checkElementsNotNull(objArr, 3);
        return asImmutableList(objArr, 3);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4) {
        Object[] objArr = {e1, e2, e3, e4};
        ObjectArrays.checkElementsNotNull(objArr, 4);
        return asImmutableList(objArr, 4);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5) {
        Object[] objArr = {e1, e2, e3, e4, e5};
        ObjectArrays.checkElementsNotNull(objArr, 5);
        return asImmutableList(objArr, 5);
    }

    public static <E> ImmutableList<E> copyOf(E[] eArr) {
        if (eArr.length == 0) {
            return (ImmutableList<E>) RegularImmutableList.EMPTY;
        }
        Object[] objArr = (Object[]) eArr.clone();
        ObjectArrays.checkElementsNotNull(objArr, objArr.length);
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6};
        ObjectArrays.checkElementsNotNull(objArr, 6);
        return asImmutableList(objArr, 6);
    }

    @Override // com.google.common.collect.ImmutableCollection
    @InlineMe(replacement = "this")
    @Deprecated
    public final ImmutableList<E> asList() {
        return this;
    }

    public static <E> ImmutableList<E> copyOf(Iterable<? extends E> elements) {
        elements.getClass();
        if (elements instanceof Collection) {
            return copyOf((Collection) elements);
        }
        return copyOf(elements.iterator());
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6, e7};
        ObjectArrays.checkElementsNotNull(objArr, 7);
        return asImmutableList(objArr, 7);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6, e7, e8};
        ObjectArrays.checkElementsNotNull(objArr, 8);
        return asImmutableList(objArr, 8);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6, e7, e8, e9};
        ObjectArrays.checkElementsNotNull(objArr, 9);
        return asImmutableList(objArr, 9);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10};
        ObjectArrays.checkElementsNotNull(objArr, 10);
        return asImmutableList(objArr, 10);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11) {
        Object[] objArr = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11};
        ObjectArrays.checkElementsNotNull(objArr, 11);
        return asImmutableList(objArr, 11);
    }

    @SafeVarargs
    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10, E e11, E e12, E... others) {
        Preconditions.checkArgument(others.length <= 2147483635, "the total number of elements must fit in an int");
        int length = others.length + 12;
        Object[] objArr = new Object[length];
        objArr[0] = e1;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        objArr[6] = e7;
        objArr[7] = e8;
        objArr[8] = e9;
        objArr[9] = e10;
        objArr[10] = e11;
        objArr[11] = e12;
        System.arraycopy(others, 0, objArr, 12, others.length);
        ObjectArrays.checkElementsNotNull(objArr, length);
        return asImmutableList(objArr, length);
    }
}
