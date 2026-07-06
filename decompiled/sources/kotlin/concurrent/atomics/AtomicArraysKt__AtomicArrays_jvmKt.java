package kotlin.concurrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AtomicArraysKt__AtomicArrays_jvmKt extends AtomicArraysKt__AtomicArrays_commonKt {
    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicIntegerArray asJavaAtomicArray(@NotNull AtomicIntegerArray atomicIntegerArray) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicIntegerArray asKotlinAtomicArray(@NotNull AtomicIntegerArray atomicIntegerArray) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        return atomicIntegerArray;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicLongArray asJavaAtomicArray(@NotNull AtomicLongArray atomicLongArray) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final AtomicLongArray asKotlinAtomicArray(@NotNull AtomicLongArray atomicLongArray) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        return atomicLongArray;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final <T> AtomicReferenceArray<T> asJavaAtomicArray(@NotNull AtomicReferenceArray<T> atomicReferenceArray) {
        Intrinsics.checkNotNullParameter(atomicReferenceArray, "<this>");
        return atomicReferenceArray;
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    @NotNull
    public static final <T> AtomicReferenceArray<T> asKotlinAtomicArray(@NotNull AtomicReferenceArray<T> atomicReferenceArray) {
        Intrinsics.checkNotNullParameter(atomicReferenceArray, "<this>");
        return atomicReferenceArray;
    }
}
