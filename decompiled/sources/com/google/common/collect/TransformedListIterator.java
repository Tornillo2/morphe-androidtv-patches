package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class TransformedListIterator<F, T> extends TransformedIterator<F, T> implements ListIterator<T> {
    public TransformedListIterator(ListIterator<? extends F> backingIterator) {
        super(backingIterator);
    }

    @Override // java.util.ListIterator
    public void add(@ParametricNullness T element) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<? extends F> backingIterator() {
        return (ListIterator) this.backingIterator;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return ((ListIterator) this.backingIterator).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return ((ListIterator) this.backingIterator).nextIndex();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ListIterator
    @ParametricNullness
    public final T previous() {
        return (T) transform(((ListIterator) this.backingIterator).previous());
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return ((ListIterator) this.backingIterator).previousIndex();
    }

    public void set(@ParametricNullness T element) {
        throw new UnsupportedOperationException();
    }
}
