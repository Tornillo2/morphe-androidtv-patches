package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class NaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
    public static final NaturalOrdering INSTANCE = new NaturalOrdering();

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @LazyInit
    public transient Ordering<Comparable<?>> nullsFirst;

    @LazyInit
    public transient Ordering<Comparable<?>> nullsLast;

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> nullsFirst() {
        Ordering<S> ordering = (Ordering<S>) this.nullsFirst;
        if (ordering != null) {
            return ordering;
        }
        NullsFirstOrdering nullsFirstOrdering = new NullsFirstOrdering(this);
        this.nullsFirst = nullsFirstOrdering;
        return nullsFirstOrdering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> nullsLast() {
        Ordering<S> ordering = (Ordering<S>) this.nullsLast;
        if (ordering != null) {
            return ordering;
        }
        NullsLastOrdering nullsLastOrdering = new NullsLastOrdering(this);
        this.nullsLast = nullsLastOrdering;
        return nullsLastOrdering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable<?> left, Comparable<?> right) {
        left.getClass();
        right.getClass();
        return left.compareTo(right);
    }
}
