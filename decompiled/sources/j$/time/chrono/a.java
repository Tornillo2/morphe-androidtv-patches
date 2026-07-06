package j$.time.chrono;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements m {
    public static final ConcurrentHashMap a = new ConcurrentHashMap();
    public static final ConcurrentHashMap b = new ConcurrentHashMap();

    public abstract /* synthetic */ b k();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return i().compareTo(((m) obj).i());
    }

    static {
        new Locale("ja", "JP", "JP");
    }

    public static m l(m mVar, String str) {
        String strM;
        m mVar2 = (m) a.putIfAbsent(str, mVar);
        if (mVar2 == null && (strM = mVar.m()) != null) {
            b.putIfAbsent(strM, mVar);
        }
        return mVar2;
    }

    @Override // j$.time.chrono.m
    public b J(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
        if (map.containsKey(aVar)) {
            return h(((Long) map.remove(aVar)).longValue());
        }
        w(map, yVar);
        b bVarC = C(map, yVar);
        if (bVarC != null) {
            return bVarC;
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR;
        if (!map.containsKey(aVar2)) {
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.MONTH_OF_YEAR;
        if (map.containsKey(aVar3)) {
            if (map.containsKey(j$.time.temporal.a.DAY_OF_MONTH)) {
                return B(map, yVar);
            }
            j$.time.temporal.a aVar4 = j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH;
            if (map.containsKey(aVar4)) {
                j$.time.temporal.a aVar5 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                if (map.containsKey(aVar5)) {
                    int iA = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
                    if (yVar == j$.time.format.y.LENIENT) {
                        long jM = j$.com.android.tools.r8.a.M(((Long) map.remove(aVar3)).longValue(), 1L);
                        return H(iA, 1, 1).d(jM, (j$.time.temporal.t) j$.time.temporal.b.MONTHS).d(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar4)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.WEEKS).d(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar5)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
                    }
                    int iA2 = q(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    int iA3 = q(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4);
                    b bVarD = H(iA, iA2, 1).d((q(aVar5).a(((Long) map.remove(aVar5)).longValue(), aVar5) - 1) + ((iA3 - 1) * 7), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
                    if (yVar != j$.time.format.y.STRICT || bVarD.j(aVar3) == iA2) {
                        return bVarD;
                    }
                    throw new j$.time.b("Strict mode rejected resolved date as it is in a different month");
                }
                j$.time.temporal.a aVar6 = j$.time.temporal.a.DAY_OF_WEEK;
                if (map.containsKey(aVar6)) {
                    int iA4 = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
                    if (yVar == j$.time.format.y.LENIENT) {
                        return o(H(iA4, 1, 1), j$.com.android.tools.r8.a.M(((Long) map.remove(aVar3)).longValue(), 1L), j$.com.android.tools.r8.a.M(((Long) map.remove(aVar4)).longValue(), 1L), j$.com.android.tools.r8.a.M(((Long) map.remove(aVar6)).longValue(), 1L));
                    }
                    int iA5 = q(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    b bVarV = H(iA4, iA5, 1).d((q(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4) - 1) * 7, (j$.time.temporal.t) j$.time.temporal.b.DAYS).v(new j$.time.temporal.p(j$.time.d.N(q(aVar6).a(((Long) map.remove(aVar6)).longValue(), aVar6)).getValue(), 0));
                    if (yVar != j$.time.format.y.STRICT || bVarV.j(aVar3) == iA5) {
                        return bVarV;
                    }
                    throw new j$.time.b("Strict mode rejected resolved date as it is in a different month");
                }
            }
        }
        j$.time.temporal.a aVar7 = j$.time.temporal.a.DAY_OF_YEAR;
        if (map.containsKey(aVar7)) {
            int iA6 = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
            if (yVar != j$.time.format.y.LENIENT) {
                return n(iA6, q(aVar7).a(((Long) map.remove(aVar7)).longValue(), aVar7));
            }
            return n(iA6, 1).d(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar7)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
        }
        j$.time.temporal.a aVar8 = j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR;
        if (!map.containsKey(aVar8)) {
            return null;
        }
        j$.time.temporal.a aVar9 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR;
        if (map.containsKey(aVar9)) {
            int iA7 = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
            if (yVar == j$.time.format.y.LENIENT) {
                return n(iA7, 1).d(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar8)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.WEEKS).d(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar9)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
            }
            int iA8 = q(aVar8).a(((Long) map.remove(aVar8)).longValue(), aVar8);
            b bVarD2 = n(iA7, 1).d((q(aVar9).a(((Long) map.remove(aVar9)).longValue(), aVar9) - 1) + ((iA8 - 1) * 7), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
            if (yVar != j$.time.format.y.STRICT || bVarD2.j(aVar2) == iA7) {
                return bVarD2;
            }
            throw new j$.time.b("Strict mode rejected resolved date as it is in a different year");
        }
        j$.time.temporal.a aVar10 = j$.time.temporal.a.DAY_OF_WEEK;
        if (!map.containsKey(aVar10)) {
            return null;
        }
        int iA9 = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
        if (yVar == j$.time.format.y.LENIENT) {
            return o(n(iA9, 1), 0L, j$.com.android.tools.r8.a.M(((Long) map.remove(aVar8)).longValue(), 1L), j$.com.android.tools.r8.a.M(((Long) map.remove(aVar10)).longValue(), 1L));
        }
        b bVarV2 = n(iA9, 1).d((q(aVar8).a(((Long) map.remove(aVar8)).longValue(), aVar8) - 1) * 7, (j$.time.temporal.t) j$.time.temporal.b.DAYS).v(new j$.time.temporal.p(j$.time.d.N(q(aVar10).a(((Long) map.remove(aVar10)).longValue(), aVar10)).getValue(), 0));
        if (yVar != j$.time.format.y.STRICT || bVarV2.j(aVar2) == iA9) {
            return bVarV2;
        }
        throw new j$.time.b("Strict mode rejected resolved date as it is in a different year");
    }

    @Override // j$.time.chrono.m
    public e z(j$.time.j jVar) {
        try {
            return y(jVar).E(j$.time.l.P(jVar));
        } catch (j$.time.b e) {
            throw new j$.time.b("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + j$.time.j.class, e);
        }
    }

    public void w(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long l = (Long) map.remove(aVar);
        if (l != null) {
            if (yVar != j$.time.format.y.LENIENT) {
                aVar.C(l.longValue());
            }
            b bVarC = k().c(1L, (j$.time.temporal.r) j$.time.temporal.a.DAY_OF_MONTH).c(l.longValue(), (j$.time.temporal.r) aVar);
            j(map, j$.time.temporal.a.MONTH_OF_YEAR, bVarC.j(r0));
            j(map, j$.time.temporal.a.YEAR, bVarC.j(r0));
        }
    }

    public b C(Map map, j$.time.format.y yVar) {
        int iA;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR_OF_ERA;
        Long l = (Long) map.remove(aVar);
        if (l == null) {
            j$.time.temporal.a aVar2 = j$.time.temporal.a.ERA;
            if (!map.containsKey(aVar2)) {
                return null;
            }
            q(aVar2).b(((Long) map.get(aVar2)).longValue(), aVar2);
            return null;
        }
        Long l2 = (Long) map.remove(j$.time.temporal.a.ERA);
        if (yVar != j$.time.format.y.LENIENT) {
            iA = q(aVar).a(l.longValue(), aVar);
        } else {
            long jLongValue = l.longValue();
            int i = (int) jLongValue;
            if (jLongValue != i) {
                throw new ArithmeticException();
            }
            iA = i;
        }
        if (l2 != null) {
            j(map, j$.time.temporal.a.YEAR, t(s(q(r2).a(l2.longValue(), r2)), iA));
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.YEAR;
        if (map.containsKey(aVar3)) {
            j(map, aVar3, t(n(q(aVar3).a(((Long) map.get(aVar3)).longValue(), aVar3), 1).F(), iA));
            return null;
        }
        if (yVar == j$.time.format.y.STRICT) {
            map.put(aVar, l);
            return null;
        }
        if (r().isEmpty()) {
            j(map, aVar3, iA);
            return null;
        }
        j(map, aVar3, t((n) r12.get(r12.size() - 1), iA));
        return null;
    }

    public b B(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int iA = q(aVar).a(((Long) map.remove(aVar)).longValue(), aVar);
        if (yVar == j$.time.format.y.LENIENT) {
            long jM = j$.com.android.tools.r8.a.M(((Long) map.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1L);
            return H(iA, 1, 1).d(jM, (j$.time.temporal.t) j$.time.temporal.b.MONTHS).d(j$.com.android.tools.r8.a.M(((Long) map.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1L), (j$.time.temporal.t) j$.time.temporal.b.DAYS);
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        int iA2 = q(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        int iA3 = q(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
        if (yVar != j$.time.format.y.SMART) {
            return H(iA, iA2, iA3);
        }
        try {
            return H(iA, iA2, iA3);
        } catch (j$.time.b unused) {
            return H(iA, iA2, 1).v(new j$.time.e(2));
        }
    }

    public static b o(b bVar, long j, long j2, long j3) {
        long j4;
        b bVarD = bVar.d(j, (j$.time.temporal.t) j$.time.temporal.b.MONTHS);
        j$.time.temporal.b bVar2 = j$.time.temporal.b.WEEKS;
        b bVarD2 = bVarD.d(j2, (j$.time.temporal.t) bVar2);
        if (j3 > 7) {
            long j5 = j3 - 1;
            bVarD2 = bVarD2.d(j5 / 7, (j$.time.temporal.t) bVar2);
            j4 = j5 % 7;
        } else {
            if (j3 < 1) {
                bVarD2 = bVarD2.d(j$.com.android.tools.r8.a.M(j3, 7L) / 7, (j$.time.temporal.t) bVar2);
                j4 = (j3 + 6) % 7;
            }
            return bVarD2.v(new j$.time.temporal.p(j$.time.d.N((int) j3).getValue(), 0));
        }
        j3 = j4 + 1;
        return bVarD2.v(new j$.time.temporal.p(j$.time.d.N((int) j3).getValue(), 0));
    }

    public static void j(Map map, j$.time.temporal.a aVar, long j) {
        Long l = (Long) map.get(aVar);
        if (l != null && l.longValue() != j) {
            throw new j$.time.b("Conflict found: " + aVar + StringUtils.SPACE + l + " differs from " + aVar + StringUtils.SPACE + j);
        }
        map.put(aVar, Long.valueOf(j));
    }

    @Override // j$.time.chrono.m
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && i().compareTo(((a) obj).i()) == 0;
    }

    @Override // j$.time.chrono.m
    public final int hashCode() {
        return getClass().hashCode() ^ i().hashCode();
    }

    @Override // j$.time.chrono.m
    public final String toString() {
        return i();
    }
}
