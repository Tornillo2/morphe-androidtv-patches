package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class c0 implements j$.time.temporal.m, j$.time.chrono.j, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    public final j a;
    public final z b;
    public final ZoneId c;

    @Override // j$.time.chrono.j
    public final /* synthetic */ long M() {
        return j$.com.android.tools.r8.a.r(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return j$.com.android.tools.r8.a.d(this, (j$.time.chrono.j) obj);
    }

    @Override // j$.time.chrono.j
    public final j$.time.chrono.m a() {
        return ((h) f()).a();
    }

    public static c0 N(j jVar, ZoneId zoneId, z zVar) {
        Objects.requireNonNull(jVar, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof z) {
            return new c0(jVar, zoneId, (z) zoneId);
        }
        j$.time.zone.f fVarN = zoneId.N();
        List listF = fVarN.f(jVar);
        if (listF.size() == 1) {
            zVar = (z) listF.get(0);
        } else if (listF.size() != 0) {
            if (zVar == null || !listF.contains(zVar)) {
                zVar = (z) Objects.requireNonNull((z) listF.get(0), "offset");
            }
        } else {
            Object objE = fVarN.e(jVar);
            j$.time.zone.b bVar = objE instanceof j$.time.zone.b ? (j$.time.zone.b) objE : null;
            jVar = jVar.T(Duration.k(bVar.d.b - bVar.c.b, 0).getSeconds());
            zVar = bVar.d;
        }
        return new c0(jVar, zoneId, zVar);
    }

    public static c0 o(long j, int i, ZoneId zoneId) {
        z zVarD = zoneId.N().d(Instant.ofEpochSecond(j, i));
        return new c0(j.R(j, i, zVarD), zoneId, zVarD);
    }

    public c0(j jVar, ZoneId zoneId, z zVar) {
        this.a = jVar;
        this.b = zVar;
        this.c = zoneId;
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return true;
        }
        return rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.INSTANT_SECONDS || rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) rVar).b;
            }
            return this.a.l(rVar);
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = b0.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new j$.time.temporal.u("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return this.b.b;
            }
            return this.a.j(rVar);
        }
        return j$.com.android.tools.r8.a.h(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        int i = b0.a[((j$.time.temporal.a) rVar).ordinal()];
        return i != 1 ? i != 2 ? this.a.C(rVar) : this.b.b : j$.com.android.tools.r8.a.r(this);
    }

    @Override // j$.time.chrono.j
    public final z g() {
        return this.b;
    }

    @Override // j$.time.chrono.j
    public final ZoneId A() {
        return this.c;
    }

    @Override // j$.time.chrono.j
    public final j$.time.chrono.j u(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        return this.c.equals(zoneId) ? this : N(this.a, zoneId, this.b);
    }

    @Override // j$.time.chrono.j
    public final j$.time.chrono.e p() {
        return this.a;
    }

    @Override // j$.time.chrono.j
    public final j$.time.chrono.b f() {
        return this.a.a;
    }

    @Override // j$.time.chrono.j
    public final l b() {
        return this.a.b;
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return N(j.Q(hVar, this.a.b), this.c, this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            int i = b0.a[aVar.ordinal()];
            if (i == 1) {
                return o(j, this.a.b.d, this.c);
            }
            if (i != 2) {
                return N(this.a.c(j, rVar), this.c, this.b);
            }
            z zVarU = z.U(aVar.b.a(j, aVar));
            return (zVarU.equals(this.b) || !this.c.N().f(this.a).contains(zVarU)) ? this : new c0(this.a, this.c, zVarU);
        }
        return (c0) rVar.B(this, j);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public final c0 d(long j, j$.time.temporal.t tVar) {
        if (!(tVar instanceof j$.time.temporal.b)) {
            return (c0) tVar.j(this, j);
        }
        j$.time.temporal.b bVar = (j$.time.temporal.b) tVar;
        if (bVar.compareTo(j$.time.temporal.b.DAYS) >= 0 && bVar != j$.time.temporal.b.FOREVER) {
            return N(this.a.d(j, tVar), this.c, this.b);
        }
        j jVarD = this.a.d(j, tVar);
        z zVar = this.b;
        ZoneId zoneId = this.c;
        Objects.requireNonNull(jVarD, "localDateTime");
        Objects.requireNonNull(zVar, "offset");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId.N().f(jVarD).contains(zVar)) {
            return new c0(jVarD, zoneId, zVar);
        }
        jVarD.getClass();
        return o(j$.com.android.tools.r8.a.q(jVarD, zVar), jVarD.b.d, zoneId);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.f) {
            return this.a.a;
        }
        return j$.com.android.tools.r8.a.o(this, eVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            if (this.a.equals(c0Var.a) && this.b.equals(c0Var.b) && this.c.equals(c0Var.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.b) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    public final String toString() {
        String str = this.a.toString() + this.b.c;
        z zVar = this.b;
        ZoneId zoneId = this.c;
        if (zVar == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    private Object writeReplace() {
        return new u((byte) 6, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
