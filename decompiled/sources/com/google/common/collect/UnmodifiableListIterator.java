package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotCall;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class UnmodifiableListIterator<E> extends UnmodifiableIterator<E> implements ListIterator<E> {
    @Override // java.util.ListIterator
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void add(@ParametricNullness E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void set(@ParametricNullness E e) {
        throw new UnsupportedOperationException();
    }
}
