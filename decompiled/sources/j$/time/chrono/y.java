package j$.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class y extends d {
    public static final j$.time.h d = j$.time.h.X(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    public final transient j$.time.h a;
    public final transient z b;
    public final transient int c;

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final e E(j$.time.l lVar) {
        return new g(this, lVar);
    }

    public y(j$.time.h hVar) {
        if (hVar.T(d)) {
            throw new j$.time.b("JapaneseDate before Meiji 6 is not supported");
        }
        z zVarH = z.h(hVar);
        this.b = zVarH;
        this.c = (hVar.a - zVarH.b.a) + 1;
        this.a = hVar;
    }

    public y(z zVar, int i, j$.time.h hVar) {
        if (hVar.T(d)) {
            throw new j$.time.b("JapaneseDate before Meiji 6 is not supported");
        }
        this.b = zVar;
        this.c = i;
        this.a = hVar;
    }

    @Override // j$.time.chrono.b
    public final m a() {
        return w.c;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        w.c.getClass();
        return this.a.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final n F() {
        return this.b;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH || rVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR || rVar == j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH || rVar == j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).isDateBased();
        }
        return rVar != null && rVar.j(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    @Override // j$.time.chrono.d, j$.time.temporal.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final j$.time.temporal.v l(j$.time.temporal.r r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof j$.time.temporal.a
            if (r0 == 0) goto L99
            boolean r0 = r5.e(r6)
            if (r0 == 0) goto L8d
            j$.time.temporal.a r6 = (j$.time.temporal.a) r6
            int[] r0 = j$.time.chrono.x.a
            int r1 = r6.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 1
            if (r0 == r1) goto L81
            r4 = 2
            if (r0 == r4) goto L48
            r4 = 3
            if (r0 == r4) goto L26
            j$.time.chrono.w r0 = j$.time.chrono.w.c
            j$.time.temporal.v r6 = r0.q(r6)
            return r6
        L26:
            j$.time.chrono.z r6 = r5.b
            j$.time.h r0 = r6.b
            int r0 = r0.a
            j$.time.chrono.z r6 = r6.i()
            if (r6 == 0) goto L3e
            j$.time.h r6 = r6.b
            int r6 = r6.a
            int r6 = r6 - r0
            int r6 = r6 + r1
            long r0 = (long) r6
            j$.time.temporal.v r6 = j$.time.temporal.v.f(r2, r0)
            return r6
        L3e:
            r6 = 999999999(0x3b9ac9ff, float:0.004723787)
            int r6 = r6 - r0
            long r0 = (long) r6
            j$.time.temporal.v r6 = j$.time.temporal.v.f(r2, r0)
            return r6
        L48:
            j$.time.chrono.z r6 = r5.b
            j$.time.chrono.z r6 = r6.i()
            if (r6 == 0) goto L60
            j$.time.h r6 = r6.b
            int r0 = r6.a
            j$.time.h r4 = r5.a
            int r4 = r4.a
            if (r0 != r4) goto L60
            int r6 = r6.S()
            int r6 = r6 - r1
            goto L6d
        L60:
            j$.time.h r6 = r5.a
            boolean r6 = r6.U()
            if (r6 == 0) goto L6b
            r6 = 366(0x16e, float:5.13E-43)
            goto L6d
        L6b:
            r6 = 365(0x16d, float:5.11E-43)
        L6d:
            int r0 = r5.c
            if (r0 != r1) goto L7b
            j$.time.chrono.z r0 = r5.b
            j$.time.h r0 = r0.b
            int r0 = r0.S()
            int r0 = r0 - r1
            int r6 = r6 - r0
        L7b:
            long r0 = (long) r6
            j$.time.temporal.v r6 = j$.time.temporal.v.f(r2, r0)
            return r6
        L81:
            j$.time.h r6 = r5.a
            int r6 = r6.V()
            long r0 = (long) r6
            j$.time.temporal.v r6 = j$.time.temporal.v.f(r2, r0)
            return r6
        L8d:
            j$.time.temporal.u r0 = new j$.time.temporal.u
            java.lang.String r1 = "Unsupported field: "
            java.lang.String r6 = j$.time.c.a(r1, r6)
            r0.<init>(r6)
            throw r0
        L99:
            j$.time.temporal.v r6 = r6.k(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.y.l(j$.time.temporal.r):j$.time.temporal.v");
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        switch (x.a[((j$.time.temporal.a) rVar).ordinal()]) {
            case 2:
                return this.c == 1 ? (this.a.S() - this.b.b.S()) + 1 : this.a.S();
            case 3:
                return this.c;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
            case 8:
                return this.b.a;
            default:
                return this.a.C(rVar);
        }
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public final y c(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            if (C(aVar) == j) {
                return this;
            }
            int[] iArr = x.a;
            int i = iArr[aVar.ordinal()];
            if (i == 3 || i == 8 || i == 9) {
                w wVar = w.c;
                int iA = wVar.q(aVar).a(j, aVar);
                int i2 = iArr[aVar.ordinal()];
                if (i2 == 3) {
                    return V(this.a.i0(wVar.t(this.b, iA)));
                }
                if (i2 == 8) {
                    return V(this.a.i0(wVar.t(z.m(iA), this.c)));
                }
                if (i2 == 9) {
                    return V(this.a.i0(iA));
                }
            }
            return V(this.a.c(j, rVar));
        }
        return (y) super.c(j, rVar);
    }

    public final y U(j$.time.e eVar) {
        return (y) super.v(eVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(j$.time.h hVar) {
        return (y) super.v(hVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b v(j$.time.temporal.o oVar) {
        return (y) super.v(oVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b I(j$.time.temporal.q qVar) {
        return (y) super.I(qVar);
    }

    @Override // j$.time.chrono.d
    public final b R(long j) {
        return V(this.a.e0(j));
    }

    @Override // j$.time.chrono.d
    public final b Q(long j) {
        return V(this.a.c0(j));
    }

    @Override // j$.time.chrono.d
    public final b P(long j) {
        return V(this.a.b0(j));
    }

    public final y S(long j, j$.time.temporal.b bVar) {
        return (y) super.d(j, (j$.time.temporal.t) bVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.m
    public final b d(long j, j$.time.temporal.t tVar) {
        return (y) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.t tVar) {
        return (y) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d
    /* JADX INFO: renamed from: O */
    public final b w(long j, j$.time.temporal.t tVar) {
        return (y) super.w(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return (y) super.w(j, bVar);
    }

    public final y V(j$.time.h hVar) {
        return hVar.equals(this.a) ? this : new y(hVar);
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
        if (obj instanceof y) {
            return this.a.equals(((y) obj).a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new f0((byte) 4, this);
    }
}
