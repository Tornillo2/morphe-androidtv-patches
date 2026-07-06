package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nScatterSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterSet.kt\nandroidx/collection/ScatterSetKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1100:1\n1#2:1101\n*E\n"})
public final class ScatterSetKt {

    @NotNull
    public static final MutableScatterSet<Object> EmptyScatterSet = new MutableScatterSet<>(0);

    @NotNull
    public static final <E> ScatterSet<E> emptyScatterSet() {
        MutableScatterSet<Object> mutableScatterSet = EmptyScatterSet;
        Intrinsics.checkNotNull(mutableScatterSet, "null cannot be cast to non-null type androidx.collection.ScatterSet<E of androidx.collection.ScatterSetKt.emptyScatterSet>");
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> MutableScatterSet<E> mutableScatterSetOf() {
        return new MutableScatterSet<>(0, 1, null);
    }

    @NotNull
    public static final <E> ScatterSet<E> scatterSetOf() {
        MutableScatterSet<Object> mutableScatterSet = EmptyScatterSet;
        Intrinsics.checkNotNull(mutableScatterSet, "null cannot be cast to non-null type androidx.collection.ScatterSet<E of androidx.collection.ScatterSetKt.scatterSetOf>");
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> MutableScatterSet<E> mutableScatterSetOf(E e) {
        MutableScatterSet<E> mutableScatterSet = new MutableScatterSet<>(1);
        mutableScatterSet.plusAssign(e);
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> ScatterSet<E> scatterSetOf(E e) {
        return mutableScatterSetOf(e);
    }

    @NotNull
    public static final <E> ScatterSet<E> scatterSetOf(E e, E e2) {
        return mutableScatterSetOf(e, e2);
    }

    @NotNull
    public static final <E> MutableScatterSet<E> mutableScatterSetOf(E e, E e2) {
        MutableScatterSet<E> mutableScatterSet = new MutableScatterSet<>(2);
        mutableScatterSet.plusAssign(e);
        mutableScatterSet.plusAssign(e2);
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> ScatterSet<E> scatterSetOf(E e, E e2, E e3) {
        return mutableScatterSetOf(e, e2, e3);
    }

    @NotNull
    public static final <E> ScatterSet<E> scatterSetOf(@NotNull E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableScatterSet mutableScatterSet = new MutableScatterSet(elements.length);
        mutableScatterSet.plusAssign((Object[]) elements);
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> MutableScatterSet<E> mutableScatterSetOf(E e, E e2, E e3) {
        MutableScatterSet<E> mutableScatterSet = new MutableScatterSet<>(3);
        mutableScatterSet.plusAssign(e);
        mutableScatterSet.plusAssign(e2);
        mutableScatterSet.plusAssign(e3);
        return mutableScatterSet;
    }

    @NotNull
    public static final <E> MutableScatterSet<E> mutableScatterSetOf(@NotNull E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableScatterSet<E> mutableScatterSet = new MutableScatterSet<>(elements.length);
        mutableScatterSet.plusAssign((Object[]) elements);
        return mutableScatterSet;
    }
}
