package j$.time.chrono;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d implements b, j$.time.temporal.m, j$.time.temporal.o, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    @Override // j$.time.temporal.n
    public final /* synthetic */ Object B(j$.time.e eVar) {
        return j$.com.android.tools.r8.a.m(this, eVar);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: L */
    public final /* synthetic */ int compareTo(b bVar) {
        return j$.com.android.tools.r8.a.b(this, bVar);
    }

    public abstract b P(long j);

    public abstract b Q(long j);

    public abstract b R(long j);

    @Override // j$.time.chrono.b, j$.time.temporal.n
    public /* synthetic */ boolean e(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.k(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public /* synthetic */ j$.time.temporal.v l(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return j$.com.android.tools.r8.a.a(this, mVar);
    }

    public static b N(m mVar, j$.time.temporal.m mVar2) {
        b bVar = (b) mVar2;
        if (mVar.equals(bVar.a())) {
            return bVar;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + mVar.i() + ", actual: " + bVar.a().i());
    }

    @Override // j$.time.chrono.b
    public e E(j$.time.l lVar) {
        return new g(this, lVar);
    }

    @Override // j$.time.temporal.m
    public b d(long j, j$.time.temporal.t tVar) {
        boolean z = tVar instanceof j$.time.temporal.b;
        if (!z) {
            if (!z) {
                return N(a(), tVar.j(this, j));
            }
            throw new j$.time.temporal.u("Unsupported unit: " + tVar);
        }
        switch (c.a[((j$.time.temporal.b) tVar).ordinal()]) {
            case 1:
                return P(j);
            case 2:
                return P(j$.com.android.tools.r8.a.L(j, 7));
            case 3:
                return Q(j);
            case 4:
                return R(j);
            case 5:
                return R(j$.com.android.tools.r8.a.L(j, 10));
            case 6:
                return R(j$.com.android.tools.r8.a.L(j, 100));
            case 7:
                return R(j$.com.android.tools.r8.a.L(j, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.F(C(aVar), j), (j$.time.temporal.r) aVar);
            default:
                throw new j$.time.temporal.u("Unsupported unit: " + tVar);
        }
    }

    @Override // j$.time.chrono.b
    public n F() {
        return a().s(j$.time.temporal.s.a(this, j$.time.temporal.a.ERA));
    }

    @Override // j$.time.chrono.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && j$.com.android.tools.r8.a.b(this, (b) obj) == 0;
    }

    @Override // j$.time.chrono.b
    public int hashCode() {
        long jD = D();
        return a().hashCode() ^ ((int) (jD ^ (jD >>> 32)));
    }

    @Override // j$.time.temporal.m
    public b v(j$.time.temporal.o oVar) {
        return N(a(), oVar.o(this));
    }

    @Override // j$.time.chrono.b
    public final String toString() {
        long jC = C(j$.time.temporal.a.YEAR_OF_ERA);
        long jC2 = C(j$.time.temporal.a.MONTH_OF_YEAR);
        long jC3 = C(j$.time.temporal.a.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(a().toString());
        sb.append(StringUtils.SPACE);
        sb.append(F());
        sb.append(StringUtils.SPACE);
        sb.append(jC);
        sb.append(jC2 < 10 ? "-0" : "-");
        sb.append(jC2);
        sb.append(jC3 < 10 ? "-0" : "-");
        sb.append(jC3);
        return sb.toString();
    }

    @Override // j$.time.temporal.m
    public b c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
        }
        return N(a(), rVar.B(this, j));
    }

    @Override // j$.time.chrono.b
    public b I(j$.time.temporal.q qVar) {
        return N(a(), qVar.j(this));
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public b w(long j, j$.time.temporal.t tVar) {
        return N(a(), j$.time.temporal.s.b(this, j, tVar));
    }

    @Override // j$.time.chrono.b
    public long D() {
        return C(j$.time.temporal.a.EPOCH_DAY);
    }
}
