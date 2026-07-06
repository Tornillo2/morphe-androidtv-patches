package com.google.common.collect;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class ObjectArrays {
    @CanIgnoreReturnValue
    public static Object checkElementNotNull(Object element, int index) {
        if (element != null) {
            return element;
        }
        throw new NullPointerException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("at index ", index));
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object... array) {
        checkElementsNotNull(array, array.length);
        return array;
    }

    @GwtIncompatible
    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    public static Object[] copyAsObjectArray(Object[] elements, int offset, int length) {
        Preconditions.checkPositionIndexes(offset, offset + length, elements.length);
        if (length == 0) {
            return new Object[0];
        }
        Object[] objArr = new Object[length];
        System.arraycopy(elements, offset, objArr, 0, length);
        return objArr;
    }

    @CanIgnoreReturnValue
    public static Object[] fillArray(Iterable<?> elements, Object[] array) {
        Iterator<?> it = elements.iterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next();
            i++;
        }
        return array;
    }

    @GwtIncompatible
    public static <T> T[] newArray(Class<T> cls, int i) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
    }

    public static void swap(Object[] array, int i, int j) {
        Object obj = array[i];
        array[i] = array[j];
        array[j] = obj;
    }

    public static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = (T[]) Platform.newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object[] array, int length) {
        for (int i = 0; i < length; i++) {
            checkElementNotNull(array[i], i);
        }
        return array;
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) Platform.newArray(tArr, i);
    }

    public static <T> T[] concat(@ParametricNullness T t, T[] tArr) {
        T[] tArr2 = (T[]) Platform.newArray(tArr, tArr.length + 1);
        tArr2[0] = t;
        System.arraycopy(tArr, 0, tArr2, 1, tArr.length);
        return tArr2;
    }

    public static <T> T[] toArrayImpl(Object[] objArr, int i, int i2, T[] tArr) {
        Preconditions.checkPositionIndexes(i, i + i2, objArr.length);
        if (tArr.length < i2) {
            tArr = (T[]) Platform.newArray(tArr, i2);
        } else if (tArr.length > i2) {
            tArr[i2] = null;
        }
        System.arraycopy(objArr, i, tArr, 0, i2);
        return tArr;
    }

    public static <T> T[] concat(T[] tArr, @ParametricNullness T t) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length + 1);
        tArr2[tArr.length] = t;
        return tArr2;
    }

    public static Object[] toArrayImpl(Collection<?> c) {
        Object[] objArr = new Object[c.size()];
        fillArray(c, objArr);
        return objArr;
    }
}
