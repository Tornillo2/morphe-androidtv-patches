package com.google.common.collect;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Iterators {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2<T> implements Iterator<T> {
        public Iterator<T> iterator = EmptyModifiableIterator.INSTANCE;
        public final /* synthetic */ Iterable val$iterable;

        public AnonymousClass2(final Iterable val$iterable) {
            this.val$iterable = val$iterable;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext() || this.val$iterable.iterator().hasNext();
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            if (!this.iterator.hasNext()) {
                Iterator<T> it = this.val$iterable.iterator();
                this.iterator = it;
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
            }
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3<I> extends UnmodifiableIterator<I> {
        public int index = 0;
        public final /* synthetic */ Iterator[] val$elements;

        public AnonymousClass3(final Iterator[] val$elements) {
            this.val$elements = val$elements;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.val$elements.length;
        }

        /* JADX WARN: Incorrect return type in method signature: ()TI; */
        @Override // java.util.Iterator
        public Iterator next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Iterator it = this.val$elements[this.index];
            Objects.requireNonNull(it);
            Iterator it2 = it;
            Iterator[] itArr = this.val$elements;
            int i = this.index;
            itArr[i] = null;
            this.index = i + 1;
            return it2;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T, F] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass6<F, T> extends TransformedIterator<F, T> {
        public final /* synthetic */ Function val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(Iterator backingIterator, final Function val$function) {
            super(backingIterator);
            this.val$function = val$function;
        }

        @Override // com.google.common.collect.TransformedIterator
        @ParametricNullness
        public T transform(@ParametricNullness F f) {
            return (T) this.val$function.apply(f);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass7<T> implements Iterator<T> {
        public int count;
        public final /* synthetic */ Iterator val$iterator;
        public final /* synthetic */ int val$limitSize;

        public AnonymousClass7(final int val$limitSize, final Iterator val$iterator) {
            this.val$limitSize = val$limitSize;
            this.val$iterator = val$iterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.count < this.val$limitSize && this.val$iterator.hasNext();
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.count++;
            return (T) this.val$iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.val$iterator.remove();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ArrayItr<T> extends AbstractIndexedListIterator<T> {
        public static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr(new Object[0], 0);
        public final T[] array;

        public ArrayItr(T[] array, int position) {
            super(array.length, position);
            this.array = array;
        }

        @Override // com.google.common.collect.AbstractIndexedListIterator
        @ParametricNullness
        public T get(int index) {
            return this.array[index];
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConcatenatedIterator<T> implements Iterator<T> {
        public Iterator<? extends T> iterator = ArrayItr.EMPTY;
        public Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;
        public Iterator<? extends T> toRemove;
        public Iterator<? extends Iterator<? extends T>> topMetaIterator;

        public ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> metaIterator) {
            metaIterator.getClass();
            this.topMetaIterator = metaIterator;
        }

        public final Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it = this.topMetaIterator;
                if (it != null && it.hasNext()) {
                    return this.topMetaIterator;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.metaIterators;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.topMetaIterator = this.metaIterators.removeFirst();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                Iterator<? extends T> it = this.iterator;
                it.getClass();
                if (it.hasNext()) {
                    return true;
                }
                Iterator<? extends Iterator<? extends T>> topMetaIterator = getTopMetaIterator();
                this.topMetaIterator = topMetaIterator;
                if (topMetaIterator == null) {
                    return false;
                }
                Iterator<? extends T> next = topMetaIterator.next();
                this.iterator = next;
                if (next instanceof ConcatenatedIterator) {
                    ConcatenatedIterator concatenatedIterator = (ConcatenatedIterator) next;
                    this.iterator = concatenatedIterator.iterator;
                    if (this.metaIterators == null) {
                        this.metaIterators = new ArrayDeque();
                    }
                    this.metaIterators.addFirst(this.topMetaIterator);
                    if (concatenatedIterator.metaIterators != null) {
                        while (!concatenatedIterator.metaIterators.isEmpty()) {
                            this.metaIterators.addFirst(concatenatedIterator.metaIterators.removeLast());
                        }
                    }
                    this.topMetaIterator = concatenatedIterator.topMetaIterator;
                }
            }
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Iterator<? extends T> it = this.iterator;
            this.toRemove = it;
            return it.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            Iterator<? extends T> it = this.toRemove;
            if (it == null) {
                throw new IllegalStateException("no calls to next() since the last call to remove()");
            }
            it.remove();
            this.toRemove = null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum EmptyModifiableIterator implements Iterator<Object> {
        INSTANCE;

        public static /* synthetic */ EmptyModifiableIterator[] $values() {
            return new EmptyModifiableIterator[]{INSTANCE};
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MergingIterator<T> extends UnmodifiableIterator<T> {
        public final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterators, final Comparator<? super T> itemComparator) {
            this.queue = new PriorityQueue(2, new Comparator() { // from class: com.google.common.collect.Iterators$MergingIterator$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return itemComparator.compare(((PeekingIterator) obj).peek(), ((PeekingIterator) obj2).peek());
                }
            });
            for (Iterator<? extends T> it : iterators) {
                if (it.hasNext()) {
                    this.queue.add(Iterators.peekingIterator(it));
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            PeekingIterator<T> peekingIteratorRemove = this.queue.remove();
            T next = peekingIteratorRemove.next();
            if (peekingIteratorRemove.hasNext()) {
                this.queue.add(peekingIteratorRemove);
            }
            return next;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PeekingImpl<E> implements PeekingIterator<E> {
        public boolean hasPeeked;
        public final Iterator<? extends E> iterator;
        public E peekedElement;

        public PeekingImpl(Iterator<? extends E> iterator) {
            iterator.getClass();
            this.iterator = iterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        @ParametricNullness
        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E e = this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return e;
        }

        @Override // com.google.common.collect.PeekingIterator
        @ParametricNullness
        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return this.peekedElement;
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SingletonIterator<T> extends UnmodifiableIterator<T> {
        public boolean done;
        public final T value;

        public SingletonIterator(T value) {
            this.value = value;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.done;
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            if (this.done) {
                throw new NoSuchElementException();
            }
            this.done = true;
            return this.value;
        }
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator) {
        addTo.getClass();
        iterator.getClass();
        boolean zAdd = false;
        while (iterator.hasNext()) {
            zAdd |= addTo.add(iterator.next());
        }
        return zAdd;
    }

    @CanIgnoreReturnValue
    public static int advance(Iterator<?> iterator, int numberToAdvance) {
        iterator.getClass();
        int i = 0;
        Preconditions.checkArgument(numberToAdvance >= 0, "numberToAdvance must be nonnegative");
        while (i < numberToAdvance && iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }

    public static <T> boolean all(Iterator<T> iterator, Predicate<? super T> predicate) {
        predicate.getClass();
        while (iterator.hasNext()) {
            if (!predicate.apply(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean any(Iterator<T> iterator, Predicate<? super T> predicate) {
        return indexOf(iterator, predicate) != -1;
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> iterator) {
        iterator.getClass();
        return new Enumeration<T>() { // from class: com.google.common.collect.Iterators.10
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            @Override // java.util.Enumeration
            @ParametricNullness
            public T nextElement() {
                return (T) iterator.next();
            }
        };
    }

    public static void checkNonnegative(int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("position (", position, ") must not be negative"));
        }
    }

    public static void clear(Iterator<?> iterator) {
        iterator.getClass();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @SafeVarargs
    public static <T> Iterator<T> concat(Iterator<? extends T>... inputs) {
        return concatNoDefensiveCopy((Iterator[]) Arrays.copyOf(inputs, inputs.length));
    }

    public static <T> Iterator<T> concatNoDefensiveCopy(Iterator<? extends T>... inputs) {
        inputs.getClass();
        for (Iterator<? extends T> it : inputs) {
            it.getClass();
        }
        return new ConcatenatedIterator(new AnonymousClass3(inputs));
    }

    public static <I extends Iterator<?>> Iterator<I> consumingForArray(I... elements) {
        return new AnonymousClass3(elements);
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> iterator) {
        iterator.getClass();
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.8
            @Override // java.util.Iterator
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public T next() {
                T t = (T) iterator.next();
                iterator.remove();
                return t;
            }

            public String toString() {
                return "Iterators.consumingIterator(...)";
            }
        };
    }

    public static boolean contains(Iterator<?> iterator, Object element) {
        if (element == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (iterator.hasNext()) {
            if (element.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T... elements) {
        return new AnonymousClass2(Lists.newArrayList(elements));
    }

    public static boolean elementsEqual(Iterator<?> iterator1, Iterator<?> iterator2) {
        while (iterator1.hasNext()) {
            if (!iterator2.hasNext() || !com.google.common.base.Objects.equal(iterator1.next(), iterator2.next())) {
                return false;
            }
        }
        return !iterator2.hasNext();
    }

    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return ArrayItr.EMPTY;
    }

    public static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return (UnmodifiableListIterator<T>) ArrayItr.EMPTY;
    }

    public static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    @GwtIncompatible
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> unfiltered, Class<T> desiredType) {
        return filter(unfiltered, new Predicates.InstanceOfPredicate(desiredType));
    }

    @ParametricNullness
    public static <T> T find(Iterator<T> iterator, Predicate<? super T> predicate) {
        iterator.getClass();
        predicate.getClass();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... array) {
        return forArrayWithPosition(array, 0);
    }

    public static <T> UnmodifiableListIterator<T> forArrayWithPosition(T[] tArr, int i) {
        if (tArr.length != 0) {
            return new ArrayItr(tArr, i);
        }
        Preconditions.checkPositionIndex(i, tArr.length);
        return (UnmodifiableListIterator<T>) ArrayItr.EMPTY;
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        enumeration.getClass();
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.9
            @Override // java.util.Iterator
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public T next() {
                return (T) enumeration.nextElement();
            }
        };
    }

    public static int frequency(Iterator<?> iterator, Object element) {
        int i = 0;
        while (contains(iterator, element)) {
            i++;
        }
        return i;
    }

    @ParametricNullness
    public static <T> T get(Iterator<T> iterator, int position) {
        checkNonnegative(position);
        int iAdvance = advance(iterator, position);
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline0.m("position (", position, ") must be less than the number of elements that remained (", iAdvance, ")"));
    }

    @ParametricNullness
    public static <T> T getLast(Iterator<T> iterator) {
        T next;
        do {
            next = iterator.next();
        } while (iterator.hasNext());
        return next;
    }

    @ParametricNullness
    public static <T> T getNext(Iterator<? extends T> iterator, @ParametricNullness T defaultValue) {
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterator<T> iterator) {
        T next = iterator.next();
        if (!iterator.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && iterator.hasNext(); i++) {
            sb.append(", ");
            sb.append(iterator.next());
        }
        if (iterator.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> int indexOf(Iterator<T> iterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (iterator.hasNext()) {
            if (predicate.apply(iterator.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T> Iterator<T> limit(Iterator<T> iterator, int limitSize) {
        iterator.getClass();
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new AnonymousClass7(limitSize, iterator);
    }

    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterators, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterators, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator(iterators, comparator);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> iterator, int size) {
        return partitionImpl(iterator, size, true);
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> iterator, int size) {
        return partitionImpl(iterator, size, false);
    }

    public static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> iterator, final int size, final boolean pad) {
        iterator.getClass();
        Preconditions.checkArgument(size > 0);
        return new UnmodifiableIterator<List<T>>() { // from class: com.google.common.collect.Iterators.4
            @Override // java.util.Iterator
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override // java.util.Iterator
            public List<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object[] objArr = new Object[size];
                int i = 0;
                while (i < size && iterator.hasNext()) {
                    objArr[i] = iterator.next();
                    i++;
                }
                for (int i2 = i; i2 < size; i2++) {
                    objArr[i2] = null;
                }
                List<T> listUnmodifiableList = DesugarCollections.unmodifiableList(Arrays.asList(objArr));
                return (pad || i == size) ? listUnmodifiableList : listUnmodifiableList.subList(0, i);
            }
        };
    }

    @InlineMe(replacement = "checkNotNull(iterator)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> iterator) {
        iterator.getClass();
        return iterator;
    }

    public static <T> T pollNext(Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            return null;
        }
        T next = iterator.next();
        iterator.remove();
        return next;
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterator<?> removeFrom, Collection<?> elementsToRemove) {
        elementsToRemove.getClass();
        boolean z = false;
        while (removeFrom.hasNext()) {
            if (elementsToRemove.contains(removeFrom.next())) {
                removeFrom.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterator<T> removeFrom, Predicate<? super T> predicate) {
        predicate.getClass();
        boolean z = false;
        while (removeFrom.hasNext()) {
            if (predicate.apply(removeFrom.next())) {
                removeFrom.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterator<?> removeFrom, Collection<?> elementsToRetain) {
        elementsToRetain.getClass();
        boolean z = false;
        while (removeFrom.hasNext()) {
            if (!elementsToRetain.contains(removeFrom.next())) {
                removeFrom.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(@ParametricNullness T value) {
        return new SingletonIterator(value);
    }

    public static int size(Iterator<?> iterator) {
        long j = 0;
        while (iterator.hasNext()) {
            iterator.next();
            j++;
        }
        return Ints.saturatedCast(j);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterator<? extends T> it, Class<T> cls) {
        return (T[]) Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static String toString(Iterator<?> iterator) {
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (iterator.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(iterator.next());
            z = false;
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    public static <F, T> Iterator<T> transform(Iterator<F> fromIterator, Function<? super F, ? extends T> function) {
        function.getClass();
        return new AnonymousClass6(fromIterator, function);
    }

    public static <T> Optional<T> tryFind(Iterator<T> iterator, Predicate<? super T> predicate) {
        iterator.getClass();
        predicate.getClass();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.apply(next)) {
                return Optional.of(next);
            }
        }
        return Absent.INSTANCE;
    }

    @InlineMe(replacement = "checkNotNull(iterator)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> iterator) {
        iterator.getClass();
        return iterator;
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> inputs) {
        return new ConcatenatedIterator(inputs);
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> iterator) {
        return iterator instanceof PeekingImpl ? (PeekingImpl) iterator : new PeekingImpl(iterator);
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<? extends T> iterator) {
        iterator.getClass();
        return iterator instanceof UnmodifiableIterator ? (UnmodifiableIterator) iterator : new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public T next() {
                return (T) iterator.next();
            }
        };
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b) {
        a.getClass();
        b.getClass();
        return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{a, b}));
    }

    public static <T> Iterator<T> cycle(Iterable<T> iterable) {
        iterable.getClass();
        return new AnonymousClass2(iterable);
    }

    @ParametricNullness
    public static <T> T getLast(Iterator<? extends T> iterator, @ParametricNullness T defaultValue) {
        if (iterator.hasNext()) {
            do {
                defaultValue = iterator.next();
            } while (iterator.hasNext());
        }
        return defaultValue;
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> unfiltered, final Predicate<? super T> retainIfTrue) {
        unfiltered.getClass();
        retainIfTrue.getClass();
        return new AbstractIterator<T>() { // from class: com.google.common.collect.Iterators.5
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // com.google.common.collect.AbstractIterator
            public T computeNext() {
                while (unfiltered.hasNext()) {
                    T t = (T) unfiltered.next();
                    if (retainIfTrue.apply(t)) {
                        return t;
                    }
                }
                endOfData();
                return null;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    public static <T> T find(Iterator<? extends T> it, Predicate<? super T> predicate, T t) {
        it.getClass();
        predicate.getClass();
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        return t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c) {
        a.getClass();
        b.getClass();
        c.getClass();
        return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{a, b, c}));
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterator<? extends T> it, @ParametricNullness T t) {
        return it.hasNext() ? (T) getOnlyElement(it) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c, Iterator<? extends T> d) {
        a.getClass();
        b.getClass();
        c.getClass();
        d.getClass();
        return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{a, b, c, d}));
    }

    @ParametricNullness
    public static <T> T get(Iterator<? extends T> iterator, int position, @ParametricNullness T defaultValue) {
        checkNonnegative(position);
        advance(iterator, position);
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }
}
