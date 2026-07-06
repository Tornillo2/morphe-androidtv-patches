package j$.time.chrono;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class j0 extends d {
    private static final long serialVersionUID = -8722293800195731463L;
    public final transient j$.time.h a;

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final e E(j$.time.l lVar) {
        return new g(this, lVar);
    }

    public j0(j$.time.h hVar) {
        Objects.requireNonNull(hVar, "isoDate");
        this.a = hVar;
    }

    @Override // j$.time.chrono.b
    public final m a() {
        return h0.c;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        h0.c.getClass();
        return this.a.hashCode() ^ 146118545;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final n F() {
        return S() >= 1 ? k0.BE : k0.BEFORE_BE;
    }

    @Override // j$.time.chrono.d, j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        if (!j$.com.android.tools.r8.a.k(this, rVar)) {
            throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        int i = i0.a[aVar.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return this.a.l(rVar);
        }
        if (i != 4) {
            return h0.c.q(aVar);
        }
        j$.time.temporal.v vVar = j$.time.temporal.a.YEAR.b;
        return j$.time.temporal.v.f(1L, S() <= 0 ? (-(vVar.a + 543)) + 1 : 543 + vVar.d);
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = i0.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 4) {
                int iS = S();
                if (iS < 1) {
                    iS = 1 - iS;
                }
                return iS;
            }
            if (i == 5) {
                return ((((long) S()) * 12) + ((long) this.a.b)) - 1;
            }
            if (i == 6) {
                return S();
            }
            if (i != 7) {
                return this.a.C(rVar);
            }
            return S() < 1 ? 0 : 1;
        }
        return rVar.w(this);
    }

    public final int S() {
        return this.a.a + 543;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.chrono.j0 c(long r8, j$.time.temporal.r r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof j$.time.temporal.a
            if (r0 == 0) goto La0
            r0 = r10
            j$.time.temporal.a r0 = (j$.time.temporal.a) r0
            long r1 = r7.C(r0)
            int r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r3 != 0) goto L10
            return r7
        L10:
            int[] r1 = j$.time.chrono.i0.a
            int r2 = r0.ordinal()
            r2 = r1[r2]
            r3 = 7
            r4 = 6
            r5 = 4
            if (r2 == r5) goto L4a
            r6 = 5
            if (r2 == r6) goto L25
            if (r2 == r4) goto L4a
            if (r2 == r3) goto L4a
            goto L60
        L25:
            j$.time.chrono.h0 r10 = j$.time.chrono.h0.c
            j$.time.temporal.v r10 = r10.q(r0)
            r10.b(r8, r0)
            int r10 = r7.S()
            long r0 = (long) r10
            r2 = 12
            long r0 = r0 * r2
            j$.time.h r10 = r7.a
            short r2 = r10.b
            long r2 = (long) r2
            long r0 = r0 + r2
            r2 = 1
            long r0 = r0 - r2
            long r8 = r8 - r0
            j$.time.h r8 = r10.c0(r8)
            j$.time.chrono.j0 r8 = r7.U(r8)
            return r8
        L4a:
            j$.time.chrono.h0 r2 = j$.time.chrono.h0.c
            j$.time.temporal.v r2 = r2.q(r0)
            int r2 = r2.a(r8, r0)
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r5) goto L89
            if (r0 == r4) goto L7c
            if (r0 == r3) goto L6b
        L60:
            j$.time.h r0 = r7.a
            j$.time.h r8 = r0.c(r8, r10)
            j$.time.chrono.j0 r8 = r7.U(r8)
            return r8
        L6b:
            j$.time.h r8 = r7.a
            int r9 = r7.S()
            int r9 = (-542) - r9
            j$.time.h r8 = r8.i0(r9)
            j$.time.chrono.j0 r8 = r7.U(r8)
            return r8
        L7c:
            j$.time.h r8 = r7.a
            int r2 = r2 + (-543)
            j$.time.h r8 = r8.i0(r2)
            j$.time.chrono.j0 r8 = r7.U(r8)
            return r8
        L89:
            j$.time.h r8 = r7.a
            int r9 = r7.S()
            r10 = 1
            if (r9 < r10) goto L93
            goto L95
        L93:
            int r2 = 1 - r2
        L95:
            int r2 = r2 + (-543)
            j$.time.h r8 = r8.i0(r2)
            j$.time.chrono.j0 r8 = r7.U(r8)
            return r8
        La0:
            j$.time.chrono.b r8 = super.c(r8, r10)
            j$.time.chrono.j0 r8 = (j$.time.chrono.j0) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.j0.c(long, j$.time.temporal.r):j$.time.chrono.j0");
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(j$.time.h hVar) {
        return (j0) super.v(hVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b v(j$.time.temporal.o oVar) {
        return (j0) super.v(oVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b I(j$.time.temporal.q qVar) {
        return (j0) super.I(qVar);
    }

    @Override // j$.time.chrono.d
    public final b R(long j) {
        return U(this.a.e0(j));
    }

    @Override // j$.time.chrono.d
    public final b Q(long j) {
        return U(this.a.c0(j));
    }

    @Override // j$.time.chrono.d
    public final b P(long j) {
        return U(this.a.b0(j));
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.m
    public final b d(long j, j$.time.temporal.t tVar) {
        return (j0) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.t tVar) {
        return (j0) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d
    /* JADX INFO: renamed from: O */
    public final b w(long j, j$.time.temporal.t tVar) {
        return (j0) super.w(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return (j0) super.w(j, bVar);
    }

    public final j0 U(j$.time.h hVar) {
        return hVar.equals(this.a) ? this : new j0(hVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final long D() {
        return this.a.D();
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j0) {
            return this.a.equals(((j0) obj).a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new f0((byte) 8, this);
    }
}
