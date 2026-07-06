package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class StandardElementSet extends Multisets.ElementSet<E> {
        public StandardElementSet() {
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new Multisets.AnonymousClass5(multiset().entrySet().iterator());
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        public Multiset<E> multiset() {
            return ForwardingMultiset.this;
        }
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@ParametricNullness E element, int occurrences) {
        return delegate().add(element, occurrences);
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        return delegate().count(element);
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract Multiset<E> delegate();

    public Set<E> elementSet() {
        return delegate().elementSet();
    }

    public Set<Multiset.Entry<E>> entrySet() {
        return delegate().entrySet();
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public boolean equals(Object object) {
        return object == this || delegate().equals(object);
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object element, int occurrences) {
        return delegate().remove(element, occurrences);
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@ParametricNullness E element, int count) {
        return delegate().setCount(element, count);
    }

    public boolean standardAdd(@ParametricNullness E element) {
        add(element, 1);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardAddAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardContains(Object object) {
        return count(object) > 0;
    }

    public int standardCount(Object object) {
        for (Multiset.Entry<E> entry : entrySet()) {
            if (Objects.equal(entry.getElement(), object)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    public boolean standardEquals(Object object) {
        return Multisets.equalsImpl(this, object);
    }

    public int standardHashCode() {
        return entrySet().hashCode();
    }

    public Iterator<E> standardIterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemove(Object element) {
        return remove(element, 1) > 0;
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemoveAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRetainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    public int standardSetCount(@ParametricNullness E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    public int standardSize() {
        return Multisets.linearTimeSizeImpl(this);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public String standardToString() {
        return entrySet().toString();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
        return delegate().setCount(element, oldCount, newCount);
    }

    public boolean standardSetCount(@ParametricNullness E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }
}
