package org.apache.commons.lang3.tuple;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ImmutablePair<L, R> extends Pair<L, R> {
    public static final ImmutablePair NULL = new ImmutablePair(null, null);
    public static final long serialVersionUID = 4954918890077093841L;
    public final L left;
    public final R right;

    public ImmutablePair(L l, R r) {
        this.left = l;
        this.right = r;
    }

    public static <L, R> ImmutablePair<L, R> nullPair() {
        return NULL;
    }

    public static <L, R> ImmutablePair<L, R> of(L l, R r) {
        return new ImmutablePair<>(l, r);
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public L getLeft() {
        return this.left;
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public R getRight() {
        return this.right;
    }

    @Override // java.util.Map.Entry
    public R setValue(R r) {
        throw new UnsupportedOperationException();
    }
}
