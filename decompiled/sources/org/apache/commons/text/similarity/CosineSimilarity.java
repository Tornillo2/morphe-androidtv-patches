package org.apache.commons.text.similarity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CosineSimilarity {
    public Double cosineSimilarity(Map<CharSequence, Integer> map, Map<CharSequence, Integer> map2) {
        if (map == null || map2 == null) {
            throw new IllegalArgumentException("Vectors must not be null");
        }
        double dDot = dot(map, map2, getIntersection(map, map2));
        Iterator<Integer> it = map.values().iterator();
        double dSqrt = 0.0d;
        double dPow = 0.0d;
        while (it.hasNext()) {
            dPow += Math.pow(it.next().intValue(), 2.0d);
        }
        Iterator<Integer> it2 = map2.values().iterator();
        double dPow2 = 0.0d;
        while (it2.hasNext()) {
            dPow2 += Math.pow(it2.next().intValue(), 2.0d);
        }
        if (dPow > 0.0d && dPow2 > 0.0d) {
            dSqrt = dDot / (Math.sqrt(dPow2) * Math.sqrt(dPow));
        }
        return Double.valueOf(dSqrt);
    }

    public final double dot(Map<CharSequence, Integer> map, Map<CharSequence, Integer> map2, Set<CharSequence> set) {
        long jIntValue = 0;
        for (CharSequence charSequence : set) {
            jIntValue += (long) (map2.get(charSequence).intValue() * map.get(charSequence).intValue());
        }
        return jIntValue;
    }

    public final Set<CharSequence> getIntersection(Map<CharSequence, Integer> map, Map<CharSequence, Integer> map2) {
        HashSet hashSet = new HashSet(map.keySet());
        hashSet.retainAll(map2.keySet());
        return hashSet;
    }
}
