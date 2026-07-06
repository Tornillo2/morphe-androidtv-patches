package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class y extends w implements java.util.SortedSet, SortedSet {
    private static final long serialVersionUID = -4929149591599911165L;
    public final java.util.SortedSet b;

    public y(java.util.SortedSet sortedSet) {
        super(sortedSet);
        this.b = sortedSet;
    }

    @Override // java.util.SortedSet
    public final java.util.Comparator comparator() {
        return this.b.comparator();
    }

    @Override // java.util.SortedSet
    public final java.util.SortedSet subSet(Object obj, Object obj2) {
        return new y(this.b.subSet(obj, obj2));
    }

    @Override // java.util.SortedSet
    public final java.util.SortedSet headSet(Object obj) {
        return new y(this.b.headSet(obj));
    }

    @Override // java.util.SortedSet
    public final java.util.SortedSet tailSet(Object obj) {
        return new y(this.b.tailSet(obj));
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.first();
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.b.last();
    }
}
