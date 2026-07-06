package com.google.common.collect;

import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMakerInternalMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    public static final int LEFT_IS_GREATER = 1;
    public static final int RIGHT_IS_GREATER = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    @J2ktIncompatible
    public static class ArbitraryOrdering extends Ordering<Object> {
        public final AtomicInteger counter = new AtomicInteger(0);
        public final ConcurrentMap<Object, Integer> uids;

        public ArbitraryOrdering() {
            MapMaker mapMaker = new MapMaker();
            mapMaker.setKeyStrength(MapMakerInternalMap.Strength.WEAK);
            this.uids = mapMaker.makeMap();
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Object left, Object right) {
            if (left == right) {
                return 0;
            }
            if (left == null) {
                return -1;
            }
            if (right == null) {
                return 1;
            }
            int iIdentityHashCode = identityHashCode(left);
            int iIdentityHashCode2 = identityHashCode(right);
            if (iIdentityHashCode != iIdentityHashCode2) {
                return iIdentityHashCode < iIdentityHashCode2 ? -1 : 1;
            }
            int iCompareTo = getUid(left).compareTo(getUid(right));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            throw new AssertionError();
        }

        public final Integer getUid(Object obj) {
            Integer numPutIfAbsent;
            Integer numValueOf = this.uids.get(obj);
            return (numValueOf != null || (numPutIfAbsent = this.uids.putIfAbsent(obj, (numValueOf = Integer.valueOf(this.counter.getAndIncrement())))) == null) ? numValueOf : numPutIfAbsent;
        }

        public int identityHashCode(Object object) {
            return System.identityHashCode(object);
        }

        public String toString() {
            return "Ordering.arbitrary()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class ArbitraryOrderingHolder {
        public static final Ordering<Object> ARBITRARY_ORDERING = new ArbitraryOrdering();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IncomparableValueException extends ClassCastException {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Object value;

        public IncomparableValueException(Object value) {
            super(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot compare value: ", value));
            this.value = value;
        }
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    @J2ktIncompatible
    public static Ordering<Object> arbitrary() {
        return ArbitraryOrderingHolder.ARBITRARY_ORDERING;
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(List<T> valuesInOrder) {
        return new ExplicitOrdering(valuesInOrder);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = true)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    @Deprecated
    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.binarySearch(sortedList, key, this)")
    @InlineMeValidationDisabled("While binarySearch() is not final, the inlining is still safe as long as any overrides follow the contract.")
    public int binarySearch(List<? extends T> sortedList, @ParametricNullness T key) {
        return Collections.binarySearch(sortedList, key, this);
    }

    @Override // java.util.Comparator
    public abstract int compare(@ParametricNullness T left, @ParametricNullness T right);

    @GwtCompatible(serializable = true)
    public <U extends T> Ordering<U> compound(Comparator<? super U> secondaryComparator) {
        secondaryComparator.getClass();
        return new CompoundOrdering(this, secondaryComparator);
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int k) {
        return reverse().leastOf(iterable, k);
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> elements) {
        return ImmutableList.sortedCopyOf(this, elements);
    }

    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int k) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= ((long) k) * 2) {
                Object[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > k) {
                    array = Arrays.copyOf(array, k);
                }
                return DesugarCollections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), k);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    @ParametricNullness
    public <E extends T> E max(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = (E) max(next, it.next());
        }
        return next;
    }

    @ParametricNullness
    public <E extends T> E min(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = (E) min(next, it.next());
        }
        return next;
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return (Ordering<Map.Entry<T2, ?>>) onResultOf(Maps.EntryFunction.KEY);
    }

    @GwtCompatible(serializable = true)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        Arrays.sort(array, this);
        return Lists.newArrayList(Arrays.asList(array));
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(T leastValue, T... remainingValuesInOrder) {
        return new ExplicitOrdering(new Lists.OnePlusArrayList(leastValue, remainingValuesInOrder));
    }

    public <E extends T> List<E> greatestOf(Iterator<E> iterator, int k) {
        return reverse().leastOf(iterator, k);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> comparators) {
        return new CompoundOrdering(comparators);
    }

    @GwtCompatible(serializable = true)
    @Deprecated
    @InlineMe(replacement = "checkNotNull(ordering)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        ordering.getClass();
        return ordering;
    }

    @ParametricNullness
    public <E extends T> E max(Iterable<E> iterable) {
        return (E) max(iterable.iterator());
    }

    @ParametricNullness
    public <E extends T> E min(Iterable<E> iterable) {
        return (E) min(iterable.iterator());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ParametricNullness
    public <E extends T> E max(@ParametricNullness E a, @ParametricNullness E b) {
        return compare(a, b) >= 0 ? a : b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ParametricNullness
    public <E extends T> E min(@ParametricNullness E a, @ParametricNullness E b) {
        return compare(a, b) <= 0 ? a : b;
    }

    @ParametricNullness
    public <E extends T> E max(@ParametricNullness E e, @ParametricNullness E e2, @ParametricNullness E e3, E... eArr) {
        E e4 = (E) max(max(e, e2), e3);
        for (E e5 : eArr) {
            e4 = (E) max(e4, e5);
        }
        return e4;
    }

    @ParametricNullness
    public <E extends T> E min(@ParametricNullness E e, @ParametricNullness E e2, @ParametricNullness E e3, E... eArr) {
        E e4 = (E) min(min(e, e2), e3);
        for (E e5 : eArr) {
            e4 = (E) min(e4, e5);
        }
        return e4;
    }

    public <E extends T> List<E> leastOf(Iterator<E> iterator, int k) {
        iterator.getClass();
        CollectPreconditions.checkNonnegative(k, "k");
        if (k == 0 || !iterator.hasNext()) {
            return Collections.EMPTY_LIST;
        }
        if (k >= 1073741823) {
            ArrayList arrayListNewArrayList = Lists.newArrayList(iterator);
            Collections.sort(arrayListNewArrayList, this);
            if (arrayListNewArrayList.size() > k) {
                arrayListNewArrayList.subList(k, arrayListNewArrayList.size()).clear();
            }
            arrayListNewArrayList.trimToSize();
            return DesugarCollections.unmodifiableList(arrayListNewArrayList);
        }
        TopKSelector topKSelector = new TopKSelector(this, k);
        topKSelector.offerAll(iterator);
        return topKSelector.topK();
    }
}
