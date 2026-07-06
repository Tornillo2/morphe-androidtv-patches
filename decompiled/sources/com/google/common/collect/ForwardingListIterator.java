package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingListIterator<E> extends ForwardingIterator<E> implements ListIterator<E> {
    @Override // java.util.ListIterator
    public void add(@ParametricNullness E element) {
        delegate().add(element);
    }

    @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
    public abstract ListIterator<E> delegate();

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return delegate().hasPrevious();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return delegate().nextIndex();
    }

    @Override // java.util.ListIterator
    @ParametricNullness
    @CanIgnoreReturnValue
    public E previous() {
        return delegate().previous();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return delegate().previousIndex();
    }

    @Override // java.util.ListIterator
    public void set(@ParametricNullness E element) {
        delegate().set(element);
    }
}
