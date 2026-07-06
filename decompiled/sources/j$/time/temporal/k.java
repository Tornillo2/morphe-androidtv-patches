package j$.time.temporal;

import j$.time.format.x;
import j$.time.format.y;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public enum k implements r {
    JULIAN_DAY("JulianDay", 2440588),
    MODIFIED_JULIAN_DAY("ModifiedJulianDay", 40587),
    RATA_DIE("RataDie", 719163);

    private static final long serialVersionUID = -7501623920830201812L;
    public final transient String a;
    public final transient v b;
    public final transient long c;

    @Override // j$.time.temporal.r
    public final boolean isDateBased() {
        return true;
    }

    static {
        b bVar = b.NANOS;
    }

    k(String str, long j) {
        this.a = str;
        this.b = v.f((-365243219162L) + j, 365241780471L + j);
        this.c = j;
    }

    @Override // j$.time.temporal.r
    public final m B(m mVar, long j) {
        if (!this.b.e(j)) {
            throw new j$.time.b("Invalid value: " + this.a + StringUtils.SPACE + j);
        }
        return mVar.c(j$.com.android.tools.r8.a.M(j, this.c), a.EPOCH_DAY);
    }

    @Override // j$.time.temporal.r
    public final v o() {
        return this.b;
    }

    @Override // j$.time.temporal.r
    public final boolean j(n nVar) {
        return nVar.e(a.EPOCH_DAY);
    }

    @Override // j$.time.temporal.r
    public final v k(n nVar) {
        if (nVar.e(a.EPOCH_DAY)) {
            return this.b;
        }
        throw new j$.time.b("Unsupported field: " + this);
    }

    @Override // j$.time.temporal.r
    public final long w(n nVar) {
        return nVar.C(a.EPOCH_DAY) + this.c;
    }

    @Override // j$.time.temporal.r
    public final n l(Map map, x xVar, y yVar) {
        long jLongValue = ((Long) map.remove(this)).longValue();
        j$.time.chrono.m mVarE = j$.com.android.tools.r8.a.E(xVar);
        y yVar2 = y.LENIENT;
        long j = this.c;
        if (yVar == yVar2) {
            return mVarE.h(j$.com.android.tools.r8.a.M(jLongValue, j));
        }
        this.b.b(jLongValue, this);
        return mVarE.h(jLongValue - j);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
