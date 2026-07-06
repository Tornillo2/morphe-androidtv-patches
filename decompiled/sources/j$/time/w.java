package j$.time;

import com.google.common.base.Ascii;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class w implements j$.time.temporal.m, j$.time.temporal.o, Comparable, Serializable {
    public static final /* synthetic */ int b = 0;
    private static final long serialVersionUID = -23038383694477807L;
    public final int a;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.a - ((w) obj).a;
    }

    static {
        j$.time.format.p pVar = new j$.time.format.p();
        pVar.h(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.z.EXCEEDS_PAD);
        pVar.l(Locale.getDefault(), j$.time.format.y.SMART, null);
    }

    public static w N(int i) {
        j$.time.temporal.a.YEAR.C(i);
        return new w(i);
    }

    public w(int i) {
        this.a = i;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.YEAR || rVar == j$.time.temporal.a.YEAR_OF_ERA || rVar == j$.time.temporal.a.ERA : rVar != null && rVar.j(this);
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
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        int i = v.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i == 1) {
            int i2 = this.a;
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        if (i == 2) {
            return this.a;
        }
        if (i == 3) {
            return this.a < 1 ? 0 : 1;
        }
        throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public final w c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (w) rVar.B(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = v.a[aVar.ordinal()];
        if (i == 1) {
            if (this.a < 1) {
                j = 1 - j;
            }
            return N((int) j);
        }
        if (i == 2) {
            return N((int) j);
        }
        if (i == 3) {
            return C(j$.time.temporal.a.ERA) == j ? this : N(1 - this.a);
        }
        throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public final w d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return (w) tVar.j(this, j);
        }
        int i = v.b[((j$.time.temporal.b) tVar).ordinal()];
        if (i == 1) {
            return P(j);
        }
        if (i == 2) {
            return P(j$.com.android.tools.r8.a.L(j, 10));
        }
        if (i == 3) {
            return P(j$.com.android.tools.r8.a.L(j, 100));
        }
        if (i == 4) {
            return P(j$.com.android.tools.r8.a.L(j, 1000));
        }
        if (i == 5) {
            j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
            return c(j$.com.android.tools.r8.a.F(C(aVar), j), aVar);
        }
        throw new j$.time.temporal.u("Unsupported unit: " + tVar);
    }

    public final w P(long j) {
        if (j == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return N(aVar.b.a(((long) this.a) + j, aVar));
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
            return j$.time.temporal.b.YEARS;
        }
        return j$.time.temporal.s.c(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        if (!j$.com.android.tools.r8.a.E(mVar).equals(j$.time.chrono.t.c)) {
            throw new b("Adjustment only supported on ISO date-time");
        }
        return mVar.c(this.a, j$.time.temporal.a.YEAR);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof w) && this.a == ((w) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }

    public final String toString() {
        return Integer.toString(this.a);
    }

    private Object writeReplace() {
        return new u(Ascii.VT, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return (w) j$.com.android.tools.r8.a.a(hVar, this);
    }
}
