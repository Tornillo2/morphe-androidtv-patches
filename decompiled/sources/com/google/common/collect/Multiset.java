package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface Multiset<E> extends Collection<E> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Entry<E> {
        boolean equals(Object o);

        int getCount();

        @ParametricNullness
        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(@ParametricNullness E element, int occurrences);

    @CanIgnoreReturnValue
    boolean add(@ParametricNullness E element);

    boolean contains(Object element);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> elements);

    int count(@CompatibleWith(ExifInterface.LONGITUDE_EAST) Object element);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(Object object);

    int hashCode();

    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@CompatibleWith(ExifInterface.LONGITUDE_EAST) Object element, int occurrences);

    @CanIgnoreReturnValue
    boolean remove(Object element);

    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> c);

    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> c);

    @CanIgnoreReturnValue
    int setCount(@ParametricNullness E element, int count);

    @CanIgnoreReturnValue
    boolean setCount(@ParametricNullness E element, int oldCount, int newCount);

    int size();

    String toString();
}
