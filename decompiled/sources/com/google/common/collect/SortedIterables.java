package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class SortedIterables {
    public static <E> Comparator<? super E> comparator(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator = sortedSet.comparator();
        return comparator == null ? NaturalOrdering.INSTANCE : comparator;
    }

    public static boolean hasSameComparator(Comparator<?> comparator, Iterable<?> elements) {
        Object objComparator;
        comparator.getClass();
        elements.getClass();
        if (elements instanceof SortedSet) {
            objComparator = ((SortedSet) elements).comparator();
            if (objComparator == null) {
                objComparator = NaturalOrdering.INSTANCE;
            }
        } else {
            if (!(elements instanceof SortedIterable)) {
                return false;
            }
            objComparator = ((SortedIterable) elements).comparator();
        }
        return comparator.equals(objComparator);
    }
}
