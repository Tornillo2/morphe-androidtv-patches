package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract SortedSet<E> delegate();

    @Override // java.util.SortedSet
    @ParametricNullness
    public E first() {
        return delegate().first();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(@ParametricNullness E toElement) {
        return delegate().headSet(toElement);
    }

    @Override // java.util.SortedSet
    @ParametricNullness
    public E last() {
        return delegate().last();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardContains(Object object) {
        return ForwardingSortedMap.unsafeCompare(comparator(), tailSet(object).first(), object) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemove(Object object) {
        try {
            Iterator<E> it = tailSet(object).iterator();
            if (it.hasNext()) {
                if (ForwardingSortedMap.unsafeCompare(comparator(), it.next(), object) == 0) {
                    it.remove();
                    return true;
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public SortedSet<E> standardSubSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
        return tailSet(fromElement).headSet(toElement);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
        return delegate().subSet(fromElement, toElement);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
        return delegate().tailSet(fromElement);
    }
}
