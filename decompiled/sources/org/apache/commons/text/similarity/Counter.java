package org.apache.commons.text.similarity;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Counter {
    public static Map<CharSequence, Integer> of(CharSequence[] charSequenceArr) {
        HashMap map = new HashMap();
        for (CharSequence charSequence : charSequenceArr) {
            if (map.containsKey(charSequence)) {
                map.put(charSequence, Integer.valueOf(((Integer) map.get(charSequence)).intValue() + 1));
            } else {
                map.put(charSequence, 1);
            }
        }
        return map;
    }
}
