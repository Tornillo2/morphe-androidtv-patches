package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MovingSubList<E> extends AbstractList<E> implements RandomAccess {
    public int _size;
    public int fromIndex;

    @NotNull
    public final List<E> list;

    /* JADX WARN: Multi-variable type inference failed */
    public MovingSubList(@NotNull List<? extends E> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public E get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this._size);
        return this.list.get(this.fromIndex + i);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this._size;
    }

    public final void move(int i, int i2) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.list.size());
        this.fromIndex = i;
        this._size = i2 - i;
    }
}
