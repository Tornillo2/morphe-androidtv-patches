package j$.time;

import com.google.common.base.Ascii;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class y implements j$.time.temporal.m, j$.time.temporal.o, Comparable, Serializable {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 4183400860270640070L;
    public final int a;
    public final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        y yVar = (y) obj;
        int i = this.a - yVar.a;
        return i == 0 ? this.b - yVar.b : i;
    }

    static {
        j$.time.format.p pVar = new j$.time.format.p();
        pVar.h(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.z.EXCEEDS_PAD);
        pVar.c('-');
        pVar.g(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        pVar.l(Locale.getDefault(), j$.time.format.y.SMART, null);
    }

    public y(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final y R(int i, int i2) {
        return (this.a == i && this.b == i2) ? this : new y(i, i2);
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.YEAR || rVar == j$.time.temporal.a.MONTH_OF_YEAR || rVar == j$.time.temporal.a.PROLEPTIC_MONTH || rVar == j$.time.temporal.a.YEAR_OF_ERA || rVar == j$.time.temporal.a.ERA : rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.v.f(1L, this.a <= 0 ? 1000000000L : 999999999L);
        }
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        return l(rVar).a(C(rVar), rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        int i;
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        int i2 = x.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i2 == 1) {
            i = this.b;
        } else {
            if (i2 == 2) {
                return N();
            }
            if (i2 == 3) {
                int i3 = this.a;
                if (i3 < 1) {
                    i3 = 1 - i3;
                }
                return i3;
            }
            if (i2 != 4) {
                if (i2 == 5) {
                    return this.a < 1 ? 0 : 1;
                }
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
            }
            i = this.a;
        }
        return i;
    }

    public final long N() {
        return ((((long) this.a) * 12) + ((long) this.b)) - 1;
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public final y c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (y) rVar.B(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = x.a[aVar.ordinal()];
        if (i == 1) {
            int i2 = (int) j;
            j$.time.temporal.a.MONTH_OF_YEAR.C(i2);
            return R(this.a, i2);
        }
        if (i == 2) {
            return P(j - N());
        }
        if (i == 3) {
            if (this.a < 1) {
                j = 1 - j;
            }
            int i3 = (int) j;
            j$.time.temporal.a.YEAR.C(i3);
            return R(i3, this.b);
        }
        if (i == 4) {
            int i4 = (int) j;
            j$.time.temporal.a.YEAR.C(i4);
            return R(i4, this.b);
        }
        if (i != 5) {
            throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
        if (C(j$.time.temporal.a.ERA) == j) {
            return this;
        }
        int i5 = 1 - this.a;
        j$.time.temporal.a.YEAR.C(i5);
        return R(i5, this.b);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public final y d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return (y) tVar.j(this, j);
        }
        switch (x.b[((j$.time.temporal.b) tVar).ordinal()]) {
            case 1:
                return P(j);
            case 2:
                return Q(j);
            case 3:
                return Q(j$.com.android.tools.r8.a.L(j, 10));
            case 4:
                return Q(j$.com.android.tools.r8.a.L(j, 100));
            case 5:
                return Q(j$.com.android.tools.r8.a.L(j, 1000));
            case 6:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.F(C(aVar), j), aVar);
            default:
                throw new j$.time.temporal.u("Unsupported unit: " + tVar);
        }
    }

    public final y Q(long j) {
        if (j == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return R(aVar.b.a(((long) this.a) + j, aVar), this.b);
    }

    public final y P(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (((long) this.a) * 12) + ((long) (this.b - 1)) + j;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        long j3 = 12;
        return R(aVar.b.a(j$.com.android.tools.r8.a.K(j2, j3), aVar), ((int) j$.com.android.tools.r8.a.J(j2, j3)) + 1);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.b) {
            return j$.time.chrono.t.c;
        }
        if (eVar == j$.time.temporal.s.c) {
            return j$.time.temporal.b.MONTHS;
        }
        return j$.time.temporal.s.c(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        if (!j$.com.android.tools.r8.a.E(mVar).equals(j$.time.chrono.t.c)) {
            throw new b("Adjustment only supported on ISO date-time");
        }
        return mVar.c(N(), j$.time.temporal.a.PROLEPTIC_MONTH);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof y) {
            y yVar = (y) obj;
            if (this.a == yVar.a && this.b == yVar.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a ^ (this.b << 27);
    }

    public final String toString() {
        int iAbs = Math.abs(this.a);
        StringBuilder sb = new StringBuilder(9);
        if (iAbs < 1000) {
            int i = this.a;
            if (i < 0) {
                sb.append(i - 10000);
                sb.deleteCharAt(1);
            } else {
                sb.append(i + 10000);
                sb.deleteCharAt(0);
            }
        } else {
            sb.append(this.a);
        }
        sb.append(this.b < 10 ? "-0" : "-");
        sb.append(this.b);
        return sb.toString();
    }

    private Object writeReplace() {
        return new u(Ascii.FF, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return (y) j$.com.android.tools.r8.a.a(hVar, this);
    }
}
