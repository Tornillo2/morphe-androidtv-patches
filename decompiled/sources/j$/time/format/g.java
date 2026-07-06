package j$.time.format;

import j$.util.Objects;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends i {
    public final boolean g;

    @Override // j$.time.format.i
    public final boolean a(q qVar) {
        return qVar.c && this.b == this.c && !this.g;
    }

    @Override // j$.time.format.i, j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        boolean z = qVar.c;
        a aVar = qVar.a;
        int i2 = (z || a(qVar)) ? this.b : 0;
        int i3 = (qVar.c || a(qVar)) ? this.c : 9;
        int length = charSequence.length();
        if (i != length) {
            if (this.g) {
                char cCharAt = charSequence.charAt(i);
                aVar.c.getClass();
                if (cCharAt == '.') {
                    i++;
                } else if (i2 > 0) {
                    return ~i;
                }
            }
            int i4 = i;
            int i5 = i2 + i4;
            if (i5 > length) {
                return ~i4;
            }
            int iMin = Math.min(i3 + i4, length);
            int i6 = i4;
            int i7 = 0;
            while (true) {
                if (i6 >= iMin) {
                    break;
                }
                int i8 = i6 + 1;
                char cCharAt2 = charSequence.charAt(i6);
                aVar.c.getClass();
                int i9 = cCharAt2 - '0';
                if (i9 < 0 || i9 > 9) {
                    i9 = -1;
                }
                if (i9 >= 0) {
                    i7 = (i7 * 10) + i9;
                    i6 = i8;
                } else if (i8 < i5) {
                    return ~i4;
                }
            }
            BigDecimal bigDecimalMovePointLeft = new BigDecimal(i7).movePointLeft(i6 - i4);
            j$.time.temporal.v vVarO = this.a.o();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(vVarO.a);
            return qVar.f(this.a, bigDecimalMovePointLeft.multiply(BigDecimal.valueOf(vVarO.d).subtract(bigDecimalValueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(bigDecimalValueOf).longValueExact(), i4, i6);
        }
        if (i2 > 0) {
            return ~i;
        }
        return i;
    }

    public g(j$.time.temporal.r rVar) {
        this(rVar, 0, 9, true, 0);
        Objects.requireNonNull(rVar, "field");
        j$.time.temporal.v vVarO = rVar.o();
        if (vVarO.a != vVarO.b || vVarO.c != vVarO.d) {
            throw new IllegalArgumentException(j$.time.c.a("Field must have a fixed set of values: ", rVar));
        }
    }

    public g(j$.time.temporal.r rVar, int i, int i2, boolean z, int i3) {
        super(rVar, i, i2, z.NOT_NEGATIVE, i3);
        this.g = z;
    }

    @Override // j$.time.format.i
    public final i b() {
        if (this.e == -1) {
            return this;
        }
        return new g(this.a, this.b, this.c, this.g, -1);
    }

    @Override // j$.time.format.i
    public final i c(int i) {
        return new g(this.a, this.b, this.c, this.g, this.e + i);
    }

    @Override // j$.time.format.i, j$.time.format.f
    public final boolean j(t tVar, StringBuilder sb) {
        j$.time.temporal.r rVar = this.a;
        Long lA = tVar.a(rVar);
        if (lA == null) {
            return false;
        }
        w wVar = tVar.b.c;
        long jLongValue = lA.longValue();
        j$.time.temporal.v vVarO = rVar.o();
        vVarO.b(jLongValue, rVar);
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(vVarO.a);
        BigDecimal bigDecimalAdd = BigDecimal.valueOf(vVarO.d).subtract(bigDecimalValueOf).add(BigDecimal.ONE);
        BigDecimal bigDecimalSubtract = BigDecimal.valueOf(jLongValue).subtract(bigDecimalValueOf);
        RoundingMode roundingMode = RoundingMode.FLOOR;
        BigDecimal bigDecimalDivide = bigDecimalSubtract.divide(bigDecimalAdd, 9, roundingMode);
        BigDecimal bigDecimal = BigDecimal.ZERO;
        if (bigDecimalDivide.compareTo(bigDecimal) != 0) {
            bigDecimal = bigDecimalDivide.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : bigDecimalDivide.stripTrailingZeros();
        }
        int iScale = bigDecimal.scale();
        boolean z = this.g;
        int i = this.b;
        if (iScale != 0) {
            String strSubstring = bigDecimal.setScale(Math.min(Math.max(bigDecimal.scale(), i), this.c), roundingMode).toPlainString().substring(2);
            wVar.getClass();
            if (z) {
                sb.append('.');
            }
            sb.append(strSubstring);
            return true;
        }
        if (i > 0) {
            if (z) {
                wVar.getClass();
                sb.append('.');
            }
            for (int i2 = 0; i2 < i; i2++) {
                wVar.getClass();
                sb.append('0');
            }
        }
        return true;
    }

    @Override // j$.time.format.i
    public final String toString() {
        return "Fraction(" + this.a + "," + this.b + "," + this.c + (this.g ? ",DecimalPoint" : "") + ")";
    }
}
