package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class ArrayBasedEscaperMap {
    public static final char[][] EMPTY_REPLACEMENT_ARRAY = (char[][]) Array.newInstance((Class<?>) Character.TYPE, 0, 0);
    public final char[][] replacementArray;

    public ArrayBasedEscaperMap(char[][] replacementArray) {
        this.replacementArray = replacementArray;
    }

    public static ArrayBasedEscaperMap create(Map<Character, String> replacements) {
        return new ArrayBasedEscaperMap(createReplacementArray(replacements));
    }

    @VisibleForTesting
    public static char[][] createReplacementArray(Map<Character, String> map) {
        map.getClass();
        if (map.isEmpty()) {
            return EMPTY_REPLACEMENT_ARRAY;
        }
        char[][] cArr = new char[((Character) Collections.max(map.keySet())).charValue() + 1][];
        for (Character ch : map.keySet()) {
            cArr[ch.charValue()] = map.get(ch).toCharArray();
        }
        return cArr;
    }

    public char[][] getReplacementArray() {
        return this.replacementArray;
    }
}
