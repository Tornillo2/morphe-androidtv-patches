package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Iterables {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable val$iterable;

        public AnonymousClass1(final Iterable val$iterable) {
            this.val$iterable = val$iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.cycle(this.val$iterable);
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.val$iterable.toString() + " (cycled)";
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4<T> extends FluentIterable<T> {
        public final /* synthetic */ Predicate val$retainIfTrue;
        public final /* synthetic */ Iterable val$unfiltered;

        public AnonymousClass4(final Iterable val$unfiltered, final Predicate val$retainIfTrue) {
            this.val$unfiltered = val$unfiltered;
            this.val$retainIfTrue = val$retainIfTrue;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.filter(this.val$unfiltered.iterator(), this.val$retainIfTrue);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass7<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable val$iterable;
        public final /* synthetic */ int val$limitSize;

        public AnonymousClass7(final Iterable val$iterable, final int val$limitSize) {
            this.val$iterable = val$iterable;
            this.val$limitSize = val$limitSize;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.limit(this.val$iterable.iterator(), this.val$limitSize);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnmodifiableIterable<T> extends FluentIterable<T> {
        public final Iterable<? extends T> iterable;

        public /* synthetic */ UnmodifiableIterable(Iterable iterable, AnonymousClass1 anonymousClass1) {
            this(iterable);
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.unmodifiableIterator(this.iterable.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.iterable.toString();
        }

        public UnmodifiableIterable(Iterable<? extends T> iterable) {
            this.iterable = iterable;
        }
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> addTo, Iterable<? extends T> elementsToAdd) {
        if (elementsToAdd instanceof Collection) {
            return addTo.addAll((Collection) elementsToAdd);
        }
        elementsToAdd.getClass();
        return Iterators.addAll(addTo, elementsToAdd.iterator());
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        return iterable instanceof Collection ? (Collection) iterable : Lists.newArrayList(iterable.iterator());
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return FluentIterable.concat(a, b);
    }

    public static <T> Iterable<T> consumingIterable(final Iterable<T> iterable) {
        iterable.getClass();
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.8
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                Iterable iterable2 = iterable;
                return iterable2 instanceof Queue ? new ConsumingQueueIterator((Queue) iterable2) : Iterators.consumingIterator(iterable2.iterator());
            }

            @Override // com.google.common.collect.FluentIterable
            public String toString() {
                return "Iterables.consumingIterable(...)";
            }
        };
    }

    public static boolean contains(Iterable<?> iterable, Object element) {
        if (!(iterable instanceof Collection)) {
            return Iterators.contains(iterable.iterator(), element);
        }
        try {
            return ((Collection) iterable).contains(element);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @SafeVarargs
    public static <T> Iterable<T> cycle(T... elements) {
        return new AnonymousClass1(Lists.newArrayList(elements));
    }

    public static boolean elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2) {
        if ((iterable1 instanceof Collection) && (iterable2 instanceof Collection) && ((Collection) iterable1).size() != ((Collection) iterable2).size()) {
            return false;
        }
        return Iterators.elementsEqual(iterable1.iterator(), iterable2.iterator());
    }

    public static <T> Iterable<T> filter(Iterable<T> unfiltered, Predicate<? super T> retainIfTrue) {
        unfiltered.getClass();
        retainIfTrue.getClass();
        return new AnonymousClass4(unfiltered, retainIfTrue);
    }

    @ParametricNullness
    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return (T) Iterators.find(iterable.iterator(), predicate);
    }

    public static int frequency(Iterable<?> iterable, Object obj) {
        return iterable instanceof Multiset ? ((Multiset) iterable).count(obj) : iterable instanceof Set ? ((Set) iterable).contains(obj) ? 1 : 0 : Iterators.frequency(iterable.iterator(), obj);
    }

    @ParametricNullness
    public static <T> T get(Iterable<T> iterable, int i) {
        iterable.getClass();
        return iterable instanceof List ? (T) ((List) iterable).get(i) : (T) Iterators.get(iterable.iterator(), i);
    }

    @ParametricNullness
    public static <T> T getFirst(Iterable<? extends T> iterable, @ParametricNullness T t) {
        return (T) Iterators.getNext(iterable.iterator(), t);
    }

    @ParametricNullness
    public static <T> T getLast(Iterable<T> iterable) {
        if (!(iterable instanceof List)) {
            return (T) Iterators.getLast(iterable.iterator());
        }
        List list = (List) iterable;
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) list.get(list.size() - 1);
    }

    @ParametricNullness
    public static <T> T getLastInNonemptyList(List<T> list) {
        return list.get(list.size() - 1);
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return (T) Iterators.getOnlyElement(iterable.iterator());
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable instanceof Collection ? ((Collection) iterable).isEmpty() : !iterable.iterator().hasNext();
    }

    public static <T> Iterable<T> limit(Iterable<T> iterable, int limitSize) {
        iterable.getClass();
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new AnonymousClass7(iterable, limitSize);
    }

    public static <T> Iterable<T> mergeSorted(final Iterable<? extends Iterable<? extends T>> iterables, final Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterables, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new UnmodifiableIterable(new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.9
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.mergeSorted(Iterables.transform(iterables, new FluentIterable$2$$ExternalSyntheticLambda0()), comparator);
            }
        });
    }

    public static <T> Iterable<List<T>> paddedPartition(final Iterable<T> iterable, final int size) {
        iterable.getClass();
        Preconditions.checkArgument(size > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.3
            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.partitionImpl(iterable.iterator(), size, true);
            }
        };
    }

    public static <T> Iterable<List<T>> partition(final Iterable<T> iterable, final int size) {
        iterable.getClass();
        Preconditions.checkArgument(size > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.2
            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.partitionImpl(iterable.iterator(), size, false);
            }
        };
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterable<?> removeFrom, Collection<?> elementsToRemove) {
        if (!(removeFrom instanceof Collection)) {
            return Iterators.removeAll(removeFrom.iterator(), elementsToRemove);
        }
        elementsToRemove.getClass();
        return ((Collection) removeFrom).removeAll(elementsToRemove);
    }

    public static <T> T removeFirstMatching(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        predicate.getClass();
        Iterator<T> it = removeFrom.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                it.remove();
                return next;
            }
        }
        return null;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        if (!(removeFrom instanceof RandomAccess) || !(removeFrom instanceof List)) {
            return Iterators.removeIf(removeFrom.iterator(), predicate);
        }
        predicate.getClass();
        return removeIfFromRandomAccessList((List) removeFrom, predicate);
    }

    public static <T> boolean removeIfFromRandomAccessList(List<T> list, Predicate<? super T> predicate) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            T t = list.get(i);
            if (!predicate.apply(t)) {
                if (i > i2) {
                    try {
                        list.set(i2, t);
                    } catch (IllegalArgumentException unused) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    } catch (UnsupportedOperationException unused2) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    }
                }
                i2++;
            }
            i++;
        }
        list.subList(i2, list.size()).clear();
        return i != i2;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain) {
        if (!(removeFrom instanceof Collection)) {
            return Iterators.retainAll(removeFrom.iterator(), elementsToRetain);
        }
        elementsToRetain.getClass();
        return ((Collection) removeFrom).retainAll(elementsToRetain);
    }

    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : Iterators.size(iterable.iterator());
    }

    public static <T> Iterable<T> skip(final Iterable<T> iterable, final int numberToSkip) {
        iterable.getClass();
        Preconditions.checkArgument(numberToSkip >= 0, "number to skip cannot be negative");
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.6
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                Iterable iterable2 = iterable;
                if (iterable2 instanceof List) {
                    List list = (List) iterable2;
                    return list.subList(Math.min(list.size(), numberToSkip), list.size()).iterator();
                }
                final Iterator<T> it = iterable2.iterator();
                Iterators.advance(it, numberToSkip);
                return new Iterator<T>(this) { // from class: com.google.common.collect.Iterables.6.1
                    public boolean atStart = true;
                    public final /* synthetic */ AnonymousClass6 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override // java.util.Iterator
                    @ParametricNullness
                    public T next() {
                        T t = (T) it.next();
                        this.atStart = false;
                        return t;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        CollectPreconditions.checkRemove(!this.atStart);
                        it.remove();
                    }
                };
            }
        };
    }

    public static <T> void slowRemoveIfForRemainingElements(List<T> list, Predicate<? super T> predicate, int to, int from) {
        for (int size = list.size() - 1; size > from; size--) {
            if (predicate.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i = from - 1; i >= to; i--) {
            list.remove(i);
        }
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> cls) {
        return (T[]) toArray(iterable, (Object[]) Array.newInstance((Class<?>) cls, 0));
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    public static <F, T> Iterable<T> transform(final Iterable<F> fromIterable, final Function<? super F, ? extends T> function) {
        fromIterable.getClass();
        function.getClass();
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.5
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.transform(fromIterable.iterator(), function);
            }
        };
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    @InlineMe(replacement = "checkNotNull(iterable)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> iterable) {
        iterable.getClass();
        return iterable;
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c) {
        return FluentIterable.concat(a, b, c);
    }

    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, T t) {
        return (T) Iterators.find(iterable.iterator(), predicate, t);
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterable<? extends T> iterable, @ParametricNullness T t) {
        return (T) Iterators.getOnlyElement(iterable.iterator(), t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Iterable<T> unmodifiableIterable(Iterable<? extends T> iterable) {
        iterable.getClass();
        return ((iterable instanceof UnmodifiableIterable) || (iterable instanceof ImmutableCollection)) ? iterable : new UnmodifiableIterable(iterable);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c, Iterable<? extends T> d) {
        return FluentIterable.concat(a, b, c, d);
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        iterable.getClass();
        return new AnonymousClass1(iterable);
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, T[] tArr) {
        return (T[]) castOrCopyToCollection(iterable).toArray(tArr);
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<? extends T>... inputs) {
        return FluentIterable.concat(inputs);
    }

    @GwtIncompatible
    public static <T> Iterable<T> filter(Iterable<?> unfiltered, Class<T> desiredType) {
        unfiltered.getClass();
        desiredType.getClass();
        return filter(unfiltered, new Predicates.InstanceOfPredicate(desiredType));
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> inputs) {
        return FluentIterable.concat(inputs);
    }

    @ParametricNullness
    public static <T> T get(Iterable<? extends T> iterable, int i, @ParametricNullness T t) {
        iterable.getClass();
        Iterators.checkNonnegative(i);
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (i < list.size()) {
                return (T) list.get(i);
            }
        } else {
            Iterator<? extends T> it = iterable.iterator();
            Iterators.advance(it, i);
            if (it.hasNext()) {
                return it.next();
            }
        }
        return t;
    }

    public static Object[] toArray(Iterable<?> iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }

    @ParametricNullness
    public static <T> T getLast(Iterable<? extends T> iterable, @ParametricNullness T t) {
        if (iterable instanceof Collection) {
            if (((Collection) iterable).isEmpty()) {
                return t;
            }
            if (iterable instanceof List) {
                return (T) ((List) iterable).get(r1.size() - 1);
            }
        }
        return (T) Iterators.getLast(iterable.iterator(), t);
    }
}
