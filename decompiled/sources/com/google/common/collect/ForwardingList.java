package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    public void add(int index, @ParametricNullness E element) {
        delegate();
        Collections.EMPTY_LIST.add(index, element);
    }

    @CanIgnoreReturnValue
    public boolean addAll(int index, Collection<? extends E> elements) {
        delegate();
        return Collections.EMPTY_LIST.addAll(index, elements);
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public /* bridge */ /* synthetic */ Object delegate() {
        delegate();
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract List<E> delegate();

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        delegate();
        return Collections.EMPTY_LIST.equals(object);
    }

    @Override // java.util.List
    @ParametricNullness
    public E get(int i) {
        delegate();
        return (E) Collections.EMPTY_LIST.get(i);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        delegate();
        return Collections.EMPTY_LIST.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        delegate();
        return Collections.EMPTY_LIST.indexOf(element);
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        delegate();
        return Collections.EMPTY_LIST.lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        delegate();
        return Collections.EMPTY_LIST.listIterator();
    }

    @Override // java.util.List
    @ParametricNullness
    @CanIgnoreReturnValue
    public E remove(int i) {
        delegate();
        return (E) Collections.EMPTY_LIST.remove(i);
    }

    @Override // java.util.List
    @ParametricNullness
    @CanIgnoreReturnValue
    public E set(int i, @ParametricNullness E e) {
        delegate();
        return (E) Collections.EMPTY_LIST.set(i, e);
    }

    public boolean standardAdd(@ParametricNullness E element) {
        add(size(), element);
        return true;
    }

    public boolean standardAddAll(int index, Iterable<? extends E> elements) {
        return Lists.addAllImpl(this, index, elements);
    }

    public boolean standardEquals(Object object) {
        return Lists.equalsImpl(this, object);
    }

    public int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }

    public int standardIndexOf(Object element) {
        return Lists.indexOfImpl(this, element);
    }

    public Iterator<E> standardIterator() {
        return listIterator();
    }

    public int standardLastIndexOf(Object element) {
        return Lists.lastIndexOfImpl(this, element);
    }

    public ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    public List<E> standardSubList(int fromIndex, int toIndex) {
        return Lists.subListImpl(this, fromIndex, toIndex);
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        delegate();
        return Collections.EMPTY_LIST.subList(fromIndex, toIndex);
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public /* bridge */ /* synthetic */ Collection delegate() {
        delegate();
        return Collections.EMPTY_LIST;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        delegate();
        return Collections.EMPTY_LIST.listIterator(index);
    }

    public ListIterator<E> standardListIterator(int start) {
        return Lists.listIteratorImpl(this, start);
    }
}
