package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final Comparator<? super T> elementOrder;

    public LexicographicalOrdering(Comparator<? super T> elementOrder) {
        this.elementOrder = elementOrder;
    }

    @Override // java.util.Comparator
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof LexicographicalOrdering) {
            return this.elementOrder.equals(((LexicographicalOrdering) object).elementOrder);
        }
        return false;
    }

    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    public String toString() {
        return this.elementOrder + ".lexicographical()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Iterable<T> leftIterable, Iterable<T> rightIterable) {
        Iterator<T> it = leftIterable.iterator();
        Iterator<T> it2 = rightIterable.iterator();
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return 1;
            }
            int iCompare = this.elementOrder.compare(it.next(), it2.next());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return it2.hasNext() ? -1 : 0;
    }
}
