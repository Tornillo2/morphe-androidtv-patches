package androidx.collection;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FloatObjectMapKt {

    @NotNull
    public static final MutableFloatObjectMap EmptyFloatObjectMap = new MutableFloatObjectMap(0);

    @NotNull
    public static final <V> FloatObjectMap<V> emptyFloatObjectMap() {
        MutableFloatObjectMap mutableFloatObjectMap = EmptyFloatObjectMap;
        Intrinsics.checkNotNull(mutableFloatObjectMap, "null cannot be cast to non-null type androidx.collection.FloatObjectMap<V of androidx.collection.FloatObjectMapKt.emptyFloatObjectMap>");
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf() {
        MutableFloatObjectMap mutableFloatObjectMap = EmptyFloatObjectMap;
        Intrinsics.checkNotNull(mutableFloatObjectMap, "null cannot be cast to non-null type androidx.collection.FloatObjectMap<V of androidx.collection.FloatObjectMapKt.floatObjectMapOf>");
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf() {
        return new MutableFloatObjectMap<>(0, 1, null);
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf(float f, V v) {
        MutableFloatObjectMap mutableFloatObjectMap = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf(float f, V v) {
        MutableFloatObjectMap<V> mutableFloatObjectMap = new MutableFloatObjectMap<>(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf(float f, V v, float f2, V v2) {
        MutableFloatObjectMap mutableFloatObjectMap = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf(float f, V v, float f2, V v2) {
        MutableFloatObjectMap<V> mutableFloatObjectMap = new MutableFloatObjectMap<>(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3) {
        MutableFloatObjectMap mutableFloatObjectMap = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3) {
        MutableFloatObjectMap<V> mutableFloatObjectMap = new MutableFloatObjectMap<>(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3, float f4, V v4) {
        MutableFloatObjectMap mutableFloatObjectMap = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        mutableFloatObjectMap.set(f4, v4);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3, float f4, V v4) {
        MutableFloatObjectMap<V> mutableFloatObjectMap = new MutableFloatObjectMap<>(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        mutableFloatObjectMap.set(f4, v4);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> FloatObjectMap<V> floatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3, float f4, V v4, float f5, V v5) {
        MutableFloatObjectMap mutableFloatObjectMap = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        mutableFloatObjectMap.set(f4, v4);
        mutableFloatObjectMap.set(f5, v5);
        return mutableFloatObjectMap;
    }

    @NotNull
    public static final <V> MutableFloatObjectMap<V> mutableFloatObjectMapOf(float f, V v, float f2, V v2, float f3, V v3, float f4, V v4, float f5, V v5) {
        MutableFloatObjectMap<V> mutableFloatObjectMap = new MutableFloatObjectMap<>(0, 1, null);
        mutableFloatObjectMap.set(f, v);
        mutableFloatObjectMap.set(f2, v2);
        mutableFloatObjectMap.set(f3, v3);
        mutableFloatObjectMap.set(f4, v4);
        mutableFloatObjectMap.set(f5, v5);
        return mutableFloatObjectMap;
    }
}
