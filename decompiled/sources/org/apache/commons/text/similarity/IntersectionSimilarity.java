package org.apache.commons.text.similarity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class IntersectionSimilarity<T> implements SimilarityScore<IntersectionResult> {
    public final Function<CharSequence, Collection<T>> converter;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BagCount {
        public int count;

        public BagCount() {
            this.count = 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class TinyBag {
        public final Map<T, BagCount> map;

        public TinyBag(int i) {
            this.map = new HashMap(i);
        }

        public void add(T t) {
            BagCount bagCount = this.map.get(t);
            if (bagCount == null) {
                this.map.put(t, new BagCount());
            } else {
                bagCount.count++;
            }
        }

        public Set<Map.Entry<T, BagCount>> entrySet() {
            return this.map.entrySet();
        }

        public int getCount(Object obj) {
            BagCount bagCount = this.map.get(obj);
            if (bagCount != null) {
                return bagCount.count;
            }
            return 0;
        }

        public int uniqueElementSize() {
            return this.map.size();
        }
    }

    public IntersectionSimilarity(Function<CharSequence, Collection<T>> function) {
        if (function == null) {
            throw new IllegalArgumentException("Converter must not be null");
        }
        this.converter = function;
    }

    public static <T> int getIntersection(Set<T> set, Set<T> set2) {
        Iterator<T> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (set2.contains(it.next())) {
                i++;
            }
        }
        return i;
    }

    public final IntersectionSimilarity<T>.TinyBag toBag(Collection<T> collection) {
        IntersectionSimilarity<T>.TinyBag tinyBag = new TinyBag(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            tinyBag.add(it.next());
        }
        return tinyBag;
    }

    @Override // org.apache.commons.text.similarity.SimilarityScore
    public IntersectionResult apply(CharSequence charSequence, CharSequence charSequence2) {
        int intersection;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        Collection<T> collectionApply = this.converter.apply(charSequence);
        Collection<T> collectionApply2 = this.converter.apply(charSequence2);
        int size = collectionApply.size();
        int size2 = collectionApply2.size();
        if (Math.min(size, size2) == 0) {
            return new IntersectionResult(size, size2, 0);
        }
        if ((collectionApply instanceof Set) && (collectionApply2 instanceof Set)) {
            intersection = size < size2 ? getIntersection((Set) collectionApply, (Set) collectionApply2) : getIntersection((Set) collectionApply2, (Set) collectionApply);
        } else {
            IntersectionSimilarity<T>.TinyBag bag = toBag(collectionApply);
            IntersectionSimilarity<T>.TinyBag bag2 = toBag(collectionApply2);
            intersection = bag.map.size() < bag2.map.size() ? getIntersection(bag, bag2) : getIntersection(bag2, bag);
        }
        return new IntersectionResult(size, size2, intersection);
    }

    public final int getIntersection(IntersectionSimilarity<T>.TinyBag tinyBag, IntersectionSimilarity<T>.TinyBag tinyBag2) {
        int iMin = 0;
        for (Map.Entry<T, BagCount> entry : tinyBag.entrySet()) {
            iMin += Math.min(entry.getValue().count, tinyBag2.getCount(entry.getKey()));
        }
        return iMin;
    }
}
