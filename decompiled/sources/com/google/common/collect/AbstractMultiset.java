package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {

    @LazyInit
    public transient Set<E> elementSet;

    @LazyInit
    public transient Set<Multiset.Entry<E>> entrySet;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ElementSet extends Multisets.ElementSet<E> {
        public ElementSet() {
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return AbstractMultiset.this.elementIterator();
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class EntrySet extends Multisets.EntrySet<E> {
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Multiset.Entry<E>> iterator() {
            return AbstractMultiset.this.entryIterator();
        }

        @Override // com.google.common.collect.Multisets.EntrySet
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractMultiset.this.distinctElements();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean add(@ParametricNullness E element) {
        add(element, 1);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public abstract void clear();

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public boolean contains(Object element) {
        return count(element) > 0;
    }

    public Set<E> createElementSet() {
        return new ElementSet();
    }

    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    public abstract int distinctElements();

    public abstract Iterator<E> elementIterator();

    @Override // com.google.common.collect.Multiset
    public Set<E> elementSet() {
        Set<E> set = this.elementSet;
        if (set != null) {
            return set;
        }
        Set<E> setCreateElementSet = createElementSet();
        this.elementSet = setCreateElementSet;
        return setCreateElementSet;
    }

    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    @Override // com.google.common.collect.Multiset
    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> setCreateEntrySet = createEntrySet();
        this.entrySet = setCreateEntrySet;
        return setCreateEntrySet;
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public final boolean equals(Object object) {
        return Multisets.equalsImpl(this, object);
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public final int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean remove(Object element) {
        return remove(element, 1) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    @CanIgnoreReturnValue
    public int setCount(@ParametricNullness E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset
    public final String toString() {
        return entrySet().toString();
    }

    @CanIgnoreReturnValue
    public int add(@ParametricNullness E element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public int remove(Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }
}
