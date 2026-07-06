package com.google.common.math;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class MathPreconditions {
    public static void checkInRangeForRoundingInputs(boolean condition, double input, RoundingMode mode) {
        if (condition) {
            return;
        }
        throw new ArithmeticException("rounded value is out of range for input " + input + " and rounding mode " + mode);
    }

    public static void checkNoOverflow(boolean condition, String methodName, int a, int b) {
        if (condition) {
            return;
        }
        StringBuilder sb = new StringBuilder("overflow: ");
        sb.append(methodName);
        sb.append("(");
        sb.append(a);
        sb.append(", ");
        throw new ArithmeticException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, b, ")"));
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(String role, int x) {
        if (x >= 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static int checkPositive(String role, int x) {
        if (x > 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }

    public static void checkRoundingUnnecessary(boolean condition) {
        if (!condition) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(String role, long x) {
        if (x >= 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static long checkPositive(String role, long x) {
        if (x > 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }

    @CanIgnoreReturnValue
    public static BigInteger checkNonNegative(String role, BigInteger x) {
        if (x.signum() >= 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static BigInteger checkPositive(String role, BigInteger x) {
        if (x.signum() > 0) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }

    @CanIgnoreReturnValue
    public static double checkNonNegative(String role, double x) {
        if (x >= 0.0d) {
            return x;
        }
        throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }

    public static void checkNoOverflow(boolean condition, String methodName, long a, long b) {
        if (condition) {
            return;
        }
        throw new ArithmeticException("overflow: " + methodName + "(" + a + ", " + b + ")");
    }
}
