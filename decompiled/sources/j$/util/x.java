package j$.util;

import java.util.SortedMap;

/* JADX INFO: loaded from: classes2.dex */
public final class x extends u implements SortedMap {
    private static final long serialVersionUID = -8806743815996713206L;
    public final SortedMap e;

    public x(SortedMap sortedMap) {
        super(sortedMap);
        this.e = sortedMap;
    }

    @Override // java.util.SortedMap
    public final java.util.Comparator comparator() {
        return this.e.comparator();
    }

    @Override // java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return new x(this.e.subMap(obj, obj2));
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return new x(this.e.headMap(obj));
    }

    @Override // java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return new x(this.e.tailMap(obj));
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return this.e.firstKey();
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return this.e.lastKey();
    }
}
