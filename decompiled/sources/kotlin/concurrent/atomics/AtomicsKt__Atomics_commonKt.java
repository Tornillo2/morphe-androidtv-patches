package kotlin.concurrent.atomics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AtomicsKt__Atomics_commonKt {
    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int decrementAndFetch(@NotNull AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        return atomicInteger.addAndGet(-1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndDecrement(@NotNull AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        return atomicInteger.getAndAdd(-1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndIncrement(@NotNull AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        return atomicInteger.getAndAdd(1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int incrementAndFetch(@NotNull AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        return atomicInteger.addAndGet(1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final void minusAssign(@NotNull AtomicInteger atomicInteger, int i) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        atomicInteger.addAndGet(-i);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final void plusAssign(@NotNull AtomicInteger atomicInteger, int i) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        atomicInteger.addAndGet(i);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long decrementAndFetch(@NotNull AtomicLong atomicLong) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        return atomicLong.addAndGet(-1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndDecrement(@NotNull AtomicLong atomicLong) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        return atomicLong.getAndAdd(-1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndIncrement(@NotNull AtomicLong atomicLong) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        return atomicLong.getAndAdd(1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long incrementAndFetch(@NotNull AtomicLong atomicLong) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        return atomicLong.addAndGet(1L);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final void minusAssign(@NotNull AtomicLong atomicLong, long j) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        atomicLong.addAndGet(-j);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final void plusAssign(@NotNull AtomicLong atomicLong, long j) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        atomicLong.addAndGet(j);
    }
}
