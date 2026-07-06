package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.MapMakerInternalMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static <T> T[] copy(Object[] objArr, int i, int i2, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    public static <E extends Enum<E>> Class<E> getDeclaringClassOrObjectForJ2cl(E e) {
        return e.getDeclaringClass();
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        if (tArr.length != 0) {
            tArr = (T[]) Arrays.copyOf(tArr, 0);
        }
        return (T[]) Arrays.copyOf(tArr, i);
    }

    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return CompactHashMap.createWithExpectedSize(expectedSize);
    }

    public static <E> Set<E> newHashSetWithExpectedSize(int expectedSize) {
        return CompactHashSet.createWithExpectedSize(expectedSize);
    }

    public static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return CompactLinkedHashMap.createWithExpectedSize(expectedSize);
    }

    public static <E> Set<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return CompactLinkedHashSet.createWithExpectedSize(expectedSize);
    }

    public static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return CompactHashSet.create();
    }

    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return CompactHashMap.create();
    }

    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMapWithExpectedSize(int expectedSize) {
        return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
    }

    @J2ktIncompatible
    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        mapMaker.getClass();
        mapMaker.setKeyStrength(MapMakerInternalMap.Strength.WEAK);
        return mapMaker;
    }

    public static int reduceExponentIfGwt(int exponent) {
        return exponent;
    }

    public static int reduceIterationsIfGwt(int iterations) {
        return iterations;
    }
}
