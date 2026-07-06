package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLongSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongSet.kt\nandroidx/collection/LongSetKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,853:1\n1#2:854\n*E\n"})
public final class LongSetKt {

    @NotNull
    public static final MutableLongSet EmptyLongSet = new MutableLongSet(0);

    @NotNull
    public static final long[] EmptyLongArray = new long[0];

    @NotNull
    public static final LongSet emptyLongSet() {
        return EmptyLongSet;
    }

    @NotNull
    public static final long[] getEmptyLongArray() {
        return EmptyLongArray;
    }

    public static final int hash(long j) {
        int iM = FloatFloatPair$$ExternalSyntheticBackport0.m(j) * (-862048943);
        return iM ^ (iM << 16);
    }

    @NotNull
    public static final LongSet longSetOf() {
        return EmptyLongSet;
    }

    @NotNull
    public static final MutableLongSet mutableLongSetOf() {
        return new MutableLongSet(0, 1, null);
    }

    @NotNull
    public static final LongSet longSetOf(long j) {
        return mutableLongSetOf(j);
    }

    @NotNull
    public static final MutableLongSet mutableLongSetOf(long j) {
        MutableLongSet mutableLongSet = new MutableLongSet(1);
        mutableLongSet.plusAssign(j);
        return mutableLongSet;
    }

    @NotNull
    public static final LongSet longSetOf(long j, long j2) {
        return mutableLongSetOf(j, j2);
    }

    @NotNull
    public static final LongSet longSetOf(long j, long j2, long j3) {
        return mutableLongSetOf(j, j2, j3);
    }

    @NotNull
    public static final MutableLongSet mutableLongSetOf(long j, long j2) {
        MutableLongSet mutableLongSet = new MutableLongSet(2);
        mutableLongSet.plusAssign(j);
        mutableLongSet.plusAssign(j2);
        return mutableLongSet;
    }

    @NotNull
    public static final LongSet longSetOf(@NotNull long... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableLongSet mutableLongSet = new MutableLongSet(elements.length);
        mutableLongSet.plusAssign(elements);
        return mutableLongSet;
    }

    @NotNull
    public static final MutableLongSet mutableLongSetOf(long j, long j2, long j3) {
        MutableLongSet mutableLongSet = new MutableLongSet(3);
        mutableLongSet.plusAssign(j);
        mutableLongSet.plusAssign(j2);
        mutableLongSet.plusAssign(j3);
        return mutableLongSet;
    }

    @NotNull
    public static final MutableLongSet mutableLongSetOf(@NotNull long... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableLongSet mutableLongSet = new MutableLongSet(elements.length);
        mutableLongSet.plusAssign(elements);
        return mutableLongSet;
    }
}
