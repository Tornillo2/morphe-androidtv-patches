package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use Iterators.peekingIterator")
@GwtCompatible
public interface PeekingIterator<E> extends Iterator<E> {
    @Override // java.util.Iterator
    @ParametricNullness
    @CanIgnoreReturnValue
    E next();

    @ParametricNullness
    E peek();

    @Override // java.util.Iterator
    void remove();
}
