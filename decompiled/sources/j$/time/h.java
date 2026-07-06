package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements j$.time.temporal.m, j$.time.temporal.o, j$.time.chrono.b, Serializable {
    public static final h d = X(-999999999, 1, 1);
    public static final h e = X(999999999, 12, 31);
    private static final long serialVersionUID = 2942565459149668126L;
    public final int a;
    public final short b;
    public final short c;

    static {
        X(1970, 1, 1);
    }

    public static h W(a aVar) {
        Objects.requireNonNull(aVar, "clock");
        long jCurrentTimeMillis = System.currentTimeMillis();
        Instant instant = Instant.c;
        long j = 1000;
        Instant instantN = Instant.N(j$.com.android.tools.r8.a.K(jCurrentTimeMillis, j), ((int) j$.com.android.tools.r8.a.J(jCurrentTimeMillis, j)) * 1000000);
        ZoneId zoneId = aVar.a;
        Objects.requireNonNull(instantN, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return Y(j$.com.android.tools.r8.a.K(instantN.getEpochSecond() + ((long) zoneId.N().d(instantN).b), 86400));
    }

    public static h X(int i, int i2, int i3) {
        j$.time.temporal.a.YEAR.C(i);
        j$.time.temporal.a.MONTH_OF_YEAR.C(i2);
        j$.time.temporal.a.DAY_OF_MONTH.C(i3);
        return O(i, i2, i3);
    }

    public static h Z(int i, int i2) {
        long j = i;
        j$.time.temporal.a.YEAR.C(j);
        j$.time.temporal.a.DAY_OF_YEAR.C(i2);
        j$.time.chrono.t.c.getClass();
        boolean zN = j$.time.chrono.t.N(j);
        if (i2 == 366 && !zN) {
            throw new b("Invalid date 'DayOfYear 366' as '" + i + "' is not a leap year");
        }
        n nVarQ = n.Q(((i2 - 1) / 31) + 1);
        if (i2 > (nVarQ.O(zN) + nVarQ.N(zN)) - 1) {
            nVarQ = n.a[((((int) 1) + 12) + nVarQ.ordinal()) % 12];
        }
        return new h(i, nVarQ.getValue(), (i2 - nVarQ.N(zN)) + 1);
    }

    public static h Y(long j) {
        long j2;
        j$.time.temporal.a.EPOCH_DAY.C(j);
        long j3 = 719468 + j;
        if (j3 < 0) {
            long j4 = ((j + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((j5 / 400) + (((j5 / 4) + (j5 * 365)) - (j5 / 100)));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((j5 / 400) + (((j5 / 4) + (365 * j5)) - (j5 / 100)));
        }
        int i = (int) j6;
        int i2 = ((i * 5) + 2) / 153;
        int i3 = ((i2 + 2) % 12) + 1;
        int i4 = (i - (((i2 * 306) + 5) / 10)) + 1;
        long j7 = j5 + j2 + ((long) (i2 / 10));
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return new h(aVar.b.a(j7, aVar), i3, i4);
    }

    public static h P(j$.time.temporal.n nVar) {
        Objects.requireNonNull(nVar, "temporal");
        h hVar = (h) nVar.B(j$.time.temporal.s.f);
        if (hVar != null) {
            return hVar;
        }
        throw new b("Unable to obtain LocalDate from TemporalAccessor: " + nVar + " of type " + nVar.getClass().getName());
    }

    public static h O(int i, int i2, int i3) {
        int i4 = 28;
        if (i3 > 28) {
            if (i2 != 2) {
                i4 = (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) ? 30 : 31;
            } else {
                j$.time.chrono.t.c.getClass();
                if (j$.time.chrono.t.N(i)) {
                    i4 = 29;
                }
            }
            if (i3 > i4) {
                if (i3 == 29) {
                    throw new b("Invalid date 'February 29' as '" + i + "' is not a leap year");
                }
                throw new b("Invalid date '" + n.Q(i2).name() + StringUtils.SPACE + i3 + "'");
            }
        }
        return new h(i, i2, i3);
    }

    public static h f0(int i, int i2, int i3) {
        if (i2 == 2) {
            j$.time.chrono.t.c.getClass();
            i3 = Math.min(i3, j$.time.chrono.t.N((long) i) ? 29 : 28);
        } else if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
            i3 = Math.min(i3, 30);
        }
        return new h(i, i2, i3);
    }

    public h(int i, int i2, int i3) {
        this.a = i;
        this.b = (short) i2;
        this.c = (short) i3;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.k(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        if (!aVar.isDateBased()) {
            throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
        int i = g.a[aVar.ordinal()];
        if (i == 1) {
            return j$.time.temporal.v.f(1L, V());
        }
        if (i == 2) {
            return j$.time.temporal.v.f(1L, U() ? 366 : 365);
        }
        if (i != 3) {
            return i != 4 ? aVar.b : this.a <= 0 ? j$.time.temporal.v.f(1L, 1000000000L) : j$.time.temporal.v.f(1L, 999999999L);
        }
        return j$.time.temporal.v.f(1L, (n.Q(this.b) != n.FEBRUARY || U()) ? 5L : 4L);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return Q(rVar);
        }
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.EPOCH_DAY) {
                return D();
            }
            if (rVar != j$.time.temporal.a.PROLEPTIC_MONTH) {
                return Q(rVar);
            }
            return ((((long) this.a) * 12) + ((long) this.b)) - 1;
        }
        return rVar.w(this);
    }

    public final int Q(j$.time.temporal.r rVar) {
        switch (g.a[((j$.time.temporal.a) rVar).ordinal()]) {
            case 1:
                return this.c;
            case 2:
                return S();
            case 3:
                return ((this.c - 1) / 7) + 1;
            case 4:
                int i = this.a;
                return i >= 1 ? i : 1 - i;
            case 5:
                return R().getValue();
            case 6:
                return ((this.c - 1) % 7) + 1;
            case 7:
                return ((S() - 1) % 7) + 1;
            case 8:
                throw new j$.time.temporal.u("Invalid field 'EpochDay' for get() method, use getLong() instead");
            case 9:
                return ((S() - 1) / 7) + 1;
            case 10:
                return this.b;
            case 11:
                throw new j$.time.temporal.u("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
            case 12:
                return this.a;
            case 13:
                return this.a >= 1 ? 1 : 0;
            default:
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.m a() {
        return j$.time.chrono.t.c;
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.n F() {
        return this.a >= 1 ? j$.time.chrono.u.CE : j$.time.chrono.u.BCE;
    }

    public final int S() {
        return (n.Q(this.b).N(U()) + this.c) - 1;
    }

    public final d R() {
        return d.N(((int) j$.com.android.tools.r8.a.J(D() + 3, 7)) + 1);
    }

    public final boolean U() {
        j$.time.chrono.t tVar = j$.time.chrono.t.c;
        long j = this.a;
        tVar.getClass();
        return j$.time.chrono.t.N(j);
    }

    public final int V() {
        short s = this.b;
        return s != 2 ? (s == 4 || s == 6 || s == 9 || s == 11) ? 30 : 31 : U() ? 29 : 28;
    }

    @Override // j$.time.chrono.b
    /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final h v(j$.time.temporal.o oVar) {
        if (oVar instanceof h) {
            return (h) oVar;
        }
        return (h) oVar.o(this);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final h c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (h) rVar.B(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        switch (g.a[aVar.ordinal()]) {
            case 1:
                int i = (int) j;
                if (this.c != i) {
                    return X(this.a, this.b, i);
                }
                return this;
            case 2:
                int i2 = (int) j;
                if (S() != i2) {
                    return Z(this.a, i2);
                }
                return this;
            case 3:
                return d0(j - C(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH));
            case 4:
                if (this.a < 1) {
                    j = 1 - j;
                }
                return i0((int) j);
            case 5:
                return b0(j - ((long) R().getValue()));
            case 6:
                return b0(j - C(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 7:
                return b0(j - C(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 8:
                return Y(j);
            case 9:
                return d0(j - C(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR));
            case 10:
                int i3 = (int) j;
                if (this.b != i3) {
                    j$.time.temporal.a.MONTH_OF_YEAR.C(i3);
                    return f0(this.a, i3, this.c);
                }
                return this;
            case 11:
                return c0(j - (((((long) this.a) * 12) + ((long) this.b)) - 1));
            case 12:
                return i0((int) j);
            case 13:
                if (C(j$.time.temporal.a.ERA) != j) {
                    return i0(1 - this.a);
                }
                return this;
            default:
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
        }
    }

    public final h i0(int i) {
        if (this.a == i) {
            return this;
        }
        j$.time.temporal.a.YEAR.C(i);
        return f0(i, this.b, this.c);
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.b I(j$.time.temporal.q qVar) {
        if (c.b(qVar)) {
            t tVar = (t) qVar;
            return c0((((long) tVar.a) * 12) + ((long) tVar.b)).b0(tVar.c);
        }
        Objects.requireNonNull(qVar, "amountToAdd");
        return (h) ((t) qVar).j(this);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public final h d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return (h) tVar.j(this, j);
        }
        switch (g.b[((j$.time.temporal.b) tVar).ordinal()]) {
            case 1:
                return b0(j);
            case 2:
                return d0(j);
            case 3:
                return c0(j);
            case 4:
                return e0(j);
            case 5:
                return e0(j$.com.android.tools.r8.a.L(j, 10));
            case 6:
                return e0(j$.com.android.tools.r8.a.L(j, 100));
            case 7:
                return e0(j$.com.android.tools.r8.a.L(j, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.F(C(aVar), j), aVar);
            default:
                throw new j$.time.temporal.u("Unsupported unit: " + tVar);
        }
    }

    public final h e0(long j) {
        if (j == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return f0(aVar.b.a(((long) this.a) + j, aVar), this.b, this.c);
    }

    public final h c0(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (((long) this.a) * 12) + ((long) (this.b - 1)) + j;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        long j3 = 12;
        return f0(aVar.b.a(j$.com.android.tools.r8.a.K(j2, j3), aVar), ((int) j$.com.android.tools.r8.a.J(j2, j3)) + 1, this.c);
    }

    public final h d0(long j) {
        return b0(j$.com.android.tools.r8.a.L(j, 7));
    }

    public final h b0(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = ((long) this.c) + j;
        if (j2 > 0) {
            if (j2 <= 28) {
                return new h(this.a, this.b, (int) j2);
            }
            if (j2 <= 59) {
                long jV = V();
                if (j2 <= jV) {
                    return new h(this.a, this.b, (int) j2);
                }
                short s = this.b;
                if (s < 12) {
                    return new h(this.a, s + 1, (int) (j2 - jV));
                }
                j$.time.temporal.a.YEAR.C(this.a + 1);
                return new h(this.a + 1, 1, (int) (j2 - jV));
            }
        }
        return Y(j$.com.android.tools.r8.a.F(D(), j));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        return eVar == j$.time.temporal.s.f ? this : j$.com.android.tools.r8.a.m(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return j$.com.android.tools.r8.a.a(this, mVar);
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.e E(l lVar) {
        return j.Q(this, lVar);
    }

    @Override // j$.time.chrono.b
    public final long D() {
        long j = this.a;
        long j2 = this.b;
        long j3 = 365 * j;
        long j4 = (((367 * j2) - 362) / 12) + (j >= 0 ? ((j + 399) / 400) + (((3 + j) / 4) - ((99 + j) / 100)) + j3 : j3 - ((j / (-400)) + ((j / (-4)) - (j / (-100))))) + ((long) (this.c - 1));
        if (j2 > 2) {
            j4 = !U() ? j4 - 2 : j4 - 1;
        }
        return j4 - 719528;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public final int compareTo(j$.time.chrono.b bVar) {
        if (bVar instanceof h) {
            return N((h) bVar);
        }
        return j$.com.android.tools.r8.a.b(this, bVar);
    }

    public final int N(h hVar) {
        int i = this.a - hVar.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - hVar.b;
        return i2 == 0 ? this.c - hVar.c : i2;
    }

    public final boolean T(j$.time.chrono.b bVar) {
        return bVar instanceof h ? N((h) bVar) < 0 : D() < bVar.D();
    }

    @Override // j$.time.chrono.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof h) && N((h) obj) == 0;
    }

    @Override // j$.time.chrono.b
    public final int hashCode() {
        int i = this.a;
        return (((i << 11) + (this.b << 6)) + this.c) ^ (i & (-2048));
    }

    @Override // j$.time.chrono.b
    public final String toString() {
        int i = this.a;
        short s = this.b;
        short s2 = this.c;
        int iAbs = Math.abs(i);
        StringBuilder sb = new StringBuilder(10);
        if (iAbs >= 1000) {
            if (i > 9999) {
                sb.append('+');
            }
            sb.append(i);
        } else if (i < 0) {
            sb.append(i - 10000);
            sb.deleteCharAt(1);
        } else {
            sb.append(i + 10000);
            sb.deleteCharAt(0);
        }
        sb.append(s < 10 ? "-0" : "-");
        sb.append((int) s);
        sb.append(s2 < 10 ? "-0" : "-");
        sb.append((int) s2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new u((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
