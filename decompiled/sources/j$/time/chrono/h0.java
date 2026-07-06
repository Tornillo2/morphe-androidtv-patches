package j$.time.chrono;

import j$.time.Instant;
import j$.time.ZoneId;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class h0 extends a implements Serializable {
    public static final h0 c = new h0();
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map.put("en", new String[]{"BB", "BE"});
        map.put("th", new String[]{"BB", "BE"});
        map2.put("en", new String[]{"B.B.", "B.E."});
        map2.put("th", new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        map3.put("en", new String[]{"Before Buddhist", "Budhhist Era"});
        map3.put("th", new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    @Override // j$.time.chrono.m
    public final n s(int i) {
        if (i == 0) {
            return k0.BEFORE_BE;
        }
        if (i == 1) {
            return k0.BE;
        }
        throw new j$.time.b("Invalid era: " + i);
    }

    @Override // j$.time.chrono.m
    public final String i() {
        return "ThaiBuddhist";
    }

    @Override // j$.time.chrono.m
    public final String m() {
        return "buddhist";
    }

    @Override // j$.time.chrono.m
    public final b H(int i, int i2, int i3) {
        return new j0(j$.time.h.X(i - 543, i2, i3));
    }

    @Override // j$.time.chrono.m
    public final b n(int i, int i2) {
        return new j0(j$.time.h.Z(i - 543, i2));
    }

    @Override // j$.time.chrono.m
    public final b h(long j) {
        return new j0(j$.time.h.Y(j));
    }

    @Override // j$.time.chrono.a
    public final b k() {
        return new j0(j$.time.h.P(j$.time.h.W(j$.com.android.tools.r8.a.Q())));
    }

    @Override // j$.time.chrono.m
    public final b y(j$.time.temporal.n nVar) {
        if (nVar instanceof j0) {
            return (j0) nVar;
        }
        return new j0(j$.time.h.P(nVar));
    }

    @Override // j$.time.chrono.m
    public final int t(n nVar, int i) {
        if (nVar instanceof k0) {
            return nVar == k0.BE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be BuddhistEra");
    }

    private h0() {
    }

    @Override // j$.time.chrono.m
    public final List r() {
        return j$.com.android.tools.r8.a.G(k0.values());
    }

    @Override // j$.time.chrono.m
    public final j$.time.temporal.v q(j$.time.temporal.a aVar) {
        int i = g0.a[aVar.ordinal()];
        if (i == 1) {
            j$.time.temporal.v vVar = j$.time.temporal.a.PROLEPTIC_MONTH.b;
            return j$.time.temporal.v.f(vVar.a + 6516, vVar.d + 6516);
        }
        if (i == 2) {
            j$.time.temporal.v vVar2 = j$.time.temporal.a.YEAR.b;
            return j$.time.temporal.v.g((-(vVar2.a + 543)) + 1, vVar2.d + 543);
        }
        if (i != 3) {
            return aVar.b;
        }
        j$.time.temporal.v vVar3 = j$.time.temporal.a.YEAR.b;
        return j$.time.temporal.v.f(vVar3.a + 543, vVar3.d + 543);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.m
    public final b J(Map map, j$.time.format.y yVar) {
        return (j0) super.J(map, yVar);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.m
    public final j K(Instant instant, ZoneId zoneId) {
        return l.O(this, instant, zoneId);
    }

    public Object writeReplace() {
        return new f0((byte) 1, this);
    }
}
