package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class s implements j$.time.temporal.m, j$.time.temporal.o, Comparable, Serializable {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 7264499704384272492L;
    public final l a;
    public final z b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        s sVar = (s) obj;
        if (this.b.equals(sVar.b)) {
            return this.a.compareTo(sVar.a);
        }
        int iCompare = Long.compare(this.a.Z() - (((long) this.b.b) * 1000000000), sVar.a.Z() - (((long) sVar.b.b) * 1000000000));
        return iCompare == 0 ? this.a.compareTo(sVar.a) : iCompare;
    }

    static {
        l lVar = l.e;
        z zVar = z.h;
        lVar.getClass();
        new s(lVar, zVar);
        l lVar2 = l.f;
        z zVar2 = z.g;
        lVar2.getClass();
        new s(lVar2, zVar2);
    }

    public s(l lVar, z zVar) {
        this.a = (l) Objects.requireNonNull(lVar, "time");
        this.b = (z) Objects.requireNonNull(zVar, "offset");
    }

    public final s O(l lVar, z zVar) {
        return (this.a == lVar && this.b.equals(zVar)) ? this : new s(lVar, zVar);
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) rVar).N() || rVar == j$.time.temporal.a.OFFSET_SECONDS : rVar != null && rVar.j(this);
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar != j$.time.temporal.a.OFFSET_SECONDS) {
                l lVar = this.a;
                lVar.getClass();
                return j$.time.temporal.s.d(lVar, rVar);
            }
            return ((j$.time.temporal.a) rVar).b;
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.n
    public final int j(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return this.b.b;
            }
            return this.a.C(rVar);
        }
        return rVar.w(this);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
                return O(this.a, z.U(aVar.b.a(j, aVar)));
            }
            return O(this.a.c(j, rVar), this.b);
        }
        return (s) rVar.B(this, j);
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public final s d(long j, j$.time.temporal.t tVar) {
        if (tVar instanceof j$.time.temporal.b) {
            return O(this.a.d(j, tVar), this.b);
        }
        return (s) tVar.j(this, j);
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
        if (((eVar == j$.time.temporal.s.a) || (eVar == j$.time.temporal.s.b)) || eVar == j$.time.temporal.s.f) {
            return null;
        }
        if (eVar == j$.time.temporal.s.g) {
            return this.a;
        }
        if (eVar == j$.time.temporal.s.c) {
            return j$.time.temporal.b.NANOS;
        }
        return eVar.g(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(this.a.Z(), j$.time.temporal.a.NANO_OF_DAY).c(this.b.b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof s) {
            s sVar = (s) obj;
            if (this.a.equals(sVar.a) && this.b.equals(sVar.b)) {
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
        return new u((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(h hVar) {
        return (s) j$.com.android.tools.r8.a.a(hVar, this);
    }
}
