package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class Atomics {
    public static <V> AtomicReference<V> newReference() {
        return new AtomicReference<>();
    }

    public static <E> AtomicReferenceArray<E> newReferenceArray(int length) {
        return new AtomicReferenceArray<>(length);
    }

    public static <V> AtomicReference<V> newReference(@ParametricNullness V initialValue) {
        return new AtomicReference<>(initialValue);
    }

    public static <E> AtomicReferenceArray<E> newReferenceArray(E[] array) {
        return new AtomicReferenceArray<>(array);
    }
}
