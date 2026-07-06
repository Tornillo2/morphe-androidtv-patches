package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class PairwiseEquivalence<E, T extends E> extends Equivalence<Iterable<T>> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final Equivalence<E> elementEquivalence;

    public PairwiseEquivalence(Equivalence<E> elementEquivalence) {
        elementEquivalence.getClass();
        this.elementEquivalence = elementEquivalence;
    }

    public boolean equals(Object object) {
        if (object instanceof PairwiseEquivalence) {
            return this.elementEquivalence.equals(((PairwiseEquivalence) object).elementEquivalence);
        }
        return false;
    }

    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 1185147655;
    }

    public String toString() {
        return this.elementEquivalence + ".pairwise()";
    }

    @Override // com.google.common.base.Equivalence
    public boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
        Iterator<T> it = iterableA.iterator();
        Iterator<T> it2 = iterableB.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!this.elementEquivalence.equivalent(it.next(), it2.next())) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    @Override // com.google.common.base.Equivalence
    public int doHash(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        int iHash = 78721;
        while (it.hasNext()) {
            iHash = (iHash * 24943) + this.elementEquivalence.hash(it.next());
        }
        return iHash;
    }
}
