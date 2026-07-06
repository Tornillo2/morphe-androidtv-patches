package j$.time.temporal;

import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import j$.time.format.x;
import j$.time.format.y;
import java.util.Map;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public abstract class h implements r {
    public static final h DAY_OF_QUARTER;
    public static final h QUARTER_OF_YEAR;
    public static final h WEEK_BASED_YEAR;
    public static final h WEEK_OF_WEEK_BASED_YEAR;
    public static final int[] a;
    public static final /* synthetic */ h[] b;

    @Override // j$.time.temporal.r
    public final boolean isDateBased() {
        return true;
    }

    public /* synthetic */ n l(Map map, x xVar, y yVar) {
        return null;
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) b.clone();
    }

    static {
        h hVar = new h() { // from class: j$.time.temporal.d
            @Override // j$.time.temporal.r
            public final v o() {
                return v.g(90L, 92L);
            }

            @Override // j$.time.temporal.r
            public final boolean j(n nVar) {
                if (!nVar.e(a.DAY_OF_YEAR) || !nVar.e(a.MONTH_OF_YEAR) || !nVar.e(a.YEAR)) {
                    return false;
                }
                h hVar2 = j.a;
                return j$.com.android.tools.r8.a.E(nVar).equals(j$.time.chrono.t.c);
            }

            @Override // j$.time.temporal.r
            public final v k(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: DayOfQuarter");
                }
                long jC = nVar.C(h.QUARTER_OF_YEAR);
                if (jC == 1) {
                    long jC2 = nVar.C(a.YEAR);
                    j$.time.chrono.t.c.getClass();
                    return j$.time.chrono.t.N(jC2) ? v.f(1L, 91L) : v.f(1L, 90L);
                }
                if (jC == 2) {
                    return v.f(1L, 91L);
                }
                if (jC == 3 || jC == 4) {
                    return v.f(1L, 92L);
                }
                return o();
            }

            @Override // j$.time.temporal.r
            public final long w(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: DayOfQuarter");
                }
                int iJ = nVar.j(a.DAY_OF_YEAR);
                int iJ2 = nVar.j(a.MONTH_OF_YEAR);
                long jC = nVar.C(a.YEAR);
                int i = (iJ2 - 1) / 3;
                j$.time.chrono.t.c.getClass();
                return iJ - h.a[i + (j$.time.chrono.t.N(jC) ? 4 : 0)];
            }

            @Override // j$.time.temporal.r
            public final m B(m mVar, long j) {
                long jW = w(mVar);
                o().b(j, this);
                a aVar = a.DAY_OF_YEAR;
                return mVar.c((j - jW) + mVar.C(aVar), aVar);
            }

            @Override // j$.time.temporal.h, j$.time.temporal.r
            public final n l(Map map, x xVar, y yVar) {
                j$.time.h hVarC0;
                long jM;
                a aVar = a.YEAR;
                Long l = (Long) map.get(aVar);
                r rVar = h.QUARTER_OF_YEAR;
                Long l2 = (Long) map.get(rVar);
                if (l == null || l2 == null) {
                    return null;
                }
                int iA = aVar.b.a(l.longValue(), aVar);
                long jLongValue = ((Long) map.get(h.DAY_OF_QUARTER)).longValue();
                h hVar2 = j.a;
                if (!j$.com.android.tools.r8.a.E(xVar).equals(j$.time.chrono.t.c)) {
                    throw new j$.time.b("Resolve requires IsoChronology");
                }
                if (yVar == y.LENIENT) {
                    hVarC0 = j$.time.h.X(iA, 1, 1).c0(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.M(l2.longValue(), 1L), 3));
                    jM = j$.com.android.tools.r8.a.M(jLongValue, 1L);
                } else {
                    j$.time.h hVarX = j$.time.h.X(iA, ((rVar.o().a(l2.longValue(), rVar) - 1) * 3) + 1, 1);
                    if (jLongValue < 1 || jLongValue > 90) {
                        if (yVar == y.STRICT) {
                            k(hVarX).b(jLongValue, this);
                        } else {
                            o().b(jLongValue, this);
                        }
                    }
                    hVarC0 = hVarX;
                    jM = jLongValue - 1;
                }
                map.remove(this);
                map.remove(aVar);
                map.remove(rVar);
                return hVarC0.b0(jM);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "DayOfQuarter";
            }
        };
        DAY_OF_QUARTER = hVar;
        h hVar2 = new h() { // from class: j$.time.temporal.e
            @Override // j$.time.temporal.r
            public final v o() {
                return v.f(1L, 4L);
            }

            @Override // j$.time.temporal.r
            public final boolean j(n nVar) {
                if (!nVar.e(a.MONTH_OF_YEAR)) {
                    return false;
                }
                h hVar3 = j.a;
                return j$.com.android.tools.r8.a.E(nVar).equals(j$.time.chrono.t.c);
            }

            @Override // j$.time.temporal.r
            public final long w(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: QuarterOfYear");
                }
                return (nVar.C(a.MONTH_OF_YEAR) + 2) / 3;
            }

            @Override // j$.time.temporal.r
            public final v k(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: QuarterOfYear");
                }
                return o();
            }

            @Override // j$.time.temporal.r
            public final m B(m mVar, long j) {
                long jW = w(mVar);
                o().b(j, this);
                a aVar = a.MONTH_OF_YEAR;
                return mVar.c(((j - jW) * 3) + mVar.C(aVar), aVar);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "QuarterOfYear";
            }
        };
        QUARTER_OF_YEAR = hVar2;
        h hVar3 = new h() { // from class: j$.time.temporal.f
            @Override // j$.time.temporal.r
            public final v o() {
                return v.g(52L, 53L);
            }

            @Override // j$.time.temporal.r
            public final boolean j(n nVar) {
                if (!nVar.e(a.EPOCH_DAY)) {
                    return false;
                }
                h hVar4 = j.a;
                return j$.com.android.tools.r8.a.E(nVar).equals(j$.time.chrono.t.c);
            }

            @Override // j$.time.temporal.r
            public final v k(n nVar) {
                if (j(nVar)) {
                    return h.P(j$.time.h.P(nVar));
                }
                throw new u("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // j$.time.temporal.r
            public final long w(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: WeekOfWeekBasedYear");
                }
                return h.C(j$.time.h.P(nVar));
            }

            @Override // j$.time.temporal.r
            public final m B(m mVar, long j) {
                o().b(j, this);
                return mVar.d(j$.com.android.tools.r8.a.M(j, w(mVar)), b.WEEKS);
            }

            @Override // j$.time.temporal.h, j$.time.temporal.r
            public final n l(Map map, x xVar, y yVar) {
                j$.time.h hVarC;
                long j;
                long j2;
                r rVar = h.WEEK_BASED_YEAR;
                Long l = (Long) map.get(rVar);
                a aVar = a.DAY_OF_WEEK;
                Long l2 = (Long) map.get(aVar);
                if (l == null || l2 == null) {
                    return null;
                }
                int iA = rVar.o().a(l.longValue(), rVar);
                long jLongValue = ((Long) map.get(h.WEEK_OF_WEEK_BASED_YEAR)).longValue();
                h hVar4 = j.a;
                if (!j$.com.android.tools.r8.a.E(xVar).equals(j$.time.chrono.t.c)) {
                    throw new j$.time.b("Resolve requires IsoChronology");
                }
                j$.time.h hVarX = j$.time.h.X(iA, 1, 4);
                if (yVar == y.LENIENT) {
                    long jLongValue2 = l2.longValue();
                    if (jLongValue2 > 7) {
                        long j3 = jLongValue2 - 1;
                        j = 1;
                        hVarX = hVarX.d0(j3 / 7);
                        j2 = j3 % 7;
                    } else {
                        j = 1;
                        if (jLongValue2 < 1) {
                            hVarX = hVarX.d0(j$.com.android.tools.r8.a.M(jLongValue2, 7L) / 7);
                            j2 = (jLongValue2 + 6) % 7;
                        }
                        hVarC = hVarX.d0(j$.com.android.tools.r8.a.M(jLongValue, j)).c(jLongValue2, aVar);
                    }
                    jLongValue2 = j2 + j;
                    hVarC = hVarX.d0(j$.com.android.tools.r8.a.M(jLongValue, j)).c(jLongValue2, aVar);
                } else {
                    int iA2 = aVar.b.a(l2.longValue(), aVar);
                    if (jLongValue < 1 || jLongValue > 52) {
                        if (yVar == y.STRICT) {
                            h.P(hVarX).b(jLongValue, this);
                        } else {
                            o().b(jLongValue, this);
                        }
                    }
                    hVarC = hVarX.d0(jLongValue - 1).c(iA2, aVar);
                }
                map.remove(this);
                map.remove(rVar);
                map.remove(aVar);
                return hVarC;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekOfWeekBasedYear";
            }
        };
        WEEK_OF_WEEK_BASED_YEAR = hVar3;
        h hVar4 = new h() { // from class: j$.time.temporal.g
            @Override // j$.time.temporal.r
            public final v o() {
                return a.YEAR.b;
            }

            @Override // j$.time.temporal.r
            public final boolean j(n nVar) {
                if (!nVar.e(a.EPOCH_DAY)) {
                    return false;
                }
                h hVar5 = j.a;
                return j$.com.android.tools.r8.a.E(nVar).equals(j$.time.chrono.t.c);
            }

            @Override // j$.time.temporal.r
            public final long w(n nVar) {
                if (j(nVar)) {
                    return h.N(j$.time.h.P(nVar));
                }
                throw new u("Unsupported field: WeekBasedYear");
            }

            @Override // j$.time.temporal.r
            public final v k(n nVar) {
                if (!j(nVar)) {
                    throw new u("Unsupported field: WeekBasedYear");
                }
                return o();
            }

            @Override // j$.time.temporal.r
            public final m B(m mVar, long j) {
                if (!j(mVar)) {
                    throw new u("Unsupported field: WeekBasedYear");
                }
                int iA = a.YEAR.b.a(j, h.WEEK_BASED_YEAR);
                j$.time.h hVarP = j$.time.h.P(mVar);
                int iJ = hVarP.j(a.DAY_OF_WEEK);
                int iC = h.C(hVarP);
                if (iC == 53 && h.O(iA) == 52) {
                    iC = 52;
                }
                return mVar.v(j$.time.h.X(iA, 1, 4).b0(((iC - 1) * 7) + (iJ - r6.j(r0))));
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekBasedYear";
            }
        };
        WEEK_BASED_YEAR = hVar4;
        b = new h[]{hVar, hVar2, hVar3, hVar4};
        a = new int[]{0, 90, 181, 273, 0, 91, 182, DefaultImageHeaderParser.ORIENTATION_TAG_TYPE};
    }

    public static v P(j$.time.h hVar) {
        return v.f(1L, O(N(hVar)));
    }

    public static int O(int i) {
        j$.time.h hVarX = j$.time.h.X(i, 1, 1);
        if (hVarX.R() != j$.time.d.THURSDAY) {
            return (hVarX.R() == j$.time.d.WEDNESDAY && hVarX.U()) ? 53 : 52;
        }
        return 53;
    }

    public static int C(j$.time.h hVar) {
        int iOrdinal = hVar.R().ordinal();
        int iS = hVar.S() - 1;
        int i = (3 - iOrdinal) + iS;
        int i2 = i - ((i / 7) * 7);
        int i3 = i2 - 3;
        if (i3 < -3) {
            i3 = i2 + 4;
        }
        if (iS >= i3) {
            int i4 = ((iS - i3) / 7) + 1;
            if (i4 != 53 || i3 == -3 || (i3 == -2 && hVar.U())) {
                return i4;
            }
            return 1;
        }
        if (hVar.S() != 180) {
            hVar = j$.time.h.Z(hVar.a, 180);
        }
        return (int) P(hVar.e0(-1L)).d;
    }

    public static int N(j$.time.h hVar) {
        int i = hVar.a;
        int iS = hVar.S();
        if (iS <= 3) {
            return iS - hVar.R().ordinal() < -2 ? i - 1 : i;
        }
        if (iS >= 363) {
            return ((iS - 363) - (hVar.U() ? 1 : 0)) - hVar.R().ordinal() >= 0 ? i + 1 : i;
        }
        return i;
    }
}
