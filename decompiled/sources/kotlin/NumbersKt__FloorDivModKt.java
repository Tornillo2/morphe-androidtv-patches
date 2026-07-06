package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class NumbersKt__FloorDivModKt extends NumbersKt__BigIntegersKt {
    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(byte b, byte b2) {
        int i = b / b2;
        return ((b ^ b2) >= 0 || b2 * i == b) ? i : i - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final byte mod(byte b, byte b2) {
        int i = b % b2;
        return (byte) (i + (b2 & (((i ^ b2) & ((-i) | i)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(byte b, short s) {
        int i = b / s;
        return ((b ^ s) >= 0 || s * i == b) ? i : i - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final short mod(byte b, short s) {
        int i = b % s;
        return (short) (i + (s & (((i ^ s) & ((-i) | i)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(byte b, int i) {
        int i2 = b / i;
        return ((b ^ i) >= 0 || i * i2 == b) ? i2 : i2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int mod(byte b, int i) {
        int i2 = b % i;
        return i2 + (i & (((i2 ^ i) & ((-i2) | i2)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(byte b, long j) {
        long j2 = b;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long mod(byte b, long j) {
        long j2 = ((long) b) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(short s, byte b) {
        int i = s / b;
        return ((s ^ b) >= 0 || b * i == s) ? i : i - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final byte mod(short s, byte b) {
        int i = s % b;
        return (byte) (i + (b & (((i ^ b) & ((-i) | i)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(short s, short s2) {
        int i = s / s2;
        return ((s ^ s2) >= 0 || s2 * i == s) ? i : i - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final short mod(short s, short s2) {
        int i = s % s2;
        return (short) (i + (s2 & (((i ^ s2) & ((-i) | i)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(short s, int i) {
        int i2 = s / i;
        return ((s ^ i) >= 0 || i * i2 == s) ? i2 : i2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int mod(short s, int i) {
        int i2 = s % i;
        return i2 + (i & (((i2 ^ i) & ((-i2) | i2)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(short s, long j) {
        long j2 = s;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long mod(short s, long j) {
        long j2 = ((long) s) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(int i, byte b) {
        int i2 = i / b;
        return ((i ^ b) >= 0 || b * i2 == i) ? i2 : i2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final byte mod(int i, byte b) {
        int i2 = i % b;
        return (byte) (i2 + (b & (((i2 ^ b) & ((-i2) | i2)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(int i, short s) {
        int i2 = i / s;
        return ((i ^ s) >= 0 || s * i2 == i) ? i2 : i2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final short mod(int i, short s) {
        int i2 = i % s;
        return (short) (i2 + (s & (((i2 ^ s) & ((-i2) | i2)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int floorDiv(int i, int i2) {
        int i3 = i / i2;
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int mod(int i, int i2) {
        int i3 = i % i2;
        return i3 + (i2 & (((i3 ^ i2) & ((-i3) | i3)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(int i, long j) {
        long j2 = i;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long mod(int i, long j) {
        long j2 = ((long) i) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(long j, byte b) {
        long j2 = b;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final byte mod(long j, byte b) {
        long j2 = j % b;
        return (byte) (j2 + (r0 & (((j2 ^ r0) & ((-j2) | j2)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(long j, short s) {
        long j2 = s;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final short mod(long j, short s) {
        long j2 = j % s;
        return (short) (j2 + (r0 & (((j2 ^ r0) & ((-j2) | j2)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(long j, int i) {
        long j2 = i;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final int mod(long j, int i) {
        long j2 = i;
        long j3 = j % j2;
        return (int) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long floorDiv(long j, long j2) {
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final float mod(float f, float f2) {
        float f3 = f % f2;
        return (f3 == 0.0f || Math.signum(f3) == Math.signum(f2)) ? f3 : f3 + f2;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final double mod(float f, double d) {
        double d2 = ((double) f) % d;
        return (d2 == 0.0d || Math.signum(d2) == Math.signum(d)) ? d2 : d2 + d;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final double mod(double d, float f) {
        double d2 = f;
        double d3 = d % d2;
        return (d3 == 0.0d || Math.signum(d3) == Math.signum(d2)) ? d3 : d3 + d2;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    public static final double mod(double d, double d2) {
        double d3 = d % d2;
        return (d3 == 0.0d || Math.signum(d3) == Math.signum(d2)) ? d3 : d3 + d2;
    }
}
