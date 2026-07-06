package com.google.common.math;

import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class DoubleMath {

    @VisibleForTesting
    public static final int MAX_FACTORIAL = 170;
    public static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    public static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    public static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    public static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    public static final double LN_2 = Math.log(2.0d);

    @VisibleForTesting
    public static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* JADX INFO: renamed from: com.google.common.math.DoubleMath$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static double checkFinite(double argument) {
        Preconditions.checkArgument(DoubleUtils.isFinite(argument));
        return argument;
    }

    public static double factorial(int n) {
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, n);
        if (n > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d = 1.0d;
        for (int i = (n & (-16)) + 1; i <= n; i++) {
            d *= (double) i;
        }
        return d * everySixteenthFactorial[n >> 4];
    }

    public static int fuzzyCompare(double a, double b, double tolerance) {
        if (fuzzyEquals(a, b, tolerance)) {
            return 0;
        }
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return Boolean.compare(Double.isNaN(a), Double.isNaN(b));
    }

    public static boolean fuzzyEquals(double a, double b, double tolerance) {
        MathPreconditions.checkNonNegative("tolerance", tolerance);
        if (Math.copySign(a - b, 1.0d) <= tolerance || a == b) {
            return true;
        }
        return Double.isNaN(a) && Double.isNaN(b);
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double x) {
        if (DoubleUtils.isFinite(x)) {
            return x == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(x)) <= Math.getExponent(x);
        }
        return false;
    }

    @GwtIncompatible
    public static boolean isPowerOfTwo(double x) {
        if (x > 0.0d && DoubleUtils.isFinite(x)) {
            long significand = DoubleUtils.getSignificand(x);
            if ((significand & (significand - 1)) == 0) {
                return true;
            }
        }
        return false;
    }

    public static double log2(double x) {
        return Math.log(x) / LN_2;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(double... values) {
        Preconditions.checkArgument(values.length > 0, "Cannot take mean of 0 values");
        double d = values[0];
        checkFinite(d);
        long j = 1;
        for (int i = 1; i < values.length; i++) {
            checkFinite(values[i]);
            j++;
            d += (values[i] - d) / j;
        }
        return d;
    }

    @GwtIncompatible
    public static double roundIntermediate(double x, RoundingMode mode) {
        if (!DoubleUtils.isFinite(x)) {
            throw new ArithmeticException("input is infinite or NaN");
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(x));
                return x;
            case 2:
                return (x >= 0.0d || isMathematicalInteger(x)) ? x : ((long) x) - 1;
            case 3:
                return (x <= 0.0d || isMathematicalInteger(x)) ? x : ((long) x) + 1;
            case 4:
                return x;
            case 5:
                if (isMathematicalInteger(x)) {
                    return x;
                }
                return ((long) x) + ((long) (x > 0.0d ? 1 : -1));
            case 6:
                return Math.rint(x);
            case 7:
                double dRint = Math.rint(x);
                return Math.abs(x - dRint) == 0.5d ? Math.copySign(0.5d, x) + x : dRint;
            case 8:
                double dRint2 = Math.rint(x);
                return Math.abs(x - dRint2) == 0.5d ? x : dRint2;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static BigInteger roundToBigInteger(double x, RoundingMode mode) {
        double dRoundIntermediate = roundIntermediate(x, mode);
        if ((dRoundIntermediate < 9.223372036854776E18d) && ((-9.223372036854776E18d) - dRoundIntermediate < 1.0d)) {
            return BigInteger.valueOf((long) dRoundIntermediate);
        }
        BigInteger bigIntegerShiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(dRoundIntermediate)).shiftLeft(Math.getExponent(dRoundIntermediate) - 52);
        return dRoundIntermediate < 0.0d ? bigIntegerShiftLeft.negate() : bigIntegerShiftLeft;
    }

    @GwtIncompatible
    public static int roundToInt(double x, RoundingMode mode) {
        double dRoundIntermediate = roundIntermediate(x, mode);
        MathPreconditions.checkInRangeForRoundingInputs((dRoundIntermediate > -2.147483649E9d) & (dRoundIntermediate < 2.147483648E9d), x, mode);
        return (int) dRoundIntermediate;
    }

    @GwtIncompatible
    public static long roundToLong(double x, RoundingMode mode) {
        double dRoundIntermediate = roundIntermediate(x, mode);
        MathPreconditions.checkInRangeForRoundingInputs(((-9.223372036854776E18d) - dRoundIntermediate < 1.0d) & (dRoundIntermediate < 9.223372036854776E18d), x, mode);
        return (long) dRoundIntermediate;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @com.google.common.annotations.GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int log2(double r6, java.math.RoundingMode r8) {
        /*
            r0 = 0
            r2 = 0
            r3 = 1
            int r4 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r4 <= 0) goto L10
            boolean r0 = com.google.common.math.DoubleUtils.isFinite(r6)
            if (r0 == 0) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            java.lang.String r1 = "x must be positive and finite"
            com.google.common.base.Preconditions.checkArgument(r0, r1)
            int r0 = java.lang.Math.getExponent(r6)
            boolean r1 = com.google.common.math.DoubleUtils.isNormal(r6)
            if (r1 != 0) goto L2b
            r0 = 4841369599423283200(0x4330000000000000, double:4.503599627370496E15)
            double r6 = r6 * r0
            int r6 = log2(r6, r8)
            int r6 = r6 + (-52)
            return r6
        L2b:
            int[] r1 = com.google.common.math.DoubleMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r8 = r8.ordinal()
            r8 = r1[r8]
            switch(r8) {
                case 1: goto L63;
                case 2: goto L6a;
                case 3: goto L5c;
                case 4: goto L54;
                case 5: goto L4a;
                case 6: goto L3c;
                case 7: goto L3c;
                case 8: goto L3c;
                default: goto L36;
            }
        L36:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            r6.<init>()
            throw r6
        L3c:
            double r6 = com.google.common.math.DoubleUtils.scaleNormalize(r6)
            double r6 = r6 * r6
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L6a
            r2 = 1
            goto L6a
        L4a:
            if (r0 < 0) goto L4d
            r2 = 1
        L4d:
            boolean r6 = isPowerOfTwo(r6)
        L51:
            r6 = r6 ^ r3
            r2 = r2 & r6
            goto L6a
        L54:
            if (r0 >= 0) goto L57
            r2 = 1
        L57:
            boolean r6 = isPowerOfTwo(r6)
            goto L51
        L5c:
            boolean r6 = isPowerOfTwo(r6)
            r2 = r6 ^ 1
            goto L6a
        L63:
            boolean r6 = isPowerOfTwo(r6)
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r6)
        L6a:
            if (r2 == 0) goto L6d
            int r0 = r0 + r3
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.DoubleMath.log2(double, java.math.RoundingMode):int");
    }

    @Deprecated
    public static double mean(int... values) {
        Preconditions.checkArgument(values.length > 0, "Cannot take mean of 0 values");
        long j = 0;
        for (int i : values) {
            j += (long) i;
        }
        return j / ((double) values.length);
    }

    @Deprecated
    public static double mean(long... values) {
        Preconditions.checkArgument(values.length > 0, "Cannot take mean of 0 values");
        double d = values[0];
        long j = 1;
        for (int i = 1; i < values.length; i++) {
            j++;
            d += (values[i] - d) / j;
        }
        return d;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterable<? extends Number> values) {
        return mean(values.iterator());
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterator<? extends Number> values) {
        Preconditions.checkArgument(values.hasNext(), "Cannot take mean of 0 values");
        double dDoubleValue = values.next().doubleValue();
        checkFinite(dDoubleValue);
        long j = 1;
        while (values.hasNext()) {
            double dDoubleValue2 = values.next().doubleValue();
            checkFinite(dDoubleValue2);
            j++;
            dDoubleValue += (dDoubleValue2 - dDoubleValue) / j;
        }
        return dDoubleValue;
    }
}
