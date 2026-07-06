package com.amazon.ion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Decimal extends BigDecimal {
    public static final long serialVersionUID = 1;
    public static final Decimal ZERO = new Decimal(0);
    public static final Decimal NEGATIVE_ZERO = new NegativeZero(0);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NegativeZero extends Decimal {
        public static final long serialVersionUID = 1;

        @Override // java.math.BigDecimal
        public BigDecimal abs() {
            return new BigDecimal(unscaledValue(), scale());
        }

        @Override // java.math.BigDecimal, java.lang.Number
        public double doubleValue() {
            double dDoubleValue = super.doubleValue();
            return Double.compare(0.0d, dDoubleValue) <= 0 ? dDoubleValue * (-1.0d) : dDoubleValue;
        }

        @Override // java.math.BigDecimal, java.lang.Number
        public float floatValue() {
            float fFloatValue = super.floatValue();
            return Float.compare(0.0f, fFloatValue) <= 0 ? fFloatValue * (-1.0f) : fFloatValue;
        }

        @Override // java.math.BigDecimal
        public String toEngineeringString() {
            return "-" + super.toEngineeringString();
        }

        @Override // java.math.BigDecimal
        public String toPlainString() {
            return "-" + super.toPlainString();
        }

        @Override // java.math.BigDecimal
        public String toString() {
            return "-" + super.toString();
        }

        @Override // java.math.BigDecimal
        public BigDecimal abs(MathContext mathContext) {
            return new BigDecimal(unscaledValue(), scale(), mathContext);
        }

        public NegativeZero(int i) {
            super(BigInteger.ZERO, i);
        }

        public NegativeZero(int i, MathContext mathContext) {
            super(BigInteger.ZERO, i, mathContext);
        }
    }

    public Decimal(BigInteger bigInteger, int i) {
        super(bigInteger, i);
    }

    public static BigDecimal bigDecimalValue(BigDecimal bigDecimal) {
        return (bigDecimal == null || bigDecimal.getClass() == BigDecimal.class) ? bigDecimal : new BigDecimal(bigDecimal.unscaledValue(), bigDecimal.scale());
    }

    public static boolean equals(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return isNegativeZero(bigDecimal) == isNegativeZero(bigDecimal2) && bigDecimal.equals(bigDecimal2);
    }

    public static boolean isNegativeZero(BigDecimal bigDecimal) {
        return bigDecimal.getClass() == NegativeZero.class;
    }

    public static Decimal negativeZero(int i) {
        return new NegativeZero(i);
    }

    public static Decimal valueOf(BigInteger bigInteger, int i) {
        return new Decimal(bigInteger, i);
    }

    public Decimal(BigInteger bigInteger, int i, AnonymousClass1 anonymousClass1) {
        super(bigInteger, i);
    }

    public final boolean isNegativeZero() {
        return getClass() == NegativeZero.class;
    }

    public Decimal(BigInteger bigInteger, int i, MathContext mathContext) {
        super(bigInteger, i, mathContext);
    }

    public static Decimal negativeZero(int i, MathContext mathContext) {
        return new NegativeZero(i, mathContext);
    }

    public static Decimal valueOf(BigInteger bigInteger, int i, MathContext mathContext) {
        return new Decimal(bigInteger, i, mathContext);
    }

    public final BigDecimal bigDecimalValue() {
        return new BigDecimal(unscaledValue(), scale());
    }

    public Decimal(BigInteger bigInteger, int i, MathContext mathContext, AnonymousClass1 anonymousClass1) {
        super(bigInteger, i, mathContext);
    }

    public Decimal(BigInteger bigInteger) {
        super(bigInteger);
    }

    public static Decimal valueOf(BigInteger bigInteger) {
        return new Decimal(bigInteger);
    }

    public Decimal(BigInteger bigInteger, MathContext mathContext) {
        super(bigInteger, mathContext);
    }

    public Decimal(int i) {
        super(i);
    }

    public static Decimal valueOf(BigInteger bigInteger, MathContext mathContext) {
        return new Decimal(bigInteger, mathContext);
    }

    public Decimal(int i, MathContext mathContext) {
        super(i, mathContext);
    }

    public Decimal(long j) {
        super(j);
    }

    public static Decimal valueOf(int i) {
        return new Decimal(i);
    }

    public Decimal(long j, MathContext mathContext) {
        super(j, mathContext);
    }

    public Decimal(double d) {
        super(d);
    }

    public static Decimal valueOf(int i, MathContext mathContext) {
        return new Decimal(i, mathContext);
    }

    public Decimal(double d, MathContext mathContext) {
        super(d, mathContext);
    }

    public Decimal(char[] cArr, int i, int i2) {
        super(cArr, i, i2);
    }

    public static Decimal valueOf(long j) {
        return new Decimal(j);
    }

    public Decimal(char[] cArr, int i, int i2, MathContext mathContext) {
        super(cArr, i, i2, mathContext);
    }

    public Decimal(char[] cArr) {
        super(cArr);
    }

    public static Decimal valueOf(long j, MathContext mathContext) {
        return new Decimal(j, mathContext);
    }

    public Decimal(char[] cArr, MathContext mathContext) {
        super(cArr, mathContext);
    }

    public Decimal(String str) {
        super(str);
    }

    public static Decimal valueOf(double d) {
        if (Double.compare(d, -0.0d) == 0) {
            return new NegativeZero(1);
        }
        return new Decimal(Double.toString(d));
    }

    public Decimal(String str, MathContext mathContext) {
        super(str, mathContext);
    }

    public static Decimal valueOf(double d, MathContext mathContext) {
        if (Double.compare(d, -0.0d) == 0) {
            return new NegativeZero(1, mathContext);
        }
        return new Decimal(Double.toString(d), mathContext);
    }

    public static Decimal valueOf(BigDecimal bigDecimal) {
        if (bigDecimal != null && !(bigDecimal instanceof Decimal)) {
            return new Decimal(bigDecimal.unscaledValue(), bigDecimal.scale());
        }
        return (Decimal) bigDecimal;
    }

    public static Decimal valueOf(BigDecimal bigDecimal, MathContext mathContext) {
        return new Decimal(bigDecimal.unscaledValue(), bigDecimal.scale(), mathContext);
    }

    public static Decimal valueOf(String str) {
        boolean zStartsWith = str.startsWith("-");
        Decimal decimal = new Decimal(str);
        return (zStartsWith && decimal.signum() == 0) ? new NegativeZero(decimal.scale()) : decimal;
    }

    public static Decimal valueOf(String str, MathContext mathContext) {
        boolean zStartsWith = str.startsWith("-");
        Decimal decimal = new Decimal(str, mathContext);
        return (zStartsWith && decimal.signum() == 0) ? new NegativeZero(decimal.scale(), mathContext) : decimal;
    }
}
