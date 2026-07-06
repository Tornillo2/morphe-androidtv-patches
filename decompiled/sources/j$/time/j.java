package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class j implements j$.time.temporal.m, j$.time.temporal.o, j$.time.chrono.e, Serializable {
    public static final j c = Q(h.d, l.e);
    public static final j d = Q(h.e, l.f);
    private static final long serialVersionUID = 6207766400415563566L;
    public final h a;
    public final l b;

    @Override // j$.time.chrono.e
    public final j$.time.chrono.m a() {
        return ((h) f()).a();
    }

    @Override // j$.time.chrono.e
    public final j$.time.chrono.j x(ZoneId zoneId) {
        return c0.N(this, zoneId, null);
    }

    public static j Q(h hVar, l lVar) {
        Objects.requireNonNull(hVar, StringLookupFactory.KEY_DATE);
        Objects.requireNonNull(lVar, "time");
        return new j(hVar, lVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(((h) f()).D(), j$.time.temporal.a.EPOCH_DAY).c(b().Z(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public static j R(long j, int i, z zVar) {
        Objects.requireNonNull(zVar, "offset");
        long j2 = i;
        j$.time.temporal.a.NANO_OF_SECOND.C(j2);
        long j3 = j + ((long) zVar.b);
        long j4 = 86400;
        return new j(h.Y(j$.com.android.tools.r8.a.K(j3, j4)), l.S((((long) ((int) j$.com.android.tools.r8.a.J(j3, j4))) * 1000000000) + j2));
    }

    public static j O(j$.time.temporal.n nVar) {
        if (nVar instanceof j) {
            return (j) nVar;
        }
        if (!(nVar instanceof c0)) {
            if (!(nVar instanceof r)) {
                try {
                    return new j(h.P(nVar), l.P(nVar));
                } catch (b e) {
                    throw new b("Unable to obtain LocalDateTime from TemporalAccessor: " + nVar + " of type " + nVar.getClass().getName(), e);
                }
            }
            return ((r) nVar).a;
        }
        return ((c0) nVar).a;
    }

    public j(h hVar, l lVar) {
        this.a = hVar;
        this.b = lVar;
    }

    public final j W(h hVar, l lVar) {
        return (this.a == hVar && this.b == lVar) ? this : new j(hVar, lVar);
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
            if (((j$.time.temporal.a) rVar).N()) {
                l lVar = this.b;
                lVar.getClass();
                return j$.time.temporal.s.d(lVar, rVar);
            }
            return this.a.l(rVar);
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).N() ? this.b.j(rVar) : this.a.j(rVar);
        }
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).N() ? this.b.C(rVar) : this.a.C(rVar);
        }
        return rVar.w(this);
    }

    @Override // j$.time.chrono.e
    public final j$.time.chrono.b f() {
        return this.a;
    }

    @Override // j$.time.chrono.e
    public final l b() {
        return this.b;
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return W(hVar, this.b);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public final j c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) rVar).N()) {
                return W(this.a, this.b.c(j, rVar));
            }
            return W(this.a.c(j, rVar), this.b);
        }
        return (j) rVar.B(this, j);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public final j d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return (j) tVar.j(this, j);
        }
        switch (i.a[((j$.time.temporal.b) tVar).ordinal()]) {
            case 1:
                return U(this.a, 0L, 0L, 0L, j);
            case 2:
                j jVarW = W(this.a.b0(j / 86400000000L), this.b);
                return jVarW.U(jVarW.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
            case 3:
                j jVarW2 = W(this.a.b0(j / 86400000), this.b);
                return jVarW2.U(jVarW2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
            case 4:
                return T(j);
            case 5:
                return U(this.a, 0L, j, 0L, 0L);
            case 6:
                return U(this.a, j, 0L, 0L, 0L);
            case 7:
                j jVarW3 = W(this.a.b0(j / 256), this.b);
                return jVarW3.U(jVarW3.a, (j % 256) * 12, 0L, 0L, 0L);
            default:
                return W(this.a.d(j, tVar), this.b);
        }
    }

    public final j T(long j) {
        return U(this.a, 0L, 0L, j, 0L);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    public final j U(h hVar, long j, long j2, long j3, long j4) {
        if ((j | j2 | j3 | j4) == 0) {
            return W(hVar, this.b);
        }
        long j5 = 1;
        long jZ = this.b.Z();
        long j6 = ((((j % 24) * 3600000000000L) + ((j2 % 1440) * 60000000000L) + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L)) * j5) + jZ;
        long jK = j$.com.android.tools.r8.a.K(j6, 86400000000000L) + (((j / 24) + (j2 / 1440) + (j3 / 86400) + (j4 / 86400000000000L)) * j5);
        long J = j$.com.android.tools.r8.a.J(j6, 86400000000000L);
        return W(hVar.b0(jK), J == jZ ? this.b : l.S(J));
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.f) {
            return this.a;
        }
        return j$.com.android.tools.r8.a.n(this, eVar);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public final int compareTo(j$.time.chrono.e eVar) {
        if (eVar instanceof j) {
            return N((j) eVar);
        }
        return j$.com.android.tools.r8.a.c(this, eVar);
    }

    public final int N(j jVar) {
        int iN = this.a.N(jVar.a);
        return iN == 0 ? this.b.compareTo(jVar.b) : iN;
    }

    public final boolean P(j$.time.chrono.e eVar) {
        if (eVar instanceof j) {
            return N((j) eVar) < 0;
        }
        long jD = this.a.D();
        long jD2 = eVar.f().D();
        if (jD >= jD2) {
            return jD == jD2 && this.b.Z() < eVar.b().Z();
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (this.a.equals(jVar.a) && this.b.equals(jVar.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + "T" + this.b.toString();
    }

    private Object writeReplace() {
        return new u((byte) 5, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
