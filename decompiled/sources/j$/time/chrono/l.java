package j$.time.chrono;

import j$.time.Duration;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class l implements j, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    public final transient g a;
    public final transient j$.time.z b;
    public final transient ZoneId c;

    @Override // j$.time.temporal.n
    public final /* synthetic */ Object B(j$.time.e eVar) {
        return j$.com.android.tools.r8.a.o(this, eVar);
    }

    @Override // j$.time.chrono.j
    public final /* synthetic */ long M() {
        return j$.com.android.tools.r8.a.r(this);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.h(this, rVar);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return j$.com.android.tools.r8.a.d(this, (j) obj);
    }

    public static l N(ZoneId zoneId, j$.time.z zVar, g gVar) {
        Objects.requireNonNull(gVar, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof j$.time.z) {
            return new l(zoneId, (j$.time.z) zoneId, gVar);
        }
        j$.time.zone.f fVarN = zoneId.N();
        j$.time.j jVarO = j$.time.j.O(gVar);
        List listF = fVarN.f(jVarO);
        if (listF.size() == 1) {
            zVar = (j$.time.z) listF.get(0);
        } else if (listF.size() != 0) {
            if (zVar == null || !listF.contains(zVar)) {
                zVar = (j$.time.z) listF.get(0);
            }
            gVar = gVar;
        } else {
            Object objE = fVarN.e(jVarO);
            j$.time.zone.b bVar = objE instanceof j$.time.zone.b ? (j$.time.zone.b) objE : null;
            gVar = gVar.P(gVar.a, 0L, 0L, Duration.k(bVar.d.b - bVar.c.b, 0).getSeconds(), 0L);
            zVar = bVar.d;
        }
        Objects.requireNonNull(zVar, "offset");
        return new l(zoneId, zVar, gVar);
    }

    public static l O(m mVar, Instant instant, ZoneId zoneId) {
        j$.time.z zVarD = zoneId.N().d(instant);
        Objects.requireNonNull(zVarD, "offset");
        return new l(zoneId, zVarD, (g) mVar.z(j$.time.j.R(instant.getEpochSecond(), instant.getNano(), zVarD)));
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar != j$.time.temporal.a.INSTANT_SECONDS && rVar != j$.time.temporal.a.OFFSET_SECONDS) {
                return ((g) p()).l(rVar);
            }
            return ((j$.time.temporal.a) rVar).b;
        }
        return rVar.k(this);
    }

    public static l o(m mVar, j$.time.temporal.m mVar2) {
        l lVar = (l) mVar2;
        if (mVar.equals(lVar.a())) {
            return lVar;
        }
        throw new ClassCastException("Chronology mismatch, required: " + mVar.i() + ", actual: " + lVar.a().i());
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = i.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                return M();
            }
            if (i != 2) {
                return ((g) p()).C(rVar);
            }
            return g().b;
        }
        return rVar.w(this);
    }

    public l(ZoneId zoneId, j$.time.z zVar, g gVar) {
        this.a = (g) Objects.requireNonNull(gVar, "dateTime");
        this.b = (j$.time.z) Objects.requireNonNull(zVar, "offset");
        this.c = (ZoneId) Objects.requireNonNull(zoneId, "zone");
    }

    @Override // j$.time.chrono.j
    public final j$.time.z g() {
        return this.b;
    }

    @Override // j$.time.chrono.j
    public final b f() {
        return ((g) p()).f();
    }

    @Override // j$.time.chrono.j
    public final j$.time.l b() {
        return ((g) p()).b();
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.b) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    @Override // j$.time.chrono.j
    public final e p() {
        return this.a;
    }

    public final String toString() {
        String str = this.a.toString() + this.b.c;
        j$.time.z zVar = this.b;
        ZoneId zoneId = this.c;
        if (zVar == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    @Override // j$.time.chrono.j
    public final ZoneId A() {
        return this.c;
    }

    @Override // j$.time.chrono.j
    public final m a() {
        return f().a();
    }

    @Override // j$.time.chrono.j
    public final j u(ZoneId zoneId) {
        return N(zoneId, this.b, this.a);
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return true;
        }
        return rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return o(a(), rVar.B(this, j));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        int i = k.a[aVar.ordinal()];
        if (i == 1) {
            return d(j - j$.com.android.tools.r8.a.r(this), j$.time.temporal.b.SECONDS);
        }
        if (i != 2) {
            return N(this.c, this.b, this.a.c(j, rVar));
        }
        j$.time.z zVarU = j$.time.z.U(aVar.b.a(j, aVar));
        g gVar = this.a;
        gVar.getClass();
        return O(a(), Instant.ofEpochSecond(j$.com.android.tools.r8.a.q(gVar, zVarU), gVar.b.d), this.c);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public final l d(long j, j$.time.temporal.t tVar) {
        if (tVar instanceof j$.time.temporal.b) {
            return o(a(), this.a.d(j, tVar).o(this));
        }
        return o(a(), tVar.j(this, j));
    }

    private Object writeReplace() {
        return new f0((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof j) && j$.com.android.tools.r8.a.d(this, (j) obj) == 0;
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(j$.time.h hVar) {
        return o(a(), hVar.o(this));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return o(a(), j$.time.temporal.s.b(this, j, bVar));
    }
}
