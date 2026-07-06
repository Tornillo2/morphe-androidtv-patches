package kotlin.concurrent.internal;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AtomicIntrinsicsKt {
    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final int compareAndExchange(@NotNull AtomicInteger atomicInteger, int i, int i2) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<this>");
        do {
            int i3 = atomicInteger.get();
            if (i != i3) {
                return i3;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final long compareAndExchange(@NotNull AtomicLong atomicLong, long j, long j2) {
        Intrinsics.checkNotNullParameter(atomicLong, "<this>");
        do {
            long j3 = atomicLong.get();
            if (j != j3) {
                return j3;
            }
        } while (!atomicLong.compareAndSet(j, j2));
        return j;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final boolean compareAndExchange(@NotNull AtomicBoolean atomicBoolean, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(atomicBoolean, "<this>");
        do {
            boolean z3 = atomicBoolean.get();
            if (z != z3) {
                return z3;
            }
        } while (!atomicBoolean.compareAndSet(z, z2));
        return z;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final <T> T compareAndExchange(@NotNull AtomicReference<T> atomicReference, T t, T t2) {
        Intrinsics.checkNotNullParameter(atomicReference, "<this>");
        do {
            T t3 = atomicReference.get();
            if (t != t3) {
                return t3;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, t, t2));
        return t;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final int compareAndExchange(@NotNull AtomicIntegerArray atomicIntegerArray, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(atomicIntegerArray, "<this>");
        do {
            int i4 = atomicIntegerArray.get(i);
            if (i2 != i4) {
                return i4;
            }
        } while (!atomicIntegerArray.compareAndSet(i, i2, i3));
        return i2;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final long compareAndExchange(@NotNull AtomicLongArray atomicLongArray, int i, long j, long j2) {
        Intrinsics.checkNotNullParameter(atomicLongArray, "<this>");
        do {
            long j3 = atomicLongArray.get(i);
            if (j != j3) {
                return j3;
            }
        } while (!atomicLongArray.compareAndSet(i, j, j2));
        return j;
    }

    @SinceKotlin(version = "2.1")
    @PublishedApi
    public static final <T> T compareAndExchange(@NotNull AtomicReferenceArray<T> atomicReferenceArray, int i, T t, T t2) {
        Intrinsics.checkNotNullParameter(atomicReferenceArray, "<this>");
        do {
            T t3 = atomicReferenceArray.get(i);
            if (t != t3) {
                return t3;
            }
        } while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray, i, t, t2));
        return t;
    }
}
