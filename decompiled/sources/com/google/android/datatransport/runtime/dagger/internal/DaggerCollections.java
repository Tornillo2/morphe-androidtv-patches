package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DaggerCollections {
    public static final int MAX_POWER_OF_TWO = 1073741824;

    public static int calculateInitialCapacity(int i) {
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    public static <T> HashSet<T> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(calculateInitialCapacity(i));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return new LinkedHashMap<>(calculateInitialCapacity(i));
    }

    public static <T> List<T> presizedList(int i) {
        return i == 0 ? Collections.EMPTY_LIST : new ArrayList(i);
    }
}
