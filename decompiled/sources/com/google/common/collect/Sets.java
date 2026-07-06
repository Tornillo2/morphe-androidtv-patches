package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.Collector;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Sets {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Sets$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2<E> extends SetView<E> {
        public final /* synthetic */ Set val$set1;
        public final /* synthetic */ Set val$set2;

        public AnonymousClass2(final Set val$set1, final Set val$set2) {
            this.val$set1 = val$set1;
            this.val$set2 = val$set2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            return this.val$set1.contains(object) && this.val$set2.contains(object);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return this.val$set1.containsAll(collection) && this.val$set2.containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return Collections.disjoint(this.val$set2, this.val$set1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            Iterator<E> it = this.val$set1.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (this.val$set2.contains(it.next())) {
                    i++;
                }
            }
            return i;
        }

        @Override // com.google.common.collect.Sets.SetView
        public int upperBoundSize() {
            return Math.min(SetView.upperBoundSize(this.val$set1), SetView.upperBoundSize(this.val$set2));
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.2.1
                public final Iterator<E> itr;

                {
                    this.itr = AnonymousClass2.this.val$set1.iterator();
                }

                @Override // com.google.common.collect.AbstractIterator
                public E computeNext() {
                    while (this.itr.hasNext()) {
                        E next = this.itr.next();
                        if (AnonymousClass2.this.val$set2.contains(next)) {
                            return next;
                        }
                    }
                    endOfData();
                    return null;
                }
            };
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Sets$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5<E> extends AbstractSet<Set<E>> {
        public final /* synthetic */ ImmutableMap val$index;
        public final /* synthetic */ int val$size;

        /* JADX INFO: renamed from: com.google.common.collect.Sets$5$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends AbstractIterator<Set<E>> {
            public final BitSet bits;

            public AnonymousClass1() {
                this.bits = new BitSet(AnonymousClass5.this.val$index.size());
            }

            @Override // com.google.common.collect.AbstractIterator
            public Set<E> computeNext() {
                if (this.bits.isEmpty()) {
                    this.bits.set(0, AnonymousClass5.this.val$size);
                } else {
                    int iNextSetBit = this.bits.nextSetBit(0);
                    int iNextClearBit = this.bits.nextClearBit(iNextSetBit);
                    if (iNextClearBit == AnonymousClass5.this.val$index.size()) {
                        endOfData();
                        return null;
                    }
                    int i = (iNextClearBit - iNextSetBit) - 1;
                    this.bits.set(0, i);
                    this.bits.clear(i, iNextClearBit);
                    this.bits.set(iNextClearBit);
                }
                final BitSet bitSet = (BitSet) this.bits.clone();
                return new AbstractSet<E>(this) { // from class: com.google.common.collect.Sets.5.1.1
                    public final /* synthetic */ AnonymousClass1 this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public boolean contains(Object o) {
                        Integer num = (Integer) AnonymousClass5.this.val$index.get(o);
                        return num != null && bitSet.get(num.intValue());
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                    public Iterator<E> iterator() {
                        return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.5.1.1.1
                            public int i = -1;

                            @Override // com.google.common.collect.AbstractIterator
                            public E computeNext() {
                                int iNextSetBit2 = bitSet.nextSetBit(this.i + 1);
                                this.i = iNextSetBit2;
                                if (iNextSetBit2 != -1) {
                                    return AnonymousClass5.this.val$index.keySet().asList().get(this.i);
                                }
                                endOfData();
                                return null;
                            }
                        };
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public int size() {
                        return AnonymousClass5.this.val$size;
                    }
                };
            }
        }

        public AnonymousClass5(final int val$size, final ImmutableMap val$index) {
            this.val$size = val$size;
            this.val$index = val$index;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (o instanceof Set) {
                Set set = (Set) o;
                if (set.size() == this.val$size && this.val$index.keySet().containsAll(set)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntMath.binomial(this.val$index.size(), this.val$size);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder("Sets.combinations(");
            sb.append(this.val$index.keySet());
            sb.append(", ");
            return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.val$size, ")");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        public final transient ImmutableList<ImmutableSet<E>> axes;
        public final transient CartesianList<E> delegate;

        public CartesianSet(ImmutableList<ImmutableSet<E>> axes, CartesianList<E> delegate) {
            this.axes = axes;
            this.delegate = delegate;
        }

        public static <E> Set<List<E>> create(List<? extends Set<? extends E>> sets) {
            ImmutableList.Builder builder = new ImmutableList.Builder(sets.size());
            Iterator<? extends Set<? extends E>> it = sets.iterator();
            while (it.hasNext()) {
                ImmutableSet immutableSetCopyOf = ImmutableSet.copyOf((Collection) it.next());
                if (immutableSetCopyOf.isEmpty()) {
                    return RegularImmutableSet.EMPTY;
                }
                builder.add(immutableSetCopyOf);
            }
            final ImmutableList<E> immutableListBuild = builder.build();
            return new CartesianSet(immutableListBuild, new CartesianList(new ImmutableList<List<E>>() { // from class: com.google.common.collect.Sets.CartesianSet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return immutableListBuild.size();
                }

                @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
                @J2ktIncompatible
                @GwtIncompatible
                public Object writeReplace() {
                    return super.writeReplace();
                }

                @Override // java.util.List
                public List<E> get(int i) {
                    return ((ImmutableSet) immutableListBuild.get(i)).asList();
                }
            }));
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean contains(Object object) {
            if (!(object instanceof List)) {
                return false;
            }
            List list = (List) object;
            if (list.size() != this.axes.size()) {
                return false;
            }
            Iterator<E> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (!this.axes.get(i).contains(it.next())) {
                    return false;
                }
                i++;
            }
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object object) {
            if (object instanceof CartesianSet) {
                return this.axes.equals(((CartesianSet) object).axes);
            }
            if (!(object instanceof Set)) {
                return false;
            }
            Set set = (Set) object;
            return size() == set.size() && containsAll(set);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int i = 1;
            int size = size() - 1;
            for (int i2 = 0; i2 < this.axes.size(); i2++) {
                size = ~(~(size * 31));
            }
            UnmodifiableIterator<ImmutableSet<E>> it = this.axes.iterator();
            while (it.hasNext()) {
                ImmutableSet<E> next = it.next();
                i = ~(~((next.hashCode() * (size() / next.size())) + (i * 31)));
            }
            return ~(~(i + size));
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<List<E>> delegate() {
            return this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        public final NavigableSet<E> forward;

        public DescendingSet(NavigableSet<E> forward) {
            this.forward = forward;
        }

        public static <T> Ordering<T> reverse(Comparator<T> forward) {
            return Ordering.from(forward).reverse();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E ceiling(@ParametricNullness E e) {
            return this.forward.floor(e);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator = this.forward.comparator();
            if (comparator != null) {
                return reverse(comparator);
            }
            NaturalOrdering.INSTANCE.getClass();
            return ReverseNaturalOrdering.INSTANCE;
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        @ParametricNullness
        public E first() {
            return this.forward.last();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E floor(@ParametricNullness E e) {
            return this.forward.ceiling(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return this.forward.tailSet(toElement, inclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E higher(@ParametricNullness E e) {
            return this.forward.lower(e);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        @ParametricNullness
        public E last() {
            return this.forward.first();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E lower(@ParametricNullness E e) {
            return this.forward.higher(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollFirst() {
            return this.forward.pollLast();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollLast() {
            return this.forward.pollFirst();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return this.forward.headSet(fromElement, inclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return standardHeadSet(toElement);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return standardSubSet(fromElement, toElement);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return standardTailSet(fromElement);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.forward;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        public FilteredNavigableSet(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
            super((Collection) unfiltered, (Predicate) predicate);
        }

        @Override // java.util.NavigableSet
        public E ceiling(@ParametricNullness E e) {
            return (E) Iterables.find(unfiltered().tailSet(e, true), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Sets.filter((NavigableSet) unfiltered().descendingSet(), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public E floor(@ParametricNullness E e) {
            return (E) Iterators.find(unfiltered().headSet(e, true).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return Sets.filter((NavigableSet) unfiltered().headSet(toElement, inclusive), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public E higher(@ParametricNullness E e) {
            return (E) Iterables.find(unfiltered().tailSet(e, false), this.predicate, null);
        }

        @Override // com.google.common.collect.Sets.FilteredSortedSet, java.util.SortedSet
        @ParametricNullness
        public E last() {
            return (E) Iterators.find(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public E lower(@ParametricNullness E e) {
            return (E) Iterators.find(unfiltered().headSet(e, false).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) Iterables.removeFirstMatching(unfiltered(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return Sets.filter((NavigableSet) unfiltered().subSet(fromElement, fromInclusive, toElement, toInclusive), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return Sets.filter((NavigableSet) unfiltered().tailSet(fromElement, inclusive), (Predicate) this.predicate);
        }

        public NavigableSet<E> unfiltered() {
            return (NavigableSet) this.unfiltered;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        public FilteredSet(Set<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object object) {
            return Sets.equalsImpl(this, object);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        public FilteredSortedSet(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
            super((Collection) unfiltered, (Predicate) predicate);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        @Override // java.util.SortedSet
        @ParametricNullness
        public E first() {
            return (E) Iterators.find(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return new FilteredSortedSet((Collection) ((SortedSet) this.unfiltered).headSet(toElement), (Predicate) this.predicate);
        }

        @ParametricNullness
        public E last() {
            SortedSet sortedSetHeadSet = (SortedSet) this.unfiltered;
            while (true) {
                E e = (Object) sortedSetHeadSet.last();
                if (this.predicate.apply(e)) {
                    return e;
                }
                sortedSetHeadSet = sortedSetHeadSet.headSet(e);
            }
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return new FilteredSortedSet((Collection) ((SortedSet) this.unfiltered).subSet(fromElement, toElement), (Predicate) this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return new FilteredSortedSet((Collection) ((SortedSet) this.unfiltered).tailSet(fromElement), (Predicate) this.predicate);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            return Sets.removeAllImpl(this, c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            c.getClass();
            return super.retainAll(c);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PowerSet<E> extends AbstractSet<Set<E>> {
        public final ImmutableMap<E, Integer> inputSet;

        public PowerSet(Set<E> input) {
            Preconditions.checkArgument(input.size() <= 30, "Too many elements to create power set: %s > 30", input.size());
            this.inputSet = Maps.indexMap(input);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.inputSet.keySet().containsAll((Set) obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj instanceof PowerSet ? this.inputSet.keySet().equals(((PowerSet) obj).inputSet.keySet()) : super.equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return this.inputSet.keySet().hashCode() << (this.inputSet.size() - 1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) { // from class: com.google.common.collect.Sets.PowerSet.1
                @Override // com.google.common.collect.AbstractIndexedListIterator
                public Set<E> get(int setBits) {
                    return new SubSet(PowerSet.this.inputSet, setBits);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1 << this.inputSet.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "powerSet(" + this.inputSet + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SetView<E> extends AbstractSet<E> {
        public SetView() {
        }

        public static int upperBoundSize(Set<?> set) {
            return set instanceof SetView ? ((SetView) set).upperBoundSize() : set.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        @CanIgnoreReturnValue
        public final boolean add(@ParametricNullness E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        @CanIgnoreReturnValue
        public final boolean addAll(Collection<? extends E> newElements) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @CanIgnoreReturnValue
        public <S extends Set<E>> S copyInto(S set) {
            set.addAll(this);
            return set;
        }

        public ImmutableSet<E> immutableCopy() {
            int iUpperBoundSize = upperBoundSize();
            if (iUpperBoundSize == 0) {
                return RegularImmutableSet.EMPTY;
            }
            ImmutableSet.Builder builderBuilderWithExpectedSize = ImmutableSet.builderWithExpectedSize(iUpperBoundSize);
            UnmodifiableIterator<E> it = iterator();
            while (it.hasNext()) {
                E next = it.next();
                next.getClass();
                builderBuilderWithExpectedSize.add((Object) next);
            }
            return builderBuilderWithExpectedSize.build();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract UnmodifiableIterator<E> iterator();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        public final boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        @CanIgnoreReturnValue
        public final boolean removeAll(Collection<?> oldElements) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @DoNotCall("Always throws UnsupportedOperationException")
        @Deprecated
        @CanIgnoreReturnValue
        public final boolean retainAll(Collection<?> elementsToKeep) {
            throw new UnsupportedOperationException();
        }

        public abstract int upperBoundSize();

        public SetView(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SubSet<E> extends AbstractSet<E> {
        public final ImmutableMap<E, Integer> inputSet;
        public final int mask;

        public SubSet(ImmutableMap<E, Integer> inputSet, int mask) {
            this.inputSet = inputSet;
            this.mask = mask;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            Integer num = this.inputSet.get(o);
            if (num != null) {
                return ((1 << num.intValue()) & this.mask) != 0;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new UnmodifiableIterator<E>() { // from class: com.google.common.collect.Sets.SubSet.1
                public final ImmutableList<E> elements;
                public int remainingSetBits;

                {
                    this.elements = SubSet.this.inputSet.keySet().asList();
                    this.remainingSetBits = SubSet.this.mask;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.remainingSetBits != 0;
                }

                @Override // java.util.Iterator
                public E next() {
                    int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(this.remainingSetBits);
                    if (iNumberOfTrailingZeros == 32) {
                        throw new NoSuchElementException();
                    }
                    this.remainingSetBits &= ~(1 << iNumberOfTrailingZeros);
                    return this.elements.get(iNumberOfTrailingZeros);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Integer.bitCount(this.mask);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final NavigableSet<E> delegate;

        @LazyInit
        public transient UnmodifiableNavigableSet<E> descendingSet;
        public final SortedSet<E> unmodifiableDelegate;

        public UnmodifiableNavigableSet(NavigableSet<E> delegate) {
            delegate.getClass();
            this.delegate = delegate;
            this.unmodifiableDelegate = DesugarCollections.unmodifiableSortedSet(delegate);
        }

        @Override // java.util.NavigableSet
        public E ceiling(@ParametricNullness E e) {
            return this.delegate.ceiling(e);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.unmodifiableDelegate;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet = this.descendingSet;
            if (unmodifiableNavigableSet != null) {
                return unmodifiableNavigableSet;
            }
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet2 = new UnmodifiableNavigableSet<>(this.delegate.descendingSet());
            this.descendingSet = unmodifiableNavigableSet2;
            unmodifiableNavigableSet2.descendingSet = this;
            return unmodifiableNavigableSet2;
        }

        @Override // java.util.NavigableSet
        public E floor(@ParametricNullness E e) {
            return this.delegate.floor(e);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public E higher(@ParametricNullness E e) {
            return this.delegate.higher(e);
        }

        @Override // java.util.NavigableSet
        public E lower(@ParametricNullness E e) {
            return this.delegate.lower(e);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(fromElement, inclusive));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection delegate() {
            return this.unmodifiableDelegate;
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set delegate() {
            return this.unmodifiableDelegate;
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.unmodifiableDelegate;
        }
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> sets) {
        return CartesianSet.create(sets);
    }

    public static <E> Set<Set<E>> combinations(Set<E> set, int size) {
        ImmutableMap immutableMapIndexMap = Maps.indexMap(set);
        CollectPreconditions.checkNonnegative(size, "size");
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) immutableMapIndexMap;
        Preconditions.checkArgument(size <= ((RegularImmutableMap) immutableMapIndexMap).size, "size (%s) must be <= set.size() (%s)", size, regularImmutableMap.size);
        return size == 0 ? new SingletonImmutableSet(ImmutableSet.of()) : size == regularImmutableMap.size ? ImmutableSet.of(immutableMapIndexMap.keySet()) : new AnonymousClass5(size, immutableMapIndexMap);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, collection.iterator().next().getDeclaringClass());
    }

    public static <E> SetView<E> difference(final Set<E> set1, final Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.3

            /* JADX INFO: renamed from: com.google.common.collect.Sets$3$1, reason: invalid class name */
            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public class AnonymousClass1 extends AbstractIterator<E> {
                public final Iterator<E> itr;

                public AnonymousClass1() {
                    this.itr = set1.iterator();
                }

                @Override // com.google.common.collect.AbstractIterator
                public E computeNext() {
                    while (this.itr.hasNext()) {
                        E next = this.itr.next();
                        if (!set2.contains(next)) {
                            return next;
                        }
                    }
                    endOfData();
                    return null;
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object element) {
                return set1.contains(element) && !set2.contains(element);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set2.containsAll(set1);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                Iterator<E> it = set1.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (!set2.contains(it.next())) {
                        i++;
                    }
                }
                return i;
            }

            @Override // com.google.common.collect.Sets.SetView
            public int upperBoundSize() {
                return SetView.upperBoundSize(set1);
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return new AnonymousClass1();
            }
        };
    }

    public static boolean equalsImpl(Set<?> s, Object object) {
        if (s == object) {
            return true;
        }
        if (object instanceof Set) {
            Set set = (Set) object;
            try {
                if (s.size() == set.size()) {
                    if (s.containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <E> Set<E> filter(Set<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof SortedSet) {
            return filter((SortedSet) unfiltered, (Predicate) predicate);
        }
        if (unfiltered instanceof FilteredSet) {
            return new FilteredSet(r1.unfiltered, Predicates.and(((FilteredSet) unfiltered).predicate, predicate));
        }
        unfiltered.getClass();
        predicate.getClass();
        return new FilteredSet((Collection) unfiltered, (Predicate) predicate);
    }

    public static int hashCodeImpl(Set<?> s) {
        Iterator<?> it = s.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E anElement, E... otherElements) {
        return ImmutableEnumSet.asImmutable(EnumSet.of((Enum) anElement, (Enum[]) otherElements));
    }

    public static <E> SetView<E> intersection(Set<E> set1, Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new AnonymousClass2(set1, set2);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> type) {
        EnumSet<E> enumSetAllOf = EnumSet.allOf(type);
        enumSetAllOf.removeAll(collection);
        return enumSetAllOf;
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> elementType) {
        EnumSet<E> enumSetNoneOf = EnumSet.noneOf(elementType);
        Iterables.addAll(enumSetNoneOf, iterable);
        return enumSetNoneOf;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(Maps.capacity(expectedSize));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return new LinkedHashSet<>(Maps.capacity(expectedSize));
    }

    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.newSetFromMap(map)")
    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    public static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        collection.getClass();
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return (!(collection instanceof Set) || collection.size() <= set.size()) ? removeAllImpl(set, collection.iterator()) : Iterators.removeAll(set.iterator(), collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> set, Range<K> range) {
        if (set.comparator() != null && set.comparator() != NaturalOrdering.INSTANCE && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(set.comparator().compare(range.lowerBound.endpoint(), range.upperBound.endpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            Comparable comparableEndpoint = range.lowerBound.endpoint();
            BoundType boundTypeTypeAsLowerBound = range.lowerBound.typeAsLowerBound();
            BoundType boundType = BoundType.CLOSED;
            return set.subSet(comparableEndpoint, boundTypeTypeAsLowerBound == boundType, range.upperBound.endpoint(), range.upperBound.typeAsUpperBound() == boundType);
        }
        if (range.hasLowerBound()) {
            return set.tailSet(range.lowerBound.endpoint(), range.lowerBound.typeAsLowerBound() == BoundType.CLOSED);
        }
        if (range.hasUpperBound()) {
            return set.headSet(range.upperBound.endpoint(), range.upperBound.typeAsUpperBound() == BoundType.CLOSED);
        }
        return set;
    }

    public static <E> SetView<E> symmetricDifference(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.4
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object element) {
                return set2.contains(element) ^ set1.contains(element);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set1.equals(set2);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                Iterator<E> it = set1.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (!set2.contains(it.next())) {
                        i++;
                    }
                }
                Iterator<E> it2 = set2.iterator();
                while (it2.hasNext()) {
                    if (!set1.contains(it2.next())) {
                        i++;
                    }
                }
                return i;
            }

            @Override // com.google.common.collect.Sets.SetView
            public int upperBoundSize() {
                return SetView.upperBoundSize(set2) + SetView.upperBoundSize(set1);
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                final Iterator<E> it = set1.iterator();
                final Iterator<E> it2 = set2.iterator();
                return new AbstractIterator<E>(this) { // from class: com.google.common.collect.Sets.4.1
                    public final /* synthetic */ AnonymousClass4 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (it.hasNext()) {
                            E e = (E) it.next();
                            if (!set2.contains(e)) {
                                return e;
                            }
                        }
                        while (it2.hasNext()) {
                            E e2 = (E) it2.next();
                            if (!set1.contains(e2)) {
                                return e2;
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.navigableSet(navigableSet);
    }

    @IgnoreJRERequirement
    public static <E extends Enum<E>> Collector<E, ?, ImmutableSet<E>> toImmutableEnumSet() {
        return CollectCollectors.toImmutableEnumSet();
    }

    public static <E> SetView<E> union(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object object) {
                return set1.contains(object) || set2.contains(object);
            }

            @Override // com.google.common.collect.Sets.SetView
            public <S extends Set<E>> S copyInto(S set) {
                set.addAll(set1);
                set.addAll(set2);
                return set;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set1.isEmpty() && set2.isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int size = set1.size();
                Iterator<E> it = set2.iterator();
                while (it.hasNext()) {
                    if (!set1.contains(it.next())) {
                        size++;
                    }
                }
                return size;
            }

            @Override // com.google.common.collect.Sets.SetView
            public int upperBoundSize() {
                return SetView.upperBoundSize(set2) + SetView.upperBoundSize(set1);
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.1.1
                    public final Iterator<? extends E> itr1;
                    public final Iterator<? extends E> itr2;

                    {
                        this.itr1 = set1.iterator();
                        this.itr2 = set2.iterator();
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        if (this.itr1.hasNext()) {
                            return this.itr1.next();
                        }
                        while (this.itr2.hasNext()) {
                            E next = this.itr2.next();
                            if (!set1.contains(next)) {
                                return next;
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
        };
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> set) {
        return ((set instanceof ImmutableCollection) || (set instanceof UnmodifiableNavigableSet)) ? set : new UnmodifiableNavigableSet(set);
    }

    @SafeVarargs
    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... sets) {
        return CartesianSet.create(Arrays.asList(sets));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> elements) {
        if (elements instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) elements;
        }
        if (elements instanceof Collection) {
            Collection collection = (Collection) elements;
            return collection.isEmpty() ? ImmutableSet.of() : ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator<E> it = elements.iterator();
        if (!it.hasNext()) {
            return ImmutableSet.of();
        }
        EnumSet enumSetOf = EnumSet.of((Enum) it.next());
        Iterators.addAll(enumSetOf, it);
        return ImmutableEnumSet.asImmutable(enumSetOf);
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> elements) {
        Set<E> setNewConcurrentHashSet = newConcurrentHashSet();
        Iterables.addAll(setNewConcurrentHashSet, elements);
        return setNewConcurrentHashSet;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterable<? extends E> elements) {
        return new CopyOnWriteArraySet<>(elements instanceof Collection ? (Collection) elements : Lists.newArrayList(elements));
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
        HashSet<E> hashSet = new HashSet<>();
        Iterators.addAll(hashSet, elements);
        return hashSet;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new LinkedHashSet<>((Collection) elements);
        }
        LinkedHashSet<E> linkedHashSet = new LinkedHashSet<>();
        Iterables.addAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
        TreeSet<E> treeSet = new TreeSet<>();
        Iterables.addAll(treeSet, elements);
        return treeSet;
    }

    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> hashSetNewHashSetWithExpectedSize = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(hashSetNewHashSetWithExpectedSize, elements);
        return hashSetNewHashSetWithExpectedSize;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        comparator.getClass();
        return new TreeSet<>(comparator);
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new HashSet<>((Collection) elements);
        }
        return newHashSet(elements.iterator());
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> type) {
        collection.getClass();
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        return makeComplementByHand(collection, type);
    }

    public static boolean removeAllImpl(Set<?> set, Iterator<?> iterator) {
        boolean zRemove = false;
        while (iterator.hasNext()) {
            zRemove |= set.remove(iterator.next());
        }
        return zRemove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> SortedSet<E> filter(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof FilteredSet) {
            return new FilteredSortedSet(r1.unfiltered, Predicates.and(((FilteredSet) unfiltered).predicate, predicate));
        }
        unfiltered.getClass();
        predicate.getClass();
        return new FilteredSortedSet((Collection) unfiltered, (Predicate) predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <E> NavigableSet<E> filter(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof FilteredSet) {
            return new FilteredNavigableSet(r1.unfiltered, Predicates.and(((FilteredSet) unfiltered).predicate, predicate));
        }
        unfiltered.getClass();
        predicate.getClass();
        return new FilteredNavigableSet((Collection) unfiltered, (Predicate) predicate);
    }
}
