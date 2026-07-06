package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import j$.util.Objects;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    public static final int DEFAULT_CAPACITY = 11;
    public static final int EVEN_POWERS_OF_TWO = 1431655765;
    public static final int ODD_POWERS_OF_TWO = -1431655766;
    public final MinMaxPriorityQueue<E>.Heap maxHeap;

    @VisibleForTesting
    public final int maximumSize;
    public final MinMaxPriorityQueue<E>.Heap minHeap;
    public int modCount;
    public Object[] queue;
    public int size;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<B> {
        public static final int UNSET_EXPECTED_SIZE = -1;
        public final Comparator<B> comparator;
        public int expectedSize;
        public int maximumSize;

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.EMPTY_SET);
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int expectedSize) {
            Preconditions.checkArgument(expectedSize >= 0);
            this.expectedSize = expectedSize;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int maximumSize) {
            Preconditions.checkArgument(maximumSize > 0);
            this.maximumSize = maximumSize;
            return this;
        }

        public final <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            comparator.getClass();
            this.comparator = comparator;
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> initialContents) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, initialContents));
            Iterator<? extends T> it = initialContents.iterator();
            while (it.hasNext()) {
                minMaxPriorityQueue.offer(it.next());
            }
            return minMaxPriorityQueue;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Heap {
        public final Ordering<E> ordering;

        @Weak
        public MinMaxPriorityQueue<E>.Heap otherHeap;

        public Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        public void bubbleUp(int index, E x) {
            Heap heap;
            int iCrossOverUp = crossOverUp(index, x);
            if (iCrossOverUp == index) {
                iCrossOverUp = index;
                heap = this;
            } else {
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(iCrossOverUp, x);
        }

        @CanIgnoreReturnValue
        public int bubbleUpAlternatingLevels(int i, E e) {
            while (i > 2) {
                int grandparentIndex = getGrandparentIndex(i);
                Object objElementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (this.ordering.compare((E) objElementData, e) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i] = objElementData;
                i = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i] = e;
            return i;
        }

        public int compareElements(int i, int i2) {
            return this.ordering.compare((E) MinMaxPriorityQueue.this.elementData(i), (E) MinMaxPriorityQueue.this.elementData(i2));
        }

        public int crossOver(int i, E e) {
            int iFindMinChild = findMinChild(i);
            if (iFindMinChild <= 0 || this.ordering.compare((E) MinMaxPriorityQueue.this.elementData(iFindMinChild), e) >= 0) {
                return crossOverUp(i, e);
            }
            MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
            minMaxPriorityQueue.queue[i] = minMaxPriorityQueue.elementData(iFindMinChild);
            MinMaxPriorityQueue.this.queue[iFindMinChild] = e;
            return iFindMinChild;
        }

        public int crossOverUp(int i, E e) {
            int parentIndex;
            if (i == 0) {
                MinMaxPriorityQueue.this.queue[0] = e;
                return 0;
            }
            int parentIndex2 = getParentIndex(i);
            Object objElementData = MinMaxPriorityQueue.this.elementData(parentIndex2);
            if (parentIndex2 != 0 && (parentIndex = (getParentIndex(parentIndex2) * 2) + 2) != parentIndex2) {
                int leftChildIndex = getLeftChildIndex(parentIndex);
                MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
                if (leftChildIndex >= minMaxPriorityQueue.size) {
                    Object objElementData2 = minMaxPriorityQueue.elementData(parentIndex);
                    if (this.ordering.compare((E) objElementData2, (E) objElementData) < 0) {
                        parentIndex2 = parentIndex;
                        objElementData = objElementData2;
                    }
                }
            }
            if (this.ordering.compare((E) objElementData, e) >= 0) {
                MinMaxPriorityQueue.this.queue[i] = e;
                return i;
            }
            Object[] objArr = MinMaxPriorityQueue.this.queue;
            objArr[i] = objElementData;
            objArr[parentIndex2] = e;
            return parentIndex2;
        }

        public int fillHoleAt(int index) {
            while (true) {
                int iFindMinGrandChild = findMinGrandChild(index);
                if (iFindMinGrandChild <= 0) {
                    return index;
                }
                MinMaxPriorityQueue.this.queue[index] = MinMaxPriorityQueue.this.elementData(iFindMinGrandChild);
                index = iFindMinGrandChild;
            }
        }

        public int findMin(int index, int len) {
            if (index >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(index > 0);
            int iMin = Math.min(index, MinMaxPriorityQueue.this.size - len) + len;
            for (int i = index + 1; i < iMin; i++) {
                if (compareElements(i, index) < 0) {
                    index = i;
                }
            }
            return index;
        }

        public int findMinChild(int index) {
            return findMin(getLeftChildIndex(index), 2);
        }

        public int findMinGrandChild(int index) {
            int leftChildIndex = getLeftChildIndex(index);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        public final int getGrandparentIndex(int i) {
            return getParentIndex(getParentIndex(i));
        }

        public final int getLeftChildIndex(int i) {
            return (i * 2) + 1;
        }

        public final int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        public final int getRightChildIndex(int i) {
            return (i * 2) + 2;
        }

        public int swapWithConceptuallyLastElement(E e) {
            int parentIndex;
            int parentIndex2 = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex2 != 0 && (parentIndex = (getParentIndex(parentIndex2) * 2) + 2) != parentIndex2) {
                int leftChildIndex = getLeftChildIndex(parentIndex);
                MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
                if (leftChildIndex >= minMaxPriorityQueue.size) {
                    Object objElementData = minMaxPriorityQueue.elementData(parentIndex);
                    if (this.ordering.compare((E) objElementData, e) < 0) {
                        MinMaxPriorityQueue minMaxPriorityQueue2 = MinMaxPriorityQueue.this;
                        Object[] objArr = minMaxPriorityQueue2.queue;
                        objArr[parentIndex] = e;
                        objArr[minMaxPriorityQueue2.size] = objElementData;
                        return parentIndex;
                    }
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        public MoveDesc<E> tryCrossOverAndBubbleUp(int removeIndex, int vacated, E toTrickle) {
            int iCrossOver = crossOver(vacated, toTrickle);
            if (iCrossOver == vacated) {
                return null;
            }
            Object objElementData = iCrossOver < removeIndex ? MinMaxPriorityQueue.this.elementData(removeIndex) : MinMaxPriorityQueue.this.elementData(getParentIndex(removeIndex));
            if (this.otherHeap.bubbleUpAlternatingLevels(iCrossOver, toTrickle) < removeIndex) {
                return new MoveDesc<>(toTrickle, objElementData);
            }
            return null;
        }

        public final boolean verifyIndex(int i) {
            if (getLeftChildIndex(i) < MinMaxPriorityQueue.this.size && compareElements(i, getLeftChildIndex(i)) > 0) {
                return false;
            }
            int i2 = (i * 2) + 2;
            if (i2 < MinMaxPriorityQueue.this.size && compareElements(i, i2) > 0) {
                return false;
            }
            if (i <= 0 || compareElements(i, getParentIndex(i)) <= 0) {
                return i <= 2 || compareElements(getGrandparentIndex(i), i) <= 0;
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MoveDesc<E> {
        public final E replaced;
        public final E toTrickle;

        public MoveDesc(E toTrickle, E replaced) {
            this.toTrickle = toTrickle;
            this.replaced = replaced;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class QueueIterator implements Iterator<E> {
        public boolean canRemove;
        public int cursor;
        public int expectedModCount;
        public Queue<E> forgetMeNot;
        public E lastFromForgetMeNot;
        public int nextCursor;
        public List<E> skipMe;

        public QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        public final void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean foundAndRemovedExactReference(Iterable<E> elements, E target) {
            Iterator<E> it = elements.iterator();
            while (it.hasNext()) {
                if (it.next() == target) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Queue<E> queue;
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            return this.nextCursor < MinMaxPriorityQueue.this.size || !((queue = this.forgetMeNot) == null || queue.isEmpty());
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            int i = this.nextCursor;
            MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
            int i2 = minMaxPriorityQueue.size;
            if (i < i2) {
                this.cursor = i;
                this.canRemove = true;
                return (E) minMaxPriorityQueue.elementData(i);
            }
            Queue<E> queue = this.forgetMeNot;
            if (queue != null) {
                this.cursor = i2;
                E ePoll = queue.poll();
                this.lastFromForgetMeNot = ePoll;
                if (ePoll != null) {
                    this.canRemove = true;
                    return ePoll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void nextNotInSkipMe(int c) {
            if (this.nextCursor < c) {
                if (this.skipMe != null) {
                    while (true) {
                        MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
                        if (c >= minMaxPriorityQueue.size || !foundAndRemovedExactReference(this.skipMe, minMaxPriorityQueue.elementData(c))) {
                            break;
                        } else {
                            c++;
                        }
                    }
                }
                this.nextCursor = c;
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            int i = this.cursor;
            MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
            if (i >= minMaxPriorityQueue.size) {
                E e = this.lastFromForgetMeNot;
                Objects.requireNonNull(e);
                Preconditions.checkState(removeExact(e));
                this.lastFromForgetMeNot = null;
                return;
            }
            MoveDesc<E> moveDescRemoveAt = minMaxPriorityQueue.removeAt(i);
            if (moveDescRemoveAt != null) {
                if (this.forgetMeNot == null || this.skipMe == null) {
                    this.forgetMeNot = new ArrayDeque();
                    this.skipMe = new ArrayList(3);
                }
                if (!foundAndRemovedExactReference(this.skipMe, moveDescRemoveAt.toTrickle)) {
                    this.forgetMeNot.add(moveDescRemoveAt.toTrickle);
                }
                if (!foundAndRemovedExactReference(this.forgetMeNot, moveDescRemoveAt.replaced)) {
                    this.skipMe.add(moveDescRemoveAt.replaced);
                }
            }
            this.cursor--;
            this.nextCursor--;
        }

        public final boolean removeExact(Object target) {
            for (int i = 0; i < MinMaxPriorityQueue.this.size; i++) {
                MinMaxPriorityQueue minMaxPriorityQueue = MinMaxPriorityQueue.this;
                if (minMaxPriorityQueue.queue[i] == target) {
                    minMaxPriorityQueue.removeAt(i);
                    return true;
                }
            }
            return false;
        }
    }

    public static int capAtMaximumSize(int queueSize, int maximumSize) {
        return Math.min(queueSize - 1, maximumSize) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(NaturalOrdering.INSTANCE).create(Collections.EMPTY_SET);
    }

    public static Builder<Comparable> expectedSize(int expectedSize) {
        Builder<Comparable> builder = new Builder<>(NaturalOrdering.INSTANCE);
        builder.expectedSize(expectedSize);
        return builder;
    }

    @VisibleForTesting
    public static int initialQueueSize(int configuredExpectedSize, int maximumSize, Iterable<?> initialContents) {
        if (configuredExpectedSize == -1) {
            configuredExpectedSize = 11;
        }
        if (initialContents instanceof Collection) {
            configuredExpectedSize = Math.max(configuredExpectedSize, ((Collection) initialContents).size());
        }
        return capAtMaximumSize(configuredExpectedSize, maximumSize);
    }

    @VisibleForTesting
    public static boolean isEvenLevel(int index) {
        int i = ~(~(index + 1));
        Preconditions.checkState(i > 0, "negative index");
        return (1431655765 & i) > (i & ODD_POWERS_OF_TWO);
    }

    public static Builder<Comparable> maximumSize(int maximumSize) {
        Builder<Comparable> builder = new Builder<>(NaturalOrdering.INSTANCE);
        builder.maximumSize(maximumSize);
        return builder;
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E element) {
        offer(element);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> newElements) {
        Iterator<? extends E> it = newElements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            offer(it.next());
            z = true;
        }
        return z;
    }

    public final int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    @VisibleForTesting
    public int capacity() {
        return this.queue.length;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    public E elementData(int i) {
        E e = (E) this.queue[i];
        Objects.requireNonNull(e);
        return e;
    }

    public final MoveDesc<E> fillHole(int index, E toTrickle) {
        MinMaxPriorityQueue<E>.Heap heapHeapForIndex = heapForIndex(index);
        int iFillHoleAt = heapHeapForIndex.fillHoleAt(index);
        int iBubbleUpAlternatingLevels = heapHeapForIndex.bubbleUpAlternatingLevels(iFillHoleAt, toTrickle);
        if (iBubbleUpAlternatingLevels == iFillHoleAt) {
            return heapHeapForIndex.tryCrossOverAndBubbleUp(index, iFillHoleAt, toTrickle);
        }
        if (iBubbleUpAlternatingLevels < index) {
            return new MoveDesc<>(toTrickle, elementData(index));
        }
        return null;
    }

    public final int getMaxElementIndex() {
        int i = this.size;
        if (i != 1) {
            return (i == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    public final void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    public final MinMaxPriorityQueue<E>.Heap heapForIndex(int i) {
        return isEvenLevel(i) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    public boolean isIntact() {
        for (int i = 1; i < this.size; i++) {
            if (!heapForIndex(i).verifyIndex(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E element) {
        element.getClass();
        this.modCount++;
        int i = this.size;
        this.size = i + 1;
        growIfNeeded();
        heapForIndex(i).bubbleUp(i, element);
        return this.size <= this.maximumSize || pollLast() != element;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    public final E removeAndGet(int index) {
        E eElementData = elementData(index);
        removeAt(index);
        return eElementData;
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public MoveDesc<E> removeAt(int index) {
        Preconditions.checkPositionIndex(index, this.size);
        this.modCount++;
        int i = this.size - 1;
        this.size = i;
        if (i == index) {
            this.queue[i] = null;
            return null;
        }
        E eElementData = elementData(i);
        int iSwapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(eElementData);
        if (iSwapWithConceptuallyLastElement == index) {
            this.queue[this.size] = null;
            return null;
        }
        E eElementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> moveDescFillHole = fillHole(index, eElementData2);
        return iSwapWithConceptuallyLastElement < index ? moveDescFillHole == null ? new MoveDesc<>(eElementData, eElementData2) : new MoveDesc<>(eElementData, moveDescFillHole.replaced) : moveDescFillHole;
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAndGet(getMaxElementIndex());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @J2ktIncompatible
    public Object[] toArray() {
        int i = this.size;
        Object[] objArr = new Object[i];
        System.arraycopy(this.queue, 0, objArr, 0, i);
        return objArr;
    }

    public MinMaxPriorityQueue(Builder<? super E> builder, int queueSize) {
        Ordering orderingFrom = Ordering.from(builder.comparator);
        MinMaxPriorityQueue<E>.Heap heap = new Heap(orderingFrom);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(orderingFrom.reverse());
        this.maxHeap = heap2;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = builder.maximumSize;
        this.queue = new Object[queueSize];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> initialContents) {
        return new Builder(NaturalOrdering.INSTANCE).create(initialContents);
    }
}
