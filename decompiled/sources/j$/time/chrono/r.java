package j$.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends d {
    private static final long serialVersionUID = -5207853542612002020L;
    public final transient p a;
    public final transient int b;
    public final transient int c;
    public final transient int d;

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final e E(j$.time.l lVar) {
        return new g(this, lVar);
    }

    public r(p pVar, int i, int i2, int i3) {
        pVar.P(i, i2, i3);
        this.a = pVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public r(p pVar, long j) {
        int i = (int) j;
        pVar.N();
        if (i < pVar.e || i >= pVar.f) {
            throw new j$.time.b("Hijrah date out of range");
        }
        int iBinarySearch = Arrays.binarySearch(pVar.d, i);
        iBinarySearch = iBinarySearch < 0 ? (-iBinarySearch) - 2 : iBinarySearch;
        int i2 = pVar.g;
        int[] iArr = {(iBinarySearch + i2) / 12, ((i2 + iBinarySearch) % 12) + 1, (i - pVar.d[iBinarySearch]) + 1};
        this.a = pVar;
        this.b = iArr[0];
        this.c = iArr[1];
        this.d = iArr[2];
    }

    @Override // j$.time.chrono.b
    public final m a() {
        return this.a;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final n F() {
        return s.AH;
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
        int i = q.a[aVar.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? this.a.q(aVar) : j$.time.temporal.v.f(1L, 5L) : j$.time.temporal.v.f(1L, this.a.S(this.b, 12)) : j$.time.temporal.v.f(1L, this.a.Q(this.b, this.c));
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.w(this);
        }
        switch (q.a[((j$.time.temporal.a) rVar).ordinal()]) {
            case 1:
                return this.d;
            case 2:
                return S();
            case 3:
                return ((this.d - 1) / 7) + 1;
            case 4:
                return ((int) j$.com.android.tools.r8.a.J(D() + 3, 7)) + 1;
            case 5:
                return ((this.d - 1) % 7) + 1;
            case 6:
                return ((S() - 1) % 7) + 1;
            case 7:
                return D();
            case 8:
                return ((S() - 1) / 7) + 1;
            case 9:
                return this.c;
            case 10:
                return ((((long) this.b) * 12) + ((long) this.c)) - 1;
            case 11:
                return this.b;
            case 12:
                return this.b;
            case 13:
                return this.b <= 1 ? 0 : 1;
            default:
                throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
        }
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public final r c(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (r) super.c(j, rVar);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        this.a.q(aVar).b(j, aVar);
        int i = (int) j;
        switch (q.a[aVar.ordinal()]) {
            case 1:
                return V(this.b, this.c, i);
            case 2:
                return P(Math.min(i, this.a.S(this.b, 12)) - S());
            case 3:
                return P((j - C(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH)) * 7);
            case 4:
                return P(j - ((long) (((int) j$.com.android.tools.r8.a.J(D() + 3, 7)) + 1)));
            case 5:
                return P(j - C(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 6:
                return P(j - C(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 7:
                return new r(this.a, j);
            case 8:
                return P((j - C(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR)) * 7);
            case 9:
                return V(this.b, i, this.d);
            case 10:
                return Q(j - (((((long) this.b) * 12) + ((long) this.c)) - 1));
            case 11:
                if (this.b < 1) {
                    i = 1 - i;
                }
                return V(i, this.c, this.d);
            case 12:
                return V(i, this.c, this.d);
            case 13:
                return V(1 - this.b, this.c, this.d);
            default:
                throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
        }
    }

    public final r V(int i, int i2, int i3) {
        int iQ = this.a.Q(i, i2);
        if (i3 > iQ) {
            i3 = iQ;
        }
        return new r(this.a, i, i2, i3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    /* JADX INFO: renamed from: k */
    public final j$.time.temporal.m v(j$.time.h hVar) {
        return (r) super.v(hVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b v(j$.time.temporal.o oVar) {
        return (r) super.v(oVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b I(j$.time.temporal.q qVar) {
        return (r) super.I(qVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final long D() {
        return this.a.P(this.b, this.c, this.d);
    }

    public final int S() {
        return this.a.S(this.b, this.c - 1) + this.d;
    }

    @Override // j$.time.chrono.d
    public final b R(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = ((long) this.b) + ((long) ((int) j));
        int i = (int) j2;
        if (j2 == i) {
            return V(i, this.c, this.d);
        }
        throw new ArithmeticException();
    }

    @Override // j$.time.chrono.d
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public final r Q(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (((long) this.b) * 12) + ((long) (this.c - 1)) + j;
        p pVar = this.a;
        long jK = j$.com.android.tools.r8.a.K(j2, 12L);
        int i = pVar.g;
        if (jK >= i / 12 && jK <= (((pVar.d.length - 1) + i) / 12) - 1) {
            return V((int) jK, ((int) j$.com.android.tools.r8.a.J(j2, 12L)) + 1, this.d);
        }
        throw new j$.time.b("Invalid Hijrah year: " + jK);
    }

    @Override // j$.time.chrono.d
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public final r P(long j) {
        return new r(this.a, D() + j);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.m
    public final b d(long j, j$.time.temporal.t tVar) {
        return (r) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.t tVar) {
        return (r) super.d(j, tVar);
    }

    @Override // j$.time.chrono.d
    /* JADX INFO: renamed from: O */
    public final b w(long j, j$.time.temporal.t tVar) {
        return (r) super.w(j, tVar);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.m
    public final j$.time.temporal.m w(long j, j$.time.temporal.b bVar) {
        return (r) super.w(j, bVar);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (this.b == rVar.b && this.c == rVar.c && this.d == rVar.d && this.a.equals(rVar.a)) {
                return true;
            }
        }
        return false;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        int i = this.b;
        int i2 = this.c;
        int i3 = this.d;
        this.a.getClass();
        return (((i << 11) + (i2 << 6)) + i3) ^ ((i & (-2048)) ^ 2100100019);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new f0((byte) 6, this);
    }
}
