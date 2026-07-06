package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Function$CC;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements Multiset<E>, Collection {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 912559;

    @LazyInit
    public transient ImmutableList<E> asList;

    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> entrySet;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder<E> extends ImmutableCollection.Builder<E> {
        public boolean buildInvoked;
        public ObjectCountHashMap<E> contents;
        public boolean isLinkedHash;

        public Builder(int estimatedDistinct) {
            this.buildInvoked = false;
            this.isLinkedHash = false;
            this.contents = new ObjectCountHashMap<>(estimatedDistinct);
        }

        public static <T> ObjectCountHashMap<T> tryGetMap(Iterable<T> iterable) {
            if (iterable instanceof RegularImmutableMultiset) {
                return ((RegularImmutableMultiset) iterable).contents;
            }
            if (iterable instanceof AbstractMapBasedMultiset) {
                return ((AbstractMapBasedMultiset) iterable).backingMap;
            }
            return null;
        }

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E element, int occurrences) {
            Objects.requireNonNull(this.contents);
            if (occurrences == 0) {
                return this;
            }
            if (this.buildInvoked) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = false;
            element.getClass();
            ObjectCountHashMap<E> objectCountHashMap = this.contents;
            objectCountHashMap.put(element, objectCountHashMap.get(element) + occurrences);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E element, int count) {
            Objects.requireNonNull(this.contents);
            if (count == 0 && !this.isLinkedHash) {
                this.contents = new ObjectCountLinkedHashMap(this.contents);
                this.isLinkedHash = true;
            } else if (this.buildInvoked) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = false;
            element.getClass();
            if (count == 0) {
                this.contents.remove(element);
                return this;
            }
            this.contents.put(element, count);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableMultiset<E> build() {
            Objects.requireNonNull(this.contents);
            if (this.contents.size() == 0) {
                return ImmutableMultiset.of();
            }
            if (this.isLinkedHash) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = true;
            return new RegularImmutableMultiset(this.contents);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            return addCopies(element, 1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            Objects.requireNonNull(this.contents);
            if (elements instanceof Multiset) {
                Multiset multiset = (Multiset) elements;
                ObjectCountHashMap objectCountHashMapTryGetMap = tryGetMap(multiset);
                if (objectCountHashMapTryGetMap != null) {
                    ObjectCountHashMap<E> objectCountHashMap = this.contents;
                    objectCountHashMap.ensureCapacity(Math.max(objectCountHashMap.size(), objectCountHashMapTryGetMap.size()));
                    for (int iFirstIndex = objectCountHashMapTryGetMap.firstIndex(); iFirstIndex >= 0; iFirstIndex = objectCountHashMapTryGetMap.nextIndex(iFirstIndex)) {
                        addCopies(objectCountHashMapTryGetMap.getKey(iFirstIndex), objectCountHashMapTryGetMap.getValue(iFirstIndex));
                    }
                } else {
                    Set<Multiset.Entry<E>> setEntrySet = multiset.entrySet();
                    ObjectCountHashMap<E> objectCountHashMap2 = this.contents;
                    objectCountHashMap2.ensureCapacity(Math.max(objectCountHashMap2.size(), setEntrySet.size()));
                    for (Multiset.Entry<E> entry : multiset.entrySet()) {
                        addCopies(entry.getElement(), entry.getCount());
                    }
                }
                return this;
            }
            super.addAll((Iterable) elements);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... elements) {
            super.add((Object[]) elements);
            return this;
        }

        public Builder(boolean forSubtype) {
            this.buildInvoked = false;
            this.isLinkedHash = false;
            this.contents = null;
        }

        public Builder() {
            this(4);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll((Iterator) elements);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Use EntrySetSerializedForm");
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (o instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) o;
                if (entry.getCount() > 0 && ImmutableMultiset.this.count(entry.getElement()) == entry.getCount()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public Multiset.Entry<E> get(int index) {
            return ImmutableMultiset.this.getEntry(index);
        }

        @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableMultiset.this.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ImmutableMultiset.this.elementSet().size();
        }

        @Override // com.google.common.collect.IndexedImmutableSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public Object get(int index) {
            return ImmutableMultiset.this.getEntry(index);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @J2ktIncompatible
    public static class EntrySetSerializedForm<E> implements Serializable {
        public final ImmutableMultiset<E> multiset;

        public EntrySetSerializedForm(ImmutableMultiset<E> multiset) {
            this.multiset = multiset;
        }

        public Object readResolve() {
            return this.multiset.entrySet();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$pkZWwaJT6p_wzcADK--CccNakSQ, reason: not valid java name */
    public static /* synthetic */ int m515$r8$lambda$pkZWwaJT6p_wzcADKCccNakSQ(Object obj) {
        return 1;
    }

    public static <E> Builder<E> builder() {
        return new Builder<>(4);
    }

    public static <E> ImmutableMultiset<E> copyFromElements(E... elements) {
        Builder builder = new Builder(4);
        builder.add((Object[]) elements);
        return builder.build();
    }

    public static <E> ImmutableMultiset<E> copyFromEntries(java.util.Collection<? extends Multiset.Entry<? extends E>> entries) {
        Builder builder = new Builder(entries.size());
        for (Multiset.Entry<? extends E> entry : entries) {
            builder.addCopies(entry.getElement(), entry.getCount());
        }
        return builder.build();
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] elements) {
        return copyFromElements(elements);
    }

    private ImmutableSet<Multiset.Entry<E>> createEntrySet() {
        return isEmpty() ? RegularImmutableSet.EMPTY : new EntrySet();
    }

    public static /* synthetic */ int lambda$toImmutableMultiset$0(Object obj) {
        return 1;
    }

    public static <E> ImmutableMultiset<E> of() {
        return RegularImmutableMultiset.EMPTY;
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @IgnoreJRERequirement
    public static <E> Collector<E, ?, ImmutableMultiset<E>> toImmutableMultiset() {
        return CollectCollectors.toImmutableMultiset(Function$CC.identity(), new ImmutableMultiset$$ExternalSyntheticLambda0());
    }

    @Override // com.google.common.collect.Multiset
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final int add(E element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> immutableListAsList = super.asList();
        this.asList = immutableListAsList;
        return immutableListAsList;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object object) {
        return count(object) > 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public int copyIntoArray(Object[] dst, int offset) {
        UnmodifiableIterator<Multiset.Entry<E>> it = entrySet().iterator();
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            Arrays.fill(dst, offset, next.getCount() + offset, next.getElement());
            offset += next.getCount();
        }
        return offset;
    }

    @Override // com.google.common.collect.Multiset
    public abstract ImmutableSet<E> elementSet();

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public boolean equals(Object object) {
        return Multisets.equalsImpl(this, object);
    }

    public abstract Multiset.Entry<E> getEntry(int index);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final int remove(Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final int setCount(E element, int count) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset
    public String toString() {
        return entrySet().toString();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @J2ktIncompatible
    @GwtIncompatible
    public abstract Object writeReplace();

    public static <E> ImmutableMultiset<E> copyOf(Iterable<? extends E> elements) {
        if (elements instanceof ImmutableMultiset) {
            ImmutableMultiset<E> immutableMultiset = (ImmutableMultiset) elements;
            if (!immutableMultiset.isPartialView()) {
                return immutableMultiset;
            }
        }
        Builder builder = new Builder(Multisets.inferDistinctElements(elements));
        builder.addAll((Iterable) elements);
        return builder.build();
    }

    public static <E> ImmutableMultiset<E> of(E e1) {
        return copyFromElements(e1);
    }

    @IgnoreJRERequirement
    public static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(Function<? super T, ? extends E> elementFunction, ToIntFunction<? super T> countFunction) {
        return CollectCollectors.toImmutableMultiset(elementFunction, countFunction);
    }

    @Override // com.google.common.collect.Multiset
    public ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<E>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Multiset.Entry<E>> immutableSetCreateEntrySet = createEntrySet();
        this.entrySet = immutableSetCreateEntrySet;
        return immutableSetCreateEntrySet;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        final UnmodifiableIterator<Multiset.Entry<E>> it = entrySet().iterator();
        return new UnmodifiableIterator<E>(this) { // from class: com.google.common.collect.ImmutableMultiset.1
            public E element;
            public int remaining;
            public final /* synthetic */ ImmutableMultiset this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.remaining > 0 || it.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.remaining <= 0) {
                    Multiset.Entry entry = (Multiset.Entry) it.next();
                    this.element = (E) entry.getElement();
                    this.remaining = entry.getCount();
                }
                this.remaining--;
                E e = this.element;
                Objects.requireNonNull(e);
                return e;
            }
        };
    }

    @Override // com.google.common.collect.Multiset
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean setCount(E element, int oldCount, int newCount) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2) {
        return copyFromElements(e1, e2);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3) {
        return copyFromElements(e1, e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4) {
        return copyFromElements(e1, e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5) {
        return copyFromElements(e1, e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E... others) {
        Builder builder = new Builder(4);
        builder.addCopies(e1, 1);
        builder.addCopies(e2, 1);
        return builder.add((Object) e3).add((Object) e4).add((Object) e5).add((Object) e6).add((Object[]) others).build();
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> elements) {
        Builder builder = new Builder(4);
        builder.addAll((Iterator) elements);
        return builder.build();
    }
}
