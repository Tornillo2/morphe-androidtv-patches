package j$.time.chrono;

import j$.time.ZoneId;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements e, j$.time.temporal.m, j$.time.temporal.o, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    public final transient b a;
    public final transient j$.time.l b;

    @Override // j$.time.temporal.n
    public final /* synthetic */ Object B(j$.time.e eVar) {
        return j$.com.android.tools.r8.a.n(this, eVar);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: G */
    public final /* synthetic */ int compareTo(e eVar) {
        return j$.com.android.tools.r8.a.c(this, eVar);
    }

    public static g N(m mVar, j$.time.temporal.m mVar2) {
        g gVar = (g) mVar2;
        if (mVar.equals(gVar.a.a())) {
            return gVar;
        }
        throw new ClassCastException("Chronology mismatch, required: " + mVar.i() + ", actual: " + gVar.a.a().i());
    }

    public g(b bVar, j$.time.l lVar) {
        Objects.requireNonNull(bVar, StringLookupFactory.KEY_DATE);
        Objects.requireNonNull(lVar, "time");
        this.a = bVar;
        this.b = lVar;
    }

    public final g R(j$.time.temporal.m mVar, j$.time.l lVar) {
        b bVar = this.a;
        return (bVar == mVar && this.b == lVar) ? this : new g(d.N(bVar.a(), mVar), lVar);
    }

    @Override // j$.time.chrono.e
    public final m a() {
        return this.a.a();
    }

    @Override // j$.time.chrono.e
    public final b f() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + "T" + this.b.toString();
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return N(this.a.a(), j$.time.temporal.s.b(this, j, bVar));
    }

    @Override // j$.time.chrono.e
    public final j$.time.l b() {
        return this.b;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar != null && rVar.j(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        return aVar.isDateBased() || aVar.N();
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (!((j$.time.temporal.a) rVar).N()) {
                return this.a.l(rVar);
            }
            j$.time.l lVar = this.b;
            lVar.getClass();
            return j$.time.temporal.s.d(lVar, rVar);
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).N() ? this.b.j(rVar) : this.a.j(rVar);
        }
        return l(rVar).a(C(rVar), rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).N() ? this.b.C(rVar) : this.a.C(rVar);
        }
        return rVar.w(this);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(j$.time.h hVar) {
        return R(hVar, this.b);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public final g c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) rVar).N()) {
                return R(this.a, this.b.c(j, rVar));
            }
            return R(this.a.c(j, rVar), this.b);
        }
        return N(this.a.a(), rVar.B(this, j));
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public final g d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return N(this.a.a(), tVar.j(this, j));
        }
        switch (f.a[((j$.time.temporal.b) tVar).ordinal()]) {
            case 1:
                return P(this.a, 0L, 0L, 0L, j);
            case 2:
                g gVarR = R(this.a.d(j / 86400000000L, (j$.time.temporal.t) j$.time.temporal.b.DAYS), this.b);
                return gVarR.P(gVarR.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
            case 3:
                g gVarR2 = R(this.a.d(j / 86400000, (j$.time.temporal.t) j$.time.temporal.b.DAYS), this.b);
                return gVarR2.P(gVarR2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
            case 4:
                return P(this.a, 0L, 0L, j, 0L);
            case 5:
                return P(this.a, 0L, j, 0L, 0L);
            case 6:
                return P(this.a, j, 0L, 0L, 0L);
            case 7:
                g gVarR3 = R(this.a.d(j / 256, (j$.time.temporal.t) j$.time.temporal.b.DAYS), this.b);
                return gVarR3.P(gVarR3.a, (j % 256) * 12, 0L, 0L, 0L);
            default:
                return R(this.a.d(j, tVar), this.b);
        }
    }

    public final g P(b bVar, long j, long j2, long j3, long j4) {
        if ((j | j2 | j3 | j4) == 0) {
            return R(bVar, this.b);
        }
        long j5 = j / 24;
        long j6 = ((j % 24) * 3600000000000L) + ((j2 % 1440) * 60000000000L) + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long jZ = this.b.Z();
        long j7 = j6 + jZ;
        long jK = j$.com.android.tools.r8.a.K(j7, 86400000000000L) + j5 + (j2 / 1440) + (j3 / 86400) + (j4 / 86400000000000L);
        long J = j$.com.android.tools.r8.a.J(j7, 86400000000000L);
        return R(bVar.d(jK, (j$.time.temporal.t) j$.time.temporal.b.DAYS), J == jZ ? this.b : j$.time.l.S(J));
    }

    @Override // j$.time.chrono.e
    public final j x(ZoneId zoneId) {
        return l.N(zoneId, null, this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(f().D(), j$.time.temporal.a.EPOCH_DAY).c(b().Z(), j$.time.temporal.a.NANO_OF_DAY);
    }

    private Object writeReplace() {
        return new f0((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && j$.com.android.tools.r8.a.c(this, (e) obj) == 0;
    }
}
