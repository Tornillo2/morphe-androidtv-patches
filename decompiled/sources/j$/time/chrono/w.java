package j$.time.chrono;

import j$.time.Instant;
import j$.time.ZoneId;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.time.InstantKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class w extends a implements Serializable {
    public static final w c = new w();
    private static final long serialVersionUID = 459996390165777884L;

    @Override // j$.time.chrono.m
    public final String i() {
        return "Japanese";
    }

    @Override // j$.time.chrono.m
    public final String m() {
        return "japanese";
    }

    @Override // j$.time.chrono.m
    public final b H(int i, int i2, int i3) {
        return new y(j$.time.h.X(i, i2, i3));
    }

    @Override // j$.time.chrono.m
    public final b n(int i, int i2) {
        return new y(j$.time.h.Z(i, i2));
    }

    @Override // j$.time.chrono.m
    public final b h(long j) {
        return new y(j$.time.h.Y(j));
    }

    @Override // j$.time.chrono.a
    public final b k() {
        return new y(j$.time.h.P(j$.time.h.W(j$.com.android.tools.r8.a.Q())));
    }

    @Override // j$.time.chrono.m
    public final b y(j$.time.temporal.n nVar) {
        if (nVar instanceof y) {
            return (y) nVar;
        }
        return new y(j$.time.h.P(nVar));
    }

    @Override // j$.time.chrono.m
    public final List r() {
        z[] zVarArr = z.e;
        return j$.com.android.tools.r8.a.G((z[]) Arrays.copyOf(zVarArr, zVarArr.length));
    }

    private w() {
    }

    @Override // j$.time.chrono.m
    public final int t(n nVar, int i) {
        if (!(nVar instanceof z)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        int i2 = ((z) nVar).b.a;
        int i3 = (i2 + i) - 1;
        if (i != 1 && (i3 < -999999999 || i3 > 999999999 || i3 < i2 || nVar != z.h(j$.time.h.X(i3, 1, 1)))) {
            throw new j$.time.b("Invalid yearOfEra value");
        }
        return i3;
    }

    @Override // j$.time.chrono.m
    public final n s(int i) {
        return z.m(i);
    }

    @Override // j$.time.chrono.m
    public final j$.time.temporal.v q(j$.time.temporal.a aVar) {
        switch (v.a[aVar.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                throw new j$.time.temporal.u("Unsupported field: " + aVar);
            case 5:
                z[] zVarArr = z.e;
                int i = zVarArr[zVarArr.length - 1].b.a;
                int iMin = InstantKt.NANOS_PER_SECOND - zVarArr[zVarArr.length - 1].b.a;
                int i2 = zVarArr[0].b.a;
                int i3 = 1;
                while (true) {
                    z[] zVarArr2 = z.e;
                    if (i3 >= zVarArr2.length) {
                        return j$.time.temporal.v.g(iMin, 999999999 - i);
                    }
                    z zVar = zVarArr2[i3];
                    iMin = Math.min(iMin, (zVar.b.a - i2) + 1);
                    i2 = zVar.b.a;
                    i3++;
                }
                break;
            case 6:
                z zVar2 = z.d;
                long jMin = j$.time.temporal.a.DAY_OF_YEAR.b.c;
                for (z zVar3 : z.e) {
                    jMin = Math.min(jMin, ((zVar3.b.U() ? 366 : 365) - zVar3.b.S()) + 1);
                    if (zVar3.i() != null) {
                        jMin = Math.min(jMin, zVar3.i().b.S() - 1);
                    }
                }
                return j$.time.temporal.v.g(jMin, j$.time.temporal.a.DAY_OF_YEAR.b.d);
            case 7:
                return j$.time.temporal.v.f(y.d.a, 999999999L);
            case 8:
                long j = z.d.a;
                z[] zVarArr3 = z.e;
                return j$.time.temporal.v.f(j, zVarArr3[zVarArr3.length - 1].a);
            default:
                return aVar.b;
        }
    }

    @Override // j$.time.chrono.a, j$.time.chrono.m
    public final b J(Map map, j$.time.format.y yVar) {
        return (y) super.J(map, yVar);
    }

    @Override // j$.time.chrono.a
    public final b C(Map map, j$.time.format.y yVar) {
        j$.time.h hVarZ;
        y yVarU;
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        Long l = (Long) map.get(aVar);
        z zVarM = l != null ? z.m(q(aVar).a(l.longValue(), aVar)) : null;
        j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR_OF_ERA;
        Long l2 = (Long) map.get(aVar2);
        int iA = l2 != null ? q(aVar2).a(l2.longValue(), aVar2) : 0;
        if (zVarM == null && l2 != null && !map.containsKey(j$.time.temporal.a.YEAR) && yVar != j$.time.format.y.STRICT) {
            z[] zVarArr = z.e;
            zVarM = ((z[]) Arrays.copyOf(zVarArr, zVarArr.length))[((z[]) Arrays.copyOf(zVarArr, zVarArr.length)).length - 1];
        }
        if (l2 != null && zVarM != null) {
            j$.time.temporal.a aVar3 = j$.time.temporal.a.MONTH_OF_YEAR;
            if (map.containsKey(aVar3)) {
                j$.time.temporal.a aVar4 = j$.time.temporal.a.DAY_OF_MONTH;
                if (map.containsKey(aVar4)) {
                    map.remove(aVar);
                    map.remove(aVar2);
                    if (yVar == j$.time.format.y.LENIENT) {
                        return new y(j$.time.h.X((zVarM.b.a + iA) - 1, 1, 1)).S(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar3)).longValue(), 1L), j$.time.temporal.b.MONTHS).S(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar4)).longValue(), 1L), j$.time.temporal.b.DAYS);
                    }
                    int iA2 = q(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    int iA3 = q(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4);
                    if (yVar != j$.time.format.y.SMART) {
                        j$.time.h hVar = y.d;
                        Objects.requireNonNull(zVarM, "era");
                        j$.time.h hVarX = j$.time.h.X((zVarM.b.a + iA) - 1, iA2, iA3);
                        if (hVarX.T(zVarM.b) || zVarM != z.h(hVarX)) {
                            throw new j$.time.b("year, month, and day not valid for Era");
                        }
                        return new y(zVarM, iA, hVarX);
                    }
                    if (iA < 1) {
                        throw new j$.time.b("Invalid YearOfEra: " + iA);
                    }
                    int i = (zVarM.b.a + iA) - 1;
                    try {
                        yVarU = new y(j$.time.h.X(i, iA2, iA3));
                    } catch (j$.time.b unused) {
                        yVarU = new y(j$.time.h.X(i, iA2, 1)).U(new j$.time.e(2));
                    }
                    if (yVarU.b == zVarM || j$.time.temporal.s.a(yVarU, j$.time.temporal.a.YEAR_OF_ERA) <= 1 || iA <= 1) {
                        return yVarU;
                    }
                    throw new j$.time.b("Invalid YearOfEra for Era: " + zVarM + StringUtils.SPACE + iA);
                }
            }
            j$.time.temporal.a aVar5 = j$.time.temporal.a.DAY_OF_YEAR;
            if (map.containsKey(aVar5)) {
                map.remove(aVar);
                map.remove(aVar2);
                if (yVar == j$.time.format.y.LENIENT) {
                    return new y(j$.time.h.Z((zVarM.b.a + iA) - 1, 1)).S(j$.com.android.tools.r8.a.M(((Long) map.remove(aVar5)).longValue(), 1L), j$.time.temporal.b.DAYS);
                }
                int iA4 = q(aVar5).a(((Long) map.remove(aVar5)).longValue(), aVar5);
                j$.time.h hVar2 = y.d;
                Objects.requireNonNull(zVarM, "era");
                if (iA == 1) {
                    j$.time.h hVar3 = zVarM.b;
                    hVarZ = j$.time.h.Z(hVar3.a, (hVar3.S() + iA4) - 1);
                } else {
                    hVarZ = j$.time.h.Z((zVarM.b.a + iA) - 1, iA4);
                }
                if (hVarZ.T(zVarM.b) || zVarM != z.h(hVarZ)) {
                    throw new j$.time.b("Invalid parameters");
                }
                return new y(zVarM, iA, hVarZ);
            }
        }
        return null;
    }

    @Override // j$.time.chrono.m
    public final j K(Instant instant, ZoneId zoneId) {
        return l.O(this, instant, zoneId);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public Object writeReplace() {
        return new f0((byte) 1, this);
    }
}
