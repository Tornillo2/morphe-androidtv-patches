package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.DesugarCollections;
import j$.util.stream.Collector;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Multisets {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Multisets$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5<E> extends TransformedIterator<Multiset.Entry<E>, E> {
        public AnonymousClass5(Iterator backingIterator) {
            super(backingIterator);
        }

        @Override // com.google.common.collect.TransformedIterator
        @ParametricNullness
        public E transform(Multiset.Entry<E> entry) {
            return entry.getElement();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(Object object) {
            if (object instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) object;
                if (getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            E element = getElement();
            return (element == null ? 0 : element.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String strValueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return strValueOf;
            }
            return strValueOf + " x " + count;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DecreasingCount implements Comparator<Multiset.Entry<?>> {
        public static final Comparator<Multiset.Entry<?>> INSTANCE = new DecreasingCount();

        @Override // java.util.Comparator
        public int compare(Multiset.Entry<?> entry1, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry1.getCount();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return multiset().contains(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return multiset().containsAll(c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return multiset().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract Iterator<E> iterator();

        public abstract Multiset<E> multiset();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return multiset().remove(o, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return multiset().entrySet().size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) o;
            return entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount();
        }

        public abstract Multiset<E> multiset();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                Object element = entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return multiset().setCount(element, count, 0);
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FilteredMultiset<E> extends ViewMultiset<E> {
        public final Predicate<? super E> predicate;
        public final Multiset<E> unfiltered;

        public FilteredMultiset(Multiset<E> unfiltered, Predicate<? super E> predicate) {
            unfiltered.getClass();
            this.unfiltered = unfiltered;
            predicate.getClass();
            this.predicate = predicate;
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int add(@ParametricNullness E element, int occurrences) {
            Preconditions.checkArgument(this.predicate.apply(element), "Element %s does not match predicate %s", element, this.predicate);
            return this.unfiltered.add(element, occurrences);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object element) {
            int iCount = this.unfiltered.count(element);
            if (iCount <= 0 || !this.predicate.apply(element)) {
                return 0;
            }
            return iCount;
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Set<E> createElementSet() {
            return Sets.filter(this.unfiltered.elementSet(), this.predicate);
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), new Predicate() { // from class: com.google.common.collect.Multisets$FilteredMultiset$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Predicate
                public final boolean apply(Object obj) {
                    return this.f$0.predicate.apply((Object) ((Multiset.Entry) obj).getElement());
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<Multiset.Entry<E>> entryIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(Object element, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return count(element);
            }
            if (contains(element)) {
                return this.unfiltered.remove(element, occurrences);
            }
            return 0;
        }

        @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public UnmodifiableIterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final int count;

        @ParametricNullness
        public final E element;

        public ImmutableEntry(@ParametricNullness E element, int count) {
            this.element = element;
            this.count = count;
            CollectPreconditions.checkNonnegative(count, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public final E getElement() {
            return this.element;
        }

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MultisetIteratorImpl<E> implements Iterator<E> {
        public boolean canRemove;
        public Multiset.Entry<E> currentEntry;
        public final Iterator<Multiset.Entry<E>> entryIterator;
        public int laterCount;
        public final Multiset<E> multiset;
        public int totalCount;

        public MultisetIteratorImpl(Multiset<E> multiset, Iterator<Multiset.Entry<E>> entryIterator) {
            this.multiset = multiset;
            this.entryIterator = entryIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.laterCount == 0) {
                Multiset.Entry<E> next = this.entryIterator.next();
                this.currentEntry = next;
                int count = next.getCount();
                this.laterCount = count;
                this.totalCount = count;
            }
            this.laterCount--;
            this.canRemove = true;
            Multiset.Entry<E> entry = this.currentEntry;
            j$.util.Objects.requireNonNull(entry);
            return entry.getElement();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                Multiset<E> multiset = this.multiset;
                Multiset.Entry<E> entry = this.currentEntry;
                j$.util.Objects.requireNonNull(entry);
                multiset.remove(entry.getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Multiset<? extends E> delegate;

        @LazyInit
        public transient Set<E> elementSet;

        @LazyInit
        public transient Set<Multiset.Entry<E>> entrySet;

        public UnmodifiableMultiset(Multiset<? extends E> delegate) {
            this.delegate = delegate;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(@ParametricNullness E element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> elementsToAdd) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Set<E> createElementSet() {
            return DesugarCollections.unmodifiableSet(this.delegate.elementSet());
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set = this.elementSet;
            if (set != null) {
                return set;
            }
            Set<E> setCreateElementSet = createElementSet();
            this.elementSet = setCreateElementSet;
            return setCreateElementSet;
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Multiset.Entry<E>> setUnmodifiableSet = DesugarCollections.unmodifiableSet(this.delegate.entrySet());
            this.entrySet = setUnmodifiableSet;
            return setUnmodifiableSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.delegate.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> elementsToRemove) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> elementsToRetain) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int setCount(@ParametricNullness E element, int count) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int add(@ParametricNullness E element, int occurrences) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int remove(Object element, int occurrences) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Multiset<E> delegate() {
            return this.delegate;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ViewMultiset<E> extends AbstractMultiset<E> {
        public ViewMultiset() {
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            elementSet().clear();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public int distinctElements() {
            return elementSet().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public Iterator<E> iterator() {
            return Multisets.iteratorImpl(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return Multisets.linearTimeSizeImpl(this);
        }

        public ViewMultiset(AnonymousClass1 anonymousClass1) {
        }
    }

    public static <E> boolean addAllImpl(Multiset<E> self, Collection<? extends E> elements) {
        self.getClass();
        elements.getClass();
        if (elements instanceof Multiset) {
            return addAllImpl((Multiset) self, (Multiset) elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        return Iterators.addAll(self, elements.iterator());
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> superMultiset, Multiset<?> subMultiset) {
        superMultiset.getClass();
        subMultiset.getClass();
        for (Multiset.Entry<?> entry : subMultiset.entrySet()) {
            if (superMultiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) multiset.entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entryArr, DecreasingCount.INSTANCE);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entryArr));
    }

    public static <E> Multiset<E> difference(final Multiset<E> multiset1, final Multiset<?> multiset2) {
        multiset1.getClass();
        multiset2.getClass();
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.4
            @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public void clear() {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                int iCount = multiset1.count(element);
                if (iCount == 0) {
                    return 0;
                }
                return Math.max(0, iCount - multiset2.count(element));
            }

            @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset
            public int distinctElements() {
                return Iterators.size(entryIterator());
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                final Iterator<Multiset.Entry<E>> it = multiset1.entrySet().iterator();
                return new AbstractIterator<E>(this) { // from class: com.google.common.collect.Multisets.4.1
                    public final /* synthetic */ AnonymousClass4 this$0;

                    {
                        this.this$0 = this;
                    }

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
                    public E computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            E e = (E) entry.getElement();
                            if (entry.getCount() > multiset2.count(e)) {
                                return e;
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> it = multiset1.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>(this) { // from class: com.google.common.collect.Multisets.4.2
                    public final /* synthetic */ AnonymousClass4 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int count = entry.getCount() - multiset2.count(element);
                            if (count > 0) {
                                return new ImmutableEntry(element, count);
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
        };
    }

    public static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> entryIterator) {
        return new AnonymousClass5(entryIterator);
    }

    public static boolean equalsImpl(Multiset<?> multiset, Object object) {
        if (object == multiset) {
            return true;
        }
        if (!(object instanceof Multiset)) {
            return false;
        }
        Multiset multiset2 = (Multiset) object;
        if (multiset.size() != multiset2.size() || multiset.entrySet().size() != multiset2.entrySet().size()) {
            return false;
        }
        for (Multiset.Entry entry : multiset2.entrySet()) {
            if (multiset.count(entry.getElement()) != entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    public static <E> Multiset<E> filter(Multiset<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredMultiset)) {
            return new FilteredMultiset(unfiltered, predicate);
        }
        FilteredMultiset filteredMultiset = (FilteredMultiset) unfiltered;
        return new FilteredMultiset(filteredMultiset.unfiltered, Predicates.and(filteredMultiset.predicate, predicate));
    }

    public static <E> Multiset.Entry<E> immutableEntry(@ParametricNullness E e, int n) {
        return new ImmutableEntry(e, n);
    }

    public static int inferDistinctElements(Iterable<?> elements) {
        if (elements instanceof Multiset) {
            return ((Multiset) elements).elementSet().size();
        }
        return 11;
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset1, final Multiset<?> multiset2) {
        multiset1.getClass();
        multiset2.getClass();
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.2
            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                int iCount = multiset1.count(element);
                if (iCount == 0) {
                    return 0;
                }
                return Math.min(iCount, multiset2.count(element));
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.intersection(multiset1.elementSet(), multiset2.elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> it = multiset1.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>(this) { // from class: com.google.common.collect.Multisets.2.1
                    public final /* synthetic */ AnonymousClass2 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int iMin = Math.min(entry.getCount(), multiset2.count(element));
                            if (iMin > 0) {
                                return new ImmutableEntry(element, iMin);
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }
        };
    }

    public static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    public static int linearTimeSizeImpl(Multiset<?> multiset) {
        Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
        long count = 0;
        while (it.hasNext()) {
            count += (long) it.next().getCount();
        }
        return Ints.saturatedCast(count);
    }

    public static boolean removeAllImpl(Multiset<?> self, Collection<?> elementsToRemove) {
        if (elementsToRemove instanceof Multiset) {
            elementsToRemove = ((Multiset) elementsToRemove).elementSet();
        }
        return self.elementSet().removeAll(elementsToRemove);
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multisetToModify, Iterable<?> occurrencesToRemove) {
        if (occurrencesToRemove instanceof Multiset) {
            return removeOccurrences(multisetToModify, (Multiset<?>) occurrencesToRemove);
        }
        multisetToModify.getClass();
        occurrencesToRemove.getClass();
        Iterator<?> it = occurrencesToRemove.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= multisetToModify.remove(it.next());
        }
        return zRemove;
    }

    public static boolean retainAllImpl(Multiset<?> self, Collection<?> elementsToRetain) {
        elementsToRetain.getClass();
        if (elementsToRetain instanceof Multiset) {
            elementsToRetain = ((Multiset) elementsToRetain).elementSet();
        }
        return self.elementSet().retainAll(elementsToRetain);
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multisetToModify, Multiset<?> multisetToRetain) {
        return retainOccurrencesImpl(multisetToModify, multisetToRetain);
    }

    public static <E> boolean retainOccurrencesImpl(Multiset<E> multisetToModify, Multiset<?> occurrencesToRetain) {
        multisetToModify.getClass();
        occurrencesToRetain.getClass();
        Iterator<Multiset.Entry<E>> it = multisetToModify.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            int iCount = occurrencesToRetain.count(next.getElement());
            if (iCount == 0) {
                it.remove();
            } else if (iCount < next.getCount()) {
                multisetToModify.setCount(next.getElement(), iCount);
            }
            z = true;
        }
        return z;
    }

    public static <E> int setCountImpl(Multiset<E> self, @ParametricNullness E element, int count) {
        CollectPreconditions.checkNonnegative(count, "count");
        int iCount = self.count(element);
        int i = count - iCount;
        if (i > 0) {
            self.add(element, i);
            return iCount;
        }
        if (i < 0) {
            self.remove(element, -i);
        }
        return iCount;
    }

    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset1, final Multiset<? extends E> multiset2) {
        multiset1.getClass();
        multiset2.getClass();
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.3
            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public boolean contains(Object element) {
                return multiset1.contains(element) || multiset2.contains(element);
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                return multiset2.count(element) + multiset1.count(element);
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(multiset1.elementSet(), multiset2.elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> it = multiset1.entrySet().iterator();
                final Iterator<Multiset.Entry<E>> it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>(this) { // from class: com.google.common.collect.Multisets.3.1
                    public final /* synthetic */ AnonymousClass3 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return new ImmutableEntry(element, multiset2.count(element) + entry.getCount());
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset1.contains(element2)) {
                                return new ImmutableEntry(element2, entry2.getCount());
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public boolean isEmpty() {
                return multiset1.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public int size() {
                return IntMath.saturatedAdd(multiset1.size(), multiset2.size());
            }
        };
    }

    @IgnoreJRERequirement
    public static <T, E, M extends Multiset<E>> Collector<T, ?, M> toMultiset(Function<? super T, E> elementFunction, ToIntFunction<? super T> countFunction, Supplier<M> multisetSupplier) {
        return CollectCollectors.toMultiset(elementFunction, countFunction, multisetSupplier);
    }

    public static <E> Multiset<E> union(final Multiset<? extends E> multiset1, final Multiset<? extends E> multiset2) {
        multiset1.getClass();
        multiset2.getClass();
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.1
            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public boolean contains(Object element) {
                return multiset1.contains(element) || multiset2.contains(element);
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                return Math.max(multiset1.count(element), multiset2.count(element));
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(multiset1.elementSet(), multiset2.elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> it = multiset1.entrySet().iterator();
                final Iterator<Multiset.Entry<E>> it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>(this) { // from class: com.google.common.collect.Multisets.1.1
                    public final /* synthetic */ AnonymousClass1 this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return new ImmutableEntry(element, Math.max(entry.getCount(), multiset2.count(element)));
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset1.contains(element2)) {
                                return new ImmutableEntry(element2, entry2.getCount());
                            }
                        }
                        endOfData();
                        return null;
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public boolean isEmpty() {
                return multiset1.isEmpty() && multiset2.isEmpty();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        if ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) {
            return multiset;
        }
        multiset.getClass();
        return new UnmodifiableMultiset(multiset);
    }

    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        sortedMultiset.getClass();
        return new UnmodifiableSortedMultiset((Multiset) sortedMultiset);
    }

    public static <E> boolean setCountImpl(Multiset<E> self, @ParametricNullness E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        if (self.count(element) != oldCount) {
            return false;
        }
        self.setCount(element, newCount);
        return true;
    }

    @InlineMe(replacement = "checkNotNull(multiset)", staticImports = {"com.google.common.base.Preconditions.checkNotNull"})
    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> multiset) {
        multiset.getClass();
        return multiset;
    }

    public static <E> boolean addAllImpl(Multiset<E> self, Multiset<? extends E> elements) {
        if (elements instanceof AbstractMapBasedMultiset) {
            return addAllImpl((Multiset) self, (AbstractMapBasedMultiset) elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : elements.entrySet()) {
            self.add(entry.getElement(), entry.getCount());
        }
        return true;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multisetToModify, Multiset<?> occurrencesToRemove) {
        multisetToModify.getClass();
        occurrencesToRemove.getClass();
        Iterator<Multiset.Entry<?>> it = multisetToModify.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<?> next = it.next();
            int iCount = occurrencesToRemove.count(next.getElement());
            if (iCount >= next.getCount()) {
                it.remove();
            } else if (iCount > 0) {
                multisetToModify.remove(next.getElement(), iCount);
            }
            z = true;
        }
        return z;
    }

    public static <E> boolean addAllImpl(Multiset<E> self, AbstractMapBasedMultiset<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        elements.addTo(self);
        return true;
    }
}
