package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class TransformedIterator<F, T> implements Iterator<T> {
    public final Iterator<? extends F> backingIterator;

    public TransformedIterator(Iterator<? extends F> backingIterator) {
        backingIterator.getClass();
        this.backingIterator = backingIterator;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    @Override // java.util.Iterator
    @ParametricNullness
    public final T next() {
        return transform(this.backingIterator.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.backingIterator.remove();
    }

    @ParametricNullness
    public abstract T transform(@ParametricNullness F from);
}
