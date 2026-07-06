package com.google.common.math;

import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class BigIntegerMath {

    @VisibleForTesting
    public static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;

    @VisibleForTesting
    public static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
    public static final double LN_10 = Math.log(10.0d);
    public static final double LN_2 = Math.log(2.0d);

    /* JADX INFO: renamed from: com.google.common.math.BigIntegerMath$1, reason: invalid class name */
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
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class BigIntegerToDoubleRounder extends ToDoubleRounder<BigInteger> {
        public static final BigIntegerToDoubleRounder INSTANCE = new BigIntegerToDoubleRounder();

        @Override // com.google.common.math.ToDoubleRounder
        public BigInteger minus(BigInteger a, BigInteger b) {
            return a.subtract(b);
        }

        @Override // com.google.common.math.ToDoubleRounder
        public double roundToDoubleArbitrarily(BigInteger bigInteger) {
            return DoubleUtils.bigToDouble(bigInteger);
        }

        @Override // com.google.common.math.ToDoubleRounder
        public int sign(BigInteger bigInteger) {
            return bigInteger.signum();
        }

        @Override // com.google.common.math.ToDoubleRounder
        public BigInteger toX(double d, RoundingMode mode) {
            return DoubleMath.roundToBigInteger(d, mode);
        }
    }

    public static BigInteger binomial(int n, int k) {
        int i;
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, n);
        MathPreconditions.checkNonNegative("k", k);
        int i2 = 1;
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        int[] iArr = LongMath.biggestBinomials;
        if (k < iArr.length && n <= iArr[k]) {
            return BigInteger.valueOf(LongMath.binomial(n, k));
        }
        BigInteger bigIntegerDivide = BigInteger.ONE;
        long j = n;
        int iLog2 = LongMath.log2(j, RoundingMode.CEILING);
        long j2 = 1;
        while (true) {
            int i3 = iLog2;
            while (i2 < k) {
                i = n - i2;
                i2++;
                i3 += iLog2;
                if (i3 >= 63) {
                    break;
                }
                j *= (long) i;
                j2 *= (long) i2;
            }
            return bigIntegerDivide.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
            bigIntegerDivide = bigIntegerDivide.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
            j = i;
            j2 = i2;
        }
    }

    public static BigInteger ceilingPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.CEILING));
    }

    @GwtIncompatible
    public static BigInteger divide(BigInteger p, BigInteger q, RoundingMode mode) {
        return new BigDecimal(p).divide(new BigDecimal(q), 0, mode).toBigIntegerExact();
    }

    public static BigInteger factorial(int n) {
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, n);
        long[] jArr = LongMath.factorials;
        if (n < jArr.length) {
            return BigInteger.valueOf(jArr[n]);
        }
        RoundingMode roundingMode = RoundingMode.CEILING;
        ArrayList arrayList = new ArrayList(IntMath.divide(IntMath.log2(n, roundingMode) * n, 64, roundingMode));
        int length = jArr.length;
        long j = jArr[length - 1];
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j2 = j >> iNumberOfTrailingZeros;
        RoundingMode roundingMode2 = RoundingMode.FLOOR;
        int iLog2 = LongMath.log2(j2, roundingMode2) + 1;
        long j3 = length;
        int iLog22 = LongMath.log2(j3, roundingMode2);
        int i = iLog22 + 1;
        int i2 = 1 << iLog22;
        while (j3 <= n) {
            if ((((long) i2) & j3) != 0) {
                i2 <<= 1;
                i++;
            }
            int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(j3);
            long j4 = j3 >> iNumberOfTrailingZeros2;
            iNumberOfTrailingZeros += iNumberOfTrailingZeros2;
            if ((i - iNumberOfTrailingZeros2) + iLog2 >= 64) {
                arrayList.add(BigInteger.valueOf(j2));
                j2 = 1;
            }
            j2 *= j4;
            iLog2 = LongMath.log2(j2, RoundingMode.FLOOR) + 1;
            j3++;
        }
        if (j2 > 1) {
            arrayList.add(BigInteger.valueOf(j2));
        }
        return listProduct(arrayList, 0, arrayList.size()).shiftLeft(iNumberOfTrailingZeros);
    }

    @GwtIncompatible
    public static boolean fitsInLong(BigInteger x) {
        return x.bitLength() <= 63;
    }

    public static BigInteger floorPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.FLOOR));
    }

    public static boolean isPowerOfTwo(BigInteger x) {
        x.getClass();
        return x.signum() > 0 && x.getLowestSetBit() == x.bitLength() - 1;
    }

    public static BigInteger listProduct(List<BigInteger> nums) {
        return listProduct(nums, 0, nums.size());
    }

    @GwtIncompatible
    public static int log10(BigInteger x, RoundingMode mode) {
        int i;
        int iCompareTo;
        MathPreconditions.checkPositive("x", x);
        if (fitsInLong(x)) {
            return LongMath.log10(x.longValue(), mode);
        }
        int iLog2 = (int) ((((double) log2(x, RoundingMode.FLOOR)) * LN_2) / LN_10);
        BigInteger bigInteger = BigInteger.TEN;
        BigInteger bigIntegerPow = bigInteger.pow(iLog2);
        int iCompareTo2 = bigIntegerPow.compareTo(x);
        if (iCompareTo2 > 0) {
            do {
                iLog2--;
                bigIntegerPow = bigIntegerPow.divide(BigInteger.TEN);
                iCompareTo = bigIntegerPow.compareTo(x);
            } while (iCompareTo > 0);
        } else {
            BigInteger bigIntegerMultiply = bigInteger.multiply(bigIntegerPow);
            int iCompareTo3 = bigIntegerMultiply.compareTo(x);
            while (true) {
                int i2 = iCompareTo3;
                i = iCompareTo2;
                iCompareTo2 = i2;
                if (iCompareTo2 > 0) {
                    break;
                }
                iLog2++;
                BigInteger bigIntegerMultiply2 = BigInteger.TEN.multiply(bigIntegerMultiply);
                iCompareTo3 = bigIntegerMultiply2.compareTo(x);
                bigIntegerPow = bigIntegerMultiply;
                bigIntegerMultiply = bigIntegerMultiply2;
            }
            iCompareTo = i;
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(iCompareTo == 0);
                return iLog2;
            case 2:
            case 3:
                return iLog2;
            case 4:
            case 5:
                return bigIntegerPow.equals(x) ? iLog2 : iLog2 + 1;
            case 6:
            case 7:
            case 8:
                return x.pow(2).compareTo(bigIntegerPow.pow(2).multiply(BigInteger.TEN)) <= 0 ? iLog2 : iLog2 + 1;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int log2(java.math.BigInteger r3, java.math.RoundingMode r4) {
        /*
            r3.getClass()
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkPositive(r0, r3)
            int r0 = r3.bitLength()
            int r1 = r0 + (-1)
            int[] r2 = com.google.common.math.BigIntegerMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r4 = r4.ordinal()
            r4 = r2[r4]
            switch(r4) {
                case 1: goto L4f;
                case 2: goto L4e;
                case 3: goto L4e;
                case 4: goto L46;
                case 5: goto L46;
                case 6: goto L1f;
                case 7: goto L1f;
                case 8: goto L1f;
                default: goto L19;
            }
        L19:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L1f:
            r4 = 256(0x100, float:3.59E-43)
            if (r1 >= r4) goto L33
            java.math.BigInteger r4 = com.google.common.math.BigIntegerMath.SQRT2_PRECOMPUTED_BITS
            int r2 = 256 - r1
            java.math.BigInteger r4 = r4.shiftRight(r2)
            int r3 = r3.compareTo(r4)
            if (r3 > 0) goto L32
            goto L4e
        L32:
            return r0
        L33:
            r4 = 2
            java.math.BigInteger r3 = r3.pow(r4)
            int r3 = r3.bitLength()
            int r3 = r3 + (-1)
            int r4 = r1 * 2
            int r4 = r4 + 1
            if (r3 >= r4) goto L45
            goto L4e
        L45:
            return r0
        L46:
            boolean r3 = isPowerOfTwo(r3)
            if (r3 == 0) goto L4d
            goto L4e
        L4d:
            return r0
        L4e:
            return r1
        L4f:
            boolean r3 = isPowerOfTwo(r3)
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.BigIntegerMath.log2(java.math.BigInteger, java.math.RoundingMode):int");
    }

    @GwtIncompatible
    public static double roundToDouble(BigInteger x, RoundingMode mode) {
        return BigIntegerToDoubleRounder.INSTANCE.roundToDouble(x, mode);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @GwtIncompatible
    public static BigInteger sqrt(BigInteger x, RoundingMode mode) {
        MathPreconditions.checkNonNegative("x", x);
        if (fitsInLong(x)) {
            return BigInteger.valueOf(LongMath.sqrt(x.longValue(), mode));
        }
        BigInteger bigIntegerSqrtFloor = sqrtFloor(x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(bigIntegerSqrtFloor.pow(2).equals(x));
                return bigIntegerSqrtFloor;
            case 2:
            case 3:
                return bigIntegerSqrtFloor;
            case 4:
            case 5:
                int iIntValue = bigIntegerSqrtFloor.intValue();
                return (iIntValue * iIntValue == x.intValue() && bigIntegerSqrtFloor.pow(2).equals(x)) ? bigIntegerSqrtFloor : bigIntegerSqrtFloor.add(BigInteger.ONE);
            case 6:
            case 7:
            case 8:
                if (bigIntegerSqrtFloor.pow(2).add(bigIntegerSqrtFloor).compareTo(x) < 0) {
                    return bigIntegerSqrtFloor.add(BigInteger.ONE);
                }
                return bigIntegerSqrtFloor;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static BigInteger sqrtApproxWithDoubles(BigInteger x) {
        return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(x)), RoundingMode.HALF_EVEN);
    }

    @GwtIncompatible
    public static BigInteger sqrtFloor(BigInteger x) {
        BigInteger bigIntegerShiftLeft;
        int iLog2 = log2(x, RoundingMode.FLOOR);
        if (iLog2 < 1023) {
            bigIntegerShiftLeft = sqrtApproxWithDoubles(x);
        } else {
            int i = (iLog2 - 52) & (-2);
            bigIntegerShiftLeft = sqrtApproxWithDoubles(x.shiftRight(i)).shiftLeft(i >> 1);
        }
        BigInteger bigIntegerShiftRight = bigIntegerShiftLeft.add(x.divide(bigIntegerShiftLeft)).shiftRight(1);
        if (bigIntegerShiftLeft.equals(bigIntegerShiftRight)) {
            return bigIntegerShiftLeft;
        }
        while (true) {
            BigInteger bigIntegerShiftRight2 = bigIntegerShiftRight.add(x.divide(bigIntegerShiftRight)).shiftRight(1);
            if (bigIntegerShiftRight2.compareTo(bigIntegerShiftRight) >= 0) {
                return bigIntegerShiftRight;
            }
            bigIntegerShiftRight = bigIntegerShiftRight2;
        }
    }

    public static BigInteger listProduct(List<BigInteger> nums, int start, int end) {
        int i = end - start;
        if (i == 0) {
            return BigInteger.ONE;
        }
        if (i == 1) {
            return nums.get(start);
        }
        if (i == 2) {
            return nums.get(start).multiply(nums.get(start + 1));
        }
        if (i == 3) {
            return nums.get(start).multiply(nums.get(start + 1)).multiply(nums.get(start + 2));
        }
        int i2 = (end + start) >>> 1;
        return listProduct(nums, start, i2).multiply(listProduct(nums, i2, end));
    }
}
