package j$.time.format;

import j$.time.Instant;
import j$.time.ZoneId;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class x implements j$.time.temporal.n {
    public ZoneId b;
    public j$.time.chrono.m c;
    public boolean d;
    public y e;
    public j$.time.chrono.b f;
    public j$.time.l g;
    public final Map a = new HashMap();
    public j$.time.t h = j$.time.t.d;

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.a(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ j$.time.temporal.v l(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final boolean e(j$.time.temporal.r rVar) {
        if (((HashMap) this.a).containsKey(rVar)) {
            return true;
        }
        j$.time.chrono.b bVar = this.f;
        if (bVar != null && bVar.e(rVar)) {
            return true;
        }
        j$.time.l lVar = this.g;
        if (lVar == null || !lVar.e(rVar)) {
            return (rVar == null || (rVar instanceof j$.time.temporal.a) || !rVar.j(this)) ? false : true;
        }
        return true;
    }

    @Override // j$.time.temporal.n
    public final long C(j$.time.temporal.r rVar) {
        Objects.requireNonNull(rVar, "field");
        Long l = (Long) ((HashMap) this.a).get(rVar);
        if (l != null) {
            return l.longValue();
        }
        j$.time.chrono.b bVar = this.f;
        if (bVar != null && bVar.e(rVar)) {
            return this.f.C(rVar);
        }
        j$.time.l lVar = this.g;
        if (lVar != null && lVar.e(rVar)) {
            return this.g.C(rVar);
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.u(j$.time.c.a("Unsupported field: ", rVar));
        }
        return rVar.w(this);
    }

    @Override // j$.time.temporal.n
    public final Object B(j$.time.e eVar) {
        if (eVar == j$.time.temporal.s.a) {
            return this.b;
        }
        if (eVar == j$.time.temporal.s.b) {
            return this.c;
        }
        if (eVar == j$.time.temporal.s.f) {
            j$.time.chrono.b bVar = this.f;
            if (bVar != null) {
                return j$.time.h.P(bVar);
            }
            return null;
        }
        if (eVar == j$.time.temporal.s.g) {
            return this.g;
        }
        if (eVar == j$.time.temporal.s.d) {
            Long l = (Long) ((HashMap) this.a).get(j$.time.temporal.a.OFFSET_SECONDS);
            if (l != null) {
                return j$.time.z.U(l.intValue());
            }
            ZoneId zoneId = this.b;
            return zoneId instanceof j$.time.z ? zoneId : eVar.g(this);
        }
        if (eVar == j$.time.temporal.s.e) {
            return eVar.g(this);
        }
        if (eVar == j$.time.temporal.s.c) {
            return null;
        }
        return eVar.g(this);
    }

    public final void s(j$.time.temporal.r rVar, j$.time.temporal.a aVar, Long l) {
        Long l2 = (Long) ((HashMap) this.a).put(aVar, l);
        if (l2 == null || l2.longValue() == l.longValue()) {
            return;
        }
        throw new j$.time.b("Conflict found: " + aVar + StringUtils.SPACE + l2 + " differs from " + aVar + StringUtils.SPACE + l + " while resolving  " + rVar);
    }

    public final void i() {
        if (((HashMap) this.a).containsKey(j$.time.temporal.a.INSTANT_SECONDS)) {
            ZoneId zoneId = this.b;
            if (zoneId != null) {
                m(zoneId);
                return;
            }
            Long l = (Long) ((HashMap) this.a).get(j$.time.temporal.a.OFFSET_SECONDS);
            if (l != null) {
                m(j$.time.z.U(l.intValue()));
            }
        }
    }

    public final void m(ZoneId zoneId) {
        Map map = this.a;
        j$.time.temporal.a aVar = j$.time.temporal.a.INSTANT_SECONDS;
        r(this.c.K(Instant.N(((Long) ((HashMap) map).remove(aVar)).longValue(), 0), zoneId).f());
        s(aVar, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(r5.b().a0()));
    }

    public final void r(j$.time.chrono.b bVar) {
        j$.time.chrono.b bVar2 = this.f;
        if (bVar2 != null) {
            if (bVar == null || bVar2.equals(bVar)) {
                return;
            }
            throw new j$.time.b("Conflict found: Fields resolved to two different dates: " + this.f + StringUtils.SPACE + bVar);
        }
        if (bVar != null) {
            if (!this.c.equals(bVar.a())) {
                throw new j$.time.b("ChronoLocalDate must use the effective parsed chronology: " + this.c);
            }
            this.f = bVar;
        }
    }

    public final void o() {
        Map map = this.a;
        j$.time.temporal.a aVar = j$.time.temporal.a.CLOCK_HOUR_OF_DAY;
        if (((HashMap) map).containsKey(aVar)) {
            long jLongValue = ((Long) ((HashMap) this.a).remove(aVar)).longValue();
            y yVar = this.e;
            if (yVar == y.STRICT || (yVar == y.SMART && jLongValue != 0)) {
                aVar.C(jLongValue);
            }
            j$.time.temporal.a aVar2 = j$.time.temporal.a.HOUR_OF_DAY;
            if (jLongValue == 24) {
                jLongValue = 0;
            }
            s(aVar, aVar2, Long.valueOf(jLongValue));
        }
        Map map2 = this.a;
        j$.time.temporal.a aVar3 = j$.time.temporal.a.CLOCK_HOUR_OF_AMPM;
        if (((HashMap) map2).containsKey(aVar3)) {
            long jLongValue2 = ((Long) ((HashMap) this.a).remove(aVar3)).longValue();
            y yVar2 = this.e;
            if (yVar2 == y.STRICT || (yVar2 == y.SMART && jLongValue2 != 0)) {
                aVar3.C(jLongValue2);
            }
            s(aVar3, j$.time.temporal.a.HOUR_OF_AMPM, Long.valueOf(jLongValue2 != 12 ? jLongValue2 : 0L));
        }
        Map map3 = this.a;
        j$.time.temporal.a aVar4 = j$.time.temporal.a.AMPM_OF_DAY;
        if (((HashMap) map3).containsKey(aVar4)) {
            Map map4 = this.a;
            j$.time.temporal.a aVar5 = j$.time.temporal.a.HOUR_OF_AMPM;
            if (((HashMap) map4).containsKey(aVar5)) {
                long jLongValue3 = ((Long) ((HashMap) this.a).remove(aVar4)).longValue();
                long jLongValue4 = ((Long) ((HashMap) this.a).remove(aVar5)).longValue();
                if (this.e == y.LENIENT) {
                    s(aVar4, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.L(jLongValue3, 12), jLongValue4)));
                } else {
                    aVar4.C(jLongValue3);
                    aVar5.C(jLongValue3);
                    s(aVar4, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf((jLongValue3 * 12) + jLongValue4));
                }
            }
        }
        Map map5 = this.a;
        j$.time.temporal.a aVar6 = j$.time.temporal.a.NANO_OF_DAY;
        if (((HashMap) map5).containsKey(aVar6)) {
            long jLongValue5 = ((Long) ((HashMap) this.a).remove(aVar6)).longValue();
            if (this.e != y.LENIENT) {
                aVar6.C(jLongValue5);
            }
            s(aVar6, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue5 / 3600000000000L));
            s(aVar6, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((jLongValue5 / 60000000000L) % 60));
            s(aVar6, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf((jLongValue5 / 1000000000) % 60));
            s(aVar6, j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(jLongValue5 % 1000000000));
        }
        Map map6 = this.a;
        j$.time.temporal.a aVar7 = j$.time.temporal.a.MICRO_OF_DAY;
        if (((HashMap) map6).containsKey(aVar7)) {
            long jLongValue6 = ((Long) ((HashMap) this.a).remove(aVar7)).longValue();
            if (this.e != y.LENIENT) {
                aVar7.C(jLongValue6);
            }
            s(aVar7, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(jLongValue6 / 1000000));
            s(aVar7, j$.time.temporal.a.MICRO_OF_SECOND, Long.valueOf(jLongValue6 % 1000000));
        }
        Map map7 = this.a;
        j$.time.temporal.a aVar8 = j$.time.temporal.a.MILLI_OF_DAY;
        if (((HashMap) map7).containsKey(aVar8)) {
            long jLongValue7 = ((Long) ((HashMap) this.a).remove(aVar8)).longValue();
            if (this.e != y.LENIENT) {
                aVar8.C(jLongValue7);
            }
            s(aVar8, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(jLongValue7 / 1000));
            s(aVar8, j$.time.temporal.a.MILLI_OF_SECOND, Long.valueOf(jLongValue7 % 1000));
        }
        Map map8 = this.a;
        j$.time.temporal.a aVar9 = j$.time.temporal.a.SECOND_OF_DAY;
        if (((HashMap) map8).containsKey(aVar9)) {
            long jLongValue8 = ((Long) ((HashMap) this.a).remove(aVar9)).longValue();
            if (this.e != y.LENIENT) {
                aVar9.C(jLongValue8);
            }
            s(aVar9, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue8 / 3600));
            s(aVar9, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((jLongValue8 / 60) % 60));
            s(aVar9, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf(jLongValue8 % 60));
        }
        Map map9 = this.a;
        j$.time.temporal.a aVar10 = j$.time.temporal.a.MINUTE_OF_DAY;
        if (((HashMap) map9).containsKey(aVar10)) {
            long jLongValue9 = ((Long) ((HashMap) this.a).remove(aVar10)).longValue();
            if (this.e != y.LENIENT) {
                aVar10.C(jLongValue9);
            }
            s(aVar10, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue9 / 60));
            s(aVar10, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf(jLongValue9 % 60));
        }
        Map map10 = this.a;
        j$.time.temporal.a aVar11 = j$.time.temporal.a.NANO_OF_SECOND;
        if (((HashMap) map10).containsKey(aVar11)) {
            long jLongValue10 = ((Long) ((HashMap) this.a).get(aVar11)).longValue();
            y yVar3 = this.e;
            y yVar4 = y.LENIENT;
            if (yVar3 != yVar4) {
                aVar11.C(jLongValue10);
            }
            Map map11 = this.a;
            j$.time.temporal.a aVar12 = j$.time.temporal.a.MICRO_OF_SECOND;
            if (((HashMap) map11).containsKey(aVar12)) {
                long jLongValue11 = ((Long) ((HashMap) this.a).remove(aVar12)).longValue();
                if (this.e != yVar4) {
                    aVar12.C(jLongValue11);
                }
                jLongValue10 = (jLongValue10 % 1000) + (jLongValue11 * 1000);
                s(aVar12, aVar11, Long.valueOf(jLongValue10));
            }
            Map map12 = this.a;
            j$.time.temporal.a aVar13 = j$.time.temporal.a.MILLI_OF_SECOND;
            if (((HashMap) map12).containsKey(aVar13)) {
                long jLongValue12 = ((Long) ((HashMap) this.a).remove(aVar13)).longValue();
                if (this.e != yVar4) {
                    aVar13.C(jLongValue12);
                }
                s(aVar13, aVar11, Long.valueOf((jLongValue10 % 1000000) + (jLongValue12 * 1000000)));
            }
        }
        Map map13 = this.a;
        j$.time.temporal.a aVar14 = j$.time.temporal.a.HOUR_OF_DAY;
        if (((HashMap) map13).containsKey(aVar14)) {
            Map map14 = this.a;
            j$.time.temporal.a aVar15 = j$.time.temporal.a.MINUTE_OF_HOUR;
            if (((HashMap) map14).containsKey(aVar15)) {
                Map map15 = this.a;
                j$.time.temporal.a aVar16 = j$.time.temporal.a.SECOND_OF_MINUTE;
                if (((HashMap) map15).containsKey(aVar16) && ((HashMap) this.a).containsKey(aVar11)) {
                    n(((Long) ((HashMap) this.a).remove(aVar14)).longValue(), ((Long) ((HashMap) this.a).remove(aVar15)).longValue(), ((Long) ((HashMap) this.a).remove(aVar16)).longValue(), ((Long) ((HashMap) this.a).remove(aVar11)).longValue());
                }
            }
        }
    }

    public final void n(long j, long j2, long j3, long j4) {
        if (this.e == y.LENIENT) {
            long jF = j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.F(j$.com.android.tools.r8.a.L(j, 3600000000000L), j$.com.android.tools.r8.a.L(j2, 60000000000L)), j$.com.android.tools.r8.a.L(j3, 1000000000L)), j4);
            q(j$.time.l.S(j$.com.android.tools.r8.a.J(jF, 86400000000000L)), j$.time.t.a(0, 0, (int) j$.com.android.tools.r8.a.K(jF, 86400000000000L)));
            return;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.MINUTE_OF_HOUR;
        int iA = aVar.b.a(j2, aVar);
        j$.time.temporal.a aVar2 = j$.time.temporal.a.NANO_OF_SECOND;
        int iA2 = aVar2.b.a(j4, aVar2);
        if (this.e == y.SMART && j == 24 && iA == 0 && j3 == 0 && iA2 == 0) {
            q(j$.time.l.g, j$.time.t.a(0, 0, 1));
            return;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.HOUR_OF_DAY;
        int iA3 = aVar3.b.a(j, aVar3);
        j$.time.temporal.a aVar4 = j$.time.temporal.a.SECOND_OF_MINUTE;
        q(j$.time.l.R(iA3, iA, aVar4.b.a(j3, aVar4), iA2), j$.time.t.d);
    }

    public final void q(j$.time.l lVar, j$.time.t tVar) {
        j$.time.l lVar2 = this.g;
        if (lVar2 != null) {
            if (!lVar2.equals(lVar)) {
                throw new j$.time.b("Conflict found: Fields resolved to different times: " + this.g + StringUtils.SPACE + lVar);
            }
            j$.time.t tVar2 = this.h;
            tVar2.getClass();
            j$.time.t tVar3 = j$.time.t.d;
            if (tVar2 != tVar3 && tVar != tVar3 && !this.h.equals(tVar)) {
                throw new j$.time.b("Conflict found: Fields resolved to different excess periods: " + this.h + StringUtils.SPACE + tVar);
            }
            this.h = tVar;
            return;
        }
        this.g = lVar;
        this.h = tVar;
    }

    public final void h(j$.time.temporal.n nVar) {
        Iterator it = ((HashMap) this.a).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            j$.time.temporal.r rVar = (j$.time.temporal.r) entry.getKey();
            if (nVar.e(rVar)) {
                try {
                    long jC = nVar.C(rVar);
                    long jLongValue = ((Long) entry.getValue()).longValue();
                    if (jC != jLongValue) {
                        throw new j$.time.b("Conflict found: Field " + rVar + StringUtils.SPACE + jC + " differs from " + rVar + StringUtils.SPACE + jLongValue + " derived from " + nVar);
                    }
                    it.remove();
                } catch (RuntimeException unused) {
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.a);
        sb.append(',');
        sb.append(this.c);
        if (this.b != null) {
            sb.append(',');
            sb.append(this.b);
        }
        if (this.f != null || this.g != null) {
            sb.append(" resolved to ");
            j$.time.chrono.b bVar = this.f;
            if (bVar != null) {
                sb.append(bVar);
                if (this.g != null) {
                    sb.append('T');
                    sb.append(this.g);
                }
            } else {
                sb.append(this.g);
            }
        }
        return sb.toString();
    }
}
