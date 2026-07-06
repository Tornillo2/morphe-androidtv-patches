package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final ImmutableMap<T, Integer> rankMap;

    public ExplicitOrdering(List<T> valuesInOrder) {
        this(Maps.indexMap(valuesInOrder));
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T left, T right) {
        return rank(left) - rank(right);
    }

    @Override // java.util.Comparator
    public boolean equals(Object object) {
        if (object instanceof ExplicitOrdering) {
            return this.rankMap.equals(((ExplicitOrdering) object).rankMap);
        }
        return false;
    }

    public int hashCode() {
        return this.rankMap.hashCode();
    }

    public final int rank(T value) {
        Integer num = this.rankMap.get(value);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering.IncomparableValueException(value);
    }

    public String toString() {
        return "Ordering.explicit(" + this.rankMap.keySet() + ")";
    }

    public ExplicitOrdering(ImmutableMap<T, Integer> rankMap) {
        this.rankMap = rankMap;
    }
}
