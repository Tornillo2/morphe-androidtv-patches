package org.apache.commons.lang3.tuple;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutablePair<L, R> extends Pair<L, R> {
    public static final long serialVersionUID = 4954918890077093841L;
    public L left;
    public R right;

    public MutablePair() {
    }

    public static <L, R> MutablePair<L, R> of(L l, R r) {
        return new MutablePair<>(l, r);
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public L getLeft() {
        return this.left;
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public R getRight() {
        return this.right;
    }

    public void setLeft(L l) {
        this.left = l;
    }

    public void setRight(R r) {
        this.right = r;
    }

    @Override // java.util.Map.Entry
    public R setValue(R r) {
        R right = getRight();
        setRight(r);
        return right;
    }

    public MutablePair(L l, R r) {
        this.left = l;
        this.right = r;
    }
}
