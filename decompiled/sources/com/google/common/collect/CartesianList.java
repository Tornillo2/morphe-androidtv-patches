package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class CartesianList<E> extends AbstractList<List<E>> implements RandomAccess {
    public final transient ImmutableList<List<E>> axes;
    public final transient int[] axesSizeProduct;

    public CartesianList(ImmutableList<List<E>> axes) {
        this.axes = axes;
        int[] iArr = new int[axes.size() + 1];
        iArr[axes.size()] = 1;
        try {
            for (int size = axes.size() - 1; size >= 0; size--) {
                iArr[size] = IntMath.checkedMultiply(iArr[size + 1], axes.get(size).size());
            }
            this.axesSizeProduct = iArr;
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    public static <E> List<List<E>> create(List<? extends List<? extends E>> lists) {
        ImmutableList.Builder builder = new ImmutableList.Builder(lists.size());
        Iterator<? extends List<? extends E>> it = lists.iterator();
        while (it.hasNext()) {
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) it.next());
            if (immutableListCopyOf.isEmpty()) {
                return RegularImmutableList.EMPTY;
            }
            builder.add(immutableListCopyOf);
        }
        return new CartesianList(builder.build());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object object) {
        if (!(object instanceof List)) {
            return false;
        }
        List list = (List) object;
        if (list.size() != this.axes.size()) {
            return false;
        }
        Iterator<E> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!this.axes.get(i).contains(it.next())) {
                return false;
            }
            i++;
        }
        return true;
    }

    public final int getAxisIndexForProductIndex(int index, int axis) {
        return (index / this.axesSizeProduct[axis + 1]) % this.axes.get(axis).size();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object o) {
        if (!(o instanceof List)) {
            return -1;
        }
        List list = (List) o;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator<E> listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            int iNextIndex = listIterator.nextIndex();
            int iIndexOf = this.axes.get(iNextIndex).indexOf(listIterator.next());
            if (iIndexOf == -1) {
                return -1;
            }
            i += iIndexOf * this.axesSizeProduct[iNextIndex + 1];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object o) {
        if (!(o instanceof List)) {
            return -1;
        }
        List list = (List) o;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator<E> listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            int iNextIndex = listIterator.nextIndex();
            int iLastIndexOf = this.axes.get(iNextIndex).lastIndexOf(listIterator.next());
            if (iLastIndexOf == -1) {
                return -1;
            }
            i += iLastIndexOf * this.axesSizeProduct[iNextIndex + 1];
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.axesSizeProduct[0];
    }

    @Override // java.util.AbstractList, java.util.List
    public ImmutableList<E> get(final int index) {
        Preconditions.checkElementIndex(index, this.axesSizeProduct[0]);
        return new ImmutableList<E>(this) { // from class: com.google.common.collect.CartesianList.1
            public final /* synthetic */ CartesianList this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.List
            public E get(int axis) {
                Preconditions.checkElementIndex(axis, size());
                return this.this$0.axes.get(axis).get(this.this$0.getAxisIndexForProductIndex(index, axis));
            }

            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.this$0.axes.size();
            }

            @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
            @J2ktIncompatible
            @GwtIncompatible
            public Object writeReplace() {
                return super.writeReplace();
            }
        };
    }
}
