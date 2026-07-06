package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    public T nextOrNull;

    public AbstractSequentialIterator(T firstOrNull) {
        this.nextOrNull = firstOrNull;
    }

    public abstract T computeNext(T previous);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        T t = this.nextOrNull;
        if (t == null) {
            throw new NoSuchElementException();
        }
        this.nextOrNull = computeNext(t);
        return t;
    }
}
