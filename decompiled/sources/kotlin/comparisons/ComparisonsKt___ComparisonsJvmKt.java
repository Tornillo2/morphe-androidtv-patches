package kotlin.comparisons;

import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
    @SinceKotlin(version = "1.1")
    @NotNull
    public static <T extends Comparable<? super T>> T maxOf(@NotNull T a, @NotNull T b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.compareTo(b) >= 0 ? a : b;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a, @NotNull T b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.compareTo(b) <= 0 ? a : b;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte maxOf(byte b, byte b2) {
        return (byte) Math.max((int) b, (int) b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte minOf(byte b, byte b2) {
        return (byte) Math.min((int) b, (int) b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short maxOf(short s, short s2) {
        return (short) Math.max((int) s, (int) s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short minOf(short s, short s2) {
        return (short) Math.min((int) s, (int) s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final int maxOf(int i, int i2) {
        return Math.max(i, i2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final int minOf(int i, int i2) {
        return Math.min(i, i2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final long maxOf(long j, long j2) {
        return Math.max(j, j2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final long minOf(long j, long j2) {
        return Math.min(j, j2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final float maxOf(float f, float f2) {
        return Math.max(f, f2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final float minOf(float f, float f2) {
        return Math.min(f, f2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final double maxOf(double d, double d2) {
        return Math.max(d, d2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final double minOf(double d, double d2) {
        return Math.min(d, d2);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a, @NotNull T b, @NotNull T c) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(c, "c");
        return (T) maxOf(a, maxOf(b, c));
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a, @NotNull T b, @NotNull T c) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(c, "c");
        return (T) minOf(a, minOf(b, c));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte maxOf(byte b, byte b2, byte b3) {
        return (byte) Math.max((int) b, Math.max((int) b2, (int) b3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte minOf(byte b, byte b2, byte b3) {
        return (byte) Math.min((int) b, Math.min((int) b2, (int) b3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short maxOf(short s, short s2, short s3) {
        return (short) Math.max((int) s, Math.max((int) s2, (int) s3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short minOf(short s, short s2, short s3) {
        return (short) Math.min((int) s, Math.min((int) s2, (int) s3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final int maxOf(int i, int i2, int i3) {
        return Math.max(i, Math.max(i2, i3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final int minOf(int i, int i2, int i3) {
        return Math.min(i, Math.min(i2, i3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final long maxOf(long j, long j2, long j3) {
        return Math.max(j, Math.max(j2, j3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final long minOf(long j, long j2, long j3) {
        return Math.min(j, Math.min(j2, j3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final float maxOf(float f, float f2, float f3) {
        return Math.max(f, Math.max(f2, f3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final float minOf(float f, float f2, float f3) {
        return Math.min(f, Math.min(f2, f3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final double maxOf(double d, double d2, double d3) {
        return Math.max(d, Math.max(d2, d3));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final double minOf(double d, double d2, double d3) {
        return Math.min(d, Math.min(d2, d3));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t : other) {
            a = (T) maxOf(a, t);
        }
        return a;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t : other) {
            a = (T) minOf(a, t);
        }
        return a;
    }

    @SinceKotlin(version = "1.4")
    public static final byte maxOf(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = (byte) Math.max((int) b, (int) b2);
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    public static final byte minOf(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = (byte) Math.min((int) b, (int) b2);
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    public static final short maxOf(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = (short) Math.max((int) s, (int) s2);
        }
        return s;
    }

    @SinceKotlin(version = "1.4")
    public static final short minOf(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = (short) Math.min((int) s, (int) s2);
        }
        return s;
    }

    @SinceKotlin(version = "1.4")
    public static final int maxOf(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = Math.max(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = "1.4")
    public static final int minOf(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = Math.min(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = "1.4")
    public static final long maxOf(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = Math.max(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = "1.4")
    public static final long minOf(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = Math.min(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = "1.4")
    public static final float maxOf(float f, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f2 : other) {
            f = Math.max(f, f2);
        }
        return f;
    }

    @SinceKotlin(version = "1.4")
    public static final float minOf(float f, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f2 : other) {
            f = Math.min(f, f2);
        }
        return f;
    }

    @SinceKotlin(version = "1.4")
    public static final double maxOf(double d, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d2 : other) {
            d = Math.max(d, d2);
        }
        return d;
    }

    @SinceKotlin(version = "1.4")
    public static final double minOf(double d, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d2 : other) {
            d = Math.min(d, d2);
        }
        return d;
    }
}
