package j$.time.temporal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class s {
    public static final j$.time.e a = new j$.time.e(3);
    public static final j$.time.e b = new j$.time.e(4);
    public static final j$.time.e c = new j$.time.e(5);
    public static final j$.time.e d = new j$.time.e(6);
    public static final j$.time.e e = new j$.time.e(7);
    public static final j$.time.e f = new j$.time.e(8);
    public static final j$.time.e g = new j$.time.e(9);

    public static v d(n nVar, r rVar) {
        if (!(rVar instanceof a)) {
            Objects.requireNonNull(rVar, "field");
            return rVar.k(nVar);
        }
        if (nVar.e(rVar)) {
            return ((a) rVar).b;
        }
        throw new u(j$.time.c.a("Unsupported field: ", rVar));
    }

    public static int a(n nVar, r rVar) {
        v vVarL = nVar.l(rVar);
        if (!vVarL.d()) {
            throw new u("Invalid field " + rVar + " for get() method, use getLong() instead");
        }
        long jC = nVar.C(rVar);
        if (vVarL.e(jC)) {
            return (int) jC;
        }
        throw new j$.time.b("Invalid value for " + rVar + " (valid values " + vVarL + "): " + jC);
    }

    public static Object c(n nVar, j$.time.e eVar) {
        if (eVar == a || eVar == b || eVar == c) {
            return null;
        }
        return eVar.g(nVar);
    }

    public static m b(m mVar, long j, t tVar) {
        long j2;
        if (j == Long.MIN_VALUE) {
            mVar = mVar.d(Long.MAX_VALUE, tVar);
            j2 = 1;
        } else {
            j2 = -j;
        }
        return mVar.d(j2, tVar);
    }
}
