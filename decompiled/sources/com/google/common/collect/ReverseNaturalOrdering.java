package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class ReverseNaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
    public static final ReverseNaturalOrdering INSTANCE = new ReverseNaturalOrdering();

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> reverse() {
        return NaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable<?> left, Comparable<?> right) {
        left.getClass();
        if (left == right) {
            return 0;
        }
        return right.compareTo(left);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E max(E a, E b) {
        return (E) NaturalOrdering.INSTANCE.min(a, b);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E min(E a, E b) {
        return (E) NaturalOrdering.INSTANCE.max(a, b);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E max(E a, E b, E c, E... rest) {
        return (E) NaturalOrdering.INSTANCE.min(a, b, c, rest);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E min(E a, E b, E c, E... rest) {
        return (E) NaturalOrdering.INSTANCE.max(a, b, c, rest);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E max(Iterator<E> iterator) {
        return (E) NaturalOrdering.INSTANCE.min(iterator);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E min(Iterator<E> iterator) {
        return (E) NaturalOrdering.INSTANCE.max(iterator);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E max(Iterable<E> iterable) {
        return (E) NaturalOrdering.INSTANCE.min(iterable);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable<?>> E min(Iterable<E> iterable) {
        return (E) NaturalOrdering.INSTANCE.max(iterable);
    }
}
