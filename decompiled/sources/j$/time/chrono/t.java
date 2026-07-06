package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class t extends a implements Serializable {
    public static final t c = new t();
    private static final long serialVersionUID = -1440403870442975015L;

    @Override // j$.time.chrono.m
    public final n s(int i) {
        if (i == 0) {
            return u.BCE;
        }
        if (i == 1) {
            return u.CE;
        }
        throw new j$.time.b("Invalid era: " + i);
    }

    @Override // j$.time.chrono.m
    public final String i() {
        return ExifInterface.TAG_RW2_ISO;
    }

    @Override // j$.time.chrono.m
    public final String m() {
        return "iso8601";
    }

    @Override // j$.time.chrono.m
    public final b H(int i, int i2, int i3) {
        return j$.time.h.X(i, i2, i3);
    }

    @Override // j$.time.chrono.m
    public final b n(int i, int i2) {
        return j$.time.h.Z(i, i2);
    }

    @Override // j$.time.chrono.m
    public final b h(long j) {
        return j$.time.h.Y(j);
    }

    @Override // j$.time.chrono.m
    public final b y(j$.time.temporal.n nVar) {
        return j$.time.h.P(nVar);
    }

    private t() {
    }

    @Override // j$.time.chrono.a, j$.time.chrono.m
    public final e z(j$.time.j jVar) {
        return j$.time.j.O(jVar);
    }

    @Override // j$.time.chrono.m
    public final j K(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return j$.time.c0.o(instant.getEpochSecond(), instant.getNano(), zoneId);
    }

    @Override // j$.time.chrono.a
    public final b k() {
        j$.time.a aVarQ = j$.com.android.tools.r8.a.Q();
        Objects.requireNonNull(aVarQ, "clock");
        return j$.time.h.P(j$.time.h.W(aVarQ));
    }

    public static boolean N(long j) {
        if ((3 & j) == 0) {
            return j % 100 != 0 || j % 400 == 0;
        }
        return false;
    }

    @Override // j$.time.chrono.m
    public final int t(n nVar, int i) {
        if (nVar instanceof u) {
            return nVar == u.CE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // j$.time.chrono.m
    public final List r() {
        return j$.com.android.tools.r8.a.G(u.values());
    }

    @Override // j$.time.chrono.a, j$.time.chrono.m
    public final b J(Map map, j$.time.format.y yVar) {
        return (j$.time.h) super.J(map, yVar);
    }

    @Override // j$.time.chrono.a
    public final void w(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long l = (Long) map.remove(aVar);
        if (l != null) {
            if (yVar != j$.time.format.y.LENIENT) {
                aVar.C(l.longValue());
            }
            a.j(map, j$.time.temporal.a.MONTH_OF_YEAR, ((int) j$.com.android.tools.r8.a.J(l.longValue(), r4)) + 1);
            a.j(map, j$.time.temporal.a.YEAR, j$.com.android.tools.r8.a.K(l.longValue(), 12));
        }
    }

    @Override // j$.time.chrono.a
    public final b C(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR_OF_ERA;
        Long l = (Long) map.remove(aVar);
        if (l != null) {
            if (yVar != j$.time.format.y.LENIENT) {
                aVar.C(l.longValue());
            }
            Long l2 = (Long) map.remove(j$.time.temporal.a.ERA);
            if (l2 != null) {
                if (l2.longValue() == 1) {
                    a.j(map, j$.time.temporal.a.YEAR, l.longValue());
                    return null;
                }
                if (l2.longValue() == 0) {
                    a.j(map, j$.time.temporal.a.YEAR, j$.com.android.tools.r8.a.M(1L, l.longValue()));
                    return null;
                }
                throw new j$.time.b("Invalid value for era: " + l2);
            }
            j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR;
            Long l3 = (Long) map.get(aVar2);
            if (yVar != j$.time.format.y.STRICT) {
                a.j(map, aVar2, (l3 == null || l3.longValue() > 0) ? l.longValue() : j$.com.android.tools.r8.a.M(1L, l.longValue()));
                return null;
            }
            if (l3 != null) {
                long jLongValue = l3.longValue();
                long jLongValue2 = l.longValue();
                if (jLongValue <= 0) {
                    jLongValue2 = j$.com.android.tools.r8.a.M(1L, jLongValue2);
                }
                a.j(map, aVar2, jLongValue2);
                return null;
            }
            map.put(aVar, l);
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.ERA;
        if (!map.containsKey(aVar3)) {
            return null;
        }
        aVar3.C(((Long) map.get(aVar3)).longValue());
        return null;
    }

    @Override // j$.time.chrono.a
    public final b B(Map map, j$.time.format.y yVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int iA = aVar.b.a(((Long) map.remove(aVar)).longValue(), aVar);
        boolean z = true;
        if (yVar == j$.time.format.y.LENIENT) {
            return j$.time.h.X(iA, 1, 1).c0(j$.com.android.tools.r8.a.M(((Long) map.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1L)).b0(j$.com.android.tools.r8.a.M(((Long) map.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1L));
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        int iA2 = aVar2.b.a(((Long) map.remove(aVar2)).longValue(), aVar2);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        int iA3 = aVar3.b.a(((Long) map.remove(aVar3)).longValue(), aVar3);
        if (yVar == j$.time.format.y.SMART) {
            if (iA2 == 4 || iA2 == 6 || iA2 == 9 || iA2 == 11) {
                iA3 = Math.min(iA3, 30);
            } else if (iA2 == 2) {
                j$.time.n nVar = j$.time.n.FEBRUARY;
                long j = iA;
                int i = j$.time.w.b;
                if ((3 & j) != 0 || (j % 100 == 0 && j % 400 != 0)) {
                    z = false;
                }
                iA3 = Math.min(iA3, nVar.O(z));
            }
        }
        return j$.time.h.X(iA, iA2, iA3);
    }

    @Override // j$.time.chrono.m
    public final j$.time.temporal.v q(j$.time.temporal.a aVar) {
        return aVar.b;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public Object writeReplace() {
        return new f0((byte) 1, this);
    }
}
