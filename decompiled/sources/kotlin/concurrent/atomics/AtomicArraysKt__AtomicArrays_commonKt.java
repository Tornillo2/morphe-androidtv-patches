package kotlin.concurrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AtomicArraysKt__AtomicArrays_commonKt {
    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final <T> AtomicReferenceArray<T> AtomicArray(int i, Function1<? super Integer, ? extends T> init) {
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicIntegerArray AtomicIntArray(int i, @NotNull Function1<? super Integer, Integer> init) {
        Intrinsics.checkNotNullParameter(init, "init");
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = init.invoke(Integer.valueOf(i2)).intValue();
        }
        return new AtomicIntegerArray(iArr);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicLongArray AtomicLongArray(int i, @NotNull Function1<? super Integer, Long> init) {
        Intrinsics.checkNotNullParameter(init, "init");
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = init.invoke(Integer.valueOf(i2)).longValue();
        }
        return new AtomicLongArray(jArr);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int decrementAndFetchAt(@NotNull AtomicIntegerArray atomicIntegerArray, int i) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray.addAndGet(i, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndDecrementAt(@NotNull AtomicIntegerArray atomicIntegerArray, int i) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray.getAndAdd(i, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndIncrementAt(@NotNull AtomicIntegerArray atomicIntegerArray, int i) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray.getAndAdd(i, 1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int incrementAndFetchAt(@NotNull AtomicIntegerArray atomicIntegerArray, int i) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray.addAndGet(i, 1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long decrementAndFetchAt(@NotNull AtomicLongArray atomicLongArray, int i) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray.addAndGet(i, -1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndDecrementAt(@NotNull AtomicLongArray atomicLongArray, int i) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray.getAndAdd(i, -1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndIncrementAt(@NotNull AtomicLongArray atomicLongArray, int i) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray.getAndAdd(i, 1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long incrementAndFetchAt(@NotNull AtomicLongArray atomicLongArray, int i) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray.addAndGet(i, 1L);
    }
}
