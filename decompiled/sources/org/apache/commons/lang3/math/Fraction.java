package org.apache.commons.lang3.math;

import com.amazon.minerva.identifiers.schemaid.SchemaId;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Fraction extends Number implements Comparable<Fraction> {
    public static final long serialVersionUID = 65382027393090L;
    public final int denominator;
    public final int numerator;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public transient int hashCode = 0;
    public transient String toString = null;
    public transient String toProperString = null;

    public Fraction(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    public static int addAndCheck(int i, int i2) {
        long j = ((long) i) + ((long) i2);
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    public static Fraction getFraction(int i, int i2) {
        if (i2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            i = -i;
            i2 = -i2;
        }
        return new Fraction(i, i2);
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i == 0) {
            return ZERO;
        }
        if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
            i /= 2;
            i2 /= 2;
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            i = -i;
            i2 = -i2;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(i, i2);
        return new Fraction(i / iGreatestCommonDivisor, i2 / iGreatestCommonDivisor);
    }

    public static int greatestCommonDivisor(int i, int i2) {
        int i3;
        if (i == 0 || i2 == 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: gcd is 2^31");
            }
            return Math.abs(i2) + Math.abs(i);
        }
        if (Math.abs(i) == 1 || Math.abs(i2) == 1) {
            return 1;
        }
        if (i > 0) {
            i = -i;
        }
        if (i2 > 0) {
            i2 = -i2;
        }
        int i4 = 0;
        while (true) {
            i3 = i & 1;
            if (i3 != 0 || (i2 & 1) != 0 || i4 >= 31) {
                break;
            }
            i /= 2;
            i2 /= 2;
            i4++;
        }
        if (i4 == 31) {
            throw new ArithmeticException("overflow: gcd is 2^31");
        }
        int i5 = i3 == 1 ? i2 : -(i / 2);
        while (true) {
            if ((i5 & 1) == 0) {
                i5 /= 2;
            } else {
                if (i5 > 0) {
                    i = -i5;
                } else {
                    i2 = i5;
                }
                i5 = (i2 - i) / 2;
                if (i5 == 0) {
                    return (-i) * (1 << i4);
                }
            }
        }
    }

    public static int mulAndCheck(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int) j;
    }

    public static int mulPosAndCheck(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    public static int subAndCheck(int i, int i2) {
        long j = ((long) i) - ((long) i2);
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    public Fraction abs() {
        return this.numerator >= 0 ? this : negate();
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public final Fraction addSub(Fraction fraction, boolean z) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        if (this.numerator == 0) {
            return z ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(this.denominator, fraction.denominator);
        if (iGreatestCommonDivisor == 1) {
            int iMulAndCheck = mulAndCheck(this.numerator, fraction.denominator);
            int iMulAndCheck2 = mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(z ? addAndCheck(iMulAndCheck, iMulAndCheck2) : subAndCheck(iMulAndCheck, iMulAndCheck2), mulPosAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger bigIntegerMultiply = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / iGreatestCommonDivisor));
        BigInteger bigIntegerMultiply2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / iGreatestCommonDivisor));
        BigInteger bigIntegerAdd = z ? bigIntegerMultiply.add(bigIntegerMultiply2) : bigIntegerMultiply.subtract(bigIntegerMultiply2);
        int iIntValue = bigIntegerAdd.mod(BigInteger.valueOf(iGreatestCommonDivisor)).intValue();
        int iGreatestCommonDivisor2 = iIntValue == 0 ? iGreatestCommonDivisor : greatestCommonDivisor(iIntValue, iGreatestCommonDivisor);
        BigInteger bigIntegerDivide = bigIntegerAdd.divide(BigInteger.valueOf(iGreatestCommonDivisor2));
        if (bigIntegerDivide.bitLength() <= 31) {
            return new Fraction(bigIntegerDivide.intValue(), mulPosAndCheck(this.denominator / iGreatestCommonDivisor, fraction.denominator / iGreatestCommonDivisor2));
        }
        throw new ArithmeticException("overflow: numerator too large after multiply");
    }

    public Fraction divideBy(Fraction fraction) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        if (fraction.numerator != 0) {
            return multiplyBy(fraction.invert());
        }
        throw new ArithmeticException("The fraction to divide by must not be zero");
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return ((double) this.numerator) / ((double) this.denominator);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        return this.numerator == fraction.numerator && this.denominator == fraction.denominator;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.numerator / this.denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((this.numerator + 629) * 37) + this.denominator;
        }
        return this.hashCode;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator / this.denominator;
    }

    public Fraction invert() {
        int i = this.numerator;
        if (i == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        }
        if (i != Integer.MIN_VALUE) {
            return i < 0 ? new Fraction(-this.denominator, -i) : new Fraction(this.denominator, i);
        }
        throw new ArithmeticException("overflow: can't negate numerator");
    }

    @Override // java.lang.Number
    public long longValue() {
        return ((long) this.numerator) / ((long) this.denominator);
    }

    public Fraction multiplyBy(Fraction fraction) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        int i = this.numerator;
        if (i == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(i, fraction.denominator);
        int iGreatestCommonDivisor2 = greatestCommonDivisor(fraction.numerator, this.denominator);
        return getReducedFraction(mulAndCheck(this.numerator / iGreatestCommonDivisor, fraction.numerator / iGreatestCommonDivisor2), mulPosAndCheck(this.denominator / iGreatestCommonDivisor2, fraction.denominator / iGreatestCommonDivisor));
    }

    public Fraction negate() {
        int i = this.numerator;
        if (i != Integer.MIN_VALUE) {
            return new Fraction(-i, this.denominator);
        }
        throw new ArithmeticException("overflow: too large to negate");
    }

    public Fraction pow(int i) {
        if (i == 1) {
            return this;
        }
        if (i == 0) {
            return ONE;
        }
        if (i < 0) {
            return i == Integer.MIN_VALUE ? invert().pow(2).pow(-(i / 2)) : invert().pow(-i);
        }
        Fraction fractionMultiplyBy = multiplyBy(this);
        return i % 2 == 0 ? fractionMultiplyBy.pow(i / 2) : fractionMultiplyBy.pow(i / 2).multiplyBy(this);
    }

    public Fraction reduce() {
        int i = this.numerator;
        if (i == 0) {
            Fraction fraction = ZERO;
            if (!equals(fraction)) {
                return fraction;
            }
        } else {
            int iGreatestCommonDivisor = greatestCommonDivisor(Math.abs(i), this.denominator);
            if (iGreatestCommonDivisor != 1) {
                return getFraction(this.numerator / iGreatestCommonDivisor, this.denominator / iGreatestCommonDivisor);
            }
        }
        return this;
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public String toProperString() {
        if (this.toProperString == null) {
            int i = this.numerator;
            if (i == 0) {
                this.toProperString = "0";
            } else {
                int i2 = this.denominator;
                if (i == i2) {
                    this.toProperString = "1";
                } else if (i == i2 * (-1)) {
                    this.toProperString = "-1";
                } else {
                    if (i > 0) {
                        i = -i;
                    }
                    if (i < (-i2)) {
                        int properNumerator = getProperNumerator();
                        if (properNumerator == 0) {
                            this.toProperString = Integer.toString(getProperWhole());
                        } else {
                            this.toProperString = getProperWhole() + StringUtils.SPACE + properNumerator + SchemaId.METRIC_SCHEMA_ID_DELIMITER + this.denominator;
                        }
                    } else {
                        this.toProperString = this.numerator + SchemaId.METRIC_SCHEMA_ID_DELIMITER + this.denominator;
                    }
                }
            }
        }
        return this.toProperString;
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = this.numerator + SchemaId.METRIC_SCHEMA_ID_DELIMITER + this.denominator;
        }
        return this.toString;
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction fraction) {
        if (this == fraction) {
            return 0;
        }
        int i = this.numerator;
        int i2 = fraction.numerator;
        if (i == i2 && this.denominator == fraction.denominator) {
            return 0;
        }
        return Long.compare(((long) i) * ((long) fraction.denominator), ((long) i2) * ((long) this.denominator));
    }

    public static Fraction getFraction(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i3 < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        }
        if (i2 < 0) {
            throw new ArithmeticException("The numerator must not be negative");
        }
        long j = i < 0 ? (((long) i) * ((long) i3)) - ((long) i2) : (((long) i) * ((long) i3)) + ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return new Fraction((int) j, i3);
        }
        throw new ArithmeticException("Numerator too large to represent as an Integer.");
    }

    public static Fraction getFraction(double d) {
        int i;
        int i2;
        int i3;
        int i4 = d < 0.0d ? -1 : 1;
        double dAbs = Math.abs(d);
        if (dAbs > 2.147483647E9d || Double.isNaN(dAbs)) {
            throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
        }
        int i5 = (int) dAbs;
        double d2 = dAbs - ((double) i5);
        int i6 = (int) d2;
        double d3 = d2 - ((double) i6);
        double d4 = Double.MAX_VALUE;
        int i7 = 1;
        int i8 = 1;
        int i9 = 1;
        double d5 = 1.0d;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = (int) (d5 / d3);
            double d6 = d5 - (((double) i12) * d3);
            int i13 = (i6 * i7) + i10;
            int i14 = (i6 * i11) + i8;
            i = i4;
            i2 = i5;
            double dAbs2 = Math.abs(d2 - (((double) i13) / ((double) i14)));
            i3 = i9 + 1;
            if (d4 <= dAbs2 || i14 > 10000 || i14 <= 0 || i3 >= 25) {
                break;
            }
            i4 = i;
            i5 = i2;
            i9 = i3;
            d4 = dAbs2;
            i10 = i7;
            i8 = i11;
            i7 = i13;
            i11 = i14;
            d5 = d3;
            i6 = i12;
            d3 = d6;
        }
        if (i3 != 25) {
            return getReducedFraction(((i2 * i11) + i7) * i, i11);
        }
        throw new ArithmeticException("Unable to convert double to fraction");
    }

    public static Fraction getFraction(String str) {
        Validate.isTrue(str != null, "The string must not be null", new Object[0]);
        if (str.indexOf(46) >= 0) {
            return getFraction(Double.parseDouble(str));
        }
        int iIndexOf = str.indexOf(32);
        if (iIndexOf > 0) {
            int i = Integer.parseInt(str.substring(0, iIndexOf));
            String strSubstring = str.substring(iIndexOf + 1);
            int iIndexOf2 = strSubstring.indexOf(47);
            if (iIndexOf2 >= 0) {
                return getFraction(i, Integer.parseInt(strSubstring.substring(0, iIndexOf2)), Integer.parseInt(strSubstring.substring(iIndexOf2 + 1)));
            }
            throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 < 0) {
            return getFraction(Integer.parseInt(str), 1);
        }
        return getFraction(Integer.parseInt(str.substring(0, iIndexOf3)), Integer.parseInt(str.substring(iIndexOf3 + 1)));
    }
}
