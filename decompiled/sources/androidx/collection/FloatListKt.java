package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFloatList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatListKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 FloatList.kt\nandroidx/collection/MutableFloatList\n*L\n1#1,969:1\n1#2:970\n713#3,2:971\n713#3,2:973\n713#3,2:975\n713#3,2:977\n713#3,2:979\n713#3,2:981\n*S KotlinDebug\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatListKt\n*L\n938#1:971,2\n947#1:973,2\n948#1:975,2\n958#1:977,2\n959#1:979,2\n960#1:981,2\n*E\n"})
public final class FloatListKt {

    @NotNull
    public static final FloatList EmptyFloatList = new MutableFloatList(0);

    @NotNull
    public static final FloatList emptyFloatList() {
        return EmptyFloatList;
    }

    @NotNull
    public static final FloatList floatListOf() {
        return EmptyFloatList;
    }

    @NotNull
    public static final MutableFloatList mutableFloatListOf() {
        return new MutableFloatList(0, 1, null);
    }

    @NotNull
    public static final FloatList floatListOf(float f) {
        return mutableFloatListOf(f);
    }

    @NotNull
    public static final MutableFloatList mutableFloatListOf(float f) {
        MutableFloatList mutableFloatList = new MutableFloatList(1);
        mutableFloatList.add(f);
        return mutableFloatList;
    }

    @NotNull
    public static final FloatList floatListOf(float f, float f2) {
        return mutableFloatListOf(f, f2);
    }

    @NotNull
    public static final FloatList floatListOf(float f, float f2, float f3) {
        return mutableFloatListOf(f, f2, f3);
    }

    @NotNull
    public static final FloatList floatListOf(@NotNull float... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableFloatList mutableFloatList = new MutableFloatList(elements.length);
        mutableFloatList.plusAssign(elements);
        return mutableFloatList;
    }

    @NotNull
    public static final MutableFloatList mutableFloatListOf(float f, float f2) {
        MutableFloatList mutableFloatList = new MutableFloatList(2);
        mutableFloatList.add(f);
        mutableFloatList.add(f2);
        return mutableFloatList;
    }

    @NotNull
    public static final MutableFloatList mutableFloatListOf(float f, float f2, float f3) {
        MutableFloatList mutableFloatList = new MutableFloatList(3);
        mutableFloatList.add(f);
        mutableFloatList.add(f2);
        mutableFloatList.add(f3);
        return mutableFloatList;
    }

    @NotNull
    public static final MutableFloatList mutableFloatListOf(@NotNull float... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableFloatList mutableFloatList = new MutableFloatList(elements.length);
        mutableFloatList.plusAssign(elements);
        return mutableFloatList;
    }
}
