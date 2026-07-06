package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Lists {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Lists$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1<E> extends RandomAccessListWrapper<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public AnonymousClass1(List backingList) {
            super(backingList);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int index) {
            return this.backingList.listIterator(index);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Lists$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2<E> extends AbstractListWrapper<E> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public AnonymousClass2(List backingList) {
            super(backingList);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int index) {
            return this.backingList.listIterator(index);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AbstractListWrapper<E> extends AbstractList<E> {
        public final List<E> backingList;

        public AbstractListWrapper(List<E> backingList) {
            backingList.getClass();
            this.backingList = backingList;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int index, @ParametricNullness E element) {
            this.backingList.add(index, element);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int index, Collection<? extends E> c) {
            return this.backingList.addAll(index, c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object o) {
            return this.backingList.contains(o);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int index) {
            return this.backingList.get(index);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E remove(int index) {
            return this.backingList.remove(index);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E set(int index, @ParametricNullness E element) {
            return this.backingList.set(index, element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.backingList.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CharSequenceAsList extends AbstractList<Character> {
        public final CharSequence sequence;

        public CharSequenceAsList(CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.sequence.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int index) {
            Preconditions.checkElementIndex(index, this.sequence.length());
            return Character.valueOf(this.sequence.charAt(index));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final E first;
        public final E[] rest;

        public OnePlusArrayList(@ParametricNullness E first, E[] rest) {
            this.first = first;
            rest.getClass();
            this.rest = rest;
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int index) {
            Preconditions.checkElementIndex(index, size());
            return index == 0 ? this.first : this.rest[index - 1];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 1);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Partition<T> extends AbstractList<List<T>> {
        public final List<T> list;
        public final int size;

        public Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.list.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.divide(this.list.size(), this.size, RoundingMode.CEILING);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int index) {
            Preconditions.checkElementIndex(index, size());
            int i = this.size;
            int i2 = index * i;
            return this.list.subList(i2, Math.min(i + i2, this.list.size()));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
        public RandomAccessListWrapper(List<E> backingList) {
            super(backingList);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        public RandomAccessPartition(List<T> list, int size) {
            super(list, size);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        public RandomAccessReverseList(List<T> forwardList) {
            super(forwardList);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ReverseList<T> extends AbstractList<T> {
        public final List<T> forwardList;

        public ReverseList(List<T> forwardList) {
            forwardList.getClass();
            this.forwardList = forwardList;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int index, @ParametricNullness T element) {
            this.forwardList.add(reversePosition(index), element);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.forwardList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T get(int index) {
            return this.forwardList.get(reverseIndex(index));
        }

        public List<T> getForwardList() {
            return this.forwardList;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int index) {
            final ListIterator<T> listIterator = this.forwardList.listIterator(reversePosition(index));
            return new ListIterator<T>(this) { // from class: com.google.common.collect.Lists.ReverseList.1
                public boolean canRemoveOrSet;
                public final /* synthetic */ ReverseList this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.util.ListIterator
                public void add(@ParametricNullness T e) {
                    listIterator.add(e);
                    listIterator.previous();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return listIterator.hasNext();
                }

                @Override // java.util.ListIterator, java.util.Iterator
                @ParametricNullness
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.previous();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return this.this$0.reversePosition(listIterator.nextIndex());
                }

                @Override // java.util.ListIterator
                @ParametricNullness
                public T previous() {
                    if (!hasPrevious()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.next();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return nextIndex() - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.canRemoveOrSet);
                    listIterator.remove();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator
                public void set(@ParametricNullness T e) {
                    Preconditions.checkState(this.canRemoveOrSet);
                    listIterator.set(e);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T remove(int index) {
            return this.forwardList.remove(reverseIndex(index));
        }

        @Override // java.util.AbstractList
        public void removeRange(int fromIndex, int toIndex) {
            subList(fromIndex, toIndex).clear();
        }

        public final int reverseIndex(int index) {
            int size = size();
            Preconditions.checkElementIndex(index, size);
            return (size - 1) - index;
        }

        public final int reversePosition(int index) {
            int size = size();
            Preconditions.checkPositionIndex(index, size);
            return size - index;
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T set(int index, @ParametricNullness T element) {
            return this.forwardList.set(reverseIndex(index), element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            return Lists.reverse(this.forwardList.subList(reversePosition(toIndex), reversePosition(fromIndex)));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StringAsImmutableList extends ImmutableList<Character> {
        public final String string;

        public StringAsImmutableList(String string) {
            this.string = string;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object object) {
            if (object instanceof Character) {
                return this.string.indexOf(((Character) object).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object object) {
            if (object instanceof Character) {
                return this.string.lastIndexOf(((Character) object).charValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.string.length();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // java.util.List
        public Character get(int index) {
            Preconditions.checkElementIndex(index, this.string.length());
            return Character.valueOf(this.string.charAt(index));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<Character> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, this.string.length());
            return Lists.charactersOf(this.string.substring(fromIndex, toIndex));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final List<F> fromList;
        public final Function<? super F, ? extends T> function;

        public TransformingRandomAccessList(List<F> fromList, Function<? super F, ? extends T> function) {
            fromList.getClass();
            this.fromList = fromList;
            function.getClass();
            this.function = function;
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T get(int i) {
            return this.function.apply(this.fromList.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int index) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(index)) { // from class: com.google.common.collect.Lists.TransformingRandomAccessList.1
                @Override // com.google.common.collect.TransformedIterator
                public T transform(F from) {
                    return TransformingRandomAccessList.this.function.apply(from);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int i) {
            return this.function.apply(this.fromList.remove(i));
        }

        @Override // java.util.AbstractList
        public void removeRange(int fromIndex, int toIndex) {
            this.fromList.subList(fromIndex, toIndex).clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final List<F> fromList;
        public final Function<? super F, ? extends T> function;

        public TransformingSequentialList(List<F> fromList, Function<? super F, ? extends T> function) {
            fromList.getClass();
            this.fromList = fromList;
            function.getClass();
            this.function = function;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int index) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(index)) { // from class: com.google.common.collect.Lists.TransformingSequentialList.1
                @Override // com.google.common.collect.TransformedIterator
                @ParametricNullness
                public T transform(@ParametricNullness F from) {
                    return TransformingSequentialList.this.function.apply(from);
                }
            };
        }

        @Override // java.util.AbstractList
        public void removeRange(int fromIndex, int toIndex) {
            this.fromList.subList(fromIndex, toIndex).clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final E first;
        public final E[] rest;

        @ParametricNullness
        public final E second;

        public TwoPlusArrayList(@ParametricNullness E first, @ParametricNullness E second, E[] rest) {
            this.first = first;
            this.second = second;
            rest.getClass();
            this.rest = rest;
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int index) {
            if (index == 0) {
                return this.first;
            }
            if (index == 1) {
                return this.second;
            }
            Preconditions.checkElementIndex(index, size());
            return this.rest[index - 2];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 2);
        }
    }

    public static <E> boolean addAllImpl(List<E> list, int index, Iterable<? extends E> elements) {
        ListIterator<E> listIterator = list.listIterator(index);
        Iterator<? extends E> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            listIterator.add(it.next());
            z = true;
        }
        return z;
    }

    public static <E> List<E> asList(@ParametricNullness E first, E[] rest) {
        return new OnePlusArrayList(first, rest);
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> lists) {
        return CartesianList.create(lists);
    }

    public static ImmutableList<Character> charactersOf(String string) {
        string.getClass();
        return new StringAsImmutableList(string);
    }

    @VisibleForTesting
    public static int computeArrayListCapacity(int arraySize) {
        CollectPreconditions.checkNonnegative(arraySize, "arraySize");
        return Ints.saturatedCast(((long) arraySize) + 5 + ((long) (arraySize / 10)));
    }

    public static boolean equalsImpl(List<?> thisList, Object other) {
        thisList.getClass();
        if (other == thisList) {
            return true;
        }
        if (!(other instanceof List)) {
            return false;
        }
        List list = (List) other;
        int size = thisList.size();
        if (size != list.size()) {
            return false;
        }
        if (!(thisList instanceof RandomAccess) || !(list instanceof RandomAccess)) {
            return Iterators.elementsEqual(thisList.iterator(), list.iterator());
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equal(thisList.get(i), list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int hashCodeImpl(List<?> list) {
        Iterator<?> it = list.iterator();
        int i = 1;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~((i * 31) + (next == null ? 0 : next.hashCode())));
        }
        return i;
    }

    public static int indexOfImpl(List<?> list, Object element) {
        if (list instanceof RandomAccess) {
            return indexOfRandomAccess(list, element);
        }
        ListIterator<?> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equal(element, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    public static int indexOfRandomAccess(List<?> list, Object element) {
        int size = list.size();
        int i = 0;
        if (element == null) {
            while (i < size) {
                if (list.get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (element.equals(list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOfImpl(List<?> list, Object element) {
        if (list instanceof RandomAccess) {
            return lastIndexOfRandomAccess(list, element);
        }
        ListIterator<?> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(element, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public static int lastIndexOfRandomAccess(List<?> list, Object element) {
        if (element == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (element.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    public static <E> ListIterator<E> listIteratorImpl(List<E> list, int index) {
        return new AbstractListWrapper(list).listIterator(index);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
        CollectPreconditions.checkNonnegative(initialArraySize, "initialArraySize");
        return new ArrayList<>(initialArraySize);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithExpectedSize(int estimatedSize) {
        return new ArrayList<>(computeArrayListCapacity(estimatedSize));
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <T> List<List<T>> partition(List<T> list, int size) {
        list.getClass();
        Preconditions.checkArgument(size > 0);
        return list instanceof RandomAccess ? new RandomAccessPartition(list, size) : new Partition(list, size);
    }

    public static <T> List<T> reverse(List<T> list) {
        return list instanceof ImmutableList ? ((ImmutableList) list).reverse() : list instanceof ReverseList ? ((ReverseList) list).getForwardList() : list instanceof RandomAccess ? new RandomAccessReverseList(list) : new ReverseList(list);
    }

    public static <E> List<E> subListImpl(List<E> list, int fromIndex, int toIndex) {
        return (list instanceof RandomAccess ? new AnonymousClass1(list) : new AnonymousClass2(list)).subList(fromIndex, toIndex);
    }

    public static <F, T> List<T> transform(List<F> fromList, Function<? super F, ? extends T> function) {
        return fromList instanceof RandomAccess ? new TransformingRandomAccessList(fromList, function) : new TransformingSequentialList(fromList, function);
    }

    public static <E> List<E> asList(@ParametricNullness E first, @ParametricNullness E second, E[] rest) {
        return new TwoPlusArrayList(first, second, rest);
    }

    @SafeVarargs
    public static <B> List<List<B>> cartesianProduct(List<? extends B>... lists) {
        return CartesianList.create(Arrays.asList(lists));
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> arrayList = new ArrayList<>();
        Iterators.addAll(arrayList, elements);
        return arrayList;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> elements) {
        return new CopyOnWriteArrayList<>(elements instanceof Collection ? (Collection) elements : newArrayList(elements));
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        Iterables.addAll(linkedList, elements);
        return linkedList;
    }

    public static List<Character> charactersOf(CharSequence sequence) {
        sequence.getClass();
        return new CharSequenceAsList(sequence);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        elements.getClass();
        if (elements instanceof Collection) {
            return new ArrayList<>((Collection) elements);
        }
        return newArrayList(elements.iterator());
    }

    @SafeVarargs
    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(E... elements) {
        elements.getClass();
        ArrayList<E> arrayList = new ArrayList<>(computeArrayListCapacity(elements.length));
        Collections.addAll(arrayList, elements);
        return arrayList;
    }
}
