package com.google.common.math;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.core.location.GpsStatusWrapper;
import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import kotlin.time.InstantKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class IntMath {

    @VisibleForTesting
    public static final int FLOOR_SQRT_MAX_INT = 46340;

    @VisibleForTesting
    public static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;

    @VisibleForTesting
    public static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;

    @VisibleForTesting
    public static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};

    @VisibleForTesting
    public static final int[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, InstantKt.NANOS_PER_SECOND};

    @VisibleForTesting
    public static final int[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    public static final int[] factorials = {1, 1, 2, 6, 24, 120, MediaCodecQuerier.HD_MIN_HEIGHT, 5040, 40320, 362880, 3628800, 39916800, 479001600};

    @VisibleForTesting
    public static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, GpsStatusWrapper.QZSS_SVID_MIN, ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    /* JADX INFO: renamed from: com.google.common.math.IntMath$1, reason: invalid class name */
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

    public static int binomial(int n, int k) {
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, n);
        MathPreconditions.checkNonNegative("k", k);
        int i = 0;
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        int[] iArr = biggestBinomials;
        if (k >= iArr.length || n > iArr[k]) {
            return Integer.MAX_VALUE;
        }
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return n;
        }
        long j = 1;
        while (i < k) {
            long j2 = j * ((long) (n - i));
            i++;
            j = j2 / ((long) i);
        }
        return (int) j;
    }

    public static int ceilingPowerOfTwo(int x) {
        MathPreconditions.checkPositive("x", x);
        if (x <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(x - 1));
        }
        throw new ArithmeticException(ObjectListKt$$ExternalSyntheticOutline1.m("ceilingPowerOfTwo(", x, ") not representable as an int"));
    }

    public static int checkedAdd(int a, int b) {
        long j = ((long) a) + ((long) b);
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedAdd", a, b);
        return i;
    }

    public static int checkedMultiply(int a, int b) {
        long j = ((long) a) * ((long) b);
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedMultiply", a, b);
        return i;
    }

    public static int checkedPow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            MathPreconditions.checkNoOverflow(k < 32, "checkedPow", b, k);
            return (k & 1) == 0 ? 1 << k : (-1) << k;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            MathPreconditions.checkNoOverflow(k < 31, "checkedPow", b, k);
            return 1 << k;
        }
        int iCheckedMultiply = 1;
        while (k != 0) {
            if (k == 1) {
                return checkedMultiply(iCheckedMultiply, b);
            }
            if ((k & 1) != 0) {
                iCheckedMultiply = checkedMultiply(iCheckedMultiply, b);
            }
            k >>= 1;
            if (k > 0) {
                MathPreconditions.checkNoOverflow((-46340 <= b) & (b <= 46340), "checkedPow", b, k);
                b *= b;
            }
        }
        return iCheckedMultiply;
    }

    public static int checkedSubtract(int a, int b) {
        long j = ((long) a) - ((long) b);
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedSubtract", a, b);
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int divide(int p, int q, RoundingMode mode) {
        mode.getClass();
        if (q == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i = p / q;
        int i2 = p - (q * i);
        if (i2 == 0) {
            return i;
        }
        int i3 = ((p ^ q) >> 31) | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i2 == 0);
                return i;
            case 2:
                return i;
            case 3:
                if (i3 >= 0) {
                    return i;
                }
                return i + i3;
            case 4:
                return i + i3;
            case 5:
                if (i3 <= 0) {
                    return i;
                }
                return i + i3;
            case 6:
            case 7:
            case 8:
                int iAbs = Math.abs(i2);
                int iAbs2 = iAbs - (Math.abs(q) - iAbs);
                if (iAbs2 == 0) {
                    if (mode != RoundingMode.HALF_UP) {
                        if (!((mode == RoundingMode.HALF_EVEN) & ((i & 1) != 0))) {
                            return i;
                        }
                    }
                } else if (iAbs2 <= 0) {
                    return i;
                }
                return i + i3;
            default:
                throw new AssertionError();
        }
    }

    public static int factorial(int n) {
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, n);
        int[] iArr = factorials;
        if (n < iArr.length) {
            return iArr[n];
        }
        return Integer.MAX_VALUE;
    }

    public static int floorPowerOfTwo(int x) {
        MathPreconditions.checkPositive("x", x);
        return Integer.highestOneBit(x);
    }

    public static int gcd(int a, int b) {
        MathPreconditions.checkNonNegative("a", a);
        MathPreconditions.checkNonNegative("b", b);
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(a);
        int iNumberOfTrailingZeros2 = a >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros3 = Integer.numberOfTrailingZeros(b);
        int i = b >> iNumberOfTrailingZeros3;
        while (iNumberOfTrailingZeros2 != i) {
            int i2 = iNumberOfTrailingZeros2 - i;
            int i3 = (i2 >> 31) & i2;
            int i4 = (i2 - i3) - i3;
            i += i3;
            iNumberOfTrailingZeros2 = i4 >> Integer.numberOfTrailingZeros(i4);
        }
        return iNumberOfTrailingZeros2 << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros3);
    }

    public static boolean isPowerOfTwo(int x) {
        return (x > 0) & ((x & (x + (-1))) == 0);
    }

    @GwtIncompatible
    public static boolean isPrime(int n) {
        return LongMath.isPrime(n);
    }

    @VisibleForTesting
    public static int lessThanBranchFree(int x, int y) {
        return (~(~(x - y))) >>> 31;
    }

    @GwtIncompatible
    public static int log10(int x, RoundingMode mode) {
        int iLessThanBranchFree;
        MathPreconditions.checkPositive("x", x);
        int iLog10Floor = log10Floor(x);
        int i = powersOf10[iLog10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(x == i);
                return iLog10Floor;
            case 2:
            case 3:
                return iLog10Floor;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(i, x);
                break;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree(halfPowersOf10[iLog10Floor], x);
                break;
            default:
                throw new AssertionError();
        }
        return iLessThanBranchFree + iLog10Floor;
    }

    public static int log10Floor(int x) {
        byte b = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(x)];
        return b - lessThanBranchFree(x, powersOf10[b]);
    }

    public static int log2(int x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(x));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(x - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(x);
                return lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> iNumberOfLeadingZeros, x) + (31 - iNumberOfLeadingZeros);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(x);
    }

    public static int mean(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    public static int mod(int x, int m) {
        if (m <= 0) {
            throw new ArithmeticException(ObjectListKt$$ExternalSyntheticOutline1.m("Modulus ", m, " must be > 0"));
        }
        int i = x % m;
        return i >= 0 ? i : i + m;
    }

    @GwtIncompatible
    public static int pow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            if (k < 32) {
                return (k & 1) == 0 ? 1 << k : -(1 << k);
            }
            return 0;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            if (k < 32) {
                return 1 << k;
            }
            return 0;
        }
        int i = 1;
        while (k != 0) {
            if (k == 1) {
                return b * i;
            }
            i *= (k & 1) == 0 ? 1 : b;
            b *= b;
            k >>= 1;
        }
        return i;
    }

    public static int saturatedAdd(int a, int b) {
        return Ints.saturatedCast(((long) a) + ((long) b));
    }

    public static int saturatedMultiply(int a, int b) {
        return Ints.saturatedCast(((long) a) * ((long) b));
    }

    public static int saturatedPow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            return k >= 32 ? (k & 1) + Integer.MAX_VALUE : (k & 1) == 0 ? 1 << k : (-1) << k;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            if (k >= 31) {
                return Integer.MAX_VALUE;
            }
            return 1 << k;
        }
        int i = ((b >>> 31) & k & 1) + Integer.MAX_VALUE;
        int iSaturatedMultiply = 1;
        while (k != 0) {
            if (k == 1) {
                return saturatedMultiply(iSaturatedMultiply, b);
            }
            if ((k & 1) != 0) {
                iSaturatedMultiply = saturatedMultiply(iSaturatedMultiply, b);
            }
            k >>= 1;
            if (k > 0) {
                if ((-46340 > b) || (b > 46340)) {
                    return i;
                }
                b *= b;
            }
        }
        return iSaturatedMultiply;
    }

    public static int saturatedSubtract(int a, int b) {
        return Ints.saturatedCast(((long) a) - ((long) b));
    }

    @GwtIncompatible
    public static int sqrt(int x, RoundingMode mode) {
        int iLessThanBranchFree;
        MathPreconditions.checkNonNegative("x", x);
        int iSqrt = (int) Math.sqrt(x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(iSqrt * iSqrt == x);
                return iSqrt;
            case 2:
            case 3:
                return iSqrt;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(iSqrt * iSqrt, x);
                break;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree((iSqrt * iSqrt) + iSqrt, x);
                break;
            default:
                throw new AssertionError();
        }
        return iLessThanBranchFree + iSqrt;
    }

    public static int sqrtFloor(int x) {
        return (int) Math.sqrt(x);
    }
}
