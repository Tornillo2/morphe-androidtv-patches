package j$.time.format;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
public class i implements f {
    public static final long[] f = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};
    public final j$.time.temporal.r a;
    public final int b;
    public final int c;
    public final z d;
    public final int e;

    public i(j$.time.temporal.r rVar, int i, int i2, z zVar) {
        this.a = rVar;
        this.b = i;
        this.c = i2;
        this.d = zVar;
        this.e = 0;
    }

    public i(j$.time.temporal.r rVar, int i, int i2, z zVar, int i3) {
        this.a = rVar;
        this.b = i;
        this.c = i2;
        this.d = zVar;
        this.e = i3;
    }

    public i b() {
        if (this.e == -1) {
            return this;
        }
        return new i(this.a, this.b, this.c, this.d, -1);
    }

    public i c(int i) {
        return new i(this.a, this.b, this.c, this.d, this.e + i);
    }

    @Override // j$.time.format.f
    public boolean j(t tVar, StringBuilder sb) {
        j$.time.temporal.r rVar = this.a;
        Long lA = tVar.a(rVar);
        if (lA == null) {
            return false;
        }
        long jLongValue = lA.longValue();
        w wVar = tVar.b.c;
        String string = jLongValue == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(jLongValue));
        int length = string.length();
        int i = this.c;
        if (length > i) {
            throw new j$.time.b("Field " + rVar + " cannot be printed as the value " + jLongValue + " exceeds the maximum print width of " + i);
        }
        wVar.getClass();
        int i2 = this.b;
        z zVar = this.d;
        if (jLongValue >= 0) {
            int i3 = c.a[zVar.ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    sb.append('+');
                }
            } else if (i2 < 19 && jLongValue >= f[i2]) {
                sb.append('+');
            }
        } else {
            int i4 = c.a[zVar.ordinal()];
            if (i4 == 1 || i4 == 2 || i4 == 3) {
                sb.append('-');
            } else if (i4 == 4) {
                throw new j$.time.b("Field " + rVar + " cannot be printed as the value " + jLongValue + " cannot be negative according to the SignStyle");
            }
        }
        for (int i5 = 0; i5 < i2 - string.length(); i5++) {
            sb.append('0');
        }
        sb.append(string);
        return true;
    }

    public boolean a(q qVar) {
        int i = this.e;
        if (i != -1) {
            return i > 0 && this.b == this.c && this.d == z.NOT_NEGATIVE;
        }
        return true;
    }

    @Override // j$.time.format.f
    public int k(q qVar, CharSequence charSequence, int i) {
        boolean z;
        boolean z2;
        BigInteger bigIntegerAdd;
        boolean z3;
        boolean z4;
        int i2;
        long j;
        int i3;
        long j2;
        int i4;
        a aVar;
        boolean z5;
        int i5 = i;
        int length = charSequence.length();
        if (i5 == length) {
            return ~i5;
        }
        char cCharAt = charSequence.charAt(i);
        a aVar2 = qVar.a;
        aVar2.c.getClass();
        int i6 = this.c;
        z zVar = this.d;
        int i7 = this.b;
        int i8 = 0;
        if (cCharAt == '+') {
            boolean z6 = qVar.c;
            boolean z7 = i7 == i6;
            int iOrdinal = zVar.ordinal();
            if (iOrdinal == 0 ? z6 : !(iOrdinal == 1 || iOrdinal == 4 || (!z6 && !z7))) {
                return ~i5;
            }
            i5++;
            z = false;
            z2 = true;
        } else {
            aVar2.c.getClass();
            if (cCharAt == '-') {
                boolean z8 = qVar.c;
                boolean z9 = i7 == i6;
                int iOrdinal2 = zVar.ordinal();
                if (iOrdinal2 != 0 && iOrdinal2 != 1 && iOrdinal2 != 4 && (z8 || z9)) {
                    return ~i5;
                }
                i5++;
                z = true;
            } else {
                if (zVar == z.ALWAYS && qVar.c) {
                    return ~i5;
                }
                z = false;
            }
            z2 = false;
        }
        int i9 = (qVar.c || a(qVar)) ? i7 : 1;
        int i10 = i5 + i9;
        if (i10 > length) {
            return ~i5;
        }
        if (!qVar.c && !a(qVar)) {
            i6 = 9;
        }
        int i11 = this.e;
        int iMax = Math.max(i11, 0) + i6;
        while (true) {
            bigIntegerAdd = null;
            if (i8 >= 2) {
                z3 = z;
                z4 = z2;
                i2 = i5;
                j = 0;
                break;
            }
            int iMin = Math.min(i5 + iMax, length);
            i3 = i5;
            j2 = 0;
            while (true) {
                if (i3 >= iMin) {
                    i4 = length;
                    z3 = z;
                    break;
                }
                int i12 = i3 + 1;
                char cCharAt2 = charSequence.charAt(i3);
                i4 = length;
                aVar2.c.getClass();
                int i13 = cCharAt2 - '0';
                z3 = z;
                if (i13 < 0 || i13 > 9) {
                    i13 = -1;
                }
                if (i13 >= 0) {
                    if (i12 - i5 > 18) {
                        if (bigIntegerAdd == null) {
                            bigIntegerAdd = BigInteger.valueOf(j2);
                        }
                        aVar = aVar2;
                        z5 = z2;
                        bigIntegerAdd = bigIntegerAdd.multiply(BigInteger.TEN).add(BigInteger.valueOf(i13));
                    } else {
                        aVar = aVar2;
                        z5 = z2;
                        j2 = (j2 * 10) + ((long) i13);
                    }
                    i3 = i12;
                    z = z3;
                    length = i4;
                    aVar2 = aVar;
                    z2 = z5;
                } else if (i3 < i10) {
                    return ~i5;
                }
            }
            a aVar3 = aVar2;
            z4 = z2;
            if (i11 <= 0 || i8 != 0) {
                break;
            }
            i8++;
            iMax = Math.max(i9, (i3 - i5) - i11);
            z = z3;
            length = i4;
            aVar2 = aVar3;
            z2 = z4;
        }
        i2 = i3;
        j = j2;
        BigInteger bigIntegerDivide = bigIntegerAdd;
        if (z3) {
            if (bigIntegerDivide != null) {
                if (bigIntegerDivide.equals(BigInteger.ZERO) && qVar.c) {
                    return ~(i5 - 1);
                }
                bigIntegerDivide = bigIntegerDivide.negate();
            } else {
                if (j == 0 && qVar.c) {
                    return ~(i5 - 1);
                }
                j = -j;
            }
        } else if (zVar == z.EXCEEDS_PAD && qVar.c) {
            int i14 = i2 - i5;
            if (z4) {
                if (i14 <= i7) {
                    return ~(i5 - 1);
                }
            } else if (i14 > i7) {
                return ~i5;
            }
        }
        if (bigIntegerDivide == null) {
            return qVar.f(this.a, j, i5, i2);
        }
        if (bigIntegerDivide.bitLength() > 63) {
            bigIntegerDivide = bigIntegerDivide.divide(BigInteger.TEN);
            i2--;
        }
        return qVar.f(this.a, bigIntegerDivide.longValue(), i5, i2);
    }

    public String toString() {
        int i = this.c;
        j$.time.temporal.r rVar = this.a;
        z zVar = this.d;
        int i2 = this.b;
        if (i2 == 1 && i == 19 && zVar == z.NORMAL) {
            return "Value(" + rVar + ")";
        }
        if (i2 == i && zVar == z.NOT_NEGATIVE) {
            return "Value(" + rVar + "," + i2 + ")";
        }
        return "Value(" + rVar + "," + i2 + "," + i + "," + zVar + ")";
    }
}
