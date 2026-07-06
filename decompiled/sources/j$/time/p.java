package j$.time;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class p implements j$.time.temporal.n, j$.time.temporal.o, Comparable, Serializable {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = -939150713474957432L;
    public final int a;
    public final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        p pVar = (p) obj;
        int i = this.a - pVar.a;
        return i == 0 ? this.b - pVar.b : i;
    }

    static {
        j$.time.format.p pVar = new j$.time.format.p();
        pVar.d("--");
        pVar.g(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        pVar.c('-');
        pVar.g(j$.time.temporal.a.DAY_OF_MONTH, 2);
        pVar.l(Locale.getDefault(), j$.time.format.y.SMART, null);
    }

    public p(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.MONTH_OF_YEAR || rVar == j$.time.temporal.a.DAY_OF_MONTH : rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.MONTH_OF_YEAR) {
            return rVar.o();
        }
        if (rVar != j$.time.temporal.a.DAY_OF_MONTH) {
            return j$.time.temporal.s.d(this, rVar);
        }
        n nVarQ = n.Q(this.a);
        nVarQ.getClass();
        int i = m.a[nVarQ.ordinal()];
        return j$.time.temporal.v.g(i != 1 ? (i == 2 || i == 3 || i == 4 || i == 5) ? 30 : 31 : 28, n.Q(this.a).P());
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
        int i2 = o.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i2 == 1) {
            i = this.b;
        } else {
            if (i2 != 2) {
                throw new j$.time.temporal.u(c.a("Unsupported field: ", rVar));
            }
            i = this.a;
        }
        return i;
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.b) {
            return j$.time.chrono.t.c;
        }
        return j$.time.temporal.s.c(this, eVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        if (!j$.com.android.tools.r8.a.E(mVar).equals(j$.time.chrono.t.c)) {
            throw new b("Adjustment only supported on ISO date-time");
        }
        j$.time.temporal.m mVarC = mVar.c(this.a, j$.time.temporal.a.MONTH_OF_YEAR);
        j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
        return mVarC.c(Math.min(mVarC.l(aVar).d, this.b), aVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (this.a == pVar.a && this.b == pVar.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.a << 6) + this.b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        sb.append(this.a < 10 ? "0" : "");
        sb.append(this.a);
        sb.append(this.b < 10 ? "-0" : "-");
        sb.append(this.b);
        return sb.toString();
    }

    private Object writeReplace() {
        return new u((byte) 13, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
