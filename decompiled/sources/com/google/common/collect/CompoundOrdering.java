package com.google.common.collect;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public final class CompoundOrdering<T> extends Ordering<T> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final Comparator<? super T>[] comparators;

    public CompoundOrdering(Iterable<? extends Comparator<? super T>> comparators) {
        this.comparators = (Comparator[]) Iterables.toArray(comparators, new Comparator[0]);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(@ParametricNullness T left, @ParametricNullness T right) {
        int i = 0;
        while (true) {
            Comparator<? super T>[] comparatorArr = this.comparators;
            if (i >= comparatorArr.length) {
                return 0;
            }
            int iCompare = comparatorArr[i].compare(left, right);
            if (iCompare != 0) {
                return iCompare;
            }
            i++;
        }
    }

    @Override // java.util.Comparator
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof CompoundOrdering) {
            return Arrays.equals(this.comparators, ((CompoundOrdering) object).comparators);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.comparators);
    }

    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("Ordering.compound("), Arrays.toString(this.comparators), ")");
    }

    public CompoundOrdering(Comparator<? super T> primary, Comparator<? super T> secondary) {
        this.comparators = new Comparator[]{primary, secondary};
    }
}
