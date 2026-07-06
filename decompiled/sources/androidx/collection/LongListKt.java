package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLongList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongList.kt\nandroidx/collection/LongListKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 LongList.kt\nandroidx/collection/MutableLongList\n*L\n1#1,969:1\n1#2:970\n713#3,2:971\n713#3,2:973\n713#3,2:975\n713#3,2:977\n713#3,2:979\n713#3,2:981\n*S KotlinDebug\n*F\n+ 1 LongList.kt\nandroidx/collection/LongListKt\n*L\n938#1:971,2\n947#1:973,2\n948#1:975,2\n958#1:977,2\n959#1:979,2\n960#1:981,2\n*E\n"})
public final class LongListKt {

    @NotNull
    public static final LongList EmptyLongList = new MutableLongList(0);

    @NotNull
    public static final LongList emptyLongList() {
        return EmptyLongList;
    }

    @NotNull
    public static final LongList longListOf() {
        return EmptyLongList;
    }

    @NotNull
    public static final MutableLongList mutableLongListOf() {
        return new MutableLongList(0, 1, null);
    }

    @NotNull
    public static final LongList longListOf(long j) {
        return mutableLongListOf(j);
    }

    @NotNull
    public static final MutableLongList mutableLongListOf(long j) {
        MutableLongList mutableLongList = new MutableLongList(1);
        mutableLongList.add(j);
        return mutableLongList;
    }

    @NotNull
    public static final LongList longListOf(long j, long j2) {
        return mutableLongListOf(j, j2);
    }

    @NotNull
    public static final LongList longListOf(long j, long j2, long j3) {
        return mutableLongListOf(j, j2, j3);
    }

    @NotNull
    public static final LongList longListOf(@NotNull long... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableLongList mutableLongList = new MutableLongList(elements.length);
        mutableLongList.plusAssign(elements);
        return mutableLongList;
    }

    @NotNull
    public static final MutableLongList mutableLongListOf(long j, long j2) {
        MutableLongList mutableLongList = new MutableLongList(2);
        mutableLongList.add(j);
        mutableLongList.add(j2);
        return mutableLongList;
    }

    @NotNull
    public static final MutableLongList mutableLongListOf(long j, long j2, long j3) {
        MutableLongList mutableLongList = new MutableLongList(3);
        mutableLongList.add(j);
        mutableLongList.add(j2);
        mutableLongList.add(j3);
        return mutableLongList;
    }

    @NotNull
    public static final MutableLongList mutableLongListOf(@NotNull long... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableLongList mutableLongList = new MutableLongList(elements.length);
        mutableLongList.plusAssign(elements);
        return mutableLongList;
    }
}
