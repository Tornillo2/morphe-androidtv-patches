package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class r implements j$.time.temporal.m, j$.time.temporal.o, Comparable, Serializable {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 2287754244819255394L;
    public final j a;
    public final z b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int iCompare;
        r rVar = (r) obj;
        if (this.b.equals(rVar.b)) {
            iCompare = this.a.compareTo(rVar.a);
        } else {
            j jVar = this.a;
            z zVar = this.b;
            jVar.getClass();
            long jQ = j$.com.android.tools.r8.a.q(jVar, zVar);
            j jVar2 = rVar.a;
            z zVar2 = rVar.b;
            jVar2.getClass();
            iCompare = Long.compare(jQ, j$.com.android.tools.r8.a.q(jVar2, zVar2));
            if (iCompare == 0) {
                iCompare = this.a.b.d - rVar.a.b.d;
            }
        }
        return iCompare == 0 ? this.a.compareTo(rVar.a) : iCompare;
    }

    static {
        j jVar = j.c;
        z zVar = z.h;
        jVar.getClass();
        new r(jVar, zVar);
        j jVar2 = j.d;
        z zVar2 = z.g;
        jVar2.getClass();
        new r(jVar2, zVar2);
    }

    public r(j jVar, z zVar) {
        this.a = (j) Objects.requireNonNull(jVar, "dateTime");
        this.b = (z) Objects.requireNonNull(zVar, "offset");
    }

    public final r O(j jVar, z zVar) {
        return (this.a == jVar && this.b.equals(zVar)) ? this : new r(jVar, zVar);
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
            if (rVar != j$.time.temporal.a.INSTANT_SECONDS && rVar != j$.time.temporal.a.OFFSET_SECONDS) {
                return this.a.l(rVar);
            }
            return ((j$.time.temporal.a) rVar).b;
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = q.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new j$.time.temporal.u("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return this.b.b;
            }
            return this.a.j(rVar);
        }
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        int i = q.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i != 1) {
            return i != 2 ? this.a.C(rVar) : this.b.b;
        }
        j jVar = this.a;
        z zVar = this.b;
        jVar.getClass();
        return j$.com.android.tools.r8.a.q(jVar, zVar);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        j jVar = this.a;
        return O(jVar.W(hVar, jVar.b), this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            int i = q.a[aVar.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return O(this.a, z.U(aVar.b.a(j, aVar)));
                }
                return O(this.a.c(j, rVar), this.b);
            }
            Instant instantOfEpochSecond = Instant.ofEpochSecond(j, this.a.b.d);
            z zVar = this.b;
            Objects.requireNonNull(instantOfEpochSecond, "instant");
            Objects.requireNonNull(zVar, "zone");
            z zVarD = zVar.N().d(instantOfEpochSecond);
            return new r(j.R(instantOfEpochSecond.getEpochSecond(), instantOfEpochSecond.getNano(), zVarD), zVarD);
        }
        return (r) rVar.B(this, j);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public final r d(long j, j$.time.temporal.t tVar) {
        if (tVar instanceof j$.time.temporal.b) {
            return O(this.a.d(j, tVar), this.b);
        }
        return (r) tVar.j(this, j);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? d(Long.MAX_VALUE, bVar).d(1L, bVar) : d(-j, bVar);
    }

    @Override // j$.time.temporal.n
    public final Object B(e eVar) {
        if (eVar == j$.time.temporal.s.d || eVar == j$.time.temporal.s.e) {
            return this.b;
        }
        if (eVar == j$.time.temporal.s.a) {
            return null;
        }
        if (eVar == j$.time.temporal.s.f) {
            return this.a.a;
        }
        if (eVar == j$.time.temporal.s.g) {
            return this.a.b;
        }
        if (eVar == j$.time.temporal.s.b) {
            return j$.time.chrono.t.c;
        }
        if (eVar == j$.time.temporal.s.c) {
            return j$.time.temporal.b.NANOS;
        }
        return eVar.g(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(this.a.a.D(), j$.time.temporal.a.EPOCH_DAY).c(this.a.b.Z(), j$.time.temporal.a.NANO_OF_DAY).c(this.b.b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (this.a.equals(rVar.a) && this.b.equals(rVar.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.b;
    }

    public final String toString() {
        return this.a.toString() + this.b.c;
    }

    private Object writeReplace() {
        return new u((byte) 10, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
