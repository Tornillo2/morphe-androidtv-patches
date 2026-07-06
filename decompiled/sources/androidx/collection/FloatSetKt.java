package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFloatSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatSet.kt\nandroidx/collection/FloatSetKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,853:1\n1#2:854\n*E\n"})
public final class FloatSetKt {

    @NotNull
    public static final MutableFloatSet EmptyFloatSet = new MutableFloatSet(0);

    @NotNull
    public static final float[] EmptyFloatArray = new float[0];

    @NotNull
    public static final FloatSet emptyFloatSet() {
        return EmptyFloatSet;
    }

    @NotNull
    public static final FloatSet floatSetOf() {
        return EmptyFloatSet;
    }

    @NotNull
    public static final float[] getEmptyFloatArray() {
        return EmptyFloatArray;
    }

    public static final int hash(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f) * (-862048943);
        return iFloatToIntBits ^ (iFloatToIntBits << 16);
    }

    @NotNull
    public static final MutableFloatSet mutableFloatSetOf() {
        return new MutableFloatSet(0, 1, null);
    }

    @NotNull
    public static final FloatSet floatSetOf(float f) {
        return mutableFloatSetOf(f);
    }

    @NotNull
    public static final MutableFloatSet mutableFloatSetOf(float f) {
        MutableFloatSet mutableFloatSet = new MutableFloatSet(1);
        mutableFloatSet.plusAssign(f);
        return mutableFloatSet;
    }

    @NotNull
    public static final FloatSet floatSetOf(float f, float f2) {
        return mutableFloatSetOf(f, f2);
    }

    @NotNull
    public static final FloatSet floatSetOf(float f, float f2, float f3) {
        return mutableFloatSetOf(f, f2, f3);
    }

    @NotNull
    public static final MutableFloatSet mutableFloatSetOf(float f, float f2) {
        MutableFloatSet mutableFloatSet = new MutableFloatSet(2);
        mutableFloatSet.plusAssign(f);
        mutableFloatSet.plusAssign(f2);
        return mutableFloatSet;
    }

    @NotNull
    public static final FloatSet floatSetOf(@NotNull float... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableFloatSet mutableFloatSet = new MutableFloatSet(elements.length);
        mutableFloatSet.plusAssign(elements);
        return mutableFloatSet;
    }

    @NotNull
    public static final MutableFloatSet mutableFloatSetOf(float f, float f2, float f3) {
        MutableFloatSet mutableFloatSet = new MutableFloatSet(3);
        mutableFloatSet.plusAssign(f);
        mutableFloatSet.plusAssign(f2);
        mutableFloatSet.plusAssign(f3);
        return mutableFloatSet;
    }

    @NotNull
    public static final MutableFloatSet mutableFloatSetOf(@NotNull float... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableFloatSet mutableFloatSet = new MutableFloatSet(elements.length);
        mutableFloatSet.plusAssign(elements);
        return mutableFloatSet;
    }
}
