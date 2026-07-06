package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import j$.util.Comparator;
import j$.util.Optional;
import j$.util.stream.Collector;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Comparators {
    /* JADX INFO: renamed from: $r8$lambda$o-v3wag4AQbm6Teg-CZPKRmH1pA, reason: not valid java name */
    public static TopKSelector m514$r8$lambda$ov3wag4AQbm6TegCZPKRmH1pA(int i, Comparator comparator) {
        return new TopKSelector(comparator, i);
    }

    @IgnoreJRERequirement
    public static <T> Comparator<Optional<T>> emptiesFirst(Comparator<? super T> valueComparator) {
        valueComparator.getClass();
        return Comparator.CC.comparing(new Comparators$$ExternalSyntheticLambda4(), Comparator.CC.nullsFirst(valueComparator));
    }

    @IgnoreJRERequirement
    public static <T> java.util.Comparator<Optional<T>> emptiesLast(java.util.Comparator<? super T> valueComparator) {
        valueComparator.getClass();
        return Comparator.CC.comparing(new Comparators$$ExternalSyntheticLambda5(), Comparator.CC.nullsLast(valueComparator));
    }

    @IgnoreJRERequirement
    public static <T> Collector<T, ?, List<T>> greatest(int k, java.util.Comparator<? super T> comparator) {
        return least(k, Comparator.EL.reversed(comparator));
    }

    public static <T> boolean isInOrder(Iterable<? extends T> iterable, java.util.Comparator<T> comparator) {
        comparator.getClass();
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, java.util.Comparator<T> comparator) {
        comparator.getClass();
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    @IgnoreJRERequirement
    public static <T> Collector<T, ?, List<T>> least(final int k, final java.util.Comparator<? super T> comparator) {
        CollectPreconditions.checkNonnegative(k, "k");
        comparator.getClass();
        return Collector.CC.of(new Supplier() { // from class: com.google.common.collect.Comparators$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return new TopKSelector(comparator, k);
            }
        }, new Comparators$$ExternalSyntheticLambda1(), new Comparators$$ExternalSyntheticLambda2(), new Comparators$$ExternalSyntheticLambda3(), Collector.Characteristics.UNORDERED);
    }

    public static <T, S extends T> java.util.Comparator<Iterable<S>> lexicographical(java.util.Comparator<T> comparator) {
        comparator.getClass();
        return new LexicographicalOrdering(comparator);
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static <T extends Comparable<? super T>> T min(T a, T b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    @IgnoreJRERequirement
    public static <T> T orElseNull(Optional<T> optional) {
        return optional.orElse(null);
    }

    @ParametricNullness
    public static <T> T max(@ParametricNullness T a, @ParametricNullness T b, java.util.Comparator<? super T> comparator) {
        return comparator.compare(a, b) >= 0 ? a : b;
    }

    @ParametricNullness
    public static <T> T min(@ParametricNullness T a, @ParametricNullness T b, java.util.Comparator<? super T> comparator) {
        return comparator.compare(a, b) <= 0 ? a : b;
    }
}
